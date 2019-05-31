package com.rongbao.jyjy.rongbao.present.fragment_presnet;

import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.model.fragment_model.Ring_spack_Model;
import com.rongbao.jyjy.rongbao.view.fragment.Ring_spack_View;

/**
 * Created by Administrator on 2019/4/17 0017.
 */

public class Ring_spack_Present extends BasePresent<Ring_spack_View> {

    private Ring_spack_Model ring_spack_model;

    @Override
    protected void getModel() {
        ring_spack_model = new Ring_spack_Model();
    }

    @Override
    public void getData() {

    }


}
