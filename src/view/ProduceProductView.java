package view;
import javax.swing.*;
import java.awt.*;
import model.ProductManager;
import model.ByproductManager;
import model.Product;
import model.Byproduct;

public class ProduceProductView extends JFrame {
    private JComboBox<String> designComboBox;
    private JTextField amountField;
    private JButton produceButton;
    private JButton backButton;
    private ProductManager productManager;
    private ByproductManager byproductManager;

    public ProduceProductView(String[] designs, ProductManager productManager, ByproductManager byproductManager) {
        this.productManager = productManager;
        this.byproductManager = byproductManager;
        setTitle("Product Manufacturing");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 450, 40);
        JLabel titleLabel = new JLabel("Product Manufacturing");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(10, 5, 250, 30);
        topPanel.add(titleLabel);
        backButton = new JButton("Back");
        backButton.setBounds(340, 5, 80, 28);
        topPanel.add(backButton);
        add(topPanel);

        int y = 70;
        int labelWidth = 120;
        int valueWidth = 200;
        int height = 25;

        JLabel designLabel = new JLabel("Select Design:");
        designLabel.setBounds(60, y, labelWidth, height);
        add(designLabel);
        designComboBox = new JComboBox<>(designs);
        designComboBox.setBounds(180, y, valueWidth, height);
        add(designComboBox);
        y += 40;

        JLabel amountLabel = new JLabel("Amount to Produce:");
        amountLabel.setBounds(60, y, labelWidth, height);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(180, y, valueWidth, height);
        add(amountField);
        y += 40;

        produceButton = new JButton("Produce");
        produceButton.setBounds(100, y, 250, 30);
        add(produceButton);

        backButton.addActionListener(e -> dispose());
        produceButton.addActionListener(e -> {
            String amountStr = amountField.getText().trim();
            String designName = (String) designComboBox.getSelectedItem();
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= 0) throw new NumberFormatException();
                // Ürün ekle
                if (productManager != null && designName != null) {
                    productManager.addProduct(new Product(designName, amount));
                }
                // Yan ürün örneği (her üretimde 1 byproduct ekleniyor, isterseniz tasarıma göre özelleştirilebilir)
                if (byproductManager != null && designName != null) {
                    byproductManager.addByproduct(new Byproduct("Byproduct_of_" + designName, amount));
                }
                JOptionPane.showMessageDialog(this, "Production successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "You must enter a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JComboBox<String> getDesignComboBox() { return designComboBox; }
    public JTextField getAmountField() { return amountField; }
    public JButton getProduceButton() { return produceButton; }
    public JButton getBackButton() { return backButton; }
} 