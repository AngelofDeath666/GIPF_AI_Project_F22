package ai.gr64.Data.Interfaces;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public interface IMoveGen {
    public Move NextMove(GameState state);
}
