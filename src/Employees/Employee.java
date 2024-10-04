package Employees;

public class Employee
{
    private String name;
    private String company;
    private int salary;
    private int age;

    public Employee(String name, String company, int salary, int age)
    {
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Рабочий[" +
                "Имя = " + name +
                ", компания = " + company +
                ", зарплата = " + salary +
                " руб., возраст = " + age +
                "]";
    }
}
