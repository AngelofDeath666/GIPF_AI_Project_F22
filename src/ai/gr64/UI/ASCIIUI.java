package ai.gr64.UI;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import ai.gr64.Data.Interfaces.IUI;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.Move;

public class ASCIIUI implements IUI {
    boolean useUnicode = false;

    public ASCIIUI(boolean useUnicode) {
        if (useUnicode) {
            try {
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
                useUnicode = true;
            } catch (UnsupportedEncodingException e) {
                useUnicode = false;
            }
        }

        if (this.useUnicode){
            //set characters
        } else {
            //set ascii characters
        }
    }

    @Override
    public Move GetPlayerInput() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void UpdateUi(GameState state) {
        // TODO Auto-generated method stub

    }

}
