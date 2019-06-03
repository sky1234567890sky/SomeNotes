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
	//��Ӧ���±�ͽ���
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	//�� ����ʾ��
	// position  ��Ҫ��ʾ��  view �� �±�
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		
		View view = list.get(position);
		
		container.addView(view);
		return view;
	}
	
	//������ʧ����
	// position  Ҫ���ߵ�  ��Ӧ��   ��Ŀ��  �±�
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//super.destroyItem(container, position, object);
		
		View view = list.get(position);
		
		container.removeView(view);;
	}

}





