package com.software.m.m.creppycrypt.presenter;

import com.software.m.m.creppycrypt.CreepyApplication;
import com.software.m.m.creppycrypt.model.Currency;
import com.software.m.m.creppycrypt.model.ModelInterface;
import com.software.m.m.creppycrypt.view.MainActivityInterface;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements PresenterInterface {
    @Inject
    ModelInterface model;
    private MainActivityInterface viewI;
    private Currency displayedCurrency;
    private int displayOrder;
    private String searchQuery = "";

    public MainPresenter() {
        super();
        CreepyApplication.getComponent().inject(this);
    }

    @Override
    public void onStart(MainActivityInterface viewI) {
        this.viewI = viewI;
    }

    @Override
    public void getData() {
        if(model.hasData()){
            viewI.displayCurrencyList(model.getLoadedData());
        }else {
            viewI.showWait();
            model.getData(this);
        }
    }

    @Override
    public void showCurrency(Currency currency) {
        displayedCurrency = currency;
        viewI.showCurrency(currency);
    }

    @Override
    public Currency getDisplayedCurrency() {
        return displayedCurrency;
    }

    @Override
    public void onStop() {
        viewI = null;
    }

    @Override
    public void updateData(List<Currency> data) {
        viewI.displayCurrencyList(data);
    }

    @Override
    public void showError(String message) {
        viewI.showError(message);
    }

    @Override
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public int getDisplayOrder() {
        return displayOrder;
    }

    @Override
    public void showDisplayOrder() {
        viewI.setSpinnerSelection(displayOrder);
    }

    @Override
    public void setSearchQuery(String query) {
            searchQuery = query;
    }

    @Override
    public String getSearchQuery() {
        return searchQuery;
    }
}
