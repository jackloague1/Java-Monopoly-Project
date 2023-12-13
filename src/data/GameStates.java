package src.data;

/**
 * Represents the various states the game can be in. This will be used to change what is displayed
 * on screen depending on the state.
 */
public class GameStates 
{
    public static final int TITLE_SCREEN_STATE = 0;
    public static final int SET_UP_STATE = 1;
    public static final int CREATE_PROFILE_STATE = 2;
    public static final int PROFILE_ERROR_STATE = 3;
    public static final int PROFILE_SCREEN_STATE = 4;
    public static final int OPTIONS_SCREEN_STATE = 5;
    public static final int ROLL_STATE = 6;
    public static final int DICE_DELAY_STATE = 7;
    public static final int DICE_MOVE_STATE = 8;
    public static final int PLAYER_MOVE_STATE = 9;
    public static final int SPACE_EVENT_STATE = 10;
    public static final int NEXT_TURN_STATE = 11;
    public static final int MANAGER_MENU_STATE = 12;
    public static final int MORTGAGING_MENU_STATE = 13;
    public static final int TRADING_PLAYER_SELECT_STATE = 14;
    public static final int TRADING_CREATE_STATE = 15;
    public static final int TRADING_OFFER_STATE = 16;
    public static final int BUILDING_MENU_STATE = 17;
    public static final int DECLARE_BANKRUPTCY_STATE = 18;
    public static final int GAME_OVER_STATE = 19;

    public static int currentGameState = TITLE_SCREEN_STATE;
    public static int previousGameState = TITLE_SCREEN_STATE;
}
