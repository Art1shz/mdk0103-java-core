package Alpinists;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static int selection;

    public static void main(String[] args)
    {
        Alpinists.addAlpinist("Иван", 30, "Москва, Россия");
        Alpinists.addAlpinist("Sophie", 25, "Paris, France");
        Alpinists.addAlpinist("Ahmed", 35, "Dar es Salaam, Tanzania");
        Groups.group1.addToGroup1(Alpinists.alpinistsArray[0]);
        Groups.group1.addToGroup1(Alpinists.alpinistsArray[1]);
        Groups.group1.addToGroup1(Alpinists.alpinistsArray[2]);
        Groups.group1.setIsOpen(false);

        do {
            System.out.println("Меню:");
            System.out.println("1. Статус групп");
            System.out.println("2. Добавить альпиниста");
            System.out.println("3. Список альпинистов");
            System.out.println("4. Список гор");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            selection = scan.nextInt();
            scan.nextLine();
            switch (selection) {
                case 1:
                    groupStatus();
                    break;
                case 2:
                    addAlpinist();
                    break;
                case 3:
                    Alpinists.getAlpinists();
                    break;
                case 4:
                    System.out.println("1. " + Mountains.mountain1 + "\n2. " + Mountains.mountain2 + "\n3. " + Mountains.mountain3);
                case 5:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        } while (selection != 5);
    }

    private static void addAlpinist() {
        System.out.println("Введите имя: ");
        String name = scan.nextLine();
        System.out.println("Введите возраст: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("Введите адрес: ");
        String address = scan.nextLine();
        Alpinists.addAlpinist(name, age, address);
    }

    private static void groupStatus() {
        int alpinistNumber;
        System.out.println("Выберите номер группы состав которой вы хотите посмотреть:");
        System.out.println("1 - Группа №1");
        System.out.println("2 - Группа №2");
        System.out.println("3 - Группа №3");
        System.out.println("4. Выход");
        System.out.print("Выберите действие: ");
        selection = scan.nextInt();
        scan.nextLine();
        switch (selection) {
            case 1:
                System.out.println("Группа №1:\n Гора: " + Groups.group1.getMountainName() + "\n Статус набора: " + Groups.group1.getIsOpen() + "\nCостав: ");
                Groups.getGroup1();
                System.out.println("Выберите функцию:");
                System.out.println("1 - Добавить альпиниста в группу(если набор открыт)");
                System.out.println("2 - Изменить состояние набора");
                System.out.println("3 - Выйти в меню");
                selection = scan.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("Введите номер альпиниста:");
                        alpinistNumber = scan.nextInt();
                        Groups.group1.addToGroup1(Alpinists.alpinistsArray[alpinistNumber - 1]);
                        break;
                    case 2:
                        if(Groups.group2.getIsOpen())
                            Groups.group2.setIsOpen(false);
                        else
                            Groups.group2.setIsOpen(true);
                    case 3:
                        return;
                    default:
                        System.out.println("Такого варианта не существует");
                        return;
                }
            case 2:
                System.out.println("Группа №2:\n Гора: " + Groups.group2.getMountainName() + "\n Статус набора: " + Groups.group2.getIsOpen() + "\nCостав: ");
                Groups.getGroup2();
                System.out.println("Выберите функцию:");
                System.out.println("1 - Добавить альпиниста в группу(если набор открыт)");
                System.out.println("2 - Изменить состояние набора");
                System.out.println("3 - Выйти в меню");
                selection = scan.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("Введите номер альпиниста:");
                        alpinistNumber = scan.nextInt();
                        Groups.group2.addToGroup2(Alpinists.alpinistsArray[alpinistNumber - 1]);
                        break;
                    case 2:
                        if(Groups.group2.getIsOpen())
                            Groups.group2.setIsOpen(false);
                        else
                            Groups.group2.setIsOpen(true);
                    case 3:
                        return;
                    default:
                        System.out.println("Такого варианта не существует");
                        return;
                }
                break;
            case 3:
                System.out.println("Группа №3:\n Гора: " + Groups.group3.getMountainName() + "\n Статус набора: " + Groups.group3.getIsOpen() + "\nCостав: ");
                Groups.getGroup3();
                System.out.println("Выберите функцию:");
                System.out.println("1 - Добавить альпиниста в группу(если набор открыт)");
                System.out.println("2 - Изменить состояние набора");
                System.out.println("3 - Выйти в меню");
                selection = scan.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("Введите номер альпиниста:");
                        alpinistNumber = scan.nextInt();
                        Groups.group3.addToGroup3(Alpinists.alpinistsArray[alpinistNumber - 1]);
                        break;
                    case 2:
                        if(Groups.group3.getIsOpen())
                            Groups.group3.setIsOpen(false);
                        else
                            Groups.group3.setIsOpen(true);
                    case 3:
                        return;
                    default:
                        System.out.println("Такого варианта не существует");
                        return;
                }
        }
    }
}
