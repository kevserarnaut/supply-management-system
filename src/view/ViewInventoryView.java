package view;
import javax.swing.*;
import java.awt.*;

public class ViewInventoryView extends JFrame {
    private JButton backButton;
    private JTextArea productsArea;
    private JTextArea byproductsArea;

    public ViewInventoryView(String factoryName, String[] products, String[] byproducts) {
        setTitle("Inventory - " + factoryName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 500, 40);
        JLabel titleLabel = new JLabel("Inventory - " + factoryName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(10, 5, 250, 30);
        topPanel.add(titleLabel);
        backButton = new JButton("Back");
        backButton.setBounds(400, 5, 80, 28);
        topPanel.add(backButton);
        add(topPanel);

        JLabel productsLabel = new JLabel("Products");
        productsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        productsLabel.setBounds(10, 50, 100, 20);
        add(productsLabel);
        productsArea = new JTextArea();
        productsArea.setEditable(false);
        JScrollPane productsScroll = new JScrollPane(productsArea);
        productsScroll.setBounds(10, 70, 470, 100);
        add(productsScroll);

        JLabel byproductsLabel = new JLabel("Byproducts");
        byproductsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        byproductsLabel.setBounds(10, 180, 100, 20);
        add(byproductsLabel);
        byproductsArea = new JTextArea();
        byproductsArea.setEditable(false);
        JScrollPane byproductsScroll = new JScrollPane(byproductsArea);
        byproductsScroll.setBounds(10, 200, 470, 140);
        add(byproductsScroll);

        // Fill areas
        StringBuilder prodText = new StringBuilder();
        for (String p : products) prodText.append(p).append("\n");
        productsArea.setText(prodText.toString());
        StringBuilder byprodText = new StringBuilder();
        for (String b : byproducts) byprodText.append(b).append("\n");
        byproductsArea.setText(byprodText.toString());

        backButton.addActionListener(e -> dispose());
    }

    public JButton getBackButton() { return backButton; }
} 