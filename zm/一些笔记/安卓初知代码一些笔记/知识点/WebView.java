WebView

	����һ��������ʾWeb��Դ�Ŀؼ�������������ؼ��������ҳ

	ʹ�õĻ��������ڲ����д���WebVie��ǩ�����úô�С��id�Ժ�Ϳ���ʹ����
	<WebView
        android:id="@+id/wv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
         />
		 
	��java�����еõ����Ķ��󣬲�ʹ��һ������loadUrl(String url)�Ϳ���������ؼ����������ҳ��
	
	�������ҳ�����Ӧ�þ�Ҫʹ�����磬���ǿ�����Ӧ�ã�ֻҪʹ��ϵͳ��Դ���ͱ�������Ȩ��
		�������ǻ�Ҫ�������������Ӧ����������Ȩ��
		���嵥�ļ������һ��
		AndroidManifest.xml
			<uses-permission android:name="android.permission.INTERNET"/>
	
	ʹ��WebView��Ҫ֪��������ɲ��֣�
		1.WebSettings		��WebView������
			������������
			
			JS����
			setJavaScriptEnabled(true); //֧��js	//Android4.4�Ժ󣬴˷�����Ч
			setPluginsEnabled(true); //֧�ֲ��
			setJavaScriptCanOpenWindowsAutomatically(true); //֧��ͨ��JS���´���
			
			���Ŵ���
			setUseWideViewPort(true); //��ͼƬ�������ʺ�webview�Ĵ�С
			setLoadWithOverviewMode(true); // ��������Ļ�Ĵ�С
			setSupportZoom(true); //֧�����ţ�Ĭ��Ϊtrue���������Ǹ���ǰ�ᡣ
			setBuiltInZoomControls(true); //�������õ����ſؼ��� ���ȡ����setSupportZoom(), ��setSupportZoom(false)�����WebView�������ţ������������ʲô���������š�
			setDisplayZoomControls(false); //����ԭ�������ſؼ�
			
			���ݲ���
			setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); //֧���������²���
			supportMultipleWindows(); //�ര��
			
			�ļ�����
			setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //�ر�webview�л���
			setAllowFileAccess(true); //���ÿ��Է����ļ�
			
			��������
			setNeedInitialFocus(true); //��webview����requestFocusʱΪwebview���ýڵ�
			setLoadsImagesAutomatically(true); //֧���Զ�����ͼƬ
			setDefaultTextEncodingName(��utf-8��); //���ñ����ʽ
			setPluginState(PluginState.OFF); //�����Ƿ�֧��flash���
			setDefaultFontSize(20); //����Ĭ�������С
			
		2.WebChromeClient	����֧��js���е�
				ͨ���̳�WebChromeClient���������ķ���Ҳ����ʵ�ֲ�ͬ���ܵĶ���
			public void onProgressChanged(WebView view, int newProgress); //�����ҳ�ļ��ؽ��ȣ���ʾ�����Ͻǵ�TextView�ؼ���
			public void onReceivedTitle(WebView view, String title); //��ȡWebҳ�е�title���������Լ������е�title, �����س����ʱ�򣬱��������磬��ʱonReceiveTitle�л�ȡ�ı���Ϊ���Ҳ�������ҳ��,
			public void onReceivedIcon(WebView view, Bitmap icon); //��ȡWebҳ�е�icon
			public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg);
			public void onCloseWindow(WebView window);
			public boolean onJsAlert(WebView view, String url, String message, JsResult result); //����alert������html �����һ�ַ�ʽ
			public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) //����confirm������
			public boolean onJsConfirm(WebView view, String url, String message, JsResult result); //����prompt������
		
		3.WebViewClient
			WebViewClient��������WebView�������֪ͨ, �����¼�������ͨ���̳�WebViewClient���������ķ�������ʵ�ֲ�ͬ���ܵĶ���
			shouldOverrideUrlLoading(WebView view, String url) //����ҳ�ϵ����м��ض������������,����������ǿ������ܶ�����������ȡurl���鿴url.contains(��add��)��������Ӳ���

			shouldOverrideKeyEvent(WebView view, KeyEvent event) //������������еİ����¼���
			onPageStarted(WebView view, String url, Bitmap favicon) //��ʼ����ҳ��ʱ���õģ����ǿ����趨һ��loading��ҳ�棬�����û������ڵȴ�������Ӧ��
			onPageFinished(WebView view, String url) //��ҳ����ؽ���ʱ����, ���ǿ��Թر�loading �����л���������
			onLoadResource(WebView view, String url) //�ڼ���ҳ����Դʱ����ã�ÿһ����Դ������ͼƬ���ļ��ض������һ�Ρ�
			onReceivedError(WebView view, int errorCode, String description, String failingUrl) //���������Ϣ
			doUpdateVisitedHistory(WebView view, String url, boolean isReload) //������ʷ��¼
			onFormResubmission(WebView view, Message dontResend, Message resend) //Ӧ�ó�������������ҳ����
			onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,String realm) //��ȡ������Ϣ��Ȩ����
			onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) //��webview����https����
			onScaleChanged(WebView view, float oldScale, float newScale) //WebView�����ı�ʱ����
			onUnhandledKeyEvent(WebView view, KeyEvent event) //Key�¼�δ������ʱ����