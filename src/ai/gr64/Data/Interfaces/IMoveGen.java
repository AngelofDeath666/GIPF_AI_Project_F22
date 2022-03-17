package ai.gr64.Data.Interfaces;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.Move;

// Interface for all moveGens for the game, a moveGen being a player-type, either a AI or an actual player
public interface IMoveGen {
    public Move NextMove(GameState state);
}
