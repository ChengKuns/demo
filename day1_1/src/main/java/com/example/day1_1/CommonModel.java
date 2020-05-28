package com.example.day1_1;



public class CommonModel implements ICommonModel {
    NetManger mManger = NetManger.getInstance();
    @Override
    public void getDate(IcommonPresenter icommonPresenter, int apiType, Object[] bean) {
        int login = (int) bean[0];
        int page = (int) bean[1];
        mManger.netWork(mManger.getService().getDate(page),icommonPresenter,apiType,login);
    }
}
