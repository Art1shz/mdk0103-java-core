package Bank;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class PersonalAccount
{
    public static void authorization(List<Account> accounts) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Для того чтобы пользоваться банком, вам необходимо войти в свою учетную запись.");

        while (true) {
            BigInteger accID;
            boolean accountExists = false;

            while (true) {
                try {
                    System.out.println("Введите ваш номер счета в формате 1XXXXXXXXXXXXXXXXXXX: ");
                    accID = scan.nextBigInteger();

                    for (Account acc : accounts) {
                        if (acc.getAccID().equals(accID)) {
                            accountExists = true;
                            break;
                        }
                    }

                    if (!accountExists) {
                        System.out.println("Пользователя с таким номером счета не существует. Попробуйте еще.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Неверный формат номера счета. Попробуйте снова.");
                    scan.nextLine();
                }
            }

            boolean isAuthorized = false;

            while (true) {
                System.out.println("Введите пароль для аккаунта " + accID + " (или '0' для выхода): ");
                int inputCode = scan.nextInt();

                if (inputCode == 0) {
                    System.out.println("Выход из ввода пароля. Вернемся к вводу номера счета.");
                    break;
                }

                try {
                    for (Account acc : accounts) {
                        if (acc.getAccID().equals(accID) && acc.getCode() == inputCode) {
                            System.out.println("Авторизация успешна! Добро пожаловать, " + acc.getFullName());
                            isAuthorized = true;
                            showMenu(acc);
                            break;
                        }
                    }

                    if (isAuthorized) {
                        break;
                    } else {
                        System.out.println("Неверный пароль. Попробуйте снова.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат пароля. Попробуйте снова.");
                }
            }
            if (isAuthorized) {
                break;
            }
        }
    }

    public static void showMenu(Account acc) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n Вы зашли как: " + acc.getFullName() + "\n Баланс: " + String.format("%.2f", acc.getBalance()) + " руб. \n" +
                                                                         " Ваш уровень кэшбека - " + acc.getStatus() + "\n" +
                                                                         " Уровень вашего кэшбека позволяет вам вернуть: ");

            if (acc.getStatus().equals("Базовый")) System.out.println(" * 1% при оплате услуги от 10000р. \n" +
                                                                      " Ваша ежемесячная плата за услуги составляет: 100 рублей \n" +
                                                                      " Дата предыдущего списания: " + acc.lastMonthlyFeeDate);

            if (acc.getStatus().equals("Премиум")) System.out.println(" * 5% при оплате услуги от 10000р. \n" +
                                                                      " Ваша ежемесячная плата за услуги составляет: 0 рублей");

            if (acc.getStatus().equals("ВИП")) System.out.println(" * 1% при оплате услуги до 10000р. \n" +
                                                                  " * 5% при оплате услуги от 10000р. \n" +
                                                                  " * 10% при оплате услуги от 100000р.\n" +
                                                                  " Ваша ежемесячная плата за услуги составляет: 0 рублей");

            if (acc.getPaymentCount() == 0 ) System.out.println("Вам доступен приветственный бонус за оплату услуг (1000 рублей)");

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
