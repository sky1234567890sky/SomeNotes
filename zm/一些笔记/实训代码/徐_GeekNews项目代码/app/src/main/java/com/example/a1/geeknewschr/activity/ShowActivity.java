package com.example.a1.geeknewschr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.RlShowAdapter;
import com.example.a1.geeknewschr.base.BaseActivity;
import com.example.a1.geeknewschr.base.Constants;
import com.example.a1.geeknewschr.bean.GoldShowBean;
import com.example.a1.geeknewschr.callback.SimpleTouchHelperCallBack;
import com.example.a1.geeknewschr.persenter.EmptyP;
import com.example.a1.geeknewschr.view.EmptyV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyV,EmptyP> implements EmptyV {

    @BindView(R.id.toolbar_show)
    Toolbar mToolbarShow;
    @BindView(R.id.rl_show)
    RecyclerView mRlShow;
    private ArrayList<GoldShowBean> mList;
    @Override
    protected EmptyP initPersenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mList = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);

        mToolbarShow.setTitle(R.string.special_show);
        mToolbarShow.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(mToolbarShow);

        mToolbarShow.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mRlShow.setLayoutManager(new LinearLayoutManager(this));
        RlShowAdapter rlShowAdapter = new RlShowAdapter(mList);
        mRlShow.setAdapter(rlShowAdapter);
        mRlShow.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        SimpleTouchHelperCallBack simpleTouchHelperCallBack = new SimpleTouchHelperCallBack(rlShowAdapter);
        simpleTouchHelperCallBack.setSwipeEnable(true);//true可以划走  false  不可滑动
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(mRlShow);//绑定recycleview
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, mList);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}
