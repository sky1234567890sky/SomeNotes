Context上下文


// 概述:

它描述的是一个应用程序环境的信息，即上下文。
通过它我们可以获取应用程序的资源和类，也包括一些应用级别操作，
例如：启动一个Activity，发送广播，接受Intent信息 等。



//什么时候创建Context实例 
	1、创建Application 对象时， 而且整个App共一个Application对象
    2、创建Service对象时
    3、创建Activity对象时
    总Context实例个数 = Service个数 + Activity个数 + 1（Application对应的Context实例）                                                                                         

	
//Context的继承结构
	Activity类 
	Service类 
	Application类本质上都是Context子类
																					 