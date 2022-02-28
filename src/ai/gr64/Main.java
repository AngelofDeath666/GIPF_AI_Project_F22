package ai.gr64;

import ai.gr64.AI.RandomAI;
import ai.gr64.Engine.DTOs.Engine;
import ai.gr64.UI.ASCIIUI;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var UI = new ASCIIUI();
        var moveGen1 = new RandomAI();
        var moveGen2 = new RandomAI();

        var game = new Engine(moveGen1, moveGen2,UI);

        game.Run();
    }
}
