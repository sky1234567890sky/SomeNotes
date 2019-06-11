package com.rongbao.jyjy.rongbao.view.fragment;

import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.Video_Bean;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public interface Video_View extends BaseView {
    String getPage();
    void Onsuccessful(Video_Bean bean);
    void OnFaild(String messge);
}
