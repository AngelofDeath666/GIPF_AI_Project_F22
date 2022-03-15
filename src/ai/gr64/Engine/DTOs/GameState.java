package ai.gr64.Engine.DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Engine.DTOs.Actions.ClearRow;
import ai.gr64.Engine.DTOs.Actions.Move;
import ai.gr64.Engine.DTOs.GameGraph.InnerNode;
import ai.gr64.Engine.DTOs.GameGraph.OuterNode;

// The GameState, containing information about how the game is looking at this point in time
// Has information about, the board, how many pieces each player has left, etc.
public class GameState {
    private int whitePiecesLeft, blackPiecesLeft;
    private INode[] graph, outerNodes;
    public final int layers;
    private List<InnerNode> changedNodes = new ArrayList<>();
    private Stack<IAction> actionStack = new Stack<>();

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

    public void makeAction(IAction action) {
        actionStack.push(action);
        action.makeAction(this);
    }

    public void unmakeAction() {
        actionStack.pop().unmakeAction(this);
    }

    // The method called when making a move on the board
    public List<ClearRow> makeMove(Move move) {
        if (move.getPlacementNode() >= outerNodes.length)
            throw new IndexOutOfBoundsException(
                    "The placement-node of move must be non-negative and lower then the number of outer nodes on the board");
        outerNodes[move.getPlacementNode()].slidePiece(move.getPiece(), move.getDirection(), changedNodes);
        return rowsCompleted();
    }

    public List<Piece> getRow(int outerIndex, Direction dir) {
        return outerNodes[outerIndex].getRow(dir, null);
    }

    public void clearRow(int outerIndex, Direction dir) {
        outerNodes[outerIndex].clearRow(dir);
    }

    public void setRow(int outerIndex, Direction dir, List<Piece> pieces) {
        outerNodes[outerIndex].setRow(dir, pieces);
    }

    public boolean movePossible(Move move) {
        return outerNodes[move.getPlacementNode()].movePossible(move.getDirection());
    }

    private List<ClearRow> rowsCompleted() {
        List<ClearRow> completedRows = new ArrayList<>();
        for (InnerNode innerNode : changedNodes) {
            completedRows.addAll(innerNode.rowsCompleted());
        }
        changedNodes.clear();
        return completedRows;
    }
}
