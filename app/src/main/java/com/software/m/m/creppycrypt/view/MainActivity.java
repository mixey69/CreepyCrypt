package com.software.m.m.creppycrypt.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.software.m.m.creppycrypt.CreepyApplication;
import com.software.m.m.creppycrypt.R;
import com.software.m.m.creppycrypt.databinding.ActivityMainBinding;
import com.software.m.m.creppycrypt.model.Currency;
import com.software.m.m.creppycrypt.presenter.PresenterInterface;
import com.software.m.m.creppycrypt.utils.GridSpacingItemDecoration;
import com.software.m.m.creppycrypt.vm.MainViewModel;

import java.util.List;

import javax.inject.Inject;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    @Inject
    PresenterInterface presenter;

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private ProgressDialog progress;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new MainViewModel();
        CreepyApplication.getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setCurrencyList(mainViewModel);
        binding.executePendingBindings();
        binding.search.onActionViewExpanded();
        binding.search.clearFocus();

        recyclerView = findViewById(R.id.list);
        int spacing = Math.round(16 * getResources().getDisplayMetrics().density);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ? 2 : 1, spacing, true));
    }

    @Override
    public void showCurrency(Currency currency) {
        new CurrencyInfoFragment().show(getSupportFragmentManager(), "INFO");
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
        presenter.getData();
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainViewModel.onQueryTextChanged(newText);
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showWait() {
        if (progress != null) {
            progress.dismiss();
        }
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    public void showError(String message) {
        if (progress != null) {
            progress.dismiss();
        }
        if (binding != null) {
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayCurrencyList(List<Currency> currencyList) {
        if (progress != null) {
            progress.dismiss();
        }
        mainViewModel.init(currencyList);
        binding.setCurrencyList(mainViewModel);
    }

    @Override
    public void setSpinnerSelection(int pos) {
        binding.order.setSelection(pos, false);
    }

    @Override
    public void redrawList() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
