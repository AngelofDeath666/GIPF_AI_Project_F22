package ai.gr64.AI;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

// To be implemented, will take a user-input from the UI to make it so a player can play the game.
public class PlayerMoveGen implements IMoveGen {
    private IUI UI;

    public PlayerMoveGen(IUI uI) {
        UI = uI;
    }

    
    @Override
    public Move NextMove(GameState state) {
        //return new Move(Piece.WHITE, 0, Direction.DOWN_RIGHT);
        return UI.GetPlayerInput(state);
    }
    
}
