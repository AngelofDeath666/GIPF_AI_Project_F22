package ai.gr64.Data.Interfaces;

import java.util.List;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.ClearRow;

public interface IAction {
    public void makeAction(GameState state);
    public void unmakeAction(GameState state);
    public List<ClearRow> getAvailableActions();
}
