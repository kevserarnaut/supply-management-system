package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import model.ProductManager;

public class MarketsView extends JFrame {
    private JList<String> marketList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton editButton;
    private JButton backButton;
    private java.util.List<String> markets;
    private ProductManager productManager;
    private Map<String, Double> marketBalances = new HashMap<>();

    public MarketsView(List<String> markets, ProductManager productManager) {
        setTitle("Market List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.markets = markets;
        this.productManager = productManager;

        // Top panel with title and back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Market List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(titleLabel, BorderLayout.WEST);
        backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // List
        listModel = new DefaultListModel<>();
        for (String name : this.markets) {
            listModel.addElement(name);
            marketBalances.put(name, 0.0); // Varsayılan balance
        }
        marketList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(marketList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add New Market");
        editButton = new JButton("Edit Market");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> dispose());
        editButton.addActionListener(e -> {
            String selected = marketList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a market to edit.");
                return;
            }
            double balance = marketBalances.getOrDefault(selected, 0.0);
            MarketDetailView detailView = new MarketDetailView(selected, balance, productManager);
            detailView.setVisible(true);
        });
        addButton.addActionListener(e -> {
            MarketAddDialog dialog = new MarketAddDialog(this);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    String name = dialog.getNameField();
                    double balance = Double.parseDouble(dialog.getBalanceField());
                    if (name.isEmpty() || balance < 0) {
                        throw new IllegalArgumentException();
                    }
                    this.markets.add(name);
                    listModel.addElement(name);
                    marketBalances.put(name, balance);
                    JOptionPane.showMessageDialog(this, "Market added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "You must enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled and values must be positive!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JList<String> getMarketList() { return marketList; }
    public JButton getAddButton() { return addButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getBackButton() { return backButton; }
    public void setMarketList(List<String> markets) {
        listModel.clear();
        for (String name : markets) {
            listModel.addElement(name);
        }
    }
} 