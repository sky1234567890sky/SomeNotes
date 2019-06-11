package com.example.a1.geeknewschr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.base.BaseApp;
import com.example.a1.geeknewschr.bean.gank.FuliBean;
import com.example.a1.geeknewschr.utils.SystemUtil;

import java.util.ArrayList;


/**
 * Created by 1 on 2019/4/23.
 */

public class RlFuliAdapter extends RecyclerView.Adapter<RlFuliAdapter.MyHolder> {

    private ArrayList<FuliBean.ResultsBean> mFuliData;
    private Context context;

    public void setFuliData(ArrayList<FuliBean.ResultsBean> pFuliData) {
        mFuliData = pFuliData;
    }

    public RlFuliAdapter(ArrayList<FuliBean.ResultsBean> pFuliData, Context pContext) {
        mFuliData = pFuliData;
        context = pContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_fuli_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        FuliBean.ResultsBean resultsBean = mFuliData.get(position);
        //在设置图片之前把ImageView的宽高重新设置一下
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mImg.getLayoutParams();
        int imageWidth = BaseApp.mWidthPixels / 2 - SystemUtil.dp2px(8);
        layoutParams.width = imageWidth;
        if (resultsBean.getScale() != 0){
            layoutParams.height = (int) (imageWidth/resultsBean.getScale());
        }
        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
        holder.mImg.setLayoutParams(layoutParams);

//        RequestOptions options = new RequestOptions()
//                .transform(new RoundedCornersTransformation(SystemUtil.dp2px(6),0));
        Glide.with(context).load(resultsBean.getUrl()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mFuliData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;

        public MyHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_fl);

        }
    }
}
