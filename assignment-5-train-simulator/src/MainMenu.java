import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private static final String[] contentsAvailable = {"Oil", "Coal", "Soybeans", "Linseed, meal", "Oats"};
    private static final double[] contentsDensity = {55, 69, 47, 32, 27};
    private static final double[] contentsValue = {516.73, 0.017, 0.153, 1.30, 0.08};
    private static final Double RAIL_ROAD_CONDITIONS;
    private static Train train;
    private static int numOfCars;
    private static int enginePullCap;
    private static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        numOfCars = 0;
        String nameOfTrainEngineer = getNameOfTrainEngineerFromUser();
        println("Now please enter the details of the engine");
        String nameOfEngineOwner = getNameOfEngineOwner();
        int engineID = getUniqueEngineIDFromUser();
        int engineWeightWithBaseFrame = getEngineWeightWithBaseFrameFromUser();
        enginePullCap = getEnginePullingCapacityFromUser();
        Engine trainEngine = new Engine(engineID, nameOfEngineOwner, engineWeightWithBaseFrame, enginePullCap);//Engine First
        train = new Train(trainEngine, nameOfTrainEngineer);
        freightCarMainMenu();
    }

    private static void freightCarMainMenu() {
        int userChoice;
        do {
            do {
                printUserChoices();
                userChoice = kb.nextInt();
            } while (!(userChoice >= 1 && userChoice <= 6));
            switch (userChoice) {
                case 1:
                    addCarToTrain();
                    numOfCars++;
                    break;
                case 2:
                    displayDescriptionOfTrainEngineAndCars();
                    break;
                case 3:
                    displaySummaryOfCar();
                    break;
                case 4:
                    displayTrainWeightValueAndCars();
                    break;
                case 5:
                    main(new String[0]);
                    break;
                case 6:
                    println("App is now ending...");
                    break;
            }
        } while (userChoice != 6);

    }

    private static void displayTrainWeightValueAndCars() {
        String text = "Train Description: \n" +
                "Train Total Weight: " + train.computeTotalWeightOfTrain() + " Pounds\n" +
                "Train Total Value: " + train.computeTotalValueOfTrain() + " Dollars\n" +
                "Train total Number of cars: " + numOfCars + "\n";
        if (train.computeTotalWeightOfTrain() > enginePullCap) {
            text += "The engines pulling capacity has been EXCEEDED\n";
        }
        println(text);

    }

    private static void displaySummaryOfCar() {
        String text = "";
        ArrayList carIDs = train.freightCarsInTrainID();
        ArrayList carWeight = train.freightCarTotalWeight();
        ArrayList carValues = train.freightCarTotalValue();
        for (int i = 0; i < carIDs.size(); i++) {
            text += "Car ID: " + carIDs.get(i) + "\n";
            text += "Car Total Weight: " + carWeight.get(i) + " pounds\n";
            text += "Car Total Value: $" + carValues.get(i) + "\n";
            if ((Double) carWeight.get(i) > RAIL_ROAD_CONDITIONS) {
                text += "Car " + carIDs.get(i) + " has exceeded \n" +
                        "the weight limit allowed for the rail road\n";
            }
            text += "\n";
        }
        println(text);
    }

    private static void displayDescriptionOfTrainEngineAndCars() {
        System.out.println(train);
    }

    private static void printUserChoices() {
        println("Option (1) Add Freight Car to the Train\n");
        println("Option (2) Display complete description \n" +
                "of characteristics of the Train, Engine, and Cars\n");
        println("Option (3) Display quick Summary showing car\n" +
                "ID's and describing its weight and value\n");
        println("Option (4) Display just the total weight\n" +
                "and value of the Train, as well as the \n" +
                "number of Cars. Will Also notify you if \n" +
                "total weight is greater than the pulling \n" +
                "capacity of the engine");
        println("Option (5) Start a new Train\n");
        println("Option (6) Exit program\n");
    }

    private static void addCarToTrain() {
        Container container;
        FreightCar car;
        String nameOfOwner;
        int ID, weightOfCar;
        double loadFactor;
        println("Creating Freight Car...");
        println("1) Do you want your Freight Car container \n" +
                "to be a Trapezoidal Box or a Cylinder\n" +
                "Enter (1) for Trapezoidal Box\nor \n" +
                "(2) For Cylinder: ");
        if (kb.nextInt() == 1) {//Trapezoidal
            container = trapezoidalContainer();//creating a container
        } else {//Cylinder
            container = cylinderContainer();//creating a container
        }
        println("2) Please enter name of the owner of the\n" +
                "Freight Car: ");
        nameOfOwner = kb.next();
        println("3) Please enter a UNIQUE ID for the Freight Car:");
        ID = kb.nextInt();
        println("4) Enter Weight of the base frame of the car\n" +
                "including the wheels(Pounds): ");
        weightOfCar = kb.nextInt();
        println("5) Enter the load factor(Percentage of the car \n" +
                "full):");
        loadFactor = kb.nextDouble();
        car = new FreightCar(nameOfOwner, ID, weightOfCar, container, contentsOfCar(), loadFactor);//Creating FreightCar and Contents of FreightCar
        println("Adding Freight Car to train...");
        train.addFreightCarToList(car);
    }

    private static Contents contentsOfCar() {
        int ci;
        println("6) The contents that a car can have are as follows:");
        printContents();
        do {
            println("Enter the number corresponding to the item wanted");
            ci = kb.nextInt();
        } while (!(ci >= 0 && ci <= 5));
        String item = contentsAvailable[ci];
        double cv = contentsValue[ci];
        double cd = contentsDensity[ci];
        return new Contents(cv, item, cd);
    }

    private static void printContents() {
        for (int i = 0; i < contentsAvailable.length; i++) {
            println("(" + i + ") " + contentsAvailable[i] + "    " + contentsDensity[i] + " " + contentsValue[i]);
        }
    }

    private static Cylinder cylinderContainer() {
        println("Enter the height of the container(Feet):");
        double ch = kb.nextDouble();
        println("Enter the radius of the container(Feet):");
        double cr = (int) kb.nextDouble();
        println("Enter the thickness of the walls(Feet):");
        double wt = (int) kb.nextDouble();
        println("Enter the density of the walls(Pounds Per Cubic Foot):");
        double wd = kb.nextDouble();
        return new Cylinder(ch, cr, wt, wd);
    }

    private static TrapezoidalBox trapezoidalContainer() {
        println("Enter the Upper Length of the container(Feet):");
        double ul = kb.nextDouble();
        println("Enter the lower Length of the container(Feet):");
        double ll = kb.nextDouble();
        println("Enter the width of the container:(Feet)");
        double cw = kb.nextDouble();
        println("Enter the height of the container:(Feet)");
        double ch = kb.nextDouble();
        println("Enter the thickness of the walls(Feet):");
        double wt = kb.nextDouble();
        println("Enter the density of the walls(Pounds Per Cubic Foot):");
        double wd = kb.nextDouble();
        return new TrapezoidalBox(ul, ll, cw, ch, wt, wd);
    }

    private static String getNameOfTrainEngineerFromUser() {
        println("Please Enter the name of the Train Engineer: ");
        return kb.next();
    }

    private static String getNameOfEngineOwner() {
        println("1) Please Enter the name of the Engine Owner: ");//Asked in class and answer was name of engine
        return kb.next();
    }

    private static int getEngineWeightWithBaseFrameFromUser() {
        println("3) Please Enter Engine weight with base frame: ");
        return kb.nextInt();
    }

    private static int getUniqueEngineIDFromUser() {
        println("2) Please Enter Engine ID(MUST be Unique): ");
        return kb.nextInt();
    }

    private static int getEnginePullingCapacityFromUser() {
        println("4) Please Enter Engine pulling capacity(Pounds): ");
        return kb.nextInt();
    }

    static {
        println("Enter the maximum acceptable load \n" +
                "for a SINGLE car in pounds:");
        RAIL_ROAD_CONDITIONS = kb.nextDouble();
    }

    private static void println(String text) {
        System.out.println(text);
    }

}
