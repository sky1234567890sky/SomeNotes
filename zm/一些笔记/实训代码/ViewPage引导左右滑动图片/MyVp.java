package com.example.test1;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyVp extends PagerAdapter{

	ArrayList<View> list;

	public MyVp(ArrayList<View> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	//对应的下标和界面
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	//滑 来显示的
	// position  将要显示的  view 的 下标
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		
		View view = list.get(position);
		
		container.addView(view);
		return view;
	}
	
	//滑走消失界面
	// position  要滑走的  对应的   条目的  下标
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//super.destroyItem(container, position, object);
		
		View view = list.get(position);
		
		container.removeView(view);;
	}

}





