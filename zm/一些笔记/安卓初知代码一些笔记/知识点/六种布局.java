//布局可以嵌套使用
//六种布局:
	LinearLayout (线性布局)
	FrameLayout (单帧布局)
	AbsoluteLayout (绝对布局)
	TableLayout (表格布局)
	RelativeLayout (相对布局)
	GridLayout (网格布局)

-- LinearLayout 线性布局

	线性布局可以控制组件的排列方式:
	
		orientation 此属性可以控制布局的方向
		水平布局
			orientation="horizontal"
		垂直布局
			orientation="vertical"	
		
		gravity 此属性可以控制组件的对齐方式
			gravity="left" 
			值有:top/buttom/left/right
			可以组合使用
			
	//常用属性	
		layout_gravity	此属性可以控制组件在父容器里的对齐方式
			layout_gravity="center"
			
		layout_width 和 layout_height 此属性控制布局的宽与高
			值有:fill_parent/match_parent/wrap_content
			分别是:装满父组件/填满该组件的父组件/等比例填充
			
		background 为组件添加一个背景图片或一个背景颜色

		id 为组件添加一个资源id	,在java文件中可以通过 findViewById(id) 调用该组件
		
	//Weight 权重
		该属性是用来按等比例来划分区域的
	
	//为LinearLayout设置分割线
		很多界面开发中都会设置一些下划线,或者分割线,从而使得界面更加整洁美观,比如下面的酷狗 音乐的注册页面:
		
		对于这种线,我们通常的做法有两种:
		①直接在布局中添加一个view,这个view的作用仅仅是显示出一条线,代码也很简单:
		<View  
		android:layout_width="match_parent"  
		android:layout_height="1px"  
		android:background="#000000" />  
		
		这个是水平方向上的黑线,当然你也可以改成其他颜色,或者使用图片
		
		②第二种则是使用LinearLayout的一个divider属性,直接为LinearLayout设置分割线 这里就需要你自己准备一张线的图片了 
		1)android:divider设置作为分割线的图片 
		2)android:showDividers设置分割线的位置,none(无),beginning(开始),end(结束),middle(每两个组件间) 
		3)dividerPadding设置分割线的Padding
		
		代码如下:
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
		xmlns:tools="http://schemas.android.com/tools"  
		android:id="@+id/LinearLayout1"  
		android:layout_width="match_parent"  
		android:layout_height="match_parent"  
		android:divider="@drawable/ktv_line_div"  
		android:orientation="vertical"  
		android:showDividers="middle"  
		android:dividerPadding="10dp"  
		tools:context="com.jay.example.linearlayoutdemo.MainActivity" >  
	  
		<Button  
			android:layout_width="wrap_content"  
			android:layout_height="wrap_content"  
			android:text="按钮1" />  
	  
		<Button  
			android:layout_width="wrap_content"  
			android:layout_height="wrap_content"  
			android:text="按钮2" />  
	  
		<Button  
			android:layout_width="wrap_content"  
			android:layout_height="wrap_content"  
			android:text="按钮3" />  
  
		</LinearLayout>
		
	//注意事项
		使用Layout_gravity的一个很重要的问题!!! 
		问题内容: 在一个LinearLayout的水平方向中布置两个TextView,想让一个左,一个右,怎么搞?
		或许你会脱口而出:"gravity设置一个left,一个right就可以啦!" 
		真的这么简单?你试过吗?写个简单的Layout你就会发现,事与愿违了:
		
		当 android:orientation="vertical" 时， 只有水平方向的设置才起作用，垂直方向的设置不起作用。
		即：left，right，center_horizontal 是生效的。 
		当 android:orientation="horizontal" 时， 只有垂直方向的设置才起作用，水平方向的设置不起作用。
		即：top，bottom，center_vertical 是生效的。
	
	
-- RelativeLayout 相对布局
		
		相对布局是按照组件之间的相对位置来布局
		
		子元素通过这些属性和各自的id配合指定为关系,在指定关系是,引用的id必须在引用之前,先被定义,否则将出现异常.
		
	//基本属性
		gravity 设置容器内组件的对齐方式
		ignoreGravity 设置该属性为true时的组件,将不受gravity属性的影响
		
	//根据父容器定位
		layout_alignParentTop/Bottom/Left/Right  顶部/底部/左/右对齐
		layout_centerHorizontal 水平居中
		layout_centerVertical 垂直居中
		layout_centerInParent 中间位置
	//根据兄弟组件定位
		layout_toLeftOf 参考组件的左边
		layout_toRightOf 参考组件的右边
		layout_above 参考组件的上方
		layout_below 参考组件的下方
		layout_alianTop/Bottom/Left/Right 对齐参考组件的上/下/左/右边界
	//margin(偏移) 设置组件与父容器的边距
		layout_margin 设置组件四边的偏移量
		layout_marginTop/Button/Left/Right 设置组件离上/下/左/右面的偏移量
	//padding(填充) 设置组件内部元素键的边距
		padding 往内部元素的上下左右(四边)填充一定边距
		paddingTop/Button/Left/Right 往内部元素的上/下/左/右填充一定的边距
		
	
-- FrameLayout 帧布局
		
		帧布局是从屏幕的左上角(0,0)坐标开始布局，多个组件层叠排列，第一个添加的组件放到最底层，最后添加到框架中的视图显示在最上面。
		上一层的会覆盖下一层的控件。在这个布局中， 所有的元素都不能被指定放置的位置，他们统统放于这块区域的左上角，并且后面的子元素直接覆盖在前面的子元素上。

		实例代码如下:
		<?xml version="1.0" encoding="utf-8"?>  
		<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android" android:orientation="vertical" android:layout_width="fill_parent" android:layout_height="fill_parent">  
			<TextView android:layout_width="fill_parent" android:layout_height="fill_parent" android:background="#ff000000" android:gravity="center" android:text="1"/>  
			<TextView android:layout_width="fill_parent" android:layout_height="fill_parent" android:background="#ff654321" android:gravity="center" android:text="2"/>  
			<TextView android:layout_width="50dp" android:layout_height="50dp" android:background="#fffedcba" android:gravity="center" android:text="3"/>  
		</FrameLayout>  

	//前景图像:永远处于帧布局最上面,直接面对用户的图像,就是不会被覆盖的图片。

		两个属性:

		android:foreground:*设置改帧布局容器的前景图像
		android:foregroundGravity:设置前景图像显示的位置
	

-- AbsoluteLayout 绝对布局
		
	该布局又可以叫做坐标布局，可以直接指定子元素的绝对位置(x,y)
	但是由于手机屏幕尺寸差别较大，使用绝对定位的适应性会比较差，在屏幕的适配上有缺陷
	
	①控制大小: android:layout_width:组件宽度 android:layout_height:组件高度 
	②控制位置: android:layout_x:设置组件的X坐标 android:layout_y:设置组件的Y坐标
	
	注意：
     绝对布局通过指定子组件的确切X,Y坐标来确定组件的位置，在Android2.0 
     API文档中标明该类已经过期，可以使用FrameLayout或者RelativeLayout
     来代替。不作为重点学习的布局

	
-- TableLayout 表格布局

	表格布局是一个ViewGroup以表格显示它的子视图（view）元素，即行和列
    标识一个视图的位置。

	
	TableLayout 表格布局模型以行列的形式管理子控件，每一行为一个TableRow的对象，当然也可以是一个View的对象。
        ①如果我们直接往TableLayout中添加组件的话,那么这个组件将占满一行！！！
        ②如果我们想一行上有多个组件的话,就要添加一个TableRow的容器,把组件都丢到里面！

	表格布局常用的属性如下：
	android:collapseColumns：隐藏指定的列
	android:shrinkColumns：收缩指定的列以适合屏幕，不会挤出屏幕
	android:stretchColumns：尽量把指定的列填充空白部分
	android:layout_column：控件放在指定的列
	android:layout_span：该控件所跨越的列数
	
	
-- GridLayout 网格布局
	
	该布局是Android 4.0 后新增的一个布局，以网格的形式布局窗口控件

	这里要说一点,网格布局和其他布局不同,可以不为组件设置Layout_width和Layout_height属性
    因为组件的宽高由几行几列决定了,当然,你也可以写个wrap_content

	//常用属性:    
	排列对齐:
      ①设置组件的排列方式: android:orientation="" vertical(竖直,默认)或者
      horizontal(水平)
      ②设置组件的对齐方式: android:layout_gravity="fill" 
      center,left,right,buttom,这些,如果想同时用两种的话:eg: buttom|left
	设置布局为几行几列:
      ①设置有多少行:android:rowCount="4" //设置网格布局有4行
      ②设置有多少列:android:columnCount="4" //设置网格布局有4列

	
	设置某个组件位于几行几列
	 注:都是从0开始算的哦！
     ①组件在第几行:android:layout_row = "1" //设置组件位于第二行
     ②组件在第几列:android:layout_column = "2" //设置该组件位于第三列
     
	设置某个组件横跨几行几列:
     ①横跨几行:android:layout_rowSpan = "2" //纵向横跨2行
     ②横跨几列:android:layout_columnSpan = "3" //横向横跨2列

	  
	  
	  
	