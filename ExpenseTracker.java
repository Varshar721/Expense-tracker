import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExpenseTracker extends JFrame {

    private ArrayList<Expense> expenses;
    private JTextField descriptionField;
    private JTextField amountField;
    private JComboBox<String> categoryComboBox;
    private JTable expenseTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private double totalAmount;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        totalAmount = 0.0;

        setTitle("Expense Tracker");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));

        add(createInputPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createTotalPanel(), BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel descLabel = new JLabel("Description:");
        descriptionField = new JTextField(20);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = {"Food", "Transportation", "Entertainment", "Utilities", "Healthcare", "Other"};
        categoryComboBox = new JComboBox<>(categories);

        JButton addButton = new JButton("Add Expense");
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new AddExpenseListener());

        inputPanel.add(descLabel);
        inputPanel.add(amountLabel);
        inputPanel.add(categoryLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(amountField);
        inputPanel.add(categoryComboBox);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        return topPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        String[] columnNames = {"Description", "Amount", "Category"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        expenseTable = new JTable(tableModel);
        expenseTable.setRowHeight(25);
        expenseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        expenseTable.getTableHeader().setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(expenseTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createTotalPanel() {
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        totalLabel = new JLabel("Total: ₹0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(0, 100, 0));

        totalPanel.add(totalLabel);

        return totalPanel;
    }

    private class AddExpenseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addExpense();
        }
    }

    private void addExpense() {
        String description = descriptionField.getText().trim();
        String amountText = amountField.getText().trim();
        String category = (String) categoryComboBox.getSelectedItem();

        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a description.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a positive amount.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid numeric amount.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Expense expense = new Expense(description, amount, category);
        expenses.add(expense);

        Object[] rowData = {description, String.format("₹%.2f", amount), category};
        tableModel.addRow(rowData);

        totalAmount += amount;
        updateTotalLabel();

        clearInputFields();
    }

    private void updateTotalLabel() {
        totalLabel.setText(String.format("Total: ₹%.2f", totalAmount));
    }

    private void clearInputFields() {
        descriptionField.setText("");
        amountField.setText("");
        categoryComboBox.setSelectedIndex(0);
        descriptionField.requestFocus();
    }

    private class Expense {
        private String description;
        private double amount;
        private String category;

        public Expense(String description, double amount, String category) {
            this.description = description;
            this.amount = amount;
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseTracker();
            }
        });
    }
}
