package ai.gr64.Data.Interfaces;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;

public interface INode {
    
    void addNeighbor(INode node, Direction dir);
    void addOneWayNeighbor(INode node, Direction dir);
    boolean hasNeighbour(Direction dir);
    INode neighbour(Direction dir);
    void placePiece(Piece piece);
}
