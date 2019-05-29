//SeekBar  拖动条

拖动条概述:

	拖动条和进度条非常相似，只是进度条采用颜色填充来表明进度完成的程度，
	而拖动条则通过滑块的位置来标识数值----而且拖动条允许用户拖动滑块来改变值，
	因此拖动条通常用于对系统的某种数值进行调节，比如调节音量等。
	
	SeekBar的主要应用在音乐播放，视频播放，或者对音量调节，屏幕亮度调节的一些操作中，
	让用户可以手动的去改变相应的值。

	
Seekbar的重要属性和方法：

	该组件的属性:
	
	android:max		//设置范围最大值
	android:progress	//设置当前进度值
	android:secondaryProgress	//设置当前次进度值
	android:progressDrawable	//设置进度条的图片
	android:thumb		//设置进度条的滑块的图片
	
	常用的方法:
	
	getMax()
	getProgress()
	setMax(int)
	setOnseekBarChangeListener(SeekBar.OnSeekBarChangeListener I)  
	
	SeekBar.OnSeekBarChangeListener 中可以监听三种事件:
	1.数值的改变(onProgressChanged)
	2.开始拖动(onStartTrackingTouch)
	3.停止拖动(onStopTrackingTouch)
	
	
	