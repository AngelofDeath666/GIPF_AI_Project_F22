package ai.gr64.Data.Statics;

public class TextStatics {
    // These variables are the characters being used in the TextUI
    public static final char whiteGIPF = 'W';
    public static final char whitePieces = 'w';
    public static final char blackGIPF = 'B';
    public static final char blackPieces = 'b';
    public static final char outerSpaces = 'o';
    public static final char emptySpaces = ' ';
    public static final char linearConnectors = '-';
    public static final char leftDiagConnect = '\\';
    public static final char rightDiagConnect = '/';
    public static final char space = ' ';

    private static final String explainDirectionP1 = "These are the commands for the different directions:\n                 0   1\n";
    private static final String explainDirectionP2 = "                  \\ /\n               5---o---2\n";
    private static final String explainDirectionP3 = "                  / \\\n                 4   3\n";
    public static final String explainDirection = explainDirectionP1 + explainDirectionP2 + explainDirectionP3;
    public static final String messageP1 = "Please enter the outer node you wish to put your piece on and then which inner node you wish to push it to.\n";
    public static final String messageOuterNode = "Enter the coordinates of the outer node: ";
    public static final String messageDirection = "Enter the direction you wish to push your piece to: ";
    public static final String messagePlayersP1 = "0: human \n1: heuristic AI\n2: random moves";
    public static final String messagePlayersP2 = "Choose the white player. You will go first: ";
    public static final String messagePlayersP3 = "Choose the black player. You will go second: ";
    public static final String messageWhitePiecesLeft = "White pieces left in reserve: ";
    public static final String messageBlackPiecesLeft = "Black pieces left in reserve: ";
    public static final String messageRemovedRow = "\nA row has been removed.\n";
    public static final String messageRowChoice = "Choose a row to clear.";
    
    public static final String warningInner = "You made an illegal move, you can't place a piece on an inner node. Please try again.";
    public static final String warningOuter = "You made an illegal move, you can't move a piece to an outer node. Please try again.";
    public static final String warningFilledRow = "You can't move a piece into a full row. Please try again.";
    public static final String warningCoordinates = "You did not enter a valid coordinate. Please try again.";
    public static final String warningLetter = "Please enter a letter between a and i.";
    public static final String warningNumberRow = "Please enter a number corresponding to the row.";
    public static final String warningNumber = "Not a number.";
    public static final String warningNumberPlayer = "Please enter 0 for human or 1 for AI.";
    public static final String warningNoNeighbor = "There is no neighbor in that direction.";
    public static final String warningFullRow = "That row is full.";
    public static final String warningFullNeighborRows = "That node only has full neighboring rows. Choose another.";
    public static final String warningOutOfRange = "The number must be within the specified range.";
}
