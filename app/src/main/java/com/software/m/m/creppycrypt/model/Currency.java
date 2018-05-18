package com.software.m.m.creppycrypt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.software.m.m.creppycrypt.vm.CurrencyItemViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
    @SerializedName("rank")
    @Expose
    public int rank;
    @SerializedName("price_usd")
    @Expose
    public double priceUsd;
    @SerializedName("price_btc")
    @Expose
    public double priceBtc;
    @SerializedName("24h_volume_usd")
    @Expose
    public double _24hVolumeUsd;
    @SerializedName("market_cap_usd")
    @Expose
    public double marketCapUsd;
    @SerializedName("available_supply")
    @Expose
    public double availableSupply;
    @SerializedName("total_supply")
    @Expose
    public double totalSupply;
    @SerializedName("max_supply")
    @Expose
    public double maxSupply;
    @SerializedName("percent_change_1h")
    @Expose
    public double percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    public double percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    public double percentChange7d;
    @SerializedName("last_updated")
    @Expose
    public long lastUpdated;

    public String dateFromLastUpdated;

    public void updateDate() {
        Date date = new Date(lastUpdated * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        dateFromLastUpdated = sdf.format(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Currency) {
            return symbol.equals(((Currency) obj).symbol) && lastUpdated == ((Currency) obj).lastUpdated;
        } else if (obj instanceof CurrencyItemViewModel) {
            return symbol.equals(((CurrencyItemViewModel) obj).currency.symbol) && lastUpdated == ((CurrencyItemViewModel) obj).currency.lastUpdated;
        }else {
            return false;
        }
    }
}
