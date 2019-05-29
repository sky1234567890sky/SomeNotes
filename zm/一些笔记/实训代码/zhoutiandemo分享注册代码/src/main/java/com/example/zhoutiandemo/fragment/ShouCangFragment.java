package com.example.zhoutiandemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.adapter.ShouCangAdapter;
import com.example.zhoutiandemo.dao.ShuJuKuDemoDao;
import com.example.zhoutiandemo.db.BaseApp;
import com.example.zhoutiandemo.db.ShuJuKuDemo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouCangFragment extends Fragment {


    private RecyclerView rlv;
    private ArrayList<ShuJuKuDemo> list;
    private ShouCangAdapter adapter;

    public ShouCangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_shou_cang, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            initData();

        }
    }


    private void initData() {
        list = (ArrayList<ShuJuKuDemo>) BaseApp.getBaseApp().getShuJuKuDemoDao().queryBuilder().list();

        adapter = new ShouCangAdapter(list, getActivity());
        rlv.setAdapter(adapter);
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter.setOnItemLongClickListener(new ShouCangAdapter.OnItemLongClickListener() {
            @Override
            public void onitemclick(int position) {
                initPopupWindow(position);
            }
        });
    }

    private void initPopupWindow(final int position) {
        View inflate = View.inflate(getActivity(), R.layout.popupwindow_quxiao, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.E3C6C6));

        popupWindow.setContentView(inflate);
        popupWindow.setTouchable(true);

        popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);

        Button quxiao = inflate.findViewById(R.id.popup_quxiao);

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //数据库操作
                initdelete(position, popupWindow);
            }
        });
    }

    private void initdelete(int position, PopupWindow popupWindow) {

        String url = list.get(position).getUrl();

        ShuJuKuDemo shuju = getShuju(url);
        if (shuju != null) {
            setUserVisibleHint(delete(popupWindow, shuju));

        } else {
            Toast.makeText(getContext(), "出现未知错误", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        }
    }


    private boolean delete(PopupWindow popupWindow, ShuJuKuDemo shuju) {
        BaseApp.getBaseApp().getShuJuKuDemoDao().delete(shuju);
        Toast.makeText(getContext(), "已取消收藏", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        return true;
    }

    private void initView(View inflate) {
        rlv = (RecyclerView) inflate.findViewById(R.id.rlv);
    }

    public ShuJuKuDemo getShuju(String url) {

        return BaseApp.getBaseApp().getShuJuKuDemoDao().queryBuilder()
                .where(ShuJuKuDemoDao.Properties.Url.eq(url))
                .build()
                .unique();
    }


}
