package Events;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DB
{
    private final String host = "localhost";
    private final String port = "5432";
    private final String db_name = "events";
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
        try (Connection conn = getDBConnection();
             Statement stmt = conn.createStatement())
        {

            String createEventsTable = """
                    CREATE TABLE IF NOT EXISTS events (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        text TEXT NOT NULL,
                        date TIMESTAMP NOT NULL
                    )""";

            stmt.executeUpdate(createEventsTable);
            System.out.println("Таблица events успешно создана.");
        }
    }

    public void addEvent(Event event) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO events (title, text, date) VALUES (?, ?, ?)";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, event.getText());
            pstmt.setTimestamp(3, Timestamp.valueOf(event.getDate()));

            pstmt.executeUpdate();
            System.out.println("Событие добавлено: " + event.getTitle());
        }
    }

    public List<Event> getAllEvents() throws SQLException, ClassNotFoundException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events ORDER BY date DESC";
        
        try (Connection conn = getDBConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("text"),
                    rs.getTimestamp("date").toLocalDateTime()
                );
                events.add(event);
            }
        }
        return events;
    }

    public void updateEvent(Event event) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE events SET title = ?, text = ?, date = ? WHERE id = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, event.getText());
            pstmt.setTimestamp(3, Timestamp.valueOf(event.getDate()));
            pstmt.setInt(4, event.getId());

            pstmt.executeUpdate();
            System.out.println("Событие обновлено: " + event.getTitle());
        }
    }

    public void deleteEvent(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM events WHERE id = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Событие удалено с ID: " + id);
        }
    }

    public Event getEventById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM events WHERE id = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Event(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("text"),
                    rs.getTimestamp("date").toLocalDateTime()
                );
            }
        }
        return null;
    }
}