package com.example.test1;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

@SuppressLint("InflateParams")
public class MainActivity extends Activity {

	//初始化
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*	
		 * 	ViewPager  v4包
		 *   ViewPager   加载的是  布局      xml 布局    以单空间
		 *   	适配器
		 *   	数据源           集合    View 对象  布局对象
		 *   		控制界面的数量
		 *   		对应的下标和界面
		 *   		滑走消失界面
		 *   		滑来显示 界面
		 *   
		 *   ListView    加载了一个布局
		 * 		适配器
		 * 		数据源	 集合  具体数据   实体类
		 * 		   控件条目的
		 * 		 控制条目的下标
		 * 		 控制条目的数据
		 * 	           控制条目的显示  (布局和数据的绑定)
		 */
		ViewPager vp = (ViewPager) findViewById(R.id.vp);
		//数据源
		ArrayList<View> list = new ArrayList<View>();
	
		// 布局加载器 把  xml 布局 加载成  view 对象
		View v1 = LayoutInflater.from(this).inflate(R.layout.vp1, null);
		View v2 = LayoutInflater.from(this).inflate(R.layout.vp2, null);
		View v3 = LayoutInflater.from(this).inflate(R.layout.vp3, null);
		View v4 = LayoutInflater.from(this).inflate(R.layout.vp4, null);
		
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		
		
		//适配器
		MyVp myVp = new MyVp(list);
		
		vp.setAdapter(myVp);
		
		
	}
}
