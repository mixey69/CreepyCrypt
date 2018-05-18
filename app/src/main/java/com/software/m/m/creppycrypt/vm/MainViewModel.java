package com.software.m.m.creppycrypt.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.software.m.m.creppycrypt.BR;
import com.software.m.m.creppycrypt.R;
import com.software.m.m.creppycrypt.model.Currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class MainViewModel {

    private List<Currency> currencyList;
    public String[] sortBy = {"Rank", "Cap.", "Volume 24h", "Change 24h"};
    public ObservableList<CurrencyItemViewModel> currencyItemViewModels = new ObservableArrayList<>();
    public ItemBinding<CurrencyItemViewModel> currencyItemBinding = ItemBinding.of(BR.item, R.layout.currency_list_item);

    public void setCurrencies(List<Currency> currencies) {
        if(currencyList != null){
            if(currencyList.equals(currencies) && currencyItemViewModels.equals(currencies)){
                return;
            }
        }else {
            currencyList = currencies;
        }
        currencyItemViewModels.clear();
        for(Currency c : currencies){
            CurrencyItemViewModel vm = new CurrencyItemViewModel();
            vm.currency = c;
            currencyItemViewModels.add(vm);
        }
    }

    public void onSpinnerItemSelected(int pos){
        switch (pos){
            case 0 :
                Collections.sort(currencyItemViewModels, (o1, o2) -> o1.currency.rank - o2.currency.rank);
                break;
            case 1:
                Collections.sort(currencyItemViewModels, (o1, o2) -> (int)(o2.currency.marketCapUsd - o1.currency.marketCapUsd));
                break;
            case 2:
                Collections.sort(currencyItemViewModels, (o1, o2) -> (int)(o2.currency._24hVolumeUsd - o1.currency._24hVolumeUsd));
                break;
            case 3:
                Collections.sort(currencyItemViewModels, (o1, o2) -> (int)(o2.currency.percentChange24h - o1.currency.percentChange24h));
                break;
        }
    }

    public void onQueryTextChanged(String text){
        if(currencyList != null){
        if(text.equals("")){
            setCurrencies(currencyList);
        }else {
            List<Currency> filteredList = new ArrayList<>();
            for (Currency c : currencyList){
                if(c.name.toLowerCase().contains(text.toLowerCase()) || c.symbol.contains(text.toUpperCase())){
                    filteredList.add(c);
                }
            }
            setCurrencies(filteredList);
        }
        }
    }
}
