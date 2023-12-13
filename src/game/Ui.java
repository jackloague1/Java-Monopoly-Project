package src.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import src.actionhandler.KeyHandler;
import src.actionhandler.MouseHandler;
import src.data.Fonts;
import src.data.GameStates;
import src.data.Settings;
import src.data.SpaceData;

/**
 * Represents any element a user or player can interact with, such as buttons.
 */
public class Ui {
    public GamePanel gamePanel;
    public Fonts fonts;
    public SpaceData spaceData;
    public SaveLoad saveLoad;
    public Dice dice;
    public PlayerManager playerManager;
    public ArrayList<Profile> profiles;
    public ArrayList<Player> players;

    public final int [] playerBoxesX;
    public final int [] playerBoxesY;
    public final int playerBoxWidth;
    public final int playerBoxHeight;

    public final int [] removePlayerButtonsX;
    public final int [] removePlayerButtonsY;
    public final int removePlayersButtonsWidth;
    public final int removePlayerButtonsHeight;

    public final String [] playerTokenNames;

    public JLabel startButton;
    public JLabel profilesButton;
    public JLabel optionsButton;

    public JLabel menuBackButton;

    public JComboBox<String> setUpScreenProfileList;
    public Image [] playerTokenImages;
    public JLabel playerTokenLeftArrowLabel;
    public JLabel playerTokenRightArrowLabel;
    public JLabel [] playerBoxLabels;
    public JLabel addPlayerBoxLabel;
    public JLabel [] removePlayerBoxLabels;
    public JLabel setUpScreenCreateProfileButton;
    public JLabel startGameButton;

    public JLabel profilesScreenCancelButton;
    public JLabel profilesScreenEnterButton;
    public JLabel profilesScreenCreateButton;
    public JLabel profilesScreenMaxBackButton;
    public JTextField profileTextField;

    public JLabel boardLabel;
    public Image buttonImage;
    public Image rollButtonImage;
    public JLabel rollButton;
    public Image playerManagerButtonImage;
    public JLabel managerButton;
    public Image nextTurnButtonImage;
    public JLabel nextTurnButton;
    public JLabel okButton;
    public JLabel buyOptionText;
    public JLabel passOptionText;
    public JLabel payButton;
    public JLabel jailRollButton;
    public JLabel jailPayBailButton;
    public JLabel jailUseCardButton;
    public JLabel managerMortgagingButton;
    public JLabel managerTradingButton;
    public JLabel managerBuildingButton;
    public JLabel managerBankruptcyButton;
    public JLabel managerBackButton;
    public JLabel mortgageButton;
    public JLabel unMortgageButton;
    public JComboBox<String> playerTradingList;
    public JLabel startTradingButton;
    public PropertyBox[] mortgagingProperties;
    public PropertyBox[] tradingCurrentPlayerProperties;
    public PropertyBox[] tradingPlayerTradingWithProperties;
    public JLabel offerTradeButton;
    public JLabel declineTradeButton;
    public JLabel acceptTradeButton;
    public JLabel bankruptcyNoButton;
    public JLabel bankruptcyYesButton;
    public JLabel backtoMainMenuButton;
    // public 
    // public JLabel mortgageButton;
    // public JLabel[] tradingMenuCurrentPlayerPropertyBoxLabels;
    // public JLabel[] tradingMenuPlayerTradingWithPropertyBoxLabels;
    // public JScrollPane tradingCurrentPlayerProperties;
    // public JLabel tradingCurrentPlayerAdd;
    // public JScrollPane tradingPlayerTradingWithProperties;
    // public JLabel tradingPlayerTradingWithAdd;
    // public JScrollPane tradingCurrentPlayerTradingProperties;
    // public JLabel tradingCurrentPlayerRemove;
    // public JScrollPane tradingPlayerTradingWithTradingProperties;
    // public JLabel tradingPlayerTradingWithRemove;

    public int playerAmount;
    public int profileNameSeperator;
    public String [] selectedProfiles;
    public String [] selectedProfilesTemp;
    public ArrayList<Image> availableTokens;
    public Image [] selectedTokens;
    public int availableTokensIndex;
    public int selectedPlayerBox;
    public int hoveredPlayerBox;
    public boolean hoveringAddPlayerBox;
    public int hoveredRemovePlayerButton;
    public int mortgagingSelectedProperty;
    public int mortgagingHoveredProperty;
    public int tradingMenuHoveredCurrentPlayerProperty;
    public int tradingMenuHoveredPlayerTradingWithProperty;
    public ArrayList<Integer> tradingMenuSelectedCurrentPlayerProperties;
    public ArrayList<Integer> tradingMenuSelectedPlayerTradingWithProperties;

    public final int [] mortgagingPropertyBoxesX;
    public final int [] mortgagingPropertyBoxesY;
    public final int mortgagingPropertyWidth;
    public final int mortgagingPropertyHeight;
    public final int [] tradingMenuPropertyBoxesX;
    public final int [] tradingMenuPropertyBoxesY;
    public final int tradingMenuPropertyWidth;
    public final int tradingMenuPropertyHeight;

    public boolean lastScreenIsSetUp = false;

    public MouseHandler mouseHandler;
    public KeyHandler keyHandler;

    /**
    * Constructor.
    */
    public Ui(GamePanel gamePanel, Fonts fonts, SpaceData spaceData, SaveLoad saveLoad, Dice dice, 
              PlayerManager playerManager, ArrayList<Profile> profiles, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        this.spaceData = spaceData;
        this.saveLoad = saveLoad;
        this.dice = dice;
        this.playerManager = playerManager;
        this.profiles = profiles;
        this.players = players;

        playerBoxesX = new int [] {125, 325, 525, 125, 325, 525};
        playerBoxesY = new int [] {225, 225, 225, 425, 425, 425};
        playerBoxWidth = 150;
        playerBoxHeight = 150;

        removePlayerButtonsX = new int [] {635, 235, 435, 635};
        removePlayerButtonsY = new int [] {235, 435, 435, 435};
        removePlayersButtonsWidth = 30;
        removePlayerButtonsHeight = 30;

        playerTokenNames = new String [] {"Purple", "Blue", "Red", "Green"};

        mouseHandler = new MouseHandler(gamePanel, spaceData, this, dice, playerManager, 
                                        profiles, players);
        keyHandler = new KeyHandler(gamePanel, spaceData, this, dice, playerManager, 
                                    profiles, players);

        createTitleScreenUi();
        createSetUpScreenUi();
        createProfileScreenUi();

        playerAmount = 2;

        selectedProfiles = new String [6];

        selectedProfilesTemp = new String [6];

        if (profiles.size() >= 2) {
            selectedProfiles[0] = profiles.get(0).name;
            selectedProfiles[1] = profiles.get(1).name;
        } else if (profiles.size() == 1) {
            selectedProfiles[0] = profiles.get(0).name;
            selectedProfiles[1] = "None";
        } else {
            selectedProfiles[0] = "None";
            selectedProfiles[1] = "None";
        }

        availableTokens = new ArrayList<Image>();
        selectedTokens = new Image [6];

        selectedTokens[0] = playerTokenImages[0];
        selectedTokens[1] = playerTokenImages[1];

        selectedPlayerBox = 0;
        hoveredPlayerBox = -1;
        hoveringAddPlayerBox = false;
        hoveredRemovePlayerButton = -1;
        mortgagingHoveredProperty = -1;
        tradingMenuHoveredCurrentPlayerProperty = -1;
        tradingMenuHoveredPlayerTradingWithProperty = -1;

        tradingMenuSelectedCurrentPlayerProperties = new ArrayList<Integer>();
        tradingMenuSelectedPlayerTradingWithProperties = new ArrayList<Integer>();

        mortgagingPropertyBoxesX = new int [] { 175, 250, 325, 400, 475, 550, 625,
                                                175, 250, 325, 400, 475, 550, 625,
                                                175, 250, 325, 400, 475, 550, 625,
                                                175, 250, 325, 400, 475, 550, 625};
        mortgagingPropertyBoxesY = new int [] { 200, 200, 200, 200, 200, 200, 200, 
                                                275, 275, 275, 275, 275, 275, 275,
                                                350, 350, 350, 350, 350, 350, 350,
                                                425, 425, 425, 425, 425, 425, 425};
        mortgagingPropertyWidth = 50;
        mortgagingPropertyHeight = 50;

        tradingMenuPropertyBoxesX = new int [] { 25, 100, 175, 250, 
                                                25, 100, 175, 250,
                                                25, 100, 175, 250,
                                                25, 100, 175, 250,
                                                25, 100, 175, 250,
                                                25, 100, 175, 250,
                                                25, 100, 175, 250 };
        tradingMenuPropertyBoxesY = new int [] { 200, 200, 200, 200,
                                                275, 275, 275, 275,
                                                350, 350, 350, 350,
                                                425, 425, 425, 425,
                                                500, 500, 500, 500,
                                                575, 575, 575, 575,
                                                650, 650, 650, 650 };
        tradingMenuPropertyWidth = 50;
        tradingMenuPropertyHeight = 50;

        createMainGameUi();
        createManagerUi();
    }

    /**
    * Creates the interactable components for the title screen.
    */
    public void createTitleScreenUi() {
        startButton = new JLabel();
        createButtonLabel(startButton, "Start", fonts.pixeloidSans, Color.white, 32,
                                                300, 400, 200, 50);
        profilesButton = new JLabel();
        createButtonLabel(profilesButton, "Profiles", fonts.pixeloidSans, Color.white, 32,
                                                300, 450, 200, 50);
        optionsButton = new JLabel();
        createButtonLabel(optionsButton, "Options", fonts.pixeloidSans, Color.white, 32,
                                                300, 500, 200, 50);
        menuBackButton = new JLabel();
        createButtonLabel(menuBackButton, "Back", fonts.pixeloidSans, Color.white, 16,
                                            20, 750, 100, 30);
    }

    /**
    * Creates the interactable components for the set up game screen.
    */
    public void createSetUpScreenUi() {
        setUpScreenProfileList = new JComboBox();
        setUpScreenProfileList.setBounds(210, 150, 100, 20);
        gamePanel.add(setUpScreenProfileList);
        setUpScreenProfileList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (setUpScreenProfileList.getSelectedItem() != null) {
                    selectedProfiles[selectedPlayerBox] 
                        = setUpScreenProfileList.getSelectedItem().toString();
                    for (int i = 0; i < selectedProfiles.length; i++) {
                        System.out.print(selectedProfiles[i]);
                        System.out.print("\n");
                    }
                }

                if (setUpReady() == true) {
                    startGameButton.setForeground(Color.white);
                } else {
                    startGameButton.setForeground(new Color(255, 255, 255, 75));
                }
            }
        });

        playerTokenImages = new Image[7];
        for (int i = 0; i < playerTokenImages.length; i++) {
            if (i < 6) {
                playerTokenImages[i] = new ImageIcon(String.format("images/tuffy-%s%s", i, ".png")).getImage();
            } else {
                playerTokenImages[i] = new ImageIcon("images/blank-token.png").getImage();
            }
        }

        playerTokenLeftArrowLabel = new JLabel();
        playerTokenLeftArrowLabel.setBounds(480, 137, 30, 30);
        gamePanel.add(playerTokenLeftArrowLabel);
        playerTokenLeftArrowLabel.addMouseListener(mouseHandler);
        playerTokenRightArrowLabel = new JLabel();
        playerTokenRightArrowLabel.setBounds(630, 137, 30, 30);
        gamePanel.add(playerTokenRightArrowLabel);
        playerTokenRightArrowLabel.addMouseListener(mouseHandler);

        addPlayerBoxLabel = new JLabel();
        gamePanel.add(addPlayerBoxLabel);
        addPlayerBoxLabel.addMouseListener(mouseHandler);

        removePlayerBoxLabels = new JLabel [4];

        for (int i = 0; i < removePlayerBoxLabels.length; i++) {
            removePlayerBoxLabels[i] = new JLabel();
            removePlayerBoxLabels[i].setBounds(removePlayerButtonsX[i], removePlayerButtonsY[i], 
                                         removePlayersButtonsWidth, removePlayerButtonsHeight);
            gamePanel.add(removePlayerBoxLabels[i]);
            removePlayerBoxLabels[i].addMouseListener(mouseHandler);
        }

        playerBoxLabels = new JLabel [6];

        for (int i = 0; i < playerBoxLabels.length; i++) {
            playerBoxLabels[i] = new JLabel();
            playerBoxLabels[i].setBounds(playerBoxesX[i], 
                                                   playerBoxesY[i], 
                                                   playerBoxWidth, 
                                                   playerBoxHeight);
            gamePanel.add(playerBoxLabels[i]);
            playerBoxLabels[i].addMouseListener(mouseHandler);
        }

        setUpScreenCreateProfileButton = new JLabel();
        createButtonLabel(setUpScreenCreateProfileButton, "Create Profile", fonts.pixeloidSans, 
                          Color.white, 16, 325, 750, 150, 30);
        startGameButton = new JLabel();
        createButtonLabel(startGameButton, "Start", fonts.pixeloidSans, Color.white, 26, 
                          680, 750, 100, 30);
    }

    /**
    * Creates the interactable components for the profiles screen.
    */
    public void createProfileScreenUi() {
        profilesScreenCancelButton = new JLabel();
        createButtonLabel(profilesScreenCancelButton, "Cancel", fonts.pixeloidSans, Color.white, 16,
                          250, 520, 100, 30);
        profilesScreenEnterButton = new JLabel();
        createButtonLabel(profilesScreenEnterButton, "Enter", fonts.pixeloidSans, Color.white, 16, 
                          450, 520, 100, 30);
        profilesScreenCreateButton = new JLabel();
        createButtonLabel(profilesScreenCreateButton, "Create", fonts.pixeloidSans, Color.white, 
                          16, 680, 750, 100, 30);
        profilesScreenMaxBackButton = new JLabel();
        createButtonLabel(profilesScreenMaxBackButton, "Back", fonts.pixeloidSans, Color.white, 16, 
                          350, 520, 100, 30);

        buttonImage = new ImageIcon("images/Button-Background.png").getImage();

        profileNameSeperator = 20;

        profileTextField = new JTextField(null, null, 0);
        profileTextField.setBounds(460, 390, 100, 20);
        profileTextField.setDocument(new LimitTextField(15));
        profileTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    try {
                        gamePanel.createProfile(profileTextField.getText(), profileNameSeperator);
                        profileTextField.setText(null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    profileTextField.setText(null);
                    if (profiles.isEmpty() == true) {
                        GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
                    } else {
                        GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
                    }
                }
            }
        });
        gamePanel.add(profileTextField);
        profileTextField.setVisible(false);
    }

    /**
    * Retrieves all the available profile names that have not been selected yet for displaying on
    * the profile name drop down list for each respective player on the create game screen.
    */
    public void getAvailableProfiles(JComboBox<String> comboBox, String[] selectedProfiles) {
        boolean addProfile = true;

        comboBox.removeAllItems();
        for (int i = 0; i < profiles.size(); i++) {
            for (int j = 0; j < selectedProfiles.length; j++) {
                if (profiles.get(i).name.equals(selectedProfiles[selectedPlayerBox]) == false) {
                    if (profiles.get(i).name.equals(selectedProfiles[j])) {
                        addProfile = false;
                        break;
                    }
                }
            }

            if (addProfile == true) {
                comboBox.addItem(profiles.get(i).name);
            }

            addProfile = true;
        }

        comboBox.addItem("None");

        if (selectedProfilesTemp[selectedPlayerBox] != null) {
            setUpScreenProfileList.setSelectedItem(selectedProfilesTemp[selectedPlayerBox]);
        }
    }

    /**
    * Retrieves all the available tokens that have not been selected yet for each respective player
    * to choose from.
    */
    public void getAvailableTokens() {
        boolean addToken = true;

        availableTokens.clear();
        for (int i = 0; i < playerTokenImages.length; i++) {
            for (int j = 0; j < selectedTokens.length; j++) {
                if (playerTokenImages[i] != selectedTokens[selectedPlayerBox]) {
                    if (playerTokenImages[i] == selectedTokens[j] 
                        && i != playerTokenImages.length - 1) {
                        addToken = false;
                        break;
                    }
                }
            }

            if (addToken == true) {
                availableTokens.add(playerTokenImages[i]);
            }

            addToken = true;
        }

        if (selectedTokens[selectedPlayerBox] == null) {
            selectedTokens[selectedPlayerBox] = availableTokens.get(0);
            availableTokensIndex = 0;
        } else {
            for (int i = 0; i < availableTokens.size(); i++) {
                if (selectedTokens[selectedPlayerBox] == availableTokens.get(i)) {
                    availableTokensIndex = i;
                }
            }
        }
    }

    /**
    * Checks to ensure all users have selected a profile name and token before the game can start.
    */
    public boolean setUpReady() {
        for (int i = 0; i < selectedProfiles.length; i++) {
            if (selectedProfiles[i] == "None") {
                return false;
            }
            
            if (selectedTokens[i] == playerTokenImages[playerTokenImages.length - 1]) {
                return false;
            }
        }

        return true;
    }

    /**
    * Determines if a property in the mortgaging menu is mortgaged or not.
    */
    public boolean isPropertyMortgaged(PropertyBox selectedProperty) {
        if (mortgagingSelectedProperty != -1) {
            if (selectedProperty.propertyType == "Normal Property") {
                if (selectedProperty.normalProperty.isMortgaged == false) {
                    return false;
                } else {
                    return true;
                }
            } else if (selectedProperty.propertyType == "Railroad") {
                if (selectedProperty.railroadProperty.isMortgaged == false) {
                    return false;
                } else {
                    return true;
                }
              } else if (selectedProperty.propertyType == "Utility") {
                if (selectedProperty.utilityProperty.isMortgaged == false) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    /**
    * Determines if a player has enough money to unmortgage a property.
    */
    public boolean canPlayerUnmortgage(PropertyBox selectedProperty) {
        if (mortgagingSelectedProperty != -1) {
            if (selectedProperty.propertyType == "Normal Property") {
                if (gamePanel.currentPlayer.money >= selectedProperty.normalProperty.unMortgageFee) {
                    return true;
                } else {
                    return false;
                }
            } else if (selectedProperty.propertyType == "Railroad") {
                if (gamePanel.currentPlayer.money >= selectedProperty.railroadProperty.unMortgageFee) {
                    return true;
                } else {
                    return false;
                }
              } else if (selectedProperty.propertyType == "Utility") {
                if (gamePanel.currentPlayer.money >= selectedProperty.utilityProperty.unMortgageFee) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public void unMortgageProperty(PropertyBox property) {
        if (property.propertyType == "Normal Property") {
            property.normalProperty.isMortgaged = false;
            gamePanel.currentPlayer.money -= property.normalProperty.unMortgageFee;
        } else if (property.propertyType == "Railroad") {
            property.railroadProperty.isMortgaged = false;
            gamePanel.currentPlayer.money -= property.railroadProperty.unMortgageFee;
        } else if (property.propertyType == "Utility") {
            property.utilityProperty.isMortgaged = false;
            gamePanel.currentPlayer.money -= property.utilityProperty.unMortgageFee;
        }
    }

    public void mortgageProperty(PropertyBox property) {
        if (property.propertyType == "Normal Property") {
            property.normalProperty.isMortgaged = true;
            gamePanel.currentPlayer.money += property.normalProperty.mortgageValue;
        } else if (property.propertyType == "Railroad") {
            property.railroadProperty.isMortgaged = true;
            gamePanel.currentPlayer.money += property.railroadProperty.mortgageValue;
        } else if (property.propertyType == "Utility") {
            property.utilityProperty.isMortgaged = true;
            gamePanel.currentPlayer.money += property.utilityProperty.mortgageValue;
        }
    }

    /**
    * Checks to ensure at least one property from either the current player or the player being
    * traded with is selected before a trade can be offered.
    */
    public boolean canOfferTrade() {
        for (int i = 0; i < tradingCurrentPlayerProperties.length; i++) {
            if (tradingCurrentPlayerProperties[i].selected == true
                || tradingPlayerTradingWithProperties[i].selected == true) {
                return true;
            }
        }
        return false;
    }

    /**
    * Trades properties between players based on the properties selected in the trading menu.
    */
    public void trade() {
        for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
            if (tradingCurrentPlayerProperties[i].selected == true) {
                if (tradingCurrentPlayerProperties[i].propertyType == "Normal Property") {
                    for (int j = 0; i < spaceData.normalProperties.size(); j++) {
                        if (spaceData.normalProperties.get(j) 
                            == tradingCurrentPlayerProperties[i].normalProperty) {
                            spaceData.normalProperties.get(j).owner 
                                = playerManager.playerTradingWith;
                            break;
                        }
                    }
                } else if (tradingCurrentPlayerProperties[i].propertyType == "Railroad") {
                    for (int j = 0; i < spaceData.railroadProperties.size(); j++) {
                        if (spaceData.railroadProperties.get(j) 
                            == tradingCurrentPlayerProperties[i].railroadProperty) {
                            spaceData.railroadProperties.get(j).owner 
                                = playerManager.playerTradingWith;
                            break;
                        }
                    }
                } else if (tradingCurrentPlayerProperties[i].propertyType == "Utility") {
                    for (int j = 0; i < spaceData.utilityProperties.size(); j++) {
                        if (spaceData.utilityProperties.get(j) 
                            == tradingCurrentPlayerProperties[i].utilityProperty) {
                            spaceData.utilityProperties.get(j).owner 
                                = playerManager.playerTradingWith;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < playerManager.playerTradingWithPropertyAmount; i++) {
            if (tradingPlayerTradingWithProperties[i].selected == true) {
                if (tradingPlayerTradingWithProperties[i].propertyType == "Normal Property") {
                    for (int j = 0; i < spaceData.normalProperties.size(); j++) {
                        if (spaceData.normalProperties.get(j) 
                            == tradingPlayerTradingWithProperties[i].normalProperty) {
                            spaceData.normalProperties.get(j).owner 
                                = gamePanel.currentPlayer;
                            break;
                        }
                    }
                } else if (tradingPlayerTradingWithProperties[i].propertyType == "Railroad") {
                    for (int j = 0; i < spaceData.railroadProperties.size(); j++) {
                        if (spaceData.railroadProperties.get(j) 
                            == tradingPlayerTradingWithProperties[i].railroadProperty) {
                            spaceData.railroadProperties.get(j).owner 
                                = gamePanel.currentPlayer;
                            break;
                        }
                    }
                } else if (tradingPlayerTradingWithProperties[i].propertyType == "Utility") {
                    for (int j = 0; i < spaceData.utilityProperties.size(); j++) {
                        if (spaceData.utilityProperties.get(j) 
                            == tradingPlayerTradingWithProperties[i].utilityProperty) {
                            spaceData.utilityProperties.get(j).owner 
                                = gamePanel.currentPlayer;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
    * Creates the interactable components for the main board screen of the game.
    */
    public void createMainGameUi() {
        rollButtonImage = new ImageIcon("images/Roll-Button.png").getImage();
        rollButton = new JLabel();
        createButtonLabel(rollButton, "Roll Dice", fonts.pixeloidSans, Color.white, 16,
                          69, 41, 100, 30);
        rollButton.setVisible(false);

        playerManagerButtonImage = new ImageIcon("images/Button-Background.png").getImage();
        managerButton = new JLabel();
        createButtonLabel(managerButton, "Manager", fonts.pixeloidSans, Color.white, 16,
                          350, 41, 100, 30);
        managerButton.setVisible(false);

        nextTurnButtonImage = new ImageIcon("images/Button-Background.png").getImage();
        nextTurnButton = new JLabel();
        createButtonLabel(nextTurnButton, "Next Turn", fonts.pixeloidSans, Color.white, 16, 
                          621, 41, 100, 30);
        nextTurnButton.setVisible(false);

        okButton = new JLabel();
        createButtonLabel(okButton, "Ok", fonts.pixeloidSans, Color.white, 16, 350, 450, 100, 50);
        okButton.setVisible(false);

        buyOptionText = new JLabel();
        createButtonLabel(buyOptionText, "Buy", fonts.pixeloidSans, Color.white, 16, 
                          300, 525, 100, 50);
        buyOptionText.setVisible(false);

        passOptionText = new JLabel();
        createButtonLabel(passOptionText, "Pass", fonts.pixeloidSans, Color.white, 16, 
                          400, 525, 100, 50);
        passOptionText.setVisible(false);

        payButton = new JLabel();
        createButtonLabel(payButton, "Pay", fonts.pixeloidSans, Color.white, 16, 
                          350, 450, 100, 50);
        payButton.setVisible(false);

        jailRollButton = new JLabel();
        createButtonLabel(jailRollButton, "Roll", fonts.pixeloidSans, Color.white, 16, 
                          350, 450, 100, 50);
        jailRollButton.setVisible(false);

        jailPayBailButton = new JLabel();
        createButtonLabel(jailPayBailButton, "Pay Bail", fonts.pixeloidSans, Color.white, 16, 
                          350, 475, 100, 50);
        jailPayBailButton.setVisible(false);

        jailUseCardButton = new JLabel();
        createButtonLabel(jailUseCardButton, "Use Card", fonts.pixeloidSans, Color.white, 16, 
                          350, 500, 100, 50);
        jailUseCardButton.setVisible(false);
    }

    /**
    * Creates the interactable components for the player manager screen.
    */
    public void createManagerUi() {
        managerMortgagingButton = new JLabel();
        createButtonLabel(managerMortgagingButton, "Mortgage/Unmortgage", 
                          fonts.pixeloidSans, Color.white, 22, 100, 125, 500, 100);
        managerMortgagingButton.setVisible(false);

        managerTradingButton = new JLabel();
        createButtonLabel(managerTradingButton, "Trading", 
                          fonts.pixeloidSans, Color.white, 22, 100, 275, 500, 100);
        managerTradingButton.setVisible(false);

        managerBuildingButton = new JLabel();
        createButtonLabel(managerBuildingButton, "Build/Sell", 
                          fonts.pixeloidSans, Color.white, 22, 100, 425, 500, 100);
        managerBuildingButton.setVisible(false);

        managerBankruptcyButton = new JLabel();
        createButtonLabel(managerBankruptcyButton, "Declare Bankruptcy", 
                          fonts.pixeloidSans, Color.white, 22, 100, 575, 500, 100);
        managerBankruptcyButton.setVisible(false);
 
        managerBackButton = new JLabel();
        createButtonLabel(managerBackButton, "Back", 
                          fonts.pixeloidSans, Color.white, 16, 20, 750, 100, 30);
        managerBackButton.setVisible(false); 

        mortgageButton = new JLabel();
        createButtonLabel(mortgageButton, "Mortgage", 
                          fonts.pixeloidSans, Color.white, 16, 200, 670, 120, 30);
        mortgageButton.setVisible(false);

        unMortgageButton = new JLabel();
        createButtonLabel(unMortgageButton, "Unmortgage", 
                          fonts.pixeloidSans, Color.white, 16, 480, 670, 120, 30);
        unMortgageButton.setVisible(false);
        
        playerTradingList = new JComboBox();
        playerTradingList.setBounds(350, 150, 100, 20);
        gamePanel.add(playerTradingList);
        playerTradingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if (playerTradingList.getSelectedItem() != null) {
                //     selectedProfiles[selectedPlayerBox] 
                //         = setUpScreenProfileList.getSelectedItem().toString();
                //     for (int i = 0; i < selectedProfiles.length; i++) {
                //         System.out.print(selectedProfiles[i]);
                //         System.out.print("\n");
                //     }
                // }

                // if (setUpReady() == true) {
                //     startGameButton.setForeground(Color.white);
                // } else {
                //     startGameButton.setForeground(new Color(255, 255, 255, 75));
                // }
            }
        });
        playerTradingList.setVisible(false);

        startTradingButton = new JLabel();
        createButtonLabel(startTradingButton, "Trade", 
                          fonts.pixeloidSans, Color.white, 16, 350, 200, 100, 30);
        startTradingButton.setVisible(false);

        mortgagingProperties = new PropertyBox[28];

        for (int i = 0; i < mortgagingProperties.length; i++) {
            mortgagingProperties[i] = new PropertyBox();
            mortgagingProperties[i].boxLabel = new JLabel();
            mortgagingProperties[i].boxLabel.setBounds(mortgagingPropertyBoxesX[i], 
                                                       mortgagingPropertyBoxesY[i], 
                                                       mortgagingPropertyWidth, 
                                                       mortgagingPropertyHeight);
            gamePanel.add(mortgagingProperties[i].boxLabel);
            mortgagingProperties[i].boxLabel.addMouseListener(mouseHandler);
            mortgagingProperties[i].boxLabel.setVisible(false);
        }

        tradingCurrentPlayerProperties = new PropertyBox[28];
        tradingPlayerTradingWithProperties = new PropertyBox[28];

        for (int i = 0; i < tradingCurrentPlayerProperties.length; i++) {
            tradingCurrentPlayerProperties[i] = new PropertyBox();
            tradingCurrentPlayerProperties[i].boxLabel = new JLabel();
            tradingCurrentPlayerProperties[i].boxLabel.setBounds(
                                                   tradingMenuPropertyBoxesX[i], 
                                                   tradingMenuPropertyBoxesY[i], 
                                                   tradingMenuPropertyWidth, 
                                                   tradingMenuPropertyHeight);
            gamePanel.add(tradingCurrentPlayerProperties[i].boxLabel);
            tradingCurrentPlayerProperties[i].boxLabel.addMouseListener(mouseHandler);
            tradingCurrentPlayerProperties[i].boxLabel.setVisible(false);
            
            tradingPlayerTradingWithProperties[i] = new PropertyBox();
            tradingPlayerTradingWithProperties[i].boxLabel = new JLabel();
            tradingPlayerTradingWithProperties[i].boxLabel.setBounds(
                                                   tradingMenuPropertyBoxesX[i] + 400, 
                                                   tradingMenuPropertyBoxesY[i], 
                                                   tradingMenuPropertyWidth, 
                                                   tradingMenuPropertyHeight);
            gamePanel.add(tradingPlayerTradingWithProperties[i].boxLabel);
            tradingPlayerTradingWithProperties[i].boxLabel.addMouseListener(mouseHandler);
            tradingCurrentPlayerProperties[i].boxLabel.setVisible(false);
        }

        offerTradeButton = new JLabel();
        createButtonLabel(offerTradeButton, "Offer", 
                          fonts.pixeloidSans, Color.white, 16, 680, 750, 100, 30);
        offerTradeButton.setVisible(false);

        declineTradeButton = new JLabel();
        createButtonLabel(declineTradeButton, "Decline", 
                          fonts.pixeloidSans, Color.white, 16, 200, 670, 100, 30);
        declineTradeButton.setVisible(false);

        acceptTradeButton = new JLabel();
        createButtonLabel(acceptTradeButton, "Accept", 
                          fonts.pixeloidSans, Color.white, 16, 500, 670, 100, 30);
        acceptTradeButton.setVisible(false);

        bankruptcyNoButton = new JLabel();
        createButtonLabel(bankruptcyNoButton, "No", 
                          fonts.pixeloidSans, Color.white, 16, 220, 450, 100, 30);
        bankruptcyNoButton.setVisible(false);

        bankruptcyYesButton = new JLabel();
        createButtonLabel(bankruptcyYesButton, "Yes", 
                          fonts.pixeloidSans, Color.white, 16, 480, 450, 100, 30);
        bankruptcyYesButton.setVisible(false);

        backtoMainMenuButton = new JLabel();
        createButtonLabel(backtoMainMenuButton, "Back to Main Menu", 
                          fonts.pixeloidSans, Color.white, 16, 200, 450, 400, 50);
        backtoMainMenuButton.setVisible(false);

        // tradingCurrentPlayerProperties = new JScrollPane();
        // tradingCurrentPlayerProperties.setBounds(75, 250, 250, 150);
        // gamePanel.add(tradingCurrentPlayerProperties);
        // tradingCurrentPlayerProperties.setVisible(false);

        // tradingCurrentPlayerAdd = new JLabel();
        // createButtonLabel(tradingCurrentPlayerAdd, "Add", 
        //                   fonts.pixeloidSans, Color.white, 16, 75, 425, 100, 30);
        // tradingCurrentPlayerAdd.setVisible(false);

        // tradingPlayerTradingWithProperties = new JScrollPane();
        // tradingPlayerTradingWithProperties.setBounds(475, 250, 250, 150);
        // gamePanel.add(tradingPlayerTradingWithProperties);
        // tradingPlayerTradingWithProperties.setVisible(false);

        // tradingPlayerTradingWithAdd = new JLabel();
        // createButtonLabel(tradingPlayerTradingWithAdd, "Add", 
        //                   fonts.pixeloidSans, Color.white, 16, 475, 425, 100, 30);
        // tradingPlayerTradingWithAdd.setVisible(false);

        // tradingCurrentPlayerTradingProperties = new JScrollPane();
        // tradingCurrentPlayerTradingProperties.setBounds(75, 475, 250, 150);
        // gamePanel.add(tradingCurrentPlayerTradingProperties);
        // tradingCurrentPlayerTradingProperties.setVisible(false);


    }

    /**
    * Clears the values of all property boxes of the current player in the manager menu.
    */
    public void clearCurrentPlayerProperties() {
        for (int i = 0; i < tradingCurrentPlayerProperties.length; i++) {
            tradingCurrentPlayerProperties[i].propertyType = null;
            tradingCurrentPlayerProperties[i].normalProperty = null;
            tradingCurrentPlayerProperties[i].railroadProperty = null;
            tradingCurrentPlayerProperties[i].utilityProperty = null;
            tradingCurrentPlayerProperties[i].selected = false;
        }
    }

    /**
    * Clears the values of all property boxes of the player being traded with in the trading menu.
    */
    public void clearPlayerTradingWithProperties() {
        for (int i = 0; i < tradingPlayerTradingWithProperties.length; i++) {
            tradingPlayerTradingWithProperties[i].propertyType = null;
            tradingPlayerTradingWithProperties[i].normalProperty = null;
            tradingPlayerTradingWithProperties[i].railroadProperty = null;
            tradingPlayerTradingWithProperties[i].utilityProperty = null;
            tradingPlayerTradingWithProperties[i].selected = false;
        }
    }

    /**
    * Creates a button label that can work with the MouseHandler class so buttons can be clicked on
    * by a user.
    */
    public void createButtonLabel(JLabel optionLabel, String optionName, Font font, Color color, 
                                  int fontSize, int labelX, int labelY, 
                                  int labelWidth, int labelHeight) {
        optionLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        font = font.deriveFont(Font.PLAIN, fontSize);
        optionLabel.setFont(font);
        optionLabel.setForeground(color);
        optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        optionLabel.setVerticalAlignment(SwingConstants.CENTER);
        optionLabel.setText(optionName);
        optionLabel.addMouseListener(mouseHandler);
        gamePanel.add(optionLabel);
    }

    /**
    * Draws a blank box on screen.
    */
    public void drawBlankBox(Graphics2D g2d, Color borderColor, Color mainColor,
                           int x, int y, int width, int height, int borderWidth) {
        g2d.setColor(borderColor);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(mainColor);
        g2d.fillRect(x + borderWidth, y + borderWidth, 
                     width - (borderWidth * 2), height - (borderWidth * 2));
    }

    /**
    * Draws the UI on screen.
    */
    public void draw(Graphics2D g2d) {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            menuBackButton.setVisible(false);
            setUpScreenProfileList.setVisible(false);
            playerTokenLeftArrowLabel.setVisible(false);
            playerTokenRightArrowLabel.setVisible(false);
            for (int i = 0; i < playerBoxLabels.length; i++) {
                playerBoxLabels[i].setVisible(false);
            }
            addPlayerBoxLabel.setVisible(false);
            for (int i = 0; i < removePlayerBoxLabels.length; i++) {
                removePlayerBoxLabels[i].setVisible(false);
            }
            setUpScreenCreateProfileButton.setVisible(false);
            startGameButton.setVisible(false);
            profileTextField.setVisible(false);
            profilesScreenCancelButton.setVisible(false);
            profilesScreenEnterButton.setVisible(false);
            profilesScreenCreateButton.setVisible(false);
            profilesScreenMaxBackButton.setVisible(false);
            backtoMainMenuButton.setVisible(false);

            // Screen title.
            HelperFunctions.drawText(g2d, "Monopoly", fonts.pixeloidSans, Color.black, 72, 
                                     200, 200, 400, 100, true, true);

            // Start button.
            startButton.setVisible(true);

            // // Profiles button.
            profilesButton.setVisible(true);

            // // Options button.
            optionsButton.setVisible(false);
    
        } else if (GameStates.currentGameState == GameStates.SET_UP_STATE) {
            startButton.setVisible(false);
            profilesButton.setVisible(false);
            optionsButton.setVisible(false);

            profileTextField.setVisible(false);
            profilesScreenCancelButton.setVisible(false);
            profilesScreenEnterButton.setVisible(false);
            
            // Screen title.
            HelperFunctions.drawText(g2d, "Set Up", fonts.pixeloidSans, Color.white, 48, 200, 50, 
                                     400, 50, true, true);

            // Profile names drop down list.
            g2d.setColor(Color.white);
            if (selectedPlayerBox != -1) {
                setUpScreenProfileList.setVisible(true);
            } else {
                setUpScreenProfileList.setVisible(false);
            }

            // Token selection.
            if (selectedPlayerBox != -1) {
                g2d.setColor(Color.white);
                g2d.fillPolygon(new int[] {480, 510, 510}, new int[] {152, 137, 167}, 3);
                playerTokenLeftArrowLabel.setVisible(true);
                drawBlankBox(g2d, Color.black, Color.gray, 530, 112, 80, 80, 2);
                g2d.drawImage(selectedTokens[selectedPlayerBox], 540, 122, 60, 60, null);
                g2d.setColor(Color.white);
                g2d.fillPolygon(new int[] {630, 630, 660}, new int[] {137, 167, 152}, 3);
                playerTokenRightArrowLabel.setVisible(true);
            } else {
                playerTokenLeftArrowLabel.setVisible(false);
                playerTokenRightArrowLabel.setVisible(false);
            }

            // Player boxes.
            for (int i = 0; i < playerAmount; i++) {
                playerBoxLabels[i].setVisible(true);
                drawBlankBox(g2d, Color.black, Color.gray, 
                             playerBoxesX[i], playerBoxesY[i], 
                             playerBoxWidth, playerBoxHeight, 5);

                if (selectedProfiles[i] != null) {
                    HelperFunctions.drawText(g2d, selectedProfiles[i], fonts.pixeloidSans,
                                             Color.white, 18, playerBoxesX[i], playerBoxesY[i], 
                                             playerBoxWidth, playerBoxHeight, true, true);
                }

                if (selectedTokens[i] != null 
                    && selectedTokens[i] != playerTokenImages[playerTokenImages.length - 1]) {
                    drawBlankBox(g2d, Color.black, Color.gray, playerBoxesX[i], playerBoxesY[i], 
                                 40, 40, 2);
                    g2d.drawImage(selectedTokens[i], playerBoxesX[i] + 5, playerBoxesY[i] + 5, 
                                  30, 30, null);
                }
            }

            // Add player box.
            if (playerAmount < 6) {
                drawBlankBox(g2d, Color.black, Color.gray,
                             playerBoxesX[playerAmount], 
                             playerBoxesY[playerAmount],
                             playerBoxWidth, playerBoxHeight, 5);
                g2d.setColor(Color.black);
                g2d.drawLine(playerBoxesX[playerAmount] + 75, 
                             playerBoxesY[playerAmount] + 50,
                             playerBoxesX[playerAmount] + 75, 
                             playerBoxesY[playerAmount] + 100);
                g2d.drawLine(playerBoxesX[playerAmount] + 50, 
                             playerBoxesY[playerAmount] + 75,
                             playerBoxesX[playerAmount] + 100, 
                             playerBoxesY[playerAmount] + 75);
                addPlayerBoxLabel.setBounds(playerBoxesX[playerAmount], 
                                            playerBoxesY[playerAmount], 
                                            playerBoxWidth, playerBoxHeight);
                addPlayerBoxLabel.setVisible(true);
            } else {
                addPlayerBoxLabel.setVisible(false);
            }

            // Creates remove buttons for players 3-6.
            if (playerAmount > 2) {
                for (int i = 0; i < playerAmount - 2; i++) {
                    removePlayerBoxLabels[i].setVisible(true);
                    drawBlankBox(g2d, Color.black, Color.gray, 
                             removePlayerButtonsX[i], removePlayerButtonsY[i], 
                             removePlayersButtonsWidth, removePlayerButtonsHeight, 2);
                    g2d.setColor(Color.black);
                    g2d.drawLine(removePlayerButtonsX[i] + 7, removePlayerButtonsY[i] + 15, 
                                 removePlayerButtonsX[i] + 23, removePlayerButtonsY[i] + 15);
                }
            }

            // Highlights selected player box.
            if (selectedPlayerBox != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(playerBoxesX[selectedPlayerBox], 
                            playerBoxesY[selectedPlayerBox], 
                            playerBoxWidth, playerBoxHeight);
            }
            
            // Highlights player box that is being hovered by the cursor.
            if (hoveredPlayerBox != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(playerBoxesX[hoveredPlayerBox], 
                         playerBoxesY[hoveredPlayerBox], 
                         playerBoxWidth, playerBoxHeight);
            }

            // Highlights add player box if it is being hovered by the cursor.
            if (hoveringAddPlayerBox == true) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(10));
                g2d.drawRect(playerBoxesX[playerAmount], 
                         playerBoxesY[playerAmount], 
                         playerBoxWidth, playerBoxHeight);
            }

            // Highlights remove player button if it is being hovered by the cursor.
            if (hoveredRemovePlayerButton != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(removePlayerButtonsX[hoveredRemovePlayerButton], 
                             removePlayerButtonsY[hoveredRemovePlayerButton],
                             removePlayersButtonsWidth, removePlayerButtonsHeight);
            }
            
            // Back button.
            drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
            menuBackButton.setVisible(true);

            // Create profile button.
            drawBlankBox(g2d, Color.black, Color.gray, 325, 750, 150, 30, 2);
            setUpScreenCreateProfileButton.setVisible(true);

            // Start game button.
            drawBlankBox(g2d, Color.black, Color.gray, 680, 750, 100, 30, 2);
            startGameButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.CREATE_PROFILE_STATE) {
            startButton.setVisible(false);
            profilesButton.setVisible(false);
            optionsButton.setVisible(false);
            setUpScreenProfileList.setVisible(false);
            setUpScreenCreateProfileButton.setVisible(false);
            startGameButton.setVisible(false);
            profilesScreenCreateButton.setVisible(false);

            // Screen title.
            HelperFunctions.drawText(g2d, "Profiles", fonts.pixeloidSans, Color.white, 48, 200, 50, 
                                     400, 50, true, true);

            // Profile name box.
            drawBlankBox(g2d, Color.black, Color.gray, 200, 300, 400, 200, 5);
            HelperFunctions.drawText(g2d, "Please enter a profile name:", fonts.pixeloidSans, 
                                     Color.black, 16, 240, 390, 200, 20, true, true);

            // Profile name text field.
            profileTextField.setVisible(true);
            profileTextField.requestFocus();
            
            // Cancel profile button.
            drawBlankBox(g2d, Color.black, Color.gray, 250, 520, 100, 30, 2);
            profilesScreenCancelButton.setVisible(true);

            // Enter profile button.
            drawBlankBox(g2d, Color.black, Color.gray, 450, 520, 100, 30, 2);
            profilesScreenEnterButton.setVisible(true);

            // Back button.
            drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
            menuBackButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.PROFILE_ERROR_STATE) {
            profilesScreenCreateButton.setVisible(false);

            // Screen title.
            HelperFunctions.drawText(g2d, "Profiles", fonts.pixeloidSans, Color.white, 48, 200, 50, 
                                     400, 50, true, true);

            // Profile name box.
            drawBlankBox(g2d, Color.black, Color.gray, 200, 300, 400, 200, 5);

            if (profiles.size() >= (Settings.MAX_NUMBER_PROFILES)) {
                // Maximum profile size error message.
                HelperFunctions.drawText(g2d, "Maximum number of profiles reached.", 
                                         fonts.pixeloidSans, Color.black, 16,
                                        240, 390, 320, 20, true, true);
                profileTextField.setVisible(false);

                // Back button.
                drawBlankBox(g2d, Color.black, Color.gray, 350, 520, 100, 30, 2);
                profilesScreenMaxBackButton.setVisible(true);
            } else {
                // Profile name prompt.
                HelperFunctions.drawText(g2d, "Please enter a profile name:", fonts.pixeloidSans, 
                                         Color.black, 16, 240, 390, 200, 20, true, true);

                // Profile name text field.
                profileTextField.setVisible(true);
                profileTextField.requestFocus();
                
                // Non-unique profile name error message.
                HelperFunctions.drawText(g2d, "Profile name already taken.", 
                                         fonts.pixeloidSans, Color.black, 16,
                                         240, 420, 320, 20, true, true);
                HelperFunctions.drawText(g2d, "Please enter a different name.", 
                                         fonts.pixeloidSans, Color.black, 16,
                                         240, 440, 320, 20, true, true);

                // Cancel profile button.
                drawBlankBox(g2d, Color.black, Color.gray, 250, 520, 100, 30, 2);
                profilesScreenCancelButton.setVisible(true);

                // Enter profile button.
                drawBlankBox(g2d, Color.black, Color.gray, 450, 520, 100, 30, 2);
                profilesScreenEnterButton.setVisible(true);
            }

            // Back button.
            drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
            menuBackButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.PROFILE_SCREEN_STATE) {
            startButton.setVisible(false);
            profilesButton.setVisible(false);
            optionsButton.setVisible(false);
            profileTextField.setVisible(false);
            profilesScreenCancelButton.setVisible(false);
            profilesScreenEnterButton.setVisible(false);
            profilesScreenMaxBackButton.setVisible(false);

            // Screen title.
            HelperFunctions.drawText(g2d, "Profiles", fonts.pixeloidSans, Color.white, 48, 200, 50, 
                                     400, 50, true, true);

            // Profile names list.
            drawBlankBox(g2d, Color.black, Color.gray, 50, 150, 400, 550, 5);
            for (int i = 0; i < profiles.size(); i++) {
                HelperFunctions.drawText(g2d, profiles.get(i).name, fonts.pixeloidSans, 
                                         Color.black, 18,
                                         profiles.get(i).nameXcoordinate, 
                                         profiles.get(i).nameYcoordinate, 
                                         100, 20, false, true);
            }

            // Back button.
            drawBlankBox(g2d, Color.black, Color.gray, 20, 750, 100, 30, 2);
            menuBackButton.setVisible(true);

            // Create profile button.
            drawBlankBox(g2d, Color.black, Color.gray, 680, 750, 100, 30, 2);
            profilesScreenCreateButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            setUpScreenProfileList.setVisible(false);
            playerTokenLeftArrowLabel.setVisible(false);
            playerTokenRightArrowLabel.setVisible(false);
            menuBackButton.setVisible(false);
            setUpScreenCreateProfileButton.setVisible(false);
            startGameButton.setVisible(false);
            managerMortgagingButton.setVisible(false);
            managerTradingButton.setVisible(false);
            managerBuildingButton.setVisible(false);
            managerBankruptcyButton.setVisible(false);
            managerBackButton.setVisible(false);

            // Roll button.
            g2d.drawImage(rollButtonImage, 79, 41, null);
            rollButton.setVisible(true);

            // Manager button.
            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            managerButton.setVisible(true);

            // Next turn button.
            g2d.drawImage(nextTurnButtonImage, 621, 41, null);
            nextTurnButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.DICE_DELAY_STATE) {
            okButton.setVisible(false);

            // Roll button.
            g2d.drawImage(rollButtonImage, 79, 41, null);
            rollButton.setVisible(true);

            // Manager button.
            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            managerButton.setVisible(true);

            // Next turn button.
            g2d.drawImage(nextTurnButtonImage, 621, 41, null);
            nextTurnButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.DICE_MOVE_STATE
                   || GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
            okButton.setVisible(false);

            // Roll button.
            g2d.drawImage(rollButtonImage, 79, 41, null);
            rollButton.setVisible(true);

            // Manager button.
            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            managerButton.setVisible(true);

            // Next turn button.
            g2d.drawImage(nextTurnButtonImage, 621, 41, null);
            nextTurnButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            okButton.setVisible(false);
            managerMortgagingButton.setVisible(false);
            managerTradingButton.setVisible(false);
            managerBuildingButton.setVisible(false);
            managerBankruptcyButton.setVisible(false);
            managerBackButton.setVisible(false);
            payButton.setVisible(false);

            // Roll button.
            g2d.drawImage(rollButtonImage, 79, 41, null);
            rollButton.setVisible(true);

            // Manager button.
            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            managerButton.setVisible(true);

            // Next turn button.
            g2d.drawImage(nextTurnButtonImage, 621, 41, null);
            nextTurnButton.setVisible(true);

            if (players.get(gamePanel.currentPlayerNumber - 1).doublesStreak >= 3) {
                okButton.setVisible(true);
            } else if (spaceData.currentSpaceType == "Go") {
                // Ok button
                okButton.setVisible(true);
                // HelperFunctions.drawText(g2d, "Ok", new Color(153, 235, 255), 16, 350, 475, 
                //                          100, 30, true, true);
            } else if (spaceData.currentSpaceType == "Jail") {
                if (players.get(gamePanel.currentPlayerNumber - 1).isInJail == true) {
                    if (dice.result == 0) {
                        jailRollButton.setVisible(true);
                        jailPayBailButton.setBounds(350, 475, 100, 50);
                        jailPayBailButton.setVisible(true);
                        // jailUseCardButton.setVisible(true);
                    } else if (players.get(gamePanel.currentPlayerNumber - 1).turnsInJail >= 3) {
                        jailPayBailButton.setBounds(350, 450, 100, 50);
                        jailPayBailButton.setVisible(true);
                    } else {
                        okButton.setVisible(true);
                    }
                } else {
                    okButton.setVisible(true);
                }
            } else if (spaceData.currentSpaceType == "Free Parking") {
                okButton.setVisible(true);
            } else if (spaceData.currentSpaceType == "Go To Jail") {
                okButton.setVisible(true);
            } else if (spaceData.currentSpaceType == "Normal Property") {
                if (spaceData.currentNormalProperty.owner == null) {
                    buyOptionText.setVisible(true);
                    passOptionText.setVisible(true);

                    if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                                              < spaceData.currentNormalProperty.price) {
                        buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    }
                } else if (spaceData.currentNormalProperty.owner != gamePanel.currentPlayer
                           && spaceData.currentNormalProperty.isMortgaged == false) {
                    payButton.setVisible(true);

                    if (players.get(gamePanel.currentPlayerNumber - 1).money 
                        < spaceData.currentNormalProperty.currentRent) {
                        payButton.setForeground(new Color(255, 255, 255, 75));
                    }
                } else {
                    buyOptionText.setVisible(false);
                    passOptionText.setVisible(false);
                    okButton.setVisible(true);
                }
            } else if (spaceData.currentSpaceType == "Railroad") {
                if (spaceData.currentRailroad.owner == null) {
                    buyOptionText.setVisible(true);
                    passOptionText.setVisible(true);
                    
                    if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                                              < spaceData.currentRailroad.price) {
                        buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    }
                } else if (spaceData.currentRailroad.owner != gamePanel.currentPlayer
                           && spaceData.currentRailroad.isMortgaged == false) {
                    payButton.setVisible(true);

                    if (players.get(gamePanel.currentPlayerNumber - 1).money 
                        < spaceData.currentRailroad.currentRent) {
                        payButton.setForeground(new Color(255, 255, 255, 75));
                    }
                } else {
                    okButton.setVisible(true);
                }
            } else if (spaceData.currentSpaceType == "Utility") {
                if (spaceData.currentUtility.owner == null) {
                    buyOptionText.setVisible(true);
                    passOptionText.setVisible(true);

                    if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                                              < spaceData.currentUtility.price) {
                        buyOptionText.setForeground(new Color(255, 255, 255, 75));
                    }
                } else if (spaceData.currentUtility.owner != gamePanel.currentPlayer
                           && spaceData.currentUtility.isMortgaged == false) {
                    payButton.setVisible(true);

                    if (players.get(gamePanel.currentPlayerNumber - 1).money 
                        < spaceData.currentUtility.currentMultiplier * dice.result) {
                        payButton.setForeground(new Color(255, 255, 255, 75));
                    }
                } else {
                    okButton.setVisible(true);
                }
            } else if (spaceData.currentSpaceType == "Card") {
                okButton.setVisible(true);
            } else if (spaceData.currentSpaceType == "Tax") {
                payButton.setVisible(true);

                if (gamePanel.currentPlayer.money < spaceData.currentTaxSpace.fee) {
                    payButton.setForeground(new Color(255, 255, 255, 75));
                }
            }
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            okButton.setVisible(false);
            payButton.setVisible(false);
            managerMortgagingButton.setVisible(false);
            managerTradingButton.setVisible(false);
            managerBuildingButton.setVisible(false);
            managerBankruptcyButton.setVisible(false);
            managerBackButton.setVisible(false);
            
            // Roll button.
            g2d.drawImage(rollButtonImage, 79, 41, null);
            rollButton.setVisible(true);

            // Manager button.
            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            managerButton.setVisible(true);

            // Next turn button.
            g2d.drawImage(nextTurnButtonImage, 621, 41, null);
            nextTurnButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
            rollButton.setVisible(false);
            managerButton.setVisible(false);
            nextTurnButton.setVisible(false);
            okButton.setVisible(false);
            buyOptionText.setVisible(false);
            passOptionText.setVisible(false);
            payButton.setVisible(false);
            jailRollButton.setVisible(false);
            jailPayBailButton.setVisible(false);
            jailUseCardButton.setVisible(false);
            mortgageButton.setVisible(false);
            unMortgageButton.setVisible(false);
            playerTradingList.setVisible(false);
            startTradingButton.setVisible(false);
            declineTradeButton.setVisible(false);
            acceptTradeButton.setVisible(false);
            bankruptcyNoButton.setVisible(false);
            bankruptcyYesButton.setVisible(false);

            // Mortgaging/Unmortgaging button.
            managerMortgagingButton.setVisible(true);

            // Trading button.
            managerTradingButton.setVisible(true);

            // Building/selling button.
            managerBuildingButton.setVisible(true);

            // Declare bankruptcy button.m
            managerBankruptcyButton.setVisible(true);

            // Back button.
            managerBackButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE) {
            managerMortgagingButton.setVisible(false);
            managerTradingButton.setVisible(false);
            managerBuildingButton.setVisible(false);
            managerBankruptcyButton.setVisible(false);

            int normalPropertiesIndex = 0;
            int railroadsIndex = 0;
            int utilitiesIndex = 0;

            for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 
                                             mortgagingPropertyBoxesX[i], 
                                             mortgagingPropertyBoxesY[i], 
                                             mortgagingPropertyWidth, 
                                             mortgagingPropertyHeight, 
                                             2);
                if (i < playerManager.currentPlayerNormalProperties.size()) {
                    mortgagingProperties[i].normalProperty 
                        = playerManager.currentPlayerNormalProperties.get(normalPropertiesIndex);
                    mortgagingProperties[i].propertyType = "Normal Property";
                    if (mortgagingProperties[i].normalProperty.isMortgaged == true) {
                        HelperFunctions.drawText(g2d, "M", fonts.pixeloidSans, Color.black, 18, 
                                                 mortgagingPropertyBoxesX[i], mortgagingPropertyBoxesY[i], 
                                                 mortgagingPropertyWidth, mortgagingPropertyHeight, 
                                                 true, true);
                    }
                    g2d.setColor(HelperFunctions.getColor(playerManager.currentPlayerNormalProperties.get(normalPropertiesIndex)));
                    g2d.fillRect(mortgagingPropertyBoxesX[i] + 5, 
                                 mortgagingPropertyBoxesY[i] + 5, 
                                 40, 10);
                    normalPropertiesIndex++;
                } else if (i < playerManager.currentPlayerNormalProperties.size() 
                            + playerManager.currentPlayerRailroads.size()) {
                    mortgagingProperties[i].railroadProperty
                        = playerManager.currentPlayerRailroads.get(railroadsIndex);
                    mortgagingProperties[i].propertyType = "Railroad";
                    if (mortgagingProperties[i].railroadProperty.isMortgaged == true) {
                        HelperFunctions.drawText(g2d, "M", fonts.pixeloidSans, Color.black, 18, 
                                                 mortgagingPropertyBoxesX[i], mortgagingPropertyBoxesY[i], 
                                                 mortgagingPropertyWidth, mortgagingPropertyHeight, 
                                                 true, true);
                    }
                    g2d.drawImage(playerManager.currentPlayerRailroads.get(railroadsIndex).image, 
                                  mortgagingPropertyBoxesX[i] + 5, 
                                  mortgagingPropertyBoxesY[i] + 5, 
                                  null);
                    railroadsIndex++;
                } else if (i < playerManager.currentPlayerNormalProperties.size() 
                            + playerManager.currentPlayerRailroads.size() 
                            + playerManager.currentPlayerUtilities.size()) {
                    mortgagingProperties[i].utilityProperty 
                        = playerManager.currentPlayerUtilities.get(utilitiesIndex);
                    mortgagingProperties[i].propertyType = "Utility";
                    if (mortgagingProperties[i].utilityProperty.isMortgaged == true) {
                        HelperFunctions.drawText(g2d, "M", fonts.pixeloidSans, Color.black, 18, 
                                                 mortgagingPropertyBoxesX[i], mortgagingPropertyBoxesY[i], 
                                                 mortgagingPropertyWidth, mortgagingPropertyHeight, 
                                                 true, true);
                    }
                    utilitiesIndex++;
                }

                mortgagingProperties[i].boxLabel.setVisible(true);
            }

            // Highlights a property box if it is selected.
            if (mortgagingSelectedProperty != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(mortgagingPropertyBoxesX[mortgagingSelectedProperty], 
                             mortgagingPropertyBoxesY[mortgagingSelectedProperty],
                             mortgagingPropertyWidth, mortgagingPropertyHeight);

                if (mortgagingProperties[mortgagingSelectedProperty].propertyType 
                    == "Normal Property") {
                    HelperFunctions.drawText(g2d, 
                                             mortgagingProperties[mortgagingSelectedProperty].normalProperty.name,
                                             fonts.pixeloidSans, Color.white, 
                                             22, 200, 500, 400, 50, true, true);
                    if (mortgagingProperties[mortgagingSelectedProperty].normalProperty.isMortgaged == false) {
                        HelperFunctions.drawText(g2d, 
                                                 "Mortgage Value: $" + mortgagingProperties[mortgagingSelectedProperty].normalProperty.mortgageValue, 
                                                 fonts.pixeloidSans, Color.white, 
                                                 22, 200, 550, 400, 50, true, true);
                    } else {
                        HelperFunctions.drawText(g2d, 
                                                 "Unmortgage Fee: $" + mortgagingProperties[mortgagingSelectedProperty].normalProperty.unMortgageFee, 
                                                 fonts.pixeloidSans, Color.white, 
                                                 22, 200, 550, 400, 50, true, true);
                    }
                } else if (mortgagingProperties[mortgagingSelectedProperty].propertyType 
                    == "Railroad") {
                    HelperFunctions.drawText(g2d, 
                                             mortgagingProperties[mortgagingSelectedProperty].railroadProperty.name,
                                             fonts.pixeloidSans, Color.white, 
                                             22, 200, 500, 400, 50, true, true);
                    if (mortgagingProperties[mortgagingSelectedProperty].railroadProperty.isMortgaged == false) {
                        HelperFunctions.drawText(g2d, 
                                                 "Mortgage Value: $" + mortgagingProperties[mortgagingSelectedProperty].railroadProperty.mortgageValue, 
                                                 fonts.pixeloidSans, Color.white, 
                                                 22, 200, 550, 400, 50, true, true);
                    } else {
                        HelperFunctions.drawText(g2d, 
                                                 "Unmortgage Fee: $" + mortgagingProperties[mortgagingSelectedProperty].railroadProperty.unMortgageFee, 
                                                 fonts.pixeloidSans, Color.white, 
                                                 22, 200, 550, 400, 50, true, true);
                    }
                } else if (mortgagingProperties[mortgagingSelectedProperty].propertyType 
                    == "Utility") {
                    HelperFunctions.drawText(g2d, 
                                             mortgagingProperties[mortgagingSelectedProperty].utilityProperty.name,
                                             fonts.pixeloidSans, Color.white, 
                                             22, 200, 500, 400, 50, true, true);
                    if (mortgagingProperties[mortgagingSelectedProperty].utilityProperty.isMortgaged == false) {
                        HelperFunctions.drawText(g2d, 
                                                 "Mortgage Value: $" + mortgagingProperties[mortgagingSelectedProperty].utilityProperty.mortgageValue, 
                                                 fonts.pixeloidSans, Color.white, 
                                                 22, 200, 550, 400, 50, true, true);
                    } else {
                        HelperFunctions.drawText(g2d, 
                                                 "Unmortgage Fee: $" + mortgagingProperties[mortgagingSelectedProperty].utilityProperty.unMortgageFee, 
                                                 fonts.pixeloidSans, Color.white, 
                                                 22, 200, 550, 400, 50, true, true);
                    }
                }
            }

            // Highlights a property box if it is being hovered by the cursor.
            if (mortgagingHoveredProperty != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(mortgagingPropertyBoxesX[mortgagingHoveredProperty], 
                             mortgagingPropertyBoxesY[mortgagingHoveredProperty],
                             mortgagingPropertyWidth, mortgagingPropertyHeight);
            }

            if (playerManager.currentPlayerPropertyAmount > 0) {
                mortgageButton.setVisible(true);
                unMortgageButton.setVisible(true);
            }

            managerBackButton.setVisible(true);   
        } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
            managerMortgagingButton.setVisible(false);
            managerTradingButton.setVisible(false);
            managerBuildingButton.setVisible(false);
            managerBankruptcyButton.setVisible(false);
            offerTradeButton.setVisible(false);

            for (int i = 0; i < tradingCurrentPlayerProperties.length; i++) {
                tradingCurrentPlayerProperties[i].boxLabel.setVisible(false);
                tradingPlayerTradingWithProperties[i].boxLabel.setVisible(false);
            }

            playerTradingList.setVisible(true);
            startTradingButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.TRADING_CREATE_STATE) {
            playerTradingList.setVisible(false);
            startTradingButton.setVisible(false);
            declineTradeButton.setVisible(false);
            acceptTradeButton.setVisible(false);
            // tradingCurrentPlayerProperties.setVisible(true);
            // tradingCurrentPlayerAdd.setVisible(true);
            // tradingPlayerTradingWithProperties.setVisible(true);
            // tradingPlayerTradingWithAdd.setVisible(true);
            // tradingCurrentPlayerTradingProperties.setVisible(true);

            int normalPropertiesIndex = 0;
            int railroadsIndex = 0;
            int utilitiesIndex = 0;

            for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 
                                             tradingMenuPropertyBoxesX[i], 
                                             tradingMenuPropertyBoxesY[i], 
                                             tradingMenuPropertyWidth, 
                                             tradingMenuPropertyHeight, 
                                             2);
                if (i < playerManager.currentPlayerNormalProperties.size()) {
                    tradingCurrentPlayerProperties[i].normalProperty 
                        = playerManager.currentPlayerNormalProperties.get(normalPropertiesIndex);
                    tradingCurrentPlayerProperties[i].propertyType = "Normal Property";
                    g2d.setColor(HelperFunctions.getColor(playerManager.currentPlayerNormalProperties.get(normalPropertiesIndex)));
                    g2d.fillRect(tradingMenuPropertyBoxesX[i] + 5, 
                                 tradingMenuPropertyBoxesY[i] + 5, 
                                 40, 10);
                    normalPropertiesIndex++;
                } else if (i < playerManager.currentPlayerNormalProperties.size() 
                            + playerManager.currentPlayerRailroads.size()) {
                    tradingCurrentPlayerProperties[i].railroadProperty
                        = playerManager.currentPlayerRailroads.get(railroadsIndex);
                    tradingCurrentPlayerProperties[i].propertyType = "Railroad";
                    g2d.drawImage(playerManager.currentPlayerRailroads.get(railroadsIndex).image, 
                                  tradingMenuPropertyBoxesX[i] + 5, 
                                  tradingMenuPropertyBoxesY[i] + 5, 
                                  null);
                    railroadsIndex++;
                } else if (i < playerManager.currentPlayerNormalProperties.size() 
                            + playerManager.currentPlayerRailroads.size() 
                            + playerManager.currentPlayerUtilities.size()) {
                    tradingCurrentPlayerProperties[i].utilityProperty 
                        = playerManager.currentPlayerUtilities.get(utilitiesIndex);
                    tradingCurrentPlayerProperties[i].propertyType = "Utility";
                    utilitiesIndex++;
                }

                if (tradingCurrentPlayerProperties[i].selected == true) {
                    g2d.drawImage(playerManager.tradingRightArrowImage, 
                                 tradingMenuPropertyBoxesX[i] + 10, 
                                 tradingMenuPropertyBoxesY[i] + 10, 
                                 null); 
                }

                // for (int j = 0; j < tradingMenuSelectedCurrentPlayerProperties.size(); j++) {
                //     if (tradingMenuSelectedCurrentPlayerProperties.get(j) == i) {
                //         g2d.drawImage(playerManager.tradingRightArrowImage, tradingMenuPropertyBoxesX[i] + 10, tradingMenuPropertyBoxesY[i] + 10, null);
                //     }
                // }
                tradingCurrentPlayerProperties[i].boxLabel.setVisible(true);
            }

            // Highlights a property box of the current player if it is being hovered by the cursor.
            if (tradingMenuHoveredCurrentPlayerProperty != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(tradingMenuPropertyBoxesX[tradingMenuHoveredCurrentPlayerProperty], 
                             tradingMenuPropertyBoxesY[tradingMenuHoveredCurrentPlayerProperty],
                             tradingMenuPropertyWidth, tradingMenuPropertyHeight);
            }

            normalPropertiesIndex = 0;
            railroadsIndex = 0;
            utilitiesIndex = 0;

            for (int i = 0; i < playerManager.playerTradingWithPropertyAmount; i++) {
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 
                                             tradingMenuPropertyBoxesX[i] + 400, 
                                             tradingMenuPropertyBoxesY[i], 
                                             tradingMenuPropertyWidth, 
                                             tradingMenuPropertyHeight, 
                                             2);
                if (i < playerManager.playerTradingWithNormalProperties.size()) {
                    tradingPlayerTradingWithProperties[i].normalProperty 
                        = playerManager.playerTradingWithNormalProperties.get(normalPropertiesIndex);
                    tradingPlayerTradingWithProperties[i].propertyType 
                        = "Normal Property";
                    g2d.setColor(HelperFunctions.getColor(playerManager.playerTradingWithNormalProperties.get(normalPropertiesIndex)));
                    g2d.fillRect(tradingMenuPropertyBoxesX[i] + 405, 
                                 tradingMenuPropertyBoxesY[i] + 5, 
                                 40, 10);
                    normalPropertiesIndex++;
                } else if (i < playerManager.playerTradingWithNormalProperties.size() 
                            + playerManager.playerTradingWithRailroads.size()) {
                    tradingPlayerTradingWithProperties[i].railroadProperty 
                        = playerManager.playerTradingWithRailroads.get(railroadsIndex);
                    tradingPlayerTradingWithProperties[i].propertyType 
                        = "Railroad";
                    g2d.drawImage(playerManager.playerTradingWithRailroads.get(railroadsIndex).image, 
                                  tradingMenuPropertyBoxesX[i] + 405, 
                                  tradingMenuPropertyBoxesY[i] + 5, 
                                  null);
                    railroadsIndex++;
                } else if (i < playerManager.playerTradingWithNormalProperties.size() 
                            + playerManager.playerTradingWithRailroads.size() 
                            + playerManager.playerTradingWithUtilities.size()) {
                    tradingPlayerTradingWithProperties[i].utilityProperty
                        = playerManager.playerTradingWithUtilities.get(utilitiesIndex);
                    tradingPlayerTradingWithProperties[i].propertyType 
                        = "Utility";
                    utilitiesIndex++;
                }

                if (tradingPlayerTradingWithProperties[i].selected == true) {
                    g2d.drawImage(playerManager.tradingLeftArrowImage, 
                                 tradingMenuPropertyBoxesX[i] + 410, 
                                 tradingMenuPropertyBoxesY[i] + 10, 
                                 null); 
                }
                tradingPlayerTradingWithProperties[i].boxLabel.setVisible(true);
            }

            // Highlights a property box of the player being traded with if it is being hovered by 
            // the cursor.
            if (tradingMenuHoveredPlayerTradingWithProperty != -1) {
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(tradingMenuPropertyBoxesX[tradingMenuHoveredPlayerTradingWithProperty] + 400, 
                             tradingMenuPropertyBoxesY[tradingMenuHoveredPlayerTradingWithProperty],
                             tradingMenuPropertyWidth, tradingMenuPropertyHeight);
            }

            managerBackButton.setVisible(true);
            offerTradeButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.TRADING_OFFER_STATE) {
            // tradingCurrentPlayerProperties.setVisible(true);
            // tradingCurrentPlayerAdd.setVisible(true);
            // tradingPlayerTradingWithProperties.setVisible(true);
            // tradingPlayerTradingWithAdd.setVisible(true);
            // tradingCurrentPlayerTradingProperties.setVisible(true);
            managerBackButton.setVisible(false);
            offerTradeButton.setVisible(false);

            int normalPropertiesIndex = 0;
            int railroadsIndex = 0;
            int utilitiesIndex = 0;

            for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 
                                             tradingMenuPropertyBoxesX[i], 
                                             tradingMenuPropertyBoxesY[i], 
                                             tradingMenuPropertyWidth, 
                                             tradingMenuPropertyHeight, 
                                             2);
                if (i < playerManager.currentPlayerNormalProperties.size()) {
                    tradingCurrentPlayerProperties[i].normalProperty 
                        = playerManager.currentPlayerNormalProperties.get(normalPropertiesIndex);
                    tradingCurrentPlayerProperties[i].propertyType = "Normal Property";
                    g2d.setColor(HelperFunctions.getColor(playerManager.currentPlayerNormalProperties.get(normalPropertiesIndex)));
                    g2d.fillRect(tradingMenuPropertyBoxesX[i] + 5, 
                                 tradingMenuPropertyBoxesY[i] + 5, 
                                 40, 10);
                    normalPropertiesIndex++;
                } else if (i < playerManager.currentPlayerNormalProperties.size() 
                            + playerManager.currentPlayerRailroads.size()) {
                    tradingCurrentPlayerProperties[i].railroadProperty
                        = playerManager.currentPlayerRailroads.get(railroadsIndex);
                    tradingCurrentPlayerProperties[i].propertyType = "Railroad";
                    g2d.drawImage(playerManager.currentPlayerRailroads.get(railroadsIndex).image, 
                                  tradingMenuPropertyBoxesX[i] + 5, 
                                  tradingMenuPropertyBoxesY[i] + 5, 
                                  null);
                    railroadsIndex++;
                } else if (i < playerManager.currentPlayerNormalProperties.size() 
                            + playerManager.currentPlayerRailroads.size() 
                            + playerManager.currentPlayerUtilities.size()) {
                    tradingCurrentPlayerProperties[i].utilityProperty 
                        = playerManager.currentPlayerUtilities.get(utilitiesIndex);
                    tradingCurrentPlayerProperties[i].propertyType = "Utility";
                    utilitiesIndex++;
                }

                if (tradingCurrentPlayerProperties[i].selected == true) {
                    g2d.drawImage(playerManager.tradingRightArrowImage, 
                                 tradingMenuPropertyBoxesX[i] + 10, 
                                 tradingMenuPropertyBoxesY[i] + 10, 
                                 null); 
                }

                // for (int j = 0; j < tradingMenuSelectedCurrentPlayerProperties.size(); j++) {
                //     if (tradingMenuSelectedCurrentPlayerProperties.get(j) == i) {
                //         g2d.drawImage(playerManager.tradingRightArrowImage, tradingMenuPropertyBoxesX[i] + 10, tradingMenuPropertyBoxesY[i] + 10, null);
                //     }
                // }
                tradingCurrentPlayerProperties[i].boxLabel.setVisible(false);
            }

            normalPropertiesIndex = 0;
            railroadsIndex = 0;
            utilitiesIndex = 0;

            for (int i = 0; i < playerManager.playerTradingWithPropertyAmount; i++) {
                HelperFunctions.drawBlankBox(g2d, Color.black, Color.gray, 
                                             tradingMenuPropertyBoxesX[i] + 400, 
                                             tradingMenuPropertyBoxesY[i], 
                                             tradingMenuPropertyWidth, 
                                             tradingMenuPropertyHeight, 
                                             2);
                if (i < playerManager.playerTradingWithNormalProperties.size()) {
                    tradingPlayerTradingWithProperties[i].normalProperty 
                        = playerManager.playerTradingWithNormalProperties.get(normalPropertiesIndex);
                    tradingPlayerTradingWithProperties[i].propertyType 
                        = "Normal Property";
                    g2d.setColor(HelperFunctions.getColor(playerManager.playerTradingWithNormalProperties.get(normalPropertiesIndex)));
                    g2d.fillRect(tradingMenuPropertyBoxesX[i] + 405, 
                                 tradingMenuPropertyBoxesY[i] + 5, 
                                 40, 10);
                    normalPropertiesIndex++;
                } else if (i < playerManager.playerTradingWithNormalProperties.size() 
                            + playerManager.playerTradingWithRailroads.size()) {
                    tradingPlayerTradingWithProperties[i].railroadProperty 
                        = playerManager.playerTradingWithRailroads.get(railroadsIndex);
                    tradingPlayerTradingWithProperties[i].propertyType 
                        = "Railroad";
                    g2d.drawImage(playerManager.playerTradingWithRailroads.get(railroadsIndex).image, 
                                  tradingMenuPropertyBoxesX[i] + 405, 
                                  tradingMenuPropertyBoxesY[i] + 5, 
                                  null);
                    railroadsIndex++;
                } else if (i < playerManager.playerTradingWithNormalProperties.size() 
                            + playerManager.playerTradingWithRailroads.size() 
                            + playerManager.playerTradingWithUtilities.size()) {
                    tradingPlayerTradingWithProperties[i].utilityProperty
                        = playerManager.playerTradingWithUtilities.get(utilitiesIndex);
                    tradingPlayerTradingWithProperties[i].propertyType 
                        = "Utility";
                    utilitiesIndex++;
                }

                if (tradingPlayerTradingWithProperties[i].selected == true) {
                    g2d.drawImage(playerManager.tradingLeftArrowImage, 
                                 tradingMenuPropertyBoxesX[i] + 410, 
                                 tradingMenuPropertyBoxesY[i] + 10, 
                                 null); 
                }
                tradingPlayerTradingWithProperties[i].boxLabel.setVisible(false);
            }

            declineTradeButton.setVisible(true);
            acceptTradeButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
            managerMortgagingButton.setVisible(false);
            managerTradingButton.setVisible(false);
            managerBuildingButton.setVisible(false);
            managerBankruptcyButton.setVisible(false);
            managerBackButton.setVisible(false);

            bankruptcyNoButton.setVisible(true);
            bankruptcyYesButton.setVisible(true);
        } else if (GameStates.currentGameState == GameStates.GAME_OVER_STATE) {
            backtoMainMenuButton.setVisible(true);
        }
    }
}
