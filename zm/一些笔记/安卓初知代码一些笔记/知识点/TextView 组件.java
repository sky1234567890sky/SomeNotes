
// TextView组件

-- 概念:

TextView组件的主要功能是用于显示文本，
	实际上这种控件主要就是提供了一个标签的显示操作，此类定义如下： 
	java.lang.Object
		android.view.View
			android.widget.TextView

TextView是一个用于显示丰富的文本信息的控件


-- 常用属性	
	
	setText();           //设置文本内容，同xml中的android:text
    setTextSize();       //设置文本字体大小，同xml中的android:textSize
　　setTextColor();      //设置文本颜色，同xml中的android:textColor
　　setBackgroundColor();  //设置背景颜色，同xml中的android:background   
	android:background  //设置背景颜色或者图片
    android:gravity         //设置文本位置，设置成"center"，文本将居中显示
	
	设置字体大小推荐使用sp作为单位
	设置宽度或高度等属性时推荐使用dp(dip)作为单位

-- 点击事件连接属性:
	
	android:autoLink     //设置是否显示为可点击的链接。可选值(none/web/email/phone/map/all)
	android:linksClickable          //设置点击时是否链接，即使设置了autoLink
	
-- 插入图片属性:

	android:drawableBottom      //在text的下方输出一个drawable(图片)

　　android:drawableLeft           //在text的左边输出一个drawable(图片)

　　android:drawableRight         //在text的右边输出一个drawable(图片)

　　android:drawableTop           //在text的正上方输出一个drawable(图片)

　　android:drawablePadding     //设置text与drawable(图片)的间隔，与drawableLeft、drawableRight、drawableTop、drawableBottom一起使用，可设置为负数，单独使用没有效果


-- 设置文本的属性

	android:ellipsize       //设置当文字过长时，该控件该如何显示。可设置如下属性值："start"省略号显示在开头;"end”省略号显示在结尾;"middle"省略号显示在中间; "marquee" 以跑马灯的方式显示(动画横向移动)

	android:marqueeRepeatLimit     //在ellipsize设定为marquee时，设置重复滚动的次数，设置为marquee_forever时表示无限次。

　　android:lines                      //设置文本的行数，设置两行就显示两行，即使第二行没有数据

　　android:shadowRadius         //设置阴影的半径。设置为0.1就变成字体的颜色了，一般设置为3.0的效果比较好

　　android:shadowColor           //指定文本阴影的颜色，需要与shadowRadius一起使用

　　android:singleLine               //设置单行显示

　　android:textColorLink           //设置文字链接的颜色

　　android:textScaleX              //设置文字之间间隔，默认为1.0f

　　android:textStyle                //设置字形 bold(粗体) 0, italic(斜体) 1, bolditalic(又粗又斜) 2, 可以设置一个或多个，用“|”隔开

　　android:typeface                 //设置文本字体，必须是以下常量值之一：normal 0, sans 1, serif 2, monospace(等宽字体) 3

	android:maxLength		//设置文本的个数

跑马灯效果:

	android:ellipSize设置文字过长时，该控件是如何显示的呢？

    start—省略号显示在开头

	end—省略号显示在结尾

	middle—省略号显示在中间

	marquee—以跑马灯的方式显示

<!--无数次的跑动-->

	android:marqueeRepeatLimit=”marquee_forever”

<!--触摸时获得焦点-->

	android:focuseableTouchMode=”true”

	
	
	
	