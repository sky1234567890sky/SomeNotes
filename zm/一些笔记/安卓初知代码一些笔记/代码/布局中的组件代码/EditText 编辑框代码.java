    <!-- �����ļ�  -->
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
	    android:hint="������������:1234567890.+-*/%()"
	    android:digits="1234567890.+-*/%()"
	    android:background="@android:color/white"/>
	
	<Button 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="ȷ��"/>
	
	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="������������:ֻ��������"
	    android:numeric="integer"
	    android:background="@android:color/white"
    />
	     
	<Button 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="ȷ��"/>
	
	
	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="������������:��ʾΪ��*�õ�����"
	    android:password="true"
	    android:background="@android:color/white"/>
 
	<Button 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="ȷ��"/>
	
	<EditText 
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="����EditText������ɫ"
	    android:textColor="#EA2A2A"
	    android:background="@android:color/white"/>
	
	<EditText 
	    android:layout_marginTop="25dp"
	    android:layout_width="fill_parent"
	    android:layout_height="50dp"
	    android:hint="����EditText���ɱ��༭"
	    android:enabled="false"
	    android:background="@android:color/white"/>
	
</LinearLayout>