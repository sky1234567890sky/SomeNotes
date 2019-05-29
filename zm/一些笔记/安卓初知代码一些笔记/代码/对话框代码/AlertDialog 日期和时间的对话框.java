AlertDialog 时间和日期的对话框

// 布局文件（Main）

	<Button
		android:id="@+id/btn_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1.日期选择对话框" />

    <Button
		android:id="@+id/btn_time"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="2.时间选择对话框" />
		
// java文件(Main)	

public class MainActivity extends Activity {

	private Button btn_date;
	private Button btn_time;
	private String result = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_date = (Button) findViewById(R.id.btn_date);
		btn_time = (Button) findViewById(R.id.btn_time);

		btn_date.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar cale1 = Calendar.getInstance();
				new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						// 这里获取到的月份需要加上1哦~
						result="";
						result += "你选择的是" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
						Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
					}
				}, cale1.get(Calendar.YEAR), cale1.get(Calendar.MONTH), cale1.get(Calendar.DAY_OF_MONTH)).show();

			}
		});

		btn_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar cale2 = Calendar.getInstance();
				new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						result = "";
						result += "您选择的时间是:" + hourOfDay + "时" + minute + "分";
						Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
					}
				}, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
			}
		});

	}
}	

	
