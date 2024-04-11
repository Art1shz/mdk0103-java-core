package Alpinists;

public class Mountains
{
    private final String name;
    private final String country;
    private final int height;

    public Mountains(String name, String country, int height)
    {
        this.name = name;
        this.country = country;
        this.height = height;
    }

    static Mountains mountain1 = new Mountains("Эльбрус", "Россия", 5642);
    static Mountains mountain2 = new Mountains("Монблан", "Франция", 4808);
    static Mountains mountain3 = new Mountains("Килиманджаро", "Танзания", 5895);

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return name + ", " + country + ", " + height + "метров.";

    }
}
