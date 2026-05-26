package view;
import javax.swing.*;
import java.awt.*;
import model.ProducerManager;
import model.DesignManager;
import model.ByproductManager;
import model.ProductManager;
import model.Byproduct;
import model.Product;

public class FactoryDetailView extends JFrame {
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JLabel capacityLabel;
    private JComboBox<String> rawMaterialCombo;
    private JLabel selectedStockLabel;
    private JTextField amountField;
    private JButton buyButton;
    private JButton createDesignButton;
    private JButton produceProductButton;
    private JButton destroyByproductsButton;
    private JButton viewInventoryButton;
    private JButton backButton;
    private ProducerManager producerManager;
    private DesignManager designManager;
    private ByproductManager byproductManager;
    private ProductManager productManager;

    public FactoryDetailView(String name, String balance, String capacity, ProducerManager producerManager, DesignManager designManager, ByproductManager byproductManager, ProductManager productManager, String selectedStock) {
        this.producerManager = producerManager;
        this.designManager = designManager;
        this.byproductManager = byproductManager;
        this.productManager = productManager;
        setTitle("Factory: " + name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 500, 40);
        JLabel titleLabel = new JLabel("Factory: " + name);
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

        JLabel nameStatic = new JLabel("Name:");
        nameStatic.setBounds(40, y, labelWidth, height);
        add(nameStatic);
        nameLabel = new JLabel(name);
        nameLabel.setBounds(180, y, valueWidth, height);
        add(nameLabel);
        y += 30;

        JLabel balanceStatic = new JLabel("Balance:");
        balanceStatic.setBounds(40, y, labelWidth, height);
        add(balanceStatic);
        balanceLabel = new JLabel(balance);
        balanceLabel.setBounds(180, y, valueWidth, height);
        add(balanceLabel);
        y += 30;

        JLabel capacityStatic = new JLabel("Capacity:");
        capacityStatic.setBounds(40, y, labelWidth, height);
        add(capacityStatic);
        capacityLabel = new JLabel(capacity);
        capacityLabel.setBounds(180, y, valueWidth, height);
        add(capacityLabel);
        y += 30;

        JLabel rawMatLabel = new JLabel("Select Raw Material:");
        rawMatLabel.setBounds(40, y, labelWidth, height);
        add(rawMatLabel);
        java.util.List<String> rawMaterials = new java.util.ArrayList<>();
        if (producerManager != null) {
            for (model.Producer p : producerManager.getProducers()) {
                rawMaterials.add(p.getName());
            }
        }
        rawMaterialCombo = new JComboBox<>(rawMaterials.toArray(new String[0]));
        rawMaterialCombo.setBounds(180, y, valueWidth, height);
        add(rawMaterialCombo);
        y += 30;

        JLabel stockLabel = new JLabel("Selected Stock:");
        stockLabel.setBounds(40, y, labelWidth, height);
        add(stockLabel);
        selectedStockLabel = new JLabel(selectedStock);
        selectedStockLabel.setBounds(180, y, valueWidth, height);
        add(selectedStockLabel);
        y += 30;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, y, labelWidth, height);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(180, y, valueWidth, height);
        add(amountField);
        y += 40;

        buyButton = new JButton("Buy");
        buyButton.setBounds(100, y, 300, 30);
        add(buyButton);
        y += 40;

        createDesignButton = new JButton("Create Design");
        createDesignButton.setBounds(100, y, 300, 30);
        add(createDesignButton);
        y += 40;

        produceProductButton = new JButton("Produce Product");
        produceProductButton.setBounds(100, y, 300, 30);
        add(produceProductButton);
        y += 40;

        destroyByproductsButton = new JButton("Destroy Byproducts");
        destroyByproductsButton.setBounds(100, y, 300, 30);
        add(destroyByproductsButton);
        y += 40;

        viewInventoryButton = new JButton("View Inventory");
        viewInventoryButton.setBounds(100, y, 300, 30);
        add(viewInventoryButton);

        backButton.addActionListener(e -> dispose());

        buyButton.addActionListener(e -> {
            String amountStr = amountField.getText().trim();
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= 0) throw new NumberFormatException();
                double currentStock = Double.parseDouble(selectedStockLabel.getText());
                if (amount > currentStock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                selectedStockLabel.setText(String.valueOf(currentStock - amount));
                JOptionPane.showMessageDialog(this, "Purchased successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "You must enter a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        createDesignButton.addActionListener(e -> {
            CreateDesignView designView = new CreateDesignView(producerManager, designManager, "120");
            designView.setVisible(true);
        });

        produceProductButton.addActionListener(e -> {
            java.util.List<String> designNames = new java.util.ArrayList<>();
            if (designManager != null) {
                for (var d : designManager.getDesigns()) {
                    designNames.add(d.getName());
                }
            }
            ProduceProductView produceView = new ProduceProductView(
                designNames.toArray(new String[0]),
                productManager,
                byproductManager
            );
            produceView.setVisible(true);
        });

        destroyByproductsButton.addActionListener(e -> {
            java.util.List<String> byproductNames = new java.util.ArrayList<>();
            if (byproductManager != null) {
                for (Byproduct b : byproductManager.getByproducts()) {
                    byproductNames.add(b.getName());
                }
            }
            DestroyByproductView destroyView = new DestroyByproductView(byproductNames.toArray(new String[0]), byproductManager);
            destroyView.setVisible(true);
        });

        viewInventoryButton.addActionListener(e -> {
            java.util.List<String> productNames = new java.util.ArrayList<>();
            java.util.List<String> byproductNames = new java.util.ArrayList<>();
            if (productManager != null) {
                for (Product p : productManager.getProducts()) {
                    productNames.add(p.getName() + ": " + p.getAmount());
                }
            }
            if (byproductManager != null) {
                for (Byproduct b : byproductManager.getByproducts()) {
                    byproductNames.add(b.getName() + ": " + b.getAmount());
                }
            }
            ViewInventoryView inventoryView = new ViewInventoryView(nameLabel.getText(), productNames.toArray(new String[0]), byproductNames.toArray(new String[0]));
            inventoryView.setVisible(true);
        });
    }

    public JLabel getNameLabel() { return nameLabel; }
    public JLabel getBalanceLabel() { return balanceLabel; }
    public JLabel getCapacityLabel() { return capacityLabel; }
    public JComboBox<String> getRawMaterialCombo() { return rawMaterialCombo; }
    public JLabel getSelectedStockLabel() { return selectedStockLabel; }
    public JTextField getAmountField() { return amountField; }
    public JButton getBuyButton() { return buyButton; }
    public JButton getCreateDesignButton() { return createDesignButton; }
    public JButton getProduceProductButton() { return produceProductButton; }
    public JButton getDestroyByproductsButton() { return destroyByproductsButton; }
    public JButton getViewInventoryButton() { return viewInventoryButton; }
    public JButton getBackButton() { return backButton; }
} 