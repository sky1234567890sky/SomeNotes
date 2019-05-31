package com.rongbao.jyjy.rongbao.adapter.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rongbao.jyjy.rongbao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/28 0028.
 */

public class Ring_Spack_Adapter extends RecyclerView.Adapter {
    private final Context context;
    public   final ArrayList<String> myselfBeans;
    private setonclick ring_spack_mlistener;

    public Ring_Spack_Adapter(Context context, ArrayList<String> myselfBeans) {
        this.context = context;
        this.myselfBeans = myselfBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.fragment_ring_spack, null);
        return new Ring_Spack_Viewholder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Ring_Spack_Viewholder holder1 = (Ring_Spack_Viewholder) holder;
        holder1.ring_spack_name.setText(myselfBeans.get(position));
        Glide.with(context).load(R.drawable.qqpander).into(holder1.ring_spack_image);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ring_spack_mlistener.setOnclick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myselfBeans.size();
    }

    public void getData(List<String> usernames) {
        myselfBeans.addAll(usernames);
        notifyDataSetChanged();

    }

    class Ring_Spack_Viewholder extends RecyclerView.ViewHolder {

        private final ImageView ring_spack_image;
        private final TextView ring_spack_name;

        public Ring_Spack_Viewholder(View itemView) {
            super(itemView);
            ring_spack_image = itemView.findViewById(R.id.ring_spack_image);
            ring_spack_name = itemView.findViewById(R.id.ring_spack_name);
        }
    }
    public interface setonclick{
        void setOnclick(View v,int posittion);
    }
    public void Ring_spack_Onclick(setonclick ring_spack_mlistener){
        this.ring_spack_mlistener = ring_spack_mlistener;
    }
}
