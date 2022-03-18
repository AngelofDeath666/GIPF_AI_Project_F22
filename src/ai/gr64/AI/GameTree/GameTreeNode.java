package ai.gr64.AI.GameTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Utils.BoardUtils;

public class GameTreeNode {
    private List<GameTreeNode> children = null;
    private IAction action;
    private GameState state;

    public GameTreeNode(IAction action, GameState state) {
        this.action = action;
        this.state = state;
    }

    public IAction getAction() {
        return action;
    }

    private void generateChildren() {
        List<IAction> actions = (state.getAvailableActions().isEmpty()
                                ? Arrays.asList(BoardUtils.GetAllMoves(state))
                                : ((List<IAction>) (List<?>) state.getAvailableActions()));
        children = new ArrayList<>(actions.size());
        for (IAction action : actions) {
            children.add(new GameTreeNode(action, state));
        }

    }

    public IAction getBestAction(boolean maximizing, int depth) {
        int bestValue = (maximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        GameTreeNode bestActionNode = null;
        if (children == null)
            generateChildren();
        for (GameTreeNode gameTreeNode : children) {
            state.makeAction(gameTreeNode.getAction());
            int value = gameTreeNode.alphaBeta(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, maximizing);
            if (maximizing) {
                if (value > bestValue) {
                    bestValue = value;
                    bestActionNode = gameTreeNode;
                }
            } else {
                if (value < bestValue) {
                    bestValue = value;
                    bestActionNode = gameTreeNode;
                }
            }
            state.unmakeAction();
        }
        return bestActionNode.action;
    }

    public int alphaBeta(int depth, int alpha, int beta, boolean maximizing) {
        boolean noActionsLeft = BoardUtils.getAllActions(state).isEmpty();
        if (depth == 0 && noActionsLeft) {
            return BoardUtils.evaluateBoard(state);
        }

        if (children == null) {
            generateChildren();
        }

        if (maximizing) {
            int value = Integer.MIN_VALUE;
            for (GameTreeNode gameTreeNode : children) {
                state.makeAction(gameTreeNode.getAction());
                value = Math.max(value, gameTreeNode.alphaBeta(depth - 1, alpha, beta, false));
                state.unmakeAction();
                if (value >= beta) {
                    break;
                }
                alpha = Math.max(alpha, value);
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (GameTreeNode gameTreeNode : children) {
                state.makeAction(action);
                value = Math.min(value, gameTreeNode.alphaBeta(depth - 1, alpha, beta, true));
                state.unmakeAction();
                if (value <= alpha) {
                    break;
                }
                beta = Math.min(beta, value);
            }
            return value;
        }
    }
}
