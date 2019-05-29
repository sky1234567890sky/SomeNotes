AsyncTask

一、为何要引入AsyncTask？

1、在Android程序开始运行的时候会单独启动一个进程，默认情况下所有这个程序操作都在这个进程中进行。一个Android程序默认情况下只有一个进程，但一个进程中可以有多个线程。
2、在这些线程中，有一个线程叫做UI线程（也叫Main Thread），除了UI线程外的线程都叫子线程（Worker Thread）。UI线程主要负责控制UI界面的显示、更新、交互等。因此，UI线程中的操作延迟越短越好（流畅）。把一些耗时的操作（网络请求、数据库操作、逻辑计算等）放到单独的线程，可以避免主线程阻塞。
3、Android给我们提供了一种轻量级的异步任务类AsyncTask。该类中实现异步操作，并提供接口反馈当前异步执行结果及进度，这些接口中有直接运行在主线程中的方法（如 onPostExecute,onPreExecute等）。

二、AsyncTask的原理

1.概述：

	Android的AsyncTask比Handler更轻量级一些，适用于简单的异步处理。
首先要明确Android之所以有Handler和AsyncTask，都是为了不阻塞主线程
（UI线程），且UI的更新只能在主线程中完成，因此异步处理是不可避免的。

Android为了降低这个开发难度，提供了AsyncTask。AsyncTask就是一个封装过的后台任务类，顾名思义就是异步任务。

2、AsyncTask的泛型参数：

	AsyncTask直接继承于Object类，位置为android.os.AsyncTask。要使用AsyncTask工作我们要提供三个泛型参数，并重载几个方法(至少重载一个)。

	AsyncTask定义了三种泛型类型 Params，Progress和Result。
		.Params 启动任务执行的输入参数，比如HTTP请求的URL。
		.Progress 后台任务执行的百分比。
   	    .Result    后台执行任务最终返回的结果，比如String。

3、AsyncTask中需要重写的几个方法

	.doInBackground(Params…) 后台执行，比较耗时的操作都可以放在这里。注意这里不能直接操作UI。
							 此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间。
							 在执行过程中可以调用publicProgress(Progress…)来更新任务的进度。
							 
	.onPostExecute(Result) 相当于Handler 处理UI的方式，在这里面可以使用在doInBackground 得到的结果处理操作UI。
						   此方法在主线程执行，任务执行的结果作为此方法的参数返回
						   
    .onProgressUpdate(Progress…) 可以使用进度条增加用户体验度。 此方法在主线程执行，用于显示任务执行的进度。
	
	.onPreExecute() 这里是最终用户调用Excute时的接口，当任务执行之前开始调用此方法，可以在这里显示进度对话框。
	
	.onCancelled() 用户调用取消时，要做的操作


三、AsyncTask的使用

	1、使用AsyncTask类，以下是几条必须遵守的准则：

	· Task的实例必须在UI thread中创建；
	· execute方法必须在UI thread中调用；
	· 不要手动的调用onPreExecute(), onPostExecute(Result)，
	· doInBackground(Params...), onProgressUpdate(Progress...)这几个方法
	· 该task只能被执行一次，否则多次调用时将会出现异常；

	2、AsyncTask异步任务的使用步骤：

	<1>首先自定义一个类继承自AsyncTask
	
	<2>AsyncTask三个范型参数的类型的设置，第一参数是指doInBackground的参数类型，
	   第二个参数是指onProgressUpdate的参数，当前应用的打开进度值，第三个参数是
	   指onPostExecute的参数，表示doInBackground的返回值类型
	   
	<3>AsyncTask实例化一个对象，执行execute（）方法，方法里面传递的第一个参数就是指第一个范型
	
	<4>AsyncTask一个实例只能 执行一次，否则会报异常，执行完了要手动取消，调用cancle（false）取消任务
	
	<5>在一个进程中，最多只能并行执行5个异步任务，多于5个会等待，等待比较多时，比如20,30个，会报异常RejectExecuteException

	
四、AsyncTask和Handler的对比	
	
	1、AsyncTask实现的原理,和适用的优缺点

	实现原理：
		AsyncTask,是android提供的轻量级的异步类,可以直接继承AsyncTask,在类中实现异步操作,
		并提供接口反馈当前异步执行的程度(可以通过接口实现UI进度更新),最后反馈执行的结果给UI主线程.

	使用的优点:
		简单,快捷
		过程可控
		
	使用的缺点:
		在使用多个异步操作和并需要进行Ui变更时,就变得复杂起来

	
	2、Handler异步实现的原理和适用的优缺点
	
	实现原理：
		在Handler 异步实现时,涉及到 Handler, Looper, Message,Thread四个对象，
		实现异步的流程是主线程启动Thread（子线程），thread(子线程)运行并生成
		Message，Looper获取Message并传递给Handler，Handler逐个获取Looper中的
		Message，并进行UI变更
	
	使用的优点：
		结构清晰，功能定义明确
		对于多个后台任务时，简单，清晰
	
	使用的缺点：
		在单个后台异步处理时，显得代码过多，结构过于复杂（相对性）
	