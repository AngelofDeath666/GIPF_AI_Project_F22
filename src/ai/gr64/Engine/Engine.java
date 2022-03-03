package ai.gr64.Engine;

import ai.gr64.Data.Interfaces.IMoveGen;
import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public class Engine {
    private IMoveGen MoveGen1, MoveGen2;
    private IUI UI;
    private GameState state;

    public Engine(IMoveGen moveGen1, IMoveGen moveGen2, IUI ui) {
        MoveGen1 = moveGen1;
        MoveGen2 = moveGen2;
        UI = ui;
        state = new GameState();
    }
    
    public void Run() {
        int turn = 0;
        while (turn < 10000) {
           // Run Game 
           var move = turn % 2 == 0 ? MoveGen1.NextMove(state) : MoveGen2.NextMove(state);
           state.MakeMove(move);
           turn++;
        }
    }
}
