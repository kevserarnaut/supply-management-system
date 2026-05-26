package model;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public Customer getCustomerByName(String name) {
        for (Customer c : customers) {
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }
} 