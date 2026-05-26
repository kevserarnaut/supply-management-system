package view;
import javax.swing.*;
import java.awt.*;
import model.ProductManager;
import model.Product;

public class MarketDetailView extends JFrame {
    private JLabel balanceLabel;
    private JComboBox<String> productComboBox;
    private JLabel stockLabel;
    private JTextField amountField;
    private JButton buyButton;
    private JTextField priceField;
    private JButton updatePriceButton;
    private JButton backButton;
    private ProductManager productManager;
    private double balance;
    // Basit fiyat yönetimi için bir map (ürün adı -> fiyat)
    private java.util.Map<String, Double> priceMap = new java.util.HashMap<>();
    private java.util.Map<String, Integer> stockMap = new java.util.HashMap<>();

    public MarketDetailView(String marketName, double balance, ProductManager productManager) {
        this.productManager = productManager;
        this.balance = balance;
        setTitle("Market: " + marketName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 500, 40);
        JLabel titleLabel = new JLabel("Market: " + marketName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(10, 5, 250, 30);
        topPanel.add(titleLabel);
        backButton = new JButton("Back");
        backButton.setBounds(400, 5, 80, 28);
        topPanel.add(backButton);
        add(topPanel);

        int y = 60;
        int labelWidth = 120;
        int valueWidth = 200;
        int height = 25;

        JLabel balanceStatic = new JLabel("Balance:");
        balanceStatic.setBounds(60, y, labelWidth, height);
        add(balanceStatic);
        balanceLabel = new JLabel(String.valueOf(balance));
        balanceLabel.setBounds(180, y, valueWidth, height);
        add(balanceLabel);
        y += 30;

        JLabel productLabel = new JLabel("Select Product:");
        productLabel.setBounds(60, y, labelWidth, height);
        add(productLabel);
        productComboBox = new JComboBox<>();
        if (productManager != null) {
            for (Product p : productManager.getProducts()) {
                productComboBox.addItem(p.getName());
                stockMap.put(p.getName(), p.getAmount());
                priceMap.put(p.getName(), 10.0); // Varsayılan fiyat
            }
        }
        productComboBox.setBounds(180, y, valueWidth, height);
        add(productComboBox);
        y += 30;

        JLabel stockStatic = new JLabel("Stock Quantity:");
        stockStatic.setBounds(60, y, labelWidth, height);
        add(stockStatic);
        stockLabel = new JLabel();
        stockLabel.setBounds(180, y, valueWidth, height);
        add(stockLabel);
        y += 30;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(60, y, labelWidth, height);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(180, y, valueWidth, height);
        add(amountField);
        y += 40;

        buyButton = new JButton("Buy");
        buyButton.setBounds(100, y, 300, 30);
        add(buyButton);
        y += 40;

        JLabel priceLabel = new JLabel("Price (for stock):");
        priceLabel.setBounds(60, y, labelWidth, height);
        add(priceLabel);
        priceField = new JTextField();
        priceField.setBounds(180, y, valueWidth, height);
        add(priceField);
        y += 40;

        updatePriceButton = new JButton("Update Price");
        updatePriceButton.setBounds(100, y, 300, 30);
        add(updatePriceButton);

        // Ürün seçilince stok ve fiyatı güncelle
        productComboBox.addActionListener(e -> updateStockAndPrice());
        updateStockAndPrice();

        backButton.addActionListener(e -> dispose());
        buyButton.addActionListener(e -> {
            String amountStr = amountField.getText().trim();
            String selectedProduct = (String) productComboBox.getSelectedItem();
            if (selectedProduct == null) return;
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= 0) throw new NumberFormatException();
                int stock = stockMap.getOrDefault(selectedProduct, 0);
                double price = priceMap.getOrDefault(selectedProduct, 10.0);
                if (amount > stock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double totalCost = amount * price;
                if (totalCost > this.balance) {
                    JOptionPane.showMessageDialog(this, "Not enough balance!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Stok ve balance güncelle
                stockMap.put(selectedProduct, stock - amount);
                this.balance -= totalCost;
                balanceLabel.setText(String.valueOf(this.balance));
                updateStockAndPrice();
                JOptionPane.showMessageDialog(this, "Purchased successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "You must enter a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        updatePriceButton.addActionListener(e -> {
            // Custom dialog for product selection and price
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
            panel.add(iconLabel, gbc);
            gbc.gridheight = 1;

            gbc.gridx = 1; gbc.gridy = 0;
            panel.add(new JLabel("Select product to update price:"), gbc);
            gbc.gridy = 1;
            JComboBox<String> dialogCombo = new JComboBox<>();
            for (int i = 0; i < productComboBox.getItemCount(); i++) {
                dialogCombo.addItem(productComboBox.getItemAt(i));
            }
            panel.add(dialogCombo, gbc);

            gbc.gridx = 1; gbc.gridy = 2;
            panel.add(new JLabel("New Price:"), gbc);
            gbc.gridy = 3;
            JTextField dialogPriceField = new JTextField(10);
            panel.add(dialogPriceField, gbc);

            int result = JOptionPane.showOptionDialog(
                this,
                panel,
                "Select Product",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Cancel", "OK"},
                "OK"
            );
            if (result == 1) { // OK
                String priceStr = dialogPriceField.getText().trim();
                try {
                    double price = Double.parseDouble(priceStr);
                    if (price <= 0) throw new NumberFormatException();
                    String product = (String) dialogCombo.getSelectedItem();
                    priceMap.put(product, price);
                    updateStockAndPrice();
                    JOptionPane.showMessageDialog(this, "Price for '" + product + "' updated to " + price + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "You must enter a valid positive price!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateStockAndPrice() {
        String selectedProduct = (String) productComboBox.getSelectedItem();
        if (selectedProduct == null) {
            stockLabel.setText("0");
            priceField.setText("");
            return;
        }
        stockLabel.setText(String.valueOf(stockMap.getOrDefault(selectedProduct, 0)));
        priceField.setText(String.valueOf(priceMap.getOrDefault(selectedProduct, 10.0)));
    }

    public JLabel getBalanceLabel() { return balanceLabel; }
    public JComboBox<String> getProductComboBox() { return productComboBox; }
    public JLabel getStockLabel() { return stockLabel; }
    public JTextField getAmountField() { return amountField; }
    public JButton getBuyButton() { return buyButton; }
    public JTextField getPriceField() { return priceField; }
    public JButton getUpdatePriceButton() { return updatePriceButton; }
    public JButton getBackButton() { return backButton; }
} 