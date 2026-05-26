package model;

public class Producer {
    private String name;
    private double cost;
    private double sellingPrice;
    private int capacity;
    private double fund;
    private int stock;

    public Producer(String name, double cost, double sellingPrice, int capacity, double fund, int stock) {
        this.name = name;
        this.cost = cost;
        this.sellingPrice = sellingPrice;
        this.capacity = capacity;
        this.fund = fund;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public int getCapacity() {
        return capacity;
    }
    public double getFund() {
        return fund;
    }
    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setFund(double fund) { this.fund = fund; }
    public void setStock(int stock) { this.stock = stock; }
} 