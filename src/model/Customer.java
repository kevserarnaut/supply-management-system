package model;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String name;
    private double balance;
    private Map<String, Integer> inventory;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.inventory = new HashMap<>();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public Map<String, Integer> getInventory() { return inventory; }
    public void addToInventory(String product, int amount) {
        inventory.put(product, inventory.getOrDefault(product, 0) + amount);
    }
    public void removeFromInventory(String product, int amount) {
        int current = inventory.getOrDefault(product, 0);
        if (current <= amount) inventory.remove(product);
        else inventory.put(product, current - amount);
    }
} 