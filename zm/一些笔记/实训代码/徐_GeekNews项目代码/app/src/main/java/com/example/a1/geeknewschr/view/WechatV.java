package com.example.a1.geeknewschr.view;


import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.WeChaBean;

/**
 * Created by 1 on 2019/4/3.
 */

public interface WechatV extends BaseView {
    void onWeChatSuccess(WeChaBean bean);
    void onWechatFail(String msg);
}
