package src.game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import src.data.GameStates;

/**
* Represents background images for the game.
*/
public class Backgrounds {
    Image mainMenuBackground;
    Image mainGameBackground;

    public Backgrounds() {
        mainMenuBackground = new ImageIcon("images/Menu-Background-3.jpg").getImage();
        mainGameBackground = new ImageIcon("images/Menu-Background-2.jpg").getImage();
    }

    public void draw(Graphics2D g2d) {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE
            || GameStates.currentGameState == GameStates.SET_UP_STATE
            || GameStates.currentGameState == GameStates.CREATE_PROFILE_STATE
            || GameStates.currentGameState == GameStates.PROFILE_SCREEN_STATE
            || GameStates.currentGameState == GameStates.PROFILE_ERROR_STATE
            || GameStates.currentGameState == GameStates.MANAGER_MENU_STATE
            || GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE
            || GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE
            || GameStates.currentGameState == GameStates.TRADING_CREATE_STATE
            || GameStates.currentGameState == GameStates.TRADING_OFFER_STATE
            || GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
            g2d.drawImage(mainMenuBackground, 0, 0, 800, 800, null);
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE 
                   || GameStates.currentGameState == GameStates.DICE_DELAY_STATE
                   || GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE
                   || GameStates.currentGameState == GameStates.SPACE_EVENT_STATE
                   || GameStates.currentGameState == GameStates.NEXT_TURN_STATE
                   || GameStates.currentGameState == GameStates.GAME_OVER_STATE) {
            g2d.drawImage(mainGameBackground, 0, 0, 800, 800, null);
        }
    }
}
