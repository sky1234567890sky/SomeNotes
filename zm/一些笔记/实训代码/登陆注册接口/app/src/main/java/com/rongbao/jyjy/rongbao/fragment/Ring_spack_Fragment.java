package com.rongbao.jyjy.rongbao.fragment;

import android.content.Intent;
import android.security.keystore.UserNotAuthenticatedException;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.hyphenate.chat.EMClient;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.activity.SecondActivity;
import com.rongbao.jyjy.rongbao.adapter.fragment.Ring_Spack_Adapter;
import com.rongbao.jyjy.rongbao.apiservice.Content;
import com.rongbao.jyjy.rongbao.base.BaseApp;
import com.rongbao.jyjy.rongbao.base.BaseFragment;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.MyDB_Login_Bean;
import com.rongbao.jyjy.rongbao.db.MyDB_Login_BeanDao;
import com.rongbao.jyjy.rongbao.present.fragment_presnet.Ring_spack_Present;
import com.rongbao.jyjy.rongbao.view.fragment.Ring_spack_View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/4/15 0015.
 */

public class Ring_spack_Fragment extends BaseFragment<BasePresent, BaseView> implements Ring_spack_View {
    @BindView(R.id.ring_spack_recy)
    RecyclerView mRingSpackRecy;
    @BindView(R.id.ring_spack_smart)
    SmartRefreshLayout mRingSpackSmart;
    @BindView(R.id.ring_spack_dl)
    LinearLayout mRingSpackDl;
    private Ring_Spack_Adapter ring_spack_adapter;
    @Override
    protected BasePresent getPresnet() {
        return new Ring_spack_Present();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.ring_spack_fragment;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for (String username : usernames) {
                        Log.d("列表好友", "run: "+username.toString());
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ring_spack_adapter.getData(usernames);


                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    @Override
    protected void initView() {
        mRingSpackRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> myselfBeans = new ArrayList<>();
        ring_spack_adapter = new Ring_Spack_Adapter(getContext(),myselfBeans);
        mRingSpackRecy.setAdapter(ring_spack_adapter);
    }

    @Override
    protected void initClick() {
        mRingSpackSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        ring_spack_adapter.Ring_spack_Onclick(new Ring_Spack_Adapter.setonclick() {
            @Override
            public void setOnclick(View v, int posittion) {
                Intent intent = new Intent(getContext(),SecondActivity.class);
                String myselfBean = ring_spack_adapter.myselfBeans.get(posittion);
                intent.putExtra(Content.MYCLASS,myselfBean);
                startActivity(intent);
            }
        });

    }
}
