package ai.gr64.Engine.DTOs;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Engine.DTOs.GameGraph.OuterNode;

// The GameState, containing information about how the game is looking at this point in time
// Has information about, the board, how many pieces each player has left, etc.
public class GameState {
    private int whitePiecesLeft, blackPiecesLeft;
    private INode[] graph, outerNodes;
    public final int layers;

    public GameState(int startingPieces, INode[] graph, int layers) {
        this.whitePiecesLeft = startingPieces;
        this.blackPiecesLeft = startingPieces;
        this.graph = graph;
        this.layers = layers;

        // An array containing all the outer-nodes on the board, sorted starting with
        // the top-left, going clockwise.
        outerNodes = new INode[(layers + 1) * 6];
        outerNodes[0] = graph[0];

        // A loop which finds all the outer nodes and adds them to the outerNodes array
        // in the correct order.
        int workingIndex = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < layers + 1; j++) {
                workingIndex++;
                if (workingIndex == outerNodes.length)
                    break;
                INode nextNode = outerNodes[workingIndex - 1];
                nextNode = nextNode.neighbor(Direction.fromValue(i + 3));
                nextNode = nextNode.neighbor(Direction.fromValue(i + 1));
                outerNodes[workingIndex] = nextNode;
                ((OuterNode)nextNode).setOuterIndex(workingIndex);
            }
        }
    }

    public INode[] getGraph() {
        return graph;
    }

    // The method called when making a move on the board
    public boolean makeMove(Move move) {
        if (move.getPlacementNode() >= outerNodes.length)
            throw new IndexOutOfBoundsException(
                    "The placement-node of move must be non-negative and lower then the number of outer nodes on the board");
        return outerNodes[move.getPlacementNode()].slidePiece(move.getPiece(), move.getDirection());
    }

    public boolean movePossible(Move move) {
        return outerNodes[move.getPlacementNode()].movePossible(move.getDirection());
    }
}
