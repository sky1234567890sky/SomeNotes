按钮,编辑文本,(单选,多选)按钮


// 布局文件

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.demo1.MainActivity" 
    android:orientation="vertical">

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/按钮"
        android:background="@drawable/selector_anniu" />

    
    <EditText 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:password="true"
        android:hint="请输入密码"/>
    
    <EditText 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:phoneNumber="true"
        android:hint="请输入电话号码"/>
    
    <EditText 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:numeric="integer"
        android:hint="请输入数字"/>
    
    
    <RadioGroup 
        android:id="@+id/rg"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        
    <RadioButton 
        android:id="@+id/nan"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="男"
        android:checked="true"
        android:gravity="center"
        />
    
    <RadioButton
		android:id="@+id/nv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="女"/>
    
    </RadioGroup>
    
    
    
    <CheckBox 
        android:id="@+id/zuqiu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="足球"
        />
    <CheckBox 
        android:id="@+id/lanqiu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="篮球"
        />
    <CheckBox 
        android:id="@+id/yumaoqiu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="羽毛球"
        />
    
    <Button 
        android:id="@+id/btaihao"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="你得爱好"
        />
    
    <TextView 
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="你喜欢的运动是: "/>
    
    
</LinearLayout>


// java文件

package com.example.demo1;

import android.app.Activity;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 通过id获取多选按钮的对象
		final CheckBox zuqiu = (CheckBox) findViewById(R.id.zuqiu);
		final CheckBox lanqiu = (CheckBox) findViewById(R.id.lanqiu);
		final CheckBox yumaoqiu = (CheckBox) findViewById(R.id.yumaoqiu);

		// 通过id获取文本的对象
		final TextView tv = (TextView) findViewById(R.id.tv);

		// String string = tv.getText().toString();

		// String buffer = string;
		//
		// if (zuqiu.isChecked()) {
		// buffer += zuqiu.getText() + ",";
		// tv.setText(buffer);
		// }
		// if (lanqiu.isChecked()) {
		// buffer += lanqiu.getText() + ",";
		// }
		// if (yumaoqiu.isChecked()) {
		// buffer += yumaoqiu.getText() + ",";
		// }
		//
		// tv.setText(buffer);

		zuqiu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					String string1 = zuqiu.getText().toString();
					Toast.makeText(MainActivity.this, "你的爱好是:" + string1, Toast.LENGTH_SHORT).show();
					tv.setText(tv.getText() + string1 + " ");
				}
			}
		});

		lanqiu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					String string2 = lanqiu.getText().toString();
					Toast.makeText(MainActivity.this, "你的爱好是:" + string2, Toast.LENGTH_SHORT).show();
					tv.setText(tv.getText() + string2 + " ");
				}
			}
		});

		yumaoqiu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					String string3 = yumaoqiu.getText().toString();
					Toast.makeText(MainActivity.this, "你的爱好是:" + string3, Toast.LENGTH_SHORT).show();
					tv.setText(tv.getText() + string3 + " ");
				}
			}
		});

		// 通过id获取单选框的对象
		RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
		// 单选框监听
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (R.id.nan == checkedId) {
					Toast.makeText(MainActivity.this, "你是男的", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "你是女的", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

}


