package ArrayTask.ArrayPerson;

public class Person
{
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void displayInfo()
    {
        System.out.println("Имя: " + firstName + ", Фамилия: " + lastName + ", Возраст: " + age);
    }
}
