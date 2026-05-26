package view;
import javax.swing.*;
import java.awt.*;
import model.ByproductManager;

public class DestroyByproductView extends JFrame {
    private JComboBox<String> byproductComboBox;
    private JTextField amountField;
    private JButton destroyButton;
    private JButton backButton;
    private ByproductManager byproductManager;

    public DestroyByproductView(String[] byproducts, ByproductManager byproductManager) {
        this.byproductManager = byproductManager;
        setTitle("Destroy Byproduct");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 450, 40);
        JLabel titleLabel = new JLabel("Destroy Byproduct");
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

        JLabel byproductLabel = new JLabel("Select Byproduct:");
        byproductLabel.setBounds(60, y, labelWidth, height);
        add(byproductLabel);
        byproductComboBox = new JComboBox<>(byproducts);
        byproductComboBox.setBounds(180, y, valueWidth, height);
        add(byproductComboBox);
        y += 40;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(60, y, labelWidth, height);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(180, y, valueWidth, height);
        add(amountField);
        y += 40;

        destroyButton = new JButton("Destroy");
        destroyButton.setBounds(100, y, 250, 30);
        add(destroyButton);

        destroyButton.addActionListener(e -> {
            String selected = (String) byproductComboBox.getSelectedItem();
            String amountStr = amountField.getText().trim();
            if (selected == null || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a byproduct and enter an amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= 0) throw new NumberFormatException();
                byproductManager.removeByproduct(selected, amount);
                JOptionPane.showMessageDialog(this, "Byproduct destroyed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> dispose());
    }

    public JComboBox<String> getByproductComboBox() { return byproductComboBox; }
    public JTextField getAmountField() { return amountField; }
    public JButton getDestroyButton() { return destroyButton; }
    public JButton getBackButton() { return backButton; }
} 