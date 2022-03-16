package ai.gr64.Engine.DTOs.Actions;

import java.util.ArrayList;
import java.util.List;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;

public class ClearRow implements IAction {
    private int placementNode;
    private Direction direction;
    private List<Piece> beforeRow, afterRow;
    private List<ClearRow> availableActions = new ArrayList<>();

    public ClearRow(int placementNode, Direction direction) {
        this.placementNode = placementNode;
        this.direction = direction;
    }

    @Override
    public void makeAction(GameState state) {
        availableActions.addAll(state.getAvailableActions());
        availableActions.remove(this);
        if (beforeRow == null)
            beforeRow = state.getRow(placementNode, direction);
        state.clearRow(placementNode, direction);
        if (afterRow == null)
            afterRow = state.getRow(placementNode, direction);
        
        for (ClearRow clear : availableActions) {
            if (!state.canClearRow(clear.getPlacementNode(), clear.getDirection()))
                availableActions.remove(clear);
        }
    }

    @Override
    public void unmakeAction(GameState state) {
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
