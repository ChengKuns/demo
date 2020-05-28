package com.example.day2.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver implements Observer {
    private Disposable disposable;
    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(Object o) {
        onSuccess(o);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        onFile(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }
    public abstract void onSuccess(Object object);
    public abstract void onFile(Throwable throwable);
    public void dispose(){
        if(disposable!=null&&!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
