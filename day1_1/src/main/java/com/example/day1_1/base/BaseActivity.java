package com.example.day1_1.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day1_1.CommonPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout
            , DataListener pDataListener){
        if(pRecyclerView!=null){
            pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            pRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        }if(pRefreshLayout!=null){
            pRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    if(pDataListener!=null)pDataListener.dataType(1);
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    if(pDataListener!=null)pDataListener.dataType(2);
                }
            });
        }
    }
    public void showLog(Object content){
        Log.e("睚眦", content.toString() );
    }

    public void showToast(Object content){
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }
}
