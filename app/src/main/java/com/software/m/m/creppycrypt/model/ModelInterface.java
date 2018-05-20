package com.software.m.m.creppycrypt.model;

import com.software.m.m.creppycrypt.presenter.PresenterInterface;

import java.util.List;

public interface ModelInterface {
    void getData(PresenterInterface presenter);
    boolean hasData();
    List<Currency> getLoadedData();
}
