package com.rongbao.jyjy.rongbao.fragment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.base.BaseFragment;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.present.fragment_presnet.Compass_Presnet;
import com.rongbao.jyjy.rongbao.view.fragment.Compass_View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class ComPass_Fragment extends BaseFragment<BasePresent, BaseView> implements Compass_View {
    @BindView(R.id.txt_tem)
    TextView mTxtTem;
    @BindView(R.id.img_com)
    ImageView mImgCom;
    float start = 0f;

    @Override
    protected BasePresent getPresnet() {
        return new Compass_Presnet();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_compass;
    }
    //initView
    protected void initView() {
        //获取服务：
        SensorManager manager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        //方向传感器：
        Sensor defaultSensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
//        湿度传感器
//        Sensor defaultSensor1 = manager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        //传感器监听器：
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                int type = event.sensor.getType();
                if (type == Sensor.TYPE_ORIENTATION) {
                    float values[] = event.values;
                    //获取当前的旋转的度数：
                    float now = values[0];
                    RotateAnimation rotateAnimation = new RotateAnimation(start, now, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    if(rotateAnimation!=null) rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    mImgCom.startAnimation(rotateAnimation);
                    //保持当前旋转的度数：
                    start = -now;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        manager.registerListener(sensorEventListener, defaultSensor, SensorManager.SENSOR_DELAY_GAME);
    }
    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        //执行逻辑代码
        if (getUserVisibleHint()) {
            isVisibleToUser = true;
            Log.d("指南针", "懒加载");
            getda();
        } else {
            isVisibleToUser=false;
        }
        super.setUserVisibleHint(isVisibleToUser);

    }*/




}
