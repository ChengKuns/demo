package com.example.day1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonModel implements IcommonModel {
    public void getDate(IcommonPresenter mp,int whichApi, Object... pFs) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiSerVice.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiSerVice apiSerVice = build.create(ApiSerVice.class);
        Observable<ProjiectBean> observable = apiSerVice.getDate();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjiectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjiectBean projiectBean) {
                        mp.onSuccess(whichApi,1,projiectBean);
                    }


                    @Override
                    public void onError(Throwable e) {
                        mp.onFile(whichApi,e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
