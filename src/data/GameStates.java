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
    public static final int DICE_MOVE_STATE = 7;
    public static final int PLAYER_MOVE_STATE = 8;
    public static final int SPACE_EVENT_STATE = 9;
    public static final int NEXT_TURN_STATE = 10;

    public static int currentGameState = TITLE_SCREEN_STATE;
}
