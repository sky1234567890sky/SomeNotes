    // �����ļ� 
	
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

    <TextView 
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="����һ���ı�"/>
    
</LinearLayout>

	//java����
	
package com.example.lianxi1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        //��ȡid����
        final TextView tv = (TextView) findViewById(R.id.tv1);
        //ͨ��������������
        tv.setTextSize(50);
        
        //��������
        tv.setOnClickListener(new OnClickListener() {
			
        	
			@Override
			//ʹ�ü����ķ���
			public void onClick(View arg0) {
				//ͨ��������ֻ�ȡlog��Ϣ
				Log.i("message", "����ɹ�");
				//����¼�
				Toast.makeText(MainActivity.this, "��ʾ��Ϣ", Toast.LENGTH_SHORT).show();
			}
		});
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
	
	