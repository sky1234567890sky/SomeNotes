package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.along.myprojectdemo.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapter.VpListAdapter;
import bean.HomeData;
import bean.ProjectTab;

/**
 * Created by long on 2019/2/18.
 */

public class ProjectFragment extends Fragment {

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:

                    List<ProjectTab.DataBean> data = (List<ProjectTab.DataBean>) msg.obj;
                    Log.i("tag","data==> "+data.get(0).getName());

                    initFragment(data);

                    break;

            }

        }
    };

    //根据 网络获取的  tab 数据  添加对应的  tab 个数  和  fragment 个数
    private void initFragment(List<ProjectTab.DataBean> data) {

        ArrayList<Fragment> fragments = new ArrayList<>();

        for (int i=0; i<data.size();i++){
            // data  中 对应  fragment 需要的 接口 数据的  id
            ProjectList projectList = new ProjectList();
            Bundle bundle = new Bundle();
            bundle.putInt("id",data.get(i).getId());
            projectList.setArguments(bundle);
            fragments.add(projectList);
        }

        //创建 vp 适配器
        VpListAdapter vpListAdapter = new VpListAdapter(getChildFragmentManager(), fragments, data);
        vp.setAdapter(vpListAdapter);

        tab.setupWithViewPager(vp);// 联合  tab  vp

    }

    private TabLayout tab;
    private ViewPager vp;
    private int page = 1 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_project_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        tab = getView().findViewById(R.id.tab);
        vp = getView().findViewById(R.id.vp);
        getDataItem();
    }

   /* private void initData() {

        getDataItem();
    }*/

    public void getDataItem() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://www.wanandroid.com/project/tree/json");
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
                       // Log.i("tag","sbf==> "+sbf.toString());
                        // 解析json
                        Gson gson = new Gson();
                        ProjectTab projectTab = gson.fromJson(s, ProjectTab.class);

                        List<ProjectTab.DataBean> data = projectTab.getData();

                        Message message = new Message();
                        message.what = 1;
                        message.obj = data;
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
