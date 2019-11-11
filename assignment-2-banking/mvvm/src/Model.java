///*
//
//Model is a component that stores solely the data of the application.
// */
//public class Model {
//
//    public static final String INTRODUCTION_TO_APP = "This app will help" +
//            " predict the progress of a bank loan \n\n";
//
//    public static final String INTRODUCTION_TO_APP_2 = "First specify the " +
//            "bank's processing charge \n" +
//            "Enter the Processing charge amount:\n";
//
//    public static final String INTRODUCTION_TO_APP_3 = "Enter the threshold " +
//            "amount:\n";
//
//    public static final String GET_INITIAL_BALANCE = "Specify Initial Balance:\n";
//
//    public static final String GET_PERIODIC_PAYMENT = "Specify Periodic Payment" +
//            " Amount:\n";
//
//    public static final String GET_INTEREST_RATE = "Specify Interest Rate(Ex: " +
//            "Enter .1 for 10%, Interest Rate cannot exceed 10%):\n";
//
//    public static final String GET_INTEREST_RATE_PERIOD = "Specify Period at which " +
//            "interest rate is Compounded(Daily, Monthly, Yearly)\n" +
//            "Enter 1 for Daily, 2 for Monthly, and 3 for Yearly:\n";
//
//    public static final String OPTION_A = "In option A you can specify the number " +
//            "of periods so the \n" +
//            "the program can compute the final Balance at the end of\n" +
//            "time \n OR \n";
//
//    public static final String OPTION_B = "In option B you can specify the goal for the " +
//            "final Balance, \nand have the program calculate the how long(in the \n" +
//            "units as the compounding period) it will take for the\nfor your loan" +
//            " to be less than or equal to that amount!\n" +
//            "(enter number less than your initial balance)\n\n";
//
//    public static final String USER_CHOICE = "For option A enter 1 for option B enter 2\n";
//
//    public static final String DOES_USER_WANT_TABLE = "Enter 1 for Table or 2 for just the " +
//            "answer\n";
//
//    public static final String SPECIFY_THE_NUMBER_OF_PERIODS = "Please specify the Number " +
//            "of periods(in the same units specified for\nthe compounding period) min num " +
//            "of period is 1";
//
//    public static final String SPECIFY_GOAL_FOR_BALANCE = "Specify a goal for the " +
//            "Balance and I'll calculate how long it\nwill take to reach that Balance";
//
//    public static final String BALANCE = "Balance";
//
//    public static final String BALANCE_AT_END_OF_WANTED_PERIODS_1 = "Your Balance at the end" +
//            " of ";
//
//    public static final String BALANCE_AT_END_OF_WANTED_PERIODS_2 = " periods is: \n";
//
//    public static final String NUMBER_OF_PERIODS_AT_TO_ACHIEVE_BALANCE = "The number of " +
//            "periods required to reach ";
//
//    public static final String NUMBER_OF_PERIODS_AT_TO_ACHIEVE_BALANCE_2 = " balance is ";
//
//
//    public static final String WANT_TO_RESTART_APP_WITH_SAME_PC_TH = "\nPress 1 to restart the" +
//            " app with the same threshold and processing charge\nOR\n";
//
//    public static final String WANT_TO_RESTART_APP_WITH_SAME_PC_TH_2 = "Press 2 to restart the" +
//            " app from scratch \nOR\n";
//
//    public static final String WANT_TO_RESTART_APP_WITH_SAME_PC_TH_3 = "Press 3 to stop the App \n";
//
//    public static final String APP_IS_SHUTTING_DOWN = "App is now shutting down......\n";
//
//    public static final String NEWLINE = "\n";
//
//    public static final String DAILY_COMPOUNDING_WORD = "Day";
//    public static final String MONTHLY_COMPOUNDING_WORD = "Month";
//    public static final String YEARLY_COMPOUNDING_WORD = "Year";
//
//    public static final String TAB = "\t";
//    public static final String DOUBLE_TAB = TAB + TAB;
//
//    public static final int DAILY_COMPOUNDING = 365;
//    public static final int MONTHLY_COMPOUNDING = 12;
//    public static final int YEARLY_COMPOUNDING = 1;
//
//    public static final double MAX_INTEREST = 0.1;
//    public static final double MIN_INTEREST = 0.00;
//    public static final double MIN_PROCESSING_CHARGE = 0.00;
//    public static final double MIN_THRESHOLD_AMOUNT = 0.00;
//    public static final double MIN_INITIAL_BALANCE = 0.00;
//    public static final double MIN_PERIODIC_PAYMENT = 0.00;
//
//    public static final int MIN_COMPOUNDING_PERIOD = 1;
//    public static final int MAX_COMPOUNDING_PERIOD = 3;
//    public static final int MIN_USER_CHOICE_FOR_OPTIONS = 1;
//    public static final int MAX_USER_CHOICE_FOR_OPTIONS = 2;
//    public static final int MIN_USER_CHOICE_FOR_DISPLAY = 1;
//    public static final int MAX_USER_CHOICE_FOR_DISPLAY = 2;
//    public static final int MIN_PERIOD_NUMBER = 1;
//    public static final int MIN_RESTART_OPTION = 1;
//    public static final int MAX_RESTART_OPTION = 3;
//    public boolean isUserInSubLoanMode;
//    public boolean isUserEnteringProcessingCharge;
//    public boolean isUserEnteringThreshold;
//
//    public boolean isUserInLoanMode;
//    public boolean isUserEnteringInitialBalance;
//    public boolean isUserEnteringPeriodicPayment;
//    public boolean isUserEnteringAnnualInterestRate;
//    public boolean isUserEnteringCompoundedInterestPeriod;
//
//    public boolean isUserInDisplayInfoMode;
//    public boolean isUserEnteringOptionChoice;
//    public boolean isUserEnteringDisplayChoice;
//
//    public boolean isUserInProcessingMode;
//    public boolean isUserEnteringNumberOfPeriodsForOptionA;
//    public boolean isUserEnteringGoalBalanceForOptionB;
//    public boolean finishedCalculationForOptionA;
//    public boolean finishedCalculationForOptionB;
//    public boolean isAppStarting;
//
//    public boolean isUserInDisplayFinalAnswerMode;
//
//    public boolean isUserInRestartAppMode;
//    public boolean endApp;
//
//    public double processingCharge;
//    public double thresholdAmount;
//    public double initialBalance;
//    public double newBalance;
//    public double finalBalance;
//    public double periodicPayment;
//    public double annualInterestRate;
//    public double interest;//
//    public double goalBalanceForOptionB;
//
//    public int interestRateCompoundingPeriod;
//    public int userChoiceForOptions;
//    public int numberOfPeriodsForOptionA;
//    public int numberOfPeriodsToAchieveGoalBalanceOptionB;
//    public int userChoiceForDisplay;
//    public int userChoiceForRestartingApp;
//
//    public String optionATableAnswer;
//}
