package ai.gr64.UI;

import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Actions.ClearRow;
import ai.gr64.Engine.DTOs.Actions.Move;

// To be implemented, should be a graphical user interface
public class GUI implements IUI {

    @Override
    public Move getPlayerInput(GameState state) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void updateUi(GameState state) {

    }

    @Override
    public ClearRow getClearRow(GameState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented.");
    }

}
