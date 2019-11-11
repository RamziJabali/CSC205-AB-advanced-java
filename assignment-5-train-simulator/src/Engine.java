public class Engine extends RollingStock {
    private int pullCapacity;

    public Engine(int id, String nameOfEngine, int _weightWithFrame, int pullCap) {
        super(id, nameOfEngine, _weightWithFrame);
        pullCapacity = pullCap;
    }

    public int getPullCapacity() {
        return pullCapacity;
    }
    public double getTotalWeightOfEngine(){
        return getWeightOfFrameWithWheels();
    }

    @Override
    public String toString() {
        String text = "The engine pull capacity is: " + pullCapacity + "\n";
        return toString() + text;
    }
}
