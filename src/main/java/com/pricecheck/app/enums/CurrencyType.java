package com.pricecheck.app.enums;

public enum CurrencyType {
    BITCOIN("Bitcoin", "BTC"),
    ETHEREUM("Ethereum", "ETH"),
    USDOLLAR("U.S. Dollar", "USD");

    private final String name;
    private final String symbol;
    
    private CurrencyType(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getName(){return name;}
    public String getSymbol(){ return symbol;}
}
