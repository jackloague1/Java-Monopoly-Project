package actionhandler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import data.GameStates;
import game.GamePanel;

public class KeyBindings {
    GamePanel gamePanel;

    KeyBindings(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        AbstractAction test = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (GameStates.currentGameState == 0) {
                    System.out.println("Key Bindings class test");
                }
            }
        };

        gamePanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0),
                       "Test");
        gamePanel.getActionMap().put("Test", test);
    }
}
