package view;
import javax.swing.*;
import java.awt.*;

public class ProducerDetailView extends JFrame {
    private JLabel nameLabel;
    private JLabel fundLabel;
    private JLabel stockLabel;
    private JLabel capacityLabel;
    private JTextField amountField;
    private JButton produceButton;
    private JButton backButton;

    public ProducerDetailView(String producerName, String fund, String stock, String capacity) {
        setTitle("Producer: " + producerName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 400, 40);
        JLabel titleLabel = new JLabel("Producer: " + producerName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(10, 5, 250, 30);
        topPanel.add(titleLabel);
        backButton = new JButton("Back");
        backButton.setBounds(300, 5, 80, 28);
        topPanel.add(backButton);
        add(topPanel);

        int y = 60;
        int labelWidth = 100;
        int valueWidth = 150;
        int height = 25;

        JLabel nameStatic = new JLabel("Name:");
        nameStatic.setBounds(40, y, labelWidth, height);
        add(nameStatic);
        nameLabel = new JLabel(producerName);
        nameLabel.setBounds(160, y, valueWidth, height);
        add(nameLabel);
        y += 30;

        JLabel fundStatic = new JLabel("Fund:");
        fundStatic.setBounds(40, y, labelWidth, height);
        add(fundStatic);
        fundLabel = new JLabel(fund);
        fundLabel.setBounds(160, y, valueWidth, height);
        add(fundLabel);
        y += 30;

        JLabel stockStatic = new JLabel("Stock:");
        stockStatic.setBounds(40, y, labelWidth, height);
        add(stockStatic);
        stockLabel = new JLabel(stock);
        stockLabel.setBounds(160, y, valueWidth, height);
        add(stockLabel);
        y += 30;

        JLabel capacityStatic = new JLabel("Capacity:");
        capacityStatic.setBounds(40, y, labelWidth, height);
        add(capacityStatic);
        capacityLabel = new JLabel(capacity);
        capacityLabel.setBounds(160, y, valueWidth, height);
        add(capacityLabel);
        y += 40;

        JLabel amountLabel = new JLabel("Amount to Produce:");
        amountLabel.setBounds(40, y, labelWidth + 40, height);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(200, y, 100, height);
        add(amountField);
        y += 40;

        produceButton = new JButton("Produce");
        produceButton.setBounds(80, y, 220, 35);
        add(produceButton);
    }

    public JLabel getNameLabel() { return nameLabel; }
    public JLabel getFundLabel() { return fundLabel; }
    public JLabel getStockLabel() { return stockLabel; }
    public JLabel getCapacityLabel() { return capacityLabel; }
    public JTextField getAmountField() { return amountField; }
    public JButton getProduceButton() { return produceButton; }
    public JButton getBackButton() { return backButton; }

    public void setProducerInfo(String fund, String stock, String capacity) {
        fundLabel.setText(fund);
        stockLabel.setText(stock);
        capacityLabel.setText(capacity);
    }
} 