package ai.gr64;

import ai.gr64.AI.RandomAI;
import ai.gr64.Data.Enums.StartingPieces;
import ai.gr64.Engine.Engine;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.UI.TextUI;
import ai.gr64.Utils.StateFactory;

public class Main {

    public static void main(String[] args) {
        GameState state = StateFactory.create(3, StartingPieces.NORMAL,15);
        var UI = new TextUI();
        var moveGen1 = new RandomAI();
        var moveGen2 = new RandomAI();

        var game = new Engine(moveGen1, moveGen2,UI, state);

        game.Run();
    }

}
