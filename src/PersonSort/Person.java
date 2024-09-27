package PersonSort;

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

    public int getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName + " " + age + " лет";
    }
}