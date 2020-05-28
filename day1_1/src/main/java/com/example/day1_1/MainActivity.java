package com.example.day1_1;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day1_1.base.BaseMvpActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {


    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private ArrayList<ProjiectBean.DataBean> list = new ArrayList<>();
    private Adapter adapter;
    private int page = 1;
    @Override
    protected void initUpDate() {
        initRecyclerView(rlv,sml,model1 -> {
            if(model1==1){
                page++;
                mPresenter.getDate(100,1,page);
            }if(model1==2){
                page=1;
                mPresenter.getDate(100,2,page);
            }
        });
        adapter = new Adapter(this, list);
        rlv.setAdapter(adapter);
    }

    @Override
    protected void initUpView() {
        mPresenter.getDate(100, 0, page);
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
    public void netSuccess(int apiType, int login, Object[] bean) {
        if (login == 1) {
            sml.finishLoadMore();
        }
        if (login == 2) {
            sml.finishRefresh();
            list.clear();
        }
        List<ProjiectBean.DataBean> beans = ((ProjiectBean) bean[0]).data;
        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }
}
