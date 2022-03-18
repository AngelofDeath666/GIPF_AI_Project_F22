package ai.gr64.Engine.DTOs.Actions;

import java.util.List;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;

//DTO representing a move
public class Move implements IAction {
    private Piece piece;
    private int placementNode;
    private Direction direction;
    private List<Piece> beforeRow, afterRow;
    private List<ClearRow> availableActions;

    public Move(Piece piece, int placementNode, Direction direction) {
        this.piece = piece;
        this.placementNode = placementNode;
        this.direction = direction;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getPlacementNode() {
        return placementNode;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public List<ClearRow> getAvailableActions() {
        return availableActions;
    }

    @Override
    public void makeAction(GameState state) {
        if (afterRow != null) {
            state.setRow(placementNode, direction, afterRow);
            return;
        }

        if (piece == Piece.WHITE){
            state.setWhitePiecesLeft(state.getWhitePiecesLeft() - 1);
            state.setWhitePiecesOnBoard(state.getWhitePiecesOnBoard() + 1);
        } else {
            state.setBlackPiecesOnBoard(state.getBlackPiecesOnBoard() + 1);
            state.setBlackPiecesLeft(state.getBlackPiecesLeft() - 1);
        }

        if (beforeRow == null)
            beforeRow = state.getRow(placementNode, direction);
        this.availableActions = state.makeMove(this);
        state.setNextToPlace((piece == Piece.WHITE) ? Piece.BLACK : Piece.WHITE);
        if (afterRow == null)
            afterRow = state.getRow(placementNode, direction);
    }

    @Override
    public void unmakeAction(GameState state) {
        if (piece == Piece.WHITE) {
            state.setWhitePiecesLeft(state.getWhitePiecesLeft() + 1);
            state.setWhitePiecesOnBoard(state.getWhitePiecesOnBoard() - 1);
        } else {
            state.setBlackPiecesLeft(state.getBlackPiecesLeft() + 1);
            state.setBlackPiecesOnBoard(state.getBlackPiecesOnBoard() - 1);
        }
        state.setNextToPlace((piece != Piece.WHITE) ? Piece.BLACK : Piece.WHITE);
        state.setRow(placementNode, direction, beforeRow);
    }
}
