/*
 * Copyright 2016 czy1121
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kevin.refresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.kevin.refresh.R;


/**
 * 列表数据异常（空数据、断网）
 */
public class DataErrorLayout extends FrameLayout implements View.OnClickListener {
    private View mEmptyLayout, mErrorLayout, mLoadingLayout;
    private OnReloadListener onReloadListener;

    public static final int EMPTY_STATE = 1;        //空数据
    public static final int ERROR_STATE = 2;        //断网
    public static final int LOADING_STATE = 3;      //加载中
    public static final int NORMAL_STATE = 4;       //正常
    private int mState = NORMAL_STATE;              //当前状态

    public OnReloadListener getOnReloadListener() {
        return onReloadListener;
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    public DataErrorLayout(Context context) {
        this(context, null);
        init(context);
    }

    public DataErrorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public DataErrorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.view_data_error, this);
        mEmptyLayout = view.findViewById(R.id.empty_layout);
        mErrorLayout = view.findViewById(R.id.error_layout);
        mLoadingLayout = view.findViewById(R.id.loading_layout);
        view.findViewById(R.id.btn_retry).setOnClickListener(this);
    }

    /**
     * 显示空数据
     */
    public void showEmpty() {
        if (mState != EMPTY_STATE) {
            mEmptyLayout.setVisibility(VISIBLE);
            mErrorLayout.setVisibility(GONE);
            mLoadingLayout.setVisibility(GONE);
            mState = EMPTY_STATE;
        }

    }

    /**
     * 显示网络异常
     */
    public void showError() {
        if (mState != ERROR_STATE) {
            mEmptyLayout.setVisibility(GONE);
            mErrorLayout.setVisibility(VISIBLE);
            mLoadingLayout.setVisibility(GONE);
            mState = ERROR_STATE;
        }
    }

    /**
     * 显示加载中
     */
    public void showLoading() {
        if (mState != LOADING_STATE) {
            mEmptyLayout.setVisibility(GONE);
            mErrorLayout.setVisibility(GONE);
            mLoadingLayout.setVisibility(VISIBLE);
            mState = LOADING_STATE;
        }
    }

    /**
     * 显示数据内容
     */
    public void showContent() {
        if (mState != NORMAL_STATE) {
            mEmptyLayout.setVisibility(GONE);
            mErrorLayout.setVisibility(GONE);
            mLoadingLayout.setVisibility(GONE);
            mState = NORMAL_STATE;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retry:
                if (onReloadListener != null) {
                    showLoading();
                    onReloadListener.onReload();
                }
                break;
        }
    }

    public interface OnReloadListener {
        /**
         * 重新加载数据
         */
        void onReload();
    }
}
