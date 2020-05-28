package com.example.day2;

import com.example.day2.base.BaseObserver;
import com.example.day2.presenter.ICommonPresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetManger {
    private NetManger() {
    }

    private static volatile NetManger sNetManger;

    public static NetManger getInstance() {
        if (sNetManger == null) {
            synchronized (NetManger.class) {
                sNetManger = new NetManger();
            }
        }
        return sNetManger;
    }

    public <T> ApiSerVice getService(T... t) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .client(new OkHttpClient())
                .build()
                .create(ApiSerVice.class);
    }

    public <T, O> void netWork(Observable<T> localTestInfo, final ICommonPresenter pPresenter, final int whichApi, final int dataType, O... o) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        pPresenter.addObserver(d);
                    }

                    @Override
                    public void onSuccess(Object object) {
                        pPresenter.onSuccess(whichApi,dataType,object,o);
                    }

                    @Override
                    public void onFile(Throwable throwable) {
                        pPresenter.onFile(whichApi,throwable);
                    }
                });

    }
}
