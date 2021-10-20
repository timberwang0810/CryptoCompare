package com.pricecheck.app.restContainers.blockchain;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bid implements Comparable<Bid>{

    @JsonProperty("px")
    private Float sellPrice;

    public Bid(){}

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Bid)) return false;
        Bid b = (Bid) o;
        return b.getSellPrice() == this.sellPrice;
    }

    @Override
    public int compareTo(Bid bid){
        if (this.sellPrice > bid.getSellPrice()) return 1;
        if (this.sellPrice < bid.getSellPrice()) return -1;
        return 0;
    }
}
