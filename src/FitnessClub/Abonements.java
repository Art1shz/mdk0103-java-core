package FitnessClub;

import java.time.LocalDate;

public class Abonements {
    public String type;
    private final Clients owner;
    private final LocalDate registrationDate;
    private final LocalDate expirationDate;
    private static final int MAX_ABONEMENTS = 20;

    private static int numOfOneTimeAbonements = 0;
    private static int numOfDailyAbonements = 0;
    private static int numOfFullAbonements = 0;
    private static int numOfAllAbonements = 0;

    public static Abonements[] oneTimeAbonementsArray = new Abonements[MAX_ABONEMENTS];
    public static Abonements[] dailyAbonementsArray = new Abonements[MAX_ABONEMENTS];
    public static Abonements[] fullAbonementsArray = new Abonements[MAX_ABONEMENTS];
    public static Abonements[] allAbonementsArray = new Abonements[MAX_ABONEMENTS*3];


    public Abonements(Clients owner, LocalDate registrationDate, LocalDate expirationDate, String type) {
        this.owner = owner;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.type = type;
    }

    public Clients getOwner() {
        return owner;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getType() {
        return type;
    }

    //////////////////////////////////////////////////////
    public static void addOneTimeAbonement(Clients owner, LocalDate registrationDate, LocalDate expirationDate, String type) {
        if (numOfOneTimeAbonements < MAX_ABONEMENTS) {
            oneTimeAbonementsArray[numOfOneTimeAbonements++] = new Abonements(owner, registrationDate, expirationDate, type);
        } else {
            System.out.println("Достигнуто максимальное количество разовых абонементов");
        }
    }

    public static void addDailyAbonement(Clients owner, LocalDate registrationDate, LocalDate expirationDate, String type) {
        if (numOfDailyAbonements < MAX_ABONEMENTS) {
            dailyAbonementsArray[numOfDailyAbonements++] = new Abonements(owner, registrationDate, expirationDate, type);
        } else {
            System.out.println("Достигнуто максимальное количество дневных абонементов");
        }
    }

    public static void addFullAbonement(Clients owner, LocalDate registrationDate, LocalDate expirationDate, String type) {
        if (numOfFullAbonements < MAX_ABONEMENTS) {
            fullAbonementsArray[numOfFullAbonements++] = new Abonements(owner, registrationDate, expirationDate, type);
        } else {
            System.out.println("Достигнуто максимальное количество полных абонементов");
        }
    }

    public static void addAllAbonementsArray(Clients owner, LocalDate registrationDate, LocalDate expirationDate, String type) {
        if (numOfAllAbonements < MAX_ABONEMENTS*3)
        {
            allAbonementsArray[numOfAllAbonements++] = new Abonements(owner, registrationDate, expirationDate, type);
        }
        else
        {
            System.out.println("Достигнуто максимальное количество абонементов");
        }
    }

    //////////////////////////////////////////////////////
    public static void getOneTimeAbonements() {
        if (oneTimeAbonementsArray != null) {
            for (int i = 0; i < numOfOneTimeAbonements; i++) {
                Abonements oneTimeAbonement = oneTimeAbonementsArray[i];
                System.out.println("Владелец: " + oneTimeAbonement.getOwner() + " Дата регистрации: " + oneTimeAbonement.getRegistrationDate() + " Дата окончания: " + oneTimeAbonement.getExpirationDate() + " Тип: " + oneTimeAbonement.getType());
            }
        } else {
            System.out.println("Посетители не найдены");
        }
    }

    public static void getDailyAbonements() {
        if (dailyAbonementsArray != null) {
            for (int i = 0; i < numOfDailyAbonements; i++) {
                Abonements dailyAbonement = dailyAbonementsArray[i];
                System.out.println("Владелец: " + dailyAbonement.getOwner() + " Дата регистрации: " + dailyAbonement.getRegistrationDate() + " Дата окончания: " + dailyAbonement.getExpirationDate() + " Тип: " + dailyAbonement.getType());
            }
        } else {
            System.out.println("Посетители не найдены");
        }
    }

    public static void getFullAbonements() {
        if (fullAbonementsArray != null) {
            for (int i = 0; i < numOfFullAbonements; i++) {
                Abonements fullAbonement = fullAbonementsArray[i];
                System.out.println("Владелец: " + fullAbonement.getOwner() + " Дата регистрации: " + fullAbonement.getRegistrationDate() + " Дата окончания: " + fullAbonement.getExpirationDate() + " Тип: " + fullAbonement.getType());
            }
        } else {
            System.out.println("Посетители не найдены");
        }
    }
    public static void getAllAbonements() {
        if (allAbonementsArray != null) {
            for (int i = 0; i < numOfAllAbonements; i++) {
                Abonements allAbonement = allAbonementsArray[i];
                System.out.println(i + 1 + ". Владелец: " + allAbonement.getOwner() + " Дата регистрации: " + allAbonement.getRegistrationDate() + " Дата окончания: " + allAbonement.getExpirationDate() + " Тип: " + allAbonement.getType());
            }
        } else {
            System.out.println("Клиенты не найдены");
            return;
        }
    }
}