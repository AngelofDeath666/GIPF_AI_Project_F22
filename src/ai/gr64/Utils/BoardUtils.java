package ai.gr64.Utils;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public class BoardUtils {
    
    
    // To be implemented, should return an array of all possible moves to be made based on the given GameState
    public static Move[] GetAllMoves(GameState state) {
        return null;
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
