package view;
import javax.swing.*;
import java.awt.*;

public class ProducerAddDialog extends JDialog {
    private JTextField nameField;
    private JTextField costField;
    private JTextField sellingPriceField;
    private JTextField capacityField;
    private JTextField initialFundField;
    private boolean confirmed = false;

    public ProducerAddDialog(JFrame parent) {
        super(parent, "Add New Producer", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Icon
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 5;
        add(iconLabel, gbc);
        gbc.gridheight = 1;

        // Labels
        gbc.gridx = 1; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridy++;
        add(new JLabel("Cost:"), gbc);
        gbc.gridy++;
        add(new JLabel("Selling Price:"), gbc);
        gbc.gridy++;
        add(new JLabel("Capacity:"), gbc);
        gbc.gridy++;
        add(new JLabel("Initial Fund:"), gbc);

        // Fields
        gbc.gridx = 2; gbc.gridy = 0;
        nameField = new JTextField(15);
        add(nameField, gbc);
        gbc.gridy++;
        costField = new JTextField(15);
        add(costField, gbc);
        gbc.gridy++;
        sellingPriceField = new JTextField(15);
        add(sellingPriceField, gbc);
        gbc.gridy++;
        capacityField = new JTextField(15);
        add(capacityField, gbc);
        gbc.gridy++;
        initialFundField = new JTextField(15);
        add(initialFundField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 1; gbc.gridy++; gbc.gridwidth = 2;
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
    public String getNameField() { return nameField.getText().trim(); }
    public String getCostField() { return costField.getText().trim(); }
    public String getSellingPriceField() { return sellingPriceField.getText().trim(); }
    public String getCapacityField() { return capacityField.getText().trim(); }
    public String getInitialFundField() { return initialFundField.getText().trim(); }
} 