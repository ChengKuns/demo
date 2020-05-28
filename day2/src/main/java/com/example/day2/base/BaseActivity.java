package com.example.day2.base;


import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day2.Type;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initRecRecyclerView(RecyclerView rlv, SmartRefreshLayout sml,DisModel model){
        if(rlv!=null){
            rlv.setLayoutManager(new LinearLayoutManager(this));
            rlv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
            if(sml!=null&&model!=null){
                sml.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                        model.disType(Type.LOADMORE);
                    }

                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        model.disType(Type.REFRESH);
                    }
                });
            }
        }

    }
    public void showLog(Object content){
        Log.i("饕",content.toString());
    }
    public void showToast(Object content){
        Toast.makeText(this, ""+content.toString(), Toast.LENGTH_SHORT).show();
    }
}
