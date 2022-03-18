package ai.gr64.Engine;

import ai.gr64.Data.Enums.Piece;
import ai.gr64.Data.Interfaces.IAction;
import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Data.Statics.TextStatics;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.ClearRow;
import ai.gr64.Engine.DTOs.Actions.Move;

// The "Engine" of the game, handles how the game is run, when input should be taken from the UI/moveGens and runs the main game loop.
public class Engine {
    private IMoveGen MoveGen1, MoveGen2;
    private IUI UI;
    private GameState state;

    public Engine(IMoveGen moveGen1, IMoveGen moveGen2, IUI ui, GameState state) {
        MoveGen1 = moveGen1;
        MoveGen2 = moveGen2;
        UI = ui;
        this.state = state;
    }

    // The main loop of the game, gets the next move from the correct moveGen and
    // uses it on the game board, should also handle checking whether the game is
    // over, and who won.
    public void Run() {
        int turn = 0;
        boolean gameRunning = true;
        UI.updateUi(state);
        while (gameRunning) {
            // Run Game
            IAction move = turn % 2 == 0 ? MoveGen1.NextMove(state) : MoveGen2.NextMove(state);
            if (move instanceof Move)
                ((Move) move).setPiece(turn % 2 == 0 ? Piece.WHITE : Piece.BLACK);
            state.makeAction(move);
            UI.updateUi(state); 
            while (state.getAvailableActions().size() != 0) {
                // clear a line
                if (state.getAvailableActions().size() == 1) {
                    // clear that line
                    // write to ui that the specified line has been cleared
                    state.makeAction(state.getAvailableActions().get(0));
                    System.out.println(TextStatics.messageRemovedRow);
                } else {
                    IAction action = (turn % 2 == 0 ? MoveGen1 : MoveGen2).GetClearRowAction(state);
                    //choose line to clear
                }
                UI.updateUi(state);
                
            }
            turn++;
            if (turn % 2 == 0 ? (state.getWhitePiecesLeft() == 0) : (state.getBlackPiecesLeft() == 0))
                gameRunning = false;
        }
        System.out.println("Game over");
        if (state.getWhitePiecesLeft() == 0)
            System.out.println("Black player won");
        else
            System.out.println("White player won");
    }
}
