package com.rongbao.jyjy.rongbao.adapter.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.activity.SecondActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/29 0029.
 */

public class SecondAdapter extends RecyclerView.Adapter {
    private static final String TAG = "SecondAdapter";
    private final SecondActivity context;
    public  final ArrayList<EMMessage> messagemlist;
    private OnItemClickListener mListener;

    public SecondAdapter(SecondActivity secondActivity, ArrayList<EMMessage> messagemlist, String id, String username) {
        this.context = secondActivity;
        this.messagemlist = messagemlist;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = View.inflate(context, R.layout.second_recy_item, null);
            return new Second_viewholder1(inflate);
        } else {
            View inflate2 = View.inflate(context, R.layout.second_recy_item2, null);
            return new Second_viewholder2(inflate2);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        EMMessage emMessage = messagemlist.get(position);
        String body1 = emMessage.getBody().toString();
        String body = body1.substring(4);

        //录音地址
        String path = "";
        String[] split = body.toString().split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.startsWith("localurl")){
                String[] split1 = s.split(":");
                String s1 = split1[1];
                path = s1;
            }
        }
        final String finalPath = path;
        if (itemViewType == 1) {
            Second_viewholder1 holder1 = (Second_viewholder1) holder;
            Glide.with(context).load(R.drawable.qqpander).into(holder1.item_image);
            if(TextUtils.isEmpty(path)){
                holder1.item_content.setText(body);
            }else{
                holder1.item_content12.setVisibility(View.VISIBLE);
                holder1.item_content.setVisibility(View.GONE);
            }

            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,position,finalPath);
                }
            });


        } else {
            Second_viewholder2 holder2 = (Second_viewholder2) holder;
            Glide.with(context).load(R.drawable.qqpander).into(holder2.item_image2);
            holder2.item_content2.setText(body);
            if(TextUtils.isEmpty(path)){
                holder2.item_content2.setText(body);
            }else{
                holder2.item_content22.setVisibility(View.VISIBLE);
                holder2.item_content2.setVisibility(View.GONE);
            }
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,position,finalPath);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return messagemlist.size();
    }
    @Override
    public int getItemViewType(int position) {
        EMMessage emMessage = messagemlist.get(position);
        String name = emMessage.getUserName();
        String from = emMessage.getFrom();
        if (name.equals(from)) {
            return 1;
        } else {
            return 2;
        }
    };
 //  发送的
    public void serMessage(EMMessage message) {
        messagemlist.add(message);
        notifyDataSetChanged();
    }
    //接收到的消息
    public void getData(List<EMMessage> messages) {
        messagemlist.addAll(messages);
        notifyDataSetChanged();
    }
 // 接受语音消息
    public void serVoice_Message(EMMessage message1) {
        messagemlist.add(message1);
        notifyDataSetChanged();
    }


    class Second_viewholder1 extends RecyclerView.ViewHolder {
        private final ImageView item_image;
        private final TextView item_content;
        private final TextView item_content12;

        public Second_viewholder1(View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.recy_item_image);
            item_content = itemView.findViewById(R.id.recy_item_content);
             item_content12 = itemView.findViewById(R.id.recy_item_content12);
        }
    }

    class Second_viewholder2 extends RecyclerView.ViewHolder {
        private final ImageView item_image2;
        private final TextView item_content2;
        private final TextView item_content22;

        public Second_viewholder2(View itemView) {
            super(itemView);
            item_image2 = itemView.findViewById(R.id.item_image2);
            item_content2 = itemView.findViewById(R.id.item_content2);
            item_content22 = itemView.findViewById(R.id.item_content22);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v,int position,String path);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}
