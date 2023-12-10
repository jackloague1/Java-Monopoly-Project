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
            if (currentLabel == ui.rollButton) {
                dice.getDiceResult();
                ui.rollButton.setForeground(new Color(255, 255, 255, 75));
                ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
            if (currentLabel == ui.okButton) {
                if (spaceData.currentSpaceType == "Go") {
                    if (dice.result == 0) {
                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    } else {
                        ui.managerButton.setForeground(new Color(255, 255, 255, 75));
                        GameStates.currentGameState = GameStates.PLAYER_MOVE_STATE;
                    }
                } else {
                    ui.managerButton.setForeground(Color.white);
                    ui.nextTurnButton.setForeground(Color.white);
                    ui.buyOptionText.setForeground(Color.white);
                    GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                }
                ui.okButton.setForeground(Color.white);
            } else if (currentLabel == ui.buyOptionText) {
                if (spaceData.currentSpaceType == "Normal Property") {
                    if (players.get(gamePanel.currentPlayerNumber - 1).money 
                        >= spaceData.currentNormalProperty.price) {
                        spaceData.currentNormalProperty.owner = 
                            players.get(gamePanel.currentPlayerNumber - 1);
                        players.get(gamePanel.currentPlayerNumber - 1).money 
                            = players.get(gamePanel.currentPlayerNumber - 1).money 
                            - spaceData.currentNormalProperty.price;
                        System.out.println("Property bought");

                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.buyOptionText.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }                    
                } else if (spaceData.currentSpaceType == "Railroad") {
                    if (players.get(gamePanel.currentPlayerNumber - 1).money 
                        >= spaceData.currentRailroad.price) {
                        spaceData.currentRailroad.owner = 
                            players.get(gamePanel.currentPlayerNumber - 1);
                        players.get(gamePanel.currentPlayerNumber - 1).money 
                            = players.get(gamePanel.currentPlayerNumber - 1).money 
                            - spaceData.currentRailroad.price;
                        System.out.println("Property bought");

                        ui.managerButton.setForeground(Color.white);
                        ui.nextTurnButton.setForeground(Color.white);
                        ui.buyOptionText.setForeground(Color.white);
                        ui.buyOptionText.setVisible(false);
                        ui.passOptionText.setVisible(false);
                        GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
                    }
                } else if (spaceData.currentSpaceType == "Utility") {
                    if (players.get(gamePanel.currentPlayerNumber - 1).money 
                        >= spaceData.currentUtility.price) {
                        spaceData.currentUtility.owner = 
                            players.get(gamePanel.currentPlayerNumber - 1);
                        players.get(gamePanel.currentPlayerNumber - 1).money 
                            = players.get(gamePanel.currentPlayerNumber - 1).money 
                            - spaceData.currentUtility.price;
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
                ui.buyOptionText.setVisible(false);
                ui.passOptionText.setForeground(Color.white);
                ui.passOptionText.setVisible(false);
                GameStates.currentGameState = GameStates.NEXT_TURN_STATE;
            }
        } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
            if (currentLabel == ui.nextTurnButton) {
                gamePanel.changePlayerNumber();
                ui.rollButton.setForeground(Color.white);
                ui.nextTurnButton.setForeground(new Color(255, 255, 255, 75));
                GameStates.currentGameState = GameStates.ROLL_STATE;
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
                if (currentLabel == ui.rollButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.okButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.buyOptionText) {
                    if (spaceData.currentSpaceType == "Normal Property") {
                        if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                            >= spaceData.currentNormalProperty.price) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Railroad") {
                        if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                            >= spaceData.currentRailroad.price) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    } else if (spaceData.currentSpaceType == "Utility") {
                        if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                            >= spaceData.currentUtility.price) {
                            currentLabel.setForeground(new Color(153, 235, 255));
                        }
                    }
                } else if (currentLabel == ui.passOptionText) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                }
            } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(new Color(153, 235, 255));
                } else if (currentLabel == ui.nextTurnButton) {
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
                if (currentLabel == ui.rollButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.SPACE_EVENT_STATE) {
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.okButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.buyOptionText) {
                    if (spaceData.currentSpaceType == "Normal Property") {
                        if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                            >= spaceData.currentNormalProperty.price) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Railroad") {
                        if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                            >= spaceData.currentRailroad.price) {
                            currentLabel.setForeground(Color.white);
                        }
                    } else if (spaceData.currentSpaceType == "Utility") {
                        if (gamePanel.players.get(gamePanel.currentPlayerNumber - 1).money 
                            >= spaceData.currentUtility.price) {
                            currentLabel.setForeground(Color.white);
                        }
                    }
                } else if (currentLabel == ui.passOptionText) {
                    currentLabel.setForeground(Color.white);
                }
            } else if (GameStates.currentGameState == GameStates.NEXT_TURN_STATE) {
                if (currentLabel == ui.managerButton) {
                    currentLabel.setForeground(Color.white);
                } else if (currentLabel == ui.nextTurnButton) {
                    currentLabel.setForeground(Color.white);
                }
            }
        }
    }
}
