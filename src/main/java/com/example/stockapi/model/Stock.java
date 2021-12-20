package com.example.stockapi.model;

import com.example.stockapi.dao.StockDataService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;

@Data
public class Stock {

    static StockDataService stockDataService = new StockDataService();

    @JsonIgnore
    private final String id;

    private final String name;
    private final String symbol;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final Exchange exchange;

    private Double PE;
    private Double EPS;

    private Double LTP;

    private Double previousOpen;
    private Double previousClose;

    private Double latestHigh;
    private Double latestLow;

    private Double fiftyTwoWeekHigh;
    private Double fiftyTwoWeekLow;


    public Stock(String name, String symbol, String exchange, Double PE, Double EPS, Double LTP, Double previousOpen, Double previousClose, Double latestHigh, Double latestLow, Double fiftyTwoWeekHigh, Double fiftyTwoWeekLow) {
        this.name = name;
        this.symbol = symbol;
        this.exchange = Exchange.valueOf(exchange);
        this.PE = PE;
        this.EPS = EPS;
        this.LTP = LTP;
        this.previousOpen = previousOpen;
        this.previousClose = previousClose;
        this.latestHigh = latestHigh;
        this.latestLow = latestLow;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.id = this.exchange + ":" + this.symbol;
    }

    public Stock(String name, String symbol, String exchange) {
        this.name = name;
        this.symbol = symbol;
        this.exchange = Exchange.valueOf(exchange);
        this.id = this.exchange + ":" + this.symbol;
    }

    public static Stock getStockFromSymbolAndExchange(String symbol, String exchange) {
        return stockDataService.getStock(symbol, exchange);
    }

    public static Stock getStockFromSymbol(String symbol) {
        return getStockFromSymbolAndExchange(symbol, "NS");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return getId().equals(stock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
