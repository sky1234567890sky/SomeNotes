ImageButton ͼ�갴ť

-- ����

�밴ť�����Button�����ƣ���Android�����ṩ��һ��ͼƬ��ť������ֱ��ʹ��ImageButton���壬
���ඨ�����£� 
	java.lang.Object
	    android.view.View
	 	    android.widget.ImageView
	 	 	    android.widget.ImageButton 

				
-- �������ԣ�
    android:src������ImageView����ʾ��Drawable����id��
    android:scaleType������ͼƬ��Դ��ʾ�������Ч���ģ�ֵ�кü��֣��ֱ�
    Ϊmatrix��center��centerCrop ��centerInside��fitCenter��fitEnd��fitStart��fitXY��
	
	android:scaleType="center"  
		����ԭͼ�Ĵ�С����ʾ��ImageView�����ġ���ԭͼ��size����ImageView��size���������ֲü�����
		
	android:scaleType="centerCrop"  
		����������ImageViewΪĿ�ģ���ԭͼ�����Ķ�׼ImageView�����ģ��ȱ����Ŵ�ԭͼ��ֱ������ImageViewΪֹ��ָ����ImageView�Ŀ�͸߶�Ҫ��������ԭͼ����ImageView�Ĳ������ü�����
	
	android:scaleType="centerInside"  
		��ԭͼ��ȫ��ʾΪĿ�ģ���ͼƬ����������������ʾ��ͨ����������Сԭͼ��size��(��)���ڻ�С��ImageView�Ŀ�(��)��
		���ԭͼ��size�����С��ImageView��size����ԭͼ��size�����κδ���������ʾ��ImageView��  
	
	android:scaleType="matrix"  
		���ı�ԭͼ�Ĵ�С����ImageView�����Ͻǿ�ʼ����ԭͼ��ԭͼ����ImageView�Ĳ������ü�����  
	
	android:scaleType="fitCenter"  
		��ԭͼ�������������С��ImageView��ImageView�ĸ߶ȣ�������ʾ   
	
	android:scaleType="fitEnd"  
		��ԭͼ����������(��С)��ImageView�ĸ߶ȣ���ʾ��ImageView���²���λ�� 
	
	android:scaleType="fitStart"  
		��ԭͼ����������(��С)��ImageView�ĸ߶ȣ���ʾ��ImageView���ϲ���λ�� 
	