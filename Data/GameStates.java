package Data;

public class GameStates 
{
    public static final int TITLE_SCREEN_STATE = 0;
    public static final int CREATE_GAME_STATE = 1;
    public static final int ROLL_STATE = 2;
    public static final int PLAYER_MOVE_STATE = 3;
    public static final int SPACE_EVENT_STATE = 4;
    public static final int NEXT_TURN_STATE = 5;

    public static int currentGameState = TITLE_SCREEN_STATE;
}
