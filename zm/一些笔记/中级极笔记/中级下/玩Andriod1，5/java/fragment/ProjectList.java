package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.along.myprojectdemo.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import bean.HomeData;

/**
 * Created by long on 2019/2/19.
 */

public class ProjectList extends Fragment{

    private int page =1;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            String s = (String) msg.obj;

            tvlist.setText(s);

        }
    };
    private TextView tvlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_project_list,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        tvlist = getView().findViewById(R.id.tvlist);

        //获取 传递过来的 id
        Bundle arguments = getArguments();

        int id = arguments.getInt("id");

        intiData(page,id);// pager  id


    }

    private void intiData(final int page, final int id) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    //http://www.wanandroid.com/project/list/1/json?cid=294
                    URL url = new URL("http://www.wanandroid.com/project/list/"+page+"/json?cid="+id);
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
                       /* Gson gson = new Gson();
                        HomeData homeData = gson.fromJson(s, HomeData.class);
                        List<HomeData.DataBean.DatasBean> datas = homeData.getData().getDatas();
                        Message message = new Message();
                        message.what = 1;
                        message.obj = datas;
                        handler.sendMessage(message);*/
                        Message message = new Message();
                        //message.what = 1;
                        message.obj = s;
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
