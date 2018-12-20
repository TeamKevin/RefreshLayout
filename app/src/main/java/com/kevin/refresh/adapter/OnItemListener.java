package com.kevin.refresh.adapter;

import android.view.View;

/**
 * @date: 2018/1/9
 * @author: Chen
 * @description:
 * @version:
 */

public interface OnItemListener {
    /**
     * 每一项的点击事件监听
     * @param position
     * @param view
     */
    void onItemClick(int position, View view);
}
