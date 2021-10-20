package com.pricecheck.app.model;

import java.util.HashMap;
import java.util.Map;

import com.pricecheck.app.enums.CurrencyType;
public class ExchangeCurrency {

    private String id;
    private String name;
    private String symbol;
    private String exchange;
    private Map<CurrencyType, Float> buyPrices;
    private Map<CurrencyType, Float> sellPrices;

    public ExchangeCurrency(){
        this.buyPrices = new HashMap<>();
        this.sellPrices = new HashMap<>();
    }

    public ExchangeCurrency(String id, String name, String symbol, String exchange, Map<CurrencyType, Float> buyPrices,
            Map<CurrencyType, Float> sellPrices) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchange = exchange;
        this.buyPrices = buyPrices;
        this.sellPrices = sellPrices;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Map<CurrencyType, Float> getBuyPrices() {
        return buyPrices;
    }

    public void setBuyPrices(Map<CurrencyType, Float> buyPrices) {
        this.buyPrices = buyPrices;
    }

    public Map<CurrencyType, Float> getSellPrices() {
        return sellPrices;
    }

    public void setSellPrices(Map<CurrencyType, Float> sellPrices) {
        this.sellPrices = sellPrices;
    }

    public Float getBuyPriceForTarget(CurrencyType target) {
        if (buyPrices.containsKey(target))
            return buyPrices.get(target);
        return Float.NaN;
    }

    public Float getSellPriceForTarget(CurrencyType target) {
        if (sellPrices.containsKey(target))
            return sellPrices.get(target);
        return Float.NaN;
    }

    public void setBuyPriceForTarget(CurrencyType target, Float price) {
        buyPrices.put(target, price);
    }

    public void setSellPriceForTarget(CurrencyType target, Float price) {
        sellPrices.put(target, price);
    }
}