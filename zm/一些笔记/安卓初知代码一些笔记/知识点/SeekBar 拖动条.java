//SeekBar  �϶���

�϶�������:

	�϶����ͽ������ǳ����ƣ�ֻ�ǽ�����������ɫ���������������ɵĳ̶ȣ�
	���϶�����ͨ�������λ������ʶ��ֵ----�����϶��������û��϶��������ı�ֵ��
	����϶���ͨ�����ڶ�ϵͳ��ĳ����ֵ���е��ڣ�������������ȡ�
	
	SeekBar����ҪӦ�������ֲ��ţ���Ƶ���ţ����߶��������ڣ���Ļ���ȵ��ڵ�һЩ�����У�
	���û������ֶ���ȥ�ı���Ӧ��ֵ��

	
Seekbar����Ҫ���Ժͷ�����

	�����������:
	
	android:max		//���÷�Χ���ֵ
	android:progress	//���õ�ǰ����ֵ
	android:secondaryProgress	//���õ�ǰ�ν���ֵ
	android:progressDrawable	//���ý�������ͼƬ
	android:thumb		//���ý������Ļ����ͼƬ
	
	���õķ���:
	
	getMax()
	getProgress()
	setMax(int)
	setOnseekBarChangeListener(SeekBar.OnSeekBarChangeListener I)  
	
	SeekBar.OnSeekBarChangeListener �п��Լ��������¼�:
	1.��ֵ�ĸı�(onProgressChanged)
	2.��ʼ�϶�(onStartTrackingTouch)
	3.ֹͣ�϶�(onStopTrackingTouch)
	
	
	