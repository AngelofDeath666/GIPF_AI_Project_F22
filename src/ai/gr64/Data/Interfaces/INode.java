package ai.gr64.Data.Interfaces;

import ai.gr64.Data.Enums.Direction;

public interface INode {
    
    void addNeighbor(INode node, Direction dir);
    
}
