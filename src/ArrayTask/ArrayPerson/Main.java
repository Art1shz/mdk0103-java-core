package ArrayTask.ArrayPerson;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Person[] persons = new Person[0];
        Scanner scanner = new Scanner(System.in);
        persons = addPerson(persons, new Person("Ольга", "Бессонова", 50));
        persons = addPerson(persons, new Person("Артем", "Бессонов", 18));
        persons = addPerson(persons, new Person("Дмитрий", "Бессонов", 29));

        while (true)
        {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить человека");
            System.out.println("2. Вывести всех людей");
            System.out.println("3. Найти человека по имени");
            System.out.println("4. Удалить человека по индексу");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.print("Введите имя: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Введите фамилию: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Введите возраст: ");
                    int age = scanner.nextInt();
                    persons = addPerson(persons, new Person(firstName, lastName, age));
                    break;

                case 2:
                    printPersons(persons);
                    break;

                case 3:
                    System.out.print("Введите имя для поиска: ");
                    String searchName = scanner.nextLine();
                    findPerson(persons, searchName);
                    break;

                case 4:
                    System.out.print("Введите индекс человека для удаления: ");
                    int indexToRemove = scanner.nextInt();
                    persons = removePerson(persons, indexToRemove);
                    break;

                case 5:
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public static Person[] addPerson(Person[] persons, Person newPerson)
    {
        Person[] newPersons = new Person[persons.length + 1];
        for (int i = 0; i < persons.length; i++)
        {
            newPersons[i] = persons[i];
        }
        newPersons[persons.length] = newPerson;
        return newPersons;
    }

    public static void printPersons(Person[] persons)
    {
        if (persons.length == 0)
        {
            System.out.println("Список людей пуст.");
            return;
        }
        for (int i = 0; i < persons.length; i++)
        {
            System.out.print(i + ": ");
            persons[i].displayInfo();
        }
    }

    public static void findPerson(Person[] persons, String name)
    {
        boolean found = false;
        for (Person person : persons)
        {
            if (person.getFirstName().equalsIgnoreCase(name))
            {
                person.displayInfo();
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("Человек с именем " + name + " не найден.");
        }
    }

    public static Person[] removePerson(Person[] persons, int index)
    {
        if (index < 0 || index >= persons.length)
        {
            System.out.println("Неверный индекс.");
            return persons;
        }

        Person[] newPersons = new Person[persons.length - 1];
        for (int i = 0; i < index; i++)
        {
            newPersons[i] = persons[i];
        }

        for (int i = index; i < persons.length - 1; i++)
        {
            newPersons[i] = persons[i + 1];
        }

        System.out.println("Человек на индексе " + index + " удален.");
        return newPersons;
    }
}
