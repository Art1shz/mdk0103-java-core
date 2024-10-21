package products.Products.Nutrients.Nutrient;

public abstract class Nutrient
{
    double amount;

    public Nutrient(double amount)
    {
        setAmount(amount);
    }

    public void setAmount(double amount)
    {
        if (amount < 0)
        {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }
        this.amount = amount;
    }

    public double getAmount()
    {
        return amount;
    }
}
