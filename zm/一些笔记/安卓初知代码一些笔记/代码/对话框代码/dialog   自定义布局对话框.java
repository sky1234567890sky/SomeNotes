dialog   自定义布局对话框

xml文件
	1.自定义要使用的对话框布局(dialog.xml)
	<EditText 
        android:id="@+id/edit"
        android:layout_width="250dp"
        android:layout_height="wrap_content"
        />
    
    <Button 
        android:id="@+id/clickbtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="click me"/>
	
	
	
	2.页面布局(activity_dialog.xml)
	<TextView 
        android:id="@+id/edit"
        android:layout_width="250dp"
        android:layout_height="wrap_content"
        android:text=""
        />
    
    <Button 
        android:id="@+id/clickbtn"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="click me"/>

java 文件
	1. 创建MyCustomDialog类继承Dialog
	代码：
	
	package com.example.text5;

	import android.app.Dialog;
	import android.content.Context;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;

	//自定义dialog
	public class MyCustomDialog extends Dialog {
		
		
		
		//定义回调事件，用于dialog的点击事件             	interface 接口
		public interface OnCustomDialogListener{
			public void back(String name);
		}
		
		private String name;
		private OnCustomDialogListener customDialogListener;
		EditText etName;
		
		//添加有参构造方法
		public MyCustomDialog(Context context, String name, OnCustomDialogListener customDialogListener) {
			super(context);
			this.name = name;
			this.customDialogListener = customDialogListener;
		}
		
		//使用onCreate方法
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			//设置要使用的布局
			setContentView(R.layout.dialog);
		
			//设置标题
			setTitle(name);
			//获得布局文件中EditText 和 Button 的对象
			etName = (EditText) findViewById(R.id.edit);
			Button clickbtn = (Button) findViewById(R.id.clickbtn);
			//设置按钮的监听方法
			clickbtn.setOnClickListener(clickListener);
			
		}
		
		//监听事件
		private View.OnClickListener clickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//把editText中的数据添加到 back方法中
				customDialogListener.back(String.valueOf(etName.getText()));
				//发送这个类    dismiss 驳回，发送，撤职
				MyCustomDialog.this.dismiss();
			}
		};
		

		
		
	}
	
	2. MainActivity.java
	代码：
	
	package com.example.text5;

	import android.app.Activity;
	import android.app.Dialog;
	import android.os.Bundle;
	import android.view.Menu;
	import android.view.MenuItem;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.TextView;

	public class DialogActivity extends Activity implements OnClickListener{

		private TextView et;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_dialog);
			
			et = (TextView) findViewById(R.id.edit);
			Button bt = (Button) findViewById(R.id.clickbtn);
			bt.setOnClickListener(this);
			
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			MyCustomDialog dialog = new MyCustomDialog(this, "Enter your name", new MyCustomDialog.OnCustomDialogListener() {
				
				@Override
				public void back(String name) {
					// TODO Auto-generated method stub
					
					et.setText("Enter name is "+name);
					//et.setText(name);
					
				}
			});
			dialog.show();
			
		}


	}