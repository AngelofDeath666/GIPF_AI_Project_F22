package ai.gr64.Utils;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Enums.StartingPieces;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Engine.DTOs.GameGraph.InnerNode;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.GameGraph.OuterNode;

// Static factory-class to construct the GameState
public class StateFactory {

    public static GameState create(int layers, StartingPieces startingMode, int startingPieces) {
        //Math expression to calculate how many nodes there will be in total, including the outer nodes on which new pieces are placed
        int numNodes = (((6 + (layers + 1) * 6) * (layers + 1)) / 2) + 1;
        INode[] nodes = new INode[numNodes];

        //layer ends are the 1-indexed index of the last node in each layer/row
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

        //Generates the specific nodes and puts them in the nodes array
        //uses a method to calculate whether its an outer or inner node
        for (int i = 0; i < numNodes; i++) {
            if (OuterNode(i, layerEnds)) {
                nodes[i] = new OuterNode();

            } else {
                nodes[i] = new InnerNode();
            }
        }

        //Loops through all the nodes and adds the neighbors on each side
        for (int i = 0; i < numNodes; i++) {
            if (nodes[i] instanceof OuterNode) {
                continue;
            }
            //Some math calculation as diagonal offsets change when the row-width starts to shrink again
            int currentLayer = GetLayer(i, layerEnds) - 1;
            int size = layerEnds[currentLayer] - layerEnds[currentLayer - 1];
            int upModifier = (currentLayer > layerEnds.length/2 ? 1 : 0);
            int downModifier = (currentLayer >= layerEnds.length/2 ? 1 : 0);
            if (!nodes[i].hasNeighbor(Direction.LEFT)) {
                nodes[i].addNeighbor(nodes[i - 1], Direction.LEFT);
            }
            if (!nodes[i].hasNeighbor(Direction.RIGHT)) {
                nodes[i].addNeighbor(nodes[i + 1], Direction.RIGHT);
            }
            if (!nodes[i].hasNeighbor(Direction.UP_LEFT)) {
                nodes[i].addNeighbor(nodes[i - size - upModifier], Direction.UP_LEFT);
            }
            if (!nodes[i].hasNeighbor(Direction.UP_RIGHT)) {
                nodes[i].addNeighbor(nodes[i - size + 1 - upModifier], Direction.UP_RIGHT);
            }
            if (!nodes[i].hasNeighbor(Direction.DOWN_LEFT)) {
                nodes[i].addNeighbor(nodes[i + size - downModifier], Direction.DOWN_LEFT);
            }
            if (!nodes[i].hasNeighbor(Direction.DOWN_RIGHT)) {
                nodes[i].addNeighbor(nodes[i + size + 1 - downModifier], Direction.DOWN_RIGHT);
            }

        }

        /*Used to specify the starting mode of the game:
            None:
                there will be placed no pieces on the board to start with
            Normal:
                in each "corner" of the board, there will be either a black or a white piece
            Double:
                in each "corner" of the board there will be a GIPF piece either black or white
        */
        switch (startingMode) {
            //No pieces have to be placed
            case NONE:
                break;

            //Finds each corner and places a normal piece
            case NORMAL:
                for (int i = 0; i < 6; i++) {
                    INode workingNode = nodes[numNodes / 2];

                    for (int j = 0; j < layers; j++) {
                        workingNode = workingNode.neighbor(Direction.fromValue(i));
                    }
                    if (i % 2 == 0) {
                        workingNode.placePiece(Piece.WHITE);
                    } else {
                        workingNode.placePiece(Piece.BLACK);
                    }
                }
                break;

            //As GIPF pieces not are implemented yet, this is mode is yet to be implemented
            case DOUBLE:
                throw new Error("Handling of Gifp pieces not implemented yet");
        }

        // Generates and returns the final state
        return new GameState(startingPieces, nodes, layers);
    }

    private static int GetLayer(int nodeIndex, int[] layerEnds) {
        if (nodeIndex < 0)
            throw new IndexOutOfBoundsException("Index can't be less than 0");
        for (int i = 0; i < layerEnds.length; i++) {
            if (nodeIndex < layerEnds[i]) {
                return i + 1;
            }
        }
        throw new IndexOutOfBoundsException("Index can't be bigger, than the biggest value in layer ends");
    }

    private static boolean OuterNode(int nodeIndex, int[] layerEnds) {
        int layer = GetLayer(nodeIndex, layerEnds);
        if (layer == 1 || layer == layerEnds.length) {
            return true;
        }
        for (int index : layerEnds) {
            if (nodeIndex == index || nodeIndex + 1 == index) {
                return true;
            }
        }
        return false;
    }
}
