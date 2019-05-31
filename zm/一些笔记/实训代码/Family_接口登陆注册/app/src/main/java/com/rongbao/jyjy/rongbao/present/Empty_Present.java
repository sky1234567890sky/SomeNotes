package com.rongbao.jyjy.rongbao.present;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.model.fragment_model.Empty_Model;
import com.rongbao.jyjy.rongbao.view.Empty_View;

/**
 * Created by Administrator on 2019/4/22 0022.
 */

public class Empty_Present extends BasePresent<Empty_View> {

    private Empty_Model empty_model;

    @Override
    protected void getModel() {
        empty_model = new Empty_Model();
    }

    @Override
    public void getData() {

    }
}
