package Bank;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Authorization {
    public static void authorization(List<Account> accounts) throws SQLException, ClassNotFoundException
    {
        AuthorizationGUI AUGui = new AuthorizationGUI(accounts);
        AUGui.getFrame().setVisible(true);

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

                for (Account acc : accounts) {
                    if (acc.getAccID().equals(accID) && acc.getCode() == inputCode) {
                        System.out.println("Авторизация успешна! Добро пожаловать, " + acc.getFullName());
                        isAuthorized = true;
                        Menu.showMenu(acc);
                        break;
                    }
                }

                if (isAuthorized) {
                    break;
                } else {
                    System.out.println("Неверный пароль. Попробуйте снова.");
                }
            }

            if (isAuthorized) {
                break;
            }
        }
    }
}
