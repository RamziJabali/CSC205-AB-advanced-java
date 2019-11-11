import java.util.Random;

public class DeckHand {
    private static Random rand = new Random();
    private final int DECK_SIZE = 52;
    private Card[] deckOfCards;


    public DeckHand() {
        deckOfCards = new Card[DECK_SIZE];
    }

    public void insertCard(int suit, int value) {
        Card insertCard = new Card(suit, value);
        if (getSize() == deckOfCards.length) {
            makeBiggerArray();
        }
        deckOfCards[getSize()] = insertCard;
    }

    private void makeBiggerArray() {
        Card[] biggerArray = new Card[deckOfCards.length * 2];
        for (int i = 0; i < deckOfCards.length; i++) {
            biggerArray[i] = deckOfCards[i];
        }
        deckOfCards = biggerArray;
    }

    public int getSize() {
        int deckOfCardsSize = 0;
        for (int i = 0; i < deckOfCards.length; i++) {
            if (deckOfCards[i] != null) {
                deckOfCardsSize++;
            }
        }
        return deckOfCardsSize;
    }

    public Card removeCard(int value) {//TODO: fix
        Card discardedCard;
        int index = -1;
        if (getSize() == 0) {
            return null;
        }
        do {
            index++;
            if (deckOfCards[index] != null && deckOfCards[index].getValue() == value) {
                discardedCard = new Card(deckOfCards[index].getSuit(), deckOfCards[index].getValue());
                deckOfCards[index] = null;
                return discardedCard;
            }
        } while (index < deckOfCards.length);
        return null;
    }

    public Card removeRandomCard() {
        return removeCard(rand.nextInt(13) + 1);
    }

    public int count(int value) {
        int numberOfTimeACardExists = 0;
        for (int i = 0; i < deckOfCards.length; i++) {
            if (deckOfCards[i] != null && deckOfCards[i].getValue() == value) {
                numberOfTimeACardExists++;
            }
        }
        return numberOfTimeACardExists;
    }

    @Override
    public String toString() {
        String deckOfCardsString = "";
        for (int i = 0; i < deckOfCards.length; i++) {
            if (deckOfCards[i] != null) {
                deckOfCardsString += deckOfCards[i].toString() + "\n";
            }
        }

        return deckOfCardsString;
    }


    private Card getCard(int suit, int value) {
        return new Card(suit, value);
    }
}
