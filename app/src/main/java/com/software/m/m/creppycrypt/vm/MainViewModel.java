package com.software.m.m.creppycrypt.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.software.m.m.creppycrypt.BR;
import com.software.m.m.creppycrypt.CreepyApplication;
import com.software.m.m.creppycrypt.R;
import com.software.m.m.creppycrypt.model.Currency;
import com.software.m.m.creppycrypt.presenter.PresenterInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

import static com.software.m.m.creppycrypt.presenter.PresenterInterface.ORDER_BY_CAP;
import static com.software.m.m.creppycrypt.presenter.PresenterInterface.ORDER_BY_CHANGE;
import static com.software.m.m.creppycrypt.presenter.PresenterInterface.ORDER_BY_RANK;
import static com.software.m.m.creppycrypt.presenter.PresenterInterface.ORDER_BY_VOL;

public class MainViewModel {

    @Inject
    PresenterInterface presenter;
    private List<Currency> currencyList;
    private int displayOrder = ORDER_BY_RANK;
    private String searchQuery = "";
    public String[] sortBy = {"Rank", "Cap.", "Volume 24h", "Change 24h"};
    public ObservableList<CurrencyItemViewModel> currencyItemViewModels = new ObservableArrayList<>();
    public ItemBinding<CurrencyItemViewModel> currencyItemBinding = ItemBinding.of(BR.item, R.layout.currency_list_item);

    public MainViewModel() {
        super();
        CreepyApplication.getComponent().inject(this);
    }

    public void init(List<Currency> currencies) {
        if (currencyList != null) {
            if (currencyList.equals(currencies) && currencyItemViewModels.equals(currencies)) {
                return;
            }
        } else {
            currencyList = currencies;
        }
        updateList(currencies);
        if (!presenter.getSearchQuery().equals(searchQuery)) {
            onQueryTextChanged(presenter.getSearchQuery());
        }
    }

    private void updateList(List<Currency> currencies) {
        currencyItemViewModels.clear();
        for (Currency c : currencies) {
            CurrencyItemViewModel vm = new CurrencyItemViewModel();
            vm.currency = c;
            currencyItemViewModels.add(vm);
        }
        if (displayOrder != presenter.getDisplayOrder()) {
            presenter.showDisplayOrder();
        } else {
            onSpinnerItemSelected(displayOrder);
        }
    }

    public void onSpinnerItemSelected(int pos) {
        switch (pos) {
            case 0:
                Collections.sort(currencyItemViewModels, (o1, o2) -> o1.currency.rank - o2.currency.rank);
                displayOrder = ORDER_BY_RANK;
                break;
            case 1:
                Collections.sort(currencyItemViewModels, (o1, o2) -> (int) (o2.currency.quote.priceUsd.marketCapUsd - o1.currency.quote.priceUsd.marketCapUsd));
                displayOrder = ORDER_BY_CAP;
                break;
            case 2:
                Collections.sort(currencyItemViewModels, (o1, o2) -> (int) (o2.currency.quote.priceUsd._24hVolumeUsd - o1.currency.quote.priceUsd._24hVolumeUsd));
                displayOrder = ORDER_BY_VOL;
                break;
            case 3:
                Collections.sort(currencyItemViewModels, (o1, o2) -> (int) (o2.currency.quote.priceUsd.percentChange24h - o1.currency.quote.priceUsd.percentChange24h));
                displayOrder = ORDER_BY_CHANGE;
                break;
        }
        presenter.setDisplayOrder(displayOrder);
    }

    public void onQueryTextChanged(String text) {
        searchQuery = text;
        presenter.setSearchQuery(searchQuery);
        if (currencyList != null) {
            if (text.equals("")) {
                updateList(currencyList);
            } else {
                List<Currency> filteredList = new ArrayList<>();
                for (Currency c : currencyList) {
                    if (c.name.toLowerCase().contains(text.toLowerCase()) || c.symbol.contains(text.toUpperCase())) {
                        filteredList.add(c);
                    }
                }
                updateList(filteredList);
            }
        }
    }
}
