package com.example.a1.geeknewschr.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.bean.GoldShowBean;
import com.example.a1.geeknewschr.callback.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2019/4/19.
 */

public class RlShowAdapter extends RecyclerView.Adapter implements TouchCallBack {
    private ArrayList<GoldShowBean> mList;

    public RlShowAdapter(ArrayList<GoldShowBean> pList) {
        mList = pList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_show, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        final GoldShowBean bean = mList.get(position);
        vh.mTv.setText(bean.title);
        vh.mSc.setChecked(bean.isChecked);
        vh.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int postion) {
        mList.remove(postion);
        notifyItemRemoved(postion);
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView mTv;
        @BindView(R.id.sc)
        SwitchCompat mSc;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
