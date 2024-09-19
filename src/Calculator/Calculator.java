package Calculator;

import java.util.Scanner;

public class Calculator
{
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        double num1;
        double num2 = 0;
        int enable = 1;
        int operation;
        while (enable == 1)
        {
            System.out.println("Введите первое число: ");
            num1 = scan.nextDouble();
            System.out.println("Выберите операцию: \n 1 - Сложение \n 2 - Вычитание \n 3 - Умножение \n 4 - Деление \n 5 - log \n 6 - ln \n 7 - sin \n 8 - cos");
            operation = scan.nextInt();

            while (operation < 1 || operation > 8)
            {
                System.out.println("Неверно выбрана операция (от 1 до 8)");
                System.out.println("Выберите операцию: \n 1 - Сложение \n 2 - Вычитание \n 3 - Умножение \n 4 - Деление \n 5 - log \n 6 - ln \n 7 - sin \n 8 - cos");
                operation = scan.nextInt();
            }

            if (operation <= 4)
            {
                System.out.println("Введите второе число: ");
                num2 = scan.nextDouble();
            }

            if (operation == 4 && num2 == 0)
            {
                System.out.println("Ошибка: деление на ноль невозможно.");
            }
            else
            {
                System.out.println("Результат: " + calculator(num1, num2, operation));
            }

            System.out.println("Хотите сделать еще одно вычисление? \n 1 - Да \n 0 - Нет");
            enable = scan.nextInt();
            while (enable != 1 && enable != 0)
            {
                System.out.println("Выбор выходит из диапазона (от 0 до 1)");
                System.out.println("Хотите сделать еще одно вычисление? \n 1 - Да \n 0 - Нет");
                enable = scan.nextInt();
            }
        }
    System.exit(0);
    }

    public static double calculator(double num1, double num2, int operation)
    {
        return switch (operation)
        {
            case 1 -> add(num1, num2);
            case 2 -> subtract(num1, num2);
            case 3 -> multiply(num1, num2);
            case 4 -> divide(num1, num2);
            case 5 -> log(num1);
            case 6 -> ln(num1);
            case 7 -> sin(num1);
            case 8 -> cos(num1);
            default -> 0;
        };
    }

    private static double add(double num1, double num2)
    {
        return num1 + num2;
    }

    private static double subtract(double num1, double num2)
    {
        return num1 - num2;
    }

    private static double multiply(double num1, double num2)
    {
        return num1 * num2;
    }

    private static double divide(double num1, double num2)
    {
        return num1 / num2;
    }

    private static double log(double num1)
    {
        return Math.log10(num1);
    }

    private static double ln(double num1)
    {
        return Math.log(num1);
    }

    private static double sin(double num1)
    {
        return Math.sin(Math.toRadians(num1));
    }

    private static double cos(double num1)
    {
        return Math.cos(Math.toRadians(num1));
    }
}
