package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Data.GameStates;
import Data.SpaceData;

public class SpaceEvent 
{
    public GamePanel gamePanel;
    public UI ui;
    public ArrayList<Player> players;
    public SpaceData spaceData;

    public SpaceEvent(GamePanel gamePanel, UI ui, ArrayList<Player> players, SpaceData spaceData)
    {
        this.gamePanel = gamePanel;
        this.ui = ui;
        this.players = players;
        this.spaceData = spaceData;
    }

    public void chooseEvent()
    {
        SpaceData.getSpace(players.get(gamePanel.currentPlayerNumber-1).currentSpaceNumber);
        System.out.println(SpaceData.currentSpaceType);

        // if (SpaceData.currentSpaceType == "Normal Property")
        // {
        //      propertySpace();
        // }
    }

    public void propertySpace()
    {
        if (SpaceData.currentNormalProperty.owner == null)
        {

        }
    }

    public void draw(Graphics2D g2d)
    {
        if (SpaceData.currentSpaceType == "Normal Property")
        {
            if (SpaceData.currentNormalProperty.owner != players.get(gamePanel.currentPlayerNumber-1))
            {
                drawSpaceName(g2d, SpaceData.currentNormalProperty.name, 350, 375, 100, 50);
                ui.buyOption.setVisible(true);
                ui.auctionOption.setVisible(true);

                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money < SpaceData.currentNormalProperty.price)
                {
                    ui.buyOption.setForeground(new Color(255, 255, 255, 75));
                }
                // gamePanel.buyOption = new JLabel();
                // createOption(gamePanel.buyOption, "Buy", 350, 425, 100, 50);

                // gamePanel.auctionOption = new JLabel();
                // createOption(gamePanel.auctionOption, "Auction", 350, 450, 100, 50);
                // JLabel buyOption = new JLabel();
                // buyOption.setBounds(350, 425, 100, 50);
                // buyOption.setText("Buy");
                // buyOption.setHorizontalAlignment(SwingConstants.CENTER);
                // buyOption.setVerticalAlignment(SwingConstants.CENTER);
                // buyOption.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                // buyOption.setForeground(Color.white);
                // gamePanel.add(buyOption);
                // drawOption(g2d, "Buy", 350, 425, 100, 50);
                // drawOption(g2d, "Auction", 350, 450, 100, 50);
                // g2d.drawPolygon(new int[] {300, 300, 330}, new int[] {425, 455, 440}, 3);
            }
            else
            {
                drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                ui.rollButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
            }
        }
        else if (SpaceData.currentSpaceType == "Railroad")
        {
            if (SpaceData.currentRailroad.owner != players.get(gamePanel.currentPlayerNumber-1))
            {
                drawSpaceName(g2d, SpaceData.currentRailroad.name, 350, 375, 100, 50);
                ui.buyOption.setVisible(true);
                ui.auctionOption.setVisible(true);
                
                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money < SpaceData.currentRailroad.price)
                {
                    ui.buyOption.setForeground(new Color(255, 255, 255, 75));
                }
            }
            else
            {
                drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                ui.rollButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
            }
        }
        else if (SpaceData.currentSpaceType == "Utility")
        {
            if (SpaceData.currentUtility.owner != players.get(gamePanel.currentPlayerNumber-1))
            {
                drawSpaceName(g2d, SpaceData.currentUtility.name, 350, 375, 100, 50);
                ui.buyOption.setVisible(true);
                ui.auctionOption.setVisible(true);

                if (gamePanel.players.get(gamePanel.currentPlayerNumber-1).money < SpaceData.currentUtility.price)
                {
                    ui.buyOption.setForeground(new Color(255, 255, 255, 75));
                }
            }
            else
            {
                drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                ui.rollButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
            }
        }
        else if (SpaceData.currentSpaceType == "Card")
        {
            if (SpaceData.currentCardSpace.name == "Chance")
            {
                drawSpaceName(g2d, "Chance", 350, 375, 100, 50);
            }
            else if (SpaceData.currentCardSpace.name == "Community Chest")
            {
                drawSpaceName(g2d, "Community Chest", 350, 375, 100, 50);
            }

            ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        }
        else if (SpaceData.currentSpaceType == "Tax")
        {
            if (SpaceData.currentTaxSpace.name == "Income Tax")
            {
                drawSpaceName(g2d, "Income Tax", 350, 375, 100, 50);
            }
            else if (SpaceData.currentTaxSpace.name == "Luxury Tax")
            {
                drawSpaceName(g2d, "Luxury Tax", 350, 375, 100, 50);
            }

            ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        }
        else if (SpaceData.currentSpaceType == "Corner Space")
        {
            drawSpaceName(g2d, "Corner", 350, 375, 100, 50);
            ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        }
    }

    public void drawSpaceName(Graphics2D g2d, String spaceName, int rectX, int rectY, int rectWidth, int rectHeight)
    {
        FontMetrics fontMetrics = g2d.getFontMetrics(new Font("Times New Roman", Font.PLAIN, 26));

        int x = rectX + (rectWidth - fontMetrics.stringWidth(spaceName)) / 2;
        int y = rectY + ((rectHeight - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

        // System.out.println(y);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        g2d.drawString(spaceName, x, y);
    }

    public void drawOption(Graphics2D g2d, String optionName, int rectX, int rectY, int rectWidth, int rectHeight)
    {
        FontMetrics fontMetrics = g2d.getFontMetrics(new Font("Times New Roman", Font.PLAIN, 18));

        int x = rectX + (rectWidth - fontMetrics.stringWidth(optionName)) / 2;
        int y = rectY + ((rectHeight - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

        System.out.println(y);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // g2d.setColor(new Color(153, 235, 255));
        g2d.drawString(optionName, x, y);
    }

    public void createOption(JLabel optionLabel, String optionName, int labelX, int labelY, int labelWidth, int labelHeight)
    {
        optionLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        optionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        optionLabel.setForeground(Color.white);
        optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        optionLabel.setVerticalAlignment(SwingConstants.CENTER);
        optionLabel.setText(optionName);
        optionLabel.addMouseListener(ui.mouseHandler);
        gamePanel.add(optionLabel);
    }
}
