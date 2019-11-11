public abstract class Container {

    private double thicknessOfWalls;
    private double densityOfWalls;

    public Container(double _thicknessOfWalls, double _densityOfWalls) {
        thicknessOfWalls = _thicknessOfWalls;
        densityOfWalls = _densityOfWalls;
    }

    public abstract double interiorVolume();

    public abstract double exteriorVolume();

    private double wallVolume() {
        return exteriorVolume() - interiorVolume();
    }

    public double computeWeightOfWalls() {
        return densityOfWalls * wallVolume();
    }

    public double getDensityOfWalls() {
        return densityOfWalls;
    }

    public double getThicknessOfWalls() {
        return thicknessOfWalls;
    }

    @Override
    public String toString() {
        String text = "Wall thickness of the container: " + thicknessOfWalls + "\n" +
                "Density of the walls: " + densityOfWalls + "\n";
        return text;
    }
}