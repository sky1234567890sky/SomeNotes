//���Զ���

ValueAnimator��ʹ��
ʹ�����̣�

1.����ValueAnimator��ofInt()��ofFloat()��ofObject()��̬��������ValueAnimatorʵ�� 
2.����ʵ����setXxx�������ö�������ʱ�䣬��ֵ��ʽ���ظ������� 
3.����ʵ����addUpdateListener���AnimatorUpdateListener��������
	�ڸü������п��Ի��ValueAnimator���������ֵ�������ֵӦ�õ�ָ��������~ 
4.����ʵ����start()���������������������ǿ��Կ���ofInt��ofFloat���и������Ĳ�����
	float/int... values������Զ��ֵ�� 



-- ҳ�浹��ʱ5�룬����ת���ڶ���ҳ��


1. �ڲ����ļ�������ҳ�沼��

	��һ��ҳ�棺
	<LinearLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:orientation="vertical">
	
		<Button
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:onClick="start"
			android:text="��ʼ��ʱ" />
			
			
		<Button
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:onClick="alpha"
			android:text="ģ������" />
			
			
		<TextView 
			android:id="@+id/tv"
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:layout_gravity="center"
			android:text="5"
			android:textSize="35sp"
		/>	
			
			
			
		<TextView 
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:layout_gravity="center"
			android:text="����ʱ"
			android:textSize="35sp"
		/>
	
	</LinearLayout>
	
	�ڶ���ҳ�� : �Լ�����
	<LinearLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:orientation="vertical">
		
	</ LinearLayout>	
	

	
2. java�ļ�������

public class MainActivity extends Activity {


	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//ͨ��ָ���� id ֵ���ָ���� TextView �Ķ���
		tv = (TextView) findViewById(R.id.tv);
		
		
	}
	
	public void start(View v){
		
		//������5-0�仯��ֵ
		ValueAnimator ob = ValueAnimator.ofInt(5,0);
		
		//addUpdateListener ��Ӽ������±仯��������
		
		//�������������д�5-0�ı仯��ֵ��ֵ�仯һ�Σ�ϵͳ�ͻ����һ�μ�������
		ob.addUpdateListener(new AnimatorUpdateListener(){
			
			public void onAnimationUpdate(ValueAnimator animation){
				
				//��ȡAnimation��ֵ
				int value = (Integer) animation.getAnimatedValue();
				
				//��TextView����������ֵ
				tv.setText(value+"");
				
			}
			
		});
		
		
	//͸���ȶ���
	public void alpha(View v){
		//����͸���ȵı仯ֵ��Χ
		ValueAnimator ob = ValueAnimator.ofFloat(1.0f,0.0f);
		
		//�������е�ʱ��
		ob.setDuration(5000);
		
		//���ü���
		ob.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float value = (Float) animation.getAnimatedValue();
				//��TextView������ģ��ֵ
				tv.setAlpha(value);
			}
		});
		//��ʼ����
		ob.start();
	
	}
		
		
		
		
		//���е�ʱ��
		ob.setDuration(5000);
		
		//�Զ������м�������������ʲôʱ��ʼ/ ���� / ����
		ob.addListener(new AnimatorListener){
			
			//������ʼ
			public void onAnimationStart(Animator animation){
				
			}
			
			//��������
			public void onAnimationRepeat(Animator animation){
				
			}
			
			//��������
			public void onAnimationEnd(Animator animation){
				
				//��ͼЯ����ת���ڶ���ҳ��
				Intent intent = new Intent(MainActivity.this,SecondActivity,class);
				
				//��ʼ��תָ������ͼ
				startActivity(intent);
				
			}
			
			//����ȡ��
			public void onAnimationCancel(Animator animation){
				
			}
			
			
		}
		
		
		
		
		//��ʼ����
		ob.start();
		
		
	}
	
	
	
}	
	