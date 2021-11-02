package com.software.m.m.creppycrypt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.software.m.m.creppycrypt.vm.CurrencyItemViewModel;

public class Currency {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("cmc_rank")
    @Expose
    public int rank;
    @SerializedName("circulating_supply")
    @Expose
    public double availableSupply;
    @SerializedName("total_supply")
    @Expose
    public double totalSupply;
    @SerializedName("max_supply")
    @Expose
    public double maxSupply;
    @SerializedName("last_updated")
    @Expose
    public String lastUpdated;
    @SerializedName("quote")
    @Expose
    public Quote quote;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Currency) {
            return symbol.equals(((Currency) obj).symbol) && lastUpdated.equals(((Currency) obj).lastUpdated);
        } else if (obj instanceof CurrencyItemViewModel) {
            return symbol.equals(((CurrencyItemViewModel) obj).currency.symbol) && lastUpdated == ((CurrencyItemViewModel) obj).currency.lastUpdated;
        }else {
            return false;
        }
    }

    public class Quote {
        @SerializedName("USD")
        @Expose
        public Price priceUsd;

    }

    public class Price {
        @SerializedName("price")
        @Expose
        public double priceUsd;
        @SerializedName("volume_24h")
        @Expose
        public double _24hVolumeUsd;
        @SerializedName("market_cap")
        @Expose
        public double marketCapUsd;
        @SerializedName("percent_change_1h")
        @Expose
        public double percentChange1h;
        @SerializedName("percent_change_24h")
        @Expose
        public double percentChange24h;
        @SerializedName("percent_change_7d")
        @Expose
        public double percentChange7d;
    }
}
