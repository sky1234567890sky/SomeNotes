package com.rongbao.jyjy.rongbao.base;

/**
 * Created by Administrator on 2019/4/15 0015.
 */

public  abstract  class BasePresent<V extends BaseView>  {
    protected V baseview;
    public void bind(V baseview) {
        getModel();
        this.baseview = baseview;
    }

    protected abstract void getModel();   //获取 p层

    public abstract void getData();
    public void getVerifity(){}
}
