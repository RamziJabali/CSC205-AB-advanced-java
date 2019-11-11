public abstract class RollingStock {
    private String nameOfOwner;
    private final int UNIQUE_ID;
    private int weightOfFrameWithWheels;

    public RollingStock(int id, String owner, int _weightWithFrame) {
        nameOfOwner = owner;
        UNIQUE_ID = id;
        weightOfFrameWithWheels = _weightWithFrame;
    }

    public int getWeightOfFrameWithWheels() {
        return weightOfFrameWithWheels;
    }

    public int getUNIQUE_ID() {
        return UNIQUE_ID;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    @Override
    public String toString() {
        String text = "Name of owner: " + nameOfOwner + "\n" +
                "The ID is: " + UNIQUE_ID + "\n" +
                "The weight of the frame with the wheels is: " + weightOfFrameWithWheels + "\n";
        return text;
    }
}
