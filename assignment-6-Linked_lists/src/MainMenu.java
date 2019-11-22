import java.util.Scanner;

public class MainMenu {
    private static Scanner kb = new Scanner(System.in);

    private static List inventoryItems1 = new List();
    private static List inventoryItems2 = new List();
    private static List string = new List();

    public static void main(String[] args) {
        int userChoice;

        do {
            println("Linked List manipulation\n\n" +
                    "Purpose Of Application:\n" +
                    "To showcase the Abstract Data Type Linked List, \n" +
                    "through 9 - 10 operations. Depending on the \n" +
                    "the Linked List you want to manipulate\n");
            println("OPTION 1) Inventory Linked List 1 \n" +
                    "OPTION 2) Inventory Linked List 2\n" +
                    "OPTION 3) String Linked List\n" +
                    "OPTION 4) Quit App\n");
            do {
                println("For OPTION 1 (ENTER '1') for OPTION 2 (ENTER '2')\n" +
                        "For OPTION 3 (ENTER '3') for OPTION 4 (ENTER '4')\n");
                userChoice = kb.nextInt();
            } while (!isUserChoiceForListsWithinRange(userChoice));
            switch (userChoice) {
                case 1:
                case 2:
                    manipulateInventoryList(userChoice);
                    break;
                case 3:
                    manipulateStringList();
                    break;
            }
        } while (!doesUserWantToQuit(userChoice));
        quit();

    }

    private static boolean isUserChoiceForListsWithinRange(int userChoice) {
        return userChoice >= 1 && userChoice <= 4;
    }

    private static boolean doesUserWantToQuit(int userChoice) {
        return userChoice == 4;
    }

    private static void dispOperationsInventory() {
        println("Operations: ");
        println("0) To Go back to main menu");
        println("1) Initialize: create an empty Linked List ");
        println("2) Insert: insert a given Item at specified Index");
        println("3) InsertAtEnd: Insert given Item at the end of the List ");
        println("4) DeleteRange: delete all items within a specified range");
        println("5) DeleteItem: delete all occurrences of a specified item");
        println("6) Retrieve: return the item at a specified Index ");
        println("7) Find: return a List of the positions of a specified Item");
        println("8) getSize: return the size of the List");
        println("9) show: Print your List");
        println("10)computeTotalValueOfInventory: computes the total value of the inventory\n");
    }
    private static void dispOperationsString() {
        println("Operations: ");
        println("0) To Go back to main menu");
        println("1) Initialize: create an empty Linked List ");
        println("2) Insert: insert a given Item at specified Index");
        println("3) InsertAtEnd: Insert given Item at the end of the List ");
        println("4) DeleteRange: delete all items within a specified range");
        println("5) DeleteItem: delete all occurrences of a specified item");
        println("6) Retrieve: return the item at a specified Index ");
        println("7) Find: return a List of the positions of a specified Item");
        println("8) getSize: return the size of the List");
        println("9) show: Print your List");
    }

    public static void manipulateInventoryList(int userChoiceForLists) {
        if (userChoiceForLists == 1) {
            println("You are now manipulating Inventory LinkedList #1");
        } else {
            println("You are now manipulating Inventory LinkedList #2");
        }
        int index;
        Link link;
        List listOfIndexes;
        dispOperationsInventory();
        int userChoice = kb.nextInt();
        while (!isUserWithinRange(userChoice, 10)) {
            dispOperationsInventory();
            println("Please enter between range 0 - 10");
            userChoice = kb.nextInt();
        }
        while (userChoice != 0) {
            println("\n");
            switch (userChoice) {
                case 1:
                    println("Operation One... ");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        inventoryItems1 = new List();
                    } else {
                        println("Inventory 2:");
                        inventoryItems2 = new List();
                    }
                    println("Complete...\n");
                    break;
                case 2:
                    println("Operation Two... ");
                    println("Enter Index(List starts @ 1): ");
                    index = kb.nextInt();
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        inventoryItems1.insert(index, createInventory());
                    } else {
                        println("Inventory 2:");
                        inventoryItems2.insert(index, createInventory());
                    }
                    println("Complete...\n");
                    break;
                case 3:
                    println("Operation Three... ");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        inventoryItems1.insertAtEnd(createInventory());
                    } else {
                        println("Inventory 2:");
                        inventoryItems2.insertAtEnd(createInventory());
                    }
                    println("Complete...\n");
                    break;
                case 4:
                    println("Operation Four... ");
                    println("Enter min range");
                    int minRange = kb.nextInt();
                    println("Enter max range");
                    int maxRange = kb.nextInt();
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        inventoryItems1.deleteRange(minRange, maxRange);
                    } else {
                        println("Inventory 2:");
                        inventoryItems2.deleteRange(minRange, maxRange);
                    }
                    println("Complete...\n");
                    break;
                case 5:
                    println("Operation Five... ");
                    println("Since we are manipulating an Inventory Linked list");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        inventoryItems1.deleteItem(createInventory());
                    } else {
                        println("Inventory 2:");
                        inventoryItems2.deleteItem(createInventory());
                    }
                    println("Complete...\n");
                    break;
                case 6:
                    println("Operation Six... ");
                    println("Enter Index(List starts @ 1): ");
                    index = kb.nextInt();
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        link = inventoryItems1.retrieve(index);
                        println("This is your link" + link + "");
                    } else {
                        println("Inventory 2:");
                        link = inventoryItems2.retrieve(index);
                        println("This is your link" + link + "");
                    }
                    println("Complete...\n");
                    println("\n");
                    break;
                case 7:
                    println("Operation Seven... ");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        listOfIndexes = inventoryItems1.find(createInventory());
                        println("The indexes for your desired Item");
                        println("" + listOfIndexes);
                    } else {
                        println("Inventory 2:");
                        listOfIndexes = inventoryItems2.find(createInventory());
                        println("The indexes for your desired Item");
                        println("" + listOfIndexes);
                    }
                    println("Complete...\n");
                    println("\n");
                    break;
                case 8:
                    println("Operation Eight... ");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        Object size = inventoryItems1.getSize();
                        println("The size of your list is " + size);
                    } else {
                        println("Inventory 2:");
                        Object size = inventoryItems2.getSize();
                        println("The size of your list is " + size);
                    }
                    println("Complete...\n");
                    break;
                case 9:
                    println("Operation Nine... ");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        println(inventoryItems1 + "");
                    } else {
                        println("Inventory 2:");
                        println(inventoryItems2 + "");
                    }
                    println("Complete...\n");
                    println("\n");
                    break;
                case 10:
                    println("Operation Ten... ");
                    if (userChoiceForLists == 1) {
                        println("Inventory 1:");
                        println("Total value of inventory 1 is: " + computeTotalValue(inventoryItems1));
                    } else {
                        println("Inventory 2:");
                        println("Total value of inventory 2 is: " + computeTotalValue(inventoryItems2));

                    }
                    println("Complete...\n");
                    break;
            }
            dispOperationsInventory();
            userChoice = kb.nextInt();
        }
        println("0) GOING BACK TO MAIN MENU...");
    }

    public static void manipulateStringList() {
        println("Now Manipulating String List:");
        int index;
        Link link;
        List listOfIndexes;
        dispOperationsString();
        int userChoice = kb.nextInt();
        while (!isUserWithinRange(userChoice, 9)) {
            dispOperationsInventory();
            println("Please enter between range 0 - 9");
            userChoice = kb.nextInt();
        }
        String data;
        while (userChoice != 0) {
            println("\n");
            switch (userChoice) {
                case 1:
                    println("String List:");
                    println("Operation One... ");
                    string = new List();
                    println("Complete...\n");
                    break;
                case 2:
                    println("String List:");
                    println("Operation Two... ");
                    println("Enter Index(List starts @ 1): ");
                    index = kb.nextInt();
                    println("Enter Data(String)");
                    data =  kb.next();
                    string.insert(index, data);
                    println("Complete...\n");
                    break;
                case 3:
                    println("String List:");
                    println("Operation Three... ");
                    data =  kb.next();
                    string.insertAtEnd(data);
                    println("Complete...\n");
                    break;
                case 4:
                    println("String List:");
                    println("Operation Four... ");
                    println("Enter min range");
                    int minRange = kb.nextInt();
                    println("Enter max range");
                    int maxRange = kb.nextInt();
                    string.deleteRange(minRange, maxRange);
                    println("Complete...\n");
                    break;
                case 5:
                    println("String List:");
                    println("Operation Five... ");
                    println("Since we are manipulating an Inventory String list");
                    println("Enter Data(String)");
                    data =  kb.next();
                    string.deleteItem(data);
                    println("Complete...\n");
                    break;
                case 6:
                    println("String List:");
                    println("Operation Six... ");
                    println("Enter Index(List starts @ 1): ");
                    index = kb.nextInt();
                    link = string.retrieve(index);
                    println("This is your link:\n" + link + "");
                    println("Complete...\n");
                    println("\n");
                    break;
                case 7:
                    println("String List:");
                    println("Operation Seven... ");
                    println("Enter Data(String)");
                    data =  kb.next();
                    listOfIndexes = string.find(data);
                    println("The indexes for your desired Item");
                    println("" + listOfIndexes);
                    println("Complete...\n");
                    println("\n");
                    break;
                case 8:
                    println("String List:");
                    println("Operation Eight... ");
                    Object size = string.getSize();
                    println("The size of your list is " + size);
                    println("Complete...\n");
                    break;
                case 9:
                    println("String List:");
                    println("Operation Nine... ");
                    println(string + "");
                    println("Complete...\n");
                    println("\n");
                    break;
            }
            dispOperationsString();
            userChoice = kb.nextInt();
        }
        println("0) GOING BACK TO MAIN MENU...");
    }

    private static Double computeTotalValue(List inventoryItems) {
        Integer size = (Integer) inventoryItems.getSize();
        Double value = 0.0;
        for (int i = 1; i <= size; i++) {
            if (inventoryItems.retrieve(i).data instanceof Inventory) {
                Inventory inventory = (Inventory) inventoryItems.retrieve(i).data;
                value += inventory.value * inventory.quantity;
            }
        }
        return value;
    }

    private static void quit() {
        println("End Of App\n" +
                "Closing...");
    }

    private static Object createInventory() {
        println("Enter String Item: ");
        String item = kb.next();
        println("Enter Double Quantity: ");
        Double quantity = kb.nextDouble();
        println("Enter Double Value: ");
        Double value = kb.nextDouble();
        return new Inventory(item, quantity, value);
    }


    private static boolean isUserWithinRange(int userChoice, int maxBranch) {
        return userChoice >= 0 && userChoice <= maxBranch;
    }


    private static void println(String text) {
        System.out.println(text);
    }

    private static void print(String text) {
        System.out.print(text);
    }

}
