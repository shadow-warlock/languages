package main;

import main.gui.ProgramFrame;

public class Start {
    public static void main(String[] args) {
        Application.getInstance().frame = new ProgramFrame();
    }
}
