package products.Products;

import java.util.ArrayList;
import java.util.List;

public class MyProducts
{
    private double maxProteins;
    private double maxFats;
    private double maxCarbohydrates;
    private double maxCalories;
    private List<Product> productList;

    public MyProducts(double maxProteins, double maxFats, double maxCarbohydrates, double maxCalories)
    {
        this.maxProteins = maxProteins;
        this.maxFats = maxFats;
        this.maxCarbohydrates = maxCarbohydrates;
        this.maxCalories = maxCalories;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product)
    {
        try
        {
            if (product.getProteins() > maxProteins)
            {
                throw new IllegalArgumentException("Продукт " + product.getName() + " не может быть добавлен: слишком много белков");
            }
            if (product.getFats() > maxFats)
            {
                throw new IllegalArgumentException("Продукт " + product.getName() + " не может быть добавлен: слишком много жиров");
            }
            if (product.getCarbohydrates() > maxCarbohydrates)
            {
                throw new IllegalArgumentException("Продукт " + product.getName() + " не может быть добавлен: слишком много углеводов");
            }
            if (product.getCalories() > maxCalories)
            {
                throw new IllegalArgumentException("Продукт " + product.getName() + " не может быть добавлен: слишком много калорий");
            }

            productList.add(product);
            System.out.println("Продукт " + product.getName() + " добавлен в список");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void printProductNames()
    {
        System.out.println("Список продуктов:");
        for (Product product : productList)
        {
            System.out.println("- " + product.getName());
        }
    }
}
