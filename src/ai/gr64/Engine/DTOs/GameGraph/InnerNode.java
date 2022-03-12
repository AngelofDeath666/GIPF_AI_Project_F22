package ai.gr64.Engine.DTOs.GameGraph;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.INode;

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

    @Override
    public boolean slidePiece(Piece piece, Direction dir) {
        if (this.piece == Piece.NONE) {
            this.piece = piece;
            return true;
        }
        if (neighbor(dir).slidePiece(this.piece, dir)) {
            this.piece = piece;
            return true;
        }
        return false;
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
}



