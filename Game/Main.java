package Game;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main 
{
    static JFrame window;
    static Container container;
    static JPanel imagePanel;
    static JLabel imageLabel;
    static ImageIcon image;

    public static void main(String[] args)
    {
        window = new JFrame();
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        // window.setLayout(null);
        // container = window.getContentPane();

        // imagePanel = new JPanel();
        // imagePanel.setBounds(45, 30, 510, 510);
        // imagePanel.setBackground(Color.black);
        // container.add(imagePanel);
        //window.pack();

        // imageLabel = new JLabel();
        
        // image = new ImageIcon(new ImageIcon("Board.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

        // imageLabel.setIcon(image);
        // imagePanel.add(imageLabel);

        window.setVisible(true);

        // gamePanel.startGameThread();
    }
}