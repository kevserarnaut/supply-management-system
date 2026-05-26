package controller;

import model.Customer;
import model.CustomerManager;

public class CustomerController {
    private CustomerManager customerManager;

    public CustomerController() {
        this.customerManager = new CustomerManager();
    }

    public void addCustomer(String name, double balance, int inventoryCapacity) {
        Customer customer = new Customer(name, balance);
        customerManager.addCustomer(customer);
    }

    public void removeCustomer(String name) {
        Customer customer = getCustomer(name);
        if (customer != null) {
            customerManager.removeCustomer(customer);
        }
    }

    public Customer getCustomer(String name) {
        return customerManager.getCustomerByName(name);
    }
} 