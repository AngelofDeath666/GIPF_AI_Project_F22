package ai.gr64.Data.Interfaces;

import java.util.List;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;
import ai.gr64.Engine.DTOs.GameGraph.InnerNode;

// Interface for all types of nodes on the game board, should only be two implementations, inner and outer.
public interface INode {
    void addNeighbor(INode node, Direction dir);
    void addOneWayNeighbor(INode node, Direction dir);
    boolean hasNeighbor(Direction dir);
    INode neighbor(Direction dir);
    boolean movePossible(Direction dir);
    boolean slidePiece(Piece piece, Direction dir, List<InnerNode> changedNodes);
    char getNodeChar();
}
