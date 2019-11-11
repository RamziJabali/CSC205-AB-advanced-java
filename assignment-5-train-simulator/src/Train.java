import java.util.ArrayList;

public class Train {
    private ArrayList<FreightCar> freightCarList;
    private Engine engine;
    private String engineerName;

    public Train(Engine eng, String engName) {
        engine = eng;
        engineerName = engName;
        freightCarList = new ArrayList<FreightCar>();
    }

    public void addFreightCarToList(FreightCar _car) {
        freightCarList.add(_car);
        System.out.println("Freight Car: " + freightCarList.get(freightCarList.size() - 1).getUNIQUE_ID() + " has been added");
    }

    public void deleteFreightCarFromList(int uniqueId) {
        for (int i = 0; i < freightCarList.size(); i++) {
            if (freightCarList.get(i).getUNIQUE_ID() == uniqueId) {
                freightCarList.remove(i);
                System.out.println("Freight Car: " + uniqueId + " has been removed");
                return;
            }
        }
    }

    public String getEngineerName() {
        return engineerName;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //TODO: CHECK IF THEY FIT THE REQUIREMENTS            //TODO: CHECK IF THEY FIT THE REQUIREMENTS//
    //TODO: CHECK IF THEY FIT THE REQUIREMENTS            //TODO: CHECK IF THEY FIT THE REQUIREMENTS//
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> freightCarsInTrainID() {
        ArrayList<Integer> carId = new ArrayList<Integer>();
        for (int i = 0; i < freightCarList.size(); i++) {
            carId.add(freightCarList.get(i).getUNIQUE_ID());
        }
        return carId;
    }

    public ArrayList<Double> freightCarTotalWeight() {
        ArrayList<Double> weightPerCar = new ArrayList<Double>();
        for (int i = 0; i < freightCarList.size(); i++) {
            weightPerCar.add(freightCarList.get(i).totalWeightOfCar());
        }
        return weightPerCar;
    }

    public ArrayList<Double> freightCarTotalValue() {
        ArrayList<Double> totalValuePerCar = new ArrayList<Double>();
        for (int i = 0; i < freightCarList.size(); i++) {
            totalValuePerCar.add(freightCarList.get(i).computeTotalValueOfCar());
        }
        return totalValuePerCar;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //  END END END END END END END END END END END END END END END END END END END END END END END  //
    //  END END END END END END END END END END END END END END END END END END END END END END END  //
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void changeLoadFactorOfCar(double loadFactor, int uniqueId) {
        for (int i = 0; i < freightCarList.size(); i++) {
            if (freightCarList.get(i).getUNIQUE_ID() == uniqueId) {
                freightCarList.get(i).setLoadFactor(loadFactor);
                System.out.println("Freight Car: " + uniqueId + "has changed load factor to " + loadFactor);
                return;
            }
        }
    }

    public double computeTotalWeightOfTrain() {
        double weight = 0;
        for (int i = 0; i < freightCarList.size(); i++) {
            weight += freightCarList.get(i).totalWeightOfCar() + engine.getTotalWeightOfEngine();
        }
        System.out.println("Finished calculating total Weight of train");
        return weight;
    }

    public double computeTotalValueOfTrain() {
        double totalValueOfTrain = 0;
        for (int i = 0; i < freightCarList.size(); i++) {
            totalValueOfTrain += freightCarList.get(i).computeTotalValueOfCar();
        }
        System.out.println("Finished calculating total value of the train");
        return totalValueOfTrain;
    }

    @Override
    public String toString() {
        String text = "Train Characteristics: \n" +
                "Train Engineer:" + engineerName + "\n \n" +
                "Train Engine Characteristics:\n" +
                engine.toString() + "\n\n" +
                "Train Freight Car characteristics: \n";
        for (int i = 0; i < freightCarList.size(); i++) {
            text += freightCarList.get(i).toString();
        }
        return text;
    }
}
