package ai.gr64;

import ai.gr64.AI.GameTree.GameTreeHandler;
import ai.gr64.Data.Enums.StartingPieces;
import ai.gr64.Engine.Engine;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.UI.TextUI;
import ai.gr64.Utils.StateFactory;

public class Main {

    public static void main(String[] args) {
        //Generate nessesary resources
        GameState state = StateFactory.create(3, StartingPieces.NORMAL,15);
        GameTreeHandler.init(state);
        var UI = new TextUI();

        
        var movegens = UI.startGame();
        //The move-gens choose how to play the game, whether it is a player, some random AI, og a smart AI of some kind
        var moveGen1 = movegens.Item1;
        var moveGen2 = movegens.Item2;

        //Pass them to the game
        var game = new Engine(moveGen1, moveGen2,UI, state);

        //run the game
        game.Run();
    }

}
