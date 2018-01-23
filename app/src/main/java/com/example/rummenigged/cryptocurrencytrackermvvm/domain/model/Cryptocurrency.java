package com.example.rummenigged.cryptocurrencytrackermvvm.domain.model;

/**
 * Created by rummenigged on 22/01/18.
 */

public class Cryptocurrency {

    private String idCurrency;

    private String name;

    private String symbol;

    private int rank;

    private Double price;

    private Double percentChance24h;

    private Double percentChance7d;

    public Cryptocurrency(String idCurrency, String name, String symbol, double price, double percentChance24h, double percentChance7d) {
        this.idCurrency = idCurrency;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.percentChance24h = percentChance24h;
        this.percentChance7d = percentChance7d;
    }

    public Cryptocurrency() {

    }

    public String getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(String idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getName() {
        return name;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPercentChance24h() {
        return percentChance24h;
    }

    public void setPercentChance24h(Double percentChance24h) {
        this.percentChance24h = percentChance24h;
    }

    public Double getPercentChance7d() {
        return percentChance7d;
    }

    public void setPercentChance7d(Double percentChance7d) {
        this.percentChance7d = percentChance7d;
    }
}
