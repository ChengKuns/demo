package com.example.day1;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CommonPresenters implements IcommonPresenter {
    private IcommonView mView;
    private IcommonModel model;


    public CommonPresenters(IcommonView mView, IcommonModel model) {

        this.mView = mView;
        this.model = model;
    }

    @Override
    public void getDate(int whichApi, Object... pFs) {
        model.getDate(this,whichApi,pFs);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        mView.onSuccess(whichApi,loadType,loadType,pD);
    }
     //将所有网络请求的订阅关系，统一到中间层的集合中，用于view销毁时，统一处理
    @Override
    public void onFile(int whichApi, Throwable throwable) {
        mView.onFile(whichApi,throwable);
    }
}
