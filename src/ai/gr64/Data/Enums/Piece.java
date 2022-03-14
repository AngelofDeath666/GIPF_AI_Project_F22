package ai.gr64.Data.Enums;

import ai.gr64.Data.Statics.TextStatics;

public enum Piece {
    NONE,
    WHITE,
    BLACK,
    GIPFWHITE,
    GIPFBLACK;

    public static char getPieceChar(Piece piece) {
        switch (piece) {
            case NONE:
                return TextStatics.emptySpaces;
            case WHITE:
                return TextStatics.whitePieces;
            case BLACK:
                return TextStatics.blackPieces;
            case GIPFWHITE:
                return TextStatics.whiteGIPF;
            case GIPFBLACK:
                return TextStatics.blackGIPF;
            default:
                throw new IllegalArgumentException("An unhandled piece was given as an argument.");
        }
    }
}
