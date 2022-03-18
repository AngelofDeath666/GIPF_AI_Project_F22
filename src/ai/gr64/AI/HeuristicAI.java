package ai.gr64.AI;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.Move;

// To be implemented, An AI which in some way will evaluate the board to make the smartest move, hopefully
public class HeuristicAI implements IMoveGen {

    @Override
    public Move NextMove(GameState state) {
        // TODO Auto-generated method stub
        return new Move(Piece.WHITE, 0, Direction.DOWN_RIGHT);
    }

    @Override
    public IAction GetClearRowAction(GameState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented.");
    }
    
}
