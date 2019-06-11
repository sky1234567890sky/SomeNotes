package com.example.a1.geeknewschr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.bean.ZhihuhotBean;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/17.
 */

public class RlZhHotAdapter extends RecyclerView.Adapter<RlZhHotAdapter.MyHolder> {

    private ArrayList<ZhihuhotBean.RecentBean> mZhHotList;
    private Context context;

    public void setZhHotList(ArrayList<ZhihuhotBean.RecentBean> pZhHotList) {
        mZhHotList = pZhHotList;
    }

    public RlZhHotAdapter(ArrayList<ZhihuhotBean.RecentBean> pZhHotList, Context pContext) {
        mZhHotList = pZhHotList;
        context = pContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_zhhot_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.mTv.setText(mZhHotList.get(position).getTitle());
        Glide.with(context).load(mZhHotList.get(position).getThumbnail()).apply(new RequestOptions().centerCrop()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mZhHotList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTv;

        public MyHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_zhhot);
            mTv = itemView.findViewById(R.id.tv_zhhot);
        }
    }
}
