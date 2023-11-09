package game;

import data.GameStates;
import data.SpaceData;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Performs any action required when a player lands on a space, including displaying the space name,
 * and displaying options for the space.
 */
public class SpaceEvent {
    public GamePanel gamePanel;
    public UI ui;
    public ArrayList<Player> players;
    public SpaceData spaceData;

    /**
    * Constructor.
    */
    public SpaceEvent(GamePanel gamePanel, UI ui, ArrayList<Player> players, SpaceData spaceData) {
        this.gamePanel = gamePanel;
        this.ui = ui;
        this.players = players;
        this.spaceData = spaceData;
    }

    /**
    * Chooses what space event to display based on the space number the current player has 
      landed on.
    */
    public void chooseEvent() {
        SpaceData.getSpace(players.get(gamePanel.currentPlayerNumber - 1).currentSpaceNumber);
        // System.out.println(SpaceData.currentSpaceType);

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

    /**
    * Draws the space event text on screen.
    */
    public void draw(Graphics2D g2d) {
        if (SpaceData.currentSpaceType == "Go") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                drawSpaceName(g2d, SpaceData.go.name, 350, 375, 100, 50);
                HelperFunctions.drawText(g2d, "You collected $200 salary!", Color.white, 18, 
                                         350, 425, 100, 50, true, true);
            }
            // ui.rollButton.setForeground(Color.white);
        } else if (SpaceData.currentSpaceType == "Jail") {
            drawSpaceName(g2d, SpaceData.jail.name, 350, 375, 100, 50);
            // ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;           
        } else if (SpaceData.currentSpaceType == "Free Parking") {
            drawSpaceName(g2d, SpaceData.freeParking.name, 350, 375, 100, 50);
            // ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;            
        } else if (SpaceData.currentSpaceType == "Go To Jail") {
            drawSpaceName(g2d, SpaceData.goToJail.name, 350, 375, 100, 50);
            // ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;            
        } else if (SpaceData.currentSpaceType == "Normal Property") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (SpaceData.currentNormalProperty.owner == null) {
                    drawSpaceName(g2d, SpaceData.currentNormalProperty.name, 350, 375, 100, 50);
                    ui.buyOptionText.setVisible(true);
                    ui.passOptionText.setVisible(true);

                    if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                                              < SpaceData.currentNormalProperty.price) {
                        ui.buyOptionText.setForeground(new Color(255, 255, 255, 75));
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
                } else {
                    drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    // ui.rollButton.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
            } else {
                if (SpaceData.currentNormalProperty.owner != null) {
                    drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    // ui.rollButton.setForeground(Color.white);
                }
            }
        } else if (SpaceData.currentSpaceType == "Railroad") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (SpaceData.currentRailroad.owner == null) {
                    drawSpaceName(g2d, SpaceData.currentRailroad.name, 350, 375, 100, 50);
                    ui.buyOptionText.setVisible(true);
                    ui.passOptionText.setVisible(true);
                    
                    if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                                              < SpaceData.currentRailroad.price) {
                        ui.buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    }
                } else {
                    drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    // ui.rollButton.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
            } else {
                if (SpaceData.currentRailroad.owner != null) {
                    drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    // ui.rollButton.setForeground(Color.white);
                }
            }
        } else if (SpaceData.currentSpaceType == "Utility") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (SpaceData.currentUtility.owner == null) {
                    drawSpaceName(g2d, SpaceData.currentUtility.name, 350, 375, 100, 50);
                    ui.buyOptionText.setVisible(true);
                    ui.passOptionText.setVisible(true);

                    if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                                              < SpaceData.currentUtility.price) {
                        ui.buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    }
                } else {
                    drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    // ui.rollButton.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
            } else {
                if (SpaceData.currentUtility.owner != null) {
                    drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    // ui.rollButton.setForeground(Color.white);
                }
            }
        } else if (SpaceData.currentSpaceType == "Card") {
            if (SpaceData.currentCardSpace.name == "Chance") {
                drawSpaceName(g2d, SpaceData.currentCardSpace.name, 350, 375, 100, 50);
            } else if (SpaceData.currentCardSpace.name == "Community Chest") {
                drawSpaceName(g2d, SpaceData.currentCardSpace.name, 350, 375, 100, 50);
            }

            // ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        } else if (SpaceData.currentSpaceType == "Tax") {
            if (SpaceData.currentTaxSpace.name == "Income Tax") {
                drawSpaceName(g2d, SpaceData.currentTaxSpace.name, 350, 375, 100, 50);
            } else if (SpaceData.currentTaxSpace.name == "Luxury Tax") {
                drawSpaceName(g2d, SpaceData.currentTaxSpace.name, 350, 375, 100, 50);
            }

            // ui.rollButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        }
        // else if (SpaceData.currentSpaceType == "Corner Space")
        // {
        //     drawSpaceName(g2d, "Corner", 350, 375, 100, 50);
        //     ui.rollButton.setForeground(Color.white);
        //     GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        // }
    }

    /**
    * Draws a space name on screen.
    */
    public void drawSpaceName(Graphics2D g2d, String spaceName, int rectX, int rectY, 
                              int rectWidth, int rectHeight) {
        FontMetrics fontMetrics = g2d.getFontMetrics(new Font("Times New Roman", Font.PLAIN, 26));

        int x = rectX + (rectWidth - fontMetrics.stringWidth(spaceName)) / 2;
        int y = rectY + ((rectHeight - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

        // System.out.println(y);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        g2d.drawString(spaceName, x, y);
    }

    /**
    * Draws an option for the current space on screen.
    */
    public void drawOption(Graphics2D g2d, String optionName, int rectX, int rectY, 
                           int rectWidth, int rectHeight) {
        FontMetrics fontMetrics = g2d.getFontMetrics(new Font("Times New Roman", Font.PLAIN, 18));

        int x = rectX + (rectWidth - fontMetrics.stringWidth(optionName)) / 2;
        int y = rectY + ((rectHeight - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

        System.out.println(y);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // g2d.setColor(new Color(153, 235, 255));
        g2d.drawString(optionName, x, y);
    }

    /**
    * Creates an option for a space.
    */
    public void createOption(JLabel optionLabel, String optionName, int labelX, int labelY, 
                             int labelWidth, int labelHeight) {
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
