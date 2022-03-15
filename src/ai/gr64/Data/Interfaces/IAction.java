package ai.gr64.Data.Interfaces;

import ai.gr64.Engine.DTOs.GameState;

public interface IAction {
    public void makeAction(GameState state);
    public void unmakeAction(GameState state);
}
