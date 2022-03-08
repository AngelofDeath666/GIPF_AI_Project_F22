package ai.gr64;

import ai.gr64.AI.RandomAI;
import ai.gr64.Data.Enums.StartingPieces;
import ai.gr64.Engine.Engine;
import ai.gr64.UI.ASCIIUI;
import ai.gr64.Utils.StateFactory;

public class Main {

    public static void main(String[] args) {
        StateFactory.create(3, StartingPieces.NORMAL,15);
        var UI = new ASCIIUI();
        var moveGen1 = new RandomAI();
        var moveGen2 = new RandomAI();

        var game = new Engine(moveGen1, moveGen2,UI);

        game.Run();
    }

}
