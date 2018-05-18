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

    public static MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mainViewModel == null) {
            mainViewModel = new MainViewModel();
        }
        CreepyApplication.getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setCurrencyList(mainViewModel);
//        android:onQueryTextChange="@{(text) -> currencyList.onQueryTextChanged(text)}"
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
        binding.search.setQueryHint("Search");
        binding.search.onActionViewExpanded();
        binding.search.setIconified(false);
        binding.search.clearFocus();

        RecyclerView recyclerView = findViewById(R.id.list);
        int spacing = Math.round(16 * getResources().getDisplayMetrics().density);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ? 2 : 1, spacing, true));

//        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                binding.search.clearFocus();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
    }

    @Override
    public void showCurrency(Currency currency) {
        new CurrencyInfoFragment().show(getSupportFragmentManager(), "TAG");
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
        presenter.getData();
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
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    @Override
    public void showError(String message) {
        if (progress != null) {
            progress.dismiss();
        }
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void displayCurrencyList(List<Currency> currencyList) {
        if (progress != null) {
            progress.dismiss();
        }
        mainViewModel.setCurrencies(currencyList);
        binding.setCurrencyList(mainViewModel);
    }
}
