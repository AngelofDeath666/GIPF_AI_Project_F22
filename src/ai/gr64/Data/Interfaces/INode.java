package ai.gr64.Data.Interfaces;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;

// Interface for all types of nodes on the game board, should only be two implementations, inner and outer.
public interface INode {
    void addNeighbor(INode node, Direction dir);
    void addOneWayNeighbor(INode node, Direction dir);
    boolean hasNeighbor(Direction dir);
    INode neighbor(Direction dir);
    void placePiece(Piece piece);
}
