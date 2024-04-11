package Alpinists;

public class Alpinists
{
    private final String name;
    private final int age;
    private final String address;
    public static Alpinists[] alpinistsArray = new Alpinists[15];
    public static int numAlpinist = 0;


    public Alpinists(String name, int age, String address)
    {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getAddress()
    {
        return address;
    }

    public static void addAlpinist(String firstName, int age, String address)
    {
            alpinistsArray[numAlpinist++] = new Alpinists(firstName, age, address);
    }

    public static void getAlpinists()
    {
        for (int i = 0; i < 15; i++) {
            if (alpinistsArray[i] != null) {
                Alpinists alpinist = alpinistsArray[i];
                System.out.println(i + 1 + ". Имя: " + alpinist.getName() + " Возраст: " + alpinist.getAge() + " Адрес: " + alpinist.getAddress());
            }
            else
                return;
        }
    }
}
