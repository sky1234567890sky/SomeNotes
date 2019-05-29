    <!-- 布局文件  -->
	
	
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

	<Button 
	    android:layout_width="100dp"
	    android:layout_height="100dp"
	    android:text="按钮"
	    android:background="@drawable/my_selector"
	    />
    
</LinearLayout>



	<!-- selector设置背景图片的文件  -->

	
<!-- 创建路径  在res文件夹下 -- 创建一个文件夹 -- 创建一个xml文件
		
		 -- 使用 selector 组件布局 -- 使用item 设置背景图片   -->	
	
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
      
    
    <!-- 设置按键按下以后使用的图片  -->
    
    <item 
        android:state_pressed="true"			//state_pressed		按下的状态，true为按下，false为非按下
        android:drawable="@drawable/cache_clear"
        ></item>

    
    <item
        android:state_pressed="false"
        android:drawable="@drawable/settings_center">
    </item>
    
</selector>
	
	