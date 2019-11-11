/*
@Author: Ramzi El-Jabali/ Class CSC 205 Tuesday, Thursday.

@Purpose:

ViewModels are the brains of the app, where all the business logic lays. The ViewModel talks to the
    model for app data, processes it, then relays that information to the View via passing a ViewState object.
 */

import java.util.Scanner;

public class ViewModel implements ViewListener {
    private ViewState viewState;
    private View view;
    private Model model;

    public static void main(String[] args) {
        ViewModel startApplication = new ViewModel();
        startApplication.startApp();
    }

    private void startApp() {
        view = new View(this);
        viewState = new ViewState();
        model = new Model();
        model.isAppStarting = true;
        model.isUserInSubLoanMode = true;
        model.isUserEnteringProcessingCharge = true;
        model.isUserInDisplayFinalAnswerMode = false;
        model.endApp = false;
        model.finishedCalculationForOptionA = false;
        model.finishedCalculationForOptionB = false;
        model.isUserEnteringNumberOfPeriodsForOptionA = false;
        model.isUserEnteringGoalBalanceForOptionB = false;
        invalidateView();
    }

    private void invalidateView() {
        generateViewstateFromModel();
        view.setNewViewState(viewState);
    }

    private void generateViewstateFromModel() {
        if (model.isAppStarting) {
            viewState.output = Model.INTRODUCTION_TO_APP;
        }
        if (model.isUserInSubLoanMode) {
            if (model.isUserEnteringProcessingCharge) {
                viewState.output += Model.INTRODUCTION_TO_APP_2;
                viewState.askForInput = true;
                viewState.displayOutput = true;
                return;
            }
            if (model.isUserEnteringThreshold) {
                viewState.output = Model.INTRODUCTION_TO_APP_3;
                viewState.askForInput = true;
                viewState.displayOutput = true;
                return;
            }
        }

        if (model.isUserInLoanMode) {
            if (model.isUserEnteringInitialBalance) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.GET_INITIAL_BALANCE;
                return;
            }
            if (model.isUserEnteringPeriodicPayment) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.GET_PERIODIC_PAYMENT;
                return;
            }

            if (model.isUserEnteringAnnualInterestRate) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.GET_INTEREST_RATE;
                return;
            }
            if (model.isUserEnteringCompoundedInterestPeriod) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.GET_INTEREST_RATE_PERIOD;
                return;
            }
        }

        if (model.isUserInDisplayInfoMode) {
            if (model.isUserEnteringOptionChoice) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.OPTION_A;
                viewState.output += Model.OPTION_B;
                viewState.output += Model.USER_CHOICE;
                return;
            }

            if (model.isUserEnteringDisplayChoice) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.DOES_USER_WANT_TABLE;
                return;
            }
        }

        if (model.isUserInProcessingMode) {
            if (model.isUserEnteringNumberOfPeriodsForOptionA) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.SPECIFY_THE_NUMBER_OF_PERIODS;
                return;
            }
            if (model.isUserEnteringGoalBalanceForOptionB) {
                viewState.askForInput = true;
                viewState.displayOutput = true;
                viewState.output = Model.SPECIFY_GOAL_FOR_BALANCE;
                return;
            }
        }

        if (model.isUserInDisplayFinalAnswerMode) {
            viewState.askForInput = false;
            viewState.displayOutput = true;
            if (model.finishedCalculationForOptionA) {
                if (model.userChoiceForDisplay == 2) {
                    viewState.output = Model.BALANCE_AT_END_OF_WANTED_PERIODS_1;
                    viewState.output += model.numberOfPeriodsForOptionA;
                    viewState.output += Model.BALANCE_AT_END_OF_WANTED_PERIODS_2;
                    viewState.output += model.finalBalance + Model.NEWLINE;
                } else {
                    viewState.output = model.optionATableAnswer;
                }
            }
            if (model.finishedCalculationForOptionB) {
                if (model.userChoiceForDisplay == 2) {
                    viewState.output = Model.NUMBER_OF_PERIODS_AT_TO_ACHIEVE_BALANCE;
                    viewState.output += model.goalBalanceForOptionB;
                    viewState.output += Model.NUMBER_OF_PERIODS_AT_TO_ACHIEVE_BALANCE_2;
                    viewState.output += model.numberOfPeriodsToAchieveGoalBalanceOptionB + Model.NEWLINE;
                } else {
                    viewState.output = model.optionATableAnswer;
                }
            }
        }
        if (model.isUserInRestartAppMode) {
            viewState.askForInput = true;
            viewState.displayOutput = true;
            viewState.output += Model.WANT_TO_RESTART_APP_WITH_SAME_PC_TH;
            viewState.output += Model.WANT_TO_RESTART_APP_WITH_SAME_PC_TH_2;
            viewState.output += Model.WANT_TO_RESTART_APP_WITH_SAME_PC_TH_3;
        }
        if (model.endApp) {
            viewState.displayOutput = true;
            viewState.askForInput = false;
            viewState.output = Model.APP_IS_SHUTTING_DOWN;
        }
    }

    @Override
    public void enteredInput(String input) {
        model.isAppStarting = false;
        if (model.isUserInSubLoanMode) {
            if (model.isUserEnteringProcessingCharge) {
                model.processingCharge = getDoubleValueOrNegativeOne(input);
                if (isProcessingChargeWithinRange(model.processingCharge)) {
                    model.isUserEnteringThreshold = true;
                    model.isUserEnteringProcessingCharge = false;
                } else {
                    model.isUserEnteringProcessingCharge = true;
                }
                invalidateView();
                return;
            }
            if (model.isUserEnteringThreshold) {
                model.thresholdAmount = getDoubleValueOrNegativeOne(input);
                if (isThresholdAmountWithinRange(model.thresholdAmount)) {
                    model.isUserEnteringThreshold = false;
                    model.isUserInSubLoanMode = false;
                    model.isUserInLoanMode = true;
                    model.isUserEnteringInitialBalance = true;
                } else {
                    model.isUserEnteringThreshold = true;
                }
                invalidateView();
                return;
            }
        }
        if (model.isUserInLoanMode) {
            if (model.isUserEnteringInitialBalance) {
                model.initialBalance = getDoubleValueOrNegativeOne(input);
                model.newBalance = model.initialBalance;
                if (isInitialBalanceWithinRange(model.initialBalance)) {
                    model.isUserEnteringInitialBalance = false;
                    model.isUserEnteringPeriodicPayment = true;
                } else {
                    model.isUserEnteringInitialBalance = true;
                }
                invalidateView();
                return;
            }
            if (model.isUserEnteringPeriodicPayment) {
                model.periodicPayment = getDoubleValueOrNegativeOne(input);
                if (isPeriodicPaymentWithinRange(model.periodicPayment)) {
                    model.isUserEnteringPeriodicPayment = false;
                    model.isUserEnteringAnnualInterestRate = true;
                } else {
                    model.isUserEnteringPeriodicPayment = true;
                }
                invalidateView();
                return;
            }
            if (model.isUserEnteringAnnualInterestRate) {
                model.annualInterestRate = getDoubleValueOrNegativeOne(input);
                if (isAnnualInterestRateWithinRange(model.annualInterestRate)) {
                    model.isUserEnteringAnnualInterestRate = false;
                    model.isUserEnteringCompoundedInterestPeriod = true;
                } else {
                    model.isUserEnteringAnnualInterestRate = true;
                }
                invalidateView();
                return;
            }
            if (model.isUserEnteringCompoundedInterestPeriod) {
                model.interestRateCompoundingPeriod = getIntegerValueOrNegativeOne(input);
                if (isInterestRateCompoundingPeriodWithinRange(model.interestRateCompoundingPeriod)) {
                    model.isUserEnteringCompoundedInterestPeriod = false;
                    model.isUserInLoanMode = false;
                    model.isUserInDisplayInfoMode = true;
                    model.isUserEnteringOptionChoice = true;
                } else {
                    model.isUserEnteringCompoundedInterestPeriod = true;
                }
                invalidateView();
                return;
            }
        }

        if (model.isUserInDisplayInfoMode) {
            if (model.isUserEnteringOptionChoice) {
                model.userChoiceForOptions = getIntegerValueOrNegativeOne(input);
                if (isUserOptionChoiceWithinRange(model.userChoiceForOptions)) {
                    model.isUserEnteringOptionChoice = false;
                    model.isUserEnteringDisplayChoice = true;
                } else {
                    model.isUserEnteringOptionChoice = true;
                }
                invalidateView();
                return;
            }
            if (model.isUserEnteringDisplayChoice) {
                model.userChoiceForDisplay = getIntegerValueOrNegativeOne(input);
                if (isUserChoiceForDisplayWithinRange(model.userChoiceForDisplay)) {
                    model.isUserEnteringDisplayChoice = false;
                    model.isUserInDisplayInfoMode = false;
                    model.isUserInProcessingMode = true;
                    if (didUserChooseOptionsA()) {
                        model.isUserEnteringNumberOfPeriodsForOptionA = true;
                    } else {
                        model.isUserEnteringGoalBalanceForOptionB = true;
                    }
                } else {
                    model.isUserEnteringDisplayChoice = true;
                }
                invalidateView();
                return;
            }
        }
        if (model.isUserInProcessingMode) {
            if (didUserChooseOptionsA()) {
                model.numberOfPeriodsForOptionA = getIntegerValueOrNegativeOne(input);
                if (isTheNumberOfPeriodsValid()) {
                    OptionABalanceAtEndOfSpecifiedPeriod();
                    model.isUserEnteringNumberOfPeriodsForOptionA = false;
                    model.isUserInProcessingMode = false;
                    model.isUserInDisplayFinalAnswerMode = true;
                    model.isUserInRestartAppMode = true;
                    invalidateView();
                    return;
                }
                model.isUserEnteringNumberOfPeriodsForOptionA = true;
                invalidateView();
                return;
            }
            model.goalBalanceForOptionB = getDoubleValueOrNegativeOne(input);
            if (isGoalForBalanceValid()) {
                optionBFindNumberOfPeriodsToReachSpecifiedBalance();
                model.isUserEnteringGoalBalanceForOptionB = false;
                model.isUserInProcessingMode = false;
                model.isUserInDisplayFinalAnswerMode = true;
                model.isUserInRestartAppMode = true;
                invalidateView();
                return;
            }
            model.isUserEnteringGoalBalanceForOptionB = true;
            invalidateView();
            return;
        }
        if (model.isUserInRestartAppMode) {
            model.isUserInDisplayFinalAnswerMode = false;
            model.userChoiceForRestartingApp = getIntegerValueOrNegativeOne(input);
            if (isRestartOptionValid()) {
                model.isUserInRestartAppMode = false;
                if (model.userChoiceForRestartingApp == 1) {
                    model.isUserInLoanMode = true;
                    model.isUserEnteringInitialBalance = true;
                } else if (model.userChoiceForRestartingApp == 2) {
                    model.isAppStarting = true;
                    model.isUserInSubLoanMode = true;
                    model.isUserEnteringProcessingCharge = true;
                } else {
                    model.endApp = true;
                }
                invalidateView();
                return;
            }
            model.isUserInRestartAppMode = true;
            invalidateView();
        }
    }

    private boolean isRestartOptionValid() {
        return model.userChoiceForRestartingApp >= Model.MIN_RESTART_OPTION && model.userChoiceForRestartingApp <= Model.MAX_RESTART_OPTION;
    }

    private int getIntegerValueOrNegativeOne(String input) {
        int result;
        try {
            result = Integer.valueOf(input);
        } catch (NumberFormatException exception) {
            result = -1;
        }
        return result;
    }

    private void OptionABalanceAtEndOfSpecifiedPeriod() {
        if (model.interestRateCompoundingPeriod == 3) {
            calculateBalanceForOptionA(Model.YEARLY_COMPOUNDING, Model.YEARLY_COMPOUNDING_WORD);
            return;
        } else if (model.interestRateCompoundingPeriod == 2) {
            calculateBalanceForOptionA(Model.MONTHLY_COMPOUNDING, Model.MONTHLY_COMPOUNDING_WORD);
            return;
        } else {
            calculateBalanceForOptionA(Model.DAILY_COMPOUNDING, Model.DAILY_COMPOUNDING_WORD);
            return;
        }
    }

    private void calculateBalanceForOptionA(int compounding, String compoundingInWordForm) {
        model.optionATableAnswer = compoundingInWordForm + Model.TAB + Model.BALANCE + "\n";
        model.optionATableAnswer += "0" + Model.DOUBLE_TAB + model.initialBalance + "\n";
        for (int i = 1; i <= model.numberOfPeriodsForOptionA; i++) {
            model.interest = model.newBalance * (model.annualInterestRate / compounding);
            model.newBalance = (model.interest + model.newBalance) - model.periodicPayment;
            if (model.newBalance < model.thresholdAmount) {
                model.newBalance += model.processingCharge;
            }
            model.optionATableAnswer += i + Model.DOUBLE_TAB + model.newBalance + "\n";
        }
        model.finalBalance = model.newBalance;
        model.finishedCalculationForOptionA = true;
    }

    private void optionBFindNumberOfPeriodsToReachSpecifiedBalance() {
        if (model.interestRateCompoundingPeriod == 3) {
            calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(Model.YEARLY_COMPOUNDING, Model.YEARLY_COMPOUNDING_WORD);
            return;
        } else if (model.interestRateCompoundingPeriod == 2) {
            calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(Model.MONTHLY_COMPOUNDING, Model.MONTHLY_COMPOUNDING_WORD);
            return;
        } else {
            calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(Model.DAILY_COMPOUNDING, Model.DAILY_COMPOUNDING_WORD);
            return;
        }
    }

    private void calculateNumberOfPeriodsToAchieveGoalBalanceOptionB(int compounding, String compoundingInWordForm) {
        model.optionATableAnswer = compoundingInWordForm + Model.TAB + Model.BALANCE + "\n";
        model.optionATableAnswer += "0" + Model.DOUBLE_TAB + model.initialBalance + "\n";
        for (int i = 1; model.newBalance > model.goalBalanceForOptionB; i++) {
            model.interest = model.newBalance * (model.annualInterestRate / compounding);
            model.newBalance = (model.interest + model.newBalance) - model.periodicPayment;
            if (model.newBalance < model.thresholdAmount) {
                model.newBalance += model.processingCharge;
            }
            model.optionATableAnswer += i + Model.DOUBLE_TAB + model.newBalance + "\n";
            model.numberOfPeriodsToAchieveGoalBalanceOptionB = i;
        }
        model.finishedCalculationForOptionB = true;
    }

    private double getDoubleValueOrNegativeOne(String input) {
        double result;
        try {
            result = Double.valueOf(input);
        } catch (NumberFormatException exception) {
            result = -1;
        }
        return result;
    }

    private boolean isProcessingChargeWithinRange(double processingCharge) {
        return processingCharge >= Model.MIN_PROCESSING_CHARGE;
    }

    private boolean isThresholdAmountWithinRange(double thresholdAmount) {
        return thresholdAmount >= Model.MIN_THRESHOLD_AMOUNT;
    }

    private boolean isTheNumberOfPeriodsValid() {
        return model.numberOfPeriodsForOptionA >= Model.MIN_PERIOD_NUMBER;
    }

    private boolean isGoalForBalanceValid() {
        return model.goalBalanceForOptionB <= model.initialBalance;
    }

    private boolean didUserChooseOptionsA() {
        return model.userChoiceForOptions == 1;
    }

    private boolean isInitialBalanceWithinRange(double initialBalance) {
        return initialBalance >= Model.MIN_INITIAL_BALANCE;
    }

    private boolean isPeriodicPaymentWithinRange(double periodicPayment) {
        return periodicPayment >= Model.MIN_PERIODIC_PAYMENT;
    }

    private boolean isAnnualInterestRateWithinRange(double annualInterest) {
        return annualInterest >= Model.MIN_INTEREST && annualInterest <= Model.MAX_INTEREST;
    }

    private boolean isInterestRateCompoundingPeriodWithinRange(int interestRateCompoundingPeriod) {
        return interestRateCompoundingPeriod >= Model.MIN_COMPOUNDING_PERIOD && interestRateCompoundingPeriod <= Model.MAX_COMPOUNDING_PERIOD;
    }

    private boolean isUserOptionChoiceWithinRange(int userChoice) {
        return userChoice >= Model.MIN_USER_CHOICE_FOR_OPTIONS && userChoice <= Model.MAX_USER_CHOICE_FOR_OPTIONS;
    }

    private boolean isUserChoiceForDisplayWithinRange(int userChoiceForDisplay) {
        return userChoiceForDisplay >= Model.MIN_USER_CHOICE_FOR_DISPLAY && userChoiceForDisplay <= Model.MAX_USER_CHOICE_FOR_DISPLAY;
    }


}

/*

Model is a component that stores solely the data of the application.
 */
class Model {

    public static final String INTRODUCTION_TO_APP = "This app will help" +
            " predict the progress of a bank loan \n\n";

    public static final String INTRODUCTION_TO_APP_2 = "First specify the " +
            "bank's processing charge \n" +
            "Enter the Processing charge amount:\n";

    public static final String INTRODUCTION_TO_APP_3 = "Enter the threshold " +
            "amount:\n";

    public static final String GET_INITIAL_BALANCE = "Specify Initial Balance:\n";

    public static final String GET_PERIODIC_PAYMENT = "Specify Periodic Payment" +
            " Amount:\n";

    public static final String GET_INTEREST_RATE = "Specify Interest Rate(Ex: " +
            "Enter .1 for 10%, Interest Rate cannot exceed 10%):\n";

    public static final String GET_INTEREST_RATE_PERIOD = "Specify Period at which " +
            "interest rate is Compounded(Daily, Monthly, Yearly)\n" +
            "Enter 1 for Daily, 2 for Monthly, and 3 for Yearly:\n";

    public static final String OPTION_A = "In option A you can specify the number " +
            "of periods so the \n" +
            "the program can compute " +
            "the final Balance at the end of\n" +
            "time \n OR \n";

    public static final String OPTION_B = "In option B you can specify the goal for the " +
            "final Balance, \nand have the program calculate the how long(in the \n" +
            "units as the compounding period) it will take for the\nfor your loan" +
            " to be less than or equal to that amount!\n" +
            "(enter number less than your initial balance)\n\n";

    public static final String USER_CHOICE = "For option A enter 1 for option B enter 2\n";

    public static final String DOES_USER_WANT_TABLE = "Enter 1 for Table or 2 for just the " +
            "answer\n";

    public static final String SPECIFY_THE_NUMBER_OF_PERIODS = "Please specify the Number " +
            "of periods(in the same units specified for\nthe compounding period) min num " +
            "of period is 1";

    public static final String SPECIFY_GOAL_FOR_BALANCE = "Specify a goal for the " +
            "Balance and I'll calculate how long it\nwill take to reach that Balance";

    public static final String BALANCE = "Balance";

    public static final String BALANCE_AT_END_OF_WANTED_PERIODS_1 = "Your Balance at the end" +
            " of ";

    public static final String BALANCE_AT_END_OF_WANTED_PERIODS_2 = " periods is: \n";

    public static final String NUMBER_OF_PERIODS_AT_TO_ACHIEVE_BALANCE = "The number of " +
            "periods required to reach ";

    public static final String NUMBER_OF_PERIODS_AT_TO_ACHIEVE_BALANCE_2 = " balance is ";


    public static final String WANT_TO_RESTART_APP_WITH_SAME_PC_TH = "\nPress 1 to restart the" +
            " app with the same threshold and processing charge\nOR\n";

    public static final String WANT_TO_RESTART_APP_WITH_SAME_PC_TH_2 = "Press 2 to restart the" +
            " app from scratch \nOR\n";

    public static final String WANT_TO_RESTART_APP_WITH_SAME_PC_TH_3 = "Press 3 to stop the App \n";

    public static final String APP_IS_SHUTTING_DOWN = "App is now shutting down......\n";

    public static final String NEWLINE = "\n";

    public static final String DAILY_COMPOUNDING_WORD = "Day";
    public static final String MONTHLY_COMPOUNDING_WORD = "Month";
    public static final String YEARLY_COMPOUNDING_WORD = "Year";

    public static final String TAB = "\t";
    public static final String DOUBLE_TAB = TAB + TAB;

    public static final int DAILY_COMPOUNDING = 365;
    public static final int MONTHLY_COMPOUNDING = 12;
    public static final int YEARLY_COMPOUNDING = 1;

    public static final double MAX_INTEREST = 0.1;
    public static final double MIN_INTEREST = 0.00;
    public static final double MIN_PROCESSING_CHARGE = 0.00;
    public static final double MIN_THRESHOLD_AMOUNT = 0.00;
    public static final double MIN_INITIAL_BALANCE = 0.00;
    public static final double MIN_PERIODIC_PAYMENT = 0.00;

    public static final int MIN_COMPOUNDING_PERIOD = 1;
    public static final int MAX_COMPOUNDING_PERIOD = 3;
    public static final int MIN_USER_CHOICE_FOR_OPTIONS = 1;
    public static final int MAX_USER_CHOICE_FOR_OPTIONS = 2;
    public static final int MIN_USER_CHOICE_FOR_DISPLAY = 1;
    public static final int MAX_USER_CHOICE_FOR_DISPLAY = 2;
    public static final int MIN_PERIOD_NUMBER = 1;
    public static final int MIN_RESTART_OPTION = 1;
    public static final int MAX_RESTART_OPTION = 3;
    public boolean isUserInSubLoanMode;
    public boolean isUserEnteringProcessingCharge;
    public boolean isUserEnteringThreshold;

    public boolean isUserInLoanMode;
    public boolean isUserEnteringInitialBalance;
    public boolean isUserEnteringPeriodicPayment;
    public boolean isUserEnteringAnnualInterestRate;
    public boolean isUserEnteringCompoundedInterestPeriod;

    public boolean isUserInDisplayInfoMode;
    public boolean isUserEnteringOptionChoice;
    public boolean isUserEnteringDisplayChoice;

    public boolean isUserInProcessingMode;
    public boolean isUserEnteringNumberOfPeriodsForOptionA;
    public boolean isUserEnteringGoalBalanceForOptionB;
    public boolean finishedCalculationForOptionA;
    public boolean finishedCalculationForOptionB;
    public boolean isAppStarting;

    public boolean isUserInDisplayFinalAnswerMode;

    public boolean isUserInRestartAppMode;
    public boolean endApp;

    public double processingCharge;
    public double thresholdAmount;
    public double initialBalance;
    public double newBalance;
    public double finalBalance;
    public double periodicPayment;
    public double annualInterestRate;
    public double interest;//
    public double goalBalanceForOptionB;

    public int interestRateCompoundingPeriod;
    public int userChoiceForOptions;
    public int numberOfPeriodsForOptionA;
    public int numberOfPeriodsToAchieveGoalBalanceOptionB;
    public int userChoiceForDisplay;
    public int userChoiceForRestartingApp;

    public String optionATableAnswer;
}
/*
    View is the point of contact to the User for both input & output.
    It relays information to the user via information obtained from the ViewModel in the form of a ViewState object.
    It has no business logic what so ever.
 */
class View {

    private final ViewListener listener;
    private Scanner inputReader = new Scanner(System.in);

    public View(ViewListener listener) {
        this.listener = listener;
    }

    public void setNewViewState(ViewState newViewState) {
        if (newViewState.displayOutput) {
            System.out.println(newViewState.output);
        }
        if (newViewState.askForInput) {
            listener.enteredInput(inputReader.next());
        }
    }
}
interface ViewListener {
    void enteredInput(String input);
}
/*
    ViewState is used to update the View with
    new output or with whether or not input is
    needed
 */
class ViewState {

    public String output;

    public boolean displayOutput;

    public boolean askForInput;
}
