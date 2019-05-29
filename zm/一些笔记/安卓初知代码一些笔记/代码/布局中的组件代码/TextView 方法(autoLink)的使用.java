    <!-- 布局文件  -->
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

    <TextView 
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="www.baidu.com"
        android:textSize="25dp"
        android:autoLink="web"/>
    
    
    <TextView 
        android:id="@+id/tv2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="13453482548"
        android:textSize="25dp"
        android:autoLink="phone"/>
    
    
    <TextView 
        android:id="@+id/tv3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="www.@1324795410qq.com.cn"
        android:textSize="25dp"
        android:autoLink="email"/>
    

    <TextView 
        android:id="@+id/tv4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="山西省,翼城市,隆化镇,隆化村"
        android:textSize="25dp"
        android:drawableLeft="@drawable/ic_launcher"
        android:autoLink="map"/>    
    
</LinearLayout>