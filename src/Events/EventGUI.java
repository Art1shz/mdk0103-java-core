package Events;

import Events.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventGUI extends JFrame {
    private final DB db;
    private final JTable eventTable;
    private final DefaultTableModel tableModel;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EventGUI() {
        db = new DB();
        setTitle("Управление событиями");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Заголовок", "Текст", "Дата"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        eventTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(eventTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");
        JButton refreshButton = new JButton("Обновить");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> showAddEventDialog());
        editButton.addActionListener(e -> showEditEventDialog());
        deleteButton.addActionListener(e -> deleteSelectedEvent());
        refreshButton.addActionListener(e -> refreshEventList());

        refreshEventList();
    }

    private void refreshEventList() {
        try {
            tableModel.setRowCount(0);
            List<Event> events = db.getAllEvents();
            for (Event event : events) {
                tableModel.addRow(new Object[]{
                    event.getId(),
                    event.getTitle(),
                    event.getText(),
                    event.getDate().format(DATE_FORMATTER)
                });
            }
        } catch (Exception e) {
            showError("Ошибка при загрузке событий: " + e.getMessage());
        }
    }

    private void showAddEventDialog() {
        JDialog dialog = new JDialog(this, "Добавить событие", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField titleField = new JTextField();
        JTextArea textArea = new JTextArea(3, 20);
        JTextField dateField = new JTextField();

        panel.add(new JLabel("Заголовок:"));
        panel.add(titleField);
        panel.add(new JLabel("Текст:"));
        panel.add(new JScrollPane(textArea));
        panel.add(new JLabel("Дата (yyyy-MM-dd HH:mm):"));
        panel.add(dateField);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String text = textArea.getText();
                LocalDateTime date = LocalDateTime.parse(dateField.getText(), DATE_FORMATTER);

                if (title.isEmpty() || text.isEmpty()) {
                    showError("Заголовок и текст не могут быть пустыми");
                    return;
                }

                Event event = new Event(title, text, date);
                db.addEvent(event);
                refreshEventList();
                dialog.dispose();
            } catch (Exception ex) {
                showError("Ошибка при добавлении события: " + ex.getMessage());
            }
        });

        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showEditEventDialog() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            showError("Пожалуйста, выберите событие для редактирования");
            return;
        }

        int eventId = (int) tableModel.getValueAt(selectedRow, 0);
        try {
            Event event = db.getEventById(eventId);
            if (event == null) {
                showError("Событие не найдено");
                return;
            }

            JDialog dialog = new JDialog(this, "Редактировать событие", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            JTextField titleField = new JTextField(event.getTitle());
            JTextArea textArea = new JTextArea(event.getText(), 3, 20);
            JTextField dateField = new JTextField(event.getDate().format(DATE_FORMATTER));

            panel.add(new JLabel("Заголовок:"));
            panel.add(titleField);
            panel.add(new JLabel("Текст:"));
            panel.add(new JScrollPane(textArea));
            panel.add(new JLabel("Дата (yyyy-MM-dd HH:mm):"));
            panel.add(dateField);

            JButton saveButton = new JButton("Сохранить");
            saveButton.addActionListener(e -> {
                try {
                    String title = titleField.getText();
                    String text = textArea.getText();
                    LocalDateTime date = LocalDateTime.parse(dateField.getText(), DATE_FORMATTER);

                    if (title.isEmpty() || text.isEmpty()) {
                        showError("Заголовок и текст не могут быть пустыми");
                        return;
                    }

                    event.setTitle(title);
                    event.setText(text);
                    event.setDate(date);
                    db.updateEvent(event);
                    refreshEventList();
                    dialog.dispose();
                } catch (Exception ex) {
                    showError("Ошибка при обновлении события: " + ex.getMessage());
                }
            });

            panel.add(saveButton);
            dialog.add(panel);
            dialog.setVisible(true);
        } catch (Exception e) {
            showError("Ошибка при загрузке события: " + e.getMessage());
        }
    }

    private void deleteSelectedEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            showError("Пожалуйста, выберите событие для удаления");
            return;
        }

        int eventId = (int) tableModel.getValueAt(selectedRow, 0);
        String eventTitle = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Вы уверены, что хотите удалить событие '" + eventTitle + "'?",
            "Подтверждение удаления",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                db.deleteEvent(eventId);
                refreshEventList();
            } catch (Exception e) {
                showError("Ошибка при удалении события: " + e.getMessage());
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            DB db = new DB();
            db.isConnection();
            db.createTables();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Ошибка при инициализации базы данных: " + e.getMessage(),
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> {
            EventGUI gui = new EventGUI();
            gui.setVisible(true);
        });
    }
}