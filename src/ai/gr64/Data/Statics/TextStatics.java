package ai.gr64.Data.Statics;

public class TextStatics {
    // These variables are the characters being used in the TextUI
    public static final char whiteGIPF = 'W';
    public static final char whitePieces = 'w';
    public static final char blackGIPF = 'B';
    public static final char blackPieces = 'b';
    public static final char outerSpaces = 'o';
    public static final char emptySpaces = 'X';
    public static final char linearConnectors = '-';
    public static final char leftDiagConnect = '\\';
    public static final char rightDiagConnect = '/';
    public static final char space = ' ';

    private static final String explainDirectionP1 = "These are the commands for the different directions:\n                 0   1\n";
    private static final String explainDirectionP2 = "                  \\ /\n               5---o---2\n";
    private static final String explainDirectionP3 = "                  / \\\n                 4   3\n";
    public static final String explainDirection = explainDirectionP1 + explainDirectionP2 + explainDirectionP3;
    public static final String messageP1 = "Please enter the outer node you wish to put your piece on and then which inner node you wish to push it to.\n";
    public static final String messageOuterNode = "Type in the coordinates of the outer node: ";
    public static final String messageDirection = "";
    public static final String warningInner = "You made an illegal move, you can't place a piece on an inner node. Please try again.";
    public static final String warningOuter = "You made an illegal move, you can't move a piece to an outer node. Please try again.";
    public static final String warningFilledRow = "You made an illegal move, you can't move a piece into a full row. Please try again.";
    public static final String warningCoordinates = "You did not enter a valid coordinate. Please try again.";
    public static final String warningLetter = "Please enter a letter between a and i.";
    public static final String warningNumber = "Please enter a number corresponding to the row.";
}
