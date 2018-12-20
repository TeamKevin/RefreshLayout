package com.kevin.refresh.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;

import com.kevin.refresh.R;

import java.util.List;

/**
 * Created by Lenovo on 2018/12/20.
 */

public class DataAdapter extends BaseRecyclerAdapter {
    private OnItemListener onItemListener;

    public OnItemListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public DataAdapter(RecyclerView view, List list, int itemLayoutId) {
        super(view, list, itemLayoutId);
    }

    @Override
    public void bindConvert(RecyclerViewHolder holder, final int position, Object item, boolean isScrolling) {
        holder.setText(R.id.text,item.toString());

        holder.getCurrentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemListener!=null){
                    onItemListener.onItemClick(position,view);
                }
            }
        });
    }

}
