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

    public boolean lastScreenIsSetUp = false;

    public MouseHandler mouseHandler;
    public KeyHandler keyHandler;

    /**
    * Constructor.
    */
    public Ui(GamePanel gamePanel, Fonts fonts, SpaceData spaceData, SaveLoad saveLoad, Dice dice, 
              ArrayList<Profile> profiles, 
              ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.fonts = fonts;
        this.spaceData = spaceData;
        this.saveLoad = saveLoad;
        this.dice = dice;
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

        mouseHandler = new MouseHandler(gamePanel, spaceData, this, dice, profiles, players);
        keyHandler = new KeyHandler(gamePanel, spaceData, this, dice, profiles, players);

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

        createMainGameUi();
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
        setUpScreenProfileList.setBounds(210, 100, 100, 20);
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
        playerTokenLeftArrowLabel.setBounds(480, 117, 30, 30);
        gamePanel.add(playerTokenLeftArrowLabel);
        playerTokenLeftArrowLabel.addMouseListener(mouseHandler);
        playerTokenRightArrowLabel = new JLabel();
        playerTokenRightArrowLabel.setBounds(630, 117, 30, 30);
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

        buyOptionText = new JLabel();
        createButtonLabel(buyOptionText, "Buy", fonts.pixeloidSans, Color.white, 18, 
                          350, 425, 100, 50);
        buyOptionText.setVisible(false);

        okButton = new JLabel();
        createButtonLabel(okButton, "Ok", fonts.pixeloidSans, Color.white, 16, 350, 450, 100, 50);
        okButton.setVisible(false);

        passOptionText = new JLabel();
        createButtonLabel(passOptionText, "Pass", fonts.pixeloidSans, Color.white, 18, 
                          350, 450, 100, 50);
        passOptionText.setVisible(false);
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

            // Screen title.
            HelperFunctions.drawText(g2d, "Monopoly", fonts.pixeloidSans, Color.black, 72, 
                                     200, 200, 400, 100, true, true);

            // Start button.
            startButton.setVisible(true);

            // // Profiles button.
            profilesButton.setVisible(true);

            // // Options button.
            optionsButton.setVisible(true);
    
        } else if (GameStates.currentGameState == GameStates.SET_UP_STATE) {
            startButton.setVisible(false);
            profilesButton.setVisible(false);
            optionsButton.setVisible(false);

            profileTextField.setVisible(false);
            profilesScreenCancelButton.setVisible(false);
            profilesScreenEnterButton.setVisible(false);

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
                g2d.fillPolygon(new int[] {480, 510, 510}, new int[] {132, 117, 147}, 3);
                playerTokenLeftArrowLabel.setVisible(true);
                drawBlankBox(g2d, Color.black, Color.gray, 530, 92, 80, 80, 2);
                g2d.drawImage(selectedTokens[selectedPlayerBox], 540, 102, 60, 60, null);
                g2d.setColor(Color.white);
                g2d.fillPolygon(new int[] {630, 630, 660}, new int[] {117, 147, 132}, 3);
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
            HelperFunctions.drawText(g2d, "Profiles", fonts.pixeloidSans, Color.black, 48, 200, 50, 
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
            HelperFunctions.drawText(g2d, "Profiles", fonts.pixeloidSans, Color.black, 48, 200, 50, 
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
            HelperFunctions.drawText(g2d, "Profiles", fonts.pixeloidSans, Color.black, 48, 200, 50, 
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
            // Roll button.
            g2d.drawImage(rollButtonImage, 79, 41, null);
            rollButton.setVisible(true);

            // Manager button.
            g2d.drawImage(playerManagerButtonImage, 350, 41, null);
            managerButton.setVisible(true);

            // Next turn button.
            g2d.drawImage(nextTurnButtonImage, 621, 41, null);
            nextTurnButton.setVisible(true);

            if (spaceData.currentSpaceType == "Go") {
                // Ok button
                okButton.setVisible(true);
                // HelperFunctions.drawText(g2d, "Ok", new Color(153, 235, 255), 16, 350, 475, 
                //                          100, 30, true, true);
            } else if (spaceData.currentSpaceType == "Jail") {
                okButton.setVisible(true);
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
                } else {
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
                } else {
                    okButton.setVisible(true);
                }
            } else if (spaceData.currentSpaceType == "Card") {
                okButton.setVisible(true);
            } else if (spaceData.currentSpaceType == "Tax") {
                okButton.setVisible(true);
            }
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
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
        }
    }
}
