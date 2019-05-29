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
	    android:text="居中"/>

	<TextView
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_above="@+id/tv1"
	    android:layout_alignRight="@+id/tv1"
	    android:text="上中" />
    
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_below="@id/tv1"
	    android:layout_alignRight="@id/tv1"
	    android:text="下中"/>
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_toLeftOf="@id/tv1"
	    android:layout_alignTop="@id/tv1"
	    android:text="左边"/>
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_toRightOf="@id/tv1"
	    android:layout_alignTop="@id/tv1"
	    android:text="右边"/>
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_above="@id/tv1"
	    android:layout_toLeftOf="@id/tv1"
	    android:text="左上"
	    />
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_above="@id/tv1"
	    android:layout_toRightOf="@id/tv1"
	    android:text="右上"
	    />
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_below="@id/tv1"
	    android:layout_toLeftOf="@id/tv1"
	    android:text="左下"
	    />
	
	<TextView 
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:layout_below="@id/tv1"
	    android:layout_toRightOf="@id/tv1"
	    android:text="右下"
	    />
	
	
</RelativeLayout>