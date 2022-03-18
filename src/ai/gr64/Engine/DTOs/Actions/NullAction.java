package ai.gr64.Engine.DTOs.Actions;

import java.util.ArrayList;
import java.util.List;

import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;

public class NullAction implements IAction {

    @Override
    public void makeAction(GameState state) {
        // Intentionally does nothing
    }

    @Override
    public void unmakeAction(GameState state) {
        // Intentionally does nothing
    }

    @Override
    public List<ClearRow> getAvailableActions() {
        return new ArrayList<>(0);
    }
    
}
