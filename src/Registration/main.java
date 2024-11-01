package Registration;

import Registration.Users.*;
import Registration.Users.Exceptions.*;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        userSettings userSettings = new userSettings();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.print("Введите логин: ");
            String login = scanner.nextLine();

            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            System.out.print("Повторите пароль: ");
            String repeatPassword = scanner.nextLine();

            try
            {
                userSettings.registerUser(login, password, repeatPassword);
                System.out.println("Пользователь успешно зарегистрирован");
            }
            catch (loginException | passwordException e)
            {
                System.out.println(e.getMessage());
            }

            System.out.print("Хотите добавить еще одного пользователя? (да/нет): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("да"))
            {
                break;
            }
        }

        System.out.println("Список зарегистрированных пользователей:");
        userSettings.printUsers();
    }
}
