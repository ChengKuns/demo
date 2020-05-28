package com.example.day2.view;

public interface ICommonView<V> {
    void onSuccess(int apiType,int mold,V...bean);
    void onFile(int apiType,Throwable throwable);

}
