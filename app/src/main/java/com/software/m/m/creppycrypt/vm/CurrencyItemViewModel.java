package com.software.m.m.creppycrypt.vm;

import android.view.View;

import com.software.m.m.creppycrypt.CreepyApplication;
import com.software.m.m.creppycrypt.model.Currency;
import com.software.m.m.creppycrypt.presenter.PresenterInterface;

import javax.inject.Inject;

public class CurrencyItemViewModel {
    @Inject
    PresenterInterface presenter;
    public Currency currency;

    public CurrencyItemViewModel() {
        super();
        CreepyApplication.getComponent().inject(this);
    }
    public void onClick(View v){
        presenter.showCurrency(currency);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CurrencyItemViewModel){
            return currency.symbol.equals(((CurrencyItemViewModel) obj).currency.symbol) && currency.lastUpdated == ((CurrencyItemViewModel) obj).currency.lastUpdated;
        }else if(obj instanceof Currency){
            return currency.symbol.equals(((Currency) obj).symbol) && currency.lastUpdated == ((Currency) obj).lastUpdated;
        }else {
            return false;
        }
    }
}
