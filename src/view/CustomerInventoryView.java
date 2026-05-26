package view;
import javax.swing.*;
import java.awt.*;

public class CustomerInventoryView extends JFrame {
    private JButton backButton;

    public CustomerInventoryView(String customerName, double balance, String[] inventory) {
        setTitle("Inventory - Customer: " + customerName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Top panel with title and back button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBounds(0, 0, 400, 40);
        topPanel.setBackground(new Color(220, 220, 220));
        JLabel titleLabel = new JLabel("Inventory - Customer: " + customerName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(titleLabel, BorderLayout.WEST);
        backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        mainPanel.add(topPanel);

        // Name and balance labels
        JLabel nameLabel = new JLabel("Name: " + customerName);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        nameLabel.setBounds(15, 50, 200, 20);
        mainPanel.add(nameLabel);
        JLabel balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        balanceLabel.setBounds(15, 70, 200, 20);
        mainPanel.add(balanceLabel);

        JLabel inventoryLabel = new JLabel("Inventory:");
        inventoryLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        inventoryLabel.setBounds(15, 90, 200, 20);
        mainPanel.add(inventoryLabel);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.setBackground(new Color(240, 240, 240));
        inventoryPanel.setBounds(15, 110, 360, 70);
        for (String item : inventory) {
            JLabel itemLabel = new JLabel("- " + item);
            itemLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            inventoryPanel.add(itemLabel);
        }
        mainPanel.add(inventoryPanel);

        topPanel.setLayout(null);
        titleLabel.setBounds(10, 10, 250, 20);
        backButton.setBounds(300, 7, 80, 25);
        topPanel.add(titleLabel);
        topPanel.add(backButton);
        topPanel.setBounds(0, 0, 400, 40);
        mainPanel.add(topPanel);

        backButton.addActionListener(e -> dispose());
    }

    public JButton getBackButton() { return backButton; }
} 