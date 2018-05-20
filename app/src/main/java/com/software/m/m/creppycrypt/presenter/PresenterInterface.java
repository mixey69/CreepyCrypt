package com.software.m.m.creppycrypt.presenter;

import com.software.m.m.creppycrypt.model.Currency;
import com.software.m.m.creppycrypt.view.MainActivityInterface;

import java.util.List;

public interface PresenterInterface {
    int ORDER_BY_RANK = 0;
    int ORDER_BY_CAP = 1;
    int ORDER_BY_VOL = 2;
    int ORDER_BY_CHANGE = 3;
    void updateData(List<Currency> data);
    void onStart(MainActivityInterface viewI);
    void onStop();
    void getData();
    void showError(String message);
    void showCurrency(Currency currency);
    void setDisplayOrder(int displayOrder);
    int getDisplayOrder();
    void showDisplayOrder();
    Currency getDisplayedCurrency();
}
