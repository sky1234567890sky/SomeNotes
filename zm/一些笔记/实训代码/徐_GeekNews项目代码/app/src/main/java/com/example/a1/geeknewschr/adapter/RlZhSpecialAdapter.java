package com.example.a1.geeknewschr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.bean.SpecialColunmBean;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/17.
 */

public class RlZhSpecialAdapter extends RecyclerView.Adapter<RlZhSpecialAdapter.MyHolder> {

    private ArrayList<SpecialColunmBean.DataBean> mSpecialList;
    private Context context;

    public void setSpecialList(ArrayList<SpecialColunmBean.DataBean> pSpecialList) {
        mSpecialList = pSpecialList;
    }

    public RlZhSpecialAdapter(ArrayList<SpecialColunmBean.DataBean> pSpecialList, Context pContext) {
        mSpecialList = pSpecialList;
        context = pContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_zhspecial_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        SpecialColunmBean.DataBean dataBean = mSpecialList.get(position);
        Glide.with(context).load(dataBean.getThumbnail()).into(holder.mImg);
        holder.mTv.setText(dataBean.getDescription());
        holder.mTv_title.setText(dataBean.getName());

    }

    @Override
    public int getItemCount() {
        return mSpecialList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTv_title;
        private final TextView mTv;

        public MyHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.iv);
            mTv_title = itemView.findViewById(R.id.tv_title);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}
