package com.example.day1;

public interface IcommonPresenter<P> extends IcommonView {
    void getDate(int whichApi,P...pFs);
}
