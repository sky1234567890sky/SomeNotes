package com.example.a1.geeknewschr.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 1 on 2019/4/19.
 */

public class SimpleTouchHelperCallBack extends ItemTouchHelper.Callback {
    private TouchCallBack mTouchCallBack;
    private boolean mSwipeEnable = true;

    public SimpleTouchHelperCallBack(TouchCallBack pTouchCallBack) {
        this.mTouchCallBack = pTouchCallBack;
    }

    //可滑动方向
    /**
     *返回可以滑动的方向,一般使用makeMovementFlags
     * (int,int)或makeFlag(int, int)来构造我们的返回值
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //拖拽方向
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //滑动方向
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag,swipe);
    }

    //拖动回调
    /**
     *拖动item时回调,可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后返回true，表示被拖动的ViewHolder已经移动到了目的位置
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mTouchCallBack.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    /**
     * 当用户左右滑动item时达到删除条件就会调用,一般为一半,条目继续滑动删除,否则弹回
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mTouchCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }
    /**
     * 支持长按拖动,默认是true
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
    /**
     * 支持滑动,即可以调用到onSwiped()方法,默认是true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipeEnable;
    }
    /**
     * 设置是否支持滑动
     * @param enable
     */
    public void setSwipeEnable(boolean pSwipeEnable) {
        mSwipeEnable = pSwipeEnable;
    }
}
