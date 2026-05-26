package view;
import javax.swing.*;
import java.awt.*;
import model.ProducerManager;
import model.Producer;
import model.DesignManager;
import model.Design;

public class CreateDesignView extends JFrame {
    private JTextField productNameField;
    private JTextField productCostField;
    private JTextField byproductNameField;
    private JTextField byproductAmountField;
    private JTextField byproductCostField;
    private JComboBox<String> inputComboBox;
    private JLabel selectedStockLabel;
    private JTextField inputAmountField;
    private JButton addInputButton;
    private JButton saveDesignButton;
    private JButton backButton;
    private ProducerManager producerManager;
    private DesignManager designManager;

    public CreateDesignView(ProducerManager producerManager, DesignManager designManager, String selectedStock) {
        this.producerManager = producerManager;
        this.designManager = designManager;
        setTitle("Create Design");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 500, 40);
        JLabel titleLabel = new JLabel("Create Design");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(10, 5, 250, 30);
        topPanel.add(titleLabel);
        backButton = new JButton("Back");
        backButton.setBounds(400, 5, 80, 28);
        topPanel.add(backButton);
        add(topPanel);

        int y = 60;
        int labelWidth = 140;
        int valueWidth = 200;
        int height = 25;

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setBounds(40, y, labelWidth, height);
        add(productNameLabel);
        productNameField = new JTextField();
        productNameField.setBounds(180, y, valueWidth, height);
        add(productNameField);
        y += 30;

        JLabel productCostLabel = new JLabel("Product Cost:");
        productCostLabel.setBounds(40, y, labelWidth, height);
        add(productCostLabel);
        productCostField = new JTextField();
        productCostField.setBounds(180, y, valueWidth, height);
        add(productCostField);
        y += 30;

        JLabel byproductNameLabel = new JLabel("Byproduct Name:");
        byproductNameLabel.setBounds(40, y, labelWidth, height);
        add(byproductNameLabel);
        byproductNameField = new JTextField();
        byproductNameField.setBounds(180, y, valueWidth, height);
        add(byproductNameField);
        y += 30;

        JLabel byproductAmountLabel = new JLabel("Byproduct Amount:");
        byproductAmountLabel.setBounds(40, y, labelWidth, height);
        add(byproductAmountLabel);
        byproductAmountField = new JTextField();
        byproductAmountField.setBounds(180, y, valueWidth, height);
        add(byproductAmountField);
        y += 30;

        JLabel byproductCostLabel = new JLabel("Byproduct Cost:");
        byproductCostLabel.setBounds(40, y, labelWidth, height);
        add(byproductCostLabel);
        byproductCostField = new JTextField();
        byproductCostField.setBounds(180, y, valueWidth, height);
        add(byproductCostField);
        y += 30;

        JLabel inputLabel = new JLabel("Input (RawMaterial):");
        inputLabel.setBounds(40, y, labelWidth, height);
        add(inputLabel);
        java.util.List<String> rawMaterials = new java.util.ArrayList<>();
        if (producerManager != null) {
            for (Producer p : producerManager.getProducers()) {
                rawMaterials.add(p.getName());
            }
        }
        inputComboBox = new JComboBox<>(rawMaterials.toArray(new String[0]));
        inputComboBox.setBounds(180, y, valueWidth, height);
        add(inputComboBox);
        y += 30;

        JLabel selectedStockStatic = new JLabel("Selected Stock:");
        selectedStockStatic.setBounds(40, y, labelWidth, height);
        add(selectedStockStatic);
        selectedStockLabel = new JLabel(selectedStock);
        selectedStockLabel.setBounds(180, y, valueWidth, height);
        add(selectedStockLabel);
        y += 30;

        JLabel inputAmountLabel = new JLabel("Input Amount:");
        inputAmountLabel.setBounds(40, y, labelWidth, height);
        add(inputAmountLabel);
        inputAmountField = new JTextField();
        inputAmountField.setBounds(180, y, valueWidth, height);
        add(inputAmountField);
        y += 30;

        addInputButton = new JButton("Add Input");
        addInputButton.setBounds(40, y, 150, 30);
        add(addInputButton);
        saveDesignButton = new JButton("Save Design");
        saveDesignButton.setBounds(180, y, 200, 30);
        add(saveDesignButton);

        addInputButton.addActionListener(e -> {
            String inputMaterial = (String) inputComboBox.getSelectedItem();
            String inputAmount = inputAmountField.getText().trim();
            if (inputMaterial == null || inputAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a raw material and enter an input amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int amount = Integer.parseInt(inputAmount);
                if (amount <= 0) throw new NumberFormatException();
                JOptionPane.showMessageDialog(this, "Input added: " + inputMaterial + " x " + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input amount must be a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveDesignButton.addActionListener(e -> {
            String productName = productNameField.getText().trim();
            String productCost = productCostField.getText().trim();
            String byproductName = byproductNameField.getText().trim();
            String byproductAmount = byproductAmountField.getText().trim();
            String byproductCost = byproductCostField.getText().trim();
            if (productName.isEmpty() || productCost.isEmpty() || byproductName.isEmpty() || byproductAmount.isEmpty() || byproductCost.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                double cost = Double.parseDouble(productCost);
                Integer.parseInt(byproductAmount);
                Double.parseDouble(byproductCost);
                if (designManager != null) {
                    designManager.addDesign(new Design(productName, cost));
                }
                JOptionPane.showMessageDialog(this, "Design saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Product cost and byproduct cost must be numbers, byproduct amount must be integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> dispose());
    }

    public JTextField getProductNameField() { return productNameField; }
    public JTextField getProductCostField() { return productCostField; }
    public JTextField getByproductNameField() { return byproductNameField; }
    public JTextField getByproductAmountField() { return byproductAmountField; }
    public JTextField getByproductCostField() { return byproductCostField; }
    public JComboBox<String> getInputComboBox() { return inputComboBox; }
    public JLabel getSelectedStockLabel() { return selectedStockLabel; }
    public JTextField getInputAmountField() { return inputAmountField; }
    public JButton getAddInputButton() { return addInputButton; }
    public JButton getSaveDesignButton() { return saveDesignButton; }
    public JButton getBackButton() { return backButton; }
} 