package model;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        // Aynı isimde varsa miktarını artır
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                p.setAmount(p.getAmount() + product.getAmount());
                return;
            }
        }
        products.add(product);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
} 