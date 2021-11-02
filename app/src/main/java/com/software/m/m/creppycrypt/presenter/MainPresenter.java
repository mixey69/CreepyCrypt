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
    private MainActivityInterface view;
    private Currency displayedCurrency;
    private int displayOrder;
    private String searchQuery = "";

    public MainPresenter() {
        super();
        CreepyApplication.getComponent().inject(this);
    }

    @Override
    public void onStart(MainActivityInterface view) {
        this.view = view;
    }

    @Override
    public void getData() {
        if(model.hasData()){
            view.displayCurrencyList(model.getLoadedData());
        }else {
            view.showWait();
            model.getData(this);
        }
    }

    @Override
    public void showCurrency(Currency currency) {
        displayedCurrency = currency;
        view.showCurrency(currency);
    }

    @Override
    public Currency getDisplayedCurrency() {
        return displayedCurrency;
    }

    @Override
    public void onStop() {
        view = null;
    }

    @Override
    public void updateData(List<Currency> data) {
        view.displayCurrencyList(data);
    }

    @Override
    public void showError(String message) {
        view.showError(message);
    }

    @Override
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        view.redrawList();
    }

    @Override
    public int getDisplayOrder() {
        return displayOrder;
    }

    @Override
    public void showDisplayOrder() {
        view.setSpinnerSelection(displayOrder);
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
