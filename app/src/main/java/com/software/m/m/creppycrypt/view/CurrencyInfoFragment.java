package com.software.m.m.creppycrypt.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.m.m.creppycrypt.CreepyApplication;
import com.software.m.m.creppycrypt.databinding.FragmentCurrencyInfoBinding;
import com.software.m.m.creppycrypt.presenter.PresenterInterface;

import javax.inject.Inject;


public class CurrencyInfoFragment extends AppCompatDialogFragment {
    @Inject
    PresenterInterface presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCurrencyInfoBinding binding = FragmentCurrencyInfoBinding.inflate(inflater, container, false);
        CreepyApplication.getComponent().inject(this);
        binding.setItem(presenter.getDisplayedCurrency());
        return binding.getRoot();
    }
}
