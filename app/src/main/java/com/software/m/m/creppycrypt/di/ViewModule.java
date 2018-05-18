package com.software.m.m.creppycrypt.di;

import com.software.m.m.creppycrypt.presenter.MainPresenter;
import com.software.m.m.creppycrypt.presenter.PresenterInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    @Provides
    @Singleton
    PresenterInterface provideMainPresenter() {
        return new MainPresenter();
    }
}
