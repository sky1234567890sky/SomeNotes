    <!-- �����ļ�  -->
	
	
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

	<Button 
	    android:layout_width="100dp"
	    android:layout_height="100dp"
	    android:text="��ť"
	    android:background="@drawable/my_selector"
	    />
    
</LinearLayout>



	<!-- selector���ñ���ͼƬ���ļ�  -->

	
<!-- ����·��  ��res�ļ����� -- ����һ���ļ��� -- ����һ��xml�ļ�
		
		 -- ʹ�� selector ������� -- ʹ��item ���ñ���ͼƬ   -->	
	
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
      
    
    <!-- ���ð��������Ժ�ʹ�õ�ͼƬ  -->
    
    <item 
        android:state_pressed="true"			//state_pressed		���µ�״̬��trueΪ���£�falseΪ�ǰ���
        android:drawable="@drawable/cache_clear"
        ></item>

    
    <item
        android:state_pressed="false"
        android:drawable="@drawable/settings_center">
    </item>
    
</selector>
	
	