package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.along.myprojectdemo.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import bean.HomeBanner;
import bean.HomeData;

/**
 * Created by long on 2019/2/18.
 */

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    public ArrayList<HomeData.DataBean.DatasBean> list_item;
    private List<HomeBanner.DataBean> list_banner;

    public HomePageAdapter(Context context, ArrayList<HomeData.DataBean.DatasBean> list_item, List<HomeBanner.DataBean> list_banner) {
        this.context = context;
        this.list_item = list_item;
        this.list_banner = list_banner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        if( viewType == 1 ){ //  添加 banner
            View bannerview = LayoutInflater.from(context).inflate(R.layout.layout_home_item_banner,parent,false);
            holder = new MyHolderBanner(bannerview);

        }else{
            View itemview = LayoutInflater.from(context).inflate(R.layout.layout_home_item,parent,false);
            holder = new MyHolderItem(itemview);
        }
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        if(itemViewType == 1 ){ // 加载banner 的数据

            MyHolderBanner myHolderBanner = (MyHolderBanner) holder;
            //banner 简单设置
            myHolderBanner.banner.setImageLoader(new MyLoader());
            myHolderBanner.banner.setImages(list_banner);
            myHolderBanner.banner.start();

        }else if(itemViewType == 2){
            MyHolderItem myHolderItem = (MyHolderItem) holder;
            int mpsition = position ;
            if(list_banner.size()>0){
                mpsition  = position -1;
            }
            myHolderItem.tv_item_name.setText(list_item.get(mpsition).getChapterName()+"/"+list_item.get(mpsition).getSuperChapterName());
            myHolderItem.tv_item_title.setText(list_item.get(mpsition).getTitle());
            myHolderItem.tv_item_time.setText(list_item.get(mpsition).getNiceDate());
            myHolderItem.tv_author.setText(list_item.get(mpsition).getAuthor());

            if(!list_item.get(mpsition).isFresh()){
                myHolderItem.tv_item_fresh.setVisibility(View.INVISIBLE);
            }else{
                myHolderItem.tv_item_fresh.setTextColor(context.getResources().getColor(R.color.my_green));
                myHolderItem.tv_item_fresh.setVisibility(View.VISIBLE);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(myOnClick!=null){
                        myOnClick.myOnClick(position);
                    }

                }
            });

        }
    }
    @Override
    public int getItemCount() {
        if( list_banner.size()>0){
            return  list_item.size()+1;
        }
        return list_item.size();
    }

    @Override
    public int getItemViewType(int position) {

        if( position == 0 ){
            return 1;
        }

        return 2;
    }

    class MyHolderItem extends RecyclerView.ViewHolder{
        private TextView tv_item_fresh;
        private TextView tv_item_name;
        private TextView tv_item_time;
        private TextView tv_item_title;
        private TextView tv_author;

        //private ImageView iv_item_clock;

        public MyHolderItem(View itemView) {
            super(itemView);

            tv_item_fresh = itemView.findViewById(R.id.tv_item_fresh);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            tv_item_time = itemView.findViewById(R.id.tv_item_time);
            tv_item_title = itemView.findViewById(R.id.tv_item_title);
            tv_author = itemView.findViewById(R.id.tv_author);
            //iv_item_clock = itemView.findViewById(R.id.iv_item_clock);
            //iv_item_love = itemView.findViewById(R.id.iv_item_love);
            //iv_item_wan = itemView.findViewById(R.id.iv_item_wan);
        }
    }

    class MyHolderBanner extends RecyclerView.ViewHolder{
        private Banner banner;
        public MyHolderBanner(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    //加载banner 图片
    class MyLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            // path 就是  list_banner 的一个元素
            HomeBanner.DataBean  hd = (HomeBanner.DataBean) path;
            String imagePath = hd.getImagePath();
            Glide.with(context).load(imagePath).into(imageView);
        }
    }

    //自定义接口
    public interface MyOnClick{
        void myOnClick(int position);
    }
    // 以接口为数据类型  定义成员变量
    private MyOnClick myOnClick;
    // 定义成员变量的set方法

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }
    // 在需要的位置  使用 成员变量 调用接口方法

}
