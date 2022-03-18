package ai.gr64.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.Move;

public class BoardUtils {
    // Returns all possible moves to that can be made, only legal moves are included
    public static Move[] GetAllMoves(GameState state) {
        List<Move> moves = new ArrayList<>();
        Move newMove;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= state.layers; j++) {
                newMove = new Move(null, i * 4 + j, Direction.fromValue(i + 3));
                if (state.movePossible(newMove))
                    moves.add(newMove);
                if (j != 0) {
                    newMove = new Move(null, i * 4 + j, Direction.fromValue(i + 4));
                    if (state.movePossible(newMove))
                        moves.add(newMove);
                }
            }
        }
        return moves.toArray(new Move[0]);
    }

    // Evaluates the board, if it is best for white a positive number is returned, if it is best for black a negative number is returned
    public static int evaluateBoard(GameState state) {
        boolean whitePlayerNext = state.getNextToPlace() == Piece.WHITE;
        int whitePiecesLeft = state.getWhitePiecesLeft();
        int blackPiecesLeft = state.getBlackPiecesLeft();
        int whitePiecesOnBoard = state.getWhitePiecesOnBoard();
        int blackPiecesOnBoard = state.getBlackPiecesOnBoard();

        if (whitePlayerNext) {
            if (whitePiecesLeft == 0)
                return Integer.MIN_VALUE;
        } else {
            if (blackPiecesLeft == 0)
                return Integer.MAX_VALUE;
        }

        return (whitePiecesLeft + whitePiecesOnBoard) - (blackPiecesLeft + blackPiecesOnBoard);
    }

    public static List<IAction> getAllActions(GameState state) {
        if (!state.getAvailableActions().isEmpty())
            return (List<IAction>)(List<?>)state.getAvailableActions();
        return Arrays.asList(GetAllMoves(state));
        // IAction[] moves = GetAllMoves(state);
        // List<IAction> actions = new ArrayList<>();
        // for (IAction moveAction : moves) {
        //     actions.add(moveAction);
        // }
        // actions.addAll(state.getAvailableActions());
        // return actions;
    }

    private static Map<Integer, int[]> cachedLayers = new HashMap<Integer, int[]>();

    public static int[] LayerEnds (int layers){
        if (cachedLayers.containsKey(layers))
            return cachedLayers.get(layers);

        int[] layerEnds = new int[2 * layers + 3];

        //Loop to calculate each of the layer ends
        //The first has a specific value, and the rest are calculated based on the last value plus the amount of nodes in the current row
        for (int i = 0; i < layerEnds.length; i++) {
            if (i == 0) {
                layerEnds[0] = layers + 2;

            } else if (i <= layers + 1) {
                layerEnds[i] = layerEnds[i - 1] + layers + 2 + i;

            } else {
                layerEnds[i] = layerEnds[i - 1] + layers + 2 + (layers * 2 + 3) - i - 1;
            }
        }
        cachedLayers.put(layers, layerEnds);
        return layerEnds;
    }
}
