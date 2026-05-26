package view;
import javax.swing.*;
import java.awt.*;
import model.ProducerManager;
import model.FactoryManager;
import model.MarketManager;
import model.CustomerManager;
import model.DesignManager;
import model.ByproductManager;
import model.ProductManager;


public class MainPageView extends JFrame {
    private JButton rawMaterialProducersButton;
    private JButton factoriesButton;
    private JButton marketsButton;
    private JButton customersButton;
    private JButton exitButton;
    private ProducerManager producerManager;
    private FactoryManager factoryManager;
    private MarketManager marketManager;
    private CustomerManager customerManager;
    private DesignManager designManager;
    private ByproductManager byproductManager;
    private ProductManager productManager;

    public MainPageView(ProducerManager producerManager, FactoryManager factoryManager, MarketManager marketManager, CustomerManager customerManager, DesignManager designManager, ByproductManager byproductManager, ProductManager productManager) {
        this.producerManager = producerManager;
        this.factoryManager = factoryManager;
        this.marketManager = marketManager;
        this.customerManager = customerManager;
        this.designManager = designManager;
        this.byproductManager = byproductManager;
        this.productManager = productManager;
        setTitle("Supply Chain Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        JLabel titleLabel = new JLabel("Supply Chain Management System", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(15, 10, 300, 30);
        mainPanel.add(titleLabel);

        exitButton = new JButton("Exit");
        exitButton.setBounds(380, 10, 80, 28);
        mainPanel.add(exitButton);
        exitButton.addActionListener(e -> dispose());

        rawMaterialProducersButton = createStyledButton("Raw Material Producers");
        rawMaterialProducersButton.setBounds(60, 60, 370, 60);
        mainPanel.add(rawMaterialProducersButton);
        rawMaterialProducersButton.addActionListener(e -> {
            RawMaterialProducersView producersView = new RawMaterialProducersView(producerManager);
            producersView.setVisible(true);
        });

        factoriesButton = createStyledButton("Factories");
        factoriesButton.setBounds(60, 130, 370, 60);
        mainPanel.add(factoriesButton);
        factoriesButton.addActionListener(e -> {
            FactoriesView factoriesView = new FactoriesView(new java.util.ArrayList<>(), producerManager, designManager, byproductManager, productManager);
            factoriesView.setVisible(true);
        });

        marketsButton = createStyledButton("Markets");
        marketsButton.setBounds(60, 200, 370, 60);
        mainPanel.add(marketsButton);
        marketsButton.addActionListener(e -> {
            MarketsView marketsView = new MarketsView(new java.util.ArrayList<>(), productManager);
            marketsView.setVisible(true);
        });

        customersButton = createStyledButton("Customers");
        customersButton.setBounds(60, 270, 370, 60);
        mainPanel.add(customersButton);
        customersButton.addActionListener(e -> {
            CustomersView customersView = new CustomersView(new java.util.ArrayList<>(), customerManager, productManager);
            customersView.setVisible(true);
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(0, 0, new Color(220, 230, 245), 0, getHeight(), new Color(180, 200, 230));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(160, 180, 210));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setForeground(Color.DARK_GRAY);
        return button;
    }

    public JButton getRawMaterialProducersButton() {
        return rawMaterialProducersButton;
    }
    public JButton getFactoriesButton() {
        return factoriesButton;
    }
    public JButton getMarketsButton() {
        return marketsButton;
    }
    public JButton getCustomersButton() {
        return customersButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
} 