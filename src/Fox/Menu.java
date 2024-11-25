package Fox;

import java.util.Scanner;

public class Menu
{
    private Adventure adventure;
    private String savedState = null;

    public Menu()
    {
        this.adventure = new Adventure();
    }

    public void displayMenu()
    {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running)
        {
            System.out.println("""
                    Меню:
                    1. Начать игру
                    2. Продолжить игру
                    3. Выйти
                    """);

            int choice = scan.nextInt();

            switch (choice)
            {
                case 1 ->
                {
                    savedState = null;
                    adventure.startGame(this);
                }
                case 2 ->
                {
                    if (savedState == null)
                    {
                        System.out.println("Нет сохранённой игры. Начните новую");
                    } else
                    {
                        adventure.continueGame(savedState, this);
                    }
                }
                case 3 ->
                {
                    System.out.println("Выход из игры");
                    running = false;
                }
                default -> System.out.println("Некорректный выбор, попробуйте снова");
            }
        }
    }

    public void saveGame(String state)
    {
        savedState = state;
        System.out.println("Игра сохранена");
        displayMenu();
    }
}