package ai.gr64.Engine.DTOs.GameGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Data.Statics.GameSettings;
import ai.gr64.Engine.DTOs.Actions.ClearRow;

// Class representing an OuterNode
// An inner node is one of the spaces on the board which is not on the edge, and where pieces stay between turns. A counterpart to the outer-nodes
public class InnerNode implements INode{
    // All neighbors to this node, each inner-node will have 6 neighbors with there index corresponding to the value in the Direction-enum
    private INode[] neighbors = new INode[6];
    // Which piece is placed on this space
    private Piece piece = Piece.NONE;
    
    // Method to add a neighbor for a specific direction, it invokes addOneWayNeighbor on the other node to establish a two-way direction
    @Override
    public void addNeighbor(INode node, Direction dir){
        neighbors[dir.getValue()] = node;
        node.addOneWayNeighbor(this, Direction.opposite(dir));
    }

    // Method to get whether a neighbor exists in the specified direction
    // If this returns false after creation of the board something is wrong
    @Override
    public boolean hasNeighbor(Direction dir) {
        return neighbors[dir.getValue()] != null;
    }

    // Method to get the neighbor in a specific direction
    @Override
    public INode neighbor(Direction dir) {
        return neighbors[dir.getValue()];
    }

    // Method to set this space to contain a specific piece, should only be used when creating the board to place the starting-pieces for each player
    public void placePiece(Piece piece) {
        this.piece = piece;
    }

    // Sets this pice to the given pice, and slides the current piece on the next node, if there was a current piece
    // Checks that the piece can actually be slid on before updating, returns false if it would end up being an illegal move 
    @Override
    public boolean slidePiece(Piece piece, Direction dir, List<InnerNode> changedNodes) {
        if (this.piece == Piece.NONE) {
            this.piece = piece;
            changedNodes.add(this);
            return true;
        }
        if (neighbor(dir).slidePiece(this.piece, dir, changedNodes)) {
            if (this.piece != piece) {
                changedNodes.add(this);
            }
            this.piece = piece;
            return true;
        }
        return false;
    }

    // Check whether it is possible to make a move from an outerNode going through this node and sliding on if there is a piece on this node
    @Override
    public boolean movePossible(Direction dir) {
        if (this.piece == Piece.NONE || this.piece == null)
            return true;
        return neighbor(dir).movePossible(dir);
    }

    // Method to add a neighbor without adding this as a neighbor to the other node
    // Required to not get infinite loops when establishing the two-way connections
    @Override
    public void addOneWayNeighbor(INode node, Direction dir) {
        neighbors[dir.getValue()] = node;
    }

    @Override
    public char getNodeChar() {
        return Piece.getPieceChar(piece);
    }

    //Find all the completed rows this node is a part of
    public Collection<ClearRow> rowsCompleted() {
        List<ClearRow> rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ClearRow row = directionalRow(Direction.fromValue(i));
            if (row != null)
                rows.add(row);
        }
        return rows;
    }

    //Finds whether this row is completed in a specific direction
    private ClearRow directionalRow(Direction dir) {
        int inARow = 1;
        if (neighbor(dir) instanceof InnerNode node)
            inARow += node.samePieceInARow(dir, this.piece);
        if (neighbor(Direction.opposite(dir)) instanceof InnerNode node)
            inARow += node.samePieceInARow(Direction.opposite(dir), this.piece);
        if (inARow >= GameSettings.NumberInARowToClear) {
            INode sourceNode = neighbor(dir);
            while (sourceNode instanceof InnerNode) {
                sourceNode = sourceNode.neighbor(dir);
            }
            return new ClearRow(((OuterNode)sourceNode).getOuterIndex(), Direction.opposite(dir), this.piece);
        }
        return null;
    }

    // Finds how many of the same piece there is in a row in the given direction
    public int samePieceInARow(Direction dir, Piece piece) {
        if (this.piece != piece)
            return 0;
        if (neighbor(dir) instanceof InnerNode innerNode)
            return 1 + innerNode.samePieceInARow(dir, piece);
        return 1;
    }

    @Override
    public List<Piece> getRow(Direction dir, List<Piece> row) {
        row.add(this.piece);
        return neighbor(dir).getRow(dir, row);
    }

    @Override
    public void setRow(Direction dir, List<Piece> row, int index) {
        this.piece = row.get(index);
        neighbor(dir).setRow(dir, row, index + 1);
    }

    @Override
    public void clearRow(Direction dir) {
        this.piece = Piece.NONE;
        neighbor(dir).clearRow(dir);
    }

    @Override
    public boolean canClear(Direction dir) {
        if (samePieceInARow(dir, this.piece) + 1 >= GameSettings.NumberInARowToClear)
            return true;
        return neighbor(dir).canClear(dir);
    }
}