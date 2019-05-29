// Button 按钮组件

-- 概念

按钮在人机交互界面上使用的是最多的，当提示用户进行某些选择的时候，就可以通过按钮的操作来接收用户的选择。
在Android使用“<Button>”组件可以定义出一个显示的按钮，并且可以在按钮上指定相应的显示文字，

Button类的继承结构如下：

	java.lang.Object
		android.view.View
			android.widget.TextView
				android.widget.Button 


-- 常用属性

	android:id="@+id/mybut1"  //定义此按钮组件的ID，为Activity程序使用

    setText();              //设置文本内容，同xml中的android:text
    
	setTextSize();        //设置文本字体大小，同xml中的android:textSize
    
    setTextColor();      //设置文本颜色，同xml中的android:textColor
    
	setBackgroundColor();  //设置背景颜色，同xml中的 
   
    android:background  //设置背景颜色或者图片
   
    android:gravity         //设置文本位置，设置成"center"，文本将居中显示
    
	设置字体大小推荐使用sp作为单位
	设置宽度或高度等属性时推荐使用dp(dip)作为单位
