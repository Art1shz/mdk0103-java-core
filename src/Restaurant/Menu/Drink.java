package Restaurant.Menu;

public class Drink {
    private String name;
    public int calories;
    public boolean isAlcoholic;
    private double price;

    public Drink(String name, int calories, boolean isAlcoholic, double price) {
        this.name = name;
        this.calories = calories;
        this.isAlcoholic = isAlcoholic;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String alcoholDescription;
        if (isAlcoholic) {
            alcoholDescription = "Алкогольный";
        } else {
            alcoholDescription = "Безалкогольный";
        }
        return name + " (" + alcoholDescription + ") - " + String.format("%.2f", price) + " руб.";
    }
}