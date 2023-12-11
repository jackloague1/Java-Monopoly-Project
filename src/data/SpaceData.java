package src.data;

import java.util.ArrayList;
import src.spaces.Card;
import src.spaces.FreeParking;
import src.spaces.Go;
import src.spaces.GoToJail;
import src.spaces.Jail;
import src.spaces.NormalProperty;
import src.spaces.RailroadProperty;
import src.spaces.Space;
import src.spaces.Tax;
import src.spaces.UtilityProperty;

/**
 * Initializes and stores all information for all spaces on the board.
 */
public class SpaceData 
{
    public final int NUM_OF_SPACES = 40;
    public final int NUM_OF_NORMAL_PROPERTIES = 22;
    public final int NUM_OF_RAILROAD_PROPERTIES = 4;
    public final int NUM_OF_UTILITY_PROPERTIES = 2;
    public final int NUM_OF_CARD_SPACES = 6;
    public final int NUM_OF_TAX_SPACES = 2;

    public final int[] spaceNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                              11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
                                              21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
                                              31, 32, 33, 34, 35, 36, 37, 38, 39};
    public final int[] xCoordinates = {660, 593, 541, 489, 437, 385, 333, 281, 229, 177, 110,
                                              110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 
                                              177, 229, 281, 333, 385, 437, 489, 541, 593, 660, 
                                              660, 660, 660, 660, 660, 660, 660, 660, 660};
    public final int[] yCoordinates = {660, 660, 660, 660, 660, 660, 660, 660, 660, 660, 680,
                                              593, 541, 489, 437, 385, 333, 281, 229, 177, 110, 
                                              110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 
                                              177, 229, 281, 333, 385, 437, 489, 541, 593};

    public ArrayList<Space> spaces = new ArrayList<Space>();
    public Go go;
    public Jail jail;
    public FreeParking freeParking;
    public GoToJail goToJail;
    public ArrayList<NormalProperty> normalProperties = new ArrayList<NormalProperty>();
    public ArrayList<RailroadProperty> railroadProperties 
                                       = new ArrayList<RailroadProperty>();
    public ArrayList<UtilityProperty> utilityProperties = new ArrayList<UtilityProperty>();
    public ArrayList<Card> cardSpaces = new ArrayList<Card>();
    public ArrayList<Tax> taxSpaces = new ArrayList<Tax>();

    public String currentSpaceType;
    public NormalProperty currentNormalProperty;
    public RailroadProperty currentRailroad;
    public UtilityProperty currentUtility;
    public Card currentCardSpace;
    public Tax currentTaxSpace;

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
    public void createCornerSpaces() {
        go = new Go(661, 661, 0, "Go", 200);
        jail = new Jail(111, 661, 10, "Just Visiting");
        freeParking = new FreeParking(111, 111, 20, "Free Parking");
        goToJail = new GoToJail(661, 111, 30, "Go To Jail");
    }

    /**
    * Creates the data for all normal property spaces.
    */
    public void createNormalProperties() {
        final int[] x_coordinates = { 594, 490, 334, 230, 178, 0, 0, 0, 0, 0, 0, 
                                      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final int[] yCoordinates = { 661, 661, 661, 661, 661, 0, 0, 0, 0, 0, 0, 
                                     0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final int[] spaceNumbers = { 1, 3, 6, 8, 9, 
                                     11, 13, 14, 16, 18, 19, 
                                     21, 23, 24, 26, 27, 29, 
                                     31, 32, 34, 37, 39 };
        final String[] names = { "Student Housing", 
                                 "Residence Halls", 
                                 "Eastside Structure", 
                                 "Nutwood Structure", 
                                 "St. College Structure", 
                                 "Dan Black Hall", 
                                 "McCarthy Hall", 
                                 "Visual Arts", 
                                 "Langsdorf Hall", 
                                 "Mihaylo Hall", 
                                 "University Hall", 
                                 "Humanities", 
                                 "Pollak Library", 
                                 "Education Classroom", 
                                 "Kinesiology", 
                                 "Engineering", 
                                 "Computer Science", 
                                 "Student Union", 
                                 "Rec Center", 
                                 "Titan Shops", 
                                 "Becker Amphitheater", 
                                 "Performing Arts Center" };
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
    public void createRailroadProperties() {
        final int[] xCoordinates = { 386, 0, 0, 0 };
        final int[] yCoordinates = { 661, 0, 0, 0 };
        final int[] spaceNumbers = { 5, 15, 25, 35 };
        final String[] names = { "Anderson Field", 
                                 "Goodwin Field", 
                                 "Titan Gymnasium", 
                                 "Titan Stadium" };
        final int[] prices = { 200, 200, 200, 200 };
        final int[][] groupRent = { {25, 50, 100, 200},
                                    {25, 50, 100, 200},
                                    {25, 50, 100, 200},
                                    {25, 50, 100, 200} };

        for (int i = 0; i < NUM_OF_RAILROAD_PROPERTIES; i++) {
            railroadProperties.add(new RailroadProperty(xCoordinates[i], 
                                                        yCoordinates[i], 
                                                        spaceNumbers[i], 
                                                        names[i], 
                                                        prices[i], 
                                                        groupRent[i]));
        }
    }

    /**
    * Creates the data for all utility property spaces.
    */
    public void createUtilityProperties() {
        final int[] xCoordinates = { 0, 0 };
        final int[] yCoordinates = { 0, 0 };
        final int[] spaceNumbers = { 12, 28 };
        final String[] names = { "CSUF Electric", "CSUF Water" };
        final int[] prices = { 150, 150 };
        final int[][] rentMultiplier = { {4, 10},
                                         {4, 10} };

        for (int i = 0; i < NUM_OF_UTILITY_PROPERTIES; i++) {
            utilityProperties.add(new UtilityProperty(xCoordinates[i], 
                                                      yCoordinates[i], 
                                                      spaceNumbers[i], 
                                                      names[i], 
                                                      prices[i],
                                                      rentMultiplier[i]));
        }
    }

    /**
    * Creates the data for all card spaces.
    */
    public void createCardSpaces() {
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
    public void createTaxSpaces() {
        final int[] xCoordinates = { 438, 0 };
        final int[] yCoordinates = { 661, 0 };
        final int[] spaceNumbers = { 4, 38 };
        final String[] names = { "Income Tax", "Luxury Tax" };
        final int[] fees = { 200, 75, };

        for (int i = 0; i < NUM_OF_TAX_SPACES; i++) {
            taxSpaces.add(new Tax(xCoordinates[i], yCoordinates[i], spaceNumbers[i], names[i], 
                          fees[i]));
        }
    }

    /**
    * Checks if a single player owns all properties of a certain group.
    */
    public boolean isMonopoly() {
        ArrayList<NormalProperty> groupNormalProperties = new ArrayList<NormalProperty>();

        int groupNormalPropertiesIndex = 0;

        for (int i = 0; i < normalProperties.size(); i++) {
            if (normalProperties.get(i).group == currentNormalProperty.group) {
                if (groupNormalProperties.size() == 0) {
                    groupNormalProperties.add(normalProperties.get(i));
                    groupNormalPropertiesIndex++;
                } else {
                    if (normalProperties.get(i).owner 
                        == groupNormalProperties.get(groupNormalPropertiesIndex - 1).owner) {
                        groupNormalProperties.add(normalProperties.get(i));
                        groupNormalPropertiesIndex++;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
    * Determines how many railroads or utilities a single player owns.
    */
    public int howManyInGroup() {
        
        int number = 0;

        if (currentSpaceType == "Railroad") {
            for (int i = 0; i < railroadProperties.size(); i++) {
                if (railroadProperties.get(i).owner == currentRailroad.owner) {
                        number++;
                    }
                }
        } else if (currentSpaceType == "Utility") {
            for (int i = 0; i < utilityProperties.size(); i++) {
                if (utilityProperties.get(i).owner == currentUtility.owner) {
                    number++;
                }
            }
        }

        return number;
    }

    /**
    * Sets the current rent of the current property.
    */
    public int setRent() {
        int rent = 0;

        if (currentSpaceType == "Normal Property") {
            if (currentNormalProperty.buildingAmount > 0) {
                currentNormalProperty.currentRent 
                    = currentNormalProperty.buildingRent[currentNormalProperty.buildingAmount - 1];
            } else if (isMonopoly() == true) {
                currentNormalProperty.currentRent = currentNormalProperty.normalRent * 2;
            } else {
                currentNormalProperty.currentRent = currentNormalProperty.normalRent;
            }
        } else if (currentSpaceType == "Railroad") {
            currentRailroad.currentRent = currentRailroad.groupRent[howManyInGroup() - 1];
        } else if (currentSpaceType == "Utility") {
            currentUtility.currentMultiplier 
                = currentUtility.rentMultiplier[howManyInGroup() - 1];
            System.out.print("Current utility multipler: " + currentUtility.currentMultiplier);
        }

        return rent;
    }

    /**
    * Returns the x and y coordinates of a space given the space number.
    */
    public int[] getSpaceCoordinates(int currentSpaceNumber, int coordinateOffset) {
        
        // Variable for storing the x and y coordinates.
        int[] coordinates = new int[2];

        for (int i = 0; i < NUM_OF_SPACES; i++) {
            if (currentSpaceNumber == spaceNumbers[i]) {
                coordinates[0] = xCoordinates[i];
                coordinates[1] = yCoordinates[i];

                if (currentSpaceNumber < 10) {
                    coordinates[1] = yCoordinates[i] - coordinateOffset;
                } else if (currentSpaceNumber < 20) {
                    coordinates[0] = xCoordinates[i] + coordinateOffset;
                } else if (currentSpaceNumber < 30) {
                    coordinates[1] = yCoordinates[i] + coordinateOffset;
                } else if (currentSpaceNumber < 40) {
                    coordinates[0] = xCoordinates[i] - coordinateOffset;
                }

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
    public void getSpace(int currentSpaceNumber) {
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
