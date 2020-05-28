package com.example.day1_1;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CommonPresenter <V extends IcommonView,M extends ICommonModel>implements IcommonPresenter {
    private SoftReference<V> mView;
    private SoftReference<M > model;
    private List<Disposable> mDisposable;
    public CommonPresenter(V mView, M model) {
        mDisposable=new ArrayList<>();
        this.mView = new SoftReference<>(mView);
        this.model = new SoftReference<>(model);
    }

    @Override
    public void getDate(int apiType, Object...bean) {
       if(model!=null&&model.get()!=null)model.get().getDate(this,apiType,bean);
    }

    @Override
    public void onSuccess(int apiType, int login, Object... bean) {
        if(mView!=null&&mView.get()!=null)mView.get().onSuccess(apiType,login,bean);
    }

    @Override
    public void onFile(int apiType, Throwable throwable) {
        if(mView!=null&&mView.get()!=null)mView.get().onFile(apiType,throwable);
    }
   //将所有网络请求的订阅关系，统一到中间层的集合中，用于view销毁时，统一处理
    public void addObserver(Disposable disposable){
        if(mDisposable==null) return;
        mDisposable.add(disposable);
    }
    /**
     * 当activity页面销毁执行ondestroy时，这个时候已经没有展示数据的需求了，所以首先要将在该页面进行的所有网络请求
     * 终止，以免gc回收时发现view仍被持有不能回收导致内存泄漏。当然这个即使不处理，这个泄漏的时间会很短暂，当gc线程
     * 下一次检测到该对象，网络任务如果已经完成，并不影响activity的回收
     *
     * 在MVP使用中，为了实现视图和数据的解耦，我们通过中间层presenter来进行双向绑定和处理，但是当view销毁时，由于P层仍然
     * 持有view的引用，也可能会发生view不能被回收导致内存泄漏的情况，所以当页面销毁时，将presenter同view和model进行解绑。
     */
    public void clear() {
        for (Disposable dis:mDisposable) {
            if (dis != null && !dis.isDisposed())dis.dispose();
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
