package ai.gr64.Engine.DTOs.Actions;

import java.util.ArrayList;
import java.util.List;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;

public class ClearRow implements IAction {
    private int placementNode, whitePieces = 0, blackPieces = 0;
    private Direction direction;
    private List<Piece> beforeRow = new ArrayList<>(), afterRow = new ArrayList<>();
    private List<ClearRow> availableActions = new ArrayList<>();
    private Piece sourceColor;

    public ClearRow(int placementNode, Direction direction, Piece sourceColor) {
        this.placementNode = placementNode;
        this.direction = direction;
        this.sourceColor = sourceColor;
    }

    public Piece getSourceColor() {
        return sourceColor;
    }

    @Override
    public void makeAction(GameState state) {
        availableActions.addAll(state.getAvailableActions());
        availableActions.remove(this);
        if (beforeRow == null || beforeRow.isEmpty()) {
            state.getRow(placementNode, direction, beforeRow);
            
            for (Piece piece : beforeRow) {
                if (piece == Piece.WHITE)
                    whitePieces++;
                else if (piece == Piece.BLACK) 
                    blackPieces++;
            }
        }
        
        state.clearRow(placementNode, direction);
        
        state.setWhitePiecesOnBoard(state.getWhitePiecesOnBoard() - whitePieces);
        state.setBlackPiecesOnBoard(state.getBlackPiecesOnBoard() - blackPieces);

        if (this.sourceColor == Piece.WHITE) {
            state.setWhitePiecesLeft(state.getWhitePiecesLeft() + whitePieces);
        } else {
            state.setBlackPiecesLeft(state.getBlackPiecesLeft() + blackPieces);
        }
        if (afterRow == null)
            state.getRow(placementNode, direction, afterRow);
        
        for (ClearRow clear : availableActions) {
            if (!state.canClearRow(clear.getPlacementNode(), clear.getDirection()))
                availableActions.remove(clear);
        }
    }

    @Override
    public void unmakeAction(GameState state) {
        state.setWhitePiecesOnBoard(state.getWhitePiecesOnBoard() + whitePieces);
        state.setBlackPiecesOnBoard(state.getBlackPiecesOnBoard() + blackPieces);

        if (this.sourceColor == Piece.WHITE) {
            state.setWhitePiecesLeft(state.getWhitePiecesLeft() - whitePieces);
        } else {
            state.setBlackPiecesLeft(state.getBlackPiecesLeft() - blackPieces);
        }

        state.setRow(placementNode, direction, beforeRow);
    }

    @Override
    public List<ClearRow> getAvailableActions() {
        return availableActions;
    }

    public int getPlacementNode() {
        return placementNode;
    }

    public Direction getDirection() {
        return direction;
    }
}
