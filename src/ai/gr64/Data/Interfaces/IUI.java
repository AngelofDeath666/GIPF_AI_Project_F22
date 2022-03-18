package ai.gr64.Data.Interfaces;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.ClearRow;
import ai.gr64.Engine.DTOs.Actions.Move;

// Interface for all implementations of a User-Interface
public interface IUI {
    public Move getPlayerInput(GameState state);

    public void updateUi(GameState state);
    public ClearRow getClearRow(GameState state);

}

