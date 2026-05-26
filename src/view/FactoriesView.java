package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import model.ProducerManager;
import model.DesignManager;
import model.ByproductManager;
import model.ProductManager;

public class FactoriesView extends JFrame {
    private JList<String> factoryList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton editButton;
    private JButton backButton;
    private java.util.List<String> factories;
    private ProducerManager producerManager;
    private DesignManager designManager;
    private ByproductManager byproductManager;
    private ProductManager productManager;

    public FactoriesView(List<String> factories, ProducerManager producerManager, DesignManager designManager, ByproductManager byproductManager, ProductManager productManager) {
        setTitle("Factory List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.producerManager = producerManager;
        this.designManager = designManager;
        this.byproductManager = byproductManager;
        this.productManager = productManager;

        // Initialize with single factory
        this.factories = new ArrayList<>();
        this.factories.add("Factory_0");

        // Top panel with title and back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Factory List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(titleLabel, BorderLayout.WEST);
        backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // List
        listModel = new DefaultListModel<>();
        for (String name : this.factories) {
            listModel.addElement(name);
        }
        factoryList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(factoryList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add New Factory");
        editButton = new JButton("Edit Factory");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> dispose());
        editButton.addActionListener(e -> {
            String selected = factoryList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a factory to edit.");
                return;
            }
            FactoryDetailView detailView = new FactoryDetailView(
                selected,
                "11000.0",
                "2200",
                producerManager,
                designManager,
                byproductManager,
                productManager,
                "300.0"
            );
            detailView.setVisible(true);
        });
        addButton.addActionListener(e -> {
            FactoryAddDialog dialog = new FactoryAddDialog(this);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    String name = dialog.getNameField();
                    double balance = Double.parseDouble(dialog.getBalanceField());
                    int capacity = Integer.parseInt(dialog.getCapacityField());
                    if (name.isEmpty() || balance < 0 || capacity <= 0) {
                        throw new IllegalArgumentException();
                    }
                    this.factories.add(name);
                    listModel.addElement(name);
                    JOptionPane.showMessageDialog(this, "Factory added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "You must enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled and values must be positive!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JList<String> getFactoryList() { return factoryList; }
    public JButton getAddButton() { return addButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getBackButton() { return backButton; }
    public void setFactoryList(List<String> factories) {
        listModel.clear();
        for (String name : factories) {
            listModel.addElement(name);
        }
    }
} 