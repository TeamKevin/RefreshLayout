package com.kevin.refresh.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.refresh.R;
import com.kevin.refresh.adapter.BaseRecyclerAdapter;

/**
 * Created by Lenovo on 2018/12/17.
 */

public class RefreshRecyclerView extends RelativeLayout implements View.OnClickListener {
    private OnLoadMoreListener onLoadMoreListener;
    private View mLoadLayout, mEndLayout, mFooterView;
    private DataErrorLayout mErrorLayout;
    private ProgressBar mProgress;
    private TextView mLoadView;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mPageSize = 10;
    //是否是加载更多
    private boolean isLoadMore = false;

    public OnLoadMoreListener getOnLoadMoreListener() {
        return onLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.view_refresh_view, this);
        mFooterView = View.inflate(context, R.layout.view_footer, null);
        mRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        // 设置刷新控件颜色
        mRefreshLayout.setColorSchemeColors(Color.parseColor("#0082FE"));
        mErrorLayout = view.findViewById(R.id.data_error_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLoadLayout = mFooterView.findViewById(R.id.load_layout);
        mEndLayout = mFooterView.findViewById(R.id.end_layout);
        mProgress = mFooterView.findViewById(R.id.pb_loading);
        mLoadView = mFooterView.findViewById(R.id.tv_loading);
        mLoadLayout.setOnClickListener(this);
    }

    /**
     * 获得当前RecyclerView对象
     * @return
     */
    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    /**
     * 设置布局管理器
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 绑定适配器
     *
     * @param adapter
     */
    public void setAdapter(BaseRecyclerAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        adapter.addFooterView(mFooterView);
    }

    /**
     * 设置刷新的监听回调
     *
     * @param listener
     */
    public void setOnRefreshListener(@Nullable SwipeRefreshLayout.OnRefreshListener listener) {
        mRefreshLayout.setOnRefreshListener(listener);
    }

    /**
     * 设置重新加载的监听
     *
     * @param onReloadListener
     */
    public void setReloadListener(DataErrorLayout.OnReloadListener onReloadListener) {
        mErrorLayout.setOnReloadListener(onReloadListener);
    }

    /**
     * 显示网络异常状态
     */
    public void showLoadError() {
        mErrorLayout.showError();
        mRefreshLayout.setRefreshing(false);
        mLoadLayout.setVisibility(GONE);
        mEndLayout.setVisibility(GONE);
    }

    /**
     * 刷新完成
     */
    public void setRefreshComplete() {
        int itemCount = mLayoutManager.getItemCount() - 1;
        if (itemCount == 0) {
            mLoadLayout.setVisibility(GONE);
            mEndLayout.setVisibility(GONE);
            mErrorLayout.showEmpty();
            return;
        }else{
            mErrorLayout.showContent();
            if (itemCount % mPageSize != 0) {
                mLoadLayout.setVisibility(GONE);
                mEndLayout.setVisibility(VISIBLE);
            } else {
                mLoadLayout.setVisibility(VISIBLE);
                mEndLayout.setVisibility(GONE);
            }
        }
        if (!isLoadMore) {
            mRefreshLayout.setRefreshing(false);
        } else {
            mProgress.setVisibility(GONE);
            mLoadView.setText(R.string.load_more);
            mLoadLayout.setClickable(true);
            isLoadMore = false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.load_layout:
                if (onLoadMoreListener != null) {
                    mProgress.setVisibility(VISIBLE);
                    mLoadView.setText(R.string.now_loading);
                    mLoadLayout.setClickable(false);
                    isLoadMore = true;
                    onLoadMoreListener.onLoadMore();
                }
                break;
        }
    }


    public interface OnLoadMoreListener {
        /**
         * 加载更多
         */
        void onLoadMore();
    }
}
