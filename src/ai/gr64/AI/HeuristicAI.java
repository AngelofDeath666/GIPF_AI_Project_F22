package ai.gr64.AI;

import ai.gr64.AI.GameTree.GameTreeHandler;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Engine.DTOs.GameState;

// To be implemented, An AI which in some way will evaluate the board to make the smartest move, hopefully
public class HeuristicAI implements IMoveGen {
    private static int PLY = 4;

    @Override
    public IAction NextMove(GameState state) {
        return GameTreeHandler.getBestAction(PLY, state.getNextToPlace());
    }

    @Override
    public IAction GetClearRowAction(GameState state) {
        return GameTreeHandler.getBestAction(PLY, state.getNextToPlace() == Piece.WHITE ? Piece.BLACK : Piece.WHITE);
    }
    
    public static void setPLY(int pLY) {
        PLY = pLY;
    }
}
