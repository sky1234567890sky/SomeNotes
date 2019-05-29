WebView 浏览网页组件的使用

步骤：
	
	1.先在布局文件中设置 WebView组件，并设置id
	2.在java文件中获取id对象值，并用 loadUrl(String url)方法设置要浏览的网页路径
	3.在清单文件(AndroidManifest.xml)中设置网略权限
		<uses-permission android:name="android.permission.INTERNET"/>
		
代码如下：
   	
	布局文件：
		
		<WebView
        android:id="@+id/wv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
	
	
	java文件：
	
	public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取WebView的对象
		WebView wv = (WebView) findViewById(R.id.wv);

		// 设置要浏览的网页
		wv.loadUrl("https://www.baidu.com");

		}

	}

	
	必须在清单文件中设置权限：
	
	<uses-permission android:name="android.permission.INTERNET"/>
	
如果没有联网的话，怎么使用？
    	
	使用Tomcat，在webapps文件中，创建一个文件夹，在此文件夹下设置一个.html文件
	在网页就可以浏览页面了，例如以下网址
	网址是：http:192.168.1.10:8080/anzhuochu/a.html
	
	
网页按钮的点击事件：

	网页的代码：
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>这是一个网页</title>
	</head>

	<body>
		
		<p>这是一个网页,webview组件</p>

		<p>我喜欢谁？</p>
		
		
		<input type = "submit" value = "这是一个按钮，我会调用java中的代码" onclick = "an.startToast('郭磊喜欢石佳');"/>
		
		
	</body>
	</html>
	
	
	java文件的代码：
	
	public class MainActivity extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wv = (WebView) findViewById(R.id.wv);

		
		WebSettings settings = wv.getSettings();
		//设置字体的大小
		settings.setDefaultFontSize(30);
		//设这个会文本格式
		settings.setDefaultTextEncodingName("UTF-8");
		
		settings.setJavaScriptEnabled(true); 	//使能js， 4.4后不管用
		
		//添加js接口
		wv.addJavascriptInterface(MainActivity.this,"an");
		
		
		
		
		// 设置要浏览的网页
		//wv.loadUrl("https://www.baidu.com");
		
		wv.loadUrl("http://192.168.1.10:8080/anzhuochu/a.html");
		
		
	} 
	
	
	//网页按钮的点击事件方法：
	@JavascriptInterface
	public void startToast(String string){
		
		Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
		
		
	}
	
	
 
}   
	
	
	