package src.actionhandler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import src.data.GameStates;
import src.data.Settings;
import src.data.SpaceData;
import src.game.Dice;
import src.game.GamePanel;
import src.game.Player;
import src.game.Profile;
import src.game.Ui;

/**
 * Processes user input from the mouse.
 */
public class MouseHandler implements MouseListener {
    public GamePanel gamePanel;
    public SpaceData spaceData;
    public Ui ui;
    public Dice dice;
    public ArrayList<Profile> profiles;
    public ArrayList<Player> players;

    Player currentPlayer;
    JLabel currentLabel;

    /**
    * Constructor.
    */
    public MouseHandler(GamePanel gamePanel, SpaceData spaceData, Ui ui, Dice dice, 
                        ArrayList<Profile> profiles, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.ui = ui;
        this.dice = dice;
        this.profiles = profiles;
        this.players = players;
    }

    /**
    * Processes input when the mouse is clicked.
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Action listener test");
        currentLabel = (JLabel) e.getSource();

        if (GameStates.currentGameState == GameStates.TITLE_SCREEN_STATE) {
            if (ui.setUpReady() == true) {
                ui.startGameButton.setForeground(Color.white);
            } else {
                ui.startGameButton.setForeground(new Color(255, 255, 255, 75));
            }
            
            if (currentLabel == ui.startButton) {
                if (ui.selectedPlayerBox != -1) {
                    if (ui.selectedProfiles[ui.selectedPlayerBox] != null) {
                        ui.selectedProfilesTemp[ui.selectedPlayerBox] 
                            = ui.selectedProfiles[ui.selectedPlayerBox];
                    }
                    ui.getAvailableProfiles(ui.setUpScreenProfileList, ui.selectedProfiles);
                    ui.getAvailableTokens();
                }
                GameStates.currentGameState = GameStates.SET_UP_STATE;
            } else if (currentLabel == ui.profilesButton) {
                ui.lastScreenIsSetUp = false;
                if (gamePanel.profiles.isEmpty() == true) {
                    GameStates.currentGameState = GameStates.CREATE_PROFILE_STATE;
                } else {
                    GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
                }
            }
        } else if (GameStates.currentGameState == GameStates.SET_UP_STATE) {            
            if (currentLabel == ui.playerTokenLeftArrowLabel) {
                if (ui.availableTokensIndex == 0) {
                    ui.availableTokensIndex = ui.availableTokens.size() - 1;
                } else {
                    ui.availableTokensIndex -= 1;
                }
                ui.selectedTokens[ui.selectedPlayerBox]
                    = ui.availableTokens.get(ui.availableTokensIndex);
            } else if (currentLabel == ui.playerTokenRightArrowLabel) {
                if (ui.availableTokensIndex == ui.availableTokens.size() - 1) {
                    ui.availableTokensIndex = 0;
                } else {
                    ui.availableTokensIndex += 1;
                }
                ui.selectedTokens[ui.selectedPlayerBox] 
                    = ui.availableTokens.get(ui.availableTokensIndex);
            } else if (currentLabel == ui.addPlayerBoxLabel) {
                ui.playerAmount = ui.playerAmount + 1;
                if (ui.playerAmount >= 6) {
                    ui.hoveringAddPlayerBox = false;
                }
                ui.selectedPlayerBox = ui.playerAmount - 1;
                if (ui.selectedProfiles[ui.selectedPlayerBox] != null) {
                    ui.selectedProfilesTemp[ui.selectedPlayerBox] 
                        = ui.selectedProfiles[ui.selectedPlayerBox];
                }
                ui.getAvailableProfiles(ui.setUpScreenProfileList, ui.selectedProfiles);
                ui.getAvailableTokens();
            } else if (currentLabel == ui.menuBackButton) {
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            } else if (currentLabel == ui.setUpScreenCreateProfileButton) {
                ui.lastScreenIsSetUp = true;
                if (profiles.size() >= (Settings.MAX_NUMBER_PROFILES)) {
                    GameStates.currentGameState = GameStates.PROFILE_ERROR_STATE;
                } else {
                    GameStates.currentGameState = GameStates.CREATE_PROFILE_STATE;
                }
            } else if (currentLabel == ui.startGameButton) {
                if (ui.setUpReady() == true) {
                    gamePanel.createPlayers();
                    ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
                    GameStates.currentGameState = GameStates.ROLL_STATE;
                }
            } else {
                for (int i = 0; i < ui.playerBoxLabels.length; i++) {
                    if (currentLabel == ui.playerBoxLabels[i]) {
                        ui.selectedPlayerBox = i;
                        if (ui.selectedProfiles[ui.selectedPlayerBox] != null) {
                            ui.selectedProfilesTemp[ui.selectedPlayerBox] 
                                = ui.selectedProfiles[ui.selectedPlayerBox];
                        }
                        ui.getAvailableProfiles(ui.setUpScreenProfileList, ui.selectedProfiles);
                        ui.getAvailableTokens();
                    }
                }

                if (ui.playerAmount > 2) {
                    for (int i = 0; i < ui.removePlayerBoxLabels.length; i++) {
                        if (currentLabel == ui.removePlayerBoxLabels[i]) {
                            ui.selectedProfiles[ui.playerAmount - 1] = null;
                            ui.selectedProfilesTemp[ui.playerAmount - 1] = null;
                            ui.selectedTokens[ui.playerAmount - 1] = null;
                            ui.playerAmount = ui.playerAmount - 1;
                            ui.playerBoxLabels[ui.playerAmount].setVisible(false);
                            ui.removePlayerBoxLabels[ui.playerAmount - 2].setVisible(false);
                            ui.selectedPlayerBox = -1;

                            if (i == ui.playerAmount - 2) {
                                ui.hoveredRemovePlayerButton = -1;
                            }
                        }
                    }
                }
            }

            if (ui.setUpReady() == true) {
                ui.startGameButton.setForeground(Color.white);
            } else {
                ui.startGameButton.setForeground(new Color(255, 255, 255, 75));
            }
        } else if (GameStates.currentGameState == GameStates.CREATE_PROFILE_STATE) {
            if (currentLabel == ui.profilesScreenCancelButton) {
                if (gamePanel.profiles.isEmpty() == true) {
                    ui.profileTextField.setText(null);
                    if (ui.lastScreenIsSetUp == true) {
                        GameStates.currentGameState = GameStates.SET_UP_STATE;
                    } else {
                        GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
                    }
                } else {
                    ui.profileTextField.setText(null);
                    if (ui.lastScreenIsSetUp == true) {
                        GameStates.currentGameState = GameStates.SET_UP_STATE;
                    } else {
                        GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
                    }
                }
            } else if (currentLabel == ui.profilesScreenEnterButton) {
                try {
                    gamePanel.createProfile(ui.profileTextField.getText(), ui.profileNameSeperator);
                    ui.profileTextField.setText(null);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (currentLabel == ui.menuBackButton) {
                ui.profileTextField.setText(null);
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.PROFILE_ERROR_STATE) {
            if (currentLabel == ui.profilesScreenMaxBackButton) {
                if (ui.lastScreenIsSetUp == true) {
                    GameStates.currentGameState = GameStates.SET_UP_STATE;
                } else {
                    GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
                }
            } else if (currentLabel == ui.profilesScreenCancelButton) {
                if (gamePanel.profiles.isEmpty() == true) {
                    ui.profileTextField.setText(null);
                    if (ui.lastScreenIsSetUp == true) {
                        GameStates.currentGameState = GameStates.SET_UP_STATE;
                    } else {
                        GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
                    }
                } else {
                    ui.profileTextField.setText(null);
                    if (ui.lastScreenIsSetUp == true) {
                        GameStates.currentGameState = GameStates.SET_UP_STATE;
                    } else {
                        GameStates.currentGameState = GameStates.PROFILE_SCREEN_STATE;
                    }
                }
            } else if (currentLabel == ui.profilesScreenEnterButton) {
                try {
                    gamePanel.createProfile(ui.profileTextField.getText(), ui.profileNameSeperator);
                    ui.profileTextField.setText(null);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (currentLabel == ui.menuBackButton) {
                ui.profileTextField.setText(null);
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.PROFILE_SCREEN_STATE) {
            if (currentLabel == ui.menuBackButton) {
                GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;
            } else if (currentLabel == ui.profilesScreenCreateButton) {
                if (profiles.size() >= (Settings.MAX_NUMBER_PROFILES)) {
                    GameStates.currentGameState = GameStates.PROFILE_ERROR_STATE;
                } else {
                    GameStates.currentGameState = GameStates.CREATE_PROFILE_STATE;
                }
            }
        } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
            currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
            if (currentLabel == ui.rollButton) {
                dice.getDiceResult();
                currentPlayer.spacesLeftToMove = dice.result;
                ui.rollButton.setForeground(new Color(255, 255, 255, 75));
                ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                GameStates.currentGameState = GameStates.DICE_DELAY_STATE;
            } else if (currentLabel == ui.managerButton) {
                ui.managerButton.setForeground(Color.white);
                GameStates.previousGameState = GameStates.currentGameState;
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);

            if (currentLabel == ui.managerButton) {
                ui.managerButton.setForeground(Color.white);
                GameStates.previousGameState = GameStates.currentGameState;
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            } else if (currentLabel == ui.okButton) {
                if (currentPlayer.doublesStreak >= 3) {
                    currentPlayer.moveToJail();
                    ui.managerButton.setForeground(Color.white);
                    ui.nextTurnButton.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                } else if (spaceData.currentSpaceType == "Go") {
                    if (currentPlayer.spacesLeftToMove == 0) {
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    } else {
                        ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                        GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                    }
                } else if (spaceData.currentSpaceType == spaceData.jail.type) {
                    if (currentPlayer.isInJail == true) {
                        if (dice.doubles == true) {
                            currentPlayer.isInJail = false;
                            currentPlayer.turnsInJail = 0;
                            dice.doubles = false;
                            ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                            GameStates.currentGameState = GameStates.DICE_DELAY_STATE;
                        } else if (currentPlayer.turnsInJail >= 3) {
                            currentPlayer.isInJail = false;
                            currentPlayer.turnsInJail = 0;
                            currentPlayer.money -= Settings.JAIL_BAIL;
                            ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                            GameStates.currentGameState = GameStates.DICE_DELAY_STATE;
                        } else {
                            ui.managerButton.setForeground(Color.white);
                            ui.nextTurnButton.setForeground(Color.white);
                            GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                        }
                    } else {
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                } else if (spaceData.currentSpaceType == spaceData.goToJail.type) {
                    currentPlayer.moveToJail();
                    ui.managerButton.setForeground(Color.white);
                    ui.nextTurnButton.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                } else {
                    ui.managerButton.setForeground(Color.white);
                    ui.nextTurnButton.setForeground(Color.white);
                    ui.buyOptionText.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
                ui.okButton.setForeground(Color.white);
            } else if (currentLabel == ui.buyOptionText) {
                if (spaceData.currentSpaceType == "Normal Property") {
                    if (currentPlayer.money >= spaceData.currentNormalProperty.price) {
                        spaceData.currentNormalProperty.owner = currentPlayer;
                        currentPlayer.money -= spaceData.currentNormalProperty.price;
                        spaceData.setRent();
                        System.out.println("Property bought");

                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.buyOptionText.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }                    
                } else if (spaceData.currentSpaceType == "Railroad") {
                    if (currentPlayer.money >= spaceData.currentRailroad.price) {
                        spaceData.currentRailroad.owner = currentPlayer;
                        currentPlayer.money -= spaceData.currentRailroad.price;
                        spaceData.setRent();
                        System.out.println("Property bought");

                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.buyOptionText.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                } else if (spaceData.currentSpaceType == "Utility") {
                    if (currentPlayer.money >= spaceData.currentUtility.price) {
                        spaceData.currentUtility.owner = currentPlayer;
                        currentPlayer.money -= spaceData.currentUtility.price;
                        spaceData.setRent();
                        System.out.println("Property bought");

                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.buyOptionText.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                }
            } else if (currentLabel == ui.passOptionText) {
                System.out.println("Auction");
                
                ui.managerButton.setForeground(Color.white);
                ui.nextTurnButton.setForeground(Color.white);
                ui.buyOptionText.setForeground(Color.white);
                ui.passOptionText.setForeground(Color.white);
                ui.buyOptionText.setVisible(false);
                ui.passOptionText.setVisible(false);
                GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
            } else if (currentLabel == ui.payButton) {
                if (spaceData.currentSpaceType == "Normal Property") {
                    if (currentPlayer.money >= spaceData.currentNormalProperty.currentRent) {
                        currentPlayer.money -= spaceData.currentNormalProperty.currentRent;
                        spaceData.currentNormalProperty.owner.money 
                            += spaceData.currentNormalProperty.currentRent;
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.payButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                } else if (spaceData.currentSpaceType == "Railroad") {
                    if (currentPlayer.money >= spaceData.currentRailroad.currentRent) {
                        currentPlayer.money -= spaceData.currentRailroad.currentRent;
                        spaceData.currentRailroad.owner.money 
                            += spaceData.currentRailroad.currentRent;
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.payButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                } else if (spaceData.currentSpaceType == "Utility") {
                    if (currentPlayer.money 
                        >= spaceData.currentUtility.currentMultiplier * dice.result) {
                        currentPlayer.money 
                            -= spaceData.currentUtility.currentMultiplier * dice.result;
                        spaceData.currentUtility.owner.money 
                            += spaceData.currentUtility.currentMultiplier * dice.result;
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.payButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                } else if (spaceData.currentSpaceType == "Tax") {
                    if (currentPlayer.money >= spaceData.currentTaxSpace.fee) {
                        currentPlayer.money -= spaceData.currentTaxSpace.fee;
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.payButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                }
            } else if (currentLabel == ui.jailRollButton) {
                dice.getDiceResult();
                currentPlayer.spacesLeftToMove = dice.result;
                ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                ui.jailRollButton.setForeground(Color.white);
                ui.jailRollButton.setVisible(false);
                ui.jailPayBailButton.setVisible(false);
                ui.jailUseCardButton.setVisible(false);
                GameStates.currentGameState = GameStates.DICE_DELAY_STATE;
            } else if (currentLabel == ui.jailPayBailButton) {
                if (currentPlayer.money >= Settings.JAIL_BAIL) {
                    currentPlayer.isInJail = false;
                    currentPlayer.turnsInJail = 0;
                    currentPlayer.money -= Settings.JAIL_BAIL;
                    currentPlayer.coordinates = 
                        spaceData.getSpaceCoordinates(currentPlayer.currentSpaceNumber, 
                                                      currentPlayer.coordinateOffset);
                    currentPlayer.xcoordinate = currentPlayer.coordinates[0];
                    currentPlayer.ycoordinate = currentPlayer.coordinates[1];
                    ui.jailPayBailButton.setForeground(Color.white);
                    ui.rollButton.setForeground(Color.white);
                    ui.managerButton.setForeground(Color.white);
                    ui.jailRollButton.setVisible(false);
                    ui.jailPayBailButton.setVisible(false);
                    ui.jailUseCardButton.setVisible(false);
                    GameStates.currentGameState = GameStates.ROLL_STATE;
                }
            }
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);

            if (currentLabel == ui.managerButton) {
                ui.managerButton.setForeground(Color.white);
                GameStates.previousGameState = GameStates.currentGameState;
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            } else if (currentLabel == ui.nextTurnButton) {
                if (dice.doubles == false || currentPlayer.isInJail == true) {
                    currentPlayer.doublesStreak = 0;
                    gamePanel.changePlayerNumber();
                }

                dice.result = 0;
                dice.doubles = false;

                if (players.get(gamePanel.currentPlayerNumber - 1).isInJail == true) {
                    players.get(gamePanel.currentPlayerNumber - 1).turnsInJail++;
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
        } else if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
            if (currentLabel == ui.managerTradingButton) {
                ui.managerTradingButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.TRADING_PLAYER_SELECT_STATE;
            } else if (currentLabel == ui.managerBackButton) {
                ui.managerBackButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.previousGameState;
            }
        } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
            if (currentLabel == ui.managerBackButton) {
                ui.managerBackButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
    * Processes input when the mouse is hovering over an area.
    */
    @Override
    public void mouseEntered(MouseEvent e) {
        currentLabel = (JLabel) e.getSource();

        if (currentLabel == ui.startButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.profilesButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.optionsButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.addPlayerBoxLabel) {
            ui.hoveringAddPlayerBox = true;
        } else if (currentLabel == ui.menuBackButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.setUpScreenCreateProfileButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.startGameButton) {
            if (ui.setUpReady() == true) {
                currentLabel.setForeground(new Color(153, 235, 255));
            }
        } else if (currentLabel == ui.profilesScreenCancelButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.profilesScreenEnterButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.profilesScreenCreateButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else if (currentLabel == ui.profilesScreenMaxBackButton) {
            currentLabel.setForeground(new Color(153, 235, 255));
        } else {
            if (GameStates.currentGameState == GameStates.SET_UP_STATE) {
                for (int i = 0; i < ui.playerBoxLabels.length; i++) {
                    if (currentLabel == ui.playerBoxLabels[i]) {
                        ui.hoveredPlayerBox = i;
                    }
                }
                if (ui.playerAmount >= 2) {
                    for (int i = 0; i < ui.removePlayerBoxLabels.length; i++) {
                        if (currentLabel == ui.removePlayerBoxLabels[i]) {
                            ui.hoveredRemovePlayerButton = i;
                        }
                    }
                }
            } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
                currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
                if (currentLabel == ui.rollButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.okButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.buyOptionText) {
                    if (spaceData.currentSpaceType == "Normal Property") {
                        if (currentPlayer.money >= spaceData.currentNormalProperty.price) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Railroad") {
                        if (currentPlayer.money >= spaceData.currentRailroad.price) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Utility") {
                        if (currentPlayer.money >= spaceData.currentUtility.price) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    }
                } else if (currentLabel == ui.passOptionText) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.payButton) {
                    if (spaceData.currentSpaceType == "Normal Property") {
                        if (currentPlayer.money >= spaceData.currentNormalProperty.currentRent) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Railroad") {
                        if (currentPlayer.money >= spaceData.currentRailroad.currentRent) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Utility") {
                        if (currentPlayer.money 
                            >= spaceData.currentUtility.currentMultiplier * dice.result) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Tax") {
                        if (gamePanel.currentPlayer.money >= spaceData.currentTaxSpace.fee) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    }
                } else if (currentLabel == ui.jailRollButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.jailPayBailButton) {
                    if (currentPlayer.money >= Settings.JAIL_BAIL) {
                        currentLabel.setForeground(new Color(153, 235, 255));
                    }
                } else if (currentLabel == ui.jailUseCardButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
                currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.nextTurnButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
                if (currentLabel == ui.managerMortgagingButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerTradingButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerBuildingButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerBankruptcyButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
                if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            }
        }
    }

    /**
    * Processes input when a mouse has stopped hovering over an area.
    */
    @Override
    public void mouseExited(MouseEvent e) {
        currentLabel = (JLabel) e.getSource();

        if (currentLabel == ui.startButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.profilesButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.optionsButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.addPlayerBoxLabel) {
            ui.hoveringAddPlayerBox = false;
        } else if (currentLabel == ui.menuBackButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.setUpScreenCreateProfileButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.startGameButton) {
            if (ui.setUpReady() == true) {
                currentLabel.setForeground(Color.white);
            }
        } else if (currentLabel == ui.profilesScreenCancelButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.profilesScreenEnterButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.profilesScreenCreateButton) {
            currentLabel.setForeground(Color.white);
        } else if (currentLabel == ui.profilesScreenMaxBackButton) {
            currentLabel.setForeground(Color.white);
        } else {
            if (GameStates.currentGameState == GameStates.SET_UP_STATE) {
                for (int i = 0; i < ui.playerBoxLabels.length; i++) {
                    if (currentLabel == ui.playerBoxLabels[i]) {
                        ui.hoveredPlayerBox = -1;
                    }
                }
                if (ui.playerAmount >= 2) {
                    for (int i = 0; i < ui.removePlayerBoxLabels.length; i++) {
                        if (currentLabel == ui.removePlayerBoxLabels[i]) {
                            ui.hoveredRemovePlayerButton = -1;
                        }
                    }
                }
            } else if (GameStates.currentGameState == GameStates.ROLL_STATE) {
                currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
                if (currentLabel == ui.rollButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.okButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.buyOptionText) {
                    if (spaceData.currentSpaceType == "Normal Property") {
                        if (currentPlayer.money >= spaceData.currentNormalProperty.price) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Railroad") {
                        if (currentPlayer.money >= spaceData.currentRailroad.price) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Utility") {
                        if (currentPlayer.money >= spaceData.currentUtility.price) {
                            currentLabel.setForeground(Color.white);
                        }
                    }
                } else if (currentLabel == ui.passOptionText) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.payButton) {
                    if (spaceData.currentSpaceType == "Normal Property") {
                        if (currentPlayer.money >= spaceData.currentNormalProperty.currentRent) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Railroad") {
                        if (currentPlayer.money >= spaceData.currentRailroad.currentRent) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Utility") {
                        if (currentPlayer.money 
                            >= spaceData.currentUtility.currentMultiplier * dice.result) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Tax") {
                        if (gamePanel.currentPlayer.money >= spaceData.currentTaxSpace.fee) {
                            currentLabel.setForeground(Color.white);
                        }
                    }
                } else if (currentLabel == ui.jailRollButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.jailPayBailButton) {
                    if (currentPlayer.money >= Settings.JAIL_BAIL) {
                        currentLabel.setForeground(Color.white);
                    }
                } else if (currentLabel == ui.jailUseCardButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
                currentPlayer = players.get(gamePanel.currentPlayerNumber - 1);
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.nextTurnButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.MANAGER_MENU_STATE) {
                if (currentLabel == ui.managerMortgagingButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerTradingButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerBuildingButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerBankruptcyButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
                if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(Color.white);
                }
            }
        }
    }
}
