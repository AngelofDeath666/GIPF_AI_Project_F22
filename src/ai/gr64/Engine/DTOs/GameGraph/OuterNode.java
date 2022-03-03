package ai.gr64.Engine.DTOs.GameGraph;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.INode;

public class OuterNode implements INode{

    @Override
    public void addNeighbor(INode node, Direction dir) {

        
    }

    @Override
    public boolean hasNeighbour(Direction dir) {
        return false;
    }

}
