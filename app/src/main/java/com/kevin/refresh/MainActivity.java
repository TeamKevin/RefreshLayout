package com.kevin.refresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.kevin.refresh.adapter.DataAdapter;
import com.kevin.refresh.adapter.OnItemListener;
import com.kevin.refresh.divider.BaseDividerItemDecoration;
import com.kevin.refresh.widget.DataErrorLayout;
import com.kevin.refresh.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemListener, SwipeRefreshLayout.OnRefreshListener, DataErrorLayout.OnReloadListener, RefreshRecyclerView.OnLoadMoreListener {
    private RefreshRecyclerView mRefreshRecyclerView;
    private DataAdapter mAdapter;
    private List mList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefreshRecyclerView = findViewById(R.id.refreshRecyclerView);
        RecyclerView recyclerView = mRefreshRecyclerView.getRecyclerView();
        mAdapter = new DataAdapter(recyclerView, mList, R.layout.item_data);
        //添加分割线
        recyclerView.addItemDecoration(new BaseDividerItemDecoration(this, BaseDividerItemDecoration.VERTICAL_LIST, R.drawable.divider_gray_1));
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRefreshRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemListener(this);                       //没一项的监听
        mRefreshRecyclerView.setOnRefreshListener(this);        //下拉刷新的监听
        mRefreshRecyclerView.setOnLoadMoreListener(this);       //加载更多的监听
        mRefreshRecyclerView.setReloadListener(this);           //网络异常后重新加载
        //加载数据
        loadData();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        int start = mList.size();
        for (int i = start; i < start + 10; i++) {
            mList.add("数据：" + i);
        }
        //刷新完成后调用
        mRefreshRecyclerView.setRefreshComplete();
        //网络断开后调用，显示断网界面
//        mRefreshRecyclerView.showLoadError();
    }

    @Override
    public void onItemClick(int position, View view) {
        //每一项的点击事件
        Toast.makeText(this, mList.get(position).toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        //刷新
        mList.clear();
        loadData();
    }

    @Override
    public void onReload() {
        //重新加载
        mList.clear();
        loadData();
    }

    @Override
    public void onLoadMore() {
        //加载更多
        loadData();
    }
}
