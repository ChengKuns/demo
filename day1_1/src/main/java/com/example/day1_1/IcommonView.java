package com.example.day1_1;

public interface IcommonView <V>{
    void onSuccess(int apiType,int login,V...bean);
    void onFile(int apiType,Throwable throwable);


}
