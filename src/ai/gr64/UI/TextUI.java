package ai.gr64.UI;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Text;

import ai.gr64.AI.PlayerMoveGen;
import ai.gr64.AI.RandomAI;
import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Data.Statics.TextStatics;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.ClearRow;
import ai.gr64.Engine.DTOs.Actions.Move;
import ai.gr64.Engine.DTOs.GameGraph.InnerNode;
import ai.gr64.Engine.DTOs.GameGraph.OuterNode;
import ai.gr64.Utils.BoardUtils;
import ai.gr64.Utils.Pair;

public class TextUI implements IUI {

    private static final Scanner scan = new Scanner(System.in);
    public static String startPosition;
    public static String endPosition;

    // This method prints the board, starting with WhiteSpaces and then alternating
    // between Nodes and Diagonal Connections
    @Override
    public void updateUi(GameState state) {
        INode[] graph = state.getGraph();
        int layers = (state.layers * 2 + 3) * 2 - 1;
        StringBuilder sb = new StringBuilder();
        int[] layerEnds = BoardUtils.LayerEnds(state.layers);

        

        for (int i = 0; i < layers; i++) {
            PrintWhiteSpaces(i, layers, sb);
            if (i % 2 == 0) {
                PrintCoordinatesStart(i, sb);
                PrintNodes(graph, layerEnds, i, sb);
                PrintCoordinatesEnds(i, layerEnds, sb);
            } else {
                PrintConnections(i, layerEnds, layers, sb);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
        System.out.println("\n\n\n" + TextStatics.messageWhitePiecesLeft + state.getWhitePiecesLeft());
        System.out.println(TextStatics.messageBlackPiecesLeft + state.getBlackPiecesLeft());
        System.out.println(TextStatics.messageP1 + TextStatics.messageOuterNode);

    }

    @Override
    public Move getPlayerInput(GameState state) {
        int position = -1;
        while (position == -1)
            position = getValidNodePosition(state);

        System.out.println(TextStatics.messageDirection);

        return getValidDirection(position, state);

    }

    // prints the coordinates in the end of a row
    private void PrintCoordinatesEnds(int currentLayer, int[] layerEnds, StringBuilder sb) {
        int nodeCount = currentLayer == 0 ? layerEnds[0]
                : layerEnds[currentLayer / 2] - layerEnds[currentLayer / 2 - 1];

        sb.append(" " + String.valueOf((char) (97 + (currentLayer / 2))) + nodeCount);

    }

    // Prints the coordinates in the start of a row
    private void PrintCoordinatesStart(int currentLayer, StringBuilder sb) {
        sb.append(((char) (97 + (currentLayer / 2))) + "1 ");

    }

    // This method calculates the amount of white spaces needed and appends them to
    // the StringBuilder
    private void PrintWhiteSpaces(int currentLayer, int totalLayers, StringBuilder sb) {
        int middleLayer = totalLayers / 2 + 1;
        int whiteSpaces = Math.abs(middleLayer - (currentLayer + 1));
        for (int i = 0; i < whiteSpaces + (currentLayer % 2 == 0 ? 0 : 3); i++) {
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
                    sb.append(new char[] { TextStatics.linearConnectors, TextStatics.linearConnectors,
                            TextStatics.linearConnectors });
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


    private Move getValidDirection(int position, GameState state) {
        // validate direction and return
        Direction moveDirection = Direction.DOWN_LEFT;
        boolean validNeighbor = false;
        int direction;
        Move move = null;

        do {
            try {
                direction = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println(TextStatics.warningNumber);
                continue;
            }
            moveDirection = Direction.fromValue(direction);
            if (!state.getOuterNodes()[position].movePossible(moveDirection)) {
                System.out.println(TextStatics.warningFilledRow);
                continue;
            }
            validNeighbor = state.getOuterNodes()[position].hasNeighbor(moveDirection);
            if (!validNeighbor) {
                System.out.println(TextStatics.warningNoNeighbor);
                continue;
            }
            move = new Move(Piece.NONE, position, moveDirection);
            if (!state.movePossible(move)) 
                validNeighbor = false;
        } while (!validNeighbor);

        return move;
    }

    private int getValidNodePosition(GameState state) {
        int position = -1;
        INode[] graph = state.getGraph();
        boolean valid = false;
        int nodeNum = -1;
        char rowChar;
        int currentLayer = -1;
        int[] layerEnds = BoardUtils.LayerEnds(state.layers);

        // validate node :)
        do {
            if (valid)
                System.out.println(TextStatics.warningInner);
            valid = false;
            do {
                String node = scan.nextLine();
                rowChar = node.charAt(0);
                try {
                    nodeNum = Integer.parseInt(node.substring(1));
                } catch (Exception e) {
                    System.out.println(TextStatics.warningCoordinates);
                    continue;
                }

                if (!(rowChar >= 'a' && rowChar <= 'a' + layerEnds.length - 1)) {
                    System.out.println(TextStatics.warningLetter);
                    continue;
                }

                currentLayer = ((int) (rowChar - 'a'));
                int nodeCount = currentLayer == 0 ? layerEnds[0]
                        : layerEnds[currentLayer] - layerEnds[currentLayer - 1];

                if (!(nodeNum >= 1 && nodeNum <= nodeCount)) {
                    System.out.println(TextStatics.warningNumberRow);
                    continue;
                }

                
                position = currentLayer == 0 ? nodeNum - 1 : layerEnds[currentLayer - 1] + nodeNum - 1;
                int outerPosition = (graph[position] instanceof OuterNode ? ((OuterNode)graph[position]).getOuterIndex() : -1);
                boolean invalid = false;
                for (int i = 0; i < 6; i++) {
                    if (outerPosition == -1)
                        break;
                    if (state.movePossible(new Move(Piece.NONE, outerPosition, Direction.fromValue(i)))) 
                        break;
                    if (i == 5) {
                        invalid = true;
                    }
                }
                if (invalid) {
                    System.out.println(TextStatics.warningFullNeighborRows);
                    continue;
                } 
                
                // This line will be skipped if the input is invalid
                valid = true;

            } while (!valid);
            
        } while (graph[position] instanceof InnerNode);

        return ((OuterNode) graph[position]).getOuterIndex();

    }

    // Prints start explanation and chooses player type
    public Pair<IMoveGen,IMoveGen> startGame() {
        int playerType;
        IMoveGen player1 = new RandomAI();
        IMoveGen player2 = new RandomAI();
        boolean valid = false;
        System.out.println(TextStatics.messagePlayersP1);
        System.out.println(TextStatics.messagePlayersP2);
        do {
            try {
                playerType = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println(TextStatics.warningNumberPlayer);
                continue;
            }
            if (playerType > 1 || playerType < 0) {
                System.out.println(TextStatics.warningNumberPlayer);
                continue;
            }
            if (playerType == 0) {
                player1 = new PlayerMoveGen(this);
            }
            valid = true;
        } while (!valid);

        valid = false;
        System.out.println(TextStatics.messagePlayersP3);
        do {
            try {
                playerType = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println(TextStatics.warningNumberPlayer);
                continue;
            }
            if (playerType > 1 || playerType < 0) {
                System.out.println(TextStatics.warningNumberPlayer);
                continue;
            }
            if (playerType == 0) {
                player2 = new PlayerMoveGen(this);
            }
            valid = true;
        } while (!valid);

        System.out.println(TextStatics.explainDirection);
        return new Pair<IMoveGen,IMoveGen>(player1, player2);
    }

    @Override
    public ClearRow getClearRow(GameState state) {
        List<ClearRow> actions = state.getAvailableActions();
        int clearIndex = 0;
        boolean valid = false; 
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + 1 + ") " + placementToCoordinate(state, actions.get(i).getPlacementNode()) + " " + actions.get(i).getDirection().toString().toLowerCase());
        }
        System.out.println(TextStatics.messageRowChoice);
        do {
            try {
                clearIndex = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println(TextStatics.warningNumber);
                continue;
            }
            if (clearIndex < 1 || clearIndex > actions.size()) {
                System.out.println(TextStatics.warningOutOfRange);
                continue;
            }
            
            valid = true;
        } while (!valid);

        return actions.get(clearIndex-1);
        
    }

    private String placementToCoordinate(GameState state, int placementNode) {
        int index = Arrays.asList(state.getGraph()).indexOf(state.getOuterNodes()[placementNode]);
        int[] ends = BoardUtils.LayerEnds(state.layers);
        int layer;
        for (layer = 0; layer < ends.length; layer++) {
            if (ends[layer] > index) 
                break;
        }
        char coordinateLetter = (char) (97 + layer);
        int coordinateNumber = index - (layer != 0 ? ends[layer - 1] : 0) + 1;


        return "" + coordinateLetter + coordinateNumber;
    }

}
