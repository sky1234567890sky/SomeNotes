WebView ��������ʾ��ʽ �� ��APPҳ����ʾ��ҳ�������Է��ص���һ��ҳ��

// xml�ļ�

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


// java �ļ�

������ʾ��ʽ��

public class MainActivity extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ�ؼ�����
		wv = (WebView) findViewById(R.id.wv);

		// ���� html �����ַ�ʽ

		// 1. ������ֻ�����������м��ص���ҳ
		// wv.loadUrl("http://www.baidu.com");

		// 2. ����asset�ļ����µ�html�ļ�
		// wv.loadUrl("file:///android_asset/a.html");

		// 3. �����ֻ�sdcard�ϵ�html�ļ�
		// wv.loadUrl("content://com.ansen.webview/sdcard/a.html");

		// 4. ʹ��webView��ʾhtml�ļ�
		wv.loadDataWithBaseURL(null,
				"<html><head><title><title>����һ����ҳ</title></head><body><p>����һ����ҳ,webview���</p><p>��ϲ��˭��</p>"
						+ "</body></html>",
				"a/html", "utf-8", null);

	}

}

��APPҳ����ʾ��ҳ�������Է��ص���һ��ҳ�棺

public class MainActivity extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ�ؼ�����
		wv = (WebView) findViewById(R.id.wv);

		// ����֮�������App �ڲ���
		wv.setWebViewClient(new WebViewClient() {

			// ҳ���������
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);

				// �ر�
			}

			// ҳ������ʼ
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);

				// ���� ���ؽ��ȿ�
			}

		});

		// ���ֻ������ ���ص���ҳ
		// ��Ҫ����Ȩ�� �漰 ����˽�Ͱ�ȫ
		wv.loadUrl("http://www.baidu.com");

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		// �жϰ��µ��ǲ��Ƿ��ؼ� �򿪵�ҳ����û����һҳ
		if (keyCode == event.KEYCODE_BACK && wv.canGoBack()) {

			// ������ϣ����ص���һҳ
			wv.goBack();

			return true;

		}

		// ֱ���Ƶ�
		return super.onKeyDown(keyCode, event);
	}

}



