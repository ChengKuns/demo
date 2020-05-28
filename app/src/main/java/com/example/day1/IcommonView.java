package com.example.day1;

public interface IcommonView<D>{
    void onSuccess(int whichApi,int loadType,D... pD);
    void onFile(int whichApi,Throwable throwable);
}
