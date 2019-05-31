package com.rongbao.jyjy.rongbao.adapter.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.bean.Video_Bean;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayerStandard;


/**
 * Created by Administrator on 2019/4/27 0027.
 */

public class Fragment_Video_Adapter extends RecyclerView.Adapter {
    private final Context context;
    public  final ArrayList<Video_Bean.DataBean.FeedsListBean> video_beans;

    public Fragment_Video_Adapter(Context context, ArrayList<Video_Bean.DataBean.FeedsListBean> video_beans) {
        this.context = context;
        this.video_beans = video_beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.video_item, null);
        return new Video_Vuewholder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Video_Vuewholder holder1 = (Video_Vuewholder) holder;
        Video_Bean.DataBean.FeedsListBean feedsListBean = video_beans.get(position);
        Video_Bean.DataBean.FeedsListBean.VideoInfoBean video_info = feedsListBean.getVideo_info();
        String url = video_info.getUrl();
        String share_title = video_beans.get(position).getShare_title();
        holder1.videoplayer.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,share_title);
        Glide.with(context).load(video_info.getThumb()).into(holder1.videoplayer.thumbImageView);

    }

    @Override
    public int getItemCount() {
        return video_beans.size();
    }

    public void getData(Video_Bean bean) {
        video_beans.addAll(bean.getData().getFeeds_list());
        notifyDataSetChanged();
    }

    class Video_Vuewholder extends RecyclerView.ViewHolder {

        private final JZVideoPlayerStandard videoplayer;

        public Video_Vuewholder(View itemView) {
            super(itemView);
            videoplayer = itemView.findViewById(R.id.videoplayer);
        }
    }
}
