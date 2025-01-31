package Company;

import java.sql.*;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String db_name = "Company";
    private final String login = "postgres";
    private final String password = "1234";

    private Connection dbConn;

    private Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + db_name;
        Class.forName("org.postgresql.Driver");
        dbConn = DriverManager.getConnection(str, login, password);
        return dbConn;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnection();
        System.out.println(dbConn.isValid(1000));
    }

    public void selectPartners() throws SQLException, ClassNotFoundException {
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM partners");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String contactInfo = resultSet.getString("contact_info");
            System.out.printf("%d %s %s \n", id, name, contactInfo);
        }
    }

    public void insertPartners(String id, String name, String contactInfo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO partners (id, name, contact_info) VALUES (?, ?, ?)";
        try (PreparedStatement statement = getDBConnection().prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(id));
            statement.setString(2, name);
            statement.setString(3, contactInfo);
            statement.executeUpdate();
        }
    }

    public void UpdatePartners(String id, String newName, String newContactInfo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE partners SET name = ?, contact_info = ? WHERE id = ?";
        try (PreparedStatement statement = getDBConnection().prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newContactInfo);
            statement.setInt(3, Integer.parseInt(id));
            statement.executeUpdate();
        }
    }

    public void selectDeliveries() throws SQLException, ClassNotFoundException {
        String query = "SELECT deliveries.id, deliveries.date, deliveries.status, deliveries.amount, partners.name " +
                "FROM deliveries " +
                "JOIN partners ON deliveries.partner_id = partners.id";
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int deliveryId = resultSet.getInt("id");
            String deliveryDate = resultSet.getString("date");
            String deliveryStatus = resultSet.getString("status");
            double deliveryAmount = resultSet.getDouble("amount");
            String partnerName = resultSet.getString("name");
            System.out.printf("%d %s %s %.2f %s\n", deliveryId, deliveryDate, deliveryStatus, deliveryAmount, partnerName);
        }
    }
}
