package Company;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBase database = new DataBase();
        database.isConnection();
        database.insertPartners("1", "ааааа", "ааааа@noreply.com");
        database.UpdatePartners("1", "ббббб", "ббббб@noreply.com");
        database.selectPartners();
        database.selectDeliveries();
    }
}