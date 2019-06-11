package com.example.a1.geeknewschr.callback;

/**
 * Created by 1 on 2019/4/19.
 */

public interface TouchCallBack {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);
    //删除条目
    void onItemDelete(int postion);

}
