Activity ����ʾ��������ʾ����

1. Activity ������

һ��Activity��һ��Ӧ�ó���������ṩһ����Ļ���û�������������Ϊ�����ĳ������
���粦�š����ա�����email������ͼ��ÿһ��activity������һ�����ڣ���������Ի����û��ӿڡ�
����ͨ��������Ļ����Ҳ����С����Ļ��������������֮��

Activity��Android�Ĵ����֮һ��
       Activity��Service��BroadCastReceiver��ContentProvider

�Ĵ������Ҫ�������嵥��AndroidManifest.xml�����application��ǩ�����������


2.Activity ��������ע�᣺

Activityʵ���Ͼ��Ǳ�ʾ����һ���˻��Ľ����������ڴ�Ÿ�����ʾ�ؼ���Ҳ��Android�Ļ�����ɣ�
���е�Android��Ŀ��ʹ��Java���Խ��п���������ÿһ���̳���android.app.Activity��Java�඼����Ϊһ��Activity����
��һ��Android��Ŀ���ɶ��Activity��������ɣ������е���ʾ������������Activity�ϲſ��Խ�����ʾ��

android.app.Activity��ļ̳нṹ���£�
	java.lang.Object
		android.content.Context
			android.content.ContextWrapper
				android.view.ContextThemeWrapper
					android.app.Activity

					
ֻҪ���½���Activity����Ҫע��

      ���ֻ�Ǽ�ע�ᣬ����������
     <activity android:name=".MainActivity"></activity> �ǵ�һ��Ҫ���Ǹ���.��
      
	  �����Ҫ������ע���Activity�����к��һ�����ֵ�ҳ�棬����Ҫ����
	 <activity android:name=".MainActivity"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
     </activity>
				
�´���Activity����Ҫ��Manifest�ļ���ע��ģ�������´�����Activity��
   mainActivity����ͬһĿ¼�£��ٸ����ӣ�������������ļ�����
   TestActivity.java����ֻ��Ҫ��Manifest���һ��
 <activity android:name=".TestActivity"></activity>������´�����
    Activity��mainActivity������ͬһĿ¼������Ҫ�ڡ�android:name���м�
   �ϰ�����
					
					
<intent-filter></intent-filter>��һ�������������õ���������ʱĬ�ϵ���ָ����Activity����
	<action android:name="android.intent.action.MAIN" />��Ϊ���������� 
	
	<category android:name="android.intent.category.LAUNCHER" />
	�Ǹ���<action android:name="android.intent.action.MAIN" />���õ�ǰ��
	Activity ��ΪӦ�ó���������
	


3.Activity������		

һ����ʾ������---ֱ�ӵ���Activity��Class��
	
	�ٸ����ӣ�Activity1����Activity2����ת��Activity2��
         Intent intent = new Intent(this , Activity2.class);
         startActivity(intent);

	�������һ��Ŀ¼�£�ʹ�ð���
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


		
������ʾ������

	��ʽ����Activity��intent���׷����ĸ�activity����Ҫ��������ƥ�䣬
	һ����action�� һ����category��һ����data��
	������ȫ���򲿷�ƥ�䣬ͬ��������Service��BroadcastReceiver��    

	��ʽ������֮ͬ�����ڲ�����Ҫ��Intent(MainActivity.this, SecondActivity.class)����������Ȼ����Start��һ��Activity.

��ʽ�����������¼��֣�
	
	������Action��
	���ݣ�Data��
	�������ͣ�Type��
	�������Category��
	������Ϣ��Extras��
	�����Component��
	��־��Flags����
	
������Action����

id		Action����			Android.Manifest.xml��������		����
			

1	    ACTION_MAIN   		android.intent.action.MAIN			��Ϊһ���������ڣ�����Ҫ��������

2		ACTION_VIEW			android.intent.action.VIEW			�������ݵ���ʾ

3		ACTION_DIAL			android.intent.action.DLAL			���õ绰�ų���

4		ACTION_EDIT			android.intent.action.EDIT			���ڱ༭����������

5		ACTION_PICK			android.intent.action.PICK			���ض���һ������֮�н������ݵ�ѡ�����

6		ACTION_PUN			android.intent.action.PUN			��������

7		ACTION_SEND			android.intent.action.SEND			���÷��Ͷ��ų���

8		ACTIO_GET_CONTENT	android.intent.action.GET_CONTENT	����ָ����Type��ѡ��򿪲������ݵ�Intent

9		ACTION_CHOOSER		android.intent.action.CHOOSER		�����ļ�����ѡ����


�ٶ���һ��Activity���嵥���������������ģ�

<activity android:name=".ActionActivity">
	<intent-filter>
		<action android:name="customer_action_here"/>
	</intent-filter>
</activity>

ʹ�����´��������ת���������Activity�У�    

//����һ����ʽ��?Intent?����Action?����
    Intent intent = new Intent();
//����?Intent?�Ķ���Ϊ�嵥��ָ����action
    intent.setAction("customer_action_here");
    startActivity(intent);


	
	
	
EditText et = (EditText) findViewById(R.id.et1);
String string = et.getText().toString();
				
	//��ʾ������תҳ��
	Intent intent = new Intent();
	//intent.setClass(MainActivity.this, SecondActivity.class);
				
	//��绰
	intent.setAction(Intent.ACTION_CALL);
	//��λ�ȡ��绰��Ȩ�ޣ�
	//��AndroidManifest.xml�ļ�����Permissions��ֵ��add���Users PermissionȨ�� android.permission.CALL_PHONE
				
	intent.setData(Uri.parse("tel:"+string));
				
				
startActivity(intent);
					
	
	
	
	

