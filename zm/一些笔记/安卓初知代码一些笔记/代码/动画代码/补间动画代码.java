���䶯��

1. alpha 	͸���Ƚ���
   scale 	���Ž���
   translate	 λ�ƽ���
   rotate 	��ת����
   set	 ��Ͻ���
   
   ��res�µ�anim�ļ����д���xml�ļ�,���ϵ�Ϊ���е����
   
<set xmlns:android="http://schemas.android.com/apk/res/android">
    
    <!-- ���Ž���  -->
	<scale 
	    android:fromXScale="0.2"
	    android:fromYScale="0.2"
	    android:toXScale="1.5"
	    android:toYScale="1.5"
	    android:pivotX="50%"
	    android:pivotY="50%"
	    android:duration="2000"/>
	
	<!-- ��ת����  -->
	<rotate 
	    android:fromDegrees="0"
	    android:toDegrees="360"
	    android:duration="1000"
	    android:repeatCount="1"
	    android:repeatMode="reverse"/>
	
	<!-- λ�ƽ���  -->
	<translate 
	    android:fromXDelta="0"
	    android:fromYDelta="0"
	    android:toXDelta="320"
	    android:toYDelta="0"
	    android:duration="2000"/>
	
    <!-- ͸���Ƚ���  -->
    <alpha 
        android:fromAlpha="1"
        android:toAlpha="0"
        android:duration="2000"/>
    

</set>  


2. �ڲ����ļ������ð�ť���

	���磺
	<Button 
        android:id="@+id/set"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="Zh"
        android:text="��Ͻ���"/>
		
		
3. ��java�ļ�������		
 
	private ImageView iv;
	private Animation animation5;
 
   //��Ͻ���
	public void Zh(View v){
		//ͨ��idֵ��ȡͼƬ�Ķ���
		iv = (ImageView) findViewById(R.id.iv);
		//�����������󣬲��������õĶ���Ч����xml�ļ�
		animation5 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_set);
		//���Ŷ���
		iv.startAnimation(animation5);
	}
   
   
   
