package com.example.day1_1.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day1_1.CommonPresenter;
import com.example.day1_1.ICommonModel;
import com.example.day1_1.IcommonView;


import butterknife.ButterKnife;

public abstract  class BaseMvpActivity <M extends ICommonModel> extends BaseActivity implements IcommonView {
    public M model;
    public CommonPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        model=setModel();
        mPresenter = new CommonPresenter(this,model);
        initUpView();
        initUpDate();
    }

    protected abstract void initUpDate();

    protected abstract void initUpView();

    protected abstract M setModel();

    protected abstract int setLayout();
    public abstract void netSuccess(int apiType, int login, Object[] bean);
    public void netFile(int apiType, Throwable throwable){}
    @Override
    public void onSuccess(int apiType, int login, Object[] bean) {
        netSuccess(apiType,login,bean);
    }

    @Override
    public void onFile(int apiType, Throwable throwable) {
        showLog("net work error: "+apiType+"error content"+ throwable!=
                null && !TextUtils.isEmpty(throwable.getMessage()) ? throwable.getMessage() : "不明错误类型");
        netFile(apiType,throwable);
    }

    @Override
    protected void onDestroy() {
        mPresenter.clear();
        super.onDestroy();
    }
}
