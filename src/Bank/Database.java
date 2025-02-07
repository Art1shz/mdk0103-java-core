package Bank;

import java.sql.*;
import java.math.BigInteger;
import java.time.LocalDate;

public class Database {
    private final String host = "localhost";
    private final String port = "5432";
    private final String db_name = "Bank";
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

    public void createTables() throws SQLException, ClassNotFoundException {
        try (Connection conn = getDBConnection();
             Statement stmt = conn.createStatement()) {

            String createAccountTable = """
                CREATE TABLE IF NOT EXISTS Account (
                    accID VARCHAR(21) PRIMARY KEY,
                    fullName VARCHAR(100) NOT NULL,
                    balance DOUBLE PRECISION NOT NULL,
                    status VARCHAR(20) NOT NULL,
                    code INT NOT NULL,
                    lastMonthlyFeeDate DATE
                )""";

            String createTransactionTable = """
                CREATE TABLE IF NOT EXISTS Transaction (
                    id SERIAL PRIMARY KEY,
                    senderID VARCHAR(21) REFERENCES Account(accID),
                    recipientID VARCHAR(21) REFERENCES Account(accID),
                    amount DOUBLE PRECISION NOT NULL,
                    transactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )""";

            stmt.executeUpdate(createAccountTable);
            stmt.executeUpdate(createTransactionTable);
            System.out.println("Таблицы успешно созданы (если их не было).");
        }
    }

    public void dropTables() throws SQLException, ClassNotFoundException {
        try (Connection conn = getDBConnection();
             Statement stmt = conn.createStatement()) {

            String dropTransactionTable = "DROP TABLE IF EXISTS Transaction";
            String dropAccountTable = "DROP TABLE IF EXISTS Account";

            stmt.executeUpdate(dropTransactionTable);
            stmt.executeUpdate(dropAccountTable);

            System.out.println("Таблицы Transaction и Account успешно удалены.");
        }
    }

    public void addAccountDB(String accID, String fullName, double balance, String status, int code, LocalDate localDate) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Account (accID, fullName, balance, status, code, lastMonthlyFeeDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accID);
            pstmt.setString(2, fullName);
            pstmt.setDouble(3, balance);
            pstmt.setString(4, status);
            pstmt.setInt(5, code);
            pstmt.setDate(6, java.sql.Date.valueOf(localDate));

            pstmt.executeUpdate();
            System.out.println("Аккаунт добавлен: " + fullName);
        }
    }

    public void deleteAccountDB(String accID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Account WHERE accID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accID);

            pstmt.executeUpdate();
        }
    }

    public void addTransactionDB(String senderID, String recipientID, double amount) throws SQLException, ClassNotFoundException {
        Connection conn = getDBConnection();
        try {
            conn.setAutoCommit(false);

            String updateSenderBalance = "UPDATE Account SET balance = balance - ? WHERE accID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSenderBalance)) {
                pstmt.setDouble(1, amount);
                pstmt.setString(2, senderID);
                pstmt.executeUpdate();
            }

            String updateRecipientBalance = "UPDATE Account SET balance = balance + ? WHERE accID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateRecipientBalance)) {
                pstmt.setDouble(1, amount);
                pstmt.setString(2, recipientID);
                pstmt.executeUpdate();
            }

            String insertTransaction = "INSERT INTO Transaction (senderID, recipientID, amount) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertTransaction)) {
                pstmt.setString(1, senderID);
                pstmt.setString(2, recipientID);
                pstmt.setDouble(3, amount);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Транзакция успешно выполнена!");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Ошибка выполнения транзакции. Операции откатаны.");
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public void updateAccountBalanceDB(String accID, double newBalance) throws SQLException, ClassNotFoundException
    {
        String sql = "UPDATE Account SET balance = ? WHERE accID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accID);

            pstmt.executeUpdate();
        }
    }

    public Account getAccountById(String accID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Account WHERE accID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("fullName");
                double balance = rs.getDouble("balance");
                String status = rs.getString("status");
                int code = rs.getInt("code");
                return new Account(fullName, new BigInteger(accID), balance, status, code);
            } else {
                return null;
            }
        }
    }

    public void updateLastMonthlyFeeDataDB(String accID) throws SQLException, ClassNotFoundException
    {
        String sql = "UPDATE Account SET lastmonthlyfeedate = ? WHERE accID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setString(2, accID);

            pstmt.executeUpdate();
        }
    }

}
