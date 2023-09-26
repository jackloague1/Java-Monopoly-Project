package Game;

import java.awt.Color;

import javax.swing.JFrame;

public class GameScreen 
{
    static JFrame window;

    public GameScreen()
    {
        window = new JFrame();
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
    }
}
