package model;

import java.util.ArrayList;
import java.util.List;

public class FactoryManager {
    private List<Factory> factories;

    public FactoryManager() {
        this.factories = new ArrayList<>();
    }

    public void addFactory(Factory factory) {
        factories.add(factory);
    }

    public void removeFactory(Factory factory) {
        factories.remove(factory);
    }

    public List<Factory> getAllFactories() {
        return new ArrayList<>(factories);
    }

    public List<Factory> getFactories() {
        return factories;
    }

    public Factory getFactoryByName(String name) {
        for (Factory f : factories) {
            if (f.getName().equals(name)) return f;
        }
        return null;
    }
} 