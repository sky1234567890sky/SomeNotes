���䶯�����֣�
	AlphaAnimation	͸���Ƚ���Ч��
	ScaleAnimation	���Ž���Ч��
	TranslateAnimation	λ�ƽ���Ч��
	RotateAnimation	��ת����Ч��
	AnimationSet	��Ͻ���Ч��
	
	
1. AlphaAnimation	͸���Ƚ���Ч��  <alpha><alpha/>
	����ʱָ����ʼ�Լ�����͸���ȣ����ж����ĳ���ʱ�䣬
	͸���ȵı仯��Χ��0,1����0����ȫ͸����1����ȫ��͸����

<alpha xmlns:android="http://schemas.android.com/apk/res/android"
     android:fromAlpha="1.0"
     android:toAlpha="0.1"
     android:duration="2000">
    
</alpha>

���Խ���:

	fromAlpha����ʼ͸����

	toAlpha������͸����

	͸���ȷ�Χ��0-1����ȫ͸��-��ȫ��͸��





2. ScaleAnimation	���Ž���Ч��	<scale><scale/>
	������ʦָ����ʼ�Լ����������űȣ��Լ����Ųο��㣬
	���ж����ĳ���ʱ�䣻

<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="0.2"
    android:toXScale="1.5"
    android:fromYScale="0.2"
    android:toYScale="1.5"
    android:pivotX="50%"
    android:pivotY="50%"
    android:duration="2000" >
    
</scale>


���Խ��ͣ�

	fromXScale/fromYScale������X��/Y�����ŵ���ʼ����

	toXScale/toYScale������X��/Y�����ŵĽ�������

	pivotX/pivotY�����ŵ������X/Y���꣬�������������Ե��λ�ã�
				   ����50%������ͼ�������Ϊ�����






3. TranslateAnimation	λ�ƽ���Ч��	<translate><translate/>
	����ʱָ����ʼ�Լ�����λ�ã���ָ�������ĳ���ʱ��

<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXDelta="0" 
    android:toXDelta="320"
    android:fromYDelta="0"
    android:toYDelta="0"
    android:duration="2000">
    
</translate>


���Խ��ͣ�

	fromXDelta/fromYDelta��������ʼλ�õ�X/Y����

	toXDelta/toYDelta����������λ�õ�X/Y����




4. RotateAnimation	��ת����Ч�� <rotate><rotate/>
	����ʱָ��������ʼ�Լ���������ת�Ƕȣ��Լ�
	��������ʱ�����ת�����ģ�
	
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
     android:fromDegrees="0"
     android:toDegrees="360"
     android:duration="1000"
     android:repeatCount="1"
     android:repeatMode="reverse">
    

</rotate>	
	

���Խ��ͣ�
	
	fromDegrees/toDegrees����ת����ʼ/�����Ƕ�
	
	repeatCount����ת�Ĵ�����Ĭ��Ϊ0������һ�Σ�����������ֵ��
	����3������ת4�� ���⣬ֵΪ -1 ���� infinite ʱ����ʾ��������ֹͣ
	
	repeatMode�������ظ�ģʽ��Ĭ��restart����ֻ�е�repeatCount����0����
	infinite �� -1 ʱ����Ч�����������ó�reverse����ʾż������ʾ����ʱ��
	�������෴���˶���
	
	
	
5. AnimationSet	��Ͻ���Ч��

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
	
	