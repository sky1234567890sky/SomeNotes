package com.example.a1.geeknewschr.persenter;


import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.WeChaBean;
import com.example.a1.geeknewschr.callback.WeChatCallBack;
import com.example.a1.geeknewschr.model.WeChatMoel;
import com.example.a1.geeknewschr.view.WechatV;

/**
 * Created by 1 on 2019/4/3.
 */

public class WechatP extends BasePersenter<WechatV> {
    private String key = "52b7ec3471ac3bec6846577e79f20e4c";
    private int num = 10;
    private WeChatMoel mWeChatMoel;

    public void getdata(String key,int num,int page) {
        mWeChatMoel.getWeChat(key, num, page, new WeChatCallBack() {
            @Override
            public void onWeChatSuccess(WeChaBean bean) {
                mView.onWeChatSuccess(bean);
            }

            @Override
            public void onWechatFail(String msg) {
                mView.onWechatFail(msg);
            }
        });
    }

    protected void initModel() {
        mWeChatMoel = new WeChatMoel();
        models.add(mWeChatMoel);
    }
}
