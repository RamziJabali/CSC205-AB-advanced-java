import java.util.Scanner;

public class Program2Redo {
    private static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        final String TAB = "\t";
        final String DOUBLE_TAB = TAB + TAB;

        final String DAILY_COMPOUNDING_WORD = "Day";
        final String MONTHLY_COMPOUNDING_WORD = "Month";
        final String YEARLY_COMPOUNDING_WORD = "Year";

        final int DAILY_COMPOUNDING = 365;
        final int MONTHLY_COMPOUNDING = 12;
        final int YEARLY_COMPOUNDING = 1;

        final double MAX_INTEREST = 0.2;
        final double MIN_INTEREST = 0.00;
        final double MIN_PROCESSING_CHARGE = 0.00;
        final double MIN_THRESHOLD_AMOUNT = 0.00;
        final double MIN_INITIAL_BALANCE = 0.00;
        final double MIN_PERIODIC_PAYMENT = 0.00;

        final int MIN_COMPOUNDING_PERIOD = 1;
        final int MAX_COMPOUNDING_PERIOD = 3;
        final int MIN_USER_CHOICE_FOR_OPTIONS = 1;
        final int MAX_USER_CHOICE_FOR_OPTIONS = 2;
        final int MIN_USER_CHOICE_FOR_DISPLAY = 1;
        final int MAX_USER_CHOICE_FOR_DISPLAY = 2;
        final int MIN_PERIOD_NUMBER = 1;
        final int MIN_RESTART_OPTION = 1;
        final int MAX_RESTART_OPTION = 3;

        boolean isAppStarting = true;
        boolean isUserInSubLoanMode = false;
        boolean isUserInLoanMode = false;
        boolean isUserInDisplayInfoMode = false;

        double processingCharge = 0;
        double thresholdAmount = 0;
        double initialBalance = 0;
        double periodicPayment = 0;
        double annualInterestRate = 0;
        double goalBalanceForOptionB = 0;

        int interestRateCompoundingPeriod = 0;
        int userChoiceForOptions;
        int userChoiceForDisplay;
        int userChoiceForRestartingApp;
        int numberOfPeriodsForOptionA;

        do {
            if (isAppStarting) {
                println("This app will help" +
                        " predict the progress of a bank loan \n");
                isAppStarting = false;
                isUserInSubLoanMode = true;
            }
            if (isUserInSubLoanMode) {
                do {
                    println("First specify the banks threshold " +
                            "amount:\n");
                    thresholdAmount = getDoubleValueOrNegativeOne(kb.next());
                }
                while (!isThresholdAmountWithinRange(thresholdAmount, MIN_THRESHOLD_AMOUNT));

                do {
                    println("Now please specify the " +
                            "bank's processing charge \n" +
                            "Enter the Processing charge amount:");
                    processingCharge = getDoubleValueOrNegativeOne(kb.next());
                }
                while (!isProcessingChargeWithinRange(processingCharge, MIN_PROCESSING_CHARGE));
                isUserInSubLoanMode = false;
                isUserInLoanMode = true;
            }

            if (isUserInLoanMode) {
                do {
                    println("Specify Initial Balance: ");
                    initialBalance = getDoubleValueOrNegativeOne(kb.next());
                } while (!isInitialBalanceWithinRange(initialBalance, MIN_INITIAL_BALANCE));

                do {
                    println("Specify Periodic Payment" +
                            " Amount: ");
                    periodicPayment = getDoubleValueOrNegativeOne(kb.next());
                } while (!isPeriodicPaymentWithinRange(periodicPayment, MIN_PERIODIC_PAYMENT));

                do {
                    println("Specify Interest Rate(Ex:\n" +
                            "Enter .1 for 10%, Interest Rate cannot exceed 20% = .2):");
                    annualInterestRate = getDoubleValueOrNegativeOne(kb.next());
                } while (!isAnnualInterestRateWithinRange(annualInterestRate, MIN_INTEREST, MAX_INTEREST));

                do {
                    println("Specify Period at which " +
                            "interest rate is Compounded(Daily, Monthly, Yearly)\n" +
                            "Enter 1 for Daily, 2 for Monthly, and 3 for Yearly:\n");
                    interestRateCompoundingPeriod = getIntegerValueOrNegativeOne(kb.next());
                } while (!isInterestRateCompoundingPeriodWithinRange(interestRateCompoundingPeriod, MIN_COMPOUNDING_PERIOD, MAX_COMPOUNDING_PERIOD));
                isUserInLoanMode = false;
                isUserInDisplayInfoMode = true;
            }
            if (isUserInDisplayInfoMode) {
                do {
                    println("In option A you can specify the number " +
                            "of periods so the \n" +
                            "the program can compute " +
                            "the final Balance at the end of\n" +
                            "time \n OR \n");
                    println("In option B you can specify the goal for the " +
                            "final Balance, \nand have the program calculate the how long(in the \n" +
                            "units as the compounding period) it will take for the\nfor your loan" +
                            " to be less than or equal to that amount!\n" +
                            "(enter number less than your initial balance)\n");
                    println("For option A enter 1 for option B enter 2");
                    userChoiceForOptions = getIntegerValueOrNegativeOne(kb.next());
                } while (!isUserOptionChoiceWithinRange(userChoiceForOptions, MIN_USER_CHOICE_FOR_OPTIONS, MAX_USER_CHOICE_FOR_OPTIONS));

                do {
                    println("Enter 1 for Table or 2 for just the " +
                            "answer:");
                    userChoiceForDisplay = getIntegerValueOrNegativeOne(kb.next());
                } while (!isUserChoiceForDisplayWithinRange(userChoiceForDisplay, MIN_USER_CHOICE_FOR_DISPLAY, MAX_USER_CHOICE_FOR_DISPLAY));
                if (userChoiceForOptions == 1) {
                    do {
                        println("Please specify the Number " +
                                "of periods(in the same units specified for\nthe compounding period) min num " +
                                "of period is 1");
                        numberOfPeriodsForOptionA = getIntegerValueOrNegativeOne(kb.next());
                    } while (!isTheNumberOfPeriodsValid(numberOfPeriodsForOptionA, MIN_PERIOD_NUMBER));

                    OptionABalanceAtEndOfSpecifiedPeriod(interestRateCompoundingPeriod, YEARLY_COMPOUNDING,
                            YEARLY_COMPOUNDING_WORD, MONTHLY_COMPOUNDING, MONTHLY_COMPOUNDING_WORD,
                            DAILY_COMPOUNDING, DAILY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, numberOfPeriodsForOptionA, annualInterestRate,
                            periodicPayment, thresholdAmount, processingCharge, initialBalance, userChoiceForDisplay);
                } else {
                    do {
                        println("Specify a goal for the " +
                                "Balance and I'll calculate how long it\nwill take to reach that Balance");
                        goalBalanceForOptionB = getDoubleValueOrNegativeOne(kb.next());
                    } while (!isGoalForBalanceValid(goalBalanceForOptionB, initialBalance));
                    optionBFindNumberOfPeriodsToReachSpecifiedBalance(interestRateCompoundingPeriod, YEARLY_COMPOUNDING, YEARLY_COMPOUNDING_WORD,
                            MONTHLY_COMPOUNDING, MONTHLY_COMPOUNDING_WORD, DAILY_COMPOUNDING, DAILY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, goalBalanceForOptionB,
                            annualInterestRate, periodicPayment, thresholdAmount, processingCharge, initialBalance, userChoiceForDisplay);
                }
                isUserInDisplayInfoMode = false;
            }
            do {
                println("Press 1 to restart the app with the same threshold and processing charge\n" +
                        "Press 2 to restart the app from scratch\n" +
                        "Press 3 to end app");
                userChoiceForRestartingApp = kb.nextInt();
            } while (!isRestartOptionValid(userChoiceForRestartingApp, MIN_RESTART_OPTION, MAX_RESTART_OPTION));
            if (userChoiceForRestartingApp == 1) {
                isUserInLoanMode = true;
            } else if (userChoiceForRestartingApp == 2) {
                isAppStarting = true;
            }else{
                println("App is now closing......");
            }
        } while (userChoiceForRestartingApp != 3);

    }

    private static boolean isRestartOptionValid(int userChoiceForRestartingApp, int MIN_RESTART_OPTION, int MAX_RESTART_OPTION) {
        return userChoiceForRestartingApp >= MIN_RESTART_OPTION && userChoiceForRestartingApp <= MAX_RESTART_OPTION;
    }

    private static boolean isTheNumberOfPeriodsValid(int numberOfPeriodsForOptionA, int MIN_PERIOD_NUMBER) {
        return numberOfPeriodsForOptionA >= MIN_PERIOD_NUMBER;
    }

    private static boolean isUserChoiceForDisplayWithinRange(int userChoiceForDisplay, int MIN_USER_CHOICE_FOR_DISPLAY, int MAX_USER_CHOICE_FOR_DISPLAY) {
        return userChoiceForDisplay >= MIN_USER_CHOICE_FOR_DISPLAY && userChoiceForDisplay <= MAX_USER_CHOICE_FOR_DISPLAY;
    }

    private static boolean isUserOptionChoiceWithinRange(int userChoice, int MIN_USER_CHOICE_FOR_OPTIONS, int MAX_USER_CHOICE_FOR_OPTIONS) {
        return userChoice >= MIN_USER_CHOICE_FOR_OPTIONS && userChoice <= MAX_USER_CHOICE_FOR_OPTIONS;
    }

    private static boolean isInterestRateCompoundingPeriodWithinRange(int interestRateCompoundingPeriod, int MIN_COMPOUNDING_PERIOD, int MAX_COMPOUNDING_PERIOD) {
        return interestRateCompoundingPeriod >= MIN_COMPOUNDING_PERIOD && interestRateCompoundingPeriod <= MAX_COMPOUNDING_PERIOD;
    }

    private static boolean isAnnualInterestRateWithinRange(double annualInterest, double MIN_INTEREST, double MAX_INTEREST) {
        return annualInterest >= MIN_INTEREST && annualInterest <= MAX_INTEREST;
    }

    private static boolean isPeriodicPaymentWithinRange(double periodicPayment, double MIN_PERIODIC_PAYMENT) {
        return periodicPayment >= MIN_PERIODIC_PAYMENT;
    }

    private static boolean isInitialBalanceWithinRange(double initialBalance, double MIN_INITIAL_BALANCE) {
        return initialBalance >= MIN_INITIAL_BALANCE;
    }

    private static boolean isProcessingChargeWithinRange(double processingCharge, double MIN_PROCESSING_CHARGE) {
        return processingCharge >= MIN_PROCESSING_CHARGE;
    }

    private static boolean isThresholdAmountWithinRange(double thresholdAmount, double MIN_THRESHOLD_AMOUNT) {
        return thresholdAmount >= MIN_THRESHOLD_AMOUNT;
    }

    private static boolean isGoalForBalanceValid(double goalBalanceForOptionB, double initialBalance) {
        return goalBalanceForOptionB <= initialBalance;
    }


    private static void OptionABalanceAtEndOfSpecifiedPeriod(int interestRateCompoundingPeriod, int YEARLY_COMPOUNDING, String YEARLY_COMPOUNDING_WORD,
                                                             int MONTHLY_COMPOUNDING, String MONTHLY_COMPOUNDING_WORD, int DAILY_COMPOUNDING,
                                                             String DAILY_COMPOUNDING_WORD, String TAB, String DOUBLE_TAB,
                                                             int numberOfPeriodsForOptionA, double annualInterestRate, double periodicPayment,
                                                             double thresholdAmount, double processingCharge, double initialBalance, int displayChoice) {
        if (interestRateCompoundingPeriod == 3) {
            calculateBalanceForOptionA(YEARLY_COMPOUNDING, YEARLY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, initialBalance, numberOfPeriodsForOptionA, annualInterestRate,
                    periodicPayment, thresholdAmount, processingCharge, displayChoice);
            return;
        } else if (interestRateCompoundingPeriod == 2) {
            calculateBalanceForOptionA(MONTHLY_COMPOUNDING, MONTHLY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, initialBalance, numberOfPeriodsForOptionA, annualInterestRate,
                    periodicPayment, thresholdAmount, processingCharge, displayChoice);
            return;
        } else {
            calculateBalanceForOptionA(DAILY_COMPOUNDING, DAILY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, initialBalance, numberOfPeriodsForOptionA, annualInterestRate,
                    periodicPayment, thresholdAmount, processingCharge, displayChoice);
            return;
        }
    }

    private static void calculateBalanceForOptionA(int compounding, String compoundingInWordForm, String TAB, String DOUBLE_TAB,
                                                   double initialBalance, int numberOfPeriodsForOptionA, double annualInterestRate,
                                                   double periodicPayment, double thresholdAmount, double processingCharge, int displayChoice) {
        String optionATableAnswer = compoundingInWordForm + TAB + "Balance" + "\n";
        optionATableAnswer += "0" + DOUBLE_TAB + initialBalance + "\n";
        double newBalance = initialBalance;
        double interest;
        for (int i = 1; i <= numberOfPeriodsForOptionA; i++) {
            interest = newBalance * (annualInterestRate / compounding);
            newBalance = (interest + newBalance) - periodicPayment;
            if (newBalance < thresholdAmount) {
                newBalance += processingCharge;
            }
            optionATableAnswer += i + DOUBLE_TAB + newBalance + "\n";
        }
        if (displayChoice == 1) {
            println(optionATableAnswer);
        } else {
            println("Your Balance at the end" +
                    " of " + numberOfPeriodsForOptionA + " periods is: "
                    + newBalance);
        }
    }

    private static void optionBFindNumberOfPeriodsToReachSpecifiedBalance(int interestRateCompoundingPeriod, int YEARLY_COMPOUNDING, String YEARLY_COMPOUNDING_WORD,
                                                                          int MONTHLY_COMPOUNDING, String MONTHLY_COMPOUNDING_WORD, int DAILY_COMPOUNDING,
                                                                          String DAILY_COMPOUNDING_WORD, String TAB, String DOUBLE_TAB,
                                                                          double goalBalanceForOptionB, double annualInterestRate, double periodicPayment,
                                                                          double thresholdAmount, double processingCharge, double initialBalance, int displayChoice) {
        if (interestRateCompoundingPeriod == 3) {
            calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(YEARLY_COMPOUNDING, YEARLY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, initialBalance, annualInterestRate,
                    periodicPayment, thresholdAmount, processingCharge, displayChoice, goalBalanceForOptionB);
            return;
        } else if (interestRateCompoundingPeriod == 2) {
            calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(MONTHLY_COMPOUNDING, MONTHLY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, initialBalance, annualInterestRate,
                    periodicPayment, thresholdAmount, processingCharge, displayChoice, goalBalanceForOptionB);
            return;
        } else {
            calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(DAILY_COMPOUNDING, DAILY_COMPOUNDING_WORD, TAB, DOUBLE_TAB, initialBalance, annualInterestRate,
                    periodicPayment, thresholdAmount, processingCharge, displayChoice, goalBalanceForOptionB);
            return;
        }
    }

    private static void calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(int compounding, String compoundingInWordForm, String TAB, String DOUBLE_TAB,
                                                                            double initialBalance, double annualInterestRate, double periodicPayment,
                                                                            double thresholdAmount, double processingCharge, int displayChoice, double goalBalanceForOptionB) {
        String optionBTableAnswer = compoundingInWordForm + TAB + "balance" + "\n";
        double newBalance = initialBalance;
        int numberOfPeriodsToAchieveGoalBalanceOptionB = 0;
        optionBTableAnswer += "0" + DOUBLE_TAB + initialBalance + "\n";
        double interest;
        for (int i = 0; newBalance > goalBalanceForOptionB; i++) {
            interest = newBalance * (annualInterestRate / compounding);
            newBalance = (interest + newBalance) - periodicPayment;
            if (newBalance < thresholdAmount) {
                newBalance += processingCharge;
            }
            optionBTableAnswer += i + DOUBLE_TAB + newBalance + "\n";
            numberOfPeriodsToAchieveGoalBalanceOptionB = i;
        }
        if (displayChoice == 1) {
            println(optionBTableAnswer);
        } else {
            println("The number of periods required to reach your goal balance is " + numberOfPeriodsToAchieveGoalBalanceOptionB);
        }
    }


    private static int getIntegerValueOrNegativeOne(String input) {
        int result;
        try {
            result = Integer.valueOf(input);
        } catch (NumberFormatException exception) {
            result = -1;
        }
        return result;
    }

    private static double getDoubleValueOrNegativeOne(String input) {
        double result;
        try {
            result = Double.valueOf(input);
        } catch (NumberFormatException exception) {
            result = -1;
        }
        return result;
    }

    private static void println(String text) {
        System.out.println(text);
    }
}
