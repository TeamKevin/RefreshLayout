package com.kevin.refresh.adapter;

/**
 * Created by fashion on 2017/7/7.
 * 通用的ViewHolder
 */

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> arrayView;
    View currentView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        currentView = itemView;
        arrayView = new SparseArray<>();
    }

    /**
     * 通过填写的itemId来获取具体的View的对象
     *
     * @param itemId R.id.***
     * @param <T>    必须是View的子类
     * @return
     */
    public <T extends View> T getView(int itemId) {
        //arrayVie类似于Map容器，get(key)取出的是value值
        View mView = arrayView.get(itemId);
        if (mView == null) {
            //实例化具体的View类型
            mView = itemView.findViewById(itemId);
            arrayView.put(itemId, mView);
        }
        return (T) mView;
    }

    /**
     * 设置TextView的内容
     *
     * @param itemId
     * @param text
     */
    public void setText(int itemId, CharSequence text) {
        TextView tv = getView(itemId);
        tv.setText(text);
    }

    /**
     * 设置TextView的内容
     *
     * @param itemId
     * @param resourceId
     */
    public void setText(int itemId, int resourceId) {
        TextView tv = getView(itemId);
        tv.setText(resourceId);
    }

    /**
     * 设置图片
     *
     * @param itemId
     * @param imageId
     */
    public void setBitmapImage(int itemId, int imageId) {
        ImageView iv = getView(itemId);
        iv.setImageResource(imageId);
    }

    /**
     * 获得当前布局对象
     * @return
     */
    public View getCurrentView() {
        return currentView;
    }

    /**
     * 给当前项设置点击监听
     */
    public void setOnViewItemClickListener() {
        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
