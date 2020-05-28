package com.example.day1_1;

import io.reactivex.disposables.Disposable;

public interface IcommonPresenter<P> extends IcommonView {
    void getDate(int apiType,P...bean);
    void addObserver(Disposable disposable);
}
