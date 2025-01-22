package Bank;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Clients {
    static Map<BigInteger, Account> accounts = new HashMap<>();
    public static void addAccount(Account account) {
        accounts.put(account.getAccID(), account);
    }

    public static double useCashback(BigInteger accID, double value) {
        Account account = accounts.get(accID);
        if (account.getStatus().equals("Базовый") && value >= 10000) {
            System.out.println("Ваш кэшбэк составит: " + String.format("%.2f", value * 0.01));
            return value - (value * 0.01);
        }

        if (account.getStatus().equals("Премиум") && value >= 10000)
        {
            System.out.println("Ваш кэшбэк составит: " + String.format("%.2f", value * 0.05));
            return value - (value * 0.05);
        }

        if (account.getStatus().equals("ВИП") && value >= 100000) {
            System.out.println("Ваш кэшбэк составит: " + String.format("%.2f", value * 0.10));
            return value - (value * 0.10);
        }
        else if(account.getStatus().equals("ВИП") && value >= 10000) {
            System.out.println("Ваш кэшбэк составит: " + String.format("%.2f", value * 0.05));
            return value - (value * 0.05);
        }
        else if(account.getStatus().equals("ВИП") && value < 10000) {
            System.out.println("Ваш кэшбэк составит: " + String.format("%.2f", value * 0.01));
            return value - (value * 0.01);
        }
        return value;
    }

    public static Account getAccount(BigInteger accID) {
        return accounts.get(accID);
    }
}
