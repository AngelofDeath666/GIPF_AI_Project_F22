package ai.gr64.Engine.DTOs.GameGraph;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.INode;

public class OuterNode implements INode{

    @Override
    public void addNeighbor(INode node, Direction dir) {
        throw new Error("Method should never be called");
    }

    @Override
    public boolean hasNeighbour(Direction dir) {
        return false;
    }

    @Override
    public void addOneWayNeighbor(INode node, Direction dir) {
        node.addOneWayNeighbor(this, Direction.opposite(dir));
        
    }

}
