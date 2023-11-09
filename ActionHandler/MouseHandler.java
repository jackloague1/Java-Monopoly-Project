package actionhandler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;


import data.GameStates;
import data.SpaceData;
import game.Dice;
import game.GamePanel;
import game.Player;
import game.UI;

/**
 * Processes user input from the mouse.
 */
public class MouseHandler implements MouseListener
{
    public GamePanel gamePanel;
    public UI ui;
    public Dice dice;
    public ArrayList<Player> players;

    JLabel currentLabel;

    public MouseHandler(GamePanel gamePanel, UI ui, Dice dice, ArrayList<Player> players)
    {
        this.gamePanel = gamePanel;
        this.ui = ui;
        this.dice = dice;
        this.players = players;
    }

    // @Override
    // public void actionPerformed(ActionEvent e) 
    // {
        // System.out.println("Action listener test");
        // String choice = e.getActionCommand();

        // switch (choice)
        // {
        //     case "Roll Button Clicked": 
        //     {
        //         if (gamePanel.gameState == 0)
        //         {
        //             gamePanel.gameState = 1;
                    
        //             dice.getDiceResult();
        //             gamePanel.startTimer();
        //         }
        //     }
        // }
    // }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        System.out.println("Action listener test");
        currentLabel = (JLabel)e.getSource();
        // String choice = e.getSource();

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE)
        {
            if (currentLabel == ui.titleStartButton)
            {
                GameStates.currentGameState = GameStates.CREATE_GAME_STATE;
                // gamePanel.update();
                // gamePanel.repaint();
            }
        }
        else if (GameStates.currentGameState == GameStates.CREATE_GAME_STATE)
        {
            if (currentLabel == ui.startGameButton)
            {
                if (ui.playerAmount.getText().isEmpty() == false && Integer.parseInt(ui.playerAmount.getText()) > 1)
                {
                    gamePanel.createPlayers();
                    gamePanel.currentPlayerNumber = 1;
                    ui.currentButton = ui.boardButtons[0];
                    GameStates.currentGameState = GameStates.ROLL_STATE;
                    // gamePanel.update();
                    // gamePanel.repaint();
                }
            }
        }
        else if (GameStates.currentGameState == GameStates.ROLL_STATE)
        {
            if (currentLabel == ui.rollButtonText)
            {
                GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                    
                dice.getDiceResult();
                // gamePanel.startTimer();
            }
        }
        else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE)
        {
            if (currentLabel == ui.buyOptionText)
            {
                if (SpaceData.currentSpaceType == "Normal Property")
                {
                    if (players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentNormalProperty.price)
                    {
                        SpaceData.currentNormalProperty.owner = players.get(gamePanel.currentPlayerNumber-1);
                        players.get(gamePanel.currentPlayerNumber-1).money = players.get(gamePanel.currentPlayerNumber-1).money - SpaceData.currentNormalProperty.price;
                        System.out.println("Property bought");

                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                        ui.nextTurnButtonText.setForeground(Color.white);
                        // gamePanel.rollButton.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        // gamePanel.repaint();
                    }                    
                }
                else if (SpaceData.currentSpaceType == "Railroad")
                {
                    if (players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentRailroad.price)
                    {
                        SpaceData.currentRailroad.owner = players.get(gamePanel.currentPlayerNumber-1);
                        players.get(gamePanel.currentPlayerNumber-1).money = players.get(gamePanel.currentPlayerNumber-1).money - SpaceData.currentRailroad.price;
                        System.out.println("Property bought");

                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                        ui.nextTurnButtonText.setForeground(Color.white);
                        // gamePanel.rollButton.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        // gamePanel.repaint();
                    }
                }
                else if (SpaceData.currentSpaceType == "Utility")
                {
                    if (players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentUtility.price)
                    {
                        SpaceData.currentUtility.owner = players.get(gamePanel.currentPlayerNumber-1);
                        players.get(gamePanel.currentPlayerNumber-1).money = players.get(gamePanel.currentPlayerNumber-1).money - SpaceData.currentUtility.price;
                        System.out.println("Property bought");

                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                        ui.nextTurnButtonText.setForeground(Color.white);
                        // gamePanel.rollButton.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        // gamePanel.repaint();
                    }
                }
            }
            else if (currentLabel == ui.passOptionText)
            {
                System.out.println("Auction");
                
                GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                ui.nextTurnButtonText.setForeground(Color.white);
                // gamePanel.rollButton.setForeground(Color.white);
                ui.buyOptionText.setVisible(false);
                ui.passOptionText.setVisible(false);
                // gamePanel.repaint();
            }
        }
        else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE)
        {
            if (currentLabel == ui.nextTurnButtonText)
            {
                gamePanel.changePlayerNumber();
                GameStates.currentGameState = GameStates.ROLL_STATE;
                ui.rollButtonText.setForeground(Color.white);
                // gamePanel.update();
                // gamePanel.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        currentLabel = (JLabel)e.getSource();

        if (currentLabel == ui.titleStartButton)
        {
            currentLabel.setForeground(new Color(153, 235, 255));
        }
        else if (currentLabel == ui.startGameButton)
        {
            currentLabel.setForeground(new Color(153, 235, 255));
        }
        else if (currentLabel == ui.rollButtonText)
        {
            if (GameStates.currentGameState == GameStates.ROLL_STATE)
            {
                currentLabel.setForeground(new Color(153, 235, 255));
            }
        }
        else if (currentLabel == ui.nextTurnButtonText)
        {
            if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE)
            {
                currentLabel.setForeground(new Color(153, 235, 255));
            }
        }
        else if (currentLabel == ui.buyOptionText)
        {
            if (SpaceData.currentSpaceType == "Normal Property")
            {
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentNormalProperty.price)
                {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            }
            else if (SpaceData.currentSpaceType == "Railroad")
            {
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentRailroad.price)
                {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            }
            else if (SpaceData.currentSpaceType == "Utility")
            {
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentUtility.price)
                {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            }
        }
        else if (currentLabel == ui.passOptionText)
        {
            currentLabel.setForeground(new Color(153, 235, 255));
        }
        // currentLabel.setForeground(new Color(153, 235, 255));
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        currentLabel = (JLabel)e.getSource();

        if (currentLabel == ui.titleStartButton)
        {
            currentLabel.setForeground(Color.white);
        }
        else if (currentLabel == ui.startGameButton)
        {
            currentLabel.setForeground(Color.white);
        }
        else if (currentLabel == ui.rollButtonText)
        {
            if (GameStates.currentGameState == GameStates.ROLL_STATE)
            {
                currentLabel.setForeground(Color.white);
            }
        }
        else if (currentLabel == ui.nextTurnButtonText)
        {
            if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE)
            {
                currentLabel.setForeground(Color.white);
            }    
        }
        else if (currentLabel == ui.buyOptionText)
        {
            if (SpaceData.currentSpaceType == "Normal Property")
            {
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentNormalProperty.price)
                {
                    currentLabel.setForeground(Color.white);
                }
            }
            else if (SpaceData.currentSpaceType == "Railroad")
            {
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentRailroad.price)
                {
                    currentLabel.setForeground(Color.white);
                }
            }
            else if (SpaceData.currentSpaceType == "Utility")
            {
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money >= SpaceData.currentUtility.price)
                {
                    currentLabel.setForeground(Color.white);
                }
            }
        }
        else if (currentLabel == ui.passOptionText)
        {
            currentLabel.setForeground(Color.white);
        }
    }   
}
