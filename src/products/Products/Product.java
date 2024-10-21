package products.Products;
import products.Products.Nutrients.*;

public class Product
{
    private String name;
    private Proteins proteins;
    private Fats fats;
    private Carbohydrates carbohydrates;
    private double calories;

    public Product(String name, Proteins proteins, Fats fats, Carbohydrates carbohydrates, double calories)
    {
        this.name = name;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        setCalories(calories);
    }

    public void setCalories(double calories)
    {
        if (calories < 0)
        {
            throw new IllegalArgumentException("Количество калорий не может быть отрицательным");
        }
        this.calories = calories;
    }

    public String getName()
    {
        return name;
    }

    public double getProteins()
    {
        return proteins.getAmount();
    }

    public double getFats()
    {
        return fats.getAmount();
    }

    public double getCarbohydrates()
    {
        return carbohydrates.getAmount();
    }

    public double getCalories()
    {
        return calories;
    }
}
