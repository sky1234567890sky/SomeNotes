WebView �����ҳ�����ʹ��

���裺
	
	1.���ڲ����ļ������� WebView�����������id
	2.��java�ļ��л�ȡid����ֵ������ loadUrl(String url)��������Ҫ�������ҳ·��
	3.���嵥�ļ�(AndroidManifest.xml)����������Ȩ��
		<uses-permission android:name="android.permission.INTERNET"/>
		
�������£�
   	
	�����ļ���
		
		<WebView
        android:id="@+id/wv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
	
	
	java�ļ���
	
	public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡWebView�Ķ���
		WebView wv = (WebView) findViewById(R.id.wv);

		// ����Ҫ�������ҳ
		wv.loadUrl("https://www.baidu.com");

		}

	}

	
	�������嵥�ļ�������Ȩ�ޣ�
	
	<uses-permission android:name="android.permission.INTERNET"/>
	
���û�������Ļ�����ôʹ�ã�
    	
	ʹ��Tomcat����webapps�ļ��У�����һ���ļ��У��ڴ��ļ���������һ��.html�ļ�
	����ҳ�Ϳ������ҳ���ˣ�����������ַ
	��ַ�ǣ�http:192.168.1.10:8080/anzhuochu/a.html
	
	
��ҳ��ť�ĵ���¼���

	��ҳ�Ĵ��룺
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>����һ����ҳ</title>
	</head>

	<body>
		
		<p>����һ����ҳ,webview���</p>

		<p>��ϲ��˭��</p>
		
		
		<input type = "submit" value = "����һ����ť���һ����java�еĴ���" onclick = "an.startToast('����ϲ��ʯ��');"/>
		
		
	</body>
	</html>
	
	
	java�ļ��Ĵ��룺
	
	public class MainActivity extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wv = (WebView) findViewById(R.id.wv);

		
		WebSettings settings = wv.getSettings();
		//��������Ĵ�С
		settings.setDefaultFontSize(30);
		//��������ı���ʽ
		settings.setDefaultTextEncodingName("UTF-8");
		
		settings.setJavaScriptEnabled(true); 	//ʹ��js�� 4.4�󲻹���
		
		//���js�ӿ�
		wv.addJavascriptInterface(MainActivity.this,"an");
		
		
		
		
		// ����Ҫ�������ҳ
		//wv.loadUrl("https://www.baidu.com");
		
		wv.loadUrl("http://192.168.1.10:8080/anzhuochu/a.html");
		
		
	} 
	
	
	//��ҳ��ť�ĵ���¼�������
	@JavascriptInterface
	public void startToast(String string){
		
		Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
		
		
	}
	
	
 
}   
	
	
	