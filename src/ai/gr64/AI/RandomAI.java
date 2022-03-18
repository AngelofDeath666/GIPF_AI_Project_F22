package ai.gr64.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Utils.BoardUtils;

//To be implemented, an AI choosing moves at random
public class RandomAI implements IMoveGen {
    private static Random rand = new Random(System.currentTimeMillis());

    @Override
    public IAction NextMove(GameState state) {
        List<IAction> actions = BoardUtils.getAllActions(state);
        return actions.get(rand.nextInt(actions.size()));
    }

    @Override
    public IAction GetClearRowAction(GameState state) {
        List<IAction> actions = BoardUtils.getAllActions(state);
        return actions.get(rand.nextInt(actions.size()));
    }
    
}
