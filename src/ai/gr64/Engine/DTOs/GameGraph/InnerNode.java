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
    }


}



