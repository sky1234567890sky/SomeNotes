package com.jiyun.geek.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by $lzj on 2019/5/17.
 */
public class SimpleItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private TouchCallBack touchCallBack;

    public SimpleItemTouchHelperCallBack(TouchCallBack touchCallBack) {
        this.touchCallBack = touchCallBack;
    }

    /**
     * 设置是否上下  左右拖动
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView
            .ViewHolder viewHolder) {

        int move = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe = ItemTouchHelper.LEFT;

        return makeMovementFlags(move,swipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
            viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {

        //调用传进来的Adapter的上下移动的方法
        touchCallBack.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        //调用传进来的Adapter的左右滑动的方法
        touchCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }
}
