package Bank;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void showMenu(Account acc) throws SQLException, ClassNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n Вы зашли как: " + acc.getFullName() + "\n Баланс: " + String.format("%.2f", acc.getBalance()) + " руб. \n" +
                    " Ваш уровень кэшбека - " + acc.getStatus() + "\n" +
                    " Уровень вашего кэшбека позволяет вам вернуть: ");

            if (acc.getStatus().equals("Базовый"))
                System.out.println(" * 1% при оплате услуги от 10000р. \n" +
                        " Ваша ежемесячная плата за услуги составляет: 100 рублей \n" +
                        " Дата предыдущего списания: " + acc.lastMonthlyFeeDate);

            if (acc.getStatus().equals("Премиум"))
                System.out.println(" * 5% при оплате услуги от 10000р. \n" +
                        " Ваша ежемесячная плата за услуги составляет: 0 рублей");

            if (acc.getStatus().equals("ВИП"))
                System.out.println(" * 1% при оплате услуги до 10000р. \n" +
                        " * 5% при оплате услуги от 10000р. \n" +
                        " * 10% при оплате услуги от 100000р.\n" +
                        " Ваша ежемесячная плата за услуги составляет: 0 рублей");

            if (acc.getPaymentCount() == 0)
                System.out.println("Вам доступен приветственный бонус за оплату услуг (1000 рублей)");

            acc.applyMonthlyFee();

            System.out.println("\nВыберите действие:");
            System.out.println("1. Перевести другому человеку на счет");
            System.out.println("2. Оплатить услугу");
            System.out.println("0. Выход");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    acc.transferToAccount();
                    break;
                case 2:
                    acc.paymentForServices();
                    break;
                case 0:
                    System.out.println("Выход из учетной записи.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
