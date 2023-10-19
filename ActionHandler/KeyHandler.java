package actionhandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import data.GameStates;
import game.Dice;
import game.GamePanel;
import game.Player;
import game.UI;

/**
 * Processes user input from the keyboard.
 */
public class KeyHandler extends KeyAdapter
{
    public GamePanel gamePanel;
    public UI ui;
    public Dice dice;
    public ArrayList<Player> players;

    public KeyHandler(GamePanel gamePanel, UI ui, Dice dice, ArrayList<Player> players)
    {
        this.gamePanel = gamePanel;
        this.ui = ui;
        this.dice = dice;
        this.players = players;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("KeyHandler test");
        int code = e.getKeyCode();

        if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            if (code == KeyEvent.VK_R)
            {
                GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                
                dice.getDiceResult();
                // gamePanel.startTimer();
            }
        }
    }
    
}
