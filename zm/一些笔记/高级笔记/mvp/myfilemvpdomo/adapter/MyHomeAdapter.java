package com.example.myfilemvpdomo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfilemvpdomo.R;
import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 苏克阳 on 2019/4/23.
 */
public class MyHomeAdapter  extends RecyclerView.Adapter{
    private final Context context;
    private final List<ArtDatas.DataBean.DatasBean> artDatas;
    private final List<BeannerDatas.DataBean> beannerDatas;
    public MyHomeAdapter(Context context, List<ArtDatas.DataBean.DatasBean> artDatas, List<BeannerDatas.DataBean> beannerDatas) {
        this.context = context;
        this.artDatas = artDatas;
        this.beannerDatas = beannerDatas;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType ==1){
            View inflate = View.inflate(context, R.layout.item_artdatas, null);
            holder = new MyArtViewHolder(inflate);
        }else if (viewType==2){
            View inflate = View.inflate(context, R.layout.item_beannerdatas, null);
            holder = new MyBeaanerViewHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type==1){
            MyArtViewHolder view1 = (MyArtViewHolder) holder;
            int newposition = position;
            if (beannerDatas.size()>0){
                newposition = position - 1;
            }
            ArtDatas.DataBean.DatasBean datasBean = artDatas.get(newposition);
            view1.artTv1.setText(datasBean.getAuthor()+"\r\n"+datasBean.getChapterName());
            view1.artTv2.setText(datasBean.getDesc()+"\r\n"+datasBean.getSuperChapterName());
            RequestOptions requestOptions = new RequestOptions().circleCrop();
            Glide.with(context).load(datasBean.getEnvelopePic()).apply(requestOptions).placeholder(R.mipmap.ic_launcher).into(view1.artImg);

        }else if (type==2){
            final MyBeaanerViewHolder view2 = (MyBeaanerViewHolder) holder;
            view2.mybanner.setImages(beannerDatas).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BeannerDatas.DataBean urlpath = (BeannerDatas.DataBean) path;
                    String imagePath = urlpath.getImagePath();
                    Log.i("tag", "banner路径: "+imagePath);
                    Glide.with(context).load(imagePath).placeholder(R.mipmap.ic_launcher).into(imageView);
                }
            }).start();
        }
    }

    @Override
    public int getItemCount() {
        if (beannerDatas.size()>0){
            return artDatas.size()+1;
        }
        return artDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (beannerDatas.size()>0&&position==0){
            return 2;
        }
        return 1;
    }
    class MyArtViewHolder extends RecyclerView.ViewHolder{
        private ImageView artImg;
        private TextView artTv1;
        private TextView artTv2;
        public MyArtViewHolder(View itemView) {
            super(itemView);
            artImg = (ImageView) itemView.findViewById(R.id.art_img);
            artTv1 = (TextView) itemView.findViewById(R.id.art_tv1);
            artTv2 = (TextView) itemView.findViewById(R.id.art_tv2);
        }
    }

    class MyBeaanerViewHolder extends RecyclerView.ViewHolder{
        private Banner mybanner;
        public MyBeaanerViewHolder(View itemView) {
            super(itemView);



            mybanner = (Banner) itemView.findViewById(R.id.mybanner);


        }
    }

}
