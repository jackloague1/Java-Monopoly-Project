package src.data;

/**
 * Initalizes and stores various settings for the game.
 */
public class Settings {
    // The maximum number of profiles that can be created for the game.
    public static final int MAX_NUMBER_PROFILES = 25;
    
    // The amount divided by 60 (FPS) the rolled dice will move per second across the screen.
    public static final int DICE_MOVE_SPEED = 2;

    // The amount divided by 60 (FPS) the game will delay after the dice have been rolled.
    public static final int DICE_DELAY = 20;

    // The amount divided by 60 (FPS) the player token will move per second across the screen.
    public static final int PLAYER_MOVE_SPEED = 10;

    // The salary players collect when landing on or passing the Go space.
    public static final int SALARY = 200;

    // The bonus players collect when landing on the Free Parking space.
    public static final int FREE_PARKING_BONUS = 0;

    // The amount of money to pay for a player to leave jail.
    public static final int JAIL_BAIL = 50;
}
