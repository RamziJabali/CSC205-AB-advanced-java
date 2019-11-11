public class Card {
    private int value;
    private int suit;

    public Card(int _suit, int _value) {
        suit = _suit;
        value = _value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (value) {
            case 1:
                return "Ace Of "+suitReadable();
            case 2:
                return "2 Of "+suitReadable();
            case 3:
                return "3 Of "+suitReadable();
            case 4:
                return "4 Of "+suitReadable();
            case 5:
                return "5 Of "+suitReadable();
            case 6:
                return "6 Of "+suitReadable();
            case 7:
                return "7 Of "+suitReadable();
            case 8:
                return "8 Of "+suitReadable();
            case 9:
                return "9 Of "+suitReadable();
            case 10:
                return "10 Of "+suitReadable();
            case 11:
                return "Jack Of "+suitReadable();
            case 12:
                return "Queen Of "+suitReadable();
            case 13:
                return "King Of "+suitReadable();
        }
        return "";
    }

    private String suitReadable() {
        switch (suit){
            case 1:
                return "Clubs";
            case 2:
                return "Diamonds";
            case 3:
                return "Hearts";
            case 4:
                return "Spades";
        }
        return null;
    }
}
