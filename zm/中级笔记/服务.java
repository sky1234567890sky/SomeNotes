19.服务										
	
            开启,关闭(不可控,不能与服务传值)
			          1.Activity
						
						    两个按钮
						        switch (v.getId()) {
									case R.id.start:
										Intent intent = new Intent(this,MyService1.class);
										intent.putExtra("name","dadasdasdsa");
										startService(intent);//开启服务
										break;
									case R.id.stop:
										Intent intent1 = new Intent(this,MyService1.class);
										stopService(intent1);//关闭服务
										break;
								}
                     2.创建一个服务类类 Service1
									public class MyService1 extends Service {

									private String TAG = "MyService1";

									//必须重写
									@Override
									public IBinder onBind(Intent intent) {
										Log.e(TAG, "onBind: ");
										return null;
									}

									/**
									 * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
									 * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
									 */
									@Override
									public void onCreate() {
										super.onCreate();
										Log.e(TAG, "onCreate: ");
									}

									/**
									 * 每次通过startService()方法启动Service时都会被回调。
									 * @param intent
									 * @param flags
									 * @param startId
									 * @return
									 */
									@Override
									public int onStartCommand(Intent intent, int flags, int startId) {
										String name = intent.getStringExtra("name");
										Log.e(TAG, "onStartCommand: " + name);
										return super.onStartCommand(intent, flags, startId);
									}

									/**
									 * 服务销毁时的回调
									 */
									@Override
									public void onDestroy() {
										super.onDestroy();

										Log.e(TAG, "onDestroy: " );
									}
								}

                绑定,解绑(可控,可传值) 
				                1.Activity中的按钮  但是有服务连接不要绑定
								 
									private void initView() {
										bind = (Button) findViewById(R.id.bind);
										unbind = (Button) findViewById(R.id.unbind);

										bind.setOnClickListener(this);
										unbind.setOnClickListener(this);
										get = (Button) findViewById(R.id.get);
										get.setOnClickListener(this);
									}

									//服务连接
									ServiceConnection serviceConnection = new ServiceConnection() {

										//连接成功
										@Override
										public void onServiceConnected(ComponentName name, IBinder service) {
											binder = (MyService2.MyBinder) service;//改对象即为服务返回的IBinder对象，通过该对象进行方法的调用
											binder.abc("绑定成功");
										}

										//断开成功
										@Override
										public void onServiceDisconnected(ComponentName name) {

										}
									};

									@Override
									public void onClick(View v) {
										switch (v.getId()) {
											case R.id.bind:
												//绑定
												Intent intent = new Intent(this, MyService2.class);
												intent.putExtra("name","dasdsadasd");
												//绑定对象    绑定连接类     自动创建服务参数
												bindService(intent, serviceConnection, BIND_AUTO_CREATE);
												break;
											case R.id.unbind:
												//解绑
												unbindService(serviceConnection);
												binder=null;
												serviceConnection = null;
												break;
											case R.id.get:
												//获取服务中的数据
												if (binder!=null){
													String s = binder.get();
													Log.e("lzj", "onClick: "+s);
												}
												break;
										}
									}
								}
								
                            2.服务类
						 
									public class MyService2 extends Service {
										private String TAG = "MyService2";

										//Activity与Service直接进行传值通信的中间类
										class MyBinder extends Binder{

											public void abc(String str){//A--->S  通过参数传递
												Log.e(TAG, "abc: " + str);
											}

											public String get(){//S---->A   通过返回值传递
												return "123";
											}
										}

										//服务第一次被绑定时调用， 通常调用者通过bindService()启动时调用 该方法返回 IBinder 接口，调用者通过该接 口实现访问服务中的方法和变量
										@Override
										public IBinder onBind(Intent intent) {
											String name = intent.getStringExtra("name");
											Log.e(TAG, "onBind: " + name);
											return new MyBinder();
										}


										//服务被创建时调用，只调用一次
										@Override
										public void onCreate() {
											super.onCreate();

											Log.e(TAG, "onCreate: " );
										}


										//与服务解绑时调用   返回为true时，则有组件再次与该服务对象绑定时，会调用 onRebind()函数，否则不调用
										@Override
										public boolean onUnbind(Intent intent) {
											Log.e(TAG, "onUnbind: " );
											return super.onUnbind(intent);
										}

										//服务被系统销毁前调用该方法
										@Override
										public void onDestroy() {
											super.onDestroy();

											Log.e(TAG, "onDestroy: " );
										}
									}


20.音乐播放器
                            1.记载studio的音乐
											private void initData() {

												//1.数据源Raw
												mediaPlayer = MediaPlayer.create(this, R.raw.abc1);
											}

											private void initView() {
												play = (Button) findViewById(R.id.play);
												pause = (Button) findViewById(R.id.pause);
												stop = (Button) findViewById(R.id.stop);

												play.setOnClickListener(this);
												pause.setOnClickListener(this);
												stop.setOnClickListener(this);
											}

											@Override
											public void onClick(View v) {
												switch (v.getId()) {
													case R.id.play:
														if (!mediaPlayer.isPlaying()){//判断是否正在播放
															mediaPlayer.start();//开始播放
														}
														break;
													case R.id.pause:
														if (mediaPlayer.isPlaying()){
															mediaPlayer.pause();//暂停播放
														}
														break;
													case R.id.stop:
														if (mediaPlayer.isPlaying()){
															mediaPlayer.reset();//重置
															initData();//重新初始化数据
														}
														break;
												}
											}

											@Override
											protected void onDestroy() {
												super.onDestroy();

												if (mediaPlayer!=null){//页面关闭，清除对象
													mediaPlayer.stop();
													mediaPlayer = null;
												}
											}
                            2.播放SD卡的音乐
							   //读取SD卡权限校验
												private void initData() {
													if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
														readSD();
													}else{
														ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
													}
												}

												@Override
												public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
																					   @NonNull int[] grantResults) {
													super.onRequestPermissionsResult(requestCode, permissions, grantResults);
													if (requestCode == 1){
														if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
															readSD();
														}else{
															Toast.makeText(this,"没有权限",Toast.LENGTH_SHORT).show();
														}
													}
												}

												//读取SD中的音乐文件
												private void readSD() {
													//判断sd卡是否可以运行
													if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
														//读取SD卡根目录
														File sd = Environment.getExternalStorageDirectory();

														//1.创建播放器对象
														mediaPlayer = new MediaPlayer();
														try {
															//2.设置数据源（文件播放的路径）
															mediaPlayer.setDataSource(new File(sd, "abc1.mp3").getPath());
															//3.准备就绪
															mediaPlayer.prepare();
														} catch (IOException e) {
															e.printStackTrace();
														}
													}
												}

												private void initView() {
													play = (Button) findViewById(R.id.play);
													pause = (Button) findViewById(R.id.pause);
													stop = (Button) findViewById(R.id.stop);

													play.setOnClickListener(this);
													pause.setOnClickListener(this);
													stop.setOnClickListener(this);
												}

												@Override
												public void onClick(View v) {
													switch (v.getId()) {
														case R.id.play:
															if (!mediaPlayer.isPlaying()){
																mediaPlayer.start();
															}
															break;
														case R.id.pause:
															if (mediaPlayer.isPlaying()){
																mediaPlayer.pause();
															}
															break;
														case R.id.stop:
															if (mediaPlayer.isPlaying()){
																mediaPlayer.reset();
																readSD();
															}
															break;
													}
												}

												@Override
												protected void onDestroy() {
													super.onDestroy();

													if (mediaPlayer!=null){
														mediaPlayer.stop();
														mediaPlayer= null;
													}
												}
											}


                            3.3种方式寻找音频内容(权限是SD卡)

												public class MainActivity extends AppCompatActivity {
			
												private  ArrayList<Muisc> list;
												@Override
												protected void onCreate(Bundle savedInstanceState) {
													super.onCreate(savedInstanceState);
													setContentView(R.layout.activity_main);
													initData();
												}

												private void initData() {
													list = new ArrayList<>();
													if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
														readSd();
													}else{
														ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
													}
												}

												@Override
												public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
																					   @NonNull int[] grantResults) {
													super.onRequestPermissionsResult(requestCode, permissions, grantResults);
													if (requestCode == 1){
														if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
															readSd();
														}else{
															Toast.makeText(this,"无权限",Toast.LENGTH_SHORT).show();
														}
													}
												}

												private void readSd() {
													if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
														File sd = Environment.getExternalStorageDirectory();
														
														//mp3List1(sd);//递归
														mp3List2(sd);//文件过滤器
														mp3List3(sd);
													}
												}

												private void mp3List3(File sd) {

													Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
													Cursor query = getContentResolver().query(uri, null, null, null, null);
													if (query!=null){
														while (query.moveToNext()){
															String data = query.getString(query.getColumnIndex(MediaStore.Audio.Media.DATA));
															String name = query.getString(query.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
															String size = query.getString(query.getColumnIndex(MediaStore.Audio.Media.SIZE));

															Log.e("lzj", "mp3List3: "+ name +":" +data +":"+ size );
														}
													}
												}

												private void mp3List2(File sd) {
													File[] files = sd.listFiles(new FilenameFilter() {
														@Override
														public boolean accept(File dir, String name) {

															//false什么文件都没有    true都有
															return new File(dir,name).isFile() && name.endsWith(".mp3");
														}
													});

													for (File f:files){
														Log.e("lzj", "mp3List2: "+f.getName() );
													}
												}

												private void mp3List1(File sd) {

													//1.遍历文件夹中的所有文件
													File[] files = sd.listFiles();

													//循环
													for (File f :files){

														//2.判断是否为文件
														if (f.isFile()){//文件

															//3.判断是否为MP3文件
															if (f.getName().endsWith(".mp3")){
																Log.e("lzj", "mp3List: "+f.getName() +":"+f.getPath() );

																list.add(new Muisc(f.getName(),f.getPath()));
															}
														}else{//文件夹

															mp3List1(f);
														}
													}
												}

											}