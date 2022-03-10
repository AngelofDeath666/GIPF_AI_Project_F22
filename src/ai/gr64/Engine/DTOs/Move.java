package ai.gr64.Engine.DTOs;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Enums.Piece;

//DTO representing a move
public class Move {
    private Piece piece;
    private int placementNode;
    private Direction direction;

    public Move(Piece piece, int placementNode, Direction direction) {
        this.piece = piece;
        this.placementNode = placementNode;
        this.direction = direction;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getPlacementNode() {
        return placementNode;
    }

    public Direction getDirection() {
        return direction;
    }

}
