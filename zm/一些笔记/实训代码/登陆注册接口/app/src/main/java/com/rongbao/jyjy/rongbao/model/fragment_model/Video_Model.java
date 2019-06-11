package com.rongbao.jyjy.rongbao.model.fragment_model;

import android.util.Log;

import com.rongbao.jyjy.rongbao.apiservice.Apiservice;
import com.rongbao.jyjy.rongbao.base.BaseModel;
import com.rongbao.jyjy.rongbao.callresult.Callresult;
import com.rongbao.jyjy.rongbao.util.ModelUtils;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class Video_Model  extends BaseModel{
    public void getData(String at,final Callresult callresult) {
        ModelUtils.getInstance().getNetWork(ModelUtils.getNetMessage(Apiservice.VideoUrl).getVideoPlayer(at), callresult);
    }
}
