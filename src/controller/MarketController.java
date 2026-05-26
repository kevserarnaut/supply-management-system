package controller;

import model.Market;
import model.MarketManager;

public class MarketController {
    private MarketManager marketManager;

    public MarketController() {
        this.marketManager = new MarketManager();
    }

    public void addMarket(String name, double balance, int capacity) {
        Market market = new Market(name, balance, capacity);
        marketManager.addMarket(market);
    }

    public void removeMarket(String name) {
        Market market = getMarket(name);
        if (market != null) {
            marketManager.removeMarket(market);
        }
    }

    public Market getMarket(String name) {
        return marketManager.getMarket(name);
    }
} 