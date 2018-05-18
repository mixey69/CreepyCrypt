package com.software.m.m.creppycrypt.di;

import com.software.m.m.creppycrypt.presenter.MainPresenter;
import com.software.m.m.creppycrypt.view.CurrencyInfoFragment;
import com.software.m.m.creppycrypt.view.MainActivity;
import com.software.m.m.creppycrypt.vm.CurrencyItemViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(MainPresenter presenter);
    void inject(MainActivity mainActivity);
    void inject(CurrencyItemViewModel currencyItemViewModel);
    void inject(CurrencyInfoFragment currencyInfoFragment);
}