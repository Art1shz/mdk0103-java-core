package Restaurant.Menu;

public class Dish {
    private String name;
    public boolean isVegetarian;
    public int calories;
    public String type;
    private double price;

    public Dish(String name, boolean isVegetarian, int calories, String type, double price) {
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.calories = calories;
        this.type = type;
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
        String typeDescription;
        if (isVegetarian) {
            typeDescription = "Вегетарианское";
        } else {
            typeDescription = "Не вегетарианское";
        }
        return name + " (" + typeDescription + ") - " + String.format("%.2f", price) + " руб.";
    }
}