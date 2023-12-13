package src.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import src.data.Fonts;
import src.data.GameStates;
import src.data.SpaceData;
import src.spaces.NormalProperty;
import src.spaces.RailroadProperty;
import src.spaces.UtilityProperty;

/**
 * Represents the GUI for the player manager.
 */
public class PlayerManager {
    public GamePanel gamePanel;
    public Fonts fonts;
    public SpaceData spaceData;
    public SaveLoad saveLoad;
    public Dice dice;
    public ArrayList<Profile> profiles;
    public ArrayList<Player> players;

    public Image mortgageButtonImage;
    public Image tradingButtonImage;
    public Image buildingButtonImage;

    public Image tradingRightArrowImage;
    public Image tradingLeftArrowImage;

    public Player playerTradingWith;

    public ArrayList<NormalProperty> currentPlayerNormalProperties;
    public ArrayList<RailroadProperty> currentPlayerRailroads;
    public ArrayList<UtilityProperty> currentPlayerUtilities;

    public ArrayList<NormalProperty> playerTradingWithNormalProperties;
    public ArrayList<RailroadProperty> playerTradingWithRailroads;
    public ArrayList<UtilityProperty> playerTradingWithUtilities;

    public ArrayList<NormalProperty> currentPlayerSelectedNormalProperties;
    public ArrayList<RailroadProperty> currentPlayerSelectedRailroads;
    public ArrayList<UtilityProperty> currentPlayerSelectedUtilities;

    public ArrayList<NormalProperty> playerTradingWithSelectedNormalProperties;
    public ArrayList<RailroadProperty> playerTradingWithSelectedRailroads;
    public ArrayList<UtilityProperty> playerTradingWithSelectedUtilities; 

    public int currentPlayerPropertyAmount;
    public int playerTradingWithPropertyAmount;

    /**
    * Constructor.
    */
    public PlayerManager(GamePanel gamePanel, Fonts fonts, SpaceData spaceData, SaveLoad saveLoad, 
                         Dice dice, ArrayList<Profile> profiles, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        this.spaceData = spaceData;
        this.saveLoad = saveLoad;
        this.dice = dice;
        this.profiles = profiles;
        this.players = players;

        mortgageButtonImage = new ImageIcon("images/Player-Manager-Mortgage-Image.png").getImage();
        tradingButtonImage = new ImageIcon("images/Player-Manager-Trading-Image.png").getImage();
        buildingButtonImage = new ImageIcon("images/Player-Manager-Building-Image.png").getImage();

        tradingRightArrowImage = new ImageIcon("images/Trading-Right-Arrow.png").getImage();
        tradingLeftArrowImage = new ImageIcon("images/Trading-Left-Arrow.png").getImage();

        currentPlayerNormalProperties = new ArrayList<NormalProperty>();
        currentPlayerRailroads = new ArrayList<RailroadProperty>();
        currentPlayerUtilities = new ArrayList<UtilityProperty>();

        playerTradingWithNormalProperties = new ArrayList<NormalProperty>();
        playerTradingWithRailroads = new ArrayList<RailroadProperty>();
        playerTradingWithUtilities = new ArrayList<UtilityProperty>();

        currentPlayerSelectedNormalProperties = new ArrayList<NormalProperty>();
        currentPlayerSelectedRailroads = new ArrayList<RailroadProperty>();
        currentPlayerSelectedUtilities = new ArrayList<UtilityProperty>();

        playerTradingWithSelectedNormalProperties = new ArrayList<NormalProperty>();
        playerTradingWithSelectedRailroads = new ArrayList<RailroadProperty>();
        playerTradingWithSelectedUtilities = new ArrayList<UtilityProperty>();

        currentPlayerPropertyAmount = 0;
        playerTradingWithPropertyAmount = 0;
    }

    /**
    * Checks that at least one property has been selected from either the current player or the
    * player being traded with before a trade can be offered.
    */
    public void checkIfCanOfferTrade() {

    }

    /**
    * Empties the selected property ArrayLists for both the current player and the player being 
    * traded with.
    */
    public void clearSelectedProperties() {
        // currentPlayerSelectedNormalProperties.clear();
        // currentPlayerSelectedRailroads.clear();
        // currentPlayerSelectedUtilities.clear();

        // playerTradingWithSelectedNormalProperties.clear();
        // playerTradingWithSelectedRailroads.clear();
        // playerTradingWithSelectedUtilities.clear();
    }

    /**
    * Adds a property of the current player to a list of properties that will be traded.
    */
    // public void addPropertyToCurrentPlayerTradingList(int selectedPropertyBoxLabel) {
    //     int tradingIndex = 0;
    //     for (int i = 0; i < currentPlayerPropertyAmount; i++) {
    //         if (i < currentPlayerNormalProperties.size()) {
    //             if (selectedPropertyBoxLabel == tradingIndex) {
    //                 currentPlayerSelectedNormalProperties.add(currentPlayerNormalProperties.get(i));
    //             }
    //         } else if (i < currentPlayerNormalProperties.size() + currentPlayerRailroads.size()) {
    //             if (selectedPropertyBoxLabel == tradingIndex) {
    //                 currentPlayerSelectedRailroads.add(currentPlayerRailroads.get(i));
    //             }
    //         } else if (i < currentPlayerNormalProperties.size () + currentPlayerRailroads.size()
    //                    + currentPlayerUtilities.size()) {
    //             if (selectedPropertyBoxLabel == tradingIndex) {
    //                 currentPlayerSelectedUtilities.add(currentPlayerUtilities.get(i));
    //             }
    //         }

    //         tradingIndex++;
    //     } 
    // }

    /**
    * Adds a property of the player being traded with to a list of properties that will be traded.
    */
    // public void addPropertyToPlayerTradingWithTradingList(int selectedPropertyBoxLabel) {
    //     int tradingIndex = 0;
    //     for (int i = 0; i < playerTradingWithPropertyAmount; i++) {
    //         if (i < playerTradingWithNormalProperties.size()) {
    //             if (selectedPropertyBoxLabel == tradingIndex) {
    //                 playerTradingWithSelectedNormalProperties.add(playerTradingWithNormalProperties.get(i));
    //             }
    //         } else if (i < playerTradingWithNormalProperties.size() + playerTradingWithRailroads.size()) {
    //             if (selectedPropertyBoxLabel == tradingIndex) {
    //                 playerTradingWithSelectedRailroads.add(playerTradingWithRailroads.get(i));
    //             }
    //         } else if (i < playerTradingWithNormalProperties.size () + playerTradingWithRailroads.size()
    //                    + playerTradingWithUtilities.size()) {
    //             if (selectedPropertyBoxLabel == tradingIndex) {
    //                 playerTradingWithSelectedUtilities.add(playerTradingWithUtilities.get(i));
    //             }
    //         }

    //         tradingIndex++;
    //     } 
    // }

    /**
    * Retreives all of the properties owned by the current player and stores them in 
    * appropriate ArrayLists.
    */
    public void getCurrentPlayerProperties() {

        currentPlayerNormalProperties.clear();
        currentPlayerRailroads.clear();
        currentPlayerUtilities.clear();

        for (int i = 0; i < spaceData.normalProperties.size(); i++) {
            if (spaceData.normalProperties.get(i).owner == gamePanel.currentPlayer) {
                currentPlayerNormalProperties.add(spaceData.normalProperties.get(i));
            }
        }

        for (int i = 0; i < spaceData.railroadProperties.size(); i++) {
            if (spaceData.railroadProperties.get(i).owner == gamePanel.currentPlayer) {
                currentPlayerRailroads.add(spaceData.railroadProperties.get(i));
            }
        }

        for (int i = 0; i < spaceData.utilityProperties.size(); i++) {
            if (spaceData.utilityProperties.get(i).owner == gamePanel.currentPlayer) {
                currentPlayerUtilities.add(spaceData.utilityProperties.get(i));
            }
        }

        currentPlayerPropertyAmount = currentPlayerNormalProperties.size() 
            + currentPlayerRailroads.size() 
            + currentPlayerUtilities.size();

        playerTradingWithPropertyAmount = playerTradingWithNormalProperties.size()
            + playerTradingWithRailroads.size()
            + playerTradingWithUtilities.size();
    }

    /**
    * Retreives all of the properties owned by the player being traded with and stores them in
    * appropriate ArrayLists.
    */
    public void getPlayerTradingWithProperties() {
        playerTradingWithNormalProperties.clear();
        playerTradingWithRailroads.clear();
        playerTradingWithUtilities.clear();

        for (int i = 0; i < spaceData.normalProperties.size(); i++) {
            if (spaceData.normalProperties.get(i).owner == playerTradingWith) {
                playerTradingWithNormalProperties.add(spaceData.normalProperties.get(i));
            }
        }

        for (int i = 0; i < spaceData.railroadProperties.size(); i++) {
            if (spaceData.railroadProperties.get(i).owner == playerTradingWith) {
                playerTradingWithRailroads.add(spaceData.railroadProperties.get(i));
            }
        }

        for (int i = 0; i < spaceData.utilityProperties.size(); i++) {
            if (spaceData.utilityProperties.get(i).owner == playerTradingWith) {
                playerTradingWithUtilities.add(spaceData.utilityProperties.get(i));
            }
        }

        playerTradingWithPropertyAmount = playerTradingWithNormalProperties.size()
            + playerTradingWithRailroads.size()
            + playerTradingWithUtilities.size();
    }

    /**
    * Draws the player manager on screen.
    */
    public void draw(Graphics2D g2d) {

        // Player money box.
        HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 680, 20, 100, 30, 2);
        HelperFunctions.drawText(g2d, "$ " + gamePanel.currentPlayer.money, fonts.pixeloidSans, 
                                 Color.black, 18, 680, 20, 100, 30, true, true);

        if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Player Manager", fonts.pixeloidSans, Color.white, 
                                     48, 300, 25, 200, 50, true, true);

            // Current player token.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 20, 80, 80, 2);
            g2d.drawImage(gamePanel.currentPlayer.token, 30, 30, 60, 60, null);

            // Mortgaging/unmortgaging button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 125, 600, 100, 5);
            g2d.drawImage(mortgageButtonImage, 610, 135, null);

            // Trading button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 275, 600, 100, 5);
            g2d.drawImage(tradingButtonImage, 610, 285, null);

            // Building button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 425, 600, 100, 5);
            g2d.drawImage(buildingButtonImage, 610, 435, null);

            // Declare bankruptcy button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 575, 600, 100, 5);

            // Back button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
        } else if (GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Mortgage / Unmortgage Properties", fonts.pixeloidSans, Color.white, 
                                     24, 100, 25, 600, 50, true, true);
            
            if (currentPlayerPropertyAmount > 0) {
                // Mortgage button box.
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 200, 670, 120, 30, 2);
                
                // Unmortgage button box.
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 480, 670, 120, 30, 2);
            }

            // Back button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
        } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Trading", fonts.pixeloidSans, Color.white, 
                                     48, 300, 25, 200, 50, true, true);

            // Prompt.
            HelperFunctions.drawText(g2d, "Select the player you would like to trade with.", 
                                     fonts.pixeloidSans, Color.white, 18, 200, 100, 
                                     400, 50, true, true);

            // Trade button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 350, 200, 100, 30, 2);

            // Back button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
        } else if (GameStates.currentGameState == GameStates.TRADING_CREATE_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Trading", fonts.pixeloidSans, Color.white, 
                                     48, 300, 25, 200, 50, true, true);
            
            // Current player token.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 100, 80, 80, 2);
            g2d.drawImage(gamePanel.currentPlayer.token, 110, 110, 60, 60, null);

            // Current player name.
            HelperFunctions.drawText(g2d, gamePanel.currentPlayer.name, fonts.pixeloidSans, 
                                     Color.white, 26, 200, 125, 100, 25, true, true);

            // Player trading with token.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 500, 100, 80, 80, 2);
            g2d.drawImage(playerTradingWith.token, 510, 110, 60, 60, null);

            // Player trading with name.
            HelperFunctions.drawText(g2d, playerTradingWith.name, fonts.pixeloidSans, 
                                     Color.white, 26, 600, 125, 100, 25, true, true);

            // Back button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);

            // Offer button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 680, 750, 100, 30, 2);
        } else if (GameStates.currentGameState == GameStates.TRADING_OFFER_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Trading", fonts.pixeloidSans, Color.white, 
                                     48, 300, 25, 200, 50, true, true);
            
            // Current player token.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 100, 100, 80, 80, 2);
            g2d.drawImage(gamePanel.currentPlayer.token, 110, 110, 60, 60, null);

            // Current player name.
            HelperFunctions.drawText(g2d, gamePanel.currentPlayer.name, fonts.pixeloidSans, 
                                     Color.white, 26, 200, 125, 100, 25, true, true);

            // Player trading with token.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 500, 100, 80, 80, 2);
            g2d.drawImage(playerTradingWith.token, 510, 110, 60, 60, null);

            // Player trading with name.
            HelperFunctions.drawText(g2d, playerTradingWith.name, fonts.pixeloidSans, 
                                     Color.white, 26, 600, 125, 100, 25, true, true);
            
            // Trading prompt.
            HelperFunctions.drawText(g2d, playerTradingWith.name + ", do you accept the trade?", 
                                     fonts.pixeloidSans, Color.white, 18, 200, 620, 
                                     400, 50, true, true);
                
            // Decline button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 200, 670, 100, 30, 2);
            
            // Accept button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 500, 670, 100, 30, 2);
        } else if (GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
            // Screen title.
            HelperFunctions.drawText(g2d, "Declare Bankruptcy", fonts.pixeloidSans, Color.white, 
                                     48, 200, 25, 400, 50, true, true);

            // Prompt box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 200, 300, 400, 200, 5);

            // Are you sure message.
            HelperFunctions.drawText(g2d, "Are you sure you want to delcare bankruptcy?", fonts.pixeloidSans, Color.white, 14, 200, 350, 400, 50, true, true);
            
            // No button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 220, 450, 100, 30, 2);

            // Yes button box.
            HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 480, 450, 100, 30, 2);

        }
    }
}
