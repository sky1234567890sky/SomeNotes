    <!-- �����ļ�  -->
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    
	<!-- 
	android:scaleType="center"  (����,����)
		����ԭͼ�Ĵ�С����ʾ��ImageView�����ġ���ԭͼ��size����ImageView��size���������ֲü�����
		
	android:scaleType="centerCrop"  (����,����)
		����������ImageViewΪĿ�ģ���ԭͼ�����Ķ�׼ImageView�����ģ��ȱ����Ŵ�ԭͼ��ֱ������ImageViewΪֹ��ָ����ImageView�Ŀ�͸߶�Ҫ��������ԭͼ����ImageView�Ĳ������ü�����
	
	android:scaleType="centerInside"  (����,�ȱ����Ŵ���С)
		��ԭͼ��ȫ��ʾΪĿ�ģ���ͼƬ����������������ʾ��ͨ����������Сԭͼ��size��(��)���ڻ�С��ImageView�Ŀ�(��)��
		���ԭͼ��size�����С��ImageView��size����ԭͼ��size�����κδ���������ʾ��ImageView��  
	
	android:scaleType="matrix"  (����,���Ͻ�)
		���ı�ԭͼ�Ĵ�С����ImageView�����Ͻǿ�ʼ����ԭͼ��ԭͼ����ImageView�Ĳ������ü�����  
	
	android:scaleType="fitCenter"  (�ȸ�,����)
		��ԭͼ�������������С��ImageView��ImageView�ĸ߶ȣ�������ʾ   
	
	android:scaleType="fitEnd"  (�ȸ�,�ұ�)
		��ԭͼ����������(��С)��ImageView�ĸ߶ȣ���ʾ��ImageView���²���λ�� 
	
	android:scaleType="fitStart"  (�ȸ�,���)
		��ԭͼ����������(��С)��ImageView�ĸ߶ȣ���ʾ��ImageView���ϲ���λ�� 
	
	 -->
	 
	 
     <ImageView 
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="center"
        />
    
    <ImageView 
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="centerCrop"
        />
    
    <ImageView 
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="centerInside"
        /> 

<!--     <ImageView 
        android:layout_width="250dp"
        android:layout_height="80dp"
        android:src="@drawable/cache_clear"
        android:scaleType="matrix"
        />
    
    <ImageView 
        android:layout_width="250dp"
        android:layout_height="80dp"
        android:src="@drawable/cache_clear"
        android:scaleType="fitCenter"
        /> -->
    
<!--     <ImageView 
        android:layout_width="250dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="fitStart"
        />
    
    <ImageView 
        android:layout_width="250dp"
        android:layout_height="100dp"
        android:src="@drawable/cache_clear"
        android:scaleType="fitEnd"
        /> -->
</LinearLayout>