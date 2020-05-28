package com.example.day2.model;

import com.example.day2.presenter.ICommonPresenter;

public interface ICommonModel <M >{
    void getDate(ICommonPresenter mPresenter,int apiType,M...bean);
}
