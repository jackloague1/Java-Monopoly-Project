package src.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Initializes and stores all information for all cards in the game.
 */
public class CardData {
    ArrayList<String> communityChestCards;
    ArrayList<String> chanceCards;

    public CardData() {
        communityChestCards = new ArrayList<String>();
    }

    /**
    * Initializes all community chest cards.
    */
    public void createCommunityChestCards() {
        communityChestCards.add("Advance to Go");
        communityChestCards.add("Tuition Error");
        communityChestCards.add("Doctor's Fee");
        communityChestCards.add("Sale of Textbook");
        communityChestCards.add("Get out of Jail Free");
        communityChestCards.add("Go to Jail");
        communityChestCards.add("Club Fund Matures");
        communityChestCards.add("Income Tax Refund");
        communityChestCards.add("Tickets to Game");
        communityChestCards.add("Campus Job Paycheck");
        communityChestCards.add("Tuition Fee");
        communityChestCards.add("Textbook Fee");
        communityChestCards.add("Receive Student Aid");
        communityChestCards.add("Assessed for Construction");
        communityChestCards.add("Second Prize");
        communityChestCards.add("Parking Refund");
    }

    /**
    * Initializes all chance cards.
    */
    public void createChanceCards() {
        chanceCards.add("Advance to Performing Arts");
        chanceCards.add("Advance to Go");
        chanceCards.add("Advance to Education Classroom");
        chanceCards.add("Advance to Dan Black Hall");
        chanceCards.add("Advance to Nearest Sport");
        chanceCards.add("Advance to Nearest Sport");
        chanceCards.add("Advance to Nerest Utility");
        chanceCards.add("Receive Student Aid");
        chanceCards.add("Get out of Jail");
        chanceCards.add("Go Back 3 Spaces");
        chanceCards.add("Go to Jail");
        chanceCards.add("General Repairs");
        chanceCards.add("Speeding Fine");
        chanceCards.add("Advance to Anderson Field");
        chanceCards.add("Elected Club Leader");
        chanceCards.add("Club Fund Matures");
    }

    /**
    * Randomizes the chance and community chest cards when a game begins.
    */
    public void randomizeCards() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }

    /**
    * Randomizes the chance and community chest cards when a game begins.
    */
    public void getCardFunction() {

    }
}
