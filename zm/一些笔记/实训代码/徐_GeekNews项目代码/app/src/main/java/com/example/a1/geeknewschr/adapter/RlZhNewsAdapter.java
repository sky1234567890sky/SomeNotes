package com.example.a1.geeknewschr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.bean.DialyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


/**
 * Created by 1 on 2019/4/17.
 */

public class RlZhNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "adapter";
    private ArrayList<DialyNewsBean.TopStoriesBean> mBannerList;
    private ArrayList<DialyNewsBean.StoriesBean> mNewsList;
    private String time;
    private Context context;

    public void setNewsList(ArrayList<DialyNewsBean.StoriesBean> pNewsList) {
        mNewsList = pNewsList;
    }

    public void setBannerList(ArrayList<DialyNewsBean.TopStoriesBean> pBannerList) {
        mBannerList = pBannerList;
    }

    public RlZhNewsAdapter(ArrayList<DialyNewsBean.TopStoriesBean> pBannerList, ArrayList<DialyNewsBean.StoriesBean> pNewsList, String pTime, Context pContext) {
        mBannerList = pBannerList;
        mNewsList = pNewsList;
        time = pTime;
        context = pContext;
    }


    public void setTime(String pTime) {
        time = pTime;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner, parent, false);
            holder = new BannerVH(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_zhihunews_time, parent, false);
            holder = new TimeVH(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_zh_news_rlitem, parent, false);
            holder = new NewsVH(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type == 1) {
            BannerVH bannerVH = (BannerVH) holder;
            bannerVH.mBanner.setImages(mBannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DialyNewsBean.TopStoriesBean bannerBean = (DialyNewsBean.TopStoriesBean) path;
                    Glide.with(context).load(bannerBean.getImage()).into(imageView);
                }
            }).start();
        } else if (type == 2) {
            TimeVH timeVH = (TimeVH) holder;
            timeVH.mTv_time.setText(time);
        } else if (type == 3) {
            NewsVH newsVH = (NewsVH) holder;
            int newPosition = position - 1;
            int a = newPosition;
            if (mBannerList.size() > 0) {
                a = newPosition - 1;
            }
            String title = mNewsList.get(a).getTitle();
            newsVH.mTv_title.setText(title);
            Glide.with(context).load(mNewsList.get(a).getImages().get(0)).into(newsVH.mImg_news);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnNewsClick!=null){
                    mOnNewsClick.OnNewsClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mBannerList.size() > 0) {
            return mNewsList.size() + 1+1;
        } else {
            return mNewsList.size() + 1;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mBannerList.size() > 0) {
            if (position == 0) {
                return 1;
            } else if (position == 1) {
                return 2;
            } else {
                return 3;
            }
        } else {
            if (position==0){
                return 2;
            }else {
                return 3;
            }
        }
    }

    class BannerVH extends RecyclerView.ViewHolder {

        private final Banner mBanner;

        public BannerVH(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner);
        }
    }

    class TimeVH extends RecyclerView.ViewHolder {

        private final TextView mTv_time;

        public TimeVH(View itemView) {
            super(itemView);
            mTv_time = itemView.findViewById(R.id.tv_zh_news_time);
        }
    }

    class NewsVH extends RecyclerView.ViewHolder {

        private final TextView mTv_title;
        private final ImageView mImg_news;

        public NewsVH(View itemView) {
            super(itemView);
            mTv_title = itemView.findViewById(R.id.tv_zh_news_title);
            mImg_news = itemView.findViewById(R.id.img_zh_news);
        }
    }

    public interface OnNewsClick{
        void OnNewsClick(int position);
    }
    public OnNewsClick mOnNewsClick;

    public void setOnNewsClick(OnNewsClick pOnNewsClick) {
        mOnNewsClick = pOnNewsClick;
    }
}
