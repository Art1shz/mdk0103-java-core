package Bank;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Database db = new Database();

        db.dropTables(); // удаление таблиц чтобы не переделывать систему рандомного заполнения
        db.createTables(); // создание таблиц

        Random random = new Random();

        List<Account> accounts = new ArrayList<>();

        String[] surnames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов", "Попов", "Новиков", "Федоров", "Морозов", "Романов"};
        String[] names = {"Иван", "Петр", "Алексей", "Максим", "Дмитрий", "Сергей", "Олег", "Владимир", "Андрей", "Роман"};
        String[] patronymics = {"Иванович", "Петрович", "Алексеевич", "Максимович", "Дмитриевич", "Сергеевич", "Олегович", "Владимирович", "Андреевич", "Романович"};
        String[] status = {"Базовый", "Премиум", "ВИП"};
        BigInteger accID = new BigInteger("10000000000000000000");

            for (int i = 0; i < 15; i ++){
                String randomSurname = surnames[random.nextInt(surnames.length)];
                String randomName = names[random.nextInt(names.length)];
                String randomPatronymic = patronymics[random.nextInt(patronymics.length)];
                String randomFullName = (randomSurname + " " + randomName + " " + randomPatronymic);
                String usableStatus = null;
                accID = accID.add(BigInteger.ONE);

                String stringAccID = String.valueOf(accID);

                double randomBalance = 10000 + random.nextDouble(200000);
                int randomCode = 100000 + random.nextInt(900000);

                if (i < 5)
                    usableStatus = status[0];
                if (i >= 5 && i < 10)
                    usableStatus = status[1];
                if (i >= 10)
                    usableStatus = status[2];

                accounts.add(new Account(randomFullName, accID, randomBalance, usableStatus, randomCode));
                Clients.addAccount(accounts.get(i));

                db.addAccountDB(stringAccID, randomFullName, randomBalance, usableStatus, randomCode,  LocalDate.now().minusMonths(1)); // добавление в таблицы
            }

        System.out.println("\nВсе созданные аккаунты:");
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            System.out.println(acc.getFullName() + ", ID: " + acc.getAccID() + ", Баланс: " + String.format("%.2f", acc.getBalance()) + ", Статус: " + acc.getStatus() + ", Код для входа: " + acc.getCode());
        }
        Authorization.authorization(accounts);
    }
}
