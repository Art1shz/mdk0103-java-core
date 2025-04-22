package airCompany;

public class Main {
    public static void main(String[] args) {
        try {
            Database db = new Database();
            
            db.createTables();
            
            initializeDatabase(db);
            
            Menu menu = new Menu(db);
            menu.run();
            
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void initializeDatabase(Database db) throws Exception {
        if (db.getAllAircraft().isEmpty()) {
            System.out.println("Инициализация базы данных начальными данными...");
            
            db.addAircraft("Boeing 737", 180, 2015);
            db.addAircraft("Airbus A320", 150, 2016);
            db.addAircraft("Boeing 787", 250, 2017);
            
            db.addPilot("Иван", "Иванов", 10, "LIC001");
            db.addPilot("Алексей", "Алексеев", 15, "LIC002");
            db.addPilot("Матвей", "Матвеев", 8, "LIC003");
            
            db.addFlight("FL001", "Москва", "Санкт-Петербург", java.sql.Timestamp.valueOf("2024-03-20 10:00:00"), 1);
            db.addFlight("FL002", "Москва", "Сочи", java.sql.Timestamp.valueOf("2024-03-20 12:00:00"), 2);
            db.addFlight("FL003", "Москва", "Екатеринбург", java.sql.Timestamp.valueOf("2024-03-20 14:00:00"), 3);
            
            db.addPassenger("Василий", "Васильев", "PAS001", "+7-999-111-11-11");
            db.addPassenger("Фёдор", "Фёдоров", "PAS002", "+7-999-222-22-22");
            db.addPassenger("Григорий", "Григорьев", "PAS003", "+7-999-333-33-33");
            
            db.addTicket(1, 1, "A1", 5000.0);
            db.addTicket(2, 2, "B2", 8000.0);
            db.addTicket(3, 3, "C3", 6000.0);
            
            System.out.println("База данных успешно инициализирована!");
        } else {
            System.out.println("База данных уже содержит данные. Инициализация не требуется.");
        }
    }
}
