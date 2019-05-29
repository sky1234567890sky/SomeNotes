	//一、ProgressBar
	
一:概述

	进度条是UI界面中的一种非常实用的组件，通常向用户显示某个耗时操作完成的百分比。
	因此进度条可以动态地显示进度，避免长时间地执行某个耗时操作时，让用户感觉程序失去了响应，
	从而更好地提高用户界面的友好性。
	
	
二:方法
	
Android支持几种风格的进度条，通过style属性可以为ProgressBar指定风格，该属性支持如下几个属性值。

       style=“?android:attr/progressBarStyle”? 圆形进度条
       style=“?android:attr/progressBarStyleHorizontal”?水平进度条
       style=“?android:attr/progressBarStyleInverse”?圆形普通大小进度条
       style=“?android:attr/progressBarStyleLarge”? 圆形大进度条
       style=“?android:attr/progressBarStyleLargeInverse”?圆形大进度条

	   style=“?android:attr/progressBarStyleSmall”?小圆形进度条
	   style=“?android:attr/progressBarStyleSmallInverse”? 普通大圆形小进度条
	   style=“?android:attr/progressBarStyleSmallTitle”?带标题的圆形小进度条
	
ProgressBar支持如下表的.xml属性:

       android:max://设置该进度条的最大值
       android：progress//设置该进度条的已完成进度值
       android:indeterminate//该属性设置为true, 设置进度条不精确显示进度
       android:indeterminateDrawable//设置绘制不显示进度的Drawable对象
	   android:secondaryProgress //预加载进度	
	   
ProgressBar的重要方法:

      getMax()：//返回这个进度条的范围的上限
	  getProgress()：//返回进度值  setProgress()：
	  getSecondaryProgress()：//返回次要进度值 setSecondaryProgress()：
      incrementProgressBy(int diff)：//指定增加的进度，正数为增加进度，负数为减去进度
      isIndeterminate()：//判断进度条是否在不精确显示进度模式下
      setIndeterminate(boolean indeterminate)：//设置进度条不精确显示模式
      setVisibility(int v)：//设置该进度条是否可视		值有：visible，invisible，gone  分别为：显示；不显示（占行，隐藏）；不显示（删除）
 
 
重要事件:

　onSizeChanged(int w, int h, int oldw, int oldh)：当进度值改变时引发此事件
 