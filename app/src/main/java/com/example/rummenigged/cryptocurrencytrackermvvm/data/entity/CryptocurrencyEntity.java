package com.example.rummenigged.cryptocurrencytrackermvvm.data.entity;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyEntity {
    private String idCurrency;

    private String name;

    private String symbol;

    private int rank;

    private Double priceUsd;

    private Double priceBtc;

    private Double volumeUsd24h;

    private Double marketCapUsd;

    private Double availableSupply;

    private Double totalSupply;

    private Double maxSupply;

    private Double percentChange1h;

    private Double lastUpdated;

    private Double percentChance24h;

    private Double percentChance7d;

    public CryptocurrencyEntity(String idCurrency, String name, String symbol, int rank, Double priceUsd
            , Double priceBtc, Double volumeUsd24h, Double marketCapUsd, Double availableSupply, Double totalSupply
            , Double maxSupply, Double percentChange1h, Double lastUpdated, Double percentChance24h, Double percentChance7d) {
        this.idCurrency = idCurrency;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this.volumeUsd24h = volumeUsd24h;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.percentChange1h = percentChange1h;
        this.lastUpdated = lastUpdated;
        this.percentChance24h = percentChance24h;
        this.percentChance7d = percentChance7d;
    }

    public CryptocurrencyEntity() {
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

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Double getVolumeUsd24h() {
        return volumeUsd24h;
    }

    public void setVolumeUsd24h(Double volumeUsd24h) {
        this.volumeUsd24h = volumeUsd24h;
    }

    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Double getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Double lastUpdated) {
        this.lastUpdated = lastUpdated;
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
