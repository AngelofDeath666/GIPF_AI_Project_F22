package ai.gr64.Utils;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Enums.StartingPieces;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Engine.DTOs.GameGraph.InnerNode;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.GameGraph.OuterNode;

public class StateFactory {

    public static GameState create(int layers, StartingPieces startingMode, int startingPieces) {
        int numNodes = (((6 + (layers + 1) * 6) * (layers + 1)) / 2) + 1;
        INode[] nodes = new INode[numNodes];
        int[] layerEnds = new int[2 * layers + 3];

        for (int i = 0; i < layerEnds.length; i++) {
            if (i == 0) {
                layerEnds[0] = layers + 2;

            } else if (i <= layers + 1) {
                layerEnds[i] = layerEnds[i - 1] + layers + 2 + i;

            } else {
                layerEnds[i] = layerEnds[i - 1] + layers + 2 + (layers * 2 + 3) - i - 1;
            }
        }

        for (int i = 0; i < numNodes; i++) {
            if (OuterNode(i, layerEnds)) {
                nodes[i] = new OuterNode();

            } else {
                nodes[i] = new InnerNode();
            }
        }

        for (int i = 0; i < numNodes; i++) {
            if (nodes[i] instanceof OuterNode) {
                continue;
            }
            int currentLayer = GetLayer(i, layerEnds) - 1;
            int size = layerEnds[currentLayer] - layerEnds[currentLayer - 1];
            int upModifier = (currentLayer > layerEnds.length/2 ? 1 : 0);
            int downModifier = (currentLayer >= layerEnds.length/2 ? 1 : 0);
            if (!nodes[i].hasNeighbour(Direction.LEFT)) {
                nodes[i].addNeighbor(nodes[i - 1], Direction.LEFT);
            }
            if (!nodes[i].hasNeighbour(Direction.RIGHT)) {
                nodes[i].addNeighbor(nodes[i + 1], Direction.RIGHT);
            }
            if (!nodes[i].hasNeighbour(Direction.UP_LEFT)) {
                nodes[i].addNeighbor(nodes[i - size - upModifier], Direction.UP_LEFT);
            }
            if (!nodes[i].hasNeighbour(Direction.UP_RIGHT)) {
                nodes[i].addNeighbor(nodes[i - size + 1 - upModifier], Direction.UP_RIGHT);
            }
            if (!nodes[i].hasNeighbour(Direction.DOWN_LEFT)) {
                nodes[i].addNeighbor(nodes[i + size - downModifier], Direction.DOWN_LEFT);
            }
            if (!nodes[i].hasNeighbour(Direction.DOWN_RIGHT)) {
                nodes[i].addNeighbor(nodes[i + size + 1 - downModifier], Direction.DOWN_RIGHT);
            }

        }

        switch (startingMode) {
            case NONE:
                break;

            case NORMAL:
                for (int i = 0; i < 6; i++) {
                    INode workingNode = nodes[numNodes / 2];

                    for (int j = 0; j < layers; j++) {
                        workingNode = workingNode.neighbour(Direction.fromValue(i));
                    }
                    if (i % 2 == 0) {
                        workingNode.placePiece(Piece.WHITE);
                    } else {
                        workingNode.placePiece(Piece.BLACK);
                    }
                }
                break;
            case DOUBLE:
                throw new Error("Handling of Gifp pieces not implemented yet");
        }

        GameState state = new GameState(startingPieces, nodes, layers);

        return state;

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
