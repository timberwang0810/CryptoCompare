package com.pricecheck.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pricecheck.app.enums.CurrencyType;
import com.pricecheck.app.enums.Exchange;
import com.pricecheck.app.model.Currency;
import com.pricecheck.app.model.ExchangeCurrency;
import com.pricecheck.app.restContainers.blockchain.BlockchainBook;
import com.pricecheck.app.restContainers.gemini.GeminiBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class AppController {

    @Autowired 
    private RestTemplate restTemplate;

    private List<CurrencyType> bases = List.of(CurrencyType.BITCOIN, CurrencyType.ETHEREUM);
    private List<CurrencyType> targets = List.of(CurrencyType.USDOLLAR);
    private List<Exchange> exchanges = List.of(Exchange.BLOCKCHAIN, Exchange.GEMINI);

    @GetMapping("list")
    public List<ExchangeCurrency> getCurrencies(){
        List<ExchangeCurrency> result = new ArrayList<>();
        for (Exchange exchange : exchanges){
            for (CurrencyType base : bases){
                result.add(exchangeCurrencyFromAPI(exchange, base));
            }
        }
        return result;
    }

    @GetMapping("exchanges/{exchangeId}")
    public List<ExchangeCurrency> getCurrenciesByExchange(@PathVariable("exchangeId") String exchangeId){
        List<ExchangeCurrency> result = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            if (exchange.getId().equals(exchangeId)){
                for (CurrencyType base : bases) {
                    result.add(exchangeCurrencyFromAPI(exchange, base));
                }
            }
        }
        return result;
    }

    @GetMapping("currencies/{currencyId}")
    public List<ExchangeCurrency> getCurrenciesById(@PathVariable("currencyId") String currencyId){
        List<ExchangeCurrency> result = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            for (CurrencyType base : bases) {
                if (base.getSymbol().toLowerCase().equals(currencyId)){
                    result.add(exchangeCurrencyFromAPI(exchange, base));
                    break;
                }
            }
        }
        return result;
    }

    @GetMapping("currency/{currencyId}")
    public Currency getCurrencyById(@PathVariable("currencyId") String currencyId) {
        for (CurrencyType base : bases) {
            if (base.getSymbol().toLowerCase().equals(currencyId)) {
                return new Currency(base.getSymbol(), base.getName(), base.getSymbol());
            }
        }
        return null;
    }
    private ExchangeCurrency exchangeCurrencyFromAPI(Exchange exchange, CurrencyType base){
        Map<CurrencyType, Float> buyPrices = new HashMap<>();
        Map<CurrencyType, Float> sellPrices = new HashMap<>();
        switch (exchange){
            case BLOCKCHAIN:
                for (CurrencyType target : targets){
                    String url = exchange.getEndpoint(base, target);
                    BlockchainBook blockchainBook = restTemplate.getForObject(url, 
                            BlockchainBook.class);

                    buyPrices.put(target, Collections.min(blockchainBook.getAsks()).getBuyPrice());
                    sellPrices.put(target, Collections.max(blockchainBook.getBids()).getSellPrice());
                }
  
                return new ExchangeCurrency(base.getSymbol(), base.getName(), base.getSymbol(), exchange.getName(), 
                        buyPrices, sellPrices);
            case GEMINI:
                for (CurrencyType target : targets) {
                    String url = exchange.getEndpoint(base, target);
                    GeminiBook blockchainBook = restTemplate.getForObject(url, GeminiBook.class);

                    buyPrices.put(target, Collections.min(blockchainBook.getAsks()).getBuyPrice());
                    sellPrices.put(target, Collections.max(blockchainBook.getBids()).getSellPrice());
                }

                return new ExchangeCurrency(base.getSymbol(), base.getName(), base.getSymbol(), exchange.getName(),
                        buyPrices, sellPrices);
            default:
                return null;
        }
    }
}
