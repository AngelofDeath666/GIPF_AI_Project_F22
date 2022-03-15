package ai.gr64.UI;

import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.Move;

// To be implemented, should be a graphical user interface
public class GUI implements IUI {

    @Override
    public Move GetPlayerInput() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void UpdateUi(GameState state) {

    }

}
