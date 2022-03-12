package ai.gr64.Engine.DTOs.GameGraph;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.INode;

// Class representing an OuterNode
// An outer node is one of the outer-most spaces on the game board where a player can place a game piece, counterpart to the inner-nodes
public class OuterNode implements INode{
    // An array containing all neighbors to the node, while outer-nodes will have at most two, the index in the array specifies the direction of the connection
    // Based on the Direction-enum, where up-left is 0 and going clockwise as it increases
    private INode[] neighbors = new INode[6];

    // Method to add a neighbor to a specific direction
    // This should never be called on an outer node in order to assure outer-nodes are only connected to inner-nodes, and not other outer nodes
    @Override
    public void addNeighbor(INode node, Direction dir) {
        throw new UnsupportedOperationException("Method should never be called");
    }

    // Method to check whether there exists a neighbor in a specific direction
    @Override
    public boolean hasNeighbor(Direction dir) {
        return neighbors[dir.getValue()] != null;
    }

    // Method to get a neighbor for a specific direction
    @Override
    public INode neighbor(Direction dir) {
        return neighbors[dir.getValue()];
    }

    // The method called when a player is making a move
    @Override
    public boolean slidePiece(Piece piece, Direction dir) {
        if (! hasNeighbor(dir)) 
            return false;
        return neighbor(dir).slidePiece(piece, dir);
    }

    // Returns whether sliding a piece in the direction dir is possible
    @Override
    public boolean movePossible(Direction dir) {
        if (hasNeighbor(dir))
            return neighbor(dir).movePossible(dir);
        return false;
    }

    // Method to add the given neighbor as a neighbor in the specified direction
    // Is invoked when addNeighbor is invoked on an innerNode adjacent to this node so a two-way connection is established
    @Override
    public void addOneWayNeighbor(INode node, Direction dir) {
        neighbors[dir.getValue()] = node;
    }
}
