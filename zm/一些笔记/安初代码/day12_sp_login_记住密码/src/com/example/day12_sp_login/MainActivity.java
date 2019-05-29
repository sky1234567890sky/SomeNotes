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
			//获取账号  获取密码  显示到界面上
			String name = sp.getString("n", "");
			String paw = sp.getString("p", "");
			// 显示到界面上
			etname.setText(name);
			etpaw.setText(paw);
		}
		
		//设置勾选状态
		cb.setChecked(boolean1);
		
		btlogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		
		case R.id.btlogin:// 点击登录之后
			
			SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
			Editor edit = sp.edit();
			//获取界面的勾选状态
			boolean checked = cb.isChecked();
			if( checked ){
				// 要记住密码
				//获取界面上的  账号  密码
				String name = etname.getText().toString();// 不是 null   是 ""
				String paw = etpaw.getText().toString();
				edit.putString("n", name);
				edit.putString("p", paw);
			}

			//存储界面上的  勾选状态
			edit.putBoolean("issave", checked);
			edit.commit();// 提交
			
			break;

		default:
			break;
		}
		
	}
}
