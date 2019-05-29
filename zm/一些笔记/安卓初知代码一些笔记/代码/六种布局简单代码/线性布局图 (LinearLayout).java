//LinearLayout  线性布局

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/lin"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal" >

	<!-- 
	android:gravity="right|bottom"	右下
     top/bottom/left/right  上/下/左/右	可以两个一起用
     background 背景图片
     weight 权重
	 -->


    <!--
         <Button 
        android:id="@+id/bu"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="按钮"/>
    -->

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="2"
        android:background="@android:color/holo_green_dark"
        android:text="1" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:background="@android:color/holo_blue_dark"
        android:text="2" />

</LinearLayout>
