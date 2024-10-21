package products;
import products.Products.*;
import products.Products.Nutrients.*;

public class Application {
    public static void main(String[] args)
    {
        MyProducts myProducts = new MyProducts(50, 30, 100, 400);

        Product apple = new Product("Яблоко", new Proteins(0.3), new Fats(0.2), new Carbohydrates(14), 52);
        Product banana = new Product("Банан", new Proteins(1.1), new Fats(0.3), new Carbohydrates(23), 96);
        Product chicken = new Product("Курица", new Proteins(31), new Fats(3.6), new Carbohydrates(0), 165);
        Product cheese = new Product("Сыр", new Proteins(25), new Fats(33), new Carbohydrates(1.3), 402);
        Product rice = new Product("Рис", new Proteins(2.7), new Fats(0.3), new Carbohydrates(28), 130);
        Product chocolate = new Product("Шоколад", new Proteins(7.6), new Fats(30), new Carbohydrates(60), 546);

        myProducts.addProduct(apple);
        myProducts.addProduct(banana);
        myProducts.addProduct(chicken);
        myProducts.addProduct(cheese);
        myProducts.addProduct(rice);
        myProducts.addProduct(chocolate);

        myProducts.printProductNames();
    }
}
