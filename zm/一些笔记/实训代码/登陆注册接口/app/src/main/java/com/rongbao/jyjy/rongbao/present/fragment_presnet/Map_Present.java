package com.rongbao.jyjy.rongbao.present.fragment_presnet;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.model.Map_Model;
import com.rongbao.jyjy.rongbao.view.fragment.Map_View;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class Map_Present extends BasePresent<Map_View> {

    private Map_Model map_model;

    @Override
    protected void getModel() {
        map_model = new Map_Model();
    }

    @Override
    public void getData() {

    }
}
