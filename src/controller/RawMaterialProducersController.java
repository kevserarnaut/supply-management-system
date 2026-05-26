package controller;

import model.Producer;
import model.ProducerManager;

public class RawMaterialProducersController {
    private ProducerManager producerManager;

    public RawMaterialProducersController(ProducerManager producerManager) {
        this.producerManager = producerManager;
    }

    public void addProducer(String name, double cost, double sellingPrice, int capacity, double fund, int stock) {
        Producer producer = new Producer(name, cost, sellingPrice, capacity, fund, stock);
        producerManager.addProducer(producer);
    }

    public void removeProducer(String name) {
        Producer producer = getProducer(name);
        if (producer != null) {
            producerManager.removeProducer(producer);
        }
    }

    public Producer getProducer(String name) {
        return producerManager.getProducerByName(name);
    }

    public void updateProducer(String name, double cost, double sellingPrice, int capacity, double fund, int stock) {
        Producer producer = getProducer(name);
        if (producer != null) {
            producer.setCost(cost);
            producer.setSellingPrice(sellingPrice);
            producer.setCapacity(capacity);
            producer.setFund(fund);
            producer.setStock(stock);
        }
    }

    public void updateStock(String name, int stock) {
        Producer producer = getProducer(name);
        if (producer != null) {
            producer.setStock(stock);
        }
    }

    public void updateFund(String name, double fund) {
        Producer producer = getProducer(name);
        if (producer != null) {
            producer.setFund(fund);
        }
    }
}
