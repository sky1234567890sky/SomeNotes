package com.rongbao.jyjy.rongbao.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.base.BaseFragment;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.present.fragment_presnet.Map_Present;
import com.rongbao.jyjy.rongbao.util.PoiOverlay;
import com.rongbao.jyjy.rongbao.view.fragment.Map_View;

import butterknife.BindView;


/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class Map_Fragment extends BaseFragment<BasePresent, BaseView> implements Map_View {
    @BindView(R.id.bmapView)
    MapView mWearMapView;
    @BindView(R.id.fragment_map_tv_search)
    TextView mFramentMaptvSearch;
    @BindView(R.id.fragment_map_location)
    TextView mFramentMapLocation;
    @BindView(R.id.fragment_map_ed_name)
    EditText mFragmentMapEdNAme;
            private BaiduMap map;
            private LocationClient mLocationClient;
            public LatLng latLng;
            private PoiSearch mPoiSearch;
            private double latitude;
            private double longitude;

            @Override
            protected BasePresent getPresnet(){
            return new Map_Present();
            }

            //HS1NDXWRLOibURYXsPI04lStFTnfIhlv   地图ak
            @Override
            protected int getFragmentLayout(){
            return R.layout.map_fragment;
            }

            @Override
            protected void initView(){
            map = mWearMapView.getMap();
//        //设置地图模式为卫星地图
//        map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
           location();
//         开启交通图
            map.setTrafficEnabled(true);
            }


            public class MyLocationListener extends BDAbstractLocationListener{


            @Override
            public void onReceiveLocation(BDLocation location) {
        //mapView 销毁后不在处理新接收的位置
        if (location == null || mWearMapView == null) {
            return;
        }
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())   //经度  getDirection
                .longitude(location.getLongitude()).build();   //纬度 getLongitude
        latLng = new LatLng(latitude, latitude);
        map.setMyLocationData(locData);
    }
}
    @Override
    protected void initClick() {
        mFramentMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(latLng);
                map.setMapStatus(status2);
            }
        });
        // 点击地图上的位置然后  生成一个marker
        map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker(latLng);
            }
            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle extraInfo = marker.getExtraInfo();
                String id = extraInfo.getString("id");
                if ("id_01".equals(id)) {
                    showToast(marker.getTitle());
                }
                walkNavi(marker.getPosition());
                Log.d("marker", "marker1: "+"marke的点击事件");
                return false;
            }
        });

//  poi检索
        mFramentMaptvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mFragmentMapEdNAme.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(getContext(), "输入要搜索的东西", Toast.LENGTH_SHORT).show();
                } else {

                    //发起检索请求
                    //城市检索
                    //mPoiSearch.searchInCity()
                    /**
                     * 以自己为中心  发起以半径为5000米 的检索
                     */
                    if (latitude == 0 || longitude == 0) {
                        return;
                    }
                    //创建POI检索实例
                    mPoiSearch = PoiSearch.newInstance();
                    //设置检索监听器
                    mPoiSearch.setOnGetPoiSearchResultListener(listener);
                    Log.d("传", "latitude: " + latitude + "longitude " + longitude);
                    Log.d("搜索", "onClick: " + content);
                    mPoiSearch.searchNearby(new PoiNearbySearchOption()
                            .location(new LatLng(latitude, longitude))
                            .radius(5000)
                            .keyword(content)
                            .pageNum(10));

                }
            }
        });
    }

    //创建POI检索监听器
    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                map.clear();
                //创建PoiOverlay对象
                PoiOverlay poiOverlay = new PoiOverlay(map);
                //设置Poi检索数据
                poiOverlay.setData(poiResult);
                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        }
    };
    //步行导航
    private void walkNavi(final LatLng end) {
        // 获取导航控制类
        // 引擎初始化
        WalkNavigateHelper.getInstance().initNaviEngine((Activity) getContext(), new IWEngineInitListener() {
            @Override
            public void engineInitSuccess() {
                //引擎初始化成功的回调
                routeWalkPlanWithParam(end);
                Log.d("marker", "marker2: "+"marke的点击事件");
            }

            @Override
            public void engineInitFail() {
                Log.d("marker", "marker2中的引擎: "+"引擎初始化失败");
                //引擎初始化失败的回调
            }
        });
    }

    private void routeWalkPlanWithParam(LatLng end) {
        //发起算路
        //构造WalkNaviLaunchParam
        //起终点位置
        //构造WalkNaviLaunchParam
        WalkNaviLaunchParam mParam = new WalkNaviLaunchParam().stPt(latLng).endPt(end);
        //发起算路
        WalkNavigateHelper.getInstance().routePlanWithParams(mParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                //开始算路的回调
            }

            @Override
            public void onRoutePlanSuccess() {
                Log.d("marker", "marker3"+"marke的点击事件");
                //算路成功
                //跳转至诱导页面
                Log.d("poi检索页面", "onRoutePlanSuccess: " + "跳转的方法");
//                getActivity().startActivity(new Intent(getActivity(), WNaviGuideActivity.class));

            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError walkRoutePlanError) {
                Log.d("marker", "marker4"+"marke的点击事件");
                //算路失败的回调
            }
        });
    }


    @Override
    public void onResume() {
        mWearMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mWearMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
        map.setMyLocationEnabled(false);
        if (mPoiSearch != null) {
            mPoiSearch.destroy();
        }
        if(mWearMapView!=null){
        mWearMapView.onDestroy();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void addMarker(LatLng latLng) {
        //定义Maker坐标点
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        Bundle bundle = new Bundle();
        bundle.putString("id", "id_01");
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .animateType(MarkerOptions.MarkerAnimateType.jump)
                .draggable(true)
                .title("请稍等！（~ V~）")
                .extraInfo(bundle)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        map.addOverlay(option);
    }

    private void location() {
        //开启地图的定位图层
        map.setMyLocationEnabled(true);
        //通过LocationClient发起定位
        //定位初始化
        mLocationClient = new LocationClient(getContext());
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        mLocationClient.start();
        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL ,
                true,
                null,
                R.color.c_C7A3F3,
                R.color.c_80BCE1);
        map.setMyLocationConfiguration(myLocationConfiguration);
        mLocationClient.start();
    }


}
