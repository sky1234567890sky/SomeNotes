WebView

	就是一个可以显示Web资源的控件。就是在这个控件中浏览网页

	使用的话，首先在布局中创建WebVie标签，设置好大小与id以后就可以使用了
	<WebView
        android:id="@+id/wv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
         />
		 
	在java代码中得到他的对象，并使用一个方法loadUrl(String url)就可以在这个控件当中浏览网页了
	
	想浏览网页，这个应用就要使用网络，我们开发的应用，只要使用系统资源，就必须申请权限
		所以我们还要给这个能上网的应用申请网络权限
		在清单文件中添加一行
		AndroidManifest.xml
			<uses-permission android:name="android.permission.INTERNET"/>
	
	使用WebView就要知道他的组成部分：
		1.WebSettings		对WebView的设置
			比如设置字体
			
			JS处理
			setJavaScriptEnabled(true); //支持js	//Android4.4以后，此方法无效
			setPluginsEnabled(true); //支持插件
			setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
			
			缩放处理
			setUseWideViewPort(true); //将图片调整到适合webview的大小
			setLoadWithOverviewMode(true); // 缩放至屏幕的大小
			setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
			setBuiltInZoomControls(true); //设置内置的缩放控件。 这个取决于setSupportZoom(), 若setSupportZoom(false)，则该WebView不可缩放，这个不管设置什么都不能缩放。
			setDisplayZoomControls(false); //隐藏原生的缩放控件
			
			内容布局
			setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
			supportMultipleWindows(); //多窗口
			
			文件缓存
			setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
			setAllowFileAccess(true); //设置可以访问文件
			
			其他设置
			setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
			setLoadsImagesAutomatically(true); //支持自动加载图片
			setDefaultTextEncodingName(“utf-8”); //设置编码格式
			setPluginState(PluginState.OFF); //设置是否支持flash插件
			setDefaultFontSize(20); //设置默认字体大小
			
		2.WebChromeClient	用来支持js运行的
				通过继承WebChromeClient并重载它的方法也可以实现不同功能的定制
			public void onProgressChanged(WebView view, int newProgress); //获得网页的加载进度，显示在右上角的TextView控件中
			public void onReceivedTitle(WebView view, String title); //获取Web页中的title用来设置自己界面中的title, 当加载出错的时候，比如无网络，这时onReceiveTitle中获取的标题为”找不到该网页”,
			public void onReceivedIcon(WebView view, Bitmap icon); //获取Web页中的icon
			public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg);
			public void onCloseWindow(WebView window);
			public boolean onJsAlert(WebView view, String url, String message, JsResult result); //处理alert弹出框，html 弹框的一种方式
			public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) //处理confirm弹出框
			public boolean onJsConfirm(WebView view, String url, String message, JsResult result); //处理prompt弹出框
		
		3.WebViewClient
			WebViewClient用来帮助WebView处理各种通知, 请求事件。我们通过继承WebViewClient并重载它的方法可以实现不同功能的定制
			shouldOverrideUrlLoading(WebView view, String url) //在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。比如获取url，查看url.contains(“add”)，进行添加操作

			shouldOverrideKeyEvent(WebView view, KeyEvent event) //处理在浏览器中的按键事件。
			onPageStarted(WebView view, String url, Bitmap favicon) //开始载入页面时调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
			onPageFinished(WebView view, String url) //在页面加载结束时调用, 我们可以关闭loading 条，切换程序动作。
			onLoadResource(WebView view, String url) //在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
			onReceivedError(WebView view, int errorCode, String description, String failingUrl) //报告错误信息
			doUpdateVisitedHistory(WebView view, String url, boolean isReload) //更新历史记录
			onFormResubmission(WebView view, Message dontResend, Message resend) //应用程序重新请求网页数据
			onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,String realm) //获取返回信息授权请求
			onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) //让webview处理https请求。
			onScaleChanged(WebView view, float oldScale, float newScale) //WebView发生改变时调用
			onUnhandledKeyEvent(WebView view, KeyEvent event) //Key事件未被加载时调用