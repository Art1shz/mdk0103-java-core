package Bank;

import java.util.Random;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args)
    {
        Random random = new Random();

        List<Account> accounts = new ArrayList<>();

        String[] surnames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов", "Попов", "Новиков", "Федоров", "Морозов", "Романов"};
        String[] names = {"Иван", "Петр", "Алексей", "Максим", "Дмитрий", "Сергей", "Олег", "Владимир", "Андрей", "Роман"};
        String[] patronymics = {"Иванович", "Петрович", "Алексеевич", "Максимович", "Дмитриевич", "Сергеевич", "Олегович", "Владимирович", "Андреевич", "Романович"};
        BigInteger accID = new BigInteger("10000000000000000000");

            for (int i = 0; i < 15; i ++){
                String randomSurname = surnames[random.nextInt(surnames.length)];
                String randomName = names[random.nextInt(names.length)];
                String randomPatronymic = patronymics[random.nextInt(patronymics.length)];
                String randomFullName = (randomSurname + " " + randomName + " " + randomPatronymic);
                accID = accID.add(BigInteger.ONE);
                double randomBalance = 10000 + random.nextDouble(200000);
                int randomCode = 100000 + random.nextInt(900000);

                if (i < 5) accounts.add(new Account(randomFullName, accID, randomBalance, "Базовый", randomCode));
                if (i >= 5 && i < 10) accounts.add(new Account(randomFullName, accID, randomBalance, "Премиум", randomCode));
                if (i >= 10) accounts.add(new Account(randomFullName, accID, randomBalance, "ВИП", randomCode));
                Clients.addAccount(accounts.get(i));
            }

        System.out.println("\nВсе созданные аккаунты:");
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            System.out.println(acc.getFullName() + ", ID: " + acc.getAccID() + ", Баланс: " + String.format("%.2f", acc.getBalance()) + ", Статус: " + acc.getStatus() + ", Код для входа: " + acc.getCode());
        }
        PersonalAccount.authorization(accounts);
    }
}
