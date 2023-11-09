package game;

import data.GameStates;
import data.Settings;
import data.SpaceData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class extends the JPanel container, which can store a group of components.
 */
public class GamePanel extends JPanel implements Runnable {
    private final int screenWidth;
    private final int screenHeight;
    private final int fps;

    public double drawInterval;
    public double delta;
    public double playerDelta;
    public double diceDelta;
    public long lastTime;
    public long currentTime;

    public long timer;
    public int drawCount;

    public int playerAmount;
    public int currentPlayerNumber;

    public SpaceData spaceData; 
    public MainMenu mainMenu;
    public Dice dice;  
    public ArrayList<Player> players;
    public PlayerInfoBox playerInfoBox;
    public UI ui;
    public SpaceEvent spaceEvent;

    private Thread gameThread;

    /**
    * Constructor.
    */
    GamePanel() {
        screenWidth = 800;
        screenHeight = 800;
        fps = 60;

        spaceData = new SpaceData(); 
        mainMenu = new MainMenu();
        dice = new Dice(this);  
        players = new ArrayList<Player>();
        playerInfoBox = new PlayerInfoBox(this);
        ui = new UI(this, dice, players);
        spaceEvent = new SpaceEvent(this, ui, players, spaceData);

        // Calculates amount of nanoseconds in a second divided by FPS
        drawInterval = 1000000000 / fps;

        delta = 0;
        playerDelta = 0;
        diceDelta = 0;
        lastTime = System.nanoTime();

        timer = 0;
        drawCount = 0;

        currentPlayerNumber = 0;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.addKeyListener(ui.keyHandler);
        this.setFocusable(true);

        this.startGameThread();
    }

    /**
    * Starts the game thread.
    */
    public void startGameThread() {
        gameThread = new Thread(this);

        // Automatically calls the run() method.
        gameThread.start();
    }

    /**
    * Creates the main game loop.
    */
    @Override
    public void run() {
        // Calculates amount of nanoseconds in a second divided by FPS
        // double drawInterval = 1000000000/FPS;
        // double delta = 0;
        // double playerDelta = 0;
        // long lastTime = System.nanoTime();
        // long currentTime;

        // long timer = 0;
        // int drawCount = 0;

        // As long as gameThread exists, this while loop will run
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            playerDelta += (currentTime - lastTime) / drawInterval;
            diceDelta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // Updates information such as sprite positions
                update();

                // Draws the screen with updated information
                repaint();

                delta--;
                drawCount++;

                if (playerDelta >= Settings.PLAYER_MOVE_SPEED) {
                    playerDelta -= Settings.PLAYER_MOVE_SPEED;
                }

                if (diceDelta >= Settings.DICE_MOVE_SPEED) {
                    diceDelta -= Settings.DICE_MOVE_SPEED;
                }
            }     
            
            // Displays FPS
            if (timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
    * Creates the amount of players given by the users.
    */
    public void createPlayers() {
        playerAmount = Integer.parseInt(ui.playerAmount.getText());
            
        for (int i = 0; i < playerAmount; i++) {
            Player player = new Player(this, spaceData, dice, i + 1, 
                                       String.format("Player %d", i + 1));
            players.add(player);
        }       
    }

    /**
    * Changes the player number when a player ends their turn.
    */
    public void changePlayerNumber() {
        if (currentPlayerNumber < playerAmount) {
            currentPlayerNumber++;
        } else {
            currentPlayerNumber = 1;
        }
    }

    /**
    * Updates game information.
    */
    public void update() {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            ui.updateCursor();
        }
        if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            ui.updateCursor();
            playerInfoBox.setCurrentPlayer();
        } else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
            players.get(currentPlayerNumber - 1).move();
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            spaceEvent.chooseEvent();
        }
    }

    /**
    * Draws the updated game data on screen.
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Converts the Graphics object parameter to a Graphics2D object
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.white);

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            // mainMenu.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.CREATE_GAME_STATE) {
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            ui.draw(g2d);

            for (int i = 0; i < players.size(); i++) {
                players.get(i).draw(g2d);
            }

            playerInfoBox.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
            ui.draw(g2d);

            for (int i = 0; i < players.size(); i++) {
                players.get(i).draw(g2d);
            }

            playerInfoBox.draw(g2d);
            dice.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            ui.draw(g2d);

            for (int i = 0; i < players.size(); i++) {
                players.get(i).draw(g2d);
            }

            playerInfoBox.draw(g2d);
            dice.draw(g2d);
            spaceEvent.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            ui.draw(g2d);

            for (int i = 0; i < players.size(); i++) {
                players.get(i).draw(g2d);
            }

            playerInfoBox.draw(g2d);
            dice.draw(g2d);
            spaceEvent.draw(g2d);
        }

        // Not needed but saves memory
        // g2d.dispose();
    }
}
