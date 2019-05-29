    <!-- 布局文件  -->
<LinearLayout
    android:paddingLeft="20dp"
    android:paddingRight="20dp"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   android:background="@android:color/black"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="限制输入内容:1234567890.+-*/%()"
	    android:digits="1234567890.+-*/%()"
	    android:background="@android:color/white"/>
	
	<Button 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="确定"/>
	
	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="限制输入内容:只接受数字"
	    android:numeric="integer"
	    android:background="@android:color/white"
    />
	     
	<Button 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="确定"/>
	
	
	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="限制输入内容:显示为带*好的密码"
	    android:password="true"
	    android:background="@android:color/white"/>
 
	<Button 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="确定"/>
	
	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="设置EditText文字颜色"
	    android:textColor="#EA2A2A"
	    android:background="@android:color/white"/>
	
	<EditText 
	    android:layout_marginTop="25dp"
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="设置EditText不可被编辑"
	    android:enabled="false"
	    android:background="@android:color/white"/>
	
</LinearLayout>