package data;

import java.util.ArrayList;
import spaces.Card;
import spaces.FreeParking;
import spaces.Go;
import spaces.GoToJail;
import spaces.Jail;
import spaces.NormalProperty;
import spaces.RailroadProperty;
import spaces.Space;
import spaces.Tax;
import spaces.UtilityProperty;

/**
 * Initializes and stores all information for all spaces on the board.
 */
public class SpaceData 
{
    public static final int NUM_OF_SPACES = 40;
    public static final int NUM_OF_NORMAL_PROPERTIES = 22;
    public static final int NUM_OF_RAILROAD_PROPERTIES = 4;
    public static final int NUM_OF_UTILITY_PROPERTIES = 2;
    public static final int NUM_OF_CARD_SPACES = 6;
    public static final int NUM_OF_TAX_SPACES = 2;

    public static final int[] spaceNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                              11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
                                              21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
                                              31, 32, 33, 34, 35, 36, 37, 38, 39};
    public static final int[] xCoordinates = {661, 594, 542, 490, 438, 386, 334, 282, 230, 178, 111,
                                              111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 
                                              178, 230, 282, 334, 386, 438, 490, 542, 594, 661, 
                                              661, 661, 661, 661, 661, 661, 661, 661, 661};
    public static final int[] yCoordinates = {661, 661, 661, 661, 661, 661, 661, 661, 661, 661, 661,
                                              594, 542, 490, 438, 386, 334, 282, 230, 178, 111, 
                                              111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 
                                              178, 230, 282, 334, 386, 438, 490, 542, 594};

    public static ArrayList<Space> spaces = new ArrayList<Space>();
    public static Go go;
    public static Jail jail;
    public static FreeParking freeParking;
    public static GoToJail goToJail;
    public static ArrayList<NormalProperty> normalProperties = new ArrayList<NormalProperty>();
    public static ArrayList<RailroadProperty> railroadProperties 
                                              = new ArrayList<RailroadProperty>();
    public static ArrayList<UtilityProperty> utilityProperties = new ArrayList<UtilityProperty>();
    public static ArrayList<Card> cardSpaces = new ArrayList<Card>();
    public static ArrayList<Tax> taxSpaces = new ArrayList<Tax>();

    public static String currentSpaceType;
    public static NormalProperty currentNormalProperty;
    public static RailroadProperty currentRailroad;
    public static UtilityProperty currentUtility;
    public static Card currentCardSpace;
    public static Tax currentTaxSpace;

    /**
    * Constructor method.
    */
    public SpaceData() {
        createCornerSpaces();
        createNormalProperties();
        createRailroadProperties();
        createUtilityProperties();
        createCardSpaces();
        createTaxSpaces();
    }

    /**
    * Creates the data for the go, jail, free parking, and go to jail spaces.
    */
    public static void createCornerSpaces() {
        go = new Go(661, 661, 0, "Go", 200);
        jail = new Jail(111, 661, 10, "Just Visiting");
        freeParking = new FreeParking(111, 111, 20, "Free Parking");
        goToJail = new GoToJail(661, 111, 30, "Go To Jail");
    }

    /**
    * Creates the data for all normal property spaces.
    */
    public static void createNormalProperties() {
        final int[] x_coordinates = { 594, 490, 334, 230, 178, 0, 0, 0, 0, 0, 0, 
                                      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final int[] yCoordinates = { 661, 661, 661, 661, 661, 0, 0, 0, 0, 0, 0, 
                                     0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final int[] spaceNumbers = { 1, 3, 6, 8, 9, 
                                     11, 13, 14, 16, 18, 19, 
                                     21, 23, 24, 26, 27, 29, 
                                     31, 32, 34, 37, 39 };
        final String[] names = { "Mediterranean Avenue", 
                                 "Baltic Avenue", 
                                 "Oriental Avenue", 
                                 "Vermont Avenue", 
                                 "Connecticut Avenue", 
                                 "St. Charles Place", 
                                 "States Avenue", 
                                 "Virginia Avenue", 
                                 "St. James Place", 
                                 "Tennesse Avenue", 
                                 "New York Avenue", 
                                 "Kentucky Avenue", 
                                 "Indiana Avenue", 
                                 "Illinois Avenue", 
                                 "Atlantic Avenue", 
                                 "Ventnor Avenue", 
                                 "Marvin Gardens", 
                                 "Pacific Avenue", 
                                 "North Carolina Avenue", 
                                 "Pennsylvania Avenue", 
                                 "Park Place", 
                                 "Boardwalk" };
        final String[] groups = { "Purple", 
                                  "Purple", 
                                  "Light Blue", 
                                  "Light Blue", 
                                  "Light Blue", 
                                  "Pink", 
                                  "Pink", 
                                  "Pink",
                                  "Orange", 
                                  "Orange", 
                                  "Orange", 
                                  "Red", 
                                  "Red", 
                                  "Red", 
                                  "Yellow", 
                                  "Yellow", 
                                  "Yellow", 
                                  "Green", 
                                  "Green",
                                  "Green", 
                                  "Blue", 
                                  "Blue" };
        final int[] prices = { 60, 60, 100, 100, 120, 
                               140, 140, 160, 180, 180, 200, 
                               220, 220, 240, 260, 260, 280, 
                               300, 300, 320, 350, 400 };
        final int[] rentPrices = { 2, 4, 6, 6, 8, 
                                   10, 10, 12, 14, 14, 16, 
                                   18, 18, 20, 22, 22, 24, 
                                   26, 26, 28, 35, 50 };
        final int[] buildingPrices = { 50, 50, 50, 50, 50,
                                       100, 100, 100, 100, 100, 100,
                                       150, 150, 150, 150, 150, 150,
                                       200, 200, 200, 200, 200};
        final int[][] buildingRentPrices = { {10, 30, 90, 160, 250}, 
                                             {20, 60, 180, 320, 450}, 
                                             {30, 90, 270, 400, 550}, 
                                             {30, 90, 270, 400, 550}, 
                                             {40, 100, 300, 450, 600}, 
                                             {50, 150, 450, 625, 750}, 
                                             {50, 150, 450, 625, 750}, 
                                             {60, 180, 500, 700, 900}, 
                                             {70, 200, 550, 750, 950}, 
                                             {70, 200, 550, 750, 950}, 
                                             {80, 220, 600, 800, 1000}, 
                                             {90, 250, 700, 875, 1050}, 
                                             {90, 250, 700, 875, 1050}, 
                                             {100, 300, 750, 925, 1100}, 
                                             {110, 330, 800, 975, 1150}, 
                                             {110, 330, 800, 975, 1150}, 
                                             {120, 360, 850, 1025, 1200}, 
                                             {130, 390, 900, 1100, 1275}, 
                                             {130, 390, 900, 1100, 1275}, 
                                             {150, 450, 1000, 1200, 1400}, 
                                             {175, 500, 1100, 1300, 1500}, 
                                             {200, 600, 1400, 1700, 2000} };
        
        for (int i = 0; i < NUM_OF_NORMAL_PROPERTIES; i++) {
            normalProperties.add(new NormalProperty(x_coordinates[i], 
                                                    yCoordinates[i], 
                                                    spaceNumbers[i], 
                                                    names[i], 
                                                    groups[i], 
                                                    prices[i], 
                                                    rentPrices[i], 
                                                    buildingPrices[i],
                                                    buildingRentPrices[i]));
        }
    }

    /**
    * Creates the data for all railroad property spaces.
    */
    public static void createRailroadProperties() {
        final int[] xCoordinates = { 386, 0, 0, 0 };
        final int[] yCoordinates = { 661, 0, 0, 0 };
        final int[] spaceNumbers = { 5, 15, 25, 35 };
        final String[] names = { "Reading Railroad", 
                                 "Pennsylvania Railroad", 
                                 "B. & O. Railroad", 
                                 "Short Line" };
        final int[] prices = { 200, 200, 200, 200 };
        final int[] rentPrices = { 25, 25, 25, 25 };

        for (int i = 0; i < NUM_OF_RAILROAD_PROPERTIES; i++) {
            railroadProperties.add(new RailroadProperty(xCoordinates[i], 
                                                        yCoordinates[i], 
                                                        spaceNumbers[i], 
                                                        names[i], 
                                                        prices[i], 
                                                        rentPrices[i]));
        }
    }

    /**
    * Creates the data for all utility property spaces.
    */
    public static void createUtilityProperties() {
        final int[] xCoordinates = { 0, 0 };
        final int[] yCoordinates = { 0, 0 };
        final int[] spaceNumbers = { 12, 28 };
        final String[] names = { "Electric Company", "Water Works" };
        final int[] prices = { 150, 150 };

        for (int i = 0; i < NUM_OF_UTILITY_PROPERTIES; i++) {
            utilityProperties.add(new UtilityProperty(xCoordinates[i], 
                                                      yCoordinates[i], 
                                                      spaceNumbers[i], 
                                                      names[i], 
                                                      prices[i]));
        }
    }

    /**
    * Creates the data for all card spaces.
    */
    public static void createCardSpaces() {
        final int[] xCoordinates = { 542, 282, 0, 0, 0, 0 };
        final int[] yCoordinates = { 661, 661, 0, 0, 0, 0 };
        final int[] spaceNumbers = { 2, 7, 17, 22, 33, 36 };
        final String[] names = { "Community Chest", 
                                 "Chance", 
                                 "Community Chest", 
                                 "Chance", 
                                 "Community Chest",
                                 "Chance" };

        for (int i = 0; i < NUM_OF_CARD_SPACES; i++) {
            cardSpaces.add(new Card(xCoordinates[i], yCoordinates[i], spaceNumbers[i], names[i]));
        }
    }

    /**
    * Creates the data for all tax spaces.
    */
    public static void createTaxSpaces() {
        final int[] xCoordinates = { 438, 0 };
        final int[] yCoordinates = { 661, 0 };
        final int[] spaceNumbers = { 4, 38 };
        final String[] names = { "Income Tax", "Luxury Tax" };

        for (int i = 0; i < NUM_OF_TAX_SPACES; i++) {
            taxSpaces.add(new Tax(xCoordinates[i], yCoordinates[i], spaceNumbers[i], names[i]));
        }
    }

    /**
    * Returns the x and y coordinates of a space given the space number.
    */
    public int[] getSpaceCoordinates(int currentSpaceNumber) {
        
        // Variable for storing the x and y coordinates.
        int[] coordinates = new int[2];

        for (int i = 0; i < NUM_OF_SPACES; i++) {
            if (currentSpaceNumber == spaceNumbers[i]) {
                coordinates[0] = xCoordinates[i];
                coordinates[1] = yCoordinates[i];

                break;
            }
        //     if (currentSpaceNumber == SpaceData.normalProperties.get(i).spaceNumber)
        //     {
        //         coordinates[0] = SpaceData.normalProperties.get(i).x;
        //         coordinates[1] = SpaceData.normalProperties.get(i).y;
                
        //         return coordinates;
        //     }
        //     else if (currentSpaceNumber == SpaceData.railroadProperties.get(i).spaceNumber)
        //     {
        //         coordinates[0] = SpaceData.railroadProperties.get(i).x;
        //         coordinates[1] = SpaceData.railroadProperties.get(i).y;

        //         return coordinates;
        //     }
        //     else if (currentSpaceNumber == SpaceData.utilityProperties.get(i).spaceNumber)
        //     {
        //         coordinates[0] = SpaceData.utilityProperties.get(i).x;
        //         coordinates[1] = SpaceData.utilityProperties.get(i).y;

        //         return coordinates;
        //     }
        //     else if (currentSpaceNumber == SpaceData.cardSpaces.get(i).spaceNumber)
        //     {
        //         coordinates[0] = SpaceData.cardSpaces.get(i).x;
        //         coordinates[1] = SpaceData.cardSpaces.get(i).y;

        //         return coordinates;
        //     }
        //     else if (currentSpaceNumber == SpaceData.taxSpaces.get(i).spaceNumber)
        //     {
        //         coordinates[0] = SpaceData.taxSpaces.get(i).x;
        //         coordinates[1] = SpaceData.taxSpaces.get(i).y;

        //         return coordinates;
        //     }
        //     else
        //     {
        //         coordinates[0] = 0;
        //         coordinates[1] = 0;
        //     }
        // }

        //return coordinates;
        }

        return coordinates;
    }

    /**
    * Gets the current space of a player given the space number the player is currently on.
    */
    public static void getSpace(int currentSpaceNumber) {
        for (int i = 0; i < NUM_OF_NORMAL_PROPERTIES; i++) {
            if (currentSpaceNumber == normalProperties.get(i).spaceNumber) {
                currentSpaceType = normalProperties.get(i).type;
                currentNormalProperty = normalProperties.get(i);

                return;
            }
        }

        for (int i = 0; i < NUM_OF_RAILROAD_PROPERTIES; i++) {
            if (currentSpaceNumber == railroadProperties.get(i).spaceNumber) {
                currentSpaceType = railroadProperties.get(i).type;
                currentRailroad = railroadProperties.get(i);

                return;
            }
        }

        for (int i = 0; i < NUM_OF_UTILITY_PROPERTIES; i++) {
            if (currentSpaceNumber == utilityProperties.get(i).spaceNumber) {
                currentSpaceType = utilityProperties.get(i).type;
                currentUtility = utilityProperties.get(i);

                return;
            }
        }

        for (int i = 0; i < NUM_OF_CARD_SPACES; i++) {
            if (currentSpaceNumber == cardSpaces.get(i).spaceNumber) {
                currentSpaceType = cardSpaces.get(i).type;
                currentCardSpace = cardSpaces.get(i);

                return;
            }
        }

        for (int i = 0; i < NUM_OF_TAX_SPACES; i++) {
            if (currentSpaceNumber == taxSpaces.get(i).spaceNumber) {
                currentSpaceType = taxSpaces.get(i).type;
                currentTaxSpace = taxSpaces.get(i);

                return;
            }
        }     
        
        if (currentSpaceNumber == go.spaceNumber) {
            currentSpaceType = go.type;
            return;
        }

        if (currentSpaceNumber == jail.spaceNumber) {
            currentSpaceType = jail.type;
            return;
        }

        if (currentSpaceNumber == freeParking.spaceNumber) {
            currentSpaceType = freeParking.type;
            return;
        }
        if (currentSpaceNumber == goToJail.spaceNumber) {
            currentSpaceType = goToJail.type;
            return;
        }
    }
}
