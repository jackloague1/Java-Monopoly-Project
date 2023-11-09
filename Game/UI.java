package game;

import actionhandler.KeyHandler;
import actionhandler.MouseHandler;
import data.GameStates;
import data.SpaceData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Represents any element a user or player can interact with, such as buttons.
 */
public class UI {
    public GamePanel gamePanel;
    public Dice dice;
    public ArrayList<Player> players;

    public String [] titleScreenButtons;
    public String [] boardButtons;
    public int [] titleScreenButtonsX;
    public int [] titleScreenButtonsY;
    public int [] titleScreenButtonsWidth;
    public int [] titleScreenButtonsHeight;

    public String currentButton;
    public int currentButtonIndex;

    public int [] cursorPositionX;
    public int [] cursorPositionY;

    public JLabel titleText;
    public JLabel titleStartButton;
    public JLabel playerAmountText;
    public JTextField playerAmount;
    public JLabel startGameButton;
    public static JLabel boardLabel;
    public static Image boardImage;
    public static Image rollButtonImage;
    public static JLabel rollButtonText;
    public static Image playerManagerButtonImage;
    public static Image nextTurnButtonImage;
    public static JLabel nextTurnButtonText;
    public static JLabel buyOptionText;
    public static JLabel passOptionText;

    public MouseHandler mouseHandler;
    public KeyHandler keyHandler;

    /**
    * Constructor.
    */
    public UI(GamePanel gamePanel, Dice dice, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.dice = dice;
        this.players = players;

        cursorPositionX = new int [] {270, 270, 300};
        cursorPositionY = new int [] {410, 440, 425};

        titleScreenButtons = new String [] {"Start", "Profiles", "Options"};
        boardButtons = new String [] {"Roll Dice", "Manager", "Next Turn"};
        titleScreenButtonsX = new int [] {300, 300, 300};
        titleScreenButtonsY = new int [] {400, 450, 500};
        titleScreenButtonsWidth = new int [] {200, 200, 200};
        titleScreenButtonsHeight = new int [] {50, 50, 50};

        currentButton = titleScreenButtons[0];
        currentButtonIndex = 0;

        mouseHandler = new MouseHandler(gamePanel, this, dice, players);
        keyHandler = new KeyHandler(gamePanel, this, dice, players);

        createUI();
    }

    public void createUI() {
        titleText = new JLabel();
        titleText.setBounds(150, 350, 500, 100);
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setVerticalAlignment(SwingConstants.CENTER);
        titleText.setText("Monopoly");
        gamePanel.add(titleText);

        titleStartButton = new JLabel();
        createButton(titleStartButton, new Font("Times New Roman", Font.PLAIN, 26), "Start", 350, 600, 100, 50);
        
        playerAmountText = new JLabel();
        playerAmountText.setBounds(150, 350, 200, 50);
        playerAmountText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        playerAmountText.setText("Input amount of players:");
        gamePanel.add(playerAmountText);

        playerAmount = new JTextField(null, null, 0);
        playerAmount.setBounds(450, 350, 50, 20);
        gamePanel.add(playerAmount);

        startGameButton = new JLabel();
        createButton(startGameButton, new Font("Times New Roman", Font.PLAIN, 26), "Start", 350, 600, 100, 50);
        
        boardImage = new ImageIcon("images/Board.png").getImage();
        // boardLabel = new JLabel();
        // boardLabel.setBounds(80, 80, 642, 642);
        // boardImage = new ImageIcon("images/Monopoly-Board.png");
        // boardLabel.setIcon(boardImage);
        // this.add(boardLabel);

        rollButtonImage = new ImageIcon("images/Roll-Button.png").getImage();
        rollButtonText = new JLabel();
        createButton(rollButtonText, new Font("Times New Roman", Font.PLAIN, 16), "Roll Dice", 71, 41, 100, 30);
        rollButtonText.setVisible(false);
        // rollButton.setBounds(20, 20, 90, 30);
        // rollButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        // rollButton.setText("Roll Dice");
        // rollButton.addMouseListener(actionHandler);
        // rollButton.setForeground(Color.white);
        // this.add(rollButton);

        playerManagerButtonImage = new ImageIcon("images/Button-Background.png").getImage();

        nextTurnButtonImage = new ImageIcon("images/Button-Background.png").getImage();
        nextTurnButtonText = new JLabel();
        createButton(nextTurnButtonText, new Font("Times New Roman", Font.PLAIN, 16), "Next Turn", 622, 41, 100, 30);
        nextTurnButtonText.setVisible(false);

        buyOptionText = new JLabel();
        createButton(buyOptionText, new Font("Times New Roman", Font.PLAIN, 18), "Buy", 350, 425, 100, 50);
        buyOptionText.setVisible(false);

        passOptionText = new JLabel();
        createButton(passOptionText, new Font("Times New Roman", Font.PLAIN, 18), "Pass", 350, 450, 100, 50);
        passOptionText.setVisible(false);
    }

    public void createButton(JLabel optionLabel, Font font, String optionName, int labelX, int labelY, int labelWidth, int labelHeight)
    {
        optionLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        optionLabel.setFont(font);
        optionLabel.setForeground(Color.white);
        optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        optionLabel.setVerticalAlignment(SwingConstants.CENTER);
        optionLabel.setText(optionName);
        optionLabel.addMouseListener(mouseHandler);
        // optionLabel.setVisible(false);
        gamePanel.add(optionLabel);
    }

    public void changeButton(int code, String [] buttonArray)
    {
        if (code == KeyEvent.VK_DOWN)
            {
                if (currentButtonIndex + 1 >= buttonArray.length)
                {
                    currentButtonIndex = 0;
                }
                else
                {
                    currentButtonIndex = currentButtonIndex + 1;
                }
                
                currentButton = buttonArray[currentButtonIndex];
                System.out.print(currentButton);
            }
            else if (code == KeyEvent.VK_UP)
            {
                if (currentButtonIndex - 1 < 0)
                {
                    currentButtonIndex = buttonArray.length - 1;
                }
                else
                {
                    currentButtonIndex = currentButtonIndex - 1;
                }

                currentButton = buttonArray[currentButtonIndex];
                System.out.print(currentButton);
            }


    }

    public void updateCursor()
    {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE)
        {
            cursorPositionX = new int [] {270, 270, 300};

            if (currentButton == titleScreenButtons[0])
            {
                cursorPositionY = new int [] {410, 440, 425};
            }
            else if (currentButton == titleScreenButtons[1])
            {
                cursorPositionY = new int [] {460, 490, 475};
            }
            else if (currentButton == titleScreenButtons[2])
            {
                cursorPositionY = new int [] {510, 540, 525};
            }
        }
        else if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            cursorPositionY = new int [] {46, 66, 56};

            if (currentButton == boardButtons[0])
            {
                cursorPositionX = new int [] {41, 41, 61};
            }
            else if (currentButton == boardButtons[1])
            {
                cursorPositionX = new int [] {300, 300, 330};
            }
            else if (currentButton == boardButtons[2])
            {
                cursorPositionX = new int [] {592, 592, 622};
            }
        }
    }

    /**
    * Draws the UI on screen.
    */
    public void draw(Graphics2D g2d) {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            titleText.setVisible(false);
            titleStartButton.setVisible(true);
            playerAmountText.setVisible(false);
            playerAmount.setVisible(false);
            startGameButton.setVisible(false);

            HelperFunctions.drawText(g2d, "Monopoly", Color.black, 72, 200, 200, 
                                     400, 100, true, true);
            HelperFunctions.drawText(g2d, "Start", Color.white, 32, 300, 400, 
                                     200, 50, true, true);
            HelperFunctions.drawText(g2d, "Profiles", Color.white, 32, 300, 450, 
                                    200, 50, true, true);
            HelperFunctions.drawText(g2d, "Options", Color.white, 32, 300, 500, 
                                     200, 50, true, true);
    
            g2d.setColor(Color.black);
            g2d.fillPolygon(cursorPositionX, cursorPositionY, 3);
        } else if (GameStates.currentGameState == GameStates.CREATE_GAME_STATE) {
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(true);
            playerAmount.setVisible(true);
            startGameButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            g2d.drawImage(boardImage, 80, 80, null);

            g2d.drawImage(rollButtonImage, 81, 41, null);
            HelperFunctions.drawText(g2d, "Roll Dice", Color.white, 16, 71, 41, 
                                     100, 30, true, true);

            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            HelperFunctions.drawText(g2d, "Manager", Color.white, 16, 350, 41, 100, 30, true, true);

            g2d.drawImage(nextTurnButtonImage, 622, 41, null);
            HelperFunctions.drawText(g2d, "Next Turn", new Color(255, 255, 255, 75), 16, 622, 41, 
                                     100, 30, true, true);

            g2d.setColor(Color.black);
            g2d.fillPolygon(cursorPositionX, cursorPositionY, 3);


            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(false);
            playerAmount.setVisible(false);
            startGameButton.setVisible(false);
            // rollButtonText.setVisible(true);
            // nextTurnButtonText.setVisible(true);
            // nextTurnButtonText.setForeground(new Color(255, 255, 255, 75));
        } else if (GameStates.currentGameState == GameStates.DICE_MOVE_STATE
                   || GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
            g2d.drawImage(boardImage, 80, 80, null);

            g2d.drawImage(rollButtonImage, 81, 41, null);
            HelperFunctions.drawText(g2d, "Roll Dice", new Color(255, 255, 255, 75), 16, 71, 41, 
                                     100, 30, true, true);

            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            HelperFunctions.drawText(g2d, "Manager", new Color(255, 255, 255, 75), 16, 350, 41, 
                                     100, 30, true, true);

            g2d.drawImage(nextTurnButtonImage, 622, 41, null);
            HelperFunctions.drawText(g2d, "Next Turn", new Color(255, 255, 255, 75), 16, 622, 41, 
                                     100, 30, true, true);

            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(false);
            // rollButtonText.setForeground(new Color(255, 255, 255, 75));
            // nextTurnButtonText.setForeground(new Color(255, 255, 255, 75));          
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            g2d.drawImage(boardImage, 80, 80, null);

            g2d.drawImage(rollButtonImage, 81, 41, null);
            HelperFunctions.drawText(g2d, "Roll Dice", new Color(255, 255, 255, 75), 16, 71, 41, 
                                     100, 30, true, true);

            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            HelperFunctions.drawText(g2d, "Manager", Color.white, 16, 350, 41, 
                                     100, 30, true, true);

            g2d.drawImage(nextTurnButtonImage, 622, 41, null);
            HelperFunctions.drawText(g2d, "Next Turn", new Color(255, 255, 255, 75), 16, 622, 41, 
                                     100, 30, true, true);

            if (SpaceData.currentSpaceType == "Go") {
                HelperFunctions.drawText(g2d, "Ok", new Color(153, 235, 255), 16, 350, 475, 
                                         100, 30, true, true);
            }
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(false);
            // rollButtonText.setForeground(new Color(255, 255, 255, 75));
            // nextTurnButtonText.setForeground(new Color(255, 255, 255, 75));
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            g2d.drawImage(boardImage, 80, 80, null);

            g2d.drawImage(rollButtonImage, 81, 41, null);
            HelperFunctions.drawText(g2d, "Roll Dice", new Color(255, 255, 255, 75), 16, 71, 41, 
                                     100, 30, true, true);

            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            HelperFunctions.drawText(g2d, "Manager", Color.white, 16, 350, 41, 
                                     100, 30, true, true);

            g2d.drawImage(nextTurnButtonImage, 622, 41, null);
            HelperFunctions.drawText(g2d, "Next Turn", Color.white, 16, 622, 41, 
                                     100, 30, true, true);

            titleText.setVisible(false);
            titleStartButton.setVisible(false);
        }
    }
}
