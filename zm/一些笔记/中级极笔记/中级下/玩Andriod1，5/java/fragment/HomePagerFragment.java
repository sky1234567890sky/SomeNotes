package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.along.myprojectdemo.R;
import com.example.along.myprojectdemo.WebViewActivity;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import adapter.HomePageAdapter;
import bean.HomeBanner;
import bean.HomeData;
import widget.ColorDividerItemDecoration;

/**
 * Created by long on 2019/2/18.
 */

public class HomePagerFragment extends Fragment {

    private int page = 0;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:// item数据

                    List<HomeData.DataBean.DatasBean> datas = (List<HomeData.DataBean.DatasBean>) msg.obj;
                    list_item.addAll(datas);
                    homePageAdapter.notifyDataSetChanged();

                    break;

                case 2:// banner 数据
                    List<HomeBanner.DataBean> bannerData = (List<HomeBanner.DataBean>) msg.obj;
                    list_banner.addAll(bannerData);
                    homePageAdapter.notifyDataSetChanged();
                    break;

            }
            //通知加载跟多已经完成
            xrl.loadMoreComplete();
            xrl.refreshComplete();//刷新完成
        }
    };
    private ArrayList<HomeData.DataBean.DatasBean> list_item;
    private List<HomeBanner.DataBean> list_banner;
    private HomePageAdapter homePageAdapter;
    private XRecyclerView xrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        list_item = new ArrayList<HomeData.DataBean.DatasBean>();
        list_banner = new ArrayList<>();

        //1  找控件
        xrl = getView().findViewById(R.id.xrl);
        //2 创建管理器 设置管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        xrl.setLayoutManager(linearLayoutManager);

        //添加分割线
        xrl.addItemDecoration(new ColorDividerItemDecoration(Color.parseColor("#D1C9C9"), 30));

        //3 创建适配器
        homePageAdapter = new HomePageAdapter(getContext(), list_item, list_banner);
        //4 绑定适配器
        xrl.setAdapter(homePageAdapter);

        //点击监听
        homePageAdapter.setMyOnClick(new HomePageAdapter.MyOnClick() {
            @Override
            public void myOnClick(int position) {
                if(list_banner.size()>0){
                    position = position -1;
                }
                //Log.i("tag","点击了"+position+"条目");
                String link = list_item.get(position).getLink();
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",link);
                //跳转界面
                startActivity(intent);
                //把条目中 webview 加载的网址获取  传递 给打开的界面
                // 打开的界面使用 webview 加载网页

            }
        });

        //滑动监听
        xrl.setLoadingListener(new XRecyclerView.LoadingListener() {

            //刷新
            @Override
            public void onRefresh() {
                page =0;
                //清空集合  条目
                homePageAdapter.list_item.clear();
                //再次获取数据
                getDataItem(page);
                //显示数据

            }

            //加载更多
            @Override
            public void onLoadMore() {
                page++;
                getDataItem(page);

            }
        });

        // 5 需要数据源  来自网络
        getHomeData();
    }

    public void getHomeData() {

        getDataItem(page);
        getDataBanner();

    }

    public void getDataItem(final int page) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://www.wanandroid.com/article/list/"+page+"/json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200 ){
                        StringBuffer sbf = new StringBuffer();
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String len = null;
                        while( (len =reader.readLine())!=null){
                            sbf.append(len);
                        }
                        String s = sbf.toString();
                        //Log.i("tag","sbf==> "+sbf.toString());
                        // 解析json
                        Gson gson = new Gson();
                        HomeData homeData = gson.fromJson(s, HomeData.class);
                        List<HomeData.DataBean.DatasBean> datas = homeData.getData().getDatas();
                        Message message = new Message();
                        message.what = 1;
                        message.obj = datas;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }

    public void getDataBanner() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://www.wanandroid.com/banner/json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200 ){
                        StringBuffer sbf = new StringBuffer();
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String len = null;
                        while( (len =reader.readLine())!=null){
                            sbf.append(len);
                        }
                        String s = sbf.toString();
                        //Log.i("tag","sbf==> "+sbf.toString());
                        // 解析json
                        Gson gson = new Gson();
                        HomeBanner homeBanner = gson.fromJson(s, HomeBanner.class);

                        List<HomeBanner.DataBean> bannerData = homeBanner.getData();

                        Message message = new Message();
                        message.what = 2;
                        message.obj = bannerData;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
