package Registration.Users;

import Registration.Users.Exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class userManager
{
    private List<user> users = new ArrayList<>();

    public void registerUser(String login, String password, String repeatPassword) throws loginException, passwordException
    {
        if (!isValidLogin(login))
        {
            throw new loginException("Логин должен содержать только латинские буквы, цифры и знак подчеркивания и длина должна быть меньше 15 символов");
        }
        if (!isValidPassword(password))
        {
            throw new passwordException("Пароль должен содержать от 7 до 20 символов");
        }
        if (!password.equals(repeatPassword))
        {
            throw new passwordException("Пароли не совпадают");
        }

        users.add(new user(login, password));
    }

    private boolean isValidLogin(String login)
    {
        return login.matches("^[a-zA-Z0-9_]{1,14}$");
    }

    private boolean isValidPassword(String password)
    {
        return password.length() >= 7 && password.length() <= 20;
    }

    public void printUsers()
    {
        for (user user : users)
        {
            System.out.println(user);
        }
    }
}
