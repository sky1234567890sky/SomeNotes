	//һ��ProgressBar
	
һ:����

	��������UI�����е�һ�ַǳ�ʵ�õ������ͨ�����û���ʾĳ����ʱ������ɵİٷֱȡ�
	��˽��������Զ�̬����ʾ���ȣ����ⳤʱ���ִ��ĳ����ʱ����ʱ�����û��о�����ʧȥ����Ӧ��
	�Ӷ����õ�����û�������Ѻ��ԡ�
	
	
��:����
	
Android֧�ּ��ַ��Ľ�������ͨ��style���Կ���ΪProgressBarָ����񣬸�����֧�����¼�������ֵ��

       style=��?android:attr/progressBarStyle��? Բ�ν�����
       style=��?android:attr/progressBarStyleHorizontal��?ˮƽ������
       style=��?android:attr/progressBarStyleInverse��?Բ����ͨ��С������
       style=��?android:attr/progressBarStyleLarge��? Բ�δ������
       style=��?android:attr/progressBarStyleLargeInverse��?Բ�δ������

	   style=��?android:attr/progressBarStyleSmall��?СԲ�ν�����
	   style=��?android:attr/progressBarStyleSmallInverse��? ��ͨ��Բ��С������
	   style=��?android:attr/progressBarStyleSmallTitle��?�������Բ��С������
	
ProgressBar֧�����±��.xml����:

       android:max://���øý����������ֵ
       android��progress//���øý�����������ɽ���ֵ
       android:indeterminate//����������Ϊtrue, ���ý���������ȷ��ʾ����
       android:indeterminateDrawable//���û��Ʋ���ʾ���ȵ�Drawable����
	   android:secondaryProgress //Ԥ���ؽ���	
	   
ProgressBar����Ҫ����:

      getMax()��//��������������ķ�Χ������
	  getProgress()��//���ؽ���ֵ  setProgress()��
	  getSecondaryProgress()��//���ش�Ҫ����ֵ setSecondaryProgress()��
      incrementProgressBy(int diff)��//ָ�����ӵĽ��ȣ�����Ϊ���ӽ��ȣ�����Ϊ��ȥ����
      isIndeterminate()��//�жϽ������Ƿ��ڲ���ȷ��ʾ����ģʽ��
      setIndeterminate(boolean indeterminate)��//���ý���������ȷ��ʾģʽ
      setVisibility(int v)��//���øý������Ƿ����		ֵ�У�visible��invisible��gone  �ֱ�Ϊ����ʾ������ʾ��ռ�У����أ�������ʾ��ɾ����
 
 
��Ҫ�¼�:

��onSizeChanged(int w, int h, int oldw, int oldh)��������ֵ�ı�ʱ�������¼�
 