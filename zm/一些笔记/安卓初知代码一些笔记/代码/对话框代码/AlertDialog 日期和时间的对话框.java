AlertDialog ʱ������ڵĶԻ���

// �����ļ���Main��

	<Button
		android:id="@+id/btn_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1.����ѡ��Ի���" />

    <Button
		android:id="@+id/btn_time"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="2.ʱ��ѡ��Ի���" />
		
// java�ļ�(Main)	

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
						// �����ȡ�����·���Ҫ����1Ŷ~
						result="";
						result += "��ѡ�����" + year + "��" + (monthOfYear + 1) + "��" + dayOfMonth + "��";
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
						result += "��ѡ���ʱ����:" + hourOfDay + "ʱ" + minute + "��";
						Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
					}
				}, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
			}
		});

	}
}	

	
