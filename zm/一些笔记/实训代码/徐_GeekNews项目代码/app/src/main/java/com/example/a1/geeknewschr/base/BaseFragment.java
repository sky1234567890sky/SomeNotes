package com.example.a1.geeknewschr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 1 on 2019/4/30.
 */

public abstract class BaseFragment<V extends BaseView,P extends BasePersenter>extends Fragment implements BaseView {

  protected  P mPersenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this,inflate);
        mPersenter = initPersenter();
        if (mPersenter!=null){
            mPersenter.bind((V)this);
        }
        initView();
        initData();
        initListener();
        return inflate;
    }

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected abstract P initPersenter();

    protected abstract int getLayoutId();


}
