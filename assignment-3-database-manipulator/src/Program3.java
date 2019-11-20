
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
CRUCIAL NOTES:
- correct calculations
- correct results
- good program presentation
- good program organization
- good user interface
- main issue is not the final result, but the way
it was previously coded.
 */
public class Program3 {
    private static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        Record[] dataBaseArray = new Record[100];
        int numOfRecords = dataBaseToArray(dataBaseArray);
        mainMenu(numOfRecords, dataBaseArray);
    }

    private static void mainMenu(int numOfRecords, Record[] dataBaseArray) {
        int userInput;
        do {
            println("OPTION 1) Display Entire Data File \n" +
                    "OPTION 2) Display a selected record\n" +
                    "OPTION 3) Enter a Price range and find out the average price of an Air Jordan Shoe\n" +
                    "OPTION 4) Display a \"Histogram\" that summarizes the distribution of data.\n" +
                    "OPTION 5) Quit App");
            do {
                println("For OPTION 1 (ENTER '1') for OPTION 2 (ENTER '2')\n" +
                        "For OPTION 3 (ENTER '3') for OPTION 4 (ENTER '4')\n" +
                        "For OPTION 5 (ENTER '5'):");
                String input = kb.next();
                userInput = getValueOrNegativeOneForIntegers(input);
            } while (!isUserWithinOptionRange(userInput));
            switch (userInput) {
                case 1:
                    displayDataFileOptionOne(numOfRecords, dataBaseArray);
                    break;
                case 2:
                    displayRecordOptionTwo(numOfRecords, dataBaseArray);
                    break;
                case 3:
                    displayCumulativeStatisticsOption3(numOfRecords, dataBaseArray);
                    break;
                case 4:
                    displayHistogramOption4(numOfRecords, dataBaseArray);
                    break;
                case 5:
                    break;
            }
        } while (doesUserWantToGoBackToMenu(userInput));
        endOfApp();
    }

    private static void displayDataFileOptionOne(int numOfRecords, Record[] dataBaseArray) {
        println("OPTION 1.........\n" +
                "NOW DISPLAYING DATA..........");
        printDataFileArray(numOfRecords, dataBaseArray);
    }

    private static void displayRecordOptionTwo(int numOfRecords, Record[] dataBaseArray) {
        String input = getAndCheckUserInputForString();
        boolean wasRecordFound = false;
        int recordIndex;
        for (int i = 0; i < numOfRecords; i++) {
            //the loop that scans the data fo ra match must have two exit conditions
            //a) A match is found or b) the last record is passed
            if (dataBaseArray[i].name.equalsIgnoreCase(input)) {
                println("OPTION 2.........\n" +
                        "NOW DISPLAYING RECORD..........");
                recordIndex = i;
                wasRecordFound = true;
                System.out.printf("%-23s %-23s %-23s %-24s", "Shoe Name", "Shoe Price", "Shoe Material", "Shoe Rating");
                println("");
                for (int c = 1; c <= 4; c++) {
                    switch (c) {
                        case 1:
                            System.out.printf("%-20s", dataBaseArray[recordIndex].name);
                            break;
                        case 2:
                            System.out.printf("%-20s", dataBaseArray[recordIndex].price);
                            break;
                        case 3:
                            System.out.printf("%-20s", dataBaseArray[recordIndex].material);
                            break;
                        case 4:
                            System.out.printf("%-20s", dataBaseArray[recordIndex].rating);
                            break;
                    }
                    print("    ");
                }
                println("");
            }
        }
        if (!wasRecordFound) {
            //the loop that scans the data for a match must have two exit conditions
            //a) A match is found or b) the last record is passed
            println("SORRY THE KEY FIELD YOU ENTERED DOES NOT EXIST\n" +
                    "Going to pass final record");
            System.out.printf("%-23s %-23s %-23s %-24s", "Shoe Name", "Shoe Price", "Shoe Material", "Shoe Rating");
            println("");
            for (int c = 1; c <= 4; c++) {
                switch (c) {
                    case 1:
                        System.out.printf("%-20s", dataBaseArray[numOfRecords - 1].name);
                        break;
                    case 2:
                        System.out.printf("%-20s", dataBaseArray[numOfRecords - 1].price);
                        break;
                    case 3:
                        System.out.printf("%-20s", dataBaseArray[numOfRecords - 1].material);
                        break;
                    case 4:
                        System.out.printf("%-20s", dataBaseArray[numOfRecords - 1].rating);
                        break;
                }
                print("    ");
            }
        }
        println("");
    }

    private static void displayCumulativeStatisticsOption3(int numOfRecords, Record[] dataBaseArray) {
        int range[] = dataValidationOption3();
        int counter = 0;
        int averagePriceOfJordansBetweenRange;
        int sumOfPricesBetweenRange = 0;
        for (int i = 0; i < numOfRecords; i++) {
            if (isPriceWithinRange(getValueOrNegativeOneForIntegers(dataBaseArray[i].price), range)) {
                sumOfPricesBetweenRange += getValueOrNegativeOneForIntegers(dataBaseArray[i].price);
                counter++;
            }
        }
        averagePriceOfJordansBetweenRange = sumOfPricesBetweenRange / counter;
        println("OPTION 3.........\n" +
                "NOW DISPLAYING ANSWER..........");
        println("The Average price of an AirJordan shoe between \n" + range[1] + " and "
                + range[0] + " is $" + averagePriceOfJordansBetweenRange);
    }

    private static void displayHistogramOption4(int numOfRecords, Record[] dataBaseArray) {
        int[][] histogram = createHistogram(numOfRecords, dataBaseArray);
        String[] histogramStars = createHistogram2(histogram);
        System.out.printf("%-8s %-23s", "Decade:", "Count:");
        println("");
        for (int i = 0; i < histogram.length; i++) {
            for (int j = 0; j < histogram[i].length; j++) {
                System.out.printf("%-5s", histogram[i][j]);
                print("    ");
            }
            println(histogramStars[i]);
        }
    }

    private static String[] createHistogram2(int[][] histogram) {
        String[] histogramStars = new String[10];
        for (int i = 0; i < histogramStars.length; i++) {
            histogramStars[i] = "";
            for (int j = 0; j < histogram[i][1]; j++) {
                histogramStars[i] += "*";
            }
        }
        return histogramStars;
    }

    private static void endOfApp() {
        println("Closing app.............");
    }

    //DATA PROCESSING DATA PROCESSING DATA PROCESSING DATA PROCESSING
    //DATA PROCESSING DATA PROCESSING DATA PROCESSING DATA PROCESSING
    private static int[][] createHistogram(int numOfRecords, Record[] dataBaseArray) {
        int minAndMaxDividedDecade = (350 - 140) / 10;
        int[][] histogram = new int[10][2];
        histogram[0][0] = 140;
        for (int i = 1; i < histogram.length; i++) {
            histogram[i][0] = histogram[i - 1][0] + minAndMaxDividedDecade;
            histogram[i][1] = 0;
        }
        for (int i = 1; i < histogram.length; i++) {
            int counter = 0;
            for (int j = 0; j < numOfRecords; j++) {
                if (isPriceWithinOption4Range(histogram[i][0], histogram[i - 1][0], getValueOrNegativeOneForIntegers(dataBaseArray[j].price))) {
                    counter++;
                }
            }
            histogram[i][1] = counter;
        }
        return histogram;
    }

    //DATA VALIDATION DATA VALIDATION DATA VALIDATION DATA VALIDATION
    //DATA VALIDATION DATA VALIDATION DATA VALIDATION DATA VALIDATION
    private static boolean isPriceWithinOption4Range(int max, int min, int price) {
        return price <= max && price >= min;
    }

    private static boolean isPriceWithinRange(int price, int[] range) {
        return price >= range[1] && price <= range[0];
    }

    private static int[] dataValidationOption3() {
        int inputIntegerHigherRange;
        int inputIntegerLowerRange;
        println("ENTER a Maximum price and minimum price and I will \n" +
                "find the average price within that range:");
        do {
            do {
                println("ENTER MAXIMUM PRICE");
                String input = kb.next();
                inputIntegerHigherRange = getValueOrNegativeOneForIntegers(input);
            } while (inputIntegerHigherRange == -1);

            do {
                println("ENTER MINIMUM PRICE");
                String input = kb.next();
                inputIntegerLowerRange = getValueOrNegativeOneForIntegers(input);
            } while (inputIntegerLowerRange == -1);
        } while (inputIntegerHigherRange <= inputIntegerLowerRange);
        int[] range = new int[2];
        range[0] = inputIntegerHigherRange;
        range[1] = inputIntegerLowerRange;
        return range;
    }

    private static String getAndCheckUserInputForString() {
        println("Enter the key field for the record(All Letters):\n");
        String input = kb.next();
        boolean characterIsDigit;
        int i = 0;
        do {
            if (Character.isDigit(input.charAt(i))) {
                println("Please enter a valid key field:\n");
                input = kb.next();
                characterIsDigit = true;
            } else {
                characterIsDigit = false;
            }
            i++;
        } while (characterIsDigit);
        return input;
    }

    private static boolean isUserWithinOptionRange(int inputInteger) {
        return inputInteger >= 1 && inputInteger <= 5;
    }

    private static boolean doesUserWantToGoBackToMenu(int userInput) {
        int inputInteger;
        if (userInput == 5) {
            return false;
        }
        do {
            println("1 to go back to MAIN MENU OR 2 to END APP");
            String input = kb.next();
            inputInteger = getValueOrNegativeOneForIntegers(input);
        } while (!isUserWithinMainMenuOrEndOfAppRange(inputInteger));
        return inputInteger == 1;
    }

    private static boolean isUserWithinMainMenuOrEndOfAppRange(int inputInteger) {
        return inputInteger >= 1 && inputInteger <= 2;
    }

    //CONVERSION METHODS CONVERSION METHODS CONVERSION METHODS

    //CONVERSION METHODS CONVERSION METHODS CONVERSION METHODS

    private static int getValueOrNegativeOneForIntegers(String input) {
        int result;
        try {
            result = Integer.valueOf(input);
        } catch (NumberFormatException exception) {
            result = -1;
        }
        return result;
    }


    private static int dataBaseToArray(Record[] dataBaseArray) {
        String FILE_PATH = "/Users/ramzijabali/Code/CSC205-AB-advanced-java/assignment-3-database-manipulator/src/dataBase.txt";
        File dataBase = new File(FILE_PATH);
        Scanner readDataBase;
        int counter = -1;
        try {
            readDataBase = new Scanner(dataBase);
        } catch (FileNotFoundException e) {
            return 0;
        }
        String nextWord;
        for (int i = 0; i < dataBaseArray.length; i++) {
            counter++;
            Record record = new Record();
            for (int j = 1; j <= 4; j++) {
                nextWord = readDataBase.next();
                if (!nextWord.equals("EOF")) {
                    switch (j) {
                        case 1:
                            record.name = nextWord;
                            break;
                        case 2:
                            record.price = nextWord;
                            break;
                        case 3:
                            record.material = nextWord;
                            break;
                        case 4:
                            record.rating = nextWord;
                            break;
                    }
                } else {
                    return counter;
                }
            }
            dataBaseArray[i] = record;
        }
        return counter;
    }
    //PRINT METHODS PRINT METHODS PRINT METHODS

    //PRINT METHODS PRINT METHODS PRINT METHODS

    private static void printDataFileArray(int numOfRecords, Record[] dataBaseArray) {
        System.out.printf("%-23s %-23s %-23s %-24s", "Shoe Name", "Shoe Price", "Shoe Material", "Shoe Rating");
        println("");
        for (int j = 0; j < numOfRecords; j++) {
            if (j == 15) {
                println("Type any letter and press Enter to continue");
                kb.next();
            }
            for (int i = 1; i <= 4; i++) {
                switch (i) {
                    case 1:
                        System.out.printf("%-20s", dataBaseArray[j].name);
                        break;
                    case 2:
                        System.out.printf("%-20s", dataBaseArray[j].price);
                        break;
                    case 3:
                        System.out.printf("%-20s", dataBaseArray[j].material);
                        break;
                    case 4:
                        System.out.printf("%-20s", dataBaseArray[j].rating);
                        break;
                }
                print("    ");
            }
            println("");
        }
    }

    private static void print(String text) {
        System.out.print(text);
    }

    private static void println(String text) {
        System.out.println(text);
    }
}

class Record {
    //A 'record' that just contains data members
    // for each column of the data file.
    public String name,
            price,
            material,
            rating;
}
