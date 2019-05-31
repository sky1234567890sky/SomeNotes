package com.jiyun.demo2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.demo2.R;
import com.jiyun.demo2.bean.DbBean;
import com.jiyun.demo2.bean.ListBean;

import java.util.ArrayList;

/**
 * Created by $lzj on 2019/4/8.
 */
public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DbBean> list;

    public void setList(ArrayList<DbBean> list) {
        this.list = list;
    }

    public MyCollectAdapter(Context context, ArrayList<DbBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final DbBean resultBean = list.get(i);

        Glide.with(context).load(resultBean.getThumbnail()).into(viewHolder.img);
        viewHolder.title.setText(resultBean.getText());
        viewHolder.source.setText(resultBean.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onItemClick(i,resultBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title;
        private TextView source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);

        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onItemClick(int position, DbBean resultBean);
    }
}
