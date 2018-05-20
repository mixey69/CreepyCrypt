package com.software.m.m.creppycrypt.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.m.m.creppycrypt.presenter.PresenterInterface;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModelImpl implements ModelInterface {

    private List<Currency> currencies;

    @Override
    public void getData(PresenterInterface presenter) {
        Observable.fromCallable(new Callable<Response>() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.coinmarketcap.com/v1/ticker/")
                    .build();

            @Override
            public Response call() throws Exception {
                return client.newCall(request).execute();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        try {
                            String s = response.body().string();
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<Currency>>() {
                            }.getType();
                            List<Currency> currencies = gson.fromJson(s, listType);
                            for (Currency c : currencies) {
                                c.updateDate();
                            }
                            Collections.sort(currencies, (o1, o2) -> o1.rank - o2.rank);
                            ModelImpl.this.currencies = currencies;
                            presenter.updateData(currencies);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean hasData() {
        return currencies != null;
    }

    @Override
    public List<Currency> getLoadedData() {
        return currencies;
    }
}
