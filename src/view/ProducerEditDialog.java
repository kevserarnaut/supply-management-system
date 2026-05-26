package view;
import javax.swing.*;
import java.awt.*;

public class ProducerEditDialog extends JDialog {
    private JLabel nameLabel;
    private JLabel fundLabel;
    private JLabel stockLabel;
    private JLabel capacityLabel;
    private JTextField amountField;
    private boolean confirmed = false;

    public ProducerEditDialog(JFrame parent, String title, String name, String fund, String stock, String capacity) {
        super(parent, title, true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridy++;
        add(new JLabel("Fund:"), gbc);
        gbc.gridy++;
        add(new JLabel("Stock:"), gbc);
        gbc.gridy++;
        add(new JLabel("Capacity:"), gbc);
        gbc.gridy++;
        add(new JLabel("Amount to Produce:"), gbc);

        // Values
        gbc.gridx = 1; gbc.gridy = 0;
        nameLabel = new JLabel(name);
        add(nameLabel, gbc);
        gbc.gridy++;
        fundLabel = new JLabel(fund);
        add(fundLabel, gbc);
        gbc.gridy++;
        stockLabel = new JLabel(stock);
        add(stockLabel, gbc);
        gbc.gridy++;
        capacityLabel = new JLabel(capacity);
        add(capacityLabel, gbc);
        gbc.gridy++;
        amountField = new JTextField(15);
        add(amountField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        okButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        cancelButton.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() { return confirmed; }
    public String getAmountField() { return amountField.getText().trim(); }
    public JLabel getNameLabel() { return nameLabel; }
    public JLabel getFundLabel() { return fundLabel; }
    public JLabel getStockLabel() { return stockLabel; }
    public JLabel getCapacityLabel() { return capacityLabel; }
} 