package ai.gr64.Utils;

import java.util.ArrayList;
import java.util.List;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public class BoardUtils {

    // To be implemented, should return an array of all possible moves to be made
    // based on the given GameState
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
}
