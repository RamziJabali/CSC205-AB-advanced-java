public class TrapezoidalBox extends Container {
    private double upperLength;
    private double lowerLength;
    private double width;
    private double height;

    public TrapezoidalBox(double _upperLength, double _lowerLength, double _width, double _height, double _thicknessOfWalls, double _densityOfWalls) {
        super(_thicknessOfWalls, _densityOfWalls);
        upperLength = _upperLength;
        lowerLength = _lowerLength;
        width = _width;
        height = _height;
    }

    public double getUpperLength() {
        return upperLength;
    }

    public double getLowerLength() {
        return lowerLength;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double exteriorVolume() {
        return (0.5) * (upperLength + lowerLength) * width * height;
    }

    @Override
    public double interiorVolume() {
        return (0.5) * (upperLength - (2 * getThicknessOfWalls()) + lowerLength - (2 * getThicknessOfWalls())) * (width - (2 * getThicknessOfWalls())) * (height * getThicknessOfWalls());
    }

    @Override
    public String toString() {
        String text = "upper length of the Trapezoidal Box: " + upperLength + "\n" +
                "lower length of the Trapezoidal Box: " + lowerLength + "\n" +
                "width of the Trapezoidal Box: " + width + "\n" +
                "height of the Trapezoidal Box: " + height + "\n";
        return text + super.toString();
    }
}
