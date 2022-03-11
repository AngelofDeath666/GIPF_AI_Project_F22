package ai.gr64.UI;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public class TextUI implements IUI {
    public static final char whiteGIPF = 'W';
    public static final char whitePieces = 'w';
    public static final char blackGIPF = 'B';
    public static final char blackPieces = 'b';
    public static final char outerSpaces = 'o';
    public static final char emptySpaces = (char) 215;
    public static final char linearConnectors = '-';
    public static final char leftDiagConnect = '\\';
    public static final char rightDiagConnect = '/';
    public static final char space = ' ';

    private void PrintWhiteSpaces(int currentLayer, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers/2+1;
        int whiteSpaces = Math.abs(middleLayer-currentLayer);
        for (int i = 0; i < whiteSpaces; i++) {
            sb.append(space);
        }
    }

    private void PrintNodes(INode[] graph, int index, int currentLayer, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers/2+1;
        int nodeCount = Math.abs(middleLayer-currentLayer);
        for (int i = 0; i < whiteSpaces-1; i++) {
            
        }

        
    }

    private void PrintConnections(int currentLayer, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers/2+1;
        int whiteSpaces = Math.abs(middleLayer-currentLayer);
        if (currentLayer < middleLayer) {
            for (int i = 0; i < whiteSpaces; i++) {
            sb.append(rightDiagConnect);
            sb.append(space);
            sb.append(leftDiagConnect);
            sb.append(space);

            }
        } else {
            for (int i = 0; i < whiteSpaces; i++) {
                sb.append(leftDiagConnect);
                sb.append(space);
                sb.append(rightDiagConnect);
                sb.append(space);
            
            }
                

        }
        
    }



    @Override
    public Move GetPlayerInput(boolean player1, Direction dir, int position) {

        /*
         * 
         * needs to throw an exception if move is illegal.
         * 
         */
        return null;
    }

    @Override
    public void UpdateUi(GameState state) {
        INode[] graph = state.getGraph();
        int layers = (state.layers*2+3)*2-1;
        StringBuilder sb = new StringBuilder();
        //GameState.graph for the array 0 indexed

        //implement a graph array conversion from int to char
                   


    }

}
