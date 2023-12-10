package src.actionhandler;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import src.data.GameStates;
import src.data.Settings;
import src.data.SpaceData;
import src.game.Dice;
import src.game.GamePanel;
import src.game.Player;
import src.game.Profile;
import src.game.Ui;

/**
 * Processes user input from the keyboard.
 */
public class KeyHandler extends KeyAdapter {
    public GamePanel gamePanel;
    public SpaceData spaceData;
    public Ui ui;
    public Dice dice;
    public ArrayList<Profile> profiles;
    public ArrayList<Player> players;

    /**
    * Constructor.
    */
    public KeyHandler(GamePanel gamePanel, SpaceData spaceData, Ui ui, Dice dice, 
                      ArrayList<Profile> profiles, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.ui = ui;
        this.dice = dice;
        this.profiles = profiles;
        this.players = players;
    }

    /**
    * Invokes when a key is pressed.
    */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyHandler test");
        int code = e.getKeyCode();

        if (GameStates.currentGameState == GameStates.SET_UP_STATE 
                   || GameStates.currentGameState == GameStates.CREATE_PROFILE_STATE 
                   || GameStates.currentGameState == GameStates.OPTIONS_SCREEN_STATE) {
            if (code == KeyEvent.VK_B) {
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            } 
        } else if (GameStates.currentGameState == GameStates.PROFILE_ERROR_STATE) {
            if (code == KeyEvent.VK_ESCAPE) {
                GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
            } else if (code == KeyEvent.VK_B) {
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.PROFILE_SCREEN_STATE) {
            if (code == KeyEvent.VK_C) {
                if (profiles.size() >= (Settings.MAX_NUMBER_PROFILES)) {
                    GameStates.currentGameState = GameStates.PROFILE_ERROR_STATE;
                } else {
                    GameStates.currentGameState = GameStates.CREATE_PROFILE_STATE;
                }
            } else if (code == KeyEvent.VK_B) {
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            if (code == KeyEvent.VK_R) {
                dice.getDiceResult();
                ui.rollButton.setForeground(new Color(255, 255, 255, 75));
                ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            if (spaceData.currentSpaceType == "Go") {
                if (code == KeyEvent.VK_ENTER) {
                    GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                }
            }
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            if (code == KeyEvent.VK_N) {
                gamePanel.changePlayerNumber();
                ui.rollButton.setForeground(Color.white);
                ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
                GameStates.currentGameState = GameStates.ROLL_STATE;
            }
        }
    }    
}
