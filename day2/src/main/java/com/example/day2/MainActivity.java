package com.example.day2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day2.base.BaseMvpActivity;
import com.example.day2.model.CommonModel;
import com.example.day2.model.ICommonModel;
import com.example.day2.view.ICommonView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity  {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private ArrayList<ProjiectBean.DataBean> beans=new ArrayList<>();
    private int page=1;
    private Adapter adapter;

    @Override
    protected void initUpDate() {
        initRecRecyclerView(rlv,sml,model1 ->{
            if(model1==Type.LOADMORE){
            page++;
            mPresenter.getDate(100,Type.LOADMORE,page);
            }else {
                page=1;
                mPresenter.getDate(100,Type.REFRESH,page);
            }
        });
        adapter = new Adapter(this, beans);
        rlv.setAdapter(adapter);
    }

    @Override
    protected void initUpView() {
       mPresenter.getDate(100,Type.NORMAL,page);
    }

    @Override
    protected ICommonModel setModel() {
        return new CommonModel();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void netSuccess(int apiType, int mold, Object[] bean) {
       if(mold==Type.LOADMORE){
           sml.finishLoadMore();
       }if(mold==Type.REFRESH){
           sml.finishRefresh();
           beans.clone();
        }
        List<ProjiectBean.DataBean> list=((ProjiectBean)bean[0]).data;
        beans.addAll(list);
       adapter.notifyDataSetChanged();
    }


}
