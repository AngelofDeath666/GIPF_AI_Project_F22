package ai.gr64.UI;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;


public class TextUI implements IUI {
    char whiteGIPF = 'W';
    char whitePieces = 'w';
    char blackGIPF = 'B';
    char blackPieces = 'b';
    char outerSpaces = 'o';
    char emptySpaces =(char)215;
    char linearConnectors = '-';
    char leftDiagConnect = '\\';
    char rightDiagConnect = '/';
    char space = ' ';


    @Override
    public Move GetPlayerInput(boolean player1, Direction dir, int position) {

        /*

        needs to throw an exception if move is illegal.

         */
        return null;
    }

    @Override
    public void UpdateUi(GameState state, int[] board, int[] blackPiecesIndex, int[] blackGIPFIndex, int[] whitePiecesIndex, int[] whiteGIPFIndex) {

    }


}
