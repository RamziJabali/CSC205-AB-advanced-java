import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program3 {
    Program3Model model = new Program3Model();
    Scanner kb = new Scanner(System.in);

    public Program3() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException {
        Program3 startApp = new Program3();
        startApp.dataBaseToArray();
        startApp.startOfAppFixes();
        startApp.mainMenu();
    }

    private void startOfAppFixes() {
        model.isAppOver = false;
        model.wasRecordFound = false;
        model.sumOfPricesBetweenRange = 0;
        model.counter = 0;
    }


    private void mainMenu() {
        if (!model.isAppOver) {
            println(Program3Model.OPTIONS);
            do {
                println(Program3Model.PICK_OPTION);
                model.input = kb.next();
                model.inputInteger = getValueOrNegativeOneForIntegers(model.input);
            } while (!isUserWithinOptionRange());
            switch (model.inputInteger) {
                case 1:
                    displayDataFileOptionOne();
                    break;
                case 2:
                    displayRecordOptionTwo();
                    break;
                case 3:
                    displayCumulativeStatisticsOption3();
                    break;
                case 4:
                    displayHistogramOption4();
                    break;
                default:
                    break;
            }
        }
    }

    private void displayDataFileOptionOne() {
        println(Program3Model.OPTION_1);
        printDataFileArray();
        if (doesUserWantToGoBackToMenu()) {
            mainMenu();
        } else {
            model.isAppOver = true;
            endOfApp();
        }
    }

    private void displayRecordOptionTwo() {
        getAndCheckUserInputForString();
        model.wasRecordFound = false;
        for (int i = 0; i < model.dataBaseInArray.length; i++) {
            if (model.dataBaseInArray[i][0] == null) {
                break;
            }
            if (model.dataBaseInArray[i][0].equalsIgnoreCase(model.input)) {
                println(Program3Model.OPTION_2);
                model.wasRecordFound = true;
                System.out.printf("%-23s %-23s %-23s %-24s", Program3Model.SHOE_NAME, Program3Model.SHOE_PRICE, Program3Model.SHOE_MATERIAL, Program3Model.SHOE_RATING);
                println("");
                for (int j = 0; j < model.dataBaseInArray[i].length; j++) {
                    System.out.printf("%-20s", model.dataBaseInArray[i][j]);
                    print(Program3Model.TAB);
                }
                println("");
            }
        }
        if (!model.wasRecordFound) {
            println(Program3Model.THIS_RECORD_DOES_NOT_EXIST);
        }
        if (doesUserWantToGoBackToMenu()) {
            mainMenu();
        } else {
            model.isAppOver = true;
            endOfApp();
        }
    }

    private void displayCumulativeStatisticsOption3() {
        dataValidationOption3();
        model.counter = 0;
        for (int i = 0; i < model.dataBaseInArray.length; i++) {
            if (isPriceWithinRange(getValueOrNegativeOneForIntegers(model.dataBaseInArray[i][1]))) {
                model.sumOfPricesBetweenRange += getValueOrNegativeOneForIntegers(model.dataBaseInArray[i][1]);
                model.counter++;
            }
        }
        model.averagePriceOfJordansBetweenRange = model.sumOfPricesBetweenRange / model.counter;
        println(Program3Model.OPTION_3);
        println(Program3Model.THE_AVERAGE_PRICE_1 + model.inputIntegerLowerRange + Program3Model.THE_AVERAGE_PRICE_2 + model.inputIntegerHigherRange
                + Program3Model.THE_AVERAGE_PRICE_3 + model.averagePriceOfJordansBetweenRange);

        if (doesUserWantToGoBackToMenu()) {
            mainMenu();
        } else {
            model.isAppOver = true;
            endOfApp();
        }
    }

    private void displayHistogramOption4() {
        createHistogram();
        System.out.printf("%-8s %-23s", Program3Model.DECADE_STRING, Program3Model.COUNT);
        println("");
        for (int i = 0; i < model.histogram.length; i++) {
            for (int j = 0; j < model.histogram[i].length; j++) {
                System.out.printf("%-5s", model.histogram[i][j]);
                print(Program3Model.TAB);
            }
            println(model.histogramStars[i]);
        }
        if (doesUserWantToGoBackToMenu()) {
            mainMenu();
        } else {
            model.isAppOver = true;
            endOfApp();
        }
    }

    private void endOfApp() {
        println(Program3Model.END_OF_APP);
    }

    //DATA PROCESSING DATA PROCESSING DATA PROCESSING DATA PROCESSING
    //DATA PROCESSING DATA PROCESSING DATA PROCESSING DATA PROCESSING
    private void createHistogram() {
        model.minAndMaxDividedDecade = (Program3Model.MAX_SHOE_PRICE - Program3Model.MIN_SHOE_PRICE) / Program3Model.DECADE;
        model.histogram[0][0] = Program3Model.MIN_SHOE_PRICE;
        for (int i = 1; i < model.histogram.length; i++) {
            model.histogram[i][0] = model.histogram[i - 1][0] + model.minAndMaxDividedDecade;
            model.histogram[i][1] = 0;
        }
        for (int i = 1; i < model.histogram.length; i++) {
            model.counter = 0;
            for (int j = 0; j < model.dataBaseInArray.length; j++) {
                if (isPriceWithinOption4Range(model.histogram[i][0], model.histogram[i - 1][0], getValueOrNegativeOneForIntegers(model.dataBaseInArray[j][1]))) {
                    model.counter++;
                }
            }
            model.histogram[i][1] = model.counter;
        }
        for (int i = 0; i < model.histogramStars.length; i++) {
            model.histogramStars[i] = "";
            for (int j = 0; j < model.histogram[i][1]; j++) {
                model.histogramStars[i] += Program3Model.STAR;
            }
        }
        return;
    }

    //DATA VALIDATION DATA VALIDATION DATA VALIDATION DATA VALIDATION
    //DATA VALIDATION DATA VALIDATION DATA VALIDATION DATA VALIDATION
    private boolean isPriceWithinOption4Range(int max, int min, int price) {
        return price <= max && price >= min;
    }

    private boolean isPriceWithinRange(int price) {
        return price >= model.inputIntegerLowerRange && price <= model.inputIntegerHigherRange;
    }

    private void dataValidationOption3() {
        println(Program3Model.OPTION_3_ENTER_RANGE);
        do {
            do {
                println(Program3Model.MAXIMUM_PRICE);
                model.input = kb.next();
                model.inputIntegerHigherRange = getValueOrNegativeOneForIntegers(model.input);
            } while (model.inputIntegerHigherRange == -1);

            do {
                println(Program3Model.MINIMUM_PRICE);
                model.input = kb.next();
                model.inputIntegerLowerRange = getValueOrNegativeOneForIntegers(model.input);
            } while (model.inputIntegerHigherRange == -1);
        } while (model.inputIntegerHigherRange <= model.inputIntegerLowerRange);
    }

    private void getAndCheckUserInputForString() {
        println(Program3Model.OPTION_2_ENTER_KEY_WORD);
        model.input = kb.next();
        for (int i = 0; i < model.input.length(); i++) {
            if (Character.isDigit(model.input.charAt(i))) {
                getAndCheckUserInputForString();
            }
        }
    }

    private boolean isUserWithinOptionRange() {
        return model.inputInteger >= Program3Model.MIN_OPTION_NUM && model.inputInteger <= Program3Model.MAX_OPTION_NUM;
    }

    private boolean doesUserWantToGoBackToMenu() {
        do {
            println(Program3Model.DO_YOU_WANT_TO_STOP);
            model.input = kb.next();
            model.inputInteger = getValueOrNegativeOneForIntegers(model.input);
        } while (!isUserWithinMainMenuOrEndOfAppRange());
        return model.inputInteger == 1;
    }

    private boolean isUserWithinMainMenuOrEndOfAppRange() {
        return model.inputInteger >= Program3Model.MIN_MAINMENU_NUM && model.inputInteger <= Program3Model.MAX_MAINMENU_NUM;
    }

    //CONVERSION METHODS CONVERSION METHODS CONVERSION METHODS

    //CONVERSION METHODS CONVERSION METHODS CONVERSION METHODS

    private int getValueOrNegativeOneForIntegers(String input) {
        int result;
        try {
            result = Integer.valueOf(input);
        } catch (NumberFormatException exception) {
            result = -1;
        }
        return result;
    }


    private void dataBaseToArray() {
        for (int i = 0; i < model.dataBaseInArray.length; i++) {
            for (int j = 0; j < model.dataBaseInArray[i].length; j++) {
                Program3Model.nextWord = model.readDataBase.next();//Used to avoid Try and Catch
                if (!Program3Model.nextWord.equals(Program3Model.EOF)) {
                    model.dataBaseInArray[i][j] = Program3Model.nextWord;
                } else {
                    return;
                }
            }
        }
    }
    //PRINT METHODS PRINT METHODS PRINT METHODS

    //PRINT METHODS PRINT METHODS PRINT METHODS

    private void printDataFileArray() {
        System.out.printf("%-23s %-23s %-23s %-24s", Program3Model.SHOE_NAME, Program3Model.SHOE_PRICE, Program3Model.SHOE_MATERIAL, Program3Model.SHOE_RATING);
        println("");
        for (int i = 0; i < model.dataBaseInArray.length; i++) {
            for (int j = 0; j < model.dataBaseInArray[i].length; j++) {
                if (model.dataBaseInArray[i][j] == null) {
                    return;
                }
                System.out.printf("%-20s", model.dataBaseInArray[i][j]);
                print(Program3Model.TAB);
            }
            println("");
        }
    }

    private void print(String text) {
        System.out.print(text);
    }

    private void println(String text) {
        System.out.println(text);
    }
}

 class Program3Model {

    public final String FILE_PATH = "/Users/ramzijabali/Code/Program3/src/dataBase.txt";
    public Scanner kb = new Scanner(System.in);
    public File dataBase = new File(FILE_PATH);
    public Scanner readDataBase = new Scanner(dataBase);

    public static final String INTRO_MESSAGE = "I will first display the data base so \n" +
            "you can look at the data base and pick ";
    public static final String OPTIONS = "OPTION 1) Display Entire Data File \n" +
            "OPTION 2) Display a selected record\n" +
            "OPTION 3) Enter a Price range and find out the average price of an Air Jordan Shoe\n" +
            "OPTION 4) Display a \"Histogram\" that summarizes the distribution of data.";
    public static String PICK_OPTION = "for OPTION 1 (ENTER '1') for OPTION 2 (ENTER '2')\n" +
            "for OPTION 3 (ENTER '3') for OPTION 4 (ENTER '4'):";
    public static String OPTION_1 = "OPTION 1.........\n" +
            "NOW DISPLAYING DATA..........";
    public static String OPTION_2 = "OPTION 2.........\n" +
            "NOW DISPLAYING RECORD..........";
    public static String OPTION_3 = "OPTION 3.........\n" +
            "NOW DISPLAYING ANSWER..........";
    public static String OPTION_2_ENTER_KEY_WORD = "Enter the key field for the record:\n";
    public static String OPTION_3_ENTER_RANGE = "ENTER a Maximum price and minimum price and I will \n" +
            "find the average price of that range:";
    public static String MINIMUM_PRICE = "ENTER MINIMUM PRICE";
    public static String MAXIMUM_PRICE = "ENTER MAXIMUM PRICE";
    public static String THE_AVERAGE_PRICE_1 = "The Average price of an AirJordan shoe between \n";
    public static String THE_AVERAGE_PRICE_2 = " and ";
    public static String THE_AVERAGE_PRICE_3 = " is $";
    public static String END_OF_APP = "Closing app.............";
    public static String DO_YOU_WANT_TO_STOP = "1 to go back to MAIN MENU & 2 to END APP";
    public static String THIS_RECORD_DOES_NOT_EXIST = "SORRY THE KEY FIELD YOU ENTERED DOES NOT EXIST";
    public static String SHOE_NAME = "Shoe Name";
    public static String SHOE_PRICE = "Shoe Price";
    public static String SHOE_MATERIAL = "Shoe Material";
    public static String SHOE_RATING = "Shoe Rating";
    public static String EOF = "EOF";
    public static String DECADE_STRING = "Decade:";
    public static String COUNT= "Count:";
    public static String STAR= "*";
    public static String TAB = "    ";


    public static int ARRAY_DATABASE_SIZE_ROW = 80;
    public static int ARRAY_DATABASE_SIZE_COLUMN = 4;
    public static int ARRAY_HISTOGRAM_SIZE_COLUMN = 2;

    public static int MAX_OPTION_NUM = 4;
    public static int MIN_OPTION_NUM = 1;
    public static int MAX_MAINMENU_NUM = 2;
    public static int MIN_MAINMENU_NUM = 1;
    public static int MAX_SHOE_PRICE = 350;
    public static int MIN_SHOE_PRICE = 140;
    public static int DECADE = 10;

    public static String nextWord;

    public String input;

    public int inputInteger;
    public int inputIntegerLowerRange;
    public int inputIntegerHigherRange;
    public int sumOfPricesBetweenRange;
    public int counter;
    public int averagePriceOfJordansBetweenRange;
    public int minAndMaxDividedDecade;


    public boolean wasRecordFound;
    public boolean isAppOver;


    public String[][] dataBaseInArray = new String[ARRAY_DATABASE_SIZE_ROW][ARRAY_DATABASE_SIZE_COLUMN];
    public String[] histogramStars = new String[DECADE];
    public int[][] histogram = new int[DECADE][ARRAY_HISTOGRAM_SIZE_COLUMN];

    public Program3Model() throws FileNotFoundException {
    }
}
