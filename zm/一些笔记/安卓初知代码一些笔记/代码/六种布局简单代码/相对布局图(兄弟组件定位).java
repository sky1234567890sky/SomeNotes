<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:android="http://schemas.android.com/apk/res/android">
    
	<TextView 
	    android:id="@+id/tv1"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_centerInParent="true"
	    android:layout_margin="100dp"
	    android:text="����"/>

	<TextView
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_above="@+id/tv1"
	    android:layout_alignRight="@+id/tv1"
	    android:text="����" />
    
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_below="@id/tv1"
	    android:layout_alignRight="@id/tv1"
	    android:text="����"/>
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_toLeftOf="@id/tv1"
	    android:layout_alignTop="@id/tv1"
	    android:text="���"/>
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_toRightOf="@id/tv1"
	    android:layout_alignTop="@id/tv1"
	    android:text="�ұ�"/>
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_above="@id/tv1"
	    android:layout_toLeftOf="@id/tv1"
	    android:text="����"
	    />
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_above="@id/tv1"
	    android:layout_toRightOf="@id/tv1"
	    android:text="����"
	    />
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_below="@id/tv1"
	    android:layout_toLeftOf="@id/tv1"
	    android:text="����"
	    />
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_below="@id/tv1"
	    android:layout_toRightOf="@id/tv1"
	    android:text="����"
	    />
	
	
</RelativeLayout>