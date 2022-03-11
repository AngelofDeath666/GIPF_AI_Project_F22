package ai.gr64.Data.Enums;

import ai.gr64.UI.TextUI;

public enum Piece {
    NONE,
    WHITE,
    BLACK,
    GIPFWHITE,
    GIPFBLACK;

    public static char getPieceChar(Piece piece) {
        switch (piece) {
            case NONE:
                return TextUI.emptySpaces;
            case WHITE:
                return TextUI.whitePieces;
            case BLACK:
                return TextUI.blackPieces;
            case GIPFWHITE:
                return TextUI.whiteGIPF;
            case GIPFBLACK:
                return TextUI.blackGIPF;
            default:
                throw new IllegalArgumentException("An unhandled piece was given as an argument.");
        }
    }
}
