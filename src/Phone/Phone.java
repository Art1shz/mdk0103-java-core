package Phone;

import java.util.Random;

public class Phone {
    private String serialNumber;
    private String model;
    private int weight;
    private int price;

    public Phone(String serialNumber, String model, int weight, int price) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Телефон[" +
                "Серийный номер = " + serialNumber +
                ", модель = " + model +
                ", вес = " + weight +
                " гр., цена = " + price +
                " руб.]";
    }

    public static Phone generateRandomPhone() {
        Random random = new Random();
        String serialNumber = "SN-" + (100000000 + random.nextInt(900000000));
        String model = "M-" + (random.nextInt(9000) + 1000);
        int weight = 200 + random.nextInt(300);
        int price = 10000 + random.nextInt(90000);
        return new Phone(serialNumber, model, weight, price);
    }
}
