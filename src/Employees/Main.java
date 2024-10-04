package Employees;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        List<Employee> employees = createRandomEmployees(10);

        System.out.println("Оригинальный список:");
        for (Employee e : employees)
        {
            System.out.println(e);
        }

        System.out.println("\nСортировка по имени:");
        sortByName(employees);
        for (Employee e : employees)
        {
            System.out.println(e);
        }

        System.out.println("\nСортировка по имени и зарплате:");
        sortByNameAndSalary(employees);
        for (Employee e : employees)
        {
            System.out.println(e);
        }

        System.out.println("\nСортировка по имени, зарплате, возрасту и компании:");
        sortByAll(employees);
        for (Employee e : employees)
        {
            System.out.println(e);
        }
    }



    private static final String[] maleNames =
    {
        "Антон", "Егор", "Анатолий", "Никита", "Павел", "Олег", "Михаил", "Владислав", "Иннокентий", "Дмитрий"
    };

    private static final String[] femaleNames =
    {
        "Анна", "Мария", "Елена", "Светлана", "Ольга", "Виктория", "Юля", "Диана", "Анастасия", "Ирина"
    };

    private static final String[] companies =
    {
        "Google", "Amazon", "Facebook", "Microsoft", "Apple", "Tesla", "Adobe"
    };

    public static List<Employee> createRandomEmployees(int count)
    {
        List<Employee> employees = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++)
        {
             String name = i % 2 == 0 ? maleNames[random.nextInt(maleNames.length)] : femaleNames[random.nextInt(femaleNames.length)];
             String company = companies[random.nextInt(companies.length)];
             int salary = 30000 + random.nextInt(70000);
             int age = 21 + random.nextInt(39);

             employees.add(new Employee(name, company, salary, age));
        }

        return employees;
    }

    public static void sortByName(List<Employee> employees)
    {
        employees.sort(Comparator.comparing(Employee::getName));
    }

    public static void sortByNameAndSalary(List<Employee> employees)
    {
        employees.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary));
    }

    public static void sortByAll(List<Employee> employees)
    {
        employees.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary).thenComparing(Employee::getAge).thenComparing(Employee::getCompany));
    }
}
