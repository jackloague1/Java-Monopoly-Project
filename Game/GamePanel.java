package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Data.GameStates;
import Data.SpaceData;

public class GamePanel extends JPanel implements ActionListener
{
    public final int SCREEN_WIDTH = 800;
    public final int SCREEN_HEIGHT = 800;
    public final int FPS = 60;
    // public final int ROLL_STATE = 0;
    // public final int PLAYER_MOVE_STATE = 1;
    // public final int SPACE_EVENT_STATE = 2;

    // public static int gameState = GameStates.ROLL_STATE;
    public int playerAmount;
    public int currentPlayerNumber = 1;
    public boolean running = false;

    // public static JLabel titleText;
    // public static JLabel startGameButton;
    // public static JLabel boardLabel;
    // public static Image boardImage;
    // public static JLabel rollButton;
    // public static JLabel nextTurnButton;
    // public static JLabel buyOption;
    // public static JLabel auctionOption;

    public SpaceData spaceData = new SpaceData(); 
    public Dice dice = new Dice(this);  
    public ArrayList<Player> players = new ArrayList<Player>();
    // public Player player = new Player(this, spaceData, dice);
    public UI ui = new UI(this, dice, players);
    public SpaceEvent spaceEvent = new SpaceEvent(this, ui, players, spaceData);

    // public MouseHandler actionHandler = new MouseHandler(this, ui, dice, player);
    // public KeyHandler keyHandler = new KeyHandler(this, ui, dice, player);
    // public KeyBindings keyBindings = new KeyBindings(this);

    public Timer timer;
    // public Thread gameThread;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.addKeyListener(ui.keyHandler);
        this.setFocusable(true);

        // createUI();

        //image = ImageIO.read(new File("Board.png"));

        // imageLabel = new JLabel();
        // imageLabel.setBounds(80, 80, 642, 642);
        
        //image = new ImageIcon(new ImageIcon("Monopoly-Board.png").getImage().getScaledInstance(642, 642, Image.SCALE_DEFAULT));
        // image2 = new ImageIcon("Monopoly-Board.png");
        // image = new ImageIcon("Monopoly-Board.png").getImage();

        // rollButton = new JButton("Roll Dice");
        // rollButton.setBounds(20, 20, 90, 30);

        // this.add(rollButton);
        // imageLabel.setIcon(image2);
        // this.add(imageLabel);
    }

    public void createUI()
    {
        // titleText = new JLabel();
        // titleText.setBounds(150, 350, 500, 100);
        // titleText.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        // titleText.setHorizontalAlignment(SwingConstants.CENTER);
        // titleText.setVerticalAlignment(SwingConstants.CENTER);
        // titleText.setText("Monopoly");
        // this.add(titleText);

        // startGameButton = new JLabel();
        // createButton(startGameButton, new Font("Times New Roman", Font.PLAIN, 26), "Start", 350, 600, 100, 50);

        // boardImage = new ImageIcon("images/Monopoly-Board.png").getImage();
        // boardLabel = new JLabel();
        // boardLabel.setBounds(80, 80, 642, 642);
        // boardImage = new ImageIcon("images/Monopoly-Board.png");
        // boardLabel.setIcon(boardImage);
        // this.add(boardLabel);

        // rollButton = new JLabel();
        // createButton(rollButton, new Font("Times New Roman", Font.PLAIN, 16), "Roll Dice", 20, 20, 90, 30);
        // rollButton.setVisible(false);
        // rollButton.setBounds(20, 20, 90, 30);
        // rollButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        // rollButton.setText("Roll Dice");
        // rollButton.addMouseListener(actionHandler);
        // rollButton.setForeground(Color.white);
        // this.add(rollButton);

        // nextTurnButton = new JLabel();
        // createButton(nextTurnButton, new Font("Times New Roman", Font.PLAIN, 16), "Next Turn", 690, 20, 90, 30);
        // nextTurnButton.setVisible(false);

        // buyOption = new JLabel();
        // createButton(buyOption, new Font("Times New Roman", Font.PLAIN, 18), "Buy", 350, 425, 100, 50);
        // buyOption.setVisible(false);

        // auctionOption = new JLabel();
        // createButton(auctionOption, new Font("Times New Roman", Font.PLAIN, 18), "Auction", 350, 450, 100, 50);
        // auctionOption.setVisible(false);

        // rollButton = new JButton("Roll Dice");
        // rollButton.setBounds(20, 20, 90, 30);
        // rollButton.addMouseListener(actionHandler);
        // rollButton.setActionCommand("Roll Button Clicked");
        // rollButton.setFocusable(false);

        // this.add(rollButton);
        // repaint();

        // rollButton.addMouseListener(new MouseListener()
        // {

        //     @Override
        //     public void mouseClicked(MouseEvent e) {}

        //     @Override
        //     public void mousePressed(MouseEvent e) 
        //     {
        //         if (SwingUtilities.isLeftMouseButton(e))
        //         {
        //             System.out.println("Button clicked");
        //         }
        //     }

        //     @Override
        //     public void mouseReleased(MouseEvent e) {}

        //     @Override
        //     public void mouseEntered(MouseEvent e) {}

        //     @Override
        //     public void mouseExited(MouseEvent e) {}
        // });
    }

    // public void createButton(JLabel optionLabel, Font font, String optionName, int labelX, int labelY, int labelWidth, int labelHeight)
    // {
    //     optionLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
    //     optionLabel.setFont(font);
    //     optionLabel.setForeground(Color.white);
    //     optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    //     optionLabel.setVerticalAlignment(SwingConstants.CENTER);
    //     optionLabel.setText(optionName);
    //     optionLabel.addMouseListener(actionHandler);
    //     // optionLabel.setVisible(false);
    //     this.add(optionLabel);
    // }

    // public void startGameThread()
    // {
    //     gameThread = new Thread(this);

    //     // Automatically calls the run() method
    //     gameThread.start();
    // }

    // @Override
    // public void run()
    // {
    //     // Calculates amount of nanoseconds in a second divided by FPS
    //     double drawInterval = 1000000000/FPS;
    //     double delta = 0;
    //     long lastTime = System.nanoTime();
    //     long currentTime;

    //     long timer = 0;
    //     int drawCount = 0;

    //     // As long as gameThread exists, this while loop will run
    //     while(gameThread != null)
    //     {
    //         currentTime = System.nanoTime();
    //         delta += (currentTime - lastTime) / drawInterval;
    //         timer += (currentTime - lastTime);
    //         lastTime = currentTime;

    //         if (delta >= 1)
    //         {
    //             // Updates information such as sprite positions
    //             update();

    //             // Draws the screen with updated information
    //             repaint();

    //             delta--;
    //             drawCount++;
    //         }     
            
    //         // Displays FPS
    //         if (timer >= 1000000000)
    //         {
    //             // System.out.println("FPS: " + drawCount);
    //             drawCount = 0;
    //             timer = 0;
    //         }
    //     }
    // }

    public void createPlayers()
    {
        playerAmount = Integer.parseInt(ui.playerAmount.getText());
            
        for (int i = 0; i < playerAmount; i++)
        {
            Player player = new Player(this, spaceData, dice, String.format("Player %d", i+1));
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

    public void startTimer()
    {
        running = true;
        timer = new Timer(200, this);
        timer.start();
    }

    public void update()
    {
        if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            changePlayerNumber();
            System.out.printf("Current player number: %d%n", currentPlayerNumber);
        }
        else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE)
        {
            players.get(currentPlayerNumber-1).move();
        }
        else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE)
        {
            spaceEvent.chooseEvent();
        }
        // player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Converts the Graphics object parameter to a Graphics2D object
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.white);
        // g2d.fillRect(10, 100, 25, 25);
        //image.paintIcon(this, g, 80, 80);
        // g2d.drawImage(boardImage, 80, 80, null);

        //image2.paintIcon(this, g, 0, 0);

        // player.draw(g2d);

        //if (gameState == 0)
        //{
         //   rollButton.setForeground(Color.white);
        //}

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE)
        {
            ui.draw();
            // ui.titleText.setVisible(true);
            // ui.titleStartButton.setVisible(true);
            // ui.playerAmountText.setVisible(false);
        }
        else if (GameStates.currentGameState == GameStates.CREATE_GAME_STATE)
        {
            ui.draw();
        }
        else if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            players.get(currentPlayerNumber-1).draw(g2d);
            ui.draw();
            // ui.titleText.setVisible(false);
            // ui.titleStartButton.setVisible(false);
            // ui.rollButton.setVisible(true);
            // ui.nextTurnButton.setVisible(true);
            // ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
        }
        else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            players.get(currentPlayerNumber-1).draw(g2d);
            dice.draw(g2d);
            ui.draw();
            // ui.titleText.setVisible(false);
            // ui.titleStartButton.setVisible(false);
            // ui.rollButton.setForeground(new Color(255, 255, 255, 75));
            // ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
        }
        else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            players.get(currentPlayerNumber-1).draw(g2d);
            dice.draw(g2d);
            spaceEvent.draw(g2d);
            ui.draw();
            // ui.titleText.setVisible(false);
            // ui.titleStartButton.setVisible(false);
            // ui.rollButton.setForeground(new Color(255, 255, 255, 75));
            // ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
        }
        else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE)
        {
            g2d.drawImage(ui.boardImage, 80, 80, null);
            players.get(currentPlayerNumber-1).draw(g2d);
            dice.draw(g2d);
            ui.draw();
            // ui.titleText.setVisible(false);
            // ui.titleStartButton.setVisible(false);
        }

        // Not needed but saves memory
        // g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (running)
        {
            update();
        }

        repaint();
    }
}
