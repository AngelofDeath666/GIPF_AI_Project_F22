package ai.gr64.Data.Enums;

public enum Piece {
    NONE,
    WHITE,
    BLACK,
    GIPFWHITE,
    GIPFBLACK;

    public static char getPieceChar(Piece piece) {
        switch (piece) {
            case NONE:
                return ' ';
            case WHITE:
                return 'w';
            case BLACK:
                return 'b';
            case GIPFWHITE:
                return 'b';
            case GIPFBLACK:
                return 'b';
        }
    }
}
