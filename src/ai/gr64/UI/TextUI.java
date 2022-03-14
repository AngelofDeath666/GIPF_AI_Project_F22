package ai.gr64.UI;

import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public class TextUI implements IUI {
    // These variables are the characters being used in the TextUI
    public static final char whiteGIPF = 'W';
    public static final char whitePieces = 'w';
    public static final char blackGIPF = 'B';
    public static final char blackPieces = 'b';
    public static final char outerSpaces = 'o';
    public static final char emptySpaces = 'x';
    public static final char linearConnectors = '-';
    public static final char leftDiagConnect = '\\';
    public static final char rightDiagConnect = '/';
    public static final char space = ' ';

    // This method calculates the amount of white spaces needed and appends them to
    // the StringBuilder
    private void PrintWhiteSpaces(int currentLayer, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers / 2 + 1;
        int whiteSpaces = Math.abs(middleLayer - (currentLayer + 1));
        for (int i = 0; i < whiteSpaces; i++) {
            sb.append(space);
        }
    }

    // This method converts the characters for the nodes and their linear
    // connectors, so x for empty space and W for WhiteGipf pieces and then appends
    // them to the StringBuilder
    private void PrintNodes(INode[] graph, int[] layerEnds, int currentLayer, StringBuilder sb) {
        int nodeCount = currentLayer == 0 ? layerEnds[0]
                : layerEnds[currentLayer / 2] - layerEnds[currentLayer / 2 - 1];

        for (int i = layerEnds[currentLayer / 2] - nodeCount; i < layerEnds[currentLayer / 2]; i++) {
            if (i != layerEnds[currentLayer / 2] - nodeCount) {

                if (currentLayer != 0 && currentLayer / 2 != layerEnds.length - 1) {
                    sb.append(new char[] { linearConnectors, linearConnectors, linearConnectors });
                } else {
                    sb.append(new char[] { space, space, space });
                }

            }

            sb.append(graph[i].getNodeChar());
        }

    }

    // This method appends the diagonal connectors to the StringBuilder
    private void PrintConnections(int currentLayer, int[] layerEnds, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers / 2 + 1;
        int nodeCount = currentLayer == 1 ? layerEnds[0]
                : layerEnds[currentLayer / 2] - layerEnds[currentLayer / 2 - 1];
        if (currentLayer < middleLayer) {
            for (int i = 0; i < nodeCount; i++) {
                sb.append(new char[] { i == 0 ? space : rightDiagConnect, space,
                        i == nodeCount - 1 ? space : leftDiagConnect, space });

            }
        } else {
            for (int i = 0; i < nodeCount - 1; i++) {
                sb.append(new char[] { i == 0 ? space : leftDiagConnect, space,
                        i == nodeCount - 2 ? space : rightDiagConnect, space });

            }

        }

    }

    @Override
    public Move GetPlayerInput() {

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // This method prints the board, starting with WhiteSpaces and then alternating
    // between Nodes and Diagonal Connections
    @Override
    public void UpdateUi(GameState state) {
        INode[] graph = state.getGraph();
        int layers = (state.layers * 2 + 3) * 2 - 1;
        StringBuilder sb = new StringBuilder();
        int[] layerEnds = BoardUtils.LayerEnds(state.layers);

        for (int i = 0; i < layers; i++) {
            PrintWhiteSpaces(i, layers, sb);
            if (i % 2 == 0) {
                PrintNodes(graph, layerEnds, i, sb);
            } else {
                PrintConnections(i, layerEnds, layers, sb);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());

    }


}
