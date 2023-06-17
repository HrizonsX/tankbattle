package start;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GameStart {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame tankBattle = new StartPage();
        });
    }
}
