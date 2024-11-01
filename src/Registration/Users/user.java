package Registration.Users;

public class user
{
    private String login;
    private String password;

    public user(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Логин: " + login + ", Пароль: " + password;
    }
}
