package com.software.m.m.creppycrypt.di;

import com.software.m.m.creppycrypt.model.ModelImpl;
import com.software.m.m.creppycrypt.model.ModelInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @Singleton
    ModelInterface provideDataRepository() {
        return new ModelImpl();
    }
}
