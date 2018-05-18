package com.software.m.m.creppycrypt.view;

import com.software.m.m.creppycrypt.model.Currency;

import java.util.List;

public interface MainActivityInterface {
    void displayCurrencyList(List<Currency> currencyList);
    void showWait();
    void showError(String message);
    void showCurrency(Currency currency);
}
