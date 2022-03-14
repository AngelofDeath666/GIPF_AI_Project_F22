package ai.gr64.UI;

import java.util.Scanner;

import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Data.Statics.TextStatics;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;
import ai.gr64.Utils.BoardUtils;


public class TextUI implements IUI {
    
    
    private static final Scanner scan = new Scanner(System.in);
    public static String startPosition;
    public static String endPosition;
    


    // This method calculates the amount of white spaces needed and appends them to
    // the StringBuilder
    private void PrintWhiteSpaces(int currentLayer, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers / 2 + 1;
        int whiteSpaces = Math.abs(middleLayer - (currentLayer + 1));
        for (int i = 0; i < whiteSpaces; i++) {
            sb.append(TextStatics.space);
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
                    sb.append(new char[] { TextStatics.linearConnectors, TextStatics.linearConnectors, TextStatics.linearConnectors });
                } else {
                    sb.append(new char[] { TextStatics.space, TextStatics.space, TextStatics.space });
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
                sb.append(new char[] { i == 0 ? TextStatics.space : TextStatics.rightDiagConnect, TextStatics.space,
                        i == nodeCount - 1 ? TextStatics.space : TextStatics.leftDiagConnect, TextStatics.space });

            }
        } else {
            for (int i = 0; i < nodeCount - 1; i++) {
                sb.append(new char[] { i == 0 ? TextStatics.space : TextStatics.leftDiagConnect, TextStatics.space,
                        i == nodeCount - 2 ? TextStatics.space : TextStatics.rightDiagConnect, TextStatics.space });

            }

        }

    }

    public HandleInput(String startPosition, String endPosition) {

    }

    @Override
    public Move GetPlayerInput() {

        int position = -1;
        while (position == -1) position = getValidNodePosition();

        Direction dir = getValidDirection(position);

        return new Move(Piece.NONE, position, dir);

        
    }

    private Direction getValidDirection(int position) {
        //validate and return

        return Direction.
    }

    private int getValidNodePosition() {
        String node = scan.nextLine();
        int position = -1;

        //validate node
        
        if (!valid) {
            return -1;
        }

        return position;

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
        System.out.println("\n\n\n" + TextStatics.messageP1 + TextStatics.messageOuterNode);

    }

}
