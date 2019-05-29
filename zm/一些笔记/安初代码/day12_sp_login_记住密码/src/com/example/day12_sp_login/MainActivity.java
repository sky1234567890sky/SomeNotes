package com.example.day12_sp_login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	private EditText etname;
	private EditText etpaw;
	private CheckBox cb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btlogin = (Button) findViewById(R.id.btlogin);
		cb = (CheckBox) findViewById(R.id.cb);
		Button btregist = (Button) findViewById(R.id.btregist);
		
		etname = (EditText) findViewById(R.id.etname);
		etpaw = (EditText) findViewById(R.id.etpaw);
		
		SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
		boolean boolean1 = sp.getBoolean("issave", false);
		if(boolean1){
			//��ȡ�˺�  ��ȡ����  ��ʾ��������
			String name = sp.getString("n", "");
			String paw = sp.getString("p", "");
			// ��ʾ��������
			etname.setText(name);
			etpaw.setText(paw);
		}
		
		//���ù�ѡ״̬
		cb.setChecked(boolean1);
		
		btlogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		
		case R.id.btlogin:// �����¼֮��
			
			SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
			Editor edit = sp.edit();
			//��ȡ����Ĺ�ѡ״̬
			boolean checked = cb.isChecked();
			if( checked ){
				// Ҫ��ס����
				//��ȡ�����ϵ�  �˺�  ����
				String name = etname.getText().toString();// ���� null   �� ""
				String paw = etpaw.getText().toString();
				edit.putString("n", name);
				edit.putString("p", paw);
			}

			//�洢�����ϵ�  ��ѡ״̬
			edit.putBoolean("issave", checked);
			edit.commit();// �ύ
			
			break;

		default:
			break;
		}
		
	}
}
