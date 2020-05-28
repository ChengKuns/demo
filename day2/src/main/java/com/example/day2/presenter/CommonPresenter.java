package com.example.day2.presenter;

import com.example.day2.model.ICommonModel;
import com.example.day2.view.ICommonView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class CommonPresenter <V extends ICommonView,M extends ICommonModel>implements ICommonPresenter {
  private SoftReference<V> mView;
  private SoftReference<M> model;
  private ArrayList<Disposable> mDisposables;
    public CommonPresenter( V mView, M model) {
        mDisposables=new ArrayList<>();
        this.mView = new SoftReference<>(mView);
        this.model = new SoftReference<>(model);
    }

    @Override
    public void getDate(int apiType, Object... bean) {
        if(model!=null&&model.get()!=null)model.get().getDate(this,apiType,bean);
    }



    @Override
    public void onSuccess(int apiType, int mold, Object... bean) {
        if(mView!=null&&mView.get()!=null)mView.get().onSuccess(apiType,mold,bean);
    }

    @Override
    public void onFile(int apiType, Throwable throwable) {
        if(mView!=null&&mView.get()!=null)mView.get().onFile(apiType,throwable);
    }

    @Override
    public void addObserver(Disposable disposable) {
        if(mDisposables==null)return;
        mDisposables.add(disposable);
    }
    public void clear(){
            for (Disposable dis:mDisposables){
                if(dis!=null&&!dis.isDisposed()){
                    dis.dispose();
                }
            }
        if(mView!=null){
            mView.clear();
            mView=null;
        }if(model!=null){
            model.clear();
            model=null;
        }
    }
}
