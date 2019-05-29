WebView 的四种显示方式 与 在APP页面显示网页，并可以返回到上一个页面

// xml文件

activity_main.xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.webview2.MainActivity" >

    <WebView
        android:id="@+id/wv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>


// java 文件

四种显示方式：

public class MainActivity extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取控件对象
		wv = (WebView) findViewById(R.id.wv);

		// 加载 html 的四种方式

		// 1. 打开你的手机浏览器，进行加载的网页
		// wv.loadUrl("http://www.baidu.com");

		// 2. 加载asset文件夹下的html文件
		// wv.loadUrl("file:///android_asset/a.html");

		// 3. 加载手机sdcard上的html文件
		// wv.loadUrl("content://com.ansen.webview/sdcard/a.html");

		// 4. 使用webView显示html文件
		wv.loadDataWithBaseURL(null,
				"<html><head><title><title>这是一个网页</title></head><body><p>这是一个网页,webview组件</p><p>我喜欢谁？</p>"
						+ "</body></html>",
				"a/html", "utf-8", null);

	}

}

在APP页面显示网页，并可以返回到上一个页面：

public class MainActivity extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取控件对象
		wv = (WebView) findViewById(R.id.wv);

		// 设置之后可以在App 内部打开
		wv.setWebViewClient(new WebViewClient() {

			// 页面请求完成
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);

				// 关闭
			}

			// 页面请求开始
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);

				// 弹出 加载进度框
			}

		});

		// 打开手机浏览器 加载的网页
		// 需要设置权限 涉及 到隐私和安全
		wv.loadUrl("http://www.baidu.com");

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		// 判断按下的是不是返回键 打开的页面有没有上一页
		if (keyCode == event.KEYCODE_BACK && wv.canGoBack()) {

			// 如果符合，返回到上一页
			wv.goBack();

			return true;

		}

		// 直接推掉
		return super.onKeyDown(keyCode, event);
	}

}



