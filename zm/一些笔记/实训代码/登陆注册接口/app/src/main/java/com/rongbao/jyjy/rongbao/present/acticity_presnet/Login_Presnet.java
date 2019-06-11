package com.rongbao.jyjy.rongbao.present.acticity_presnet;

import android.text.TextUtils;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.bean.Login_Bean;
import com.rongbao.jyjy.rongbao.callresult.Callresult;
import com.rongbao.jyjy.rongbao.model.activity_model.Login_Model;
import com.rongbao.jyjy.rongbao.util.ToastUtil;
import com.rongbao.jyjy.rongbao.view.activity.Login_View;

/**
 * Created by Administrator on 2019/4/18 0018.
 */

public class Login_Presnet extends BasePresent<Login_View> {

    private Login_Model login_model;

    @Override
    protected void getModel() {
        login_model = new Login_Model();
    }

    @Override
    public void getData() {
        String username = baseview.getusername();
        String psd = baseview.getPsd();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(psd)){
            ToastUtil.ShowToast("账号或密码为空");
        }else{
            login_model.getData(username, psd, new Callresult<Login_Bean>() {
                @Override
                public void Onsuccessful(Login_Bean bean) {
                    baseview.Onsuccessful(bean);
                    if (bean.getCode() == 200) {
                        ToastUtil.ShowToast("登陆成功");
                    }
                }

                @Override
                public void OnFaild(String meaage) {
                    baseview.OnFaild(meaage);
                }
            });
        }

    }


}
