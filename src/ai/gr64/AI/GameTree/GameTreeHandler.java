package ai.gr64.AI.GameTree;

import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.NullAction;

public class GameTreeHandler {
    private static GameState state;
    private static GameTreeNode root;

    public static void init(GameState State) {
        state = State;
        root = new GameTreeNode(new NullAction(), state);
    }

    public static IAction getBestAction(int depth, Piece piece) {
        return root.getBestAction(piece == Piece.WHITE, depth);
    }

    public static void moveRoot(IAction action) {
        root = new GameTreeNode(action, state);
    }

}
