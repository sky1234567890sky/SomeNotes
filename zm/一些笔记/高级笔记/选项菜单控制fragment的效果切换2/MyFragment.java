package com.jiyun.exam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private TextView txt;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment4
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        initView(inflate);
        return inflate;
    }

    public void setTEXT(String str) {
        txt.setText(str);
    }

    private void initView(View inflate) {
        txt = (TextView) inflate.findViewById(R.id.txt);
    }
    
}
