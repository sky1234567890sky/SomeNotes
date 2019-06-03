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

	//��ʼ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*	
		 * 	ViewPager  v4��
		 *   ViewPager   ���ص���  ����      xml ����    �Ե��ռ�
		 *   	������
		 *   	����Դ           ����    View ����  ���ֶ���
		 *   		���ƽ��������
		 *   		��Ӧ���±�ͽ���
		 *   		������ʧ����
		 *   		������ʾ ����
		 *   
		 *   ListView    ������һ������
		 * 		������
		 * 		����Դ	 ����  ��������   ʵ����
		 * 		   �ؼ���Ŀ��
		 * 		 ������Ŀ���±�
		 * 		 ������Ŀ������
		 * 	           ������Ŀ����ʾ  (���ֺ����ݵİ�)
		 */
		ViewPager vp = (ViewPager) findViewById(R.id.vp);
		//����Դ
		ArrayList<View> list = new ArrayList<View>();
	
		// ���ּ����� ��  xml ���� ���س�  view ����
		View v1 = LayoutInflater.from(this).inflate(R.layout.vp1, null);
		View v2 = LayoutInflater.from(this).inflate(R.layout.vp2, null);
		View v3 = LayoutInflater.from(this).inflate(R.layout.vp3, null);
		View v4 = LayoutInflater.from(this).inflate(R.layout.vp4, null);
		
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		
		
		//������
		MyVp myVp = new MyVp(list);
		
		vp.setAdapter(myVp);
		
		
	}
}
