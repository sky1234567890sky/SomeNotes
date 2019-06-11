package com.rongbao.jyjy.rongbao.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.base.BaseActivity;
import com.rongbao.jyjy.rongbao.base.BaseApp;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.present.Empty_Present;
import com.rongbao.jyjy.rongbao.util.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<BasePresent, BaseView> implements BaseView {


    @BindView(R.id.cb_receive_push_message)
    CheckBox mCbReceivePushMessage;
    @BindView(R.id.ll_login_after)
    LinearLayout mLlLoginAfter;
    @BindView(R.id.tv_cache_size)
    TextView mTvCacheSize;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout mRlClearCache;
    @BindView(R.id.tv_versions)
    TextView mTvVersions;
    private String totalCacheSize;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresent getPresent() {
        return new Empty_Present();
    }

    @Override
    protected void initview() {

        try {
            mTvCacheSize.setText(DataCleanManager.getTotalCacheSize(BaseApp.getInstance()) );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initClick() {
        mRlClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.clearAllCache(BaseApp.getInstance());

                try {
                    mTvCacheSize.setText(DataCleanManager.getTotalCacheSize(BaseApp.getInstance()) );
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        mCbReceivePushMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mLlLoginAfter.setVisibility(View.VISIBLE);
                }else{
                    mLlLoginAfter.setVisibility(View.GONE);
                }
            }
        });



    }
}
