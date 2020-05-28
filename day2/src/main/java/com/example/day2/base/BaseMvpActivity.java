package com.example.day2.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import com.example.day2.model.ICommonModel;
import com.example.day2.presenter.CommonPresenter;
import com.example.day2.view.ICommonView;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity <V extends ICommonModel> extends BaseActivity implements ICommonView {
    public V model;
    public CommonPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        model=setModel();
        mPresenter=new CommonPresenter(this,model);
        initUpView();
        initUpDate();
    }

    protected abstract void initUpDate();

    protected abstract void initUpView();

    protected abstract V setModel();

    protected abstract int setLayout();

    public abstract void netSuccess(int apiType, int mold, Object[] bean);

    public void netFile( int apiType, Throwable throwable){

    }
    @Override
    public void onSuccess(int apiType, int mold, Object[] bean) {
        netSuccess(apiType,mold,bean);
    }

    @Override
    public void onFile(int apiType, Throwable throwable) {
        showLog("net work error: "+apiType+"error content"+ throwable != null &&
                !TextUtils.isEmpty(throwable.getMessage()) ? throwable.getMessage() : "不明错误类型");
        netFile(apiType,throwable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }
}
