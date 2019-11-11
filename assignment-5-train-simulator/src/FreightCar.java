public class FreightCar extends RollingStock {
    private double loadFactor;//What Percentage of the car is full
    private Container container;
    private Contents contents;

    public FreightCar(String nameOfOwner, int ID, int weightWithFrame, Container _container, Contents _contents, double _loadFactor) {
        super(ID, nameOfOwner, weightWithFrame);
        loadFactor = _loadFactor;
        container = _container;
        contents = _contents;

    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(double _loadFactor) {
        loadFactor = _loadFactor;
    }

    public double computeTotalValueOfCar() {
        return contents.getDensityOfContents() * contents.getValueOfContents();
    }

    public double totalWeightOfCar() {
        return ((contents.getDensityOfContents() * container.interiorVolume()) * loadFactor) + container.computeWeightOfWalls() + getWeightOfFrameWithWheels();
    }

    @Override
    public String toString() {
        String text = "Freight car load factor: " + loadFactor + "\n\n" +
                "Freight car container:\n" + container.toString() + "\n\n" +
                "Freight car contents:\n" + contents.toString() + "\n";
        return super.toString() + text;
    }
}
