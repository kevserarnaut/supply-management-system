package model;

public class Factory {
    private String name;
    private double balance;
    private int capacity;

    public Factory(String name, double balance, int capacity) {
        this.name = name;
        this.balance = balance;
        this.capacity = capacity;
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public int getCapacity() { return capacity; }

    public void setName(String name) { this.name = name; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
} 