Activity 的显示启动和隐示启动

1. Activity 概述：

一个Activity是一个应用程序组件，提供一个屏幕，用户可以用来交互为了完成某项任务，
例如拨号、拍照、发送email、看地图。每一个activity被给予一个窗口，在上面可以绘制用户接口。
窗口通常充满屏幕，但也可以小于屏幕而浮于其它窗口之上

Activity是Android四大组件之一：
       Activity、Service、BroadCastReceiver、ContentProvider

四大组件都要在配置清单：AndroidManifest.xml里面的application标签里面进行配置


2.Activity 的声明和注册：

Activity实际上就是表示的是一个人机的交互程序，用于存放各个显示控件，也是Android的基本组成，
所有的Android项目都使用Java语言进行开发，所以每一个继承了android.app.Activity的Java类都将成为一个Activity程序，
而一个Android项目将由多个Activity程序所组成，而所有的显示组件都必须放在Activity上才可以进行显示。

android.app.Activity类的继承结构如下：
	java.lang.Object
		android.content.Context
			android.content.ContextWrapper
				android.view.ContextThemeWrapper
					android.app.Activity

					
只要是新建的Activity都需要注册

      如果只是简单注册，这样就行了
     <activity android:name=".MainActivity"></activity> 记得一定要加那个“.”
      
	  如果你要让你新注册的Activity是运行后第一个出现的页面，就需要这样
	 <activity android:name=".MainActivity"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
     </activity>
				
新创建Activity是需要在Manifest文件中注册的，如果你新创建的Activity和
   mainActivity是在同一目录下（举个例子，比如我添加了文件名叫
   TestActivity.java），只需要在Manifest添加一句
 <activity android:name=".TestActivity"></activity>，如果新创建的
    Activity和mainActivity不是在同一目录，则需要在“android:name”中加
   上包名。
					
					
<intent-filter></intent-filter>是一个过滤器，设置当程序启动时默认调用指定的Activity子类
	<action android:name="android.intent.action.MAIN" />作为程序的主入口 
	
	<category android:name="android.intent.category.LAUNCHER" />
	是辅助<action android:name="android.intent.action.MAIN" />设置当前的
	Activity 成为应用程序的入口类
	


3.Activity的启动		

一、显示启动：---直接调用Activity的Class类
	
	举个例子：Activity1调用Activity2，跳转到Activity2中
         Intent intent = new Intent(this , Activity2.class);
         startActivity(intent);

	如果不在一个目录下：使用包名
		Intent intent = new Intent();
		Class forName = null;
			try {
				forName = Class.forName("com.example.activity.SecondActivity");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intent.setClass(MainActivity.this, forName);
		startActivity(intent);


		
二、隐示启动：

	隐式启动Activity的intent到底发给哪个activity，需要进行三个匹配，
	一个是action， 一个是category，一个是data，
	可以是全部或部分匹配，同样适用于Service和BroadcastReceiver。    

	隐式启动不同之处在于并不需要像Intent(MainActivity.this, SecondActivity.class)这样传参数然后再Start另一个Activity.

隐式启动包括以下几种：
	
	操作（Action）
	数据（Data）
	数据类型（Type）
	操作类别（Category）
	附加信息（Extras）
	组件（Component）
	标志（Flags）　
	
操作（Action）：

id		Action名称			Android.Manifest.xml配置名称		描述
			

1	    ACTION_MAIN   		android.intent.action.MAIN			作为一个程序的入口，不需要接收数据

2		ACTION_VIEW			android.intent.action.VIEW			用于数据的显示

3		ACTION_DIAL			android.intent.action.DLAL			调用电话号程序

4		ACTION_EDIT			android.intent.action.EDIT			用于编辑给定的数据

5		ACTION_PICK			android.intent.action.PICK			从特定的一组数据之中进行数据的选择操作

6		ACTION_PUN			android.intent.action.PUN			运行数据

7		ACTION_SEND			android.intent.action.SEND			调用发送短信程序

8		ACTIO_GET_CONTENT	android.intent.action.GET_CONTENT	根据指定的Type来选择打开操作内容的Intent

9		ACTION_CHOOSER		android.intent.action.CHOOSER		创建文件操作选择器


假定有一个Activity在清单中是这样的声明的：

<activity android:name=".ActionActivity">
	<intent-filter>
		<action android:name="customer_action_here"/>
	</intent-filter>
</activity>

使用以下代码进行跳转到上面这个Activity中：    

//创建一个隐式的?Intent?对象：Action?动作
    Intent intent = new Intent();
//设置?Intent?的动作为清单中指定的action
    intent.setAction("customer_action_here");
    startActivity(intent);


	
	
	
EditText et = (EditText) findViewById(R.id.et1);
String string = et.getText().toString();
				
	//隐示启动跳转页面
	Intent intent = new Intent();
	//intent.setClass(MainActivity.this, SecondActivity.class);
				
	//打电话
	intent.setAction(Intent.ACTION_CALL);
	//如何获取打电话的权限：
	//在AndroidManifest.xml文件中找Permissions文值，add添加Users Permission权限 android.permission.CALL_PHONE
				
	intent.setData(Uri.parse("tel:"+string));
				
				
startActivity(intent);
					
	
	
	
	

