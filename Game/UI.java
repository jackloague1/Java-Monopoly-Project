package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ActionHandler.KeyHandler;
import ActionHandler.MouseHandler;
import Data.GameStates;

public class UI 
{
    public GamePanel gamePanel;
    public Dice dice;
    public ArrayList<Player> players;
    // public Player player;

    public JLabel titleText;
    public JLabel titleStartButton;
    public JLabel playerAmountText;
    public JTextField playerAmount;
    public JLabel startGameButton;
    public static JLabel boardLabel;
    public static Image boardImage;
    public static JLabel rollButton;
    public static JLabel nextTurnButton;
    public static JLabel buyOption;
    public static JLabel auctionOption;

    public MouseHandler mouseHandler;
    public KeyHandler keyHandler;

    public UI(GamePanel gamePanel, Dice dice, ArrayList<Player> players)
    {
        this.gamePanel = gamePanel;
        this.dice = dice;
        this.players = players;

        mouseHandler = new MouseHandler(gamePanel, this, dice, players);
        keyHandler = new KeyHandler(gamePanel, this, dice, players);

        createUI();
    }

    public void createUI()
    {
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
        
        boardImage = new ImageIcon("images/Monopoly-Board.png").getImage();
        // boardLabel = new JLabel();
        // boardLabel.setBounds(80, 80, 642, 642);
        // boardImage = new ImageIcon("images/Monopoly-Board.png");
        // boardLabel.setIcon(boardImage);
        // this.add(boardLabel);

        rollButton = new JLabel();
        createButton(rollButton, new Font("Times New Roman", Font.PLAIN, 16), "Roll Dice", 20, 20, 90, 30);
        rollButton.setVisible(false);
        // rollButton.setBounds(20, 20, 90, 30);
        // rollButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        // rollButton.setText("Roll Dice");
        // rollButton.addMouseListener(actionHandler);
        // rollButton.setForeground(Color.white);
        // this.add(rollButton);

        nextTurnButton = new JLabel();
        createButton(nextTurnButton, new Font("Times New Roman", Font.PLAIN, 16), "Next Turn", 690, 20, 90, 30);
        nextTurnButton.setVisible(false);

        buyOption = new JLabel();
        createButton(buyOption, new Font("Times New Roman", Font.PLAIN, 18), "Buy", 350, 425, 100, 50);
        buyOption.setVisible(false);

        auctionOption = new JLabel();
        createButton(auctionOption, new Font("Times New Roman", Font.PLAIN, 18), "Auction", 350, 450, 100, 50);
        auctionOption.setVisible(false);
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

    public void draw()
    {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE)
        {
            titleText.setVisible(true);
            titleStartButton.setVisible(true);
            playerAmountText.setVisible(false);
            playerAmount.setVisible(false);
            startGameButton.setVisible(false);
        }
        else if (GameStates.currentGameState == GameStates.CREATE_GAME_STATE)
        {
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(true);
            playerAmount.setVisible(true);
            startGameButton.setVisible(true);
        }
        else if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(false);
            playerAmount.setVisible(false);
            startGameButton.setVisible(false);
            rollButton.setVisible(true);
            nextTurnButton.setVisible(true);
            nextTurnButton.setForeground(new Color(255, 255, 255, 75));
        }
        else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE)
        {
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(false);
            rollButton.setForeground(new Color(255, 255, 255, 75));
            nextTurnButton.setForeground(new Color(255, 255, 255, 75));
        }
        else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE)
        {
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
            playerAmountText.setVisible(false);
            rollButton.setForeground(new Color(255, 255, 255, 75));
            nextTurnButton.setForeground(new Color(255, 255, 255, 75));
        }
        else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE)
        {
            titleText.setVisible(false);
            titleStartButton.setVisible(false);
        }
    }
}
