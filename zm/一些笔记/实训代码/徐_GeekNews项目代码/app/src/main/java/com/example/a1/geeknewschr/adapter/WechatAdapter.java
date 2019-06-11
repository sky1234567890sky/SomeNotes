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
import com.example.a1.geeknewschr.bean.WeChaBean;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/11.
 */

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.MyHolder> {

    private ArrayList<WeChaBean.NewslistBean> mWechatList;
    private Context context;

    public void setWechatList(ArrayList<WeChaBean.NewslistBean> pWechatList) {
        mWechatList = pWechatList;
    }

    public WechatAdapter(ArrayList<WeChaBean.NewslistBean> pWechatList, Context pContext) {
        mWechatList = pWechatList;
        context = pContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_wechat_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Glide.with(context).load(mWechatList.get(position).getPicUrl()).into(holder.mIv);
        holder.mTv_author.setText(mWechatList.get(position).getDescription());
        holder.mTv_data.setText(mWechatList.get(position).getCtime());
        holder.mTv_title.setText(mWechatList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mWechatList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTv_title;
        private final TextView mTv_data;
        private final TextView mTv_author;

        public MyHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv_chat_item);
            mTv_title = itemView.findViewById(R.id.tv_chattitle_item);
            mTv_data = itemView.findViewById(R.id.tv_chatdatatime_item);
            mTv_author = itemView.findViewById(R.id.tv_chatauthor_item);
        }
    }
}
