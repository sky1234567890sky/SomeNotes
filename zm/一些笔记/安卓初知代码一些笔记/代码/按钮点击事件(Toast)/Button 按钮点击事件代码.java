    <!-- �����ļ�  -->
	
	
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

    <Button 
        android:id="@+id/anniu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��ť"/>
    
</LinearLayout>




   <!-- java�ļ�  -->


package com.example.lianxi1;

//Log ���ǳ��������еĹ���,ȫ����¼����,������������ά��������,�������ύbug����bug����
//    �Ժ��д��Android�������ٴ�ӡ������̨��,���������е�����Ҫʹ��LogCat

//Android�е�Log��Ϊ5���ȼ�
// i Ӧ�����в��趼���¼��������־�ļ�                               ��ɫ
// v 
// e ����ȼ�,���������й����г��ֵ�error�����д���         ��ɫ
// w warning ����ȼ�								   ��ɫ
// d debug�ȼ�									   ��ɫ

// Toast ��˾  ��ʾ��Ϣ
// context,	������ 
// text, ��Ϣ
// duration ����ʱ��

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //ͨ��ָ���ķ�����ò����ļ���id����
        Button bt = (Button) findViewById(R.id.anniu);
        
        //��������,����¼�
        bt.setOnClickListener(new OnClickListener() {
			
			//���ð�ť�ĵ���¼�
			public void onClick(View arg0) {
				
				Log.i("message", "����ɹ�");
				
				Toast.makeText(MainActivity.this, "��ʾ��Ϣ", Toast.LENGTH_SHORT).show();

			}
		});
    
    }


}