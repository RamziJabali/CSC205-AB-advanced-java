public class Inventory {
    public String item;
    public Double quantity;
    public Double value;

    public Inventory(String Item, Double Quantity, Double Value) {
        item = Item;
        quantity = Quantity;
        value = Value;
    }

    @Override
    public String toString() {
        return "Item: " + item + ", " +
                "Quantity: " + quantity + ", " +
                "Value: " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Inventory)) {
            return false;
        }
        String otherItem = ((Inventory) o).item;
        return otherItem.equalsIgnoreCase(item);
    }
}
