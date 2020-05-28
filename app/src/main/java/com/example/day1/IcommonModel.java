package com.example.day1;

public interface IcommonModel<T> {
    void getDate(IcommonPresenter presenter,int whichApi,T...params);
}
