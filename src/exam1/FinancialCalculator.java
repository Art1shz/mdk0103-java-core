package exam1;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class FinancialCalculator
{
    private static int day = 0;
    private static final BigDecimal[] expenses = new BigDecimal[30];
    private static int selection = 0;
    private static final Scanner scan = new Scanner(System.in);
    public static int rewrite = 0;
    public static BigDecimal max = new BigDecimal(0);

    public static void main(String[] args)
    {
        Arrays.fill(expenses, BigDecimal.ZERO);
        menu();
    }

    private static void menu()
    {
        System.out.println("\nВыберите нужный пункт:\n 1 - Ввести расходы за день \n 2 - Вывести траты за месяц \n 3 - Вывести самую большую сумму расходов за месяц \n 4 - Затраты за месяц в других валютах \n 0 - Выход");
        selection = scan.nextInt();

        switch (selection)
        {
            case 1:
                expensesPerDay();
                break;
            case 2:
                expensesPerMonth();
                break;
            case 3:
                biggestExpenseOfTheMonth();
                break;
            case 4:
                expensesPerMonthInAnotherCurrencies();
                break;
            case 0:
                System.out.println("\nСпасибо, что воспользовались данной программой. До свидания!");
                System.exit(1);
            default:
                System.out.println("\nВы выбрали несуществующий пункт, выберите еще раз от 0 до 4");
                menu();
        }
    }

    public static void expensesPerDay()
    {
        if (day == 0)
        {
            System.out.println("\nВыберите день от 1 до 30: ");
            day = scan.nextInt();
        }
        if (day <= 0 || day >= 31)
        {
            System.out.println("\nВы ввели день вне диапазона. Попробуйте еще раз.");
            day = 0;
            expensesPerDay();
        }
        if ((expenses[day - 1].compareTo(BigDecimal.ZERO) <= 0) || (rewrite == 1))
        {
            rewrite = 0;
            for (int i = day - 1; i < expenses.length; i++)
            {

                    System.out.println("\nВыберите сумму потраченную за " + (i + 1) + "-й день");
                    expenses[i] = scan.nextBigDecimal();
                    System.out.println("\nВвести трату за другой день? \n 1 - Да \n 0 - Вернуться в меню");
                    selection = scan.nextInt();
                    switch (selection)
                    {
                        case 1:
                            day = 0;
                            expensesPerDay();
                            break;
                        case 0:
                            menu();
                            break;
                        default:
                        System.out.println("\nВы выбрали несуществующий пункт");
                        menu();
                    }
            }
        }
        else
        {
            System.out.println("\nЗа данный день уже указана сумма " + expenses[day - 1] + " руб. Вы хотите перезаписать? \n 1 - Да \n 0 - Оставить текущую сумму");
            selection = scan.nextInt();
            switch (selection) {
                case 1:
                    rewrite = 1;
                    expensesPerDay();
                    break;
                case 0:
                    day = 0;
                    expensesPerDay();
                    break;
                default:
                    System.out.println("\nВы выбрали несуществующий пункт");
                    menu();
            }
        }
    }
    private static void expensesPerMonth()
    {
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < expenses.length; i++)
        {
            System.out.println((i + 1) + " день - " + expenses[i] + " руб.");
            sum = sum.add(expenses[i]);
        }
        System.out.println("\nВсего потрачено за месяц " + sum + " руб.");
        menu();
    }

    private static void biggestExpenseOfTheMonth ()
    {
        for (BigDecimal expense : expenses)
        {
            if (expense.compareTo(max) > 0)
            {
                max = expense;
            }
        }
        System.out.println("\nСамая большая сумма расходов за месяц: " + max + " руб.");
        menu();
    }

    private static void expensesPerMonthInAnotherCurrencies ()
    {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal dollars = new BigDecimal("91.01");
        BigDecimal euros = new BigDecimal("98.34");
        BigDecimal yuan = new BigDecimal("12.78");
        BigDecimal selectedCurrency = new BigDecimal(0);

        String[] currencies = {"USD", "EUR", "CNY"};

        System.out.printf("\nКурс валют за 03.02.2024 10:02 GMT+3: \n Доллар - %f руб. \n Евро - %f руб. \n Юань - %f руб.", dollars, euros, yuan);
        System.out.println("\n\nВыберите в какой валюте хотите узнать затраты за месяц: \n 1 - Доллар \n 2 - Eвро \n 3 - Юани \n 0 - Вернуться в меню");
        selection = scan.nextInt();
        switch (selection)
        {
            case 1:
                selectedCurrency = dollars;
                break;
            case 2:
                selectedCurrency = euros;
                break;
            case 3:
                selectedCurrency = yuan;
                break;
            case 0:
                menu();
                break;
            default:
                System.out.println("\nВы выбрали несуществующий пункт, выберите еще раз от 0 до 3");
                expensesPerMonthInAnotherCurrencies();
        }
        for (int i = 0; i < expenses.length; i++)
        {
            System.out.println((i + 1) + " день - " + (expenses[i].divide(selectedCurrency, 2, RoundingMode.HALF_UP)) + " " + currencies[selection - 1]);
            sum = sum.add(expenses[i].divide(selectedCurrency, 2, RoundingMode.HALF_UP));
        }
        System.out.println("\nВсего потрачено за месяц " + sum + " " + currencies[selection - 1]);
        menu();
    }
}
