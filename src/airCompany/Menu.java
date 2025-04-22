package airCompany;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Menu
{
    private Database db;
    private Scanner scanner;

    public Menu(Database db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        try {
            boolean running = true;
            while (running) {
                showMainMenu();
                int choice = getIntInput("Выберите пункт меню: ");
                
                switch (choice) {
                    case 1:
                        aircraftMenu();
                        break;
                    case 2:
                        pilotsMenu();
                        break;
                    case 3:
                        flightsMenu();
                        break;
                    case 4:
                        passengersMenu();
                        break;
                    case 5:
                        ticketsMenu();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Программа завершена.");
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n===== МЕНЮ АВИАКОМПАНИИ =====");
        System.out.println("1. Управление самолётами");
        System.out.println("2. Управление пилотами");
        System.out.println("3. Управление полётами");
        System.out.println("4. Управление пассажирами");
        System.out.println("5. Управление билетами");
        System.out.println("0. Выход");
    }
    
    private void aircraftMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== УПРАВЛЕНИЕ САМОЛЁТАМИ =====");
            System.out.println("1. Добавить самолёт");
            System.out.println("2. Показать все самолёты");
            System.out.println("3. Найти самолёты по вместимости");
            System.out.println("4. Обновить информацию о самолёте");
            System.out.println("5. Удалить самолёт");
            System.out.println("0. Вернуться в главное меню");
            
            int choice = getIntInput("Выберите пункт меню: ");
            
            try {
                switch (choice) {
                    case 1:
                        addAircraft();
                        break;
                    case 2:
                        showAllAircraft();
                        break;
                    case 3:
                        findAircraftByCapacity();
                        break;
                    case 4:
                        updateAircraft();
                        break;
                    case 5:
                        deleteAircraft();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void pilotsMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== УПРАВЛЕНИЕ ПИЛОТАМИ =====");
            System.out.println("1. Добавить пилота");
            System.out.println("2. Показать всех пилотов");
            System.out.println("3. Обновить информацию о пилоте");
            System.out.println("4. Удалить пилота");
            System.out.println("0. Вернуться в главное меню");
            
            int choice = getIntInput("Выберите пункт меню: ");
            
            try {
                switch (choice) {
                    case 1:
                        addPilot();
                        break;
                    case 2:
                        showAllPilots();
                        break;
                    case 3:
                        updatePilot();
                        break;
                    case 4:
                        deletePilot();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void flightsMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== УПРАВЛЕНИЕ ПОЛЁТАМИ =====");
            System.out.println("1. Добавить полёт");
            System.out.println("2. Показать все полёты");
            System.out.println("3. Обновить информацию о полёте");
            System.out.println("4. Удалить полёт");
            System.out.println("0. Вернуться в главное меню");
            
            int choice = getIntInput("Выберите пункт меню: ");
            
            try {
                switch (choice) {
                    case 1:
                        addFlight();
                        break;
                    case 2:
                        showAllFlights();
                        break;
                    case 3:
                        updateFlight();
                        break;
                    case 4:
                        deleteFlight();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void passengersMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== УПРАВЛЕНИЕ ПАССАЖИРАМИ =====");
            System.out.println("1. Добавить пассажира");
            System.out.println("2. Показать всех пассажиров");
            System.out.println("3. Обновить информацию о пассажире");
            System.out.println("4. Удалить пассажира");
            System.out.println("0. Вернуться в главное меню");
            
            int choice = getIntInput("Выберите пункт меню: ");
            
            try {
                switch (choice) {
                    case 1:
                        addPassenger();
                        break;
                    case 2:
                        showAllPassengers();
                        break;
                    case 3:
                        updatePassenger();
                        break;
                    case 4:
                        deletePassenger();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void ticketsMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== УПРАВЛЕНИЕ БИЛЕТАМИ =====");
            System.out.println("1. Добавить билет");
            System.out.println("2. Показать все билеты");
            System.out.println("3. Обновить информацию о билете");
            System.out.println("4. Удалить билет");
            System.out.println("0. Вернуться в главное меню");
            
            int choice = getIntInput("Выберите пункт меню: ");
            
            try {
                switch (choice) {
                    case 1:
                        addTicket();
                        break;
                    case 2:
                        showAllTickets();
                        break;
                    case 3:
                        updateTicket();
                        break;
                    case 4:
                        deleteTicket();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void addAircraft() throws Exception {
        System.out.println("\n===== ДОБАВЛЕНИЕ САМОЛЁТА =====");
        String model = getStringInput("Введите модель самолёта: ");
        int capacity = getIntInput("Введите вместимость: ");
        int year = getIntInput("Введите год выпуска: ");
        
        db.addAircraft(model, capacity, year);
        System.out.println("Самолёт успешно добавлен!");
    }
    
    private void showAllAircraft() throws Exception {
        System.out.println("\n===== СПИСОК ВСЕХ САМОЛЁТОВ =====");
        List<String> aircraft = db.getAllAircraft();
        if (aircraft.isEmpty()) {
            System.out.println("Самолёты не найдены.");
        } else {
            aircraft.forEach(System.out::println);
        }
    }
    
    private void findAircraftByCapacity() throws Exception {
        System.out.println("\n===== ПОИСК САМОЛЁТОВ ПО ВМЕСТИМОСТИ =====");
        int minCapacity = getIntInput("Введите минимальную вместимость: ");
        
        List<String> aircraft = db.getAircraftByCapacity(minCapacity);
        if (aircraft.isEmpty()) {
            System.out.println("Самолёты с такой вместимостью не найдены.");
        } else {
            System.out.println("Найденные самолёты:");
            aircraft.forEach(System.out::println);
        }
    }
    
    private void updateAircraft() throws Exception {
        System.out.println("\n===== ОБНОВЛЕНИЕ ИНФОРМАЦИИ О САМОЛЁТЕ =====");
        int id = getIntInput("Введите ID самолёта: ");
        String model = getStringInput("Введите новую модель самолёта: ");
        int capacity = getIntInput("Введите новую вместимость: ");
        int year = getIntInput("Введите новый год выпуска: ");
        
        db.updateAircraft(id, model, capacity, year);
        System.out.println("Информация о самолёте успешно обновлена!");
    }
    
    private void deleteAircraft() throws Exception {
        System.out.println("\n===== УДАЛЕНИЕ САМОЛЁТА =====");
        int id = getIntInput("Введите ID самолёта для удаления: ");
        
        db.deleteAircraft(id);
        System.out.println("Самолёт успешно удалён!");
    }
    
    private void addPilot() throws Exception {
        System.out.println("\n===== ДОБАВЛЕНИЕ ПИЛОТА =====");
        String firstName = getStringInput("Введите имя пилота: ");
        String lastName = getStringInput("Введите фамилию пилота: ");
        int experience = getIntInput("Введите опыт работы (лет): ");
        String license = getStringInput("Введите номер лицензии: ");
        
        db.addPilot(firstName, lastName, experience, license);
        System.out.println("Пилот успешно добавлен!");
    }
    
    private void showAllPilots() throws Exception {
        System.out.println("\n===== СПИСОК ВСЕХ ПИЛОТОВ =====");
        List<String> pilots = db.getAllPilots();
        if (pilots.isEmpty()) {
            System.out.println("Пилоты не найдены.");
        } else {
            pilots.forEach(System.out::println);
        }
    }
    
    private void updatePilot() throws Exception {
        System.out.println("\n===== ОБНОВЛЕНИЕ ИНФОРМАЦИИ О ПИЛОТЕ =====");
        int id = getIntInput("Введите ID пилота: ");
        String firstName = getStringInput("Введите новое имя пилота: ");
        String lastName = getStringInput("Введите новую фамилию пилота: ");
        int experience = getIntInput("Введите новый опыт работы (лет): ");
        String license = getStringInput("Введите новый номер лицензии: ");
        
        db.updatePilot(id, firstName, lastName, experience, license);
        System.out.println("Информация о пилоте успешно обновлена!");
    }
    
    private void deletePilot() throws Exception {
        System.out.println("\n===== УДАЛЕНИЕ ПИЛОТА =====");
        int id = getIntInput("Введите ID пилота для удаления: ");
        
        db.deletePilot(id);
        System.out.println("Пилот успешно удалён!");
    }
    
    private void addFlight() throws Exception {
        System.out.println("\n===== ДОБАВЛЕНИЕ ПОЛЁТА =====");
        String flightNumber = getStringInput("Введите номер полёта: ");
        String departureCity = getStringInput("Введите город отправления: ");
        String arrivalCity = getStringInput("Введите город прибытия: ");
        
        System.out.println("Введите дату и время отправления (формат: ГГГГ-ММ-ДД ЧЧ:ММ:СС):");
        String dateTimeStr = scanner.nextLine();
        Timestamp departureTime = Timestamp.valueOf(dateTimeStr);
        
        int aircraftId = getIntInput("Введите ID самолёта: ");
        
        db.addFlight(flightNumber, departureCity, arrivalCity, departureTime, aircraftId);
        System.out.println("Полёт успешно добавлен!");
    }
    
    private void showAllFlights() throws Exception {
        System.out.println("\n===== СПИСОК ВСЕХ ПОЛЁТОВ =====");
        List<String> flights = db.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("Полёты не найдены.");
        } else {
            flights.forEach(System.out::println);
        }
    }
    
    private void updateFlight() throws Exception {
        System.out.println("\n===== ОБНОВЛЕНИЕ ИНФОРМАЦИИ О ПОЛЁТЕ =====");
        int id = getIntInput("Введите ID полёта: ");
        String flightNumber = getStringInput("Введите новый номер полёта: ");
        String departureCity = getStringInput("Введите новый город отправления: ");
        String arrivalCity = getStringInput("Введите новый город прибытия: ");
        
        System.out.println("Введите новую дату и время отправления (формат: ГГГГ-ММ-ДД ЧЧ:ММ:СС):");
        String dateTimeStr = scanner.nextLine();
        Timestamp departureTime = Timestamp.valueOf(dateTimeStr);
        
        int aircraftId = getIntInput("Введите новый ID самолёта: ");
        
        db.updateFlight(id, flightNumber, departureCity, arrivalCity, departureTime, aircraftId);
        System.out.println("Информация о полёте успешно обновлена!");
    }
    
    private void deleteFlight() throws Exception {
        System.out.println("\n===== УДАЛЕНИЕ ПОЛЁТА =====");
        int id = getIntInput("Введите ID полёта для удаления: ");
        
        db.deleteFlight(id);
        System.out.println("Полёт успешно удалён!");
    }
    
    private void addPassenger() throws Exception {
        System.out.println("\n===== ДОБАВЛЕНИЕ ПАССАЖИРА =====");
        String firstName = getStringInput("Введите имя пассажира: ");
        String lastName = getStringInput("Введите фамилию пассажира: ");
        String passport = getStringInput("Введите номер паспорта: ");
        String phone = getStringInput("Введите номер телефона: ");
        
        db.addPassenger(firstName, lastName, passport, phone);
        System.out.println("Пассажир успешно добавлен!");
    }
    
    private void showAllPassengers() throws Exception {
        System.out.println("\n===== СПИСОК ВСЕХ ПАССАЖИРОВ =====");
        List<String> passengers = db.getAllPassengers();
        if (passengers.isEmpty()) {
            System.out.println("Пассажиры не найдены.");
        } else {
            passengers.forEach(System.out::println);
        }
    }
    
    private void updatePassenger() throws Exception {
        System.out.println("\n===== ОБНОВЛЕНИЕ ИНФОРМАЦИИ О ПАССАЖИРЕ =====");
        int id = getIntInput("Введите ID пассажира: ");
        String firstName = getStringInput("Введите новое имя пассажира: ");
        String lastName = getStringInput("Введите новую фамилию пассажира: ");
        String passport = getStringInput("Введите новый номер паспорта: ");
        String phone = getStringInput("Введите новый номер телефона: ");
        
        db.updatePassenger(id, firstName, lastName, passport, phone);
        System.out.println("Информация о пассажире успешно обновлена!");
    }
    
    private void deletePassenger() throws Exception {
        System.out.println("\n===== УДАЛЕНИЕ ПАССАЖИРА =====");
        int id = getIntInput("Введите ID пассажира для удаления: ");
        
        db.deletePassenger(id);
        System.out.println("Пассажир успешно удалён!");
    }
    
    private void addTicket() throws Exception {
        System.out.println("\n===== ДОБАВЛЕНИЕ БИЛЕТА =====");
        int flightId = getIntInput("Введите ID полёта: ");
        int passengerId = getIntInput("Введите ID пассажира: ");
        String seatNumber = getStringInput("Введите номер места: ");
        double price = getDoubleInput("Введите цену билета: ");
        
        db.addTicket(flightId, passengerId, seatNumber, price);
        System.out.println("Билет успешно добавлен!");
    }
    
    private void showAllTickets() throws Exception {
        System.out.println("\n===== СПИСОК ВСЕХ БИЛЕТОВ =====");
        List<String> tickets = db.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("Билеты не найдены.");
        } else {
            tickets.forEach(System.out::println);
        }
    }
    
    private void updateTicket() throws Exception {
        System.out.println("\n===== ОБНОВЛЕНИЕ ИНФОРМАЦИИ О БИЛЕТЕ =====");
        int id = getIntInput("Введите ID билета: ");
        int flightId = getIntInput("Введите новый ID полёта: ");
        int passengerId = getIntInput("Введите новый ID пассажира: ");
        String seatNumber = getStringInput("Введите новый номер места: ");
        double price = getDoubleInput("Введите новую цену билета: ");
        
        db.updateTicket(id, flightId, passengerId, seatNumber, price);
        System.out.println("Информация о билете успешно обновлена!");
    }
    
    private void deleteTicket() throws Exception {
        System.out.println("\n===== УДАЛЕНИЕ БИЛЕТА =====");
        int id = getIntInput("Введите ID билета для удаления: ");
        
        db.deleteTicket(id);
        System.out.println("Билет успешно удалён!");
    }
    
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите число.");
            }
        }
    }
} 