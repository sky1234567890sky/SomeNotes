package com.rongbao.jyjy.rongbao.present.acticity_presnet;

import android.text.TextUtils;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.bean.Register_Bean;
import com.rongbao.jyjy.rongbao.bean.Veridity_Bean;
import com.rongbao.jyjy.rongbao.callresult.Callresult;
import com.rongbao.jyjy.rongbao.model.activity_model.Register_Model;
import com.rongbao.jyjy.rongbao.util.ToastUtil;
import com.rongbao.jyjy.rongbao.view.activity.Register_View;

/**
 * Created by Administrator on 2019/4/19 0019.
 */

public class Register_Present extends BasePresent<Register_View>{

    private Register_Model register_model;

    @Override
    protected void getModel() {
        register_model = new Register_Model();
    }

    @Override
    public void getData() {
        String name = baseview.getUserName();
        String Psd = baseview.getUserPsd();
        String AginPsd = baseview.getUserAginPsd();
        String phone = baseview.getPhone();
        String verifi = baseview.etVeridity();
        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(Psd)&& TextUtils.isEmpty(phone)&&TextUtils.isEmpty(AginPsd)
                &&TextUtils.isEmpty(verifi)){
            ToastUtil.ShowToast("内容不能有空");
        }else{
            if(Psd.equals(AginPsd)){
                register_model.getData(name, Psd, phone, verifi, new Callresult<Register_Bean>() {
                    @Override
                    public void Onsuccessful(Register_Bean bean) {
                      if(bean.getCode()==200){
                        baseview.Onsuccessful(bean);
                      }else{
                          ToastUtil.ShowToast(bean.getRet());
                      }
                    }
                    @Override
                    public void OnFaild(String messge) {
                        baseview.OnFaild(messge);
                    }
                });
            }
        }
    }

    @Override
    public  void getVerifity() {
        register_model.getVerifity(new Callresult<Veridity_Bean>() {
            @Override
            public void Onsuccessful(Veridity_Bean bean) {
                if(bean.getCode()==200&&bean!=null){
                    baseview.Onsuccessful1(bean);

                }
            }

            @Override
            public void OnFaild(String messge) {
                baseview.OnFaild(messge);
            }
        });

    }
}
