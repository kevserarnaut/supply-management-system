package view;
import javax.swing.*;
import java.awt.*;


public class MarketAddDialog extends JDialog {
    private JTextField nameField;
    private JTextField balanceField;
    private boolean confirmed = false;

    public MarketAddDialog(JFrame parent) {
        super(parent, "Add New Market", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Icon
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        add(iconLabel, gbc);
        gbc.gridheight = 1;

        // Labels
        gbc.gridx = 1; gbc.gridy = 0;
        add(new JLabel("Market Name:"), gbc);
        gbc.gridy++;
        add(new JLabel("Initial Balance:"), gbc);

        // Fields
        gbc.gridx = 2; gbc.gridy = 0;
        nameField = new JTextField(15);
        add(nameField, gbc);
        gbc.gridy++;
        balanceField = new JTextField(15);
        add(balanceField, gbc);

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
    public String getBalanceField() { return balanceField.getText().trim(); }
} 