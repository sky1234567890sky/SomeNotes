补间动画

1. alpha 	透明度渐变
   scale 	缩放渐变
   translate	 位移渐变
   rotate 	旋转渐变
   set	 组合渐变
   
   在res下的anim文件夹中创建xml文件,以上的为所有的组件
   
<set xmlns:android="http://schemas.android.com/apk/res/android">
    
    <!-- 缩放渐变  -->
	<scale 
	    android:fromXScale="0.2"
	    android:fromYScale="0.2"
	    android:toXScale="1.5"
	    android:toYScale="1.5"
	    android:pivotX="50%"
	    android:pivotY="50%"
	    android:duration="2000"/>
	
	<!-- 旋转渐变  -->
	<rotate 
	    android:fromDegrees="0"
	    android:toDegrees="360"
	    android:duration="1000"
	    android:repeatCount="1"
	    android:repeatMode="reverse"/>
	
	<!-- 位移渐变  -->
	<translate 
	    android:fromXDelta="0"
	    android:fromYDelta="0"
	    android:toXDelta="320"
	    android:toYDelta="0"
	    android:duration="2000"/>
	
    <!-- 透明度渐变  -->
    <alpha 
        android:fromAlpha="1"
        android:toAlpha="0"
        android:duration="2000"/>
    

</set>  


2. 在布局文件中设置按钮组件

	例如：
	<Button 
        android:id="@+id/set"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="Zh"
        android:text="组合渐变"/>
		
		
3. 在java文件中设置		
 
	private ImageView iv;
	private Animation animation5;
 
   //组合渐变
	public void Zh(View v){
		//通过id值获取图片的对象
		iv = (ImageView) findViewById(R.id.iv);
		//创建动画对象，并传入设置的动画效果的xml文件
		animation5 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_set);
		//播放动画
		iv.startAnimation(animation5);
	}
   
   
   
