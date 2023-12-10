package src.game;

/**
 * The application will begin with this class. It will create a GameFrame object which will draw a
 * window using the JFrame class.
 */
class Main {
    private static GameFrame gameFrame;

    public static void main(String[] args) {
        gameFrame = new GameFrame();
    }
}