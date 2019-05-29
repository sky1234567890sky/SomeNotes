dialog   �Զ��岼�ֶԻ���

xml�ļ�
	1.�Զ���Ҫʹ�õĶԻ��򲼾�(dialog.xml)
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
	
	
	
	2.ҳ�沼��(activity_dialog.xml)
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

java �ļ�
	1. ����MyCustomDialog��̳�Dialog
	���룺
	
	package com.example.text5;

	import android.app.Dialog;
	import android.content.Context;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;

	//�Զ���dialog
	public class MyCustomDialog extends Dialog {
		
		
		
		//����ص��¼�������dialog�ĵ���¼�             	interface �ӿ�
		public interface OnCustomDialogListener{
			public void back(String name);
		}
		
		private String name;
		private OnCustomDialogListener customDialogListener;
		EditText etName;
		
		//����вι��췽��
		public MyCustomDialog(Context context, String name, OnCustomDialogListener customDialogListener) {
			super(context);
			this.name = name;
			this.customDialogListener = customDialogListener;
		}
		
		//ʹ��onCreate����
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			//����Ҫʹ�õĲ���
			setContentView(R.layout.dialog);
		
			//���ñ���
			setTitle(name);
			//��ò����ļ���EditText �� Button �Ķ���
			etName = (EditText) findViewById(R.id.edit);
			Button clickbtn = (Button) findViewById(R.id.clickbtn);
			//���ð�ť�ļ�������
			clickbtn.setOnClickListener(clickListener);
			
		}
		
		//�����¼�
		private View.OnClickListener clickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��editText�е�������ӵ� back������
				customDialogListener.back(String.valueOf(etName.getText()));
				//���������    dismiss ���أ����ͣ���ְ
				MyCustomDialog.this.dismiss();
			}
		};
		

		
		
	}
	
	2. MainActivity.java
	���룺
	
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