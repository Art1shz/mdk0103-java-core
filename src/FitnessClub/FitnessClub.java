package FitnessClub;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class FitnessClub {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int selection;
        do {
            System.out.println("Меню:");
            System.out.println("1. Вход посетителя в зону");
            System.out.println("2. Вывод информации о посетителях по зонам");
            System.out.println("3. Регистрация нового посетителя");
            System.out.println("4. Вывод всех абонементов");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    enterZone();
                    break;
                case 2:
                    clientsInfo();
                    break;
                case 3:
                    addClient();
                    break;
                case 4:
                    Abonements.getAllAbonements();
                    menu();
                    break;
                case 5:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        } while (selection != 4);
    }

    private static void addClient() {
        String firstName, lastName;
        int birthYear;
        System.out.print("Введите имя:");
        firstName = scan.nextLine();
        System.out.println("Введите фамилию:");
        lastName = scan.nextLine();
        System.out.println("Введите год рождения:");
        birthYear = scan.nextInt();
        scan.nextLine();
        System.out.println("Введите тип абонемента: \n 1 - Разовый \n 2 - Дневной \n 3 - Полный");
        int typeSelection = scan.nextInt();
        scan.nextLine();

        switch (typeSelection) {
            case 1:
                Clients.addClient(firstName, lastName, birthYear);
                Abonements.addOneTimeAbonement(Clients.clientsArray[Clients.numClients - 1], LocalDate.now(), LocalDate.now().plusDays(1), "Разовый");
                Abonements.addAllAbonementsArray(Clients.clientsArray[Clients.numClients - 1], LocalDate.now(), LocalDate.now().plusDays(1), "Разовый");
                break;
            case 2:
                Clients.addClient(firstName, lastName, birthYear);
                Abonements.addDailyAbonement(Clients.clientsArray[Clients.numClients - 1], LocalDate.now(), LocalDate.now().plusDays(14), "Дневной");
                Abonements.addAllAbonementsArray(Clients.clientsArray[Clients.numClients - 1], LocalDate.now(), LocalDate.now().plusDays(14), "Дневной");
                break;
            case 3:
                Clients.addClient(firstName, lastName, birthYear);
                Abonements.addFullAbonement(Clients.clientsArray[Clients.numClients - 1], LocalDate.now(), LocalDate.now().plusDays(30), "Полный");
                Abonements.addAllAbonementsArray(Clients.clientsArray[Clients.numClients - 1], LocalDate.now(), LocalDate.now().plusDays(30), "Полный");
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте снова.");
                menu();
                break;
        }
        menu();
    }

    private static void clientsInfo() {
        System.out.println("Посетители тренажерного зала");
        if (Abonements.oneTimeAbonementsArray != null) {
            Abonements.getOneTimeAbonements();
        }
        if (Abonements.dailyAbonementsArray != null) {
            Abonements.getDailyAbonements();
        }
        if (Abonements.fullAbonementsArray != null) {
            Abonements.getFullAbonements();
        }
        System.out.println("Посетители бассейна");
        if (Abonements.oneTimeAbonementsArray != null) {
            Abonements.getOneTimeAbonements();
        }
        if (Abonements.fullAbonementsArray != null) {
            Abonements.getFullAbonements();
        }
        System.out.println("Посетители групповых занятий");
        if (Abonements.dailyAbonementsArray != null) {
            Abonements.getDailyAbonements();
        }
        if (Abonements.fullAbonementsArray != null) {
            Abonements.getFullAbonements();
        }
    }
    private static void enterZone() {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        int zoneSelection;
        String zoneSelected;
        Clients.getClients();
        System.out.print("Выберите свой номер: ");
        int clientSelection = scan.nextInt();
        scan.nextLine();
        System.out.println("Ваш тип абонемента: "+ Abonements.allAbonementsArray[clientSelection - 1].getType());
        if (date.isAfter(Abonements.allAbonementsArray[clientSelection - 1].getExpirationDate()))
        {
            System.out.println("Ваш абонемент истек");
            menu();
        }
        System.out.println("\nВыберите, куда вы хотите пойти: ");
        System.out.println("1 - тренажерный зал");
        System.out.println("2 - бассейн");
        System.out.println("3 - групповые занятия");
        System.out.println("4 - выйти");
        zoneSelection = scan.nextInt();
        scan.nextLine();
        switch (zoneSelection) {
            case 1:
                zoneSelected = "тренажерный зал";
                if (Zones.canAccessZone(zoneSelected, Abonements.allAbonementsArray[clientSelection - 1].getType())) {
                    if (Zones.canAccessZoneByTimeAndType(Abonements.allAbonementsArray[clientSelection - 1].getType(), zoneSelected, time)) {
                        System.out.println("Доступ разрешен в " + zoneSelected);
                    } else {
                        System.out.println("Доступ в " + zoneSelected + " запрещен в данный момент");
                    }
                } else {
                    System.out.println("Доступ в " + zoneSelected + " запрещен для данного абонемента");
                }
                break;
            case 2:
                zoneSelected = "бассейн";
                if (Zones.canAccessZone(zoneSelected, Abonements.allAbonementsArray[clientSelection - 1].getType())) {
                    if (Zones.canAccessZoneByTimeAndType(Abonements.allAbonementsArray[clientSelection - 1].getType(), zoneSelected, time)) {
                        System.out.println("Доступ разрешен в " + zoneSelected);
                    }
                    else
                    {
                        System.out.println("Доступ в " + zoneSelected + " запрещен в данный момент");
                    }
                }
                else
                {
                    System.out.println("Доступ в " + zoneSelected + " запрещен для данного абонемента");
                }
                break;
            case 3:
                zoneSelected = "групповые занятия";
                if (Zones.canAccessZone(zoneSelected, Abonements.allAbonementsArray[clientSelection - 1].getType())) {
                    if (Zones.canAccessZoneByTimeAndType(Abonements.allAbonementsArray[clientSelection - 1].getType(), zoneSelected, time)) {
                        System.out.println("Доступ разрешен в " + zoneSelected);
                    } else {
                        System.out.println("Доступ в " + zoneSelected + " запрещен в данный момент");
                    }
                } else {
                    System.out.println("Доступ в " + zoneSelected + " запрещен для данного абонемента");
                }
                break;
            case 4:
                System.out.println("До свидания!");
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте снова.");
                enterZone();
                break;
        }
    }
}
