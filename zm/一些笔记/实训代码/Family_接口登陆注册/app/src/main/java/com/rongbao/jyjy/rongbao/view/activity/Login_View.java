package com.rongbao.jyjy.rongbao.view.activity;

import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.Login_Bean;

/**
 * Created by Administrator on 2019/4/18 0018.
 */

public interface Login_View extends BaseView {
    void Onsuccessful(Login_Bean bean);
    void OnFaild(String meaage);

    String getusername();
    String getPsd();
}
