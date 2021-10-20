package com.pricecheck.app.restContainers.gemini;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ask implements Comparable<Ask> {

    @JsonProperty("price")
    private Float buyPrice;

    public Ask() {
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ask))
            return false;
        Ask a = (Ask) o;
        return a.getBuyPrice() == this.buyPrice;
    }

    @Override
    public int compareTo(Ask ask) {
        if (this.buyPrice > ask.getBuyPrice())
            return 1;
        if (this.buyPrice < ask.getBuyPrice())
            return -1;
        return 0;
    }
}
