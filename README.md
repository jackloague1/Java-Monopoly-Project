# Java-Monopoly-Project
A recreation of the board game Monopoly using the Java language, with a CSU Fullerton theme.

![Screenshot](https://github.com/jackloague1/Java-Monopoly-Project/blob/main/game-screenshot.PNG)

# Running the Game (Windows)
To run the game:
1) Make sure Java is installed on your computer (Download: https://www.java.com/en/download/)
2) Download this repository
3) Open (double-click) the Game.jar file to run the game. Alternatively, run the game from the command prompt by navigating to the repository directory and running the command "java -jar Game.jar".

# How to Play
From the main menu, the user can either choose Play (which navigates to the Game Set-Up menu), or Profiles (which navigates to the Profiles menu). Within the Profiles menu, player profiles can be created and saved. Within the Game Set-Up menu, every player must have a valid profile name and token selected before a game can begin. The set-up menu allows for up to six players to join a game, however there is currently a bug where many buttons in the game lose their functionality if the game is played with four players or more.

Once a game is started, players can roll the dice and move around the board. Players can buy or pass when landing on unowned properties, and also must pay rent when landing on an owned property that they do not own. There is also functionality for the Jail space.

Within the Player Manager (located in the top-center of the main board game screen), players can manage their assets by mortgaging/unmortgaging properties, or trading properties with other players. Players may also declare bankruptcy if they cannot pay a debt or simply wish to leave the game.

# Missing Features
* There is currently no functionality for the Chance and Community Chest cards.
* Players can only trade properties, rather than also being able to trade money and Get Out of Jail Free cards.
* There is currently no functionality for building houses or hotels.

# Bugs
Some of the buttons are not responsive or always responsive.
Currently, there is a bug that causes the game to sometimes freeze when a player declares bankruptcy and leaves the game.
When the game is played with four players or more, many of the buttons lose functionality. It is unknown currently what is causing this.
