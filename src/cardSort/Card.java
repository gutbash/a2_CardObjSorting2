//// cardSort
// program for creating, editing, shuffling, and sorting a deck of cards using Fisher-Yates, bubble, and insertion for CSCI 211
// last edited Sep. 14, 2022 by S. Gutierrez

// includes project package
package cardSort;

//// Card
// creates the card class
// represents a playing card with a value and suit
// associates various order values for value, suit, and overall deck through _Rank integers
public class Card {

    // declares instance variables
    String value; // 2-10, Jack, Queen, King, Ace
    String suit; // Clubs, Diamonds, Hearts, Spades
    int valueRank; // 0-12
    int suitRank; // 0-3
    int deckRank; // 0-51

    // constructs and initializes instance variables and sets up parameters
    public Card(String cardValue, String cardSuit, int valueRankInt, int suitRankInt, int deckRankInt) {

        // System.out.println("Constructor invoked!");
        value = cardValue;
        suit = cardSuit;
        valueRank = valueRankInt;
        suitRank = suitRankInt;
        deckRank = deckRankInt;

    } // ends Card() constructor method

    // summarizes and formats information in stringed response
    public String toString() {
        return String.format("%s - %s", value, suit);
    }

    // getters //
    public String getValue() { return value; }
    public String getSuit() {
        return suit;
    }
    public int getValueRank() { return valueRank; }
    public int getSuitRank() { return suitRank; }
    public int getDeckRank() { return deckRank; }

    // setters //
    public void setValue(String value){this.value = value;}
    public void setSet(String value){this.suit = suit;}

} // ends card class
