package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.CustomerManager;
import model.Customer;
import model.ProductManager;

public class CustomersView extends JFrame {
    private JList<String> customerList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;
    private java.util.List<String> customers;
    private CustomerManager customerManager;
    private ProductManager productManager;

    public CustomersView(List<String> customers, CustomerManager customerManager, ProductManager productManager) {
        setTitle("Customer List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.customers = customers;
        this.customerManager = customerManager;
        this.productManager = productManager;

        // Top panel with title and back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Customer List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(titleLabel, BorderLayout.WEST);
        backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // List
        listModel = new DefaultListModel<>();
        for (String name : this.customers) {
            listModel.addElement(name);
        }
        customerList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(customerList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        addButton = new JButton("Add New Customer");
        editButton = new JButton("Edit Customer");
        deleteButton = new JButton("Delete Customer");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> dispose());
        editButton.addActionListener(e -> {
            String selected = customerList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a customer to view.");
                return;
            }
            Customer customer = customerManager.getCustomerByName(selected);
            double balance = customer != null ? customer.getBalance() : 0.0;
            CustomerDetailView detailView = new CustomerDetailView(selected, balance, customerManager, productManager);
            detailView.setVisible(true);
        });
        deleteButton.addActionListener(e -> {
            String selected = customerList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a customer to delete.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete '" + selected + "'?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                listModel.removeElement(selected);
                this.customers.remove(selected);
            }
        });
        addButton.addActionListener(e -> {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
            panel.add(iconLabel, gbc);
            gbc.gridheight = 1;

            gbc.gridx = 1; gbc.gridy = 0;
            panel.add(new JLabel("Customer Name:"), gbc);
            gbc.gridx = 2;
            JTextField nameField = new JTextField(12);
            panel.add(nameField, gbc);

            gbc.gridx = 1; gbc.gridy = 1;
            panel.add(new JLabel("Initial Balance:"), gbc);
            gbc.gridx = 2;
            JTextField balanceField = new JTextField(10);
            panel.add(balanceField, gbc);

            int result = JOptionPane.showOptionDialog(
                this,
                panel,
                "Add New Customer",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Cancel", "OK"},
                "OK"
            );
            if (result == 1) { // OK
                String name = nameField.getText().trim();
                String balanceStr = balanceField.getText().trim();
                try {
                    double balance = Double.parseDouble(balanceStr);
                    if (name.isEmpty() || balance < 0) throw new IllegalArgumentException();
                    Customer customer = new Customer(name, balance);
                    customerManager.addCustomer(customer);
                    this.customers.add(name);
                    listModel.addElement(name);
                    JOptionPane.showMessageDialog(this, "Customer added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "You must enter a valid numeric balance!", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled and balance must be positive!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JList<String> getCustomerList() { return customerList; }
    public JButton getAddButton() { return addButton; }
    public JButton getEditButton() { return editButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getBackButton() { return backButton; }
    public void setCustomerList(List<String> customers) {
        listModel.clear();
        for (String name : customers) {
            listModel.addElement(name);
        }
    }
} 