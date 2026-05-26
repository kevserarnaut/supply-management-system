import javax.swing.*;
import model.ProducerManager;
import model.FactoryManager;
import model.MarketManager;
import model.CustomerManager;
import model.DesignManager;
import model.ByproductManager;
import model.ProductManager;
import view.MainPageView;



public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ProducerManager producerManager = new ProducerManager();
            FactoryManager factoryManager = new FactoryManager();
            MarketManager marketManager = new MarketManager();
            CustomerManager customerManager = new CustomerManager();
            DesignManager designManager = new DesignManager();
            ByproductManager byproductManager = new ByproductManager();
            ProductManager productManager = new ProductManager();
            MainPageView mainPage = new MainPageView(producerManager, factoryManager, marketManager, customerManager, designManager, byproductManager, productManager);
            mainPage.setVisible(true);
        });
    }
} 