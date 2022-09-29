//// cardSort
// program for creating, editing, shuffling, and sorting a deck of cards using Fisher-Yates, bubble, and insertion for CSCI 211
// last edited Sep. 14, 2022 by S. Gutierrez

package cardSort; // includes project package

// imports
import java.sql.Array;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Arrays;

/**

 * Main
 * contains methods for operating on a deck of Card[] + driver code
 */
public class Main {

    /**

     * buildDeck()
     * creates an array of 52 Card objects
     */
    public static Card[] buildDeck(LinkedHashMap<String, Integer> values, LinkedHashMap<String, Integer> suits) {

        Card[] deck = new Card[52]; // creates an array of 52 Card objects

        int deckIdx = 0; // tracks value with counter
        for (String suit : suits.keySet()) { // iterates through dictionary of suits keys

            for (String value : values.keySet()) { // iterates through dictionary of values keys

                // assigns array position at counter index to new Card object
                deck[deckIdx] = new Card(value, suit, values.get(value), suits.get(suit), deckIdx);
                deckIdx++; // increases counter

            } // ends values loop
        } // ends suits loop
        return deck; // returns created deck of Card[]
    } // ends buildDeck() method

    /**

     * shuffleDeck()
     * uses Fisher-Yates to randomly shuffle a Card[] deck array
     */
    public static Card[] shuffleDeck(Card[] deck) {

        // instantiates Random object for operation
        Random rand = new Random();

        // iterates backwards from end of deck
        for (int cardIdx = deck.length - 1; cardIdx > 0; cardIdx--) {

            // generates random number to size of deck for indexing
            int ranIdx = rand.nextInt(cardIdx + 1);
            // holds current card
            Card hold = deck[cardIdx];
            // assigns random card to current card
            deck[cardIdx] = deck[ranIdx];
            // assigns hold (current card) to random card
            deck[ranIdx] = hold;

        } // ends for loop

        // returns shuffled deck
        return deck;

    } // ends shuffleDeck() method
    /**

     * printDeck()
     * prints each card in a Card[] array/deck
     */
    public static void printDeck(Card[] deck) {

        // iterates for each Card object in Card[] array
        for (Card card : deck) {

            // invokes toString() for Card object and prints formatted result
            System.out.println(card.toString());

        } // ends for each loop

        // breaks for readability
        System.out.println("\n\n\n");

    } // ends printDeck() method
    /**

     * sortDeckValueMergeSort()
     * helps sortDeckValueMerge() by sorting the split decks before main merge,
     * sorts a deck of cards strictly by value, does not consider suits,
     * takes in an ArrayList of type Card, which represents the deck of cards, and a LinkedHashMap of type String and Integer, which represents the mapping of card values to integers.
     */
    public static ArrayList<Card> sortDeckValueMergeSort(ArrayList<Card> deck, LinkedHashMap<String, Integer> values) {

        // checks if deck has only one card
        if (deck.size() <= 1) {
            // is already sorted, returns the deck
            return deck;
        } // ends if statement

        // finds and stores midpoint of deck
        int mid = Math.floorDiv(deck.size(), 2);

        // splits deck into two halves at midpoint - a left and a right half
        // returns type List from .subList(), must be converted back to ArrayList
        List<Card> left = deck.subList(0, mid);
        List<Card> right = deck.subList(mid, deck.size());

        // converts the left and right halves into ArrayLists of type Card
        ArrayList<Card> convertLeft = new ArrayList<>(left);
        ArrayList<Card> convertRight = new ArrayList<>(right);

        // calls itself on the left and right halves
        // sorts each half by value recursively
        ArrayList<Card> sLeft = sortDeckValueMergeSort(convertLeft, values);
        ArrayList<Card> sRight = sortDeckValueMergeSort(convertRight, values);

        // passes in the sorted left and right halves
        // merges the two halves into a single, sorted deck
        return sortDeckValueMerge(sLeft, sRight, values);

    } // ends sortDeckValueMergeSort() method
    /**

     * sortDeckValueMerge()
     * merges sorted decks from sortDeckValueMergeSort(),
     * takes in two ArrayLists of Cards, left and right, and a LinkedHashMap of String keys and Integer values.
     * LinkedHashMap is used to compare the values of the Cards in the left and right ArrayLists.
     */
    public static ArrayList<Card> sortDeckValueMerge(ArrayList<Card> left, ArrayList<Card> right, LinkedHashMap<String, Integer> values) {

        // creates an empty ArrayList called merged
        ArrayList<Card> merged = new ArrayList<Card>();

        // enters a while loop that runs as long as the left and right ArrayLists are not empty.
        while (!(left.isEmpty()) && !(right.isEmpty())) {

            // checks if value of the Card in the left ArrayList is less than the value of the Card in the right ArrayList
            if (values.get(left.get(0).getValue()) < values.get(right.get(0).getValue())) {

                // adds Card from the left ArrayList to the merged ArrayList.
                merged.add(left.get(0));
                // removes Card from the left ArrayList.
                left.remove(0);

            // assumes the value of the Card in the left ArrayList is greater than or equal to the value of the Card in the right ArrayList
            } else {

                // adds Card from the right ArrayList to the merged ArrayList
                merged.add(right.get(0));
                // removes Card from the right ArrayList.
                right.remove(0);

            } // ends if else statements
        } // ends while loop

        // checks if the left ArrayList is empty and the right ArrayList is not
        if (!(left.isEmpty())) {

            // adds all the Cards in the right ArrayList to the merged ArrayList
            merged.addAll(left);

        } // ends if statement

        // checks if the right ArrayList is empty and the left ArrayList is not
        if (!(right.isEmpty())) {

            // adds all the Cards in the left ArrayList to the merged ArrayList.
            merged.addAll(right);

        } // ends if statement

        // merged ArrayList is then returned.
        return merged;
    } // ends sortDeckValueMerge() method
    /**

     * sortDeckValueSuit()
     * sorts according to suit and value via quick
     * takes an ArrayList of Card objects as a parameter.
     */
    public static ArrayList<Card> sortDeckValueSuitQuick(ArrayList<Card> deck) {

        // checks if deck length is less than or equal to one (base case)
        if (deck.size() <= 1) {
            return deck;
        } // ends if statement base case

        // calculates pivot point at midpoint of the array
        int pivot = Math.floorDiv(deck.size(), 2);

        // initializes two ArrayList decks to be sorted into according to the pivot
        ArrayList<Card> left = new ArrayList<Card>();
        ArrayList<Card> right = new ArrayList<Card>();

        // begins sorting process for lesser than pivot values
        for (int l = 0; l < deck.size(); l++) {
            // sorts Card with a deck rank lower than the pivot in the left ArrayList
            if (deck.get(l).getDeckRank() < deck.get(pivot).getDeckRank()) {
                left.add(deck.get(l));
            } // ends if statement
        } // ends for loop

        // begins sorting process for greater than pivot values
        for (int r = 0; r < deck.size(); r++) {
            // sorts Card with a deck rank higher than the pivot in the right ArrayList
            if (deck.get(r).getDeckRank() > deck.get(pivot).getDeckRank()) {
                right.add(deck.get(r));
            } // ends if statement
        } // ends for loop

        // initializes new ArrayList for everything sorted to be concatenated
        ArrayList<Card> concat = new ArrayList<Card>();

        // sorts left and right ArrayLists recursively using the same method
        // concatenates all three ArrayLists once base case is reached and returned for all
        concat.addAll(sortDeckValueSuitQuick(left));
        concat.add(deck.get(pivot));
        concat.addAll(sortDeckValueSuitQuick(right));

        // returns value-suit sorted deck
        return concat;
    } // ends sortDeckValueSuitQuick() method
    /**

     * MAIN METHOD / DRIVER CODE
     */
    public static void main(String[] args) {

        // inits raw values and suits for the deck
        String[] valuesRaw = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suitsRaw = {"C", "D", "H", "S"};

        // assigns raw values + suits to hashmaps, preps for string comparison //

        // inits linked hash map
        LinkedHashMap<String, Integer> values = new LinkedHashMap<>();
        // tracks counter for assigning int to value key
        int vCount = 0;
        // loops for each raw string value
        for (String value : valuesRaw) {
            // assigns to map
            values.put(value, vCount);
            // increases int value
            vCount++;
        } // ends for loop

        // inits linked hash map
        LinkedHashMap<String, Integer> suits = new LinkedHashMap<>();
        // tracks counter for assigning int to suit key
        int sCount = 0;
        // loops for each raw string suit
        for (String suit : suitsRaw) {
            // assigns to map
            suits.put(suit, sCount);
            // increases int value
            sCount++;
        } // ends for loop

        // inits, builds, returns deck of 52 Card obj array
        Card[] deckBuilt = buildDeck(values, suits);
        // prints built deck VALUE - SUIT
        System.out.println("BUILT DECK:");
        printDeck(deckBuilt);

        // shuffles the array
        Card[] deckShuffled = shuffleDeck(deckBuilt);
        // prints shuffled deck VALUE - SUIT
        System.out.println("SHUFFLED DECK:");
        printDeck(deckShuffled);

        ArrayList<Card> deckLst = new ArrayList<Card>(Arrays.asList(deckShuffled).subList(0, deckShuffled.length));

        // sorts value-based, (2 thru A)
        ArrayList<Card> deckValueSorted = sortDeckValueMergeSort(deckLst, values);
        // prints value-based sorted deck
        System.out.println("VALUE SORTED DECK:");
        System.out.println(deckValueSorted.toString());

        // sorts suit/value-based, (C, D, H, S), (2 thru A)
        ArrayList<Card> deckValueSuitSorted = sortDeckValueSuitQuick(deckLst);
        // prints suit/value-based sorted deck
        System.out.println("VALUE-SUIT SORTED DECK:");
        System.out.println(deckValueSuitSorted.toString());

    } // ends main method

} // ends Main class
