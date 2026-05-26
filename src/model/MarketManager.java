package model;

import java.util.ArrayList;
import java.util.List;

public class MarketManager {
    private List<Market> markets;

    public MarketManager() {
        this.markets = new ArrayList<>();
    }

    public void addMarket(Market market) {
        markets.add(market);
    }

    public void removeMarket(Market market) {
        markets.remove(market);
    }

    public Market getMarket(String name) {
        for (Market market : markets) {
            if (market.getName().equals(name)) {
                return market;
            }
        }
        return null;
    }

    public List<Market> getAllMarkets() {
        return new ArrayList<>(markets);
    }
} 