package game;

import java.util.Scanner;

public class Play
{
    private static int selection = 0;
    private static final Scanner scan = new Scanner(System.in);

    public static Warriors knight = new Warriors("Рыцарь", 100, 20, 90);
    public static Warriors infantryman = new Warriors("Пехотинец", 50, 10, 50);

    public static Enemys tier1 = new Enemys("Варвар 1-ого уровня", 50, 10, 5, 25);
    public static Enemys tier2 = new Enemys("Варвар 2-ого уровня", 100, 25, 10, 50);
    public static Enemys tier3 = new Enemys("Варвар 3-ого уровня", 200, 50, 50, 100);

    public static King king = new King(200, 100, 0);

    public static int knightCount = 0;
    public static int infantrymanCount = 0;
    public static int enemysCount = 0;

    public static int wave = 1;

    public static void main(String[] args)
    {
        menu();
    }


    private static void menu()
    {
        System.out.println("Профиль:\n "
                         + "Общий счет: " + king.score
                         + "\n Количество золота: " + king.gold
                         + "\n Количество рыцарей: " + knightCount
                         + "\n Количество пехотинцев: " + infantrymanCount
                         + "\n Текущая волна: " + wave);

        System.out.println("\nВыберите нужный пункт:\n 1 - Купить воинов \n 2 - Атаковать лагерь варваров \n 0 - Выход");
        selection = scan.nextInt();

        switch (selection)
        {
            case 1:
                buyWarriors();
                break;
            case 2:
                enemyWaves();
                break;
            default:
                System.out.println("\nВы выбрали несуществующий пункт, выберите еще раз от 0 до 2");
                menu();
        }
    }
    private static void buyWarriors()
    {
        System.out.println("\nВыберите тип воина для покупки:\n 1 - Рыцарь\n 2 - Пехотинец\n 0 - Назад");
        int warriorType = scan.nextInt();

        if (warriorType == 0)
        {
            menu();
            return;
        }

        System.out.println("Введите количество воинов для покупки:");
        int quantity = scan.nextInt();

        if (quantity < 0)
        {
            System.out.println("Количество воинов не может быть отрицательным. Попробуйте снова.");
            buyWarriors();
            return;
        }

        int totalCost = 0;

        if (warriorType == 1)
        {
            totalCost = knight.getCost() * quantity;
        }
        else if (warriorType == 2)
        {
            totalCost = infantryman.getCost() * quantity;
        }

        if (king.gold >= totalCost)
        {
            king.gold -= totalCost;
            if (warriorType == 1)
            {
                knightCount += quantity;
            }
            else if (warriorType == 2)
            {
                infantrymanCount += quantity;
            }
            System.out.println("Покупка успешно завершена!");
        }
        else
        {
            System.out.println("Недостаточно золота для покупки. Выберите меньшее количество воинов или заработайте больше золота.");
        }

        menu();
    }
    private static void enemyWaves()
    {
        if (wave == 1)
        {
            while (enemysCount >= 0)
            {

            }
        }
    }
}