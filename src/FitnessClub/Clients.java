package FitnessClub;

public class Clients {
    private static int nextClientId = 1;
    public static int clientId = 1;
    private final String firstName;
    private final String lastName;
    private final int birthYear;
    private static final int MAX_CLIENTS = 60;
    public static Clients[] clientsArray = new Clients[MAX_CLIENTS];
    public static int numClients = 0;

    public Clients(String firstName, String lastName, int birthYear)
    {
        this.clientId = nextClientId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public static void addClient(String firstName, String lastName, int birthYear) {
        if (numClients < MAX_CLIENTS) {
            clientsArray[numClients++] = new Clients(firstName, lastName, birthYear);
        } else {
            System.out.println("Достигнуто максимальное количество клиентов");
        }
    }

    public static void getClients() {
        if (clientsArray != null)
        {
            for (int i = 0; i < numClients; i++) {
                Clients client = clientsArray[i];
                System.out.println(i + 1 + ". Имя: " + client.getFirstName() + " Фамилия: " + client.getLastName() + " Год рождения: " + client.getBirthYear());
            }
        }
        else
        {
            System.out.println("Клиенты не найдены");
            return;
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;

    }
}