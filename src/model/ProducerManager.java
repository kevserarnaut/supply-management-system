package model;

import java.util.ArrayList;
import java.util.List;

public class ProducerManager {
    private List<Producer> producers;

    public ProducerManager() {
        producers = new ArrayList<>();
    }

    public void addProducer(Producer producer) {
        producers.add(producer);
    }

    public void removeProducer(Producer producer) {
        producers.remove(producer);
    }

    public List<Producer> getProducers() {
        return producers; //üreticiler dışarıdan güncellenebilsin diye kopya çağırmadık.
    }

    public Producer getProducerByName(String name) {
        for (Producer p : producers) {
            if (p.getName().equals(name)) return p;
        }
        return null;
    }
} 