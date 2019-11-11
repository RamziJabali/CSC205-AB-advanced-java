import java.util.Random;
import java.util.Scanner;

public class Program4 {
    public static Scanner kb = new Scanner(System.in);
    public static Random rand = new Random();
    private static DeckHand deckHand = new DeckHand();
    private static final String MENU = "Enter\n" +
            "(1) to test deck hand\n" +
            "(2) to Play \"Go Fish\" \n" +
            "(3) to Quit\n";
    private static final String FINISHED_PROCESS = "Process finished...";
    private static final String DECKHAND_FUNCTIONS = "Deckhand Test Mode:\n" +
            "(1) To Insert card in deck \n" +
            "(2) To remove card \n" +
            "(3) To remove random card\n" +
            "(4) Display all cards in deck \n" +
            "(5) To get how many cards you have in your deck\n" +
            "(6) To get a count on a certain card of your choosing\n" +
            "(7) To exit";
    private static final String CARD_NUMBER = "Enter a number for suit:" +
            "(1) for Clubs\n" +
            "(2) for Diamonds\n" +
            "(3) for Hearts\n" +
            "(4) for Spades";

    private static final String CARD_NUMBER_2 = "Enter a number for the value of the card:\n" +
            "(1) for Ace" +
            "(2)-(10) are numbers that would accompany the suit value\n" +
            "(11) for Jack\n" +
            "(12) for Queen\n" +
            "(13) for King";
    private static final String REMOVE_CARD_2 = "Now enter Value number:" +
            "(1)-(13)\n";
    private static final String END_APP = "Ending APP.......";
    private static final String USER_TURN = "USER TURN:......";
    private static final String COMPUTER_TURN = "COMPUTER TURN:......";
    private static final String PRESS_ENTER = "PRESS [ENTER] TO CONTINUE";
    private static final String ACE = "YOU CAN CHOOSE ACE [1]";
    private static final String JACK = "YOU CAN CHOOSE JACK [2]";
    private static final String QUEEN = "YOU CAN CHOOSE QUEEN [3]";
    private static final String KING = "YOU CAN CHOOSE KING [4]";

    private static int numberOfCardsInStock = 38;
    private static int computerNumberOfBooks = 0;
    private static int userNumberOfBooks = 0;
    private static int userDraws = 0;
    private static int computerDraws = 0;
    private static int playerTurn = 0;
    private static final int NUM_OF_CARDS_TO_START = 3;

    public static void main(String[] args) {

        int userChoice;
        do {
            print(MENU);
            userChoice = kb.nextInt();
            switch (userChoice) {
                case 1:
                    testDeckHand();
                    break;
                case 2:
                    goFish();
                    break;
                case 3:
                    quitApplication();
                    break;
            }
        } while (userChoice != 3);
    }

    private static void goFish() {
        println("You are now playing GO FISH");
        DeckHand userDeck = new DeckHand();
        DeckHand computerDeck = new DeckHand();
        for (int i = 0; i < NUM_OF_CARDS_TO_START; i++) {
            userDeck.insertCard(rand.nextInt(4) + 1, getCardTrueValue(rand.nextInt(4) + 1));
            computerDeck.insertCard(rand.nextInt(4) + 1, getCardTrueValue(rand.nextInt(4) + 1));
        }
        do {
            if (playerTurn % 2 == 0) {
                playerTurn(userDeck, computerDeck);
            } else {
                computerTurn(computerDeck, userDeck);
            }
        } while (!winnerFound(computerDeck, userDeck) || outOfCards());
        if (!outOfCards()) {
            theWinnerIs(computerDeck, userDeck);
        } else {
            winnerByNumberOfHandsTheyHaveIs();
        }

    }

    private static void winnerByNumberOfHandsTheyHaveIs() {
        if (userNumberOfBooks > computerNumberOfBooks) {
            println("The Winner is the USER");
        } else if (userNumberOfBooks == computerNumberOfBooks) {
            println("we have a draw");
        } else {
            println("The Winner is the COMPUTER");
        }
    }

    private static void theWinnerIs(DeckHand comp, DeckHand user) {
        if (user.getSize() == 0) {
            println("The Winner is the USER");
        }
        if (comp.getSize() == 0) {
            println("The Winner is the COMPUTER");
        }

    }

    private static boolean outOfCards() {
        return numberOfCardsInStock == 0;
    }

    private static void computerTurn(DeckHand computerDeck, DeckHand user) {
        int ace = user.count(1);
        int spades = user.count(11);
        int queen = user.count(12);
        int king = user.count(13);


        if (canWeRemoveBookOfCardsComp(ace)) {
            removeBookFromDeck(user, 1);
            computerNumberOfBooks++;
        }
        if (canWeRemoveBookOfCardsComp(spades)) {
            removeBookFromDeck(user, 11);
            computerNumberOfBooks++;
        }
        if (canWeRemoveBookOfCardsComp(queen)) {
            removeBookFromDeck(user, 12);
            computerNumberOfBooks++;
        }
        if (canWeRemoveBookOfCardsComp(king)) {
            removeBookFromDeck(user, 13);
            computerNumberOfBooks++;
        }
        println(COMPUTER_TURN);
        int compCardValueChoice;
        do {
            compCardValueChoice = rand.nextInt(4) + 1;
        } while (!checkPlayerChoice(compCardValueChoice, ace, spades, queen, king));
        int numCardOfSameRank = user.count(getCardTrueValue(compCardValueChoice));
        if (numCardOfSameRank > 0) {
            println("User has the rank you computer asked for.\n" +
                    "user gives computer his/her card\n\n");
            for (int i = 0; i < numCardOfSameRank; i++) {
                int suit = user.removeCard(getCardTrueValue(compCardValueChoice)).getSuit();
                computerDeck.insertCard(suit, getCardTrueValue(compCardValueChoice));
            }
            playerTurn++;
        } else {
            int value = rand.nextInt(13) + 1;
            int suit = rand.nextInt(4) + 1;
            user.insertCard(suit, value);
            numberOfCardsInStock--;
            println("User Does not have the rank computers asked for \n" +
                    "user pulls from stock");
            userDraws++;
            if (getCardTrueValue(compCardValueChoice) == value) {
                println("User new card is that of computers rank\n" +
                        "computer plays again\n\n");
                user.removeCard(value);
                computerDeck.insertCard(suit, value);
            } else {
                playerTurn++;
            }
        }
        println(PRESS_ENTER + "\n \n");
        kb.nextLine();
    }

    private static void playerTurn(DeckHand user, DeckHand computer) {
        int ace = user.count(1);
        int jack = user.count(11);
        int queen = user.count(12);
        int king = user.count(13);

        if (canWeRemoveBookOfCardsUser(ace)) {
            removeBookFromDeck(user, 1);
            userNumberOfBooks++;
        }
        if (canWeRemoveBookOfCardsUser(jack)) {
            removeBookFromDeck(user, 11);
            userNumberOfBooks++;
        }
        if (canWeRemoveBookOfCardsUser(queen)) {
            removeBookFromDeck(user, 12);
            userNumberOfBooks++;
        }
        if (canWeRemoveBookOfCardsUser(king)) {
            removeBookFromDeck(user, 13);
            userNumberOfBooks++;
        }
        println(USER_TURN);
        println("User Cards:\n" + user.toString());
        println("user number of cards: " + user.getSize());
        int userCardValueChoice;
        do {
            if (ace > 0) {
                println(ACE);
            }
            if (jack > 0) {
                println(JACK);
            }
            if (queen > 0) {
                println(QUEEN);
            }
            if (king > 0) {
                println(KING);
            }
            userCardValueChoice = kb.nextInt();
        } while (!checkPlayerChoice(userCardValueChoice, ace, jack, queen, king));
        int numCardOfSameRank = computer.count(getCardTrueValue(userCardValueChoice));
        if (numCardOfSameRank > 0) {
            println("Computer has the rank you asked for.\n" +
                    "computer gives you it's card");
            for (int i = 0; i < numCardOfSameRank; i++) {
                int suit = computer.removeCard(getCardTrueValue(userCardValueChoice)).getSuit();
                user.insertCard(suit, getCardTrueValue(userCardValueChoice));
            }
            playerTurn++;
        } else {
            int value = rand.nextInt(13) + 1;
            int suit = rand.nextInt(4) + 1;
            computer.insertCard(suit, value);
            println("Computer Does not have the rank you ask for \n" +
                    "computer pulls from stock");
            computerDraws++;
            numberOfCardsInStock--;
            if (getCardTrueValue(userCardValueChoice) == value) {
                println("Computer has the rank you asked for.\n" +
                        "computer gives you it's card");
                computer.removeCard(value);
                user.insertCard(suit, value);
            } else {
                playerTurn++;
            }
        }
        println("user number of cards: " + user.getSize());
        println(PRESS_ENTER + "\n \n");
        kb.nextLine();
        kb.nextLine();
    }

    private static int getCardTrueValue(int cardValue) {
        switch (cardValue) {
            case 1:
                return 1;
            case 2:
                return 11;
            case 3:
                return 12;
            case 4:
                return 13;
            default:
                return 0;
        }
    }

    private static boolean checkPlayerChoice(int playerChoice, int ace, int jack, int queen, int king) {
        if (playerChoice == 1 && ace < 1) {
            return false;
        } else if (playerChoice == 2 && jack < 1) {
            return false;
        } else if (playerChoice == 3 && queen < 1) {
            return false;
        } else if (playerChoice == 4 && king < 1) {
            return false;
        }

        return true;
    }

    private static void printOptionsForUser(int ace, int jack, int queen, int king) {
        if (ace > 0) {
            println(ACE);
        }
        if (jack > 0) {
            println(JACK);
        }
        if (queen > 0) {
            println(QUEEN);
        }
        if (king > 0) {
            println(KING);
        }
    }

    private static boolean canWeRemoveBookOfCardsUser(int value) {
        return value >= 4 && computerDraws > 0;
    }

    private static boolean canWeRemoveBookOfCardsComp(int value) {
        return value >= 4 && userDraws > 0;

    }

    private static void removeBookFromDeck(DeckHand player, int value) {
        for (int i = 0; i < 4; i++) {
            player.removeCard(value);
        }
    }

    private static boolean winnerFound(DeckHand comp, DeckHand user) {
        return comp.getSize() == 0 || user.getSize() == 0;
    }

    private static void quitApplication() {
        print(END_APP);
    }

    private static void testDeckHand() {
        int userChoice;
        do {
            println(DECKHAND_FUNCTIONS);
            userChoice = kb.nextInt();
            switch (userChoice) {
                case 1:
                    println(CARD_NUMBER);
                    println(CARD_NUMBER_2);
                    deckHand.insertCard(kb.nextInt(), kb.nextInt());
                    println(FINISHED_PROCESS);
                    break;
                case 2:
                    println(REMOVE_CARD_2);
                    System.out.println(deckHand.removeCard(kb.nextInt()));
                    println(FINISHED_PROCESS);
                    break;
                case 3:
                    System.out.println(deckHand.removeRandomCard());
                    println(FINISHED_PROCESS);
                    break;
                case 4:
                    println(deckHand.toString());
                    println(FINISHED_PROCESS);
                    break;
                case 5:
                    println("Number of cards in your deck are " + deckHand.getSize());
                    println(FINISHED_PROCESS);
                case 6:
                    println("insert a Value and i'll tell you how many times that value comes up in the deck!");
                    println("This value occurs " + deckHand.count(kb.nextInt()) + " times");
                    println(FINISHED_PROCESS);
                case 7:
                    return;
            }
        } while (userChoice != 7);
    }

    private static void print(String text) {
        System.out.print(text);
    }

    private static void println(String text) {
        System.out.println(text);
    }
}
