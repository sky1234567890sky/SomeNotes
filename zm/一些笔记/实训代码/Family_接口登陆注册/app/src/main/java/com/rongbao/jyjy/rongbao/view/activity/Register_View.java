package com.rongbao.jyjy.rongbao.view.activity;

import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.Register_Bean;
import com.rongbao.jyjy.rongbao.bean.Veridity_Bean;

/**
 * Created by Administrator on 2019/4/19 0019.
 */

public interface Register_View extends BaseView {
    String getUserName();
    String getUserPsd();
    String getUserAginPsd();
    String getPhone();
    String etVeridity();
    void Onsuccessful(Register_Bean bean);
    void OnFaild(String messge);
    void Onsuccessful1(Veridity_Bean bean);


}
