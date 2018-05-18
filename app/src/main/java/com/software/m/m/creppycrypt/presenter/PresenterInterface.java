package com.software.m.m.creppycrypt.presenter;

import com.software.m.m.creppycrypt.model.Currency;
import com.software.m.m.creppycrypt.view.MainActivityInterface;

import java.util.List;

public interface PresenterInterface {
    void updateData(List<Currency> data);
    void onStart(MainActivityInterface viewI);
    void onStop();
    void getData();
    void showError(String message);
    void showCurrency(Currency currency);
    Currency getDisplayedCurrency();
}
