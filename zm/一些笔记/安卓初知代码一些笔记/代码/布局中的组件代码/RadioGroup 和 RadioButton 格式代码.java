 <!-- 布局文件 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >

    
    <RadioGroup
        android:id="@+id/xb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical" >
    
	<RadioButton 
	    android:id="@+id/nan"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="男"/>
	
	<RadioButton 
	    android:id="@+id/nv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="女"/>
    
	</RadioGroup>
</LinearLayout>


//java文件

package com.example.lianxi1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RadioGroup xb = (RadioGroup) findViewById(R.id.xb);
		
		xb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (R.id.nan == arg1) {
					Toast.makeText(MainActivity.this, "你是男的", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "你是女的", Toast.LENGTH_SHORT).show();
				}
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
