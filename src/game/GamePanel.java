package src.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import src.data.CardData;
import src.data.Fonts;
import src.data.GameStates;
import src.data.Settings;
import src.data.SpaceData;

/**
 * This class extends the JPanel container, which can store a group of components.
 */
public class GamePanel extends JPanel implements Runnable {
    private final int screenWidth;
    private final int screenHeight;
    private final int fps;

    public File profilesFile;

    public Fonts fonts;
    public SpaceData spaceData; 
    public CardData cardData;
    public MainMenu mainMenu;
    public Board board;
    public SaveLoad saveLoad;
    public Dice dice;
    public ArrayList<Profile> profiles;
    public ArrayList<Player> players;
    public PlayerInfoBox playerInfoBox;
    public Ui ui;
    public PlayerManager playerManager;
    public SpaceEvent spaceEvent;
    public Backgrounds backgrounds;

    private Thread gameThread;

    public Player currentPlayer;

    public double drawInterval;
    public double delta;
    public double playerDelta;
    public double diceDelta;
    public long lastTime;
    public long currentTime;

    public long timer;
    public int drawCount;

    public int playerAmount;
    public int currentPlayerNumber;

    /**
    * Constructor.
    */
    public GamePanel() {
        screenWidth = 800;
        screenHeight = 800;
        fps = 60;

        profilesFile = new File("src/saved-data/Profiles.txt");

        fonts = new Fonts();
        spaceData = new SpaceData();
        cardData = new CardData();
        mainMenu = new MainMenu();
        board = new Board();
        saveLoad = new SaveLoad();

        if (profilesFile.isFile()) {
            try {
                profiles = 
                    saveLoad.loadProfiles(new FileInputStream("src/saved-data/Profiles.txt"));
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            profiles = new ArrayList<Profile>();
        }
        players = new ArrayList<Player>();
        dice = new Dice(this, players);
        playerInfoBox = new PlayerInfoBox(this, fonts);
        playerManager = new PlayerManager(this, fonts, spaceData, saveLoad, dice, profiles, 
                                          players);
        ui = new Ui(this, fonts, spaceData, saveLoad, dice, playerManager, profiles, players);
        spaceEvent = new SpaceEvent(this, fonts, ui, dice, players, spaceData);
        backgrounds = new Backgrounds();

        // Calculates amount of nanoseconds in a second divided by FPS.
        drawInterval = 1000000000 / fps;

        delta = 0;
        playerDelta = 0;
        diceDelta = 0;
        lastTime = System.nanoTime();

        timer = 0;
        drawCount = 0;

        currentPlayerNumber = 1;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());
        this.addKeyListener(ui.keyHandler);
        this.setFocusable(true);

        this.startGameThread();
    }

    /**
    * Starts the game thread.
    */
    public void startGameThread() {
        gameThread = new Thread(this);

        // Automatically calls the run() method.
        gameThread.start();
    }

    /**
    * Creates the main game loop.
    */
    @Override
    public void run() {
        // Calculates amount of nanoseconds in a second divided by FPS
        // double drawInterval = 1000000000/FPS;
        // double delta = 0;
        // double playerDelta = 0;
        // long lastTime = System.nanoTime();
        // long currentTime;

        // long timer = 0;
        // int drawCount = 0;

        // As long as gameThread exists, this while loop will run.
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
                playerDelta += (currentTime - lastTime) / drawInterval;
            } else {
                playerDelta = 0;
            }
            if (GameStates.currentGameState == GameStates.DICE_DELAY_STATE) {
                diceDelta += (currentTime - lastTime) / drawInterval;
            } else {
                diceDelta = 0;
            }

            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // Updates information such as sprite positions.
                update();

                // Draws the screen with updated information.
                repaint();

                delta--;
                drawCount++;

                if (playerDelta >= Settings.PLAYER_MOVE_SPEED) {
                    playerDelta -= Settings.PLAYER_MOVE_SPEED;
                }

                if (diceDelta >= Settings.DICE_DELAY) {
                    diceDelta -= Settings.DICE_DELAY;
                }
            }     
            
            // Displays FPS.
            if (timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
    * Checks to make sure that a profile that a user creates does not match the name of any other
    * profiles that have already been created.
    */
    public boolean isUniqueProfileName(String name, ArrayList<Profile> profiles) {
        for (int i = 0; i < profiles.size(); i++) {
            if (name.equals(profiles.get(i).name)) {
                return false;
            }
        }

        return true;
    }

    /**
    * Creates a profile and adds it to the profiles ArrayList. The ArrayList will be saved each time
    * a new profile is added so the ArrayList can be loaded when the program is run again.
    */
    public void createProfile(String name, int profileNameSeperator) throws IOException {
        if (ui.profileTextField.getText().isEmpty() == false) {
            if (isUniqueProfileName(name, profiles) == true && name.equals("None") == false) {
                if (profiles.isEmpty() == true) {
                    profiles.add(new Profile(name, 70, 170));
                } else {
                    profiles.add(new Profile(name, 
                                            70, 
                                            profiles.get(profiles.size() - 1).nameYcoordinate 
                                            + profileNameSeperator));
                }
                saveLoad.saveProfiles(profiles, 
                                      new FileOutputStream("src/saved-data/Profiles.txt"));
                if (ui.lastScreenIsSetUp == true) {
                    if (ui.selectedPlayerBox != -1) {
                        if (ui.selectedProfiles[ui.selectedPlayerBox] != null) {
                            ui.selectedProfilesTemp[ui.selectedPlayerBox] 
                                = ui.selectedProfiles[ui.selectedPlayerBox];
                        }
                        ui.getAvailableProfiles(ui.setUpScreenProfileList, ui.selectedProfiles);
                    }
                    if (ui.setUpReady() == true) {
                        ui.startGameButton.setForeground(Color.white);
                    } else {
                        ui.startGameButton.setForeground(new Color(255, 255, 255, 75));
                    }
                    GameStates.currentGameState = GameStates.SET_UP_STATE;
                } else {
                    GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
                }
            } else {
                GameStates.currentGameState = GameStates.PROFILE_ERROR_STATE;
            }
        }
    }

    /**
    * Creates the amount of players given by the users.
    */
    public void createPlayers() {
        int coordinateOffset = 10;
        playerAmount = ui.playerAmount;
            
        for (int i = 0; i < playerAmount; i++) {
            Player player = new Player(this, spaceData, ui, dice, i + 1, 
                                       coordinateOffset, ui.selectedProfiles[i], ui.selectedTokens[i]);
            players.add(player);
            coordinateOffset -= 5;
        }  

        // players.add(new Player(this, spaceData, ui, dice, 1, 10, "Test", ui.selectedTokens[0]));
        
        // players.add(new Player(this, spaceData, ui, dice, 2, 0, "Test1", ui.selectedTokens[1]));
        
        // players.add(new Player(this, spaceData, ui, dice, 3, -10, "Test2", ui.selectedTokens[2]));
        
        // players.add(new Player(this, spaceData, ui, dice, 4, -20, "Test3", ui.selectedTokens[3]));
        
        currentPlayerNumber = 1;
        currentPlayer = players.get(currentPlayerNumber - 1);
        playerInfoBox.setCurrentPlayer();
    }

    /**
    * Changes the player number when a player ends their turn.
    */
    public void changePlayerNumber() {
        if (currentPlayerNumber < playerAmount) {
            currentPlayerNumber++;
        } else {
            currentPlayerNumber = 1;
        }

        currentPlayer = players.get(currentPlayerNumber - 1);

        playerInfoBox.setCurrentPlayer();
    }

    /**
    * Sets up turn for next player after a player delcares bankruptcy.
    */
    public void setUpNextTurn() {
        if (dice.doubles == false || currentPlayer.isInJail == true) {
            currentPlayer.doublesStreak = 0;
            changePlayerNumber();
        }

        dice.result = 0;
        dice.doubles = false;

        if (players.get(currentPlayerNumber - 1).isInJail == true) {
            players.get(currentPlayerNumber - 1).turnsInJail++;
            ui.rollButton.setForeground(new Color(255, 255, 255, 75));
            ui.managerButton.setForeground(Color.white);
            ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
            if (currentPlayer.money >= Settings.JAIL_BAIL) {
                ui.jailPayBailButton.setForeground(Color.white);
            } else {
                ui.jailPayBailButton.setForeground(new Color(255, 255, 255, 75));
            }
            GameStates.currentGameState = GameStates.SPACE_EVENT_STATE;
        } else {
            ui.rollButton.setForeground(Color.white);
            ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
            GameStates.currentGameState = GameStates.ROLL_STATE;
        }
    }
    
    /**
    * Returns all property of a removed player to the bank.
    */
    public void returnPropertyToBank() {
        for (int i = 0; i < spaceData.NUM_OF_NORMAL_PROPERTIES; i++) {
            if (spaceData.normalProperties.get(i).owner == currentPlayer) {
                spaceData.normalProperties.get(i).owner = null;
            }
        }

        for (int i = 0; i < spaceData.NUM_OF_RAILROAD_PROPERTIES; i++) {
            if (spaceData.railroadProperties.get(i).owner == currentPlayer) {
                spaceData.railroadProperties.get(i).owner = null;
            }
        }

        for (int i = 0; i < spaceData.NUM_OF_UTILITY_PROPERTIES; i++) {
            if (spaceData.utilityProperties.get(i).owner == currentPlayer) {
                spaceData.utilityProperties.get(i).owner = null;
            }
        }
    }

    /**
    * Removes a player from the game after they declare bankruptcy.
    */
    public void removePlayer() {
        returnPropertyToBank();
        setUpNextTurn();

        if (currentPlayerNumber == 1) {
            System.out.print("remove test");
            players.remove(playerAmount  - 1);
        } else {
            System.out.print("Player number removed: ");
            System.out.print(currentPlayerNumber - 2);
            players.remove(currentPlayerNumber - 2);
        }
        // playerInfoBox.setCurrentPlayer();
        playerAmount -= 1;

        if (playerAmount <= 1) {
            returnPropertyToBank();
            GameStates.currentGameState = GameStates.GAME_OVER_STATE;
        }
    }

    /**
    * Draws the players on screen, with the order of which player is drawn based on whose turn it
    * currently is.
    */
    public void drawPlayers(Graphics2D g2d) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).draw(g2d);
        }
        // int playerIndex = -1;

        // for (int i = 0; i < players.size(); i++) {
        //     if (currentPlayerNumber - 1 + playerIndex > -1) {
        //         players.get(currentPlayerNumber - 1 + playerIndex).draw(g2d);
        //         playerIndex--;
        //     } else {
        //         playerIndex = players.size() - 1;
        //         players.get(playerIndex).draw(g2d);
        //         playerIndex -= (players.size() - 1);
        //     }
        // }
    }

    /**
    * Updates game information.
    */
    public void update() {
        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
        } else if (GameStates.currentGameState == GameStates.DICE_DELAY_STATE) {
            dice.delay();
        } else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
            players.get(currentPlayerNumber - 1).move();
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            spaceEvent.chooseEvent();
        }
    }

    /**
    * Draws the updated game data on screen.
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Converts the Graphics object parameter to a Graphics2D object.
        Graphics2D g2d = (Graphics2D) g;

        // RenderingHints rh = new RenderingHints(
        //     RenderingHints.KEY_ANTIALIASING,
        //     RenderingHints.VALUE_ANTIALIAS_ON);
        // g2d.setRenderingHints(rh);

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                           RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                           RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.white);

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            // mainMenu.draw(g2d);
            backgrounds.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.SET_UP_STATE) {
            backgrounds.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.CREATE_PROFILE_STATE) {
            backgrounds.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.PROFILE_ERROR_STATE) {
            backgrounds.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.PROFILE_SCREEN_STATE) {
            backgrounds.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            backgrounds.draw(g2d);
            board.draw(g2d);
            ui.draw(g2d);

            drawPlayers(g2d);

            playerInfoBox.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.DICE_DELAY_STATE) {
            backgrounds.draw(g2d);
            board.draw(g2d);
            ui.draw(g2d);

            drawPlayers(g2d);
            playerInfoBox.draw(g2d);
            dice.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.PLAYER_MOVE_STATE) {
            backgrounds.draw(g2d);
            board.draw(g2d);
            ui.draw(g2d);

            drawPlayers(g2d);

            playerInfoBox.draw(g2d);
            dice.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            backgrounds.draw(g2d);
            board.draw(g2d);
            ui.draw(g2d);

            drawPlayers(g2d);

            playerInfoBox.draw(g2d);

            if (dice.result != 0) {
                dice.draw(g2d);
            }

            spaceEvent.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            backgrounds.draw(g2d);
            board.draw(g2d);
            ui.draw(g2d);

            drawPlayers(g2d);

            playerInfoBox.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
            backgrounds.draw(g2d);
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE) {
            backgrounds.draw(g2d);
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
            backgrounds.draw(g2d);
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.TRADING_CREATE_STATE) {
            backgrounds.draw(g2d);
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.TRADING_OFFER_STATE) {
            backgrounds.draw(g2d);
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.BUILDING_MENU_STATE) {
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
            backgrounds.draw(g2d);
            playerManager.draw(g2d);
            ui.draw(g2d);
        } else if (GameStates.currentGameState == GameStates.GAME_OVER_STATE) {
            backgrounds.draw(g2d);
            board.draw(g2d);
            ui.draw(g2d);

            drawPlayers(g2d);

            playerInfoBox.draw(g2d);

            HelperFunctions.drawText(g2d, players.get(0).name + " has won!", fonts.pixeloidSans, Color.black, 22, 200, 375, 400, 50, true, true);
        }
    }
}
