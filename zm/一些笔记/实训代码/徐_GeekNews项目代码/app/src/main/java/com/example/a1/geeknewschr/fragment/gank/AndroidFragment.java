package com.example.a1.geeknewschr.fragment.gank;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.RlIsoAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.bean.gank.IsoBean;
import com.example.a1.geeknewschr.persenter.gank.IsoP;
import com.example.a1.geeknewschr.utils.SystemUtil;
import com.example.a1.geeknewschr.view.gank.IsoV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseFragment<IsoV, IsoP> implements IsoV {


    @BindView(R.id.rl_android)
    RecyclerView mRlAndroid;
//    @BindView(R.id.img_android)
//    ImageView mImageView;
    public String teach = "Android";
    public int num = 10;
    public int page = 1;
    @BindView(R.id.iv_tech_blur)
    ImageView mIvTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView mIvTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView mTvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout mTechAppbar;
    private ArrayList<IsoBean.ResultsBean> mIsoList;
    private RlIsoAdapter mRlIsoAdapter;

    public AndroidFragment() {
        // Required empty public constructor
    }

    @Override
    protected IsoP initPersenter() {
        return new IsoP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initView() {

        int radious = 20;
        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation(getActivity(),radious));
        Glide.with(getContext()).load(R.drawable.li).apply(options).into(mIvTechBlur);
        Glide.with(getContext()).load(R.drawable.li).into(mIvTechOrigin);

        //appbar 滑动偏移监听
        mTechAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //两张图片,后面的是高斯模糊过的,前面的是原图,滑动过程中修改原图
                //的透明度,实现效果
                ///透明度值范围,0到1,0完全透明,1完全不透明
                //1dp = 3px(我的手机) 768px
                int imgHeight = SystemUtil.dp2px(256);
                //verticalOffset / imgHeight(768)范围:0到-1
                //verticalOffset 0到-768
                float rate = 1+verticalOffset * 2.0f / imgHeight;
                if (rate>=0){
                    mIvTechOrigin.setAlpha(rate);
                }
            }
        });

        mPersenter.getisoData(teach, num, page);
        mIsoList = new ArrayList<>();
        mRlIsoAdapter = new RlIsoAdapter(mIsoList, getActivity());
        mRlAndroid.setAdapter(mRlIsoAdapter);
        mRlAndroid.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onIsoSuccess(IsoBean pIsoBean) {
        List<IsoBean.ResultsBean> results = pIsoBean.getResults();
        mIsoList.addAll(results);
        mRlIsoAdapter.setIsoList(mIsoList);
        mRlIsoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onIsoFail(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

}
