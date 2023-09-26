package ActionHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Data.GameStates;
import Game.Dice;
import Game.GamePanel;
import Game.Player;
import Game.UI;

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
                gamePanel.startTimer();
            }
        }
    }
    
}
