package view;
import javax.swing.*;
import java.awt.*;
import model.CustomerManager;
import model.Customer;
import model.ProductManager;

public class CustomerDetailView extends JFrame {
    private JButton shopButton;
    private JButton inventoryButton;
    private JButton backButton;
    private CustomerManager customerManager;
    private ProductManager productManager;
    private String customerName;
    private double balance;

    public CustomerDetailView(String customerName, double balance, CustomerManager customerManager, ProductManager productManager) {
        this.customerManager = customerManager;
        this.productManager = productManager;
        this.customerName = customerName;
        this.balance = balance;
        setTitle("Customer: " + customerName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(150, 180, 210));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Top panel with title and back button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBounds(0, 0, 350, 40);
        topPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Customer: " + customerName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(titleLabel, BorderLayout.WEST);
        backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        mainPanel.add(topPanel);

        // Name and balance labels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameLabel.setBounds(40, 60, 60, 25);
        mainPanel.add(nameLabel);
        JLabel nameValue = new JLabel(customerName);
        nameValue.setFont(new Font("Arial", Font.PLAIN, 13));
        nameValue.setBounds(120, 60, 150, 25);
        mainPanel.add(nameValue);

        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 13));
        balanceLabel.setBounds(40, 90, 70, 25);
        mainPanel.add(balanceLabel);
        double realBalance = balance;
        Customer customer = customerManager.getCustomerByName(customerName);
        if (customer != null) realBalance = customer.getBalance();
        JLabel balanceValue = new JLabel(String.valueOf(realBalance));
        balanceValue.setFont(new Font("Arial", Font.PLAIN, 13));
        balanceValue.setBounds(120, 90, 150, 25);
        mainPanel.add(balanceValue);

        // Shop button
        shopButton = new JButton("Shop");
        shopButton.setBounds(90, 130, 150, 30);
        mainPanel.add(shopButton);

        // Inventory button
        inventoryButton = new JButton("View Inventory");
        inventoryButton.setBounds(90, 170, 150, 30);
        mainPanel.add(inventoryButton);

        backButton.addActionListener(e -> dispose());
        inventoryButton.addActionListener(e -> {
            Customer c = customerManager.getCustomerByName(customerName);
            double currentBalance = c != null ? c.getBalance() : balance;
            String[] inventory = c != null ? c.getInventory().entrySet().stream().map(e2 -> e2.getKey() + ": " + e2.getValue()).toArray(String[]::new) : new String[]{};
            CustomerInventoryView inventoryView = new CustomerInventoryView(customerName, currentBalance, inventory);
            inventoryView.setVisible(true);
        });
        shopButton.addActionListener(e -> {
            ShoppingView shoppingView = new ShoppingView(customerName, customerManager, productManager);
            shoppingView.setVisible(true);
        });
    }

    public JButton getShopButton() {
        return shopButton; }
    public JButton getInventoryButton() {
        return inventoryButton; }
    public JButton getBackButton() {
        return backButton; }
} 