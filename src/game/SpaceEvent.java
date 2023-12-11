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
    public Dice dice;
    public ArrayList<Player> players;
    public SpaceData spaceData;

    /**
    * Constructor.
    */
    public SpaceEvent(GamePanel gamePanel, Fonts fonts, Ui ui, Dice dice, 
                      ArrayList<Player> players, SpaceData spaceData) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        this.ui = ui;
        this.dice = dice;
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

    /**
    * Displays the contents of an unowned normal property on screen.
    */
    public void displayNormalProperty(Graphics2D g2d) {
        HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 250, 250, 300, 325, 2);
        g2d.setColor(HelperFunctions.getColor(spaceData.currentNormalProperty));
        g2d.fillRect(270, 270, 260, 40);
        HelperFunctions.drawText(g2d, spaceData.currentNormalProperty.name, 
                                 fonts.pixeloidSans, Color.black, 22, 350, 315, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "Price: $" + spaceData.currentNormalProperty.price, 
                                 fonts.pixeloidSans, Color.black, 18, 350, 350, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "Rent: $" + spaceData.currentNormalProperty.normalRent, 
                                 fonts.pixeloidSans, Color.black, 18, 270, 375, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent with 1 House:   $" + spaceData.currentNormalProperty.buildingRent[0], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 400, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent with 2 Houses: $" + spaceData.currentNormalProperty.buildingRent[1], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 425, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent with 3 Houses: $" + spaceData.currentNormalProperty.buildingRent[2], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 450, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent with 4 Houses: $" + spaceData.currentNormalProperty.buildingRent[3], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 475, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent with Hotel:        $" + spaceData.currentNormalProperty.buildingRent[4], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 500, 
                                 100, 50, false, true);
    }

    /**
    * Displays the contents of an unowned sport property on screen.
    */
    public void displayRailroad(Graphics2D g2d) {
        HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 250, 250, 300, 325, 2);
        HelperFunctions.drawText(g2d, spaceData.currentRailroad.name, 
                                 fonts.pixeloidSans, Color.black, 26, 350, 315, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "Price: $" + spaceData.currentRailroad.price, 
                                 fonts.pixeloidSans, Color.black, 18, 350, 350, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "Rent: $" + spaceData.currentRailroad.groupRent[0], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 375, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent if 2 owned: $" + (spaceData.currentRailroad.groupRent[1]), 
                                 fonts.pixeloidSans, Color.black, 18, 270, 400, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent if 3 owned: $" + (spaceData.currentRailroad.groupRent[2]), 
                                 fonts.pixeloidSans, Color.black, 18, 270, 425, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent if 4 owned: $" + spaceData.currentRailroad.groupRent[3], 
                                 fonts.pixeloidSans, Color.black, 18, 270, 450, 
                                 100, 50, false, true);              
    }

    /**
    * Displays the contents of an unowned utility property on screen.
    */
    public void displayUtility(Graphics2D g2d) {
        HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 250, 250, 300, 325, 2);
        HelperFunctions.drawText(g2d, spaceData.currentUtility.name, 
                                 fonts.pixeloidSans, Color.black, 26, 350, 315, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "Price: $" + spaceData.currentUtility.price, 
                                 fonts.pixeloidSans, Color.black, 18, 350, 350, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "Rent if 1 owned: " + "Dice * " + (spaceData.currentUtility.rentMultiplier[0]), 
                                 fonts.pixeloidSans, Color.black, 18, 270, 375, 
                                 100, 50, false, true);
        HelperFunctions.drawText(g2d, "Rent if 2 owned: " + "Dice * " + (spaceData.currentUtility.rentMultiplier[1]), 
                                 fonts.pixeloidSans, Color.black, 18, 270, 400, 
                                 100, 50, false, true);
    }

    /**
    * Displays a message for a normal property letting the player know that they owe rent to 
    * another player.
    */
    public void normalPropertyPayRent(Graphics2D g2d) {
        HelperFunctions.drawText(g2d, 
                                 "You owe $" + spaceData.currentNormalProperty.currentRent 
                                 + " to " + spaceData.currentNormalProperty.owner.name + ".",
                                 fonts.pixeloidSans, Color.black, 18, 350, 375, 
                                 100, 50, true, true);
    }

    /**
    * Displays a message for a railroad property letting the player know that they owe rent to 
    * another player.
    */
    public void railroadPayRent(Graphics2D g2d) {
        HelperFunctions.drawText(g2d, 
                                 "You owe $" + spaceData.currentRailroad.currentRent 
                                 + " to " + spaceData.currentRailroad.owner.name + ".",
                                 fonts.pixeloidSans, Color.black, 18, 350, 375, 
                                 100, 50, true, true);
    }

    /**
    * Displays a message for a utility property letting the player know that they owe rent to 
    * another player.
    */
    public void utilityPayRent(Graphics2D g2d) {
        System.out.print("Current dice result: " + dice.result);
        HelperFunctions.drawText(g2d, 
                                 "You owe $" + spaceData.currentUtility.currentMultiplier * dice.result
                                 + " to " + spaceData.currentUtility.owner.name + ".",
                                 fonts.pixeloidSans, Color.black, 18, 350, 375, 
                                 100, 50, true, true);
    }

    /**
    * Displays a message for any property letting the player know that they own it.
    */
    public void ownedProperty(Graphics2D g2d) {
        HelperFunctions.drawText(g2d, "You own this property.", fonts.pixeloidSans, Color.black, 
                                18, 350, 375, 100, 50, true, true);
    }

    /**
    * Displays a message when a player lands on a card space.
    */
    public void cardSpace(Graphics2D g2d) {
        HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 250, 325, 300, 175, 2);
        HelperFunctions.drawText(g2d, spaceData.currentCardSpace.name, 
                                 fonts.pixeloidSans, Color.black, 22, 350, 345, 
                                 100, 25, true, true);
    }

    /**
    * Displays a message when a player lands on a tax space.
    */
    public void taxSpace(Graphics2D g2d) {
        HelperFunctions.drawText(g2d, spaceData.currentTaxSpace.name, 
                                 fonts.pixeloidSans, Color.black, 26, 350, 375, 
                                 100, 50, true, true);
        HelperFunctions.drawText(g2d, "You owe $" + spaceData.currentTaxSpace.fee + " in tax.", 
                                 fonts.pixeloidSans, Color.black, 18, 350, 400, 
                                 100, 50, true, true);
    }

    /**
    * Draws the space event text on screen.
    */
    public void draw(Graphics2D g2d) {
        if (players.get(gamePanel.currentPlayerNumber - 1).doublesStreak >= 3) {
            HelperFunctions.drawText(g2d, "You rolled 3 doubles in a row.", fonts.pixeloidSans, 
                                     Color.white, 18, 350, 400, 100, 50, true, true);
            HelperFunctions.drawText(g2d, "You must head directly to jail!", fonts.pixeloidSans, 
                                     Color.white, 18, 350, 425, 100, 50, true, true);
        } else if (spaceData.currentSpaceType == "Go") {
            HelperFunctions.drawText(g2d, spaceData.go.name, fonts.pixeloidSans, Color.black, 
                                     26, 350, 375, 100, 50, true, true);
            HelperFunctions.drawText(g2d, "You collected $200 salary!", fonts.pixeloidSans, 
                                     Color.white, 18, 350, 425, 100, 50, true, true);
        } else if (spaceData.currentSpaceType == "Jail") {
            if (players.get(gamePanel.currentPlayerNumber - 1).isInJail == true) {
                if (dice.result == 0) {
                    HelperFunctions.drawText(g2d, "Jail", fonts.pixeloidSans, Color.black, 
                                        26, 350, 375, 100, 50, true, true);
                    HelperFunctions.drawText(g2d, "Choose an option.", fonts.pixeloidSans, 
                                             Color.black, 18, 350, 425, 100, 50, true, true);
                } else {
                    if (dice.doubles == true) {
                        HelperFunctions.drawText(g2d, "You rolled doubles! You may now leave jail.", 
                                                 fonts.pixeloidSans, Color.black, 18, 350, 425, 
                                                 100, 50, true, true);
                    } else if (players.get(gamePanel.currentPlayerNumber - 1).turnsInJail >= 3) {
                        HelperFunctions.drawText(g2d, "You did not roll doubles.", 
                                                 fonts.pixeloidSans, Color.black, 18, 350, 400, 
                                                 100, 50, true, true);
                        HelperFunctions.drawText(g2d, "You must pay bail and leave jail now.", 
                                                 fonts.pixeloidSans, Color.black, 18, 350, 425, 
                                                 100, 50, true, true);
                    } else {
                        HelperFunctions.drawText(g2d, "You did not roll doubles.", 
                                                 fonts.pixeloidSans, Color.black, 18, 350, 400, 
                                                 100, 50, true, true);
                        HelperFunctions.drawText(g2d, "You must stay in jail for another turn.", 
                                                 fonts.pixeloidSans, Color.black, 18, 350, 425, 
                                                 100, 50, true, true);
                    }
                }
            } else {
                HelperFunctions.drawText(g2d, spaceData.jail.name, fonts.pixeloidSans, Color.black, 
                                     26, 350, 375, 100, 50, true, true);
            }           
        } else if (spaceData.currentSpaceType == "Free Parking") {
            HelperFunctions.drawText(g2d, spaceData.freeParking.name, fonts.pixeloidSans, 
                                     Color.black, 26, 350, 375, 100, 50, true, true);          
        } else if (spaceData.currentSpaceType == "Go To Jail") {
            HelperFunctions.drawText(g2d, spaceData.goToJail.name, fonts.pixeloidSans, Color.black, 
                                     26, 350, 375, 100, 50, true, true);
            HelperFunctions.drawText(g2d, "You must head directly to jail!", fonts.pixeloidSans, 
                                     Color.black, 18, 350, 425, 100, 50, true, true);           
        } else if (spaceData.currentSpaceType == "Normal Property") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (spaceData.currentNormalProperty.owner == null) {
                    displayNormalProperty(g2d);
                } else if (spaceData.currentNormalProperty.owner != gamePanel.currentPlayer) {
                    normalPropertyPayRent(g2d);
                } else {
                    ownedProperty(g2d);
                }
            }
        } else if (spaceData.currentSpaceType == "Railroad") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (spaceData.currentRailroad.owner == null) {
                    displayRailroad(g2d);
                } else if (spaceData.currentRailroad.owner != gamePanel.currentPlayer) {
                    railroadPayRent(g2d);
                } else {
                    ownedProperty(g2d);
                }
            }
        } else if (spaceData.currentSpaceType == "Utility") {
            if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (spaceData.currentUtility.owner == null) {
                    displayUtility(g2d);
                } else if (spaceData.currentUtility.owner != gamePanel.currentPlayer) {
                    utilityPayRent(g2d);
                } else {
                    ownedProperty(g2d);
                }
            }
        } else if (spaceData.currentSpaceType == "Card") {
            cardSpace(g2d);
        } else if (spaceData.currentSpaceType == "Tax") {
            taxSpace(g2d);
        }
    }

    /**
    * Draws a space name on screen.
    */
    public void drawSpaceName(Graphics2D g2d, String spaceName, int rectX, int rectY, 
                              int rectWidth, int rectHeight) {
        FontMetrics fontMetrics = g2d.getFontMetrics(new Font("Times New Roman", Font.PLAIN, 26));

        int x = rectX + (rectWidth - fontMetrics.stringWidth(spaceName)) / 2;
        int y = rectY + ((rectHeight - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

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
