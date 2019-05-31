package com.jiyun.exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private String name;
    private RecyclerView lv;
    private ArrayList<String> list;
    private MyRAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(String name) {
        this.name = name;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null){
            name = getArguments().getString("abc");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        lv = (RecyclerView) inflate.findViewById(R.id.lv);

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("Dadsadasdasddad");
        }

        adapter = new MyRAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);

        btn = (Button) inflate.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btn.setText(name);
    }

    public void setManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        lv.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (abc!=null){
                    abc.onAbc("dagiufhasifdhasoidy");
                }
                break;
        }
    }

    private ABC abc;

    public void setAbc(ABC abc) {
        this.abc = abc;
    }

    public interface ABC{
        void onAbc(String str);
    }
}
