package com.rongbao.jyjy.rongbao.present.fragment_presnet;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.model.fragment_model.Compass_Model;
import com.rongbao.jyjy.rongbao.view.fragment.Compass_View;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class Compass_Presnet extends BasePresent<Compass_View> {

    private Compass_Model compass_model;

    @Override
    protected void getModel() {
        compass_model = new Compass_Model();
    }

    @Override
    public void getData() {

    }
}
