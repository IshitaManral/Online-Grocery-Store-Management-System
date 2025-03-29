import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroceryStoreUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, priceField, quantityField;

    public GroceryStoreUI() {
        frame = new JFrame("Grocery Store Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table setup
        String[] columnNames = {"Product Name", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 3));

        nameField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Product");
        JButton sellButton = new JButton("Sell Product");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellProduct();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(sellButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addProduct() {
        String name = nameField.getText();
        String price = priceField.getText();
        String quantity = quantityField.getText();

        if (!name.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
            tableModel.addRow(new Object[]{name, price, quantity});
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sellProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int quantity = Integer.parseInt(table.getValueAt(selectedRow, 2).toString());
            if (quantity > 1) {
                table.setValueAt(quantity - 1, selectedRow, 2);
            } else {
                tableModel.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Select a product to sell", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GroceryStoreUI::new);
    }
}

