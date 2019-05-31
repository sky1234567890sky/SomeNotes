package com.rongbao.jyjy.rongbao.present.fragment_presnet;

import android.util.Log;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.bean.Video_Bean;
import com.rongbao.jyjy.rongbao.callresult.Callresult;
import com.rongbao.jyjy.rongbao.model.fragment_model.Video_Model;
import com.rongbao.jyjy.rongbao.view.fragment.Video_View;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class Video_Presnet extends BasePresent<Video_View> {

    private Video_Model video_model;

    @Override
    protected void getModel() {
        video_model = new Video_Model();
    }

    @Override
    public void getData() {
        String page = baseview.getPage();
        video_model.getData( page, new Callresult<Video_Bean>() {
            @Override
            public void Onsuccessful(Video_Bean bean) {

                Log.d("P层请求数据", "Onsuccessful: "+bean.toString());
                if(bean!=null){
                baseview.Onsuccessful(bean);
                }
            }

            @Override
            public void OnFaild(String messge) {
                baseview.OnFaild(messge);

            }
        });
    }
}
