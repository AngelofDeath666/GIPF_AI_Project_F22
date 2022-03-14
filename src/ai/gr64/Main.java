package ai.gr64;

import ai.gr64.AI.RandomAI;
import ai.gr64.Data.Enums.StartingPieces;
import ai.gr64.Engine.Engine;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.UI.TextUI;
import ai.gr64.Utils.StateFactory;

public class Main {

    public static void main(String[] args) {
        //Generate nessesary resources
        GameState state = StateFactory.create(3, StartingPieces.NORMAL,15);
        var UI = new TextUI();

        //The move-gens choose how to play the game, whether it is a player, some random AI, og a smart AI of some kind
        var moveGen1 = new RandomAI();
        var moveGen2 = new RandomAI();

        //Pass them to the game
        var game = new Engine(moveGen1, moveGen2,UI, state);

        //run the game
        game.Run();
    }

}
