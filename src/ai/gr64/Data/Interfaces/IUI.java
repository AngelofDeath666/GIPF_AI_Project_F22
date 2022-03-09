package ai.gr64.Data.Interfaces;

import ai.gr64.Data.Enums.Direction;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public interface IUI {
    public Move GetPlayerInput(boolean player1, Direction dir, int position);

    //UI needs 4 arrays with the locations (indexes in the array) of the black and white normal pieces and the B and W GIPF pieces
    public void UpdateUi(GameState state, int [] board, int [] blackPiecesIndex, int [] blackGIPFIndex, int [] whitePiecesIndex, int [] whiteGIPFIndex);
    
}
