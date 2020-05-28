package com.example.day1;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IcommonView {

    private RecyclerView rlv;
    private  ArrayList<ProjiectBean.DataBean> beans = new ArrayList<>();
    private Adapter adapter;
    private CommonPresenters commonPresenters;
    private IcommonModel commonModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commonModel = new CommonModel();
        commonPresenters = new CommonPresenters(this,commonModel);
        commonPresenters.getDate(1);
        rlv = (RecyclerView) findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        adapter = new Adapter(this, beans);
        rlv.setAdapter(adapter);

    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        List<ProjiectBean.DataBean> beans = ((ProjiectBean) pD[0]).data;
         this.beans.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFile(int whichApi, Throwable throwable) {
        Toast.makeText(this, throwable.getMessage()!=null?throwable.getMessage():"网络请求发生错误", Toast.LENGTH_SHORT).show();
    }




}
