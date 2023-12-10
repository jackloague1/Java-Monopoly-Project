package src.game;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * The JFrame class which GameFrame extends will draw a window for the game.
 */
class GameFrame extends JFrame {
    GamePanel gamePanel;

    /**
    * Constructor.
    */
    GameFrame() {
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
    }
}
