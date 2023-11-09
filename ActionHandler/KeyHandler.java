package actionhandler;

import data.GameStates;
import data.SpaceData;
import game.Dice;
import game.GamePanel;
import game.Player;
import game.UI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Processes user input from the keyboard.
 */
public class KeyHandler extends KeyAdapter {
    public GamePanel gamePanel;
    public UI ui;
    public Dice dice;
    public ArrayList<Player> players;

    /**
    * Constructor.
    */
    public KeyHandler(GamePanel gamePanel, UI ui, Dice dice, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.ui = ui;
        this.dice = dice;
        this.players = players;
    }

    /**
    * Invokes when a key is pressed.
    */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyHandler test");
        int code = e.getKeyCode();

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP) {
                ui.changeButton(code, ui.titleScreenButtons);
            } else if (ui.currentButton == ui.titleScreenButtons[0] && code == KeyEvent.VK_ENTER) {
                GameStates.currentGameState = GameStates.CREATE_GAME_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
                ui.changeButton(code, ui.boardButtons);
            } else if ((ui.currentButton == ui.boardButtons[0] && code == KeyEvent.VK_ENTER) 
                        || code == KeyEvent.VK_R) {
                GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                
                dice.getDiceResult();
            }
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            if (SpaceData.currentSpaceType == "Go") {
                if (code == KeyEvent.VK_ENTER) {
                    GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                }
            }
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            if (code == KeyEvent.VK_N) {
                gamePanel.changePlayerNumber();
                GameStates.currentGameState = GameStates.ROLL_STATE;
            }
        }
    }    
}
