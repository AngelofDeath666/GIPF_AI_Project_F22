package ai.gr64.AI;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.Move;

//To be implemented, an AI choosing moves at random
public class RandomAI implements IMoveGen {

    @Override
    public Move NextMove(GameState state) {
        return new Move(Piece.WHITE, 0, Direction.DOWN_RIGHT);
    }
    
}
