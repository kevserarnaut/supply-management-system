package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.ProducerManager;
import model.Producer;

public class RawMaterialProducersView extends JFrame {
    private JList<String> producerList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton editButton;
    private JButton backButton;
    private ProducerManager producerManager;

    public RawMaterialProducersView(ProducerManager producerManager) {
        this.producerManager = producerManager;
        setTitle("Raw Material Producers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with title and back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Raw Material Producers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(titleLabel, BorderLayout.WEST);
        backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // List
        listModel = new DefaultListModel<>();
        updateProducerList();
        producerList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(producerList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add New Producer");
        editButton = new JButton("Edit Producer");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        editButton.addActionListener(e -> {
            String selected = producerList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a producer to edit.");
                return;
            }
            Producer p = producerManager.getProducerByName(selected);
            if (p == null) return;
            ProducerEditDialog dialog = new ProducerEditDialog(
                this,
                "Edit Producer",
                p.getName(),
                String.valueOf(p.getFund()),
                String.valueOf(p.getStock()),
                String.valueOf(p.getCapacity())
            );
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    int amount = Integer.parseInt(dialog.getAmountField());
                    double totalCost = amount * p.getCost();
                    if (amount <= 0) throw new NumberFormatException();
                    if (p.getFund() < totalCost) {
                        JOptionPane.showMessageDialog(this, "Not enough funds to produce!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (p.getStock() + amount > p.getCapacity()) {
                        JOptionPane.showMessageDialog(this, "Not enough storage capacity!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Üretim işlemi
                    p.setFund(p.getFund() - totalCost);
                    p.setStock(p.getStock() + amount);
                    JOptionPane.showMessageDialog(this, "Production successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateProducerList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "You must enter a positive integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addButton.addActionListener(e -> {
            ProducerAddDialog dialog = new ProducerAddDialog(this);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    String name = dialog.getNameField();
                    double cost = Double.parseDouble(dialog.getCostField());
                    double sellingPrice = Double.parseDouble(dialog.getSellingPriceField());
                    int capacity = Integer.parseInt(dialog.getCapacityField());
                    double fund = Double.parseDouble(dialog.getInitialFundField());
                    if (name.isEmpty() || cost < 0 || sellingPrice < 0 || capacity <= 0 || fund < 0) {
                        throw new IllegalArgumentException();
                    }
                    producerManager.addProducer(new Producer(name, cost, sellingPrice, capacity, fund, 0));
                    updateProducerList();
                    JOptionPane.showMessageDialog(this, "Producer added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "You must enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled and values must be positive!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> dispose());
    }

    private void updateProducerList() {
        listModel.clear();
        for (Producer p : producerManager.getProducers()) {
            listModel.addElement(p.getName());
        }
    }

    public JList<String> getProducerList() { return producerList; }
    public JButton getAddButton() { return addButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getBackButton() { return backButton; }
} 