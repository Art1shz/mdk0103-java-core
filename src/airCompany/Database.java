package airCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database
{
    private final String host = "localhost";
    private final String port = "5432";
    private final String db_name = "airline_db";
    private final String login = "postgres";
    private final String password = "1234";

    private Connection dbConn;

    private Connection getDBConnection() throws ClassNotFoundException, SQLException
    {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + db_name;
        Class.forName("org.postgresql.Driver");
        dbConn = DriverManager.getConnection(str, login, password);
        return dbConn;
    }

    public void isConnection() throws SQLException, ClassNotFoundException
    {
        dbConn = getDBConnection();
        System.out.println(dbConn.isValid(1000));
    }

    public void createTables() throws SQLException, ClassNotFoundException
    {
        dbConn = getDBConnection();
        Statement stmt = dbConn.createStatement();

        stmt.execute("CREATE TABLE IF NOT EXISTS aircraft (" +
                "id SERIAL PRIMARY KEY, " +
                "model VARCHAR(50) NOT NULL, " +
                "capacity INTEGER NOT NULL, " +
                "year_of_manufacture INTEGER NOT NULL)");

        stmt.execute("CREATE TABLE IF NOT EXISTS pilots (" +
                "id SERIAL PRIMARY KEY, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "experience_years INTEGER NOT NULL, " +
                "license_number VARCHAR(20) UNIQUE NOT NULL)");

        stmt.execute("CREATE TABLE IF NOT EXISTS flights (" +
                "id SERIAL PRIMARY KEY, " +
                "flight_number VARCHAR(10) UNIQUE NOT NULL, " +
                "departure_city VARCHAR(50) NOT NULL, " +
                "arrival_city VARCHAR(50) NOT NULL, " +
                "departure_time TIMESTAMP NOT NULL, " +
                "aircraft_id INTEGER REFERENCES aircraft(id))");

        stmt.execute("CREATE TABLE IF NOT EXISTS passengers (" +
                "id SERIAL PRIMARY KEY, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "passport_number VARCHAR(20) UNIQUE NOT NULL, " +
                "phone VARCHAR(20))");

        stmt.execute("CREATE TABLE IF NOT EXISTS tickets (" +
                "id SERIAL PRIMARY KEY, " +
                "flight_id INTEGER REFERENCES flights(id), " +
                "passenger_id INTEGER REFERENCES passengers(id), " +
                "seat_number VARCHAR(5) NOT NULL, " +
                "price DECIMAL(10,2) NOT NULL, " +
                "purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

        stmt.close();
    }

    public void addAircraft(String model, int capacity, int yearOfManufacture) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "INSERT INTO aircraft (model, capacity, year_of_manufacture) VALUES (?, ?, ?)";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, model);
        pstmt.setInt(2, capacity);
        pstmt.setInt(3, yearOfManufacture);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateAircraft(int id, String model, int capacity, int yearOfManufacture) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "UPDATE aircraft SET model = ?, capacity = ?, year_of_manufacture = ? WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, model);
        pstmt.setInt(2, capacity);
        pstmt.setInt(3, yearOfManufacture);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteAircraft(int id) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "DELETE FROM aircraft WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<String> getAllAircraft() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        List<String> aircraftList = new ArrayList<>();
        String sql = "SELECT * FROM aircraft";
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            aircraftList.add(String.format("ID: %d, Model: %s, Capacity: %d, Year: %d",
                    rs.getInt("id"),
                    rs.getString("model"),
                    rs.getInt("capacity"),
                    rs.getInt("year_of_manufacture")));
        }
        
        rs.close();
        stmt.close();
        return aircraftList;
    }

    public List<String> getAircraftByCapacity(int minCapacity) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        List<String> aircraftList = new ArrayList<>();
        String sql = "SELECT * FROM aircraft WHERE capacity >= ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, minCapacity);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            aircraftList.add(String.format("ID: %d, Model: %s, Capacity: %d, Year: %d",
                    rs.getInt("id"),
                    rs.getString("model"),
                    rs.getInt("capacity"),
                    rs.getInt("year_of_manufacture")));
        }
        
        rs.close();
        pstmt.close();
        return aircraftList;
    }


    public void addPilot(String firstName, String lastName, int experienceYears, String licenseNumber) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "INSERT INTO pilots (first_name, last_name, experience_years, license_number) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setInt(3, experienceYears);
        pstmt.setString(4, licenseNumber);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<String> getAllPilots() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        List<String> pilotList = new ArrayList<>();
        String sql = "SELECT * FROM pilots";
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            pilotList.add(String.format("ID: %d, Name: %s %s, Experience: %d years, License: %s",
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("experience_years"),
                    rs.getString("license_number")));
        }
        
        rs.close();
        stmt.close();
        return pilotList;
    }

    public void updatePilot(int id, String firstName, String lastName, int experienceYears, String licenseNumber) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "UPDATE pilots SET first_name = ?, last_name = ?, experience_years = ?, license_number = ? WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setInt(3, experienceYears);
        pstmt.setString(4, licenseNumber);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deletePilot(int id) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "DELETE FROM pilots WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void addFlight(String flightNumber, String departureCity, String arrivalCity, Timestamp departureTime, int aircraftId) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "INSERT INTO flights (flight_number, departure_city, arrival_city, departure_time, aircraft_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, flightNumber);
        pstmt.setString(2, departureCity);
        pstmt.setString(3, arrivalCity);
        pstmt.setTimestamp(4, departureTime);
        pstmt.setInt(5, aircraftId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<String> getAllFlights() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        List<String> flightList = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            flightList.add(String.format("ID: %d, Flight: %s, From: %s, To: %s, Departure: %s",
                    rs.getInt("id"),
                    rs.getString("flight_number"),
                    rs.getString("departure_city"),
                    rs.getString("arrival_city"),
                    rs.getTimestamp("departure_time")));
        }
        
        rs.close();
        stmt.close();
        return flightList;
    }

    public void updateFlight(int id, String flightNumber, String departureCity, String arrivalCity, Timestamp departureTime, int aircraftId) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "UPDATE flights SET flight_number = ?, departure_city = ?, arrival_city = ?, departure_time = ?, aircraft_id = ? WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, flightNumber);
        pstmt.setString(2, departureCity);
        pstmt.setString(3, arrivalCity);
        pstmt.setTimestamp(4, departureTime);
        pstmt.setInt(5, aircraftId);
        pstmt.setInt(6, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteFlight(int id) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "DELETE FROM flights WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void addPassenger(String firstName, String lastName, String passportNumber, String phone) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "INSERT INTO passengers (first_name, last_name, passport_number, phone) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, passportNumber);
        pstmt.setString(4, phone);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<String> getAllPassengers() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        List<String> passengerList = new ArrayList<>();
        String sql = "SELECT * FROM passengers";
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            passengerList.add(String.format("ID: %d, Name: %s %s, Passport: %s, Phone: %s",
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("passport_number"),
                    rs.getString("phone")));
        }
        
        rs.close();
        stmt.close();
        return passengerList;
    }

    public void updatePassenger(int id, String firstName, String lastName, String passportNumber, String phone) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "UPDATE passengers SET first_name = ?, last_name = ?, passport_number = ?, phone = ? WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, passportNumber);
        pstmt.setString(4, phone);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deletePassenger(int id) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "DELETE FROM passengers WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void addTicket(int flightId, int passengerId, String seatNumber, double price) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "INSERT INTO tickets (flight_id, passenger_id, seat_number, price) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, flightId);
        pstmt.setInt(2, passengerId);
        pstmt.setString(3, seatNumber);
        pstmt.setDouble(4, price);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<String> getAllTickets() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        List<String> ticketList = new ArrayList<>();
        String sql = "SELECT t.*, f.flight_number, p.first_name, p.last_name FROM tickets t " +
                    "JOIN flights f ON t.flight_id = f.id " +
                    "JOIN passengers p ON t.passenger_id = p.id";
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            ticketList.add(String.format("ID: %d, Flight: %s, Passenger: %s %s, Seat: %s, Price: %.2f",
                    rs.getInt("id"),
                    rs.getString("flight_number"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("seat_number"),
                    rs.getDouble("price")));
        }
        
        rs.close();
        stmt.close();
        return ticketList;
    }

    public void updateTicket(int id, int flightId, int passengerId, String seatNumber, double price) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "UPDATE tickets SET flight_id = ?, passenger_id = ?, seat_number = ?, price = ? WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, flightId);
        pstmt.setInt(2, passengerId);
        pstmt.setString(3, seatNumber);
        pstmt.setDouble(4, price);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteTicket(int id) throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        String sql = "DELETE FROM tickets WHERE id = ?";
        PreparedStatement pstmt = dbConn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
}