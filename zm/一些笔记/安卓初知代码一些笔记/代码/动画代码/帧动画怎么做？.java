֡������
	����һ��һ�ŵ���Ƭ�������ţ����ɵĶ���
	��xmlʵ��֡������
		1. �����ļ���drawable������drawable�ļ����д���һ��xml�ļ�
			ע�⣺xml����ǩ�����ǣ�<animation-list> <animation-list/>
			����������У�android:oneshot ����ͼƬ�Ƿ�ֻ����һ��

		2. ���������֡������ÿһ��ͼƬ�������ö���ʱ��
			ע�⣺��ǩΪ<item />
			android:drawable ��������ЩͼƬ
			android:duration ����ͼƬ���е�ʱ��
		
		3. ����Ƶ�xml�ļ���ΪImageView�ı���
			ע�⣺ImageView�������src
		
		4. ��java�����л�ȡImageView�ı�������ǿת�� AnimationDrawable����
		
		5. ���ھͿ��ԶԶ�������AnimationDrawable��������


1.�ڲ����ļ������ò���
	<Button 
        android:id="@+id/start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="start"
        android:text="��ʼ����"
        />
	
    <Button 
        android:id="@+id/stop"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="stop"	//�������
        android:text="ֹͣ����"
        />
	
    <ImageView 
        android:id="@+id/iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="@anim/miao_gif"
        />




2. ����res�ļ����´���һ�� anim �ļ������ļ���,�ڸ��ļ�����
   ����һ��xml�ļ���,���Ϊ <animation-list> 
   
   �����������ӵ�������	oneshot:ֻ����һ��
   android:oneshot = "true" trueΪ����һ��,falseΪѭ������
   
   <item>���������ӵ�����	duration:ʱ����������ʱ��
   android:drawable = "@drawable/a" �����Է�ͼƬ
   android:duration = "200" ������������ʱ��
   
   
3. ��java�ļ���
   ���������ֵĴ���,
   
   �� �ѻ�ȡͼƬ�ı�������ȫ������  private ImageView iv;
     ��onCreate�ķ�����: ͨ��idֵ��ȡͼƬ���� iv = (ImageView) findViewById(R.id.iv);
    
   �� ����һ����ʼ�ķ����� public void start(View v)
		//�ڸ÷����л�ȡ����ͼƬ
		AinmationDrawable background = (AnimationDrawable) iv.getBackground();
		
		//��ʼ����
		background.start();
   
   �� ����һ�������ķ����� public void stop(View v)
		
		//��ȡ����ͼƬ
		AnimationDrawable background = (AnimationDrawable) iv.getBackground();
		
		//��������
		background.stop();
		
