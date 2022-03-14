package ai.gr64.Engine;

import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

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
    
    // The main loop of the game, gets the next move from the correct moveGen and uses it on the game board, should also handle checking whether the game is over, and who won.
    public void Run() {
        int turn = 0;
        while (turn < 10000) {
           // Run Game 
           Move move = turn % 2 == 0 ? MoveGen1.NextMove(state) : MoveGen2.NextMove(state);
           state.makeMove(move);
           UI.UpdateUi(state);
           turn++;
        }
    }
}
