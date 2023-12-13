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
import src.game.PlayerManager;
import src.game.Profile;
import src.game.Ui;

/**
 * Processes user input from the mouse.
 */
public class MouseHandler implements MouseListener {
    public GamePanel gamePanel;
    public SpaceData spaceData;
    public PlayerManager playerManager;
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
                        PlayerManager playerManager, 
                        ArrayList<Profile> profiles, ArrayList<Player> players) {
        this.gamePanel = gamePanel;
        this.spaceData = spaceData;
        this.ui = ui;
        this.dice = dice;
        this.playerManager = playerManager;
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
                    // ui.selectedProfiles[ui.playerAmount - 1] = null;
                    // ui.selectedProfilesTemp[ui.playerAmount - 1] = null;
                    // ui.selectedTokens[ui.playerAmount - 1] = null;
                    // ui.playerAmount = ui.playerAmount - 1;
                    // ui.playerBoxLabels[ui.playerAmount].setVisible(false);
                    // ui.removePlayerBoxLabels[ui.playerAmount - 2].setVisible(false);
                    // ui.selectedPlayerBox = -1;

                    // // if (i == ui.playerAmount - 2) {
                    // //     ui.hoveredRemovePlayerButton = -1;
                    // // }
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
            if (currentLabel == ui.managerMortgagingButton) {
                playerManager.getCurrentPlayerProperties();
                if (playerManager.currentPlayerPropertyAmount > 0) {
                    ui.mortgagingSelectedProperty = 0;
                    if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == false) {
                        ui.mortgageButton.setForeground(Color.white);
                        ui.unMortgageButton.setForeground(new Color(255, 255, 255, 75));
                    } else {
                        ui.mortgageButton.setForeground(new Color(255, 255, 255, 75));
                        ui.unMortgageButton.setForeground(Color.white);
                    }
                } else {
                    ui.mortgagingSelectedProperty = -1;
                }

                ui.managerMortgagingButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.MORTGAGING_MENU_STATE;
            } else if (currentLabel == ui.managerTradingButton) {
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i) != gamePanel.currentPlayer) {
                        ui.playerTradingList.addItem(players.get(i).name);
                    }
                }
                ui.managerTradingButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.TRADING_PLAYER_SELECT_STATE;
            } else if (currentLabel == ui.managerBuildingButton) {
                ui.managerBuildingButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.BUILDING_MENU_STATE;
            } else if (currentLabel == ui.managerBankruptcyButton) {
                ui.managerBankruptcyButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.DECLARE_BANKRUPTCY_STATE;
            } else if (currentLabel == ui.managerBackButton) {
                ui.managerBackButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.previousGameState;
            }
        } else if (GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE) {
            if (currentLabel == ui.mortgageButton) {
                if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == false) {
                    ui.mortgageProperty(ui.mortgagingProperties[ui.mortgagingSelectedProperty]);
                    ui.mortgageButton.setForeground(new Color(255, 255, 255, 75));
                    ui.unMortgageButton.setForeground(Color.white);
                }
            } else if (currentLabel == ui.unMortgageButton) {
                if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == true
                    && ui.canPlayerUnmortgage(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == true) {
                    ui.unMortgageProperty(ui.mortgagingProperties[ui.mortgagingSelectedProperty]);
                    ui.mortgageButton.setForeground(Color.white);
                    ui.unMortgageButton.setForeground(new Color(255, 255, 255, 75));
                }
            } else if (currentLabel == ui.managerBackButton) {
                ui.clearCurrentPlayerProperties();
                ui.managerBackButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            } else {
                for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                    if (currentLabel == ui.mortgagingProperties[i].boxLabel) {
                        ui.mortgagingSelectedProperty = i;

                        if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == false) {
                            ui.mortgageButton.setForeground(Color.white);
                            ui.unMortgageButton.setForeground(new Color(255, 255, 255, 75));
                        } else {
                            ui.mortgageButton.setForeground(new Color(255, 255, 255, 75));
                            ui.unMortgageButton.setForeground(Color.white);
                        }
                    }
                }
            }
        } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
            if (currentLabel == ui.startTradingButton) {
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).name == ui.playerTradingList.getSelectedItem()) {
                        playerManager.playerTradingWith = players.get(i);
                        break;
                    }
                }

                playerManager.getCurrentPlayerProperties();
                playerManager.getPlayerTradingWithProperties();

                if (ui.canOfferTrade() == true) {
                    ui.offerTradeButton.setForeground(Color.white);
                } else {
                    ui.offerTradeButton.setForeground(new Color(255, 255, 255, 75));
                }
                ui.startTradingButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.TRADING_CREATE_STATE;
            } else if (currentLabel == ui.managerBackButton) {
                ui.playerTradingList.removeAllItems();
                ui.managerBackButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.TRADING_CREATE_STATE) {
            if (currentLabel == ui.managerBackButton) {
                ui.clearCurrentPlayerProperties();
                ui.clearPlayerTradingWithProperties();
                // playerManager.clearSelectedProperties();
                // ui.createTradingMenuPropertyBoxes();
                ui.managerBackButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.TRADING_PLAYER_SELECT_STATE;
            } else if (currentLabel == ui.offerTradeButton) {
                if (ui.canOfferTrade() == true) {
                    ui.offerTradeButton.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.TRADING_OFFER_STATE;
                }
            } else {
                for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                    if (currentLabel == ui.tradingCurrentPlayerProperties[i].boxLabel) {
                        if (ui.tradingCurrentPlayerProperties[i].selected == false) {
                            ui.tradingCurrentPlayerProperties[i].selected = true;
                        // playerManager.addPropertyToCurrentPlayerTradingList(i);
                        // ui.tradingMenuSelectedCurrentPlayerProperties.add(i);
                        } else {
                            ui.tradingCurrentPlayerProperties[i].selected = false;
                        }
                        if (ui.canOfferTrade() == true) {
                            ui.offerTradeButton.setForeground(Color.white);
                        } else {
                            ui.offerTradeButton.setForeground(new Color(255, 255, 255, 75));
                        }
                    }
                }

                for (int i = 0; i < playerManager.playerTradingWithPropertyAmount; i++) {
                    if (currentLabel == ui.tradingPlayerTradingWithProperties[i].boxLabel) {
                        if (ui.tradingPlayerTradingWithProperties[i].selected == false) {
                            ui.tradingPlayerTradingWithProperties[i].selected = true;
                        } else {
                            ui.tradingPlayerTradingWithProperties[i].selected = false;
                        }
                        // playerManager.addPropertyToPlayerTradingWithTradingList(i);
                        // ui.tradingMenuSelectedPlayerTradingWithProperties.add(i);
                        if (ui.canOfferTrade() == true) {
                            ui.offerTradeButton.setForeground(Color.white);
                        } else {
                            ui.offerTradeButton.setForeground(new Color(255, 255, 255, 75));
                        }
                    }
                }
            }
        } else if (GameStates.currentGameState == GameStates.TRADING_OFFER_STATE) {
            if (currentLabel == ui.declineTradeButton) {
                ui.declineTradeButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.TRADING_CREATE_STATE;
            } else if (currentLabel == ui.acceptTradeButton) {
                ui.trade();
                spaceData.setRentForAllProperties();
                ui.playerTradingList.removeAllItems();
                ui.clearCurrentPlayerProperties();
                ui.clearPlayerTradingWithProperties();
                ui.acceptTradeButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
            if (currentLabel == ui.bankruptcyNoButton) {
                ui.bankruptcyNoButton.setForeground(Color.white);
                GameStates.currentGameState = GameStates.MANAGER_MENU_STATE;
            } else if (currentLabel == ui.bankruptcyYesButton) {
                ui.bankruptcyYesButton.setForeground(Color.white);
                ui.bankruptcyNoButton.setVisible(false);
                ui.bankruptcyYesButton.setVisible(false);
                gamePanel.removePlayer();
            }
        } else if (GameStates.currentGameState == GameStates.GAME_OVER_STATE) {
            gamePanel.players.clear();
            ui.backtoMainMenuButton.setForeground(Color.white);
            GameStates.currentGameState = GameStates.TITLE_SCREEN_STATE;   
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
            } else if (GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE) {
                if (currentLabel == ui.mortgageButton) {
                    if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == false) {
                        ui.mortgageButton.setForeground(new Color(153, 235, 255));
                    }
                } else if (currentLabel == ui.unMortgageButton) {
                    if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == true) {
                        ui.unMortgageButton.setForeground(new Color(153, 235, 255));
                    }
                } else if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else {
                    for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                        if (currentLabel == ui.mortgagingProperties[i].boxLabel) {
                            ui.mortgagingHoveredProperty = i;
                        }
                    }
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
                if (currentLabel == ui.startTradingButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_CREATE_STATE) {
                if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.offerTradeButton) {
                    if (ui.canOfferTrade() == true) {
                        ui.offerTradeButton.setForeground(new Color(153, 235, 255));
                    }
                } else {
                    for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                        if (currentLabel == ui.tradingCurrentPlayerProperties[i].boxLabel) {
                            ui.tradingMenuHoveredCurrentPlayerProperty = i;
                        }
                    }

                    for (int i = 0; i < playerManager.playerTradingWithPropertyAmount; i++) {
                        if (currentLabel == ui.tradingPlayerTradingWithProperties[i].boxLabel) {
                            ui.tradingMenuHoveredPlayerTradingWithProperty = i;
                        }
                    }
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_OFFER_STATE) {
                if (currentLabel == ui.declineTradeButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.acceptTradeButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
                if (currentLabel == ui.bankruptcyNoButton) {
                     currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.bankruptcyYesButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.GAME_OVER_STATE) {
                if (currentLabel == ui.backtoMainMenuButton) {
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
            } else if (GameStates.currentGameState == GameStates.MORTGAGING_MENU_STATE) {
                if (currentLabel == ui.mortgageButton) {
                    if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == false) {
                        ui.mortgageButton.setForeground(Color.white);
                    }
                } else if (currentLabel == ui.unMortgageButton) {
                    if (ui.isPropertyMortgaged(ui.mortgagingProperties[ui.mortgagingSelectedProperty]) == true) {
                        ui.unMortgageButton.setForeground(Color.white);
                    }
                } else if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(Color.white);
                } else {
                    for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                        if (currentLabel == ui.mortgagingProperties[i].boxLabel) {
                            ui.mortgagingHoveredProperty = -1;
                        }
                    }
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_PLAYER_SELECT_STATE) {
                if (currentLabel == ui.startTradingButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_CREATE_STATE) {
                if (currentLabel == ui.managerBackButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.offerTradeButton) {
                    if (ui.canOfferTrade() == true) {
                        ui.offerTradeButton.setForeground(Color.white);
                    }
                } else {
                    for (int i = 0; i < playerManager.currentPlayerPropertyAmount; i++) {
                        if (currentLabel == ui.tradingCurrentPlayerProperties[i].boxLabel) {
                            ui.tradingMenuHoveredCurrentPlayerProperty = -1;
                        }
                    }

                    for (int i = 0; i < playerManager.playerTradingWithPropertyAmount; i++) {
                        if (currentLabel == ui.tradingPlayerTradingWithProperties[i].boxLabel) {
                            ui.tradingMenuHoveredPlayerTradingWithProperty = -1;
                        }
                    }
                }
            } else if (GameStates.currentGameState == GameStates.TRADING_OFFER_STATE) {
                if (currentLabel == ui.declineTradeButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.acceptTradeButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.DECLARE_BANKRUPTCY_STATE) {
                if (currentLabel == ui.bankruptcyNoButton) {
                     currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.bankruptcyYesButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.GAME_OVER_STATE) {
                if (currentLabel == ui.backtoMainMenuButton) {
                    currentLabel.setForeground(Color.white);
                }
            }
        }
    }
}
