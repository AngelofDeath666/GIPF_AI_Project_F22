package ai.gr64.Data.Interfaces;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public interface IUI {
    public Move GetPlayerInput();
    public void UpdateUi(GameState state);
    
}
