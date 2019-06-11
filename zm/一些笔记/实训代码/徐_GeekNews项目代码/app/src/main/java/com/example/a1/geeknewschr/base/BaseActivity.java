package com.example.a1.geeknewschr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 1 on 2019/4/30.
 */
//柴慧茹  1809A
public abstract class BaseActivity<V extends BaseView,P extends BasePersenter> extends AppCompatActivity {

    protected P mPersenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPersenter = initPersenter();
        if (mPersenter!=null){
            mPersenter.bind((V)this);
        }
        initView();
        initData();
        initListener();
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
