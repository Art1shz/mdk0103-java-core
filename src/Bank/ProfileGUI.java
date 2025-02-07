package Bank;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.sql.SQLException;

class ProfileGUI {
    private JFrame frame;
    private Account account;
    private JLabel balanceLabel;

    public ProfileGUI(Account account) throws SQLException, ClassNotFoundException
    {
        this.account = account;
        applyMonthlyFee();
        initialize();
    }

    private void applyMonthlyFee() throws SQLException, ClassNotFoundException {
        if (account.applyMonthlyFee()) {
            JOptionPane.showMessageDialog(frame, "Ежемесячная плата в размере 100 рублей была списана с вашего счета. Текущий баланс: " + account.getBalance() + " руб.");
        }
    }

    private void initialize() {
        frame = new JFrame("Банковское меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JLabel welcomeLabel = new JLabel("Добро пожаловать, " + account.getFullName(), SwingConstants.CENTER);
        balanceLabel = new JLabel("Баланс: " + String.format("%.2f", account.getBalance()) + " руб.", SwingConstants.CENTER);
        JLabel statusLabel = new JLabel("Статус: " + account.getStatus(), SwingConstants.CENTER);

        JButton transferButton = new JButton("Перевести деньги");
        JButton payButton = new JButton("Оплатить услугу");
        JButton exitButton = new JButton("Выход");

        transferButton.addActionListener(e -> performTransfer());
        payButton.addActionListener(e -> performPayment());
        exitButton.addActionListener(e -> frame.dispose());

        frame.add(welcomeLabel);
        frame.add(balanceLabel);
        frame.add(statusLabel);
        frame.add(transferButton);
        frame.add(payButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    private void performTransfer() {
        Database db = new Database();

        String transferIDStr = JOptionPane.showInputDialog(frame, "Введите номер счёта получателя:");
        if (transferIDStr == null) return;

        try {
            Account recipient = db.getAccountById(transferIDStr);
            if (recipient == null) {
                JOptionPane.showMessageDialog(frame, "Получатель не найден.");
                return;
            }

            String amountStr = JOptionPane.showInputDialog(frame, "Введите сумму перевода:");
            if (amountStr == null) return;

            double amount = Double.parseDouble(amountStr);
            if (amount > account.getBalance()) {
                JOptionPane.showMessageDialog(frame, "Недостаточно средств.");
                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(frame,
                    "Перевести " + amount + " руб. на счёт " + recipient.getFullName() + "?",
                    "Подтверждение", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                System.out.println("Добавляем транзакцию: " + account.getAccID() + " -> " + recipient.getAccID() + " на " + amount);
                db.addTransactionDB(account.getAccID().toString(), recipient.getAccID().toString(), amount);

                double senderBalance = account.getBalance() - amount;
                double recipientBalance = recipient.getBalance() + amount;

                account.setBalance(senderBalance);
                recipient.setBalance(recipientBalance);
                updateBalanceLabel();

                JOptionPane.showMessageDialog(frame, "Перевод успешно выполнен.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Ошибка ввода.");
        }
    }


    private void performPayment() {
        Database db = new Database();

        String amountStr = JOptionPane.showInputDialog(frame, "Введите сумму платежа:");
        if (amountStr == null) return;

        try {
            double amount = Double.parseDouble(amountStr);

            if (amount > account.getBalance()) {
                JOptionPane.showMessageDialog(frame, "Недостаточно средств.");
                return;
            }

            if (Account.isFirstPaymentForServices()) {
                System.out.println("Так как это ваша первая оплата услуг, то вам начислен приветственный бонус в виде 1000 рублей!");

                db.updateAccountBalanceDB(account.getAccID().toString(), account.getBalance() + 1000);

                account.addToBalance(1000);
            }

            double finalAmount = Clients.useCashback(account.getAccID(), amount);

            double newBalance = account.getBalance() - finalAmount;
            db.updateAccountBalanceDB(account.getAccID().toString(), newBalance);

            account.setBalance(newBalance);

            updateBalanceLabel();

            JOptionPane.showMessageDialog(frame, "Оплата успешно выполнена.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Произошла ошибка: " + e.getMessage());
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Баланс: " + String.format("%.2f", account.getBalance()) + " руб.");
    }
}