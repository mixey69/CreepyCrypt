package com.software.m.m.creppycrypt;

import android.app.Application;

import com.software.m.m.creppycrypt.di.AppComponent;
import com.software.m.m.creppycrypt.di.DaggerAppComponent;


public class CreepyApplication extends Application {
    //new comment

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
