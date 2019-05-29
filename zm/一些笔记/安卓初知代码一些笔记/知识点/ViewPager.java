ViewPager

Android 3.0后引入的一个UI控件——ViewPager(视图滑动切换工具)，实在想不到如何来称呼这个控件，
他的大概功能：
	通过手势滑动可以完成View的切换，一般是用来做APP 的引导页或者实现图片轮播，
	因为是3.0后引入的，如果想在低版本下使用，就需要引入v4 兼容包哦~，我们也可以看到，
	ViewPager在：android.support.v4.view.ViewPager目录下~ 下面我们就来学习一下这个控件的基本用法~ 官方API文档：ViewPager

	
1. ViewPager的简单介绍

	ViewPager就是一个简单的页面切换组件，我们可以往里面填充多个View，然后我们可以左右滑动，从而切换不同的View，
		我们可以通过setPageTransformer()方法为我们的ViewPager 设置切换时的动画效果，当然，动画我们还没学到，
		所以我们把为ViewPager设置动画放到下一章绘图与动画来讲解！和前面学的ListView，GridView一样，
		我们也需要一个Adapter (适配器)将我们的View和ViewPager进行绑定，而ViewPager则有一个特定的Adapter—— PagerAdapter！
		另外，Google官方是建议我们使用Fragment来填充ViewPager的，这样可以更加方便的生成每个Page，以及管理每个Page的生命周期！
		
	给我们提供了两个Fragment 专用的Adapter：FragmentPageAdapter和FragmentStatePagerAdapter 我们简要的来分析下这两个Adapter的区别：

	FragmentPageAdapter：
	
		和PagerAdapter一样，只会缓存当前的Fragment以及左边一个，右边一个，即总共会缓存3个Fragment而已，假如有1，2，3，4四个页面：

			处于1页面：缓存1，2
			处于2页面：缓存1，2，3
			处于3页面：销毁1页面，缓存2，3，4
			处于4页面：销毁2页面，缓存3，4
			更多页面的情况，依次类推~ 
			
	FragmentStatePagerAdapter：
	
		当Fragment对用户不见得时，整个Fragment会被销毁，只会保存Fragment的状态！而在页面需要重新显示的时候，会生成新的页面！		
			
	综上，FragmentPageAdapter适合固定的页面较少的场合；
	而FragmentStatePagerAdapter则适合于页面较多或者页面内容非常复杂(需占用大量内存)的情况！

2. PagerAdapter的使用		
		
我们先来介绍最普通的PagerAdapter，如果想使用这个PagerAdapter需要重写下面的四个方法：
	当然，这只是官方建议，实际上我们只需重写getCount()和isViewFromObject()就可以了~

	getCount():获得viewpager中有多少个view 

	destroyItem():移除一个给定位置的页面。适配器有责任从容器中删除这个视图。这是为了确保在finishUpdate(viewGroup)返回时视图能够被移除。 

而另外两个方法则是涉及到一个key的东东：

	instantiateItem(): ①将给定位置的view添加到ViewGroup(容器)中,创建并显示出来 
					   ②返回一个代表新增页面的Object(key),通常都是直接返回view本身就可以了,当然你也可以自定义自己的key,
					   但是key和每个view要一一对应的关系 

	isViewFromObject(): 判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是代表的同一个视图(即它俩是否是对应的，
						对应的表示同一个View),通常我们直接写 return view == object! 
		
			
			
			