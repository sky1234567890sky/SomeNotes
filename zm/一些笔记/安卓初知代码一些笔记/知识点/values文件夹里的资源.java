res �ļ����� values�ļ��������Դ

��Դ��
		������res�ļ����µĶ�����Դ
		���Ա����뵽R.java�е�
			res/raw�е��ļ��ᱻӳ�䵽R.java�ļ��У����ʵ�ʱ��ֱ��ʹ����ԴID��R.id.filename
		�����Ա����뵽R.java�е�
			assets�ļ����µ��ļ����ᱻӳ�䵽R.java�У����ʵ�ʱ����ҪAssestManager��
		����ResĿ¼�µ���Դ��������XML�б����ã�������java�����б�����
		
		
1. styles.xml ��ʽ�ļ�
		
	����������ƣ�
		<resources>
		
		<style name = "text">
			
			<item name = "android:textColor">#cccccc</item>
			<item name="android:textSize">50sp</item>
			
		</style>
			
		</resources>
	
	�ڲ����ļ������ã�
		
		style = "@style/text"
		
		
2. strings.xml �ַ����ļ�

	��Ʒ�ʽ��
		<resources>
		
		//name = "" �Ǳ����õ�   ��ť����ʾ��
			<string name = "anniu">��ť</string>
		
		</resources>
	
	�ٲ��������ã�

		android:text="@string/anniu"

		
3. include ���

	��������������������.xml�����ļ�

	������ã�
	
	//title Ϊ���õĲ��ֵ��ļ��� title.xml
		<include 
			layout="@layout/title"
		/>
	
	