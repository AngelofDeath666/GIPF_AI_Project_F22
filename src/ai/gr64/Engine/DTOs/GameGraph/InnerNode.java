package ai.gr64.Engine.DTOs.GameGraph;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.INode;

public class InnerNode implements INode{
    private INode[] neighbors = new INode[6];
    private Piece piece;
    
    @Override
    public void addNeighbor(INode node, Direction dir){
        neighbors[dir.getValue()] = node;
        node.addOneWayNeighbor(this, Direction.opposite(dir));
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
        this.piece = piece;
    }

    @Override
    public void addOneWayNeighbor(INode node, Direction dir) {
        neighbors[dir.getValue()] = node;
        
    }


}



