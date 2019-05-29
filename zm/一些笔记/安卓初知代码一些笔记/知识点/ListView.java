ListView 组件

// 概述
ListView组件在应用程序中可以说是不可或缺的一部分，ListView主要是显示列表数据，同时可以滚动查看列表所有数据。
ListView类的继承结构如下所示：

java.lang.Object
	android.view.View
  		android.view.ViewGroup
  	 		android.widget.AdapterView<T extends android.widget.Adapter>
  	 			android.widget.AbsListView
  	 	 	 		android.widget.ListView


		
		
// 列表的显示需要三个元素：
 ListVeiw：用来展示列表的View
 适配器：用来把数据映射到ListView上的中介   
 数据集：具体的将被映射的字符串，图片，或者基本组件。
注意：这三个元素结合起来实现ListView效果
在xml如何定义ListView组件：
 <ListView
        android:id="@+id/listview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" >
 </ListView>
				

// ListView常用属性
  cacheColorHint属性：设置重绘时的缓存颜色。
        android:cacheColorHint指定为透明（#00000000）
  
  fadingEdge属性——上边和下边有黑色的阴影，
		android:fadingEdge="none" 设置后没有阴影了
  
  scrollbars属性——隐藏listView的滚动条，
		android:scrollbars="none"   
  
  fadeScrollbars属性——android:fadeScrollbars="true" 
		实现滚动条的自动隐藏和显示
  
  divider属性——设置行间距的分割线，分割线可以自定义颜色或图片
  
  android:dividerHeight属性----设置分割线的高度
  
  listSelector属性——设置列表项选中或点击后的颜色
		可以设置android:listSelector=“@null”，选中或点击列表项时无背景颜色变化

		
		
		

1.// 设置Item之间无间隙

android:divider="#00000000" 或者在javaCode中如下定义：listView.setDividerHeight(0);

android:divider="@drawable/list_driver" 设置分割线的图片资源，如果则只要设置为

android:divider="@drawable/@null" 不想显示分割线

 

android:scrollbars="none" setVerticalScrollBarEnabled(true); 隐藏listView的滚动条

android:fadeScrollbars="true" 设置为true就可以实现滚动条的自动隐藏和显示

2.第三cacheColorHint属性，

很多人希望能够改变一下它的背景，使他能够符合整体的UI设计，
改变背景背很简单只需要准备一张图片然后指定属性 
android:background="@drawable/bg"，不过不要高兴地太早，
当你这么做以后，发现背景是变了，但是当你拖动，
或者点击list空白位置的时候发现ListItem都变成黑色的了，破坏了整体效果。

如果你只是换背景的颜色的话，可以直接指定android:cacheColorHint为你所要的颜色，
如果你是用图片做背景的话，那也只要将android:cacheColorHint指定为透明（#00000000）就可以了
	

// 自定义ListView
		步骤:

1.在xml文件中定义ListView标签
2.创建一个布局item,布局中创建ListView中药显示的条目布局
3.创建一个类继承抽象类BaseAdapter,并实现抽象类中的四个抽象方法
	并把要在ListView中显示的数据传递给这个类对象
	BaseAdapter中的抽象方法
		public int getCount()	//此方法返回决定了ListView创建多少个条目
		public Object getItem(int arg0)	//根究索引返回需要在指定位置显示的数据对象
		public long getItemId(int arg0)
		//每绘制一次条目就会调用一次这个方法,在此方法中指定绘制条目的样式,
		//并把要显示的相应的数据添加到对应的条目控件中
		//position 表示下一次将要绘制第多少个条目
		//contextView 默认值为null,我们给他重新赋值,用来指定条目样式
		//并获取条目中的所有控件,给响应属性赋值
		public View getView(int position, View contextView, ViewGroup arg2)
4.创建自定义Adapter对象
5.把自定义Adapter对象天剑进入ListView对象中


// ListView设置点击监听
	我们在使用ListView的时候，一般都会为ListView添加一个响应事件
	Android.widget.AdapterView.OnItemClickListener
      如何添加呢，请看下面代码：

listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
		里面实现监听具体操作

    }
	
});


监听方法:

	短监听: setOnItemClickListener
	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
						
			}
		});	
	
	
	长按监听: setOnItemLongClickListener
	
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});	
	
