RatingBar   �Ǽ�����


// RatingBar����

     �Ǽ����������϶���ʮ�����ƣ�������������ͬ�ĸ��ࣺAbsSeekBar��
	 ʵ�����Ǽ����������϶������÷������ܶ�ʮ�ֽӽ������Ƕ������û�ͨ���϶����ı���ȡ�
	 RatingBar��SeekBar����������ڣ� RatingBarͨ����������ʾ���ȡ�
 
 
// ���� xml���ԣ�

     android:isIndicator��//���ø��Ǽ��������Ƿ������û��ı䣨trueΪ������
     android:numStarts��//���ø��Ǽ��������ܹ��ж��ٸ��Ǽ�
     android:rating��//���ø��Ǽ�������Ĭ�ϵ��Ǽ�
     android:stepSize��//����ÿ��������Ҫ�ı���ٸ��Ǽ�

	 �������� setOnRatingBarChangeListener(new OnRatingChangeListener)(){
		 //rating ���Ǹ���
		 public void onRatingChanged(RatingBar ratingbar,float rating,boolean fromUser){
			 
			 Toast.makeText(MainActivity.this,""+rating*20,Toast.LENGTH_SHORT).show();
		 }
		 
	 }
	 
	 