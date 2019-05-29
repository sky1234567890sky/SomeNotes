帧动画：
	就是一张一张的照片连续播放，做成的动画
	用xml实现帧动画：
		1. 创建文件夹drawable，并在drawable文件夹中创建一个xml文件
			注意：xml根标签必须是：<animation-list> <animation-list/>
			里面的属性有：android:oneshot 设置图片是否只运行一次

		2. 在其中添加帧动画的每一张图片，并设置动画时间
			注意：标签为<item />
			android:drawable 设置有哪些图片
			android:duration 设置图片运行的时间
		
		3. 把设计的xml文件作为ImageView的背景
			注意：ImageView不能设计src
		
		4. 在java代码中获取ImageView的背景，并强转成 AnimationDrawable对象
		
		5. 现在就可以对动画对象AnimationDrawable进行设置


1.在布局文件中设置布局
	<Button 
        android:id="@+id/start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="start"
        android:text="开始播放"
        />
	
    <Button 
        android:id="@+id/stop"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="stop"	//点击监听
        android:text="停止播放"
        />
	
    <ImageView 
        android:id="@+id/iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="@anim/miao_gif"
        />




2. 先在res文件夹下创建一个 anim 文件名的文件夹,在该文件夹中
   创建一个xml文件夹,组件为 <animation-list> 
   
   该组件里需添加的属性有	oneshot:只启动一次
   android:oneshot = "true" true为运行一次,false为循环运行
   
   <item>组件里需添加的属性	duration:时长，持续的时间
   android:drawable = "@drawable/a" 该属性放图片
   android:duration = "200" 该属性是运行时间
   
   
3. 在java文件中
   设置主布局的代码,
   
   · 把获取图片的变量设置全变量中  private ImageView iv;
     在onCreate的方法中: 通过id值获取图片对象 iv = (ImageView) findViewById(R.id.iv);
    
   · 设置一个开始的方法： public void start(View v)
		//在该方法中获取背景图片
		AinmationDrawable background = (AnimationDrawable) iv.getBackground();
		
		//开始动画
		background.start();
   
   · 设置一个结束的方法： public void stop(View v)
		
		//获取背景图片
		AnimationDrawable background = (AnimationDrawable) iv.getBackground();
		
		//结束动画
		background.stop();
		
