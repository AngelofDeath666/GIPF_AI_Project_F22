package ai.gr64.Data.Interfaces;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

// Interface for all implementations of a User-Interface
public interface IUI {
    public Move GetPlayerInput(GameState state);

    public void UpdateUi(GameState state);

}
