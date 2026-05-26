package controller;

import model.Factory;
import model.FactoryManager;

public class FactoryController {
    private FactoryManager factoryManager;

    public FactoryController() {
        this.factoryManager = new FactoryManager();
    }

    public void addFactory(String name, double balance, int capacity) {
        Factory factory = new Factory(name, balance, capacity);
        factoryManager.addFactory(factory);
    }

    public void removeFactory(String name) {
        Factory factory = getFactory(name);
        if (factory != null) {
            factoryManager.removeFactory(factory);
        }
    }

    public Factory getFactory(String name) {
        for (Factory factory : factoryManager.getAllFactories()) {
            if (factory.getName().equals(name)) {
                return factory;
            }
        }
        return null;
    }
} 