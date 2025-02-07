package Bank;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Account {
    private String fullName;
    private BigInteger accID;
    private double balance;
    private String status;
    private static int paymentCount;
    private int code;
    LocalDate lastMonthlyFeeDate;

    public Account(String fullName, BigInteger accID, double balance, String status, int code) {
        this.fullName = fullName;
        this.accID = accID;
        this.balance = balance;
        this.status = status;
        this.paymentCount = 0;
        this.code = code;
        this.lastMonthlyFeeDate = LocalDate.now().minusMonths(1);
    }

    public void addToBalance(double amount) {
        this.balance += amount;
    }

    public void subtractFromBalance(double amount) {
        this.balance -= amount;
    }


    public String getFullName()
    {
        return fullName;
    }

    public BigInteger getAccID() {
        return accID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public int getCode()
    {
        return code;
    }

    public int getPaymentCount()
    {
        return paymentCount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "fullName='" + fullName + '\'' +
                ", accID=" + accID +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }

    public void addToPaymentCount() {
        this.paymentCount++;
    }

    public static boolean isFirstPaymentForServices() {
        return paymentCount == 0;
    }

    public boolean applyMonthlyFee() throws SQLException, ClassNotFoundException
    {
        Database db = new Database();

        if (status.equals("Базовый")) {
            LocalDate currentDate = LocalDate.now();
            if (lastMonthlyFeeDate.plusMonths(1).isBefore(currentDate) || lastMonthlyFeeDate.plusMonths(1).isEqual(currentDate)) {

                db.updateAccountBalanceDB(this.accID.toString(),this.getBalance() - 100); // вычет платы за ежемесячное обслуживание дб
                db.updateLastMonthlyFeeDataDB(this.accID.toString());

                balance -= 100;

                lastMonthlyFeeDate = currentDate;
                System.out.println("Ежемесячная плата в размере 100 рублей списана. Текущий баланс: " + balance + " руб.");
                return true;
            }
        }
        return false;
    }



    public void paymentForServices() throws SQLException, ClassNotFoundException
    {
        Database db = new Database();

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите сумму: ");
        double value = scan.nextDouble();

        value = Clients.useCashback(getAccID(), value);

        if (this.balance < value) {
            System.out.println("Недостаточно средств для выполнения перевода.");
            return;
        }

        if (isFirstPaymentForServices()) {
            System.out.println("Так как это ваша первая оплата услуг, то вам начислен приветственный бонус в виде 1000 рублей!");

            db.updateAccountBalanceDB(this.accID.toString(),this.getBalance() + value); // добавление приветсвенного бонуса на счет дб

            addToBalance(1000);
        }

        addToPaymentCount();

        subtractFromBalance(value);
    }


    public void transferToAccount() throws SQLException, ClassNotFoundException
    {
        Database db = new Database();

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите номер счёта получателя: ");
        BigInteger transferID = scan.nextBigInteger();

        Account recipient = Clients.getAccount(transferID);

        if (recipient == null) {
            System.out.println("Получатель с указанным номером счёта не найден.");
            return;
        }

        System.out.println("Введите сумму перевода: ");
        double value = scan.nextDouble();

        if (value > this.getBalance()) {
            System.out.println("Недостаточно средств на счёте.");
            return;
        }

        System.out.println("\nВы собираетесь перевести " + String.format("%.2f", value) + " руб. на счёт " + recipient.getFullName() +
                " (ID: " + recipient.getAccID() + ").");
        System.out.println("Подтвердите перевод (введите 'да' для подтверждения или 'нет' для отмены): ");
        String confirmation = scan.next();

        if (confirmation.equals("да")) {
            db.addTransactionDB(this.accID.toString(), recipient.getAccID().toString(), value); // создание транзакции дб

            this.setBalance(this.getBalance() - value);
            recipient.setBalance(recipient.getBalance() + value);

            System.out.println("Перевод успешно выполнен");
        } else {
            System.out.println("Перевод отменён.");
        }
    }
}
