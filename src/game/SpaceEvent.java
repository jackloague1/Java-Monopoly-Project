package src.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import src.data.Fonts;
import src.data.GameStates;
import src.data.SpaceData;

/**
 * Performs any action required when a player lands on a space, including displaying the space name,
 * and displaying options for the space.
 */
public class SpaceEvent {
    public GamePanel gamePanel;
    public Fonts fonts;
    public Ui ui;
    public ArrayList<Player> players;
    public SpaceData spaceData;

    /**
    * Constructor.
    */
    public SpaceEvent(GamePanel gamePanel, Fonts fonts, Ui ui, ArrayList<Player> players, 
                      SpaceData spaceData) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        this.ui = ui;
        this.players = players;
        this.spaceData = spaceData;
    }

    /**
    * Chooses what space event to display based on the space number the current player has 
      landed on.
    */
    public void chooseEvent() {
        spaceData.getSpace(players.get(gamePanel.currentPlayerNumber - 1).currentSpaceNumber);
        // System.out.println(SpaceData.currentSpaceType);

        // if (SpaceData.currentSpaceType == "Normal Property")
        // {
        //      propertySpace();
        // }
    }

    public void propertySpace()
    {
        if (spaceData.currentNormalProperty.owner == null)
        {

        }
    }

    /**
    * Draws the space event text on screen.
    */
    public void draw(Graphics2D g2d) {
        if (spaceData.currentSpaceType == "Go") {
            // drawSpaceName(g2d, spaceData.go.name, 350, 375, 100, 50);
            HelperFunctions.drawText(g2d, spaceData.go.name, fonts.pixeloidSans, Color.black, 
                                     26, 350, 375, 100, 50, true, true);
            HelperFunctions.drawText(g2d, "You collected $200 salary!", fonts.pixeloidSans, 
                                     Color.white, 18, 350, 425, 100, 50, true, true);
            // ui.rollButton.setForeground(Color.white);
        } else if (spaceData.currentSpaceType == "Jail") {
            // drawSpaceName(g2d, spaceData.jail.name, 350, 375, 100, 50);
            HelperFunctions.drawText(g2d, spaceData.jail.name, fonts.pixeloidSans, Color.black, 
                                     26, 350, 375, 100, 50, true, true);
            // ui.rollButton.setForeground(Color.white);
            // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;           
        } else if (spaceData.currentSpaceType == "Free Parking") {
            // drawSpaceName(g2d, spaceData.freeParking.name, 350, 375, 100, 50);
            HelperFunctions.drawText(g2d, spaceData.freeParking.name, fonts.pixeloidSans, 
                                     Color.black, 26, 350, 375, 100, 50, true, true);
            // ui.rollButton.setForeground(Color.white);
            // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;            
        } else if (spaceData.currentSpaceType == "Go To Jail") {
            // drawSpaceName(g2d, spaceData.goToJail.name, 350, 375, 100, 50);
            HelperFunctions.drawText(g2d, spaceData.goToJail.name, fonts.pixeloidSans, Color.black, 
                                     26, 350, 375, 100, 50, true, true);
            // ui.rollButton.setForeground(Color.white);
            // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;            
        } else if (spaceData.currentSpaceType == "Normal Property") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (spaceData.currentNormalProperty.owner == null) {
                    // drawSpaceName(g2d, spaceData.currentNormalProperty.name, 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, spaceData.currentNormalProperty.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.buyOptionText.setVisible(true);
                    // ui.passOptionText.setVisible(true);

                    // if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                    //                           < spaceData.currentNormalProperty.price) {
                    //     ui.buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    // }
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
                    // drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, "Property Owned",
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.rollButton.setForeground(Color.white);
                    // ui.managerButton.setForeground(Color.white);
                    // ui.nextTurnButton.setForeground(Color.white);
                    // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
            } else {
                if (spaceData.currentNormalProperty.owner != null) {
                    // drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, "Property Owned", 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.rollButton.setForeground(Color.white);
                }
            }
        } else if (spaceData.currentSpaceType == "Railroad") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (spaceData.currentRailroad.owner == null) {
                    // drawSpaceName(g2d, spaceData.currentRailroad.name, 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, spaceData.currentRailroad.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.buyOptionText.setVisible(true);
                    // ui.passOptionText.setVisible(true);
                    
                    // if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                    //                           < spaceData.currentRailroad.price) {
                    //     ui.buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    // }
                } else {
                    // drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, "Property Owned", 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.rollButton.setForeground(Color.white);
                    // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
            } else {
                if (spaceData.currentRailroad.owner != null) {
                    // drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, "Property Owned", 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.rollButton.setForeground(Color.white);
                }
            }
        } else if (spaceData.currentSpaceType == "Utility") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (spaceData.currentUtility.owner == null) {
                    // drawSpaceName(g2d, spaceData.currentUtility.name, 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, spaceData.currentUtility.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.buyOptionText.setVisible(true);
                    // ui.passOptionText.setVisible(true);

                    // if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                    //                           < spaceData.currentUtility.price) {
                    //     ui.buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    // }
                } else {
                    // drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, "Property Owned", 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.rollButton.setForeground(Color.white);
                    // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
            } else {
                if (spaceData.currentUtility.owner != null) {
                    // drawSpaceName(g2d, "Property Owned", 350, 375, 100, 50);
                    HelperFunctions.drawText(g2d, "Property Owned", 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
                    // ui.rollButton.setForeground(Color.white);
                }
            }
        } else if (spaceData.currentSpaceType == "Card") {
            if (spaceData.currentCardSpace.name == "Chance") {
                // drawSpaceName(g2d, spaceData.currentCardSpace.name, 350, 375, 100, 50);
                HelperFunctions.drawText(g2d, spaceData.currentCardSpace.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
            } else if (spaceData.currentCardSpace.name == "Community Chest") {
                // drawSpaceName(g2d, spaceData.currentCardSpace.name, 350, 375, 100, 50);
                HelperFunctions.drawText(g2d, spaceData.currentCardSpace.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
            }

            // ui.rollButton.setForeground(Color.white);
            // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
        } else if (spaceData.currentSpaceType == "Tax") {
            if (spaceData.currentTaxSpace.name == "Income Tax") {
                // drawSpaceName(g2d, spaceData.currentTaxSpace.name, 350, 375, 100, 50);
                HelperFunctions.drawText(g2d, spaceData.currentTaxSpace.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
            } else if (spaceData.currentTaxSpace.name == "Luxury Tax") {
                // drawSpaceName(g2d, spaceData.currentTaxSpace.name, 350, 375, 100, 50);
                HelperFunctions.drawText(g2d, spaceData.currentTaxSpace.name, 
                                             fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                             100, 50, true, true);
            }

            // ui.rollButton.setForeground(Color.white);
            // GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
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
