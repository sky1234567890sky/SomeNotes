//���ֿ���Ƕ��ʹ��
//���ֲ���:
	LinearLayout (���Բ���)
	FrameLayout (��֡����)
	AbsoluteLayout (���Բ���)
	TableLayout (��񲼾�)
	RelativeLayout (��Բ���)
	GridLayout (���񲼾�)

-- LinearLayout ���Բ���

	���Բ��ֿ��Կ�����������з�ʽ:
	
		orientation �����Կ��Կ��Ʋ��ֵķ���
		ˮƽ����
			orientation="horizontal"
		��ֱ����
			orientation="vertical"	
		
		gravity �����Կ��Կ�������Ķ��뷽ʽ
			gravity="left" 
			ֵ��:top/buttom/left/right
			�������ʹ��
			
	//��������	
		layout_gravity	�����Կ��Կ�������ڸ�������Ķ��뷽ʽ
			layout_gravity="center"
			
		layout_width �� layout_height �����Կ��Ʋ��ֵĿ����
			ֵ��:fill_parent/match_parent/wrap_content
			�ֱ���:װ�������/����������ĸ����/�ȱ������
			
		background Ϊ������һ������ͼƬ��һ��������ɫ

		id Ϊ������һ����Դid	,��java�ļ��п���ͨ�� findViewById(id) ���ø����
		
	//Weight Ȩ��
		���������������ȱ��������������
	
	//ΪLinearLayout���÷ָ���
		�ܶ���濪���ж�������һЩ�»���,���߷ָ���,�Ӷ�ʹ�ý��������������,��������Ŀṷ ���ֵ�ע��ҳ��:
		
		����������,����ͨ��������������:
		��ֱ���ڲ��������һ��view,���view�����ý�������ʾ��һ����,����Ҳ�ܼ�:
		<View  
		android:layout_width="match_parent"  
		android:layout_height="1px"  
		android:background="#000000" />  
		
		�����ˮƽ�����ϵĺ���,��Ȼ��Ҳ���Ըĳ�������ɫ,����ʹ��ͼƬ
		
		�ڵڶ�������ʹ��LinearLayout��һ��divider����,ֱ��ΪLinearLayout���÷ָ��� �������Ҫ���Լ�׼��һ���ߵ�ͼƬ�� 
		1)android:divider������Ϊ�ָ��ߵ�ͼƬ 
		2)android:showDividers���÷ָ��ߵ�λ��,none(��),beginning(��ʼ),end(����),middle(ÿ���������) 
		3)dividerPadding���÷ָ��ߵ�Padding
		
		��������:
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
		xmlns:tools="http://schemas.android.com/tools"  
		android:id="@+id/LinearLayout1"  
		android:layout_width="match_parent"  
		android:layout_height="match_parent"  
		android:divider="@drawable/ktv_line_div"  
		android:orientation="vertical"  
		android:showDividers="middle"  
		android:dividerPadding="10dp"  
		tools:context="com.jay.example.linearlayoutdemo.MainActivity" >  
	  
		<Button  
			android:layout_width="wrap_content"  
			android:layout_height="wrap_content"  
			android:text="��ť1" />  
	  
		<Button  
			android:layout_width="wrap_content"  
			android:layout_height="wrap_content"  
			android:text="��ť2" />  
	  
		<Button  
			android:layout_width="wrap_content"  
			android:layout_height="wrap_content"  
			android:text="��ť3" />  
  
		</LinearLayout>
		
	//ע������
		ʹ��Layout_gravity��һ������Ҫ������!!! 
		��������: ��һ��LinearLayout��ˮƽ�����в�������TextView,����һ����,һ����,��ô��?
		��������ѿڶ���:"gravity����һ��left,һ��right�Ϳ�����!" 
		�����ô��?���Թ���?д���򵥵�Layout��ͻᷢ��,����ԸΥ��:
		
		�� android:orientation="vertical" ʱ�� ֻ��ˮƽ��������ò������ã���ֱ��������ò������á�
		����left��right��center_horizontal ����Ч�ġ� 
		�� android:orientation="horizontal" ʱ�� ֻ�д�ֱ��������ò������ã�ˮƽ��������ò������á�
		����top��bottom��center_vertical ����Ч�ġ�
	
	
-- RelativeLayout ��Բ���
		
		��Բ����ǰ������֮������λ��������
		
		��Ԫ��ͨ����Щ���Ժ͸��Ե�id���ָ��Ϊ��ϵ,��ָ����ϵ��,���õ�id����������֮ǰ,�ȱ�����,���򽫳����쳣.
		
	//��������
		gravity ��������������Ķ��뷽ʽ
		ignoreGravity ���ø�����Ϊtrueʱ�����,������gravity���Ե�Ӱ��
		
	//���ݸ�������λ
		layout_alignParentTop/Bottom/Left/Right  ����/�ײ�/��/�Ҷ���
		layout_centerHorizontal ˮƽ����
		layout_centerVertical ��ֱ����
		layout_centerInParent �м�λ��
	//�����ֵ������λ
		layout_toLeftOf �ο���������
		layout_toRightOf �ο�������ұ�
		layout_above �ο�������Ϸ�
		layout_below �ο�������·�
		layout_alianTop/Bottom/Left/Right ����ο��������/��/��/�ұ߽�
	//margin(ƫ��) ��������븸�����ı߾�
		layout_margin ��������ıߵ�ƫ����
		layout_marginTop/Button/Left/Right �����������/��/��/�����ƫ����
	//padding(���) ��������ڲ�Ԫ�ؼ��ı߾�
		padding ���ڲ�Ԫ�ص���������(�ı�)���һ���߾�
		paddingTop/Button/Left/Right ���ڲ�Ԫ�ص���/��/��/�����һ���ı߾�
		
	
-- FrameLayout ֡����
		
		֡�����Ǵ���Ļ�����Ͻ�(0,0)���꿪ʼ���֣�������������У���һ����ӵ�����ŵ���ײ㣬�����ӵ�����е���ͼ��ʾ�������档
		��һ��ĻḲ����һ��Ŀؼ�������������У� ���е�Ԫ�ض����ܱ�ָ�����õ�λ�ã�����ͳͳ���������������Ͻǣ����Һ������Ԫ��ֱ�Ӹ�����ǰ�����Ԫ���ϡ�

		ʵ����������:
		<?xml version="1.0" encoding="utf-8"?>  
		<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android" android:orientation="vertical" android:layout_width="fill_parent" android:layout_height="fill_parent">  
			<TextView android:layout_width="fill_parent" android:layout_height="fill_parent" android:background="#ff000000" android:gravity="center" android:text="1"/>  
			<TextView android:layout_width="fill_parent" android:layout_height="fill_parent" android:background="#ff654321" android:gravity="center" android:text="2"/>  
			<TextView android:layout_width="50dp" android:layout_height="50dp" android:background="#fffedcba" android:gravity="center" android:text="3"/>  
		</FrameLayout>  

	//ǰ��ͼ��:��Զ����֡����������,ֱ������û���ͼ��,���ǲ��ᱻ���ǵ�ͼƬ��

		��������:

		android:foreground:*���ø�֡����������ǰ��ͼ��
		android:foregroundGravity:����ǰ��ͼ����ʾ��λ��
	

-- AbsoluteLayout ���Բ���
		
	�ò����ֿ��Խ������겼�֣�����ֱ��ָ����Ԫ�صľ���λ��(x,y)
	���������ֻ���Ļ�ߴ���ϴ�ʹ�þ��Զ�λ����Ӧ�Ի�Ƚϲ����Ļ����������ȱ��
	
	�ٿ��ƴ�С: android:layout_width:������ android:layout_height:����߶� 
	�ڿ���λ��: android:layout_x:���������X���� android:layout_y:���������Y����
	
	ע�⣺
     ���Բ���ͨ��ָ���������ȷ��X,Y������ȷ�������λ�ã���Android2.0 
     API�ĵ��б��������Ѿ����ڣ�����ʹ��FrameLayout����RelativeLayout
     �����档����Ϊ�ص�ѧϰ�Ĳ���

	
-- TableLayout ��񲼾�

	��񲼾���һ��ViewGroup�Ա����ʾ��������ͼ��view��Ԫ�أ����к���
    ��ʶһ����ͼ��λ�á�

	
	TableLayout ��񲼾�ģ�������е���ʽ�����ӿؼ���ÿһ��Ϊһ��TableRow�Ķ��󣬵�ȻҲ������һ��View�Ķ���
        ���������ֱ����TableLayout���������Ļ�,��ô��������ռ��һ�У�����
        �����������һ�����ж������Ļ�,��Ҫ���һ��TableRow������,��������������棡

	��񲼾ֳ��õ��������£�
	android:collapseColumns������ָ������
	android:shrinkColumns������ָ���������ʺ���Ļ�����ἷ����Ļ
	android:stretchColumns��������ָ���������հײ���
	android:layout_column���ؼ�����ָ������
	android:layout_span���ÿؼ�����Խ������
	
	
-- GridLayout ���񲼾�
	
	�ò�����Android 4.0 ��������һ�����֣����������ʽ���ִ��ڿؼ�

	����Ҫ˵һ��,���񲼾ֺ��������ֲ�ͬ,���Բ�Ϊ�������Layout_width��Layout_height����
    ��Ϊ����Ŀ���ɼ��м��о�����,��Ȼ,��Ҳ����д��wrap_content

	//��������:    
	���ж���:
      ��������������з�ʽ: android:orientation="" vertical(��ֱ,Ĭ��)����
      horizontal(ˮƽ)
      ����������Ķ��뷽ʽ: android:layout_gravity="fill" 
      center,left,right,buttom,��Щ,�����ͬʱ�����ֵĻ�:eg: buttom|left
	���ò���Ϊ���м���:
      �������ж�����:android:rowCount="4" //�������񲼾���4��
      �������ж�����:android:columnCount="4" //�������񲼾���4��

	
	����ĳ�����λ�ڼ��м���
	 ע:���Ǵ�0��ʼ���Ŷ��
     ������ڵڼ���:android:layout_row = "1" //�������λ�ڵڶ���
     ������ڵڼ���:android:layout_column = "2" //���ø����λ�ڵ�����
     
	����ĳ�������缸�м���:
     �ٺ�缸��:android:layout_rowSpan = "2" //������2��
     �ں�缸��:android:layout_columnSpan = "3" //������2��

	  
	  
	  
	