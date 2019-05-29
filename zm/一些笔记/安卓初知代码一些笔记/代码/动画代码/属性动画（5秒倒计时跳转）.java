//属性动画

ValueAnimator简单使用
使用流程：

1.调用ValueAnimator的ofInt()，ofFloat()或ofObject()静态方法创建ValueAnimator实例 
2.调用实例的setXxx方法设置动画持续时间，插值方式，重复次数等 
3.调用实例的addUpdateListener添加AnimatorUpdateListener监听器，
	在该监听器中可以获得ValueAnimator计算出来的值，你可以值应用到指定对象上~ 
4.调用实例的start()方法开启动画！另外我们可以看到ofInt和ofFloat都有个这样的参数：
	float/int... values代表可以多个值！ 



-- 页面倒计时5秒，并跳转到第二个页面


1. 在布局文件中设置页面布局

	第一个页面：
	<LinearLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:orientation="vertical">
	
		<Button
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:onClick="start"
			android:text="开始计时" />
			
			
		<Button
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:onClick="alpha"
			android:text="模糊渐变" />
			
			
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
			android:text="倒计时"
			android:textSize="35sp"
		/>
	
	</LinearLayout>
	
	第二个页面 : 自己设置
	<LinearLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:orientation="vertical">
		
	</ LinearLayout>	
	

	
2. java文件中设置

public class MainActivity extends Activity {


	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//通过指定的 id 值获得指定的 TextView 的对象
		tv = (TextView) findViewById(R.id.tv);
		
		
	}
	
	public void start(View v){
		
		//制作从5-0变化的值
		ValueAnimator ob = ValueAnimator.ofInt(5,0);
		
		//addUpdateListener 添加监听更新变化（动画）
		
		//监听：监听其中从5-0的变化的值，值变化一次，系统就会调用一次监听方法
		ob.addUpdateListener(new AnimatorUpdateListener(){
			
			public void onAnimationUpdate(ValueAnimator animation){
				
				//获取Animation的值
				int value = (Integer) animation.getAnimatedValue();
				
				//给TextView中设置数据值
				tv.setText(value+"");
				
			}
			
		});
		
		
	//透明度动画
	public void alpha(View v){
		//制作透明度的变化值范围
		ValueAnimator ob = ValueAnimator.ofFloat(1.0f,0.0f);
		
		//设置运行的时间
		ob.setDuration(5000);
		
		//设置监听
		ob.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float value = (Float) animation.getAnimatedValue();
				//给TextView中设置模糊值
				tv.setAlpha(value);
			}
		});
		//开始动画
		ob.start();
	
	}
		
		
		
		
		//运行的时间
		ob.setDuration(5000);
		
		//对动画进行监听：监听动画什么时候开始/ 过程 / 结束
		ob.addListener(new AnimatorListener){
			
			//动画开始
			public void onAnimationStart(Animator animation){
				
			}
			
			//动画过程
			public void onAnimationRepeat(Animator animation){
				
			}
			
			//动画结束
			public void onAnimationEnd(Animator animation){
				
				//意图携带跳转到第二个页面
				Intent intent = new Intent(MainActivity.this,SecondActivity,class);
				
				//开始跳转指定的意图
				startActivity(intent);
				
			}
			
			//动画取消
			public void onAnimationCancel(Animator animation){
				
			}
			
			
		}
		
		
		
		
		//开始动画
		ob.start();
		
		
	}
	
	
	
}	
	