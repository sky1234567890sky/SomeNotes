Handler 

知识目标：

	· Handler是什么     
	· 为什么要用Handler
	· Handler怎么用
	· Handler实现
	· 复习Thread和Runnable
	· Looper的详细介绍     

一、Handler是什么

	Handler在我们android开发中是一项非常重要的机制，那Handler是什么呢？Handler是android提供用于更新UI的一套机制，也是消息处理机制。

	Handler的主要作用有两个：
        (1).在新启动的线程中发送消息
        (2).在主线程中获取，处理消息。

	解释：
      (1) 当应用程序启动时，Android首先会开启一个主线程 (也就是UI线程) ， 主线程为管理界面中的UI控件， 进行事件分发，
		比如说， 你要是点击一个 Button ，Android会分发事件到Button上，来响应你的操作。
		主线程（UI线程）就是android程序从启动运行到最后的程序。

	  (2) 如果此时需要一个耗时的操作，例如: 联网读取数据，或者读取本地较大的一个文件的时候，你不能把这些操作放在主线程中，
		如果你放在主线程中的话，界面会出现假死现象， 如果5秒钟还没有完成的话，会收到Android系统的一个错误提示  "强制关闭"。

	  (3)这个时候我们需要把这些耗时的操作，放在一个子线程中，因为子线程涉及到UI更新，Android主线程是线程不安全的， 也就是说，
		更新UI只能在主线程中更新，子线程中操作是危险的。
	  
	  (4)这个时候，Handler就出现了。，来解决这个复杂的问题 ，由于Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据，这个时候，
		Handler就承担着接受子线程传过来的(子线程用sendMessage()方法传弟)Message对象(里面包含数据)  ， 把这些消息放入主线程队列中，配合主线程进行更新UI。

二、为什么要用Handler

	如果我们不用Handler去发送消息，更新UI可以吗？
		
		答案是不行的。 Android在设计的时候，就封装了一套消息创建，传递，处理机制，如果不遵循这样的机制，就没有办法更新UI信息的，
	就会抛出异常信息。
	抛出异常的描述：不能在非UI线程中去更新UI
	举个例子：新建一个子线程，里面定义一个变量不断地减1，减1后将这个数字显示到文本框中，实现倒计时 。

三、 Handler怎么用

	handler可以分发Message对象和Runnable对象到主线程中， 每个Handler实例，都会绑定到创建他的线程中(一般是位于主线程)，
	它有两个作用：
       (1)合理调度安排消息和runnable对象，使它们在将来的某个点被执行.
       (2)安排一个动作在不同的线程中执行.

	Handler中开启线程和分发消息的一些方法:
      post(Runnable)直接开启Runnable线程
      postAtTime(Runnable，long)在指定的时间long，开始启动线程
      postDelayed(Runnable long)在延迟long时间后，启动Runnable线程
      sendEmptyMessage(int) 发送指定的消息，通过参数int来区分不同的消息
      sendMessage(Message)发送消息到UI线程中

	
	MessageQueue：消息队列，用来存放Handler发送过来的消息，并按照FIFO规则执行。当然，存放Message并非实际意义的保存，
				而是将Message以链表的方式串联起来的，等待Looper的抽取。

	Looper：消息泵，不断地从MessageQueue中抽取Message执行。因此，一个MessageQueue需要一个Looper。

	Thread：线程，负责调度整个消息循环，即消息循环的执行场所。

	实现机制：
      android中提供了一种异步回调机制Handler,使用它，我们可以在完成一个耗时操作的任务后做出相应的通知。


	handler基本使用：
        (1)在主线程中，使用handler很简单，new一个Handler对象实现其handleMessage方法，在handleMessage中提供收到消息后相应的处理方法即可。（接收消息，并且更新UI）
        (2)在新启动的线程中发送消息

四、 Handler实现	

	新建的子线程发送消息：

public class MyThread implements Runnable{ 
	public void run(){
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
		//TODO Auto-generated catch block
		e.printStackTrace();
		}
		Message msg = new Message(); //实例化消息对象
				msg.arg1 = 98;//携带参数
				msg.arg2=99;//携带参数
        Person person=new Person();//实例化对象
				msg.obj=person; //携带参数为实体类对象
        handler.sendMessage(msg); //发送消息
	}
}	

	在主线程（UI线程）中接收处理消息

private  Handler handler = new  Handler(){

	@Override
	public void handleMessage(Message msg){
		super.handleMessage(msg);
		// 能获取到msg.arg1值
		tv.setText("msg.arg1:"+msg.arg1+"----msg.arg2="+msg.arg2);
	}
}

五、 Looper的详细

	· Android中的Looper类主要作用是来封装消息循环和消息队列的，用于在android线程中进行消息处理。handler是用来向消息队列中插入消息的并且对消息进行处理。 
	
	· Looper类主要是为每个线程开启的单独的消息循环。 默认情况下android中新诞生的线程是没有开启消息循环的。
	（主线程除外，主线程系统会自动为其创建Looper对象，开启消息循环） Looper对象负责管理MessageQueue，
	而MessageQueue主要是用来存放handler发送的消息，而且一个线程只能有一个Looper，对应一个MessageQueue。 
	
	· 我们通常是通过Handler对象来与Looper进行交互的。Handler可看做是Looper的一个接口，
	用来向指定的Looper中的MessageQueue发送消息并且Handler还必须定义自己的处理方法。 
	默认情况下Handler会与其被定义时所在线程的Looper绑定，如Handler在主线程中定义，它是与主线程的Looper绑定。 

	mainHandler = new Handler() 等价于 new Handler（Looper.myLooper()）
    Looper.myLooper()：获取当前进程的looper对象， Looper.getMainLooper() 
    用于获取主线程的Looper对象。 
	
	在非主线程中直接new Handler() 会报如下的错误: 
	Can't create handler inside thread that has not called Looper.prepare() 
	原因是非主线程中默认没有创建Looper对象，需要先调用Looper.prepare()启用Looper，然后再调用Looper.loop()。

	Looper.loop():启动looper中的循环线程，Handler就会从消息队列里取消息并进行对应处理

	Looper是一个循环从MessageQueue里面获取消息的。

Handler的简单使用

		在UI线程中创建成员变量Handler对象,并重写handlerMessage()方法
			handlerMessage()方法会被系统在UI线程中调用,如果需要操作UI控件就是在这个方法中.
		在创建的多线程中,使用主线程的handler对象给系统发送消息Message.通知系统可以调用handlerMessage()方法了.
		给系统发送消息使用的方法有四个,这四个方法都是在多线程中调用的
		
1.sendEmptyMessage() 给系统发送一个不带数据的Message对象,通知系统调用handlerMessage()方法,更改控件	
2.sendEmptymessageDelayed(int what, long delay)	发送一个不带数据的Message对象,但是是延时多少毫秒发送	
3.sendMessage(Message msg)给系统发送一个带有参数的Message消息,系统会把这个Message对象交给handlerMessage()方法,由用户获取数据
4.sendMessageDelayed(Message msg,long delay)把一个带有数据的消息延时多少毫秒发送给系统

在发送消息过程中有个东西非常关键:
	Message类中有一个成员变量what ,可以用来识别消息是由哪一个多线程发送过来的
		由what决定handlerMessage方法中修改的是哪一个控件	
		what值由我们自己来决定


-- 当在多线程中直接操作UI组件的时候,就会抛出以下错误

	android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.从错误的线程中调用了View的子类
			Called 调用
			From  从...地方
			Wrong	错误的
			Thread	线程
			ViewRootImpl View的子类





























































