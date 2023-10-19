package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import data.GameStates;
import data.Settings;
import data.SpaceData;

/**
 * This class extends the JPanel container, which can store a group of components.
 */
public class GamePanel extends JPanel implements Runnable
{
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 800;
    private final int FPS = 60;

    // Calculates amount of nanoseconds in a second divided by FPS
    public double drawInterval = 1000000000/FPS;
    public double delta = 0;
    public double playerDelta = 0;
    public long lastTime = System.nanoTime();
    public long currentTime;

    public long timer = 0;
    public int drawCount = 0;

    public int playerAmount;
    public int currentPlayerNumber = 0;

    public SpaceData spaceData = new SpaceData(); 
    public Dice dice = new Dice(this);  
    public ArrayList<Player> players = new ArrayList<Player>();
    public PlayerInfoBox playerInfoBox = new PlayerInfoBox(this);
    public UI ui = new UI(this, dice, players);
    public SpaceEvent spaceEvent = new SpaceEvent(this, ui, players, spaceData);

    private Thread gameThread;

    GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.addKeyListener(ui.keyHandler);
        this.setFocusable(true);

        this.startGameThread();

    }

    public void startGameThread()
    {
        gameThread = new Thread(this);

        // Automatically calls the run() method
        gameThread.start();
    }

    @Override
    public void run()
    {
        // Calculates amount of nanoseconds in a second divided by FPS
        // double drawInterval = 1000000000/FPS;
        // double delta = 0;
        // double playerDelta = 0;
        // long lastTime = System.nanoTime();
        // long currentTime;

        // long timer = 0;
        // int drawCount = 0;

        // As long as gameThread exists, this while loop will run
        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            playerDelta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                // Updates information such as sprite positions
                update();

                // Draws the screen with updated information
                repaint();

                delta--;
                drawCount++;

                if (playerDelta >= Settings.PLAYER_MOVE_SPEED)
                {
                    playerDelta -= Settings.PLAYER_MOVE_SPEED;
                }
            }     
            
            // Displays FPS
            if (timer >= 1000000000)
            {
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void createPlayers()
    {
        playerAmount = Integer.parseInt(ui.playerAmount.getText());
            
        for (int i = 0; i < playerAmount; i++)
        {
            Player player = new Player(this, spaceData, dice, i+1, String.format("Player %d", i+1));
            players.add(player);
        }       
    }

    public void changePlayerNumber()
    {
        if (currentPlayerNumber < playerAmount)
        {
            currentPlayerNumber++;
        }
        else
        {
            currentPlayerNumber = 1;
        }
    }

    public void update()
    {
        if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            playerInfoBox.setCurrentPlayer();
        }
        else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE)
        {
            players.get(currentPlayerNumber-1).move();
        }
        else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE)
        {
            spaceEvent.chooseEvent();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Converts the Graphics object parameter to a Graphics2D object
        Graphics2D g2d = (Graphics2D)g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.white);

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE)
        {
            ui.draw();
        }
        else if (GameStates.currentGameState == GameStates.CREATE_GAME_STATE)
        {
            ui.draw();
        }
        else if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            for (int i = 0; i < players.size(); i++)
            {
                players.get(i).draw(g2d);
            }
            playerInfoBox.draw(g2d);
            ui.draw();
        }
        else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            for (int i = 0; i < players.size(); i++)
            {
                players.get(i).draw(g2d);
            }
            playerInfoBox.draw(g2d);
            dice.draw(g2d);
            ui.draw();
        }
        else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            for (int i = 0; i < players.size(); i++)
            {
                players.get(i).draw(g2d);
            }
            playerInfoBox.draw(g2d);
            dice.draw(g2d);
            spaceEvent.draw(g2d);
            ui.draw();
        }
        else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            for (int i = 0; i < players.size(); i++)
            {
                players.get(i).draw(g2d);
            }
            playerInfoBox.draw(g2d);
            dice.draw(g2d);
            spaceEvent.draw(g2d);
            ui.draw();
        }

        // Not needed but saves memory
        // g2d.dispose();
    }
}
