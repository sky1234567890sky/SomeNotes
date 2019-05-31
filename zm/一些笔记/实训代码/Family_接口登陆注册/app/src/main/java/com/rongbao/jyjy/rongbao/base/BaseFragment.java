package com.rongbao.jyjy.rongbao.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/4/15 0015.
 */

public abstract class BaseFragment<P extends BasePresent,V extends BaseView> extends Fragment {

    protected P basefragmentpresent;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), getFragmentLayout(), null);    // 在fragment中 butter  中要用到oncreateview
        //zhaoid
        bind = ButterKnife.bind(this, inflate);
        basefragmentpresent = getPresnet();
        if(basefragmentpresent!=null){
            basefragmentpresent.bind((V)this);
        }
        initView();
        initClick();
        initData();
        return inflate;
    }

    protected void initData() {

    }

    protected void initClick() {

    }

    protected void initView() {
        
    }

    protected abstract P getPresnet();

    protected abstract int getFragmentLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
