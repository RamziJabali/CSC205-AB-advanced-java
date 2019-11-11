public class Cylinder extends Container {
    private double radius;
    private double height;

    public Cylinder(double _height, double _radius, double _thicknessOfWalls, double _densityOfWalls) {
        super(_thicknessOfWalls, _densityOfWalls);
        radius = _radius;
        height = _height;
    }

    public double getHeight() {
        return height;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double exteriorVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public double interiorVolume() {
        return Math.PI * Math.pow((radius - getThicknessOfWalls()), 2) * (height - 2 * getThicknessOfWalls());
    }

    @Override
    public String toString() {
        String text = "radius of Cylinder: " + radius + "\n" +
                "height of Cylinder: " + height + "\n";
        return text + super.toString();
    }
}
