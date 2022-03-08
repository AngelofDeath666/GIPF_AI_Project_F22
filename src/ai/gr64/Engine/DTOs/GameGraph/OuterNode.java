package ai.gr64.Engine.DTOs.GameGraph;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.INode;
import jdk.jshell.spi.ExecutionControl;

public class OuterNode implements INode{
    private INode[] neighbors = new INode[6];

    @Override
    public void addNeighbor(INode node, Direction dir) {
        throw new Error("Method should never be called");
    }

    @Override
    public boolean hasNeighbour(Direction dir) {
        return neighbors[dir.getValue()] != null;
    }

    @Override
    public INode neighbour(Direction dir) {
        return neighbors[dir.getValue()];
    }

    @Override
    public void placePiece(Piece piece) {
        throw new Error("To be implemented");
    }

    @Override
    public void addOneWayNeighbor(INode node, Direction dir) {
        neighbors[dir.getValue()] = node;
        
    }

}
