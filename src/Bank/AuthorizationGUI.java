package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

public class AuthorizationGUI {
    private JFrame frame;
    private JTextField accIDField;
    private JPasswordField codeField;
    private JLabel messageLabel;
    private List<Account> accounts;

    public AuthorizationGUI(List<Account> accounts) {
        this.accounts = accounts;
        initialize();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void initialize() {
        frame = new JFrame("Авторизация в банке");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(5, 1));

        JLabel accIDLabel = new JLabel("Введите номер счета:");
        accIDField = new JTextField();

        JLabel codeLabel = new JLabel("Введите пароль:");
        codeField = new JPasswordField();

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton loginButton = new JButton("Войти");
        loginButton.addActionListener(new LoginAction());

        frame.add(accIDLabel);
        frame.add(accIDField);
        frame.add(codeLabel);
        frame.add(codeField);
        frame.add(loginButton);
        frame.add(messageLabel);

        frame.setVisible(true);
    }

    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                BigInteger accID = new BigInteger(accIDField.getText());
                int code = Integer.parseInt(new String(codeField.getPassword()));

                for (Account acc : accounts) {
                    if (acc.getAccID().equals(accID) && acc.getCode() == code) {
                        JOptionPane.showMessageDialog(frame, "Авторизация успешна! Добро пожаловать, " + acc.getFullName());
                        frame.dispose();
                        new ProfileGUI(acc);
                        return;
                    }
                }
                messageLabel.setText("Неверный номер счета или пароль.");
            } catch (Exception ex) {
                messageLabel.setText("Ошибка ввода данных.");
            }
        }
    }
}