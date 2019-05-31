package com.jiyun.demo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jiyun.demo2.R;
import com.jiyun.demo2.adapter.MyHomeAdapter;
import com.jiyun.demo2.api.MyServer;
import com.jiyun.demo2.bean.DbBean;
import com.jiyun.demo2.bean.ListBean;
import com.jiyun.demo2.utils.DbUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MyHomeAdapter.OnClickListener {


    private static final String TAG = HomeFragment.class.getName();

    private RecyclerView lv;
    private ArrayList<ListBean.ResultBean> list;
    private MyHomeAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Call<ListBean> video = myServer.getData(1, 20, "video");

        video.enqueue(new Callback<ListBean>() {
            @Override
            public void onResponse(Call<ListBean> call, Response<ListBean> response) {
                ListBean body = response.body();
                if (body!=null&&body.getResult()!=null&&body.getResult().size()>0){
                    list.addAll(body.getResult());
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ListBean> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
    }

    private void initView(View inflate) {
        lv = (RecyclerView) inflate.findViewById(R.id.lv);

        list= new ArrayList<>();

        adapter = new MyHomeAdapter(getActivity(),list);
        lv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);

        adapter.setOnClickListener(this);
    }

    @Override
    public void onItemClick(int position, ListBean.ResultBean resultBean) {

        DbBean dbBean = new DbBean();
        dbBean.setId(null);
        dbBean.setName(resultBean.getName());
        dbBean.setText(resultBean.getText());
        dbBean.setThumbnail(resultBean.getThumbnail());

        DbUtils.getDbUtils().insert(dbBean);
    }
}
