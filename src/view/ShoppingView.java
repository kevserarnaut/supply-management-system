package view;
import javax.swing.*;
import java.awt.*;
import model.CustomerManager;
import model.ProductManager;
import model.Customer;
import model.Product;

public class ShoppingView extends JFrame {
    private JComboBox<String> productComboBox;
    private JLabel stockLabel;
    private JTextField amountField;
    private JButton buyButton;
    private JButton backButton;
    private CustomerManager customerManager;
    private ProductManager productManager;
    private String customerName;

    public ShoppingView(String customerName, CustomerManager customerManager, ProductManager productManager) {
        this.customerManager = customerManager;
        this.productManager = productManager;
        this.customerName = customerName;
        setTitle("Shopping - Customer: " + customerName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        int y = 50;
        int labelWidth = 120;
        int valueWidth = 200;
        int height = 25;

        JLabel productLabel = new JLabel("Select Product:");
        productLabel.setBounds(40, y, labelWidth, height);
        add(productLabel);
        productComboBox = new JComboBox<>();
        if (productManager != null) {
            for (Product p : productManager.getProducts()) {
                productComboBox.addItem(p.getName());
            }
        }
        productComboBox.setBounds(170, y, valueWidth, height);
        add(productComboBox);
        y += 40;

        JLabel stockStatic = new JLabel("Stock Amount:");
        stockStatic.setBounds(40, y, labelWidth, height);
        add(stockStatic);
        stockLabel = new JLabel();
        stockLabel.setBounds(170, y, valueWidth, height);
        add(stockLabel);
        y += 40;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, y, labelWidth, height);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(170, y, valueWidth, height);
        add(amountField);
        y += 40;

        buyButton = new JButton("Buy");
        buyButton.setBounds(70, y, 250, 30);
        add(buyButton);
        y += 40;

        backButton = new JButton("Back");
        backButton.setBounds(290, 10, 80, 28);
        add(backButton);

        productComboBox.addActionListener(e -> updateStock());
        updateStock();

        buyButton.addActionListener(e -> {
            String selectedProduct = (String) productComboBox.getSelectedItem();
            String amountStr = amountField.getText().trim();
            if (selectedProduct == null) return;
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= 0) throw new NumberFormatException();
                Product product = null;
                for (Product p : productManager.getProducts()) {
                    if (p.getName().equals(selectedProduct)) {
                        product = p;
                        break;
                    }
                }
                if (product == null) return;
                int stock = product.getAmount();
                if (amount > stock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Customer customer = customerManager.getCustomerByName(customerName);
                if (customer == null) return;
                double price = 10.0; // Sabit fiyat, isterseniz dinamik yapabilirsiniz
                double totalCost = amount * price;
                if (customer.getBalance() < totalCost) {
                    JOptionPane.showMessageDialog(this, "Not enough balance!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Satın alma işlemi
                product.setAmount(stock - amount);
                customer.setBalance(customer.getBalance() - totalCost);
                customer.addToInventory(selectedProduct, amount);
                updateStock();
                JOptionPane.showMessageDialog(this, "Purchase successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "You must enter a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(e -> dispose());
    }

    private void updateStock() {
        String selectedProduct = (String) productComboBox.getSelectedItem();
        if (selectedProduct == null) {
            stockLabel.setText("0");
            return;
        }
        for (Product p : productManager.getProducts()) {
            if (p.getName().equals(selectedProduct)) {
                stockLabel.setText(String.valueOf(p.getAmount()));
                return;
            }
        }
        stockLabel.setText("0");
    }
} 