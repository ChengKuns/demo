package com.example.day2.presenter;

import com.example.day2.view.ICommonView;

import io.reactivex.disposables.Disposable;

public interface ICommonPresenter<P> extends ICommonView {
    void getDate(int apiType,P...bean);
    void addObserver(Disposable disposable);
}
