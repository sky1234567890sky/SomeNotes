    <!-- 布局文件  -->
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    
	<!-- 
	android:scaleType="center"  (居中,不变)
		保持原图的大小，显示在ImageView的中心。当原图的size大于ImageView的size，超过部分裁剪处理。
		
	android:scaleType="centerCrop"  (居中,填满)
		以填满整个ImageView为目的，将原图的中心对准ImageView的中心，等比例放大原图，直到填满ImageView为止（指的是ImageView的宽和高都要填满），原图超过ImageView的部分作裁剪处理。
	
	android:scaleType="centerInside"  (居中,等比例放大缩小)
		以原图完全显示为目的，将图片的内容完整居中显示，通过按比例缩小原图的size宽(高)等于或小于ImageView的宽(高)。
		如果原图的size本身就小于ImageView的size，则原图的size不作任何处理，居中显示在ImageView。  
	
	android:scaleType="matrix"  (不变,左上角)
		不改变原图的大小，从ImageView的左上角开始绘制原图，原图超过ImageView的部分作裁剪处理。  
	
	android:scaleType="fitCenter"  (等高,居中)
		把原图按比例扩大或缩小到ImageView的ImageView的高度，居中显示   
	
	android:scaleType="fitEnd"  (等高,右边)
		把原图按比例扩大(缩小)到ImageView的高度，显示在ImageView的下部分位置 
	
	android:scaleType="fitStart"  (等高,左边)
		把原图按比例扩大(缩小)到ImageView的高度，显示在ImageView的上部分位置 
	
	 -->
	 
	 
     <ImageView 
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="center"
        />
    
    <ImageView 
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="centerCrop"
        />
    
    <ImageView 
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="centerInside"
        /> 

<!--     <ImageView 
        android:layout_width="250dp"
        android:layout_height="80dp"
        android:src="@drawable/cache_clear"
        android:scaleType="matrix"
        />
    
    <ImageView 
        android:layout_width="250dp"
        android:layout_height="80dp"
        android:src="@drawable/cache_clear"
        android:scaleType="fitCenter"
        /> -->
    
<!--     <ImageView 
        android:layout_width="250dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="fitStart"
        />
    
    <ImageView 
        android:layout_width="250dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="fitEnd"
        /> -->
</LinearLayout>