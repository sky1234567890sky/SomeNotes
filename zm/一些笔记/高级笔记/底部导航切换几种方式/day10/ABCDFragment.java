package com.jiyun.day10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ABCDFragment extends Fragment {
    //自定义常亮值
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "age";

    //自定义变量值
    private String mParam1;
    private String mParam2;
    private TextView tv;

    // 传递数据
    public static ABCDFragment newInstance(String param1, String param2) {
        ABCDFragment fragment = new ABCDFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //接收数据
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_abcd, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);

        tv.setText(mParam1 +":" + mParam2);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("http://www.baidu.com"+"-----"+mParam1);
            }
        });
    }

    // -----------------Fragment给Activity传值------------------------------------------------------
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //onAttach:生命周期第一个方法，用于与Activty绑定
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //context指代Activity对象
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    //onDetach:生命周期最后一个方法，用于解除绑定
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String uri);
    }
}
