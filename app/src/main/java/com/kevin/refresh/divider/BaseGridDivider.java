package com.kevin.refresh.divider;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @date: 2018/1/9
 * @author: Chen
 * @description: RecyclerView的表格的间距（边线颜色由RecyclerView的背景色控制）
 * @version:
 */

public class BaseGridDivider extends RecyclerView.ItemDecoration {

    private int mSpace;      //间距

    private int mColumn;     //列数

    public BaseGridDivider(int space, int number) {
        this.mSpace = space;
        this.mColumn = number;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = mSpace;
        outRect.bottom = mSpace;
        //由于每行都只有number个，所以第一个都是number的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % mColumn == 0) {
            outRect.left = 0;
        }
    }

}
