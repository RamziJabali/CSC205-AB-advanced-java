public class Contents {
    private String nameOfContent;
    private double densityOfContents;
    private double valueOfContents;

    public Contents(double _valueOfContents, String _nameOfContent, double _densityOfContents) {
        nameOfContent = _nameOfContent;
        densityOfContents = _densityOfContents;
        valueOfContents = _valueOfContents;
    }

    public void setValueOfContents(int _valueOfContents) {
        valueOfContents = _valueOfContents;
    }

    public double getDensityOfContents() {
        return densityOfContents;
    }

    public double getValueOfContents() {
        return valueOfContents;
    }

    public String getNameOfContent() {
        return nameOfContent;
    }

    @Override
    public String toString() {
        String text = "Content: " + nameOfContent + "\n" +
                "Density(Pounds per Cubic Foot): " + densityOfContents + "\n" +
                "Value(Dollars Per Pound): " + valueOfContents;
        return text;
    }
}
