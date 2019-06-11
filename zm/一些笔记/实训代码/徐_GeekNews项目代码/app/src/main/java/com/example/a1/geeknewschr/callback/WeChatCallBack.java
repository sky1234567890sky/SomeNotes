package com.example.a1.geeknewschr.callback;


import com.example.a1.geeknewschr.bean.WeChaBean;

/**
 * Created by 1 on 2019/4/11.
 */

public interface WeChatCallBack {
    void onWeChatSuccess(WeChaBean bean);
    void onWechatFail(String msg);
}
