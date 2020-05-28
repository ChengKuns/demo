package com.example.day2.model;


import com.example.day2.NetManger;
import com.example.day2.presenter.ICommonPresenter;

public class CommonModel implements ICommonModel {
    NetManger mManger = NetManger.getInstance();
    @Override
    public void getDate(ICommonPresenter icommonPresenter, int apiType, Object[] bean) {
        int login = (int) bean[0];
        int page = (int) bean[1];
        mManger.netWork(mManger.getService().getDate(page),icommonPresenter,apiType,login);
    }
}

