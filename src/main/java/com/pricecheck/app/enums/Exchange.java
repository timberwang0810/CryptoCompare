package com.pricecheck.app.enums;


import java.util.Map;

public enum Exchange {
    BLOCKCHAIN("blockchain", "Blockchain", Map.of(
        CurrencyType.BITCOIN, Map.of(CurrencyType.USDOLLAR, "https://api.blockchain.com/v3/exchange/l2/BTC-USD"), 
        CurrencyType.ETHEREUM, Map.of(CurrencyType.USDOLLAR,"https://api.blockchain.com/v3/exchange/l2/ETH-USD"))),

    GEMINI("gemini", "Gemini", Map.of(
        CurrencyType.BITCOIN, Map.of(CurrencyType.USDOLLAR, "https://api.gemini.com/v1/book/btcusd"),
        CurrencyType.ETHEREUM, Map.of(CurrencyType.USDOLLAR,"https://api.gemini.com/v1/book/ethusd")));

    private final String id;
    private final String name;
    private final Map<CurrencyType, Map<CurrencyType,String>> endPoints;
    private Exchange(String id, String name, Map<CurrencyType, Map<CurrencyType, String>> endPoints){
        this.id = id;
        this.name = name;
        this.endPoints = endPoints;
    }

    public String getId(){ return this.id; }
    public String getName(){ return this.name; }
    public String getEndpoint(CurrencyType base, CurrencyType target){
        return this.endPoints.get(base).get(target);
    }
}
