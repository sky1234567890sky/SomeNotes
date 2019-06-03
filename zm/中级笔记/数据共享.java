15.数据共享
                1.读取提供的数据：
				        
							private void initView() {
										btn = (Button) findViewById(R.id.btn);

										btn.setOnClickListener(this);
									}

									@Override
									public void onClick(View v) {
										switch (v.getId()) {
											case R.id.btn:
												checkCallPhone();
												break;
										}
									}

							private void checkCallPhone() {
								//1.判断是否已经被授权
								if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
									//已经被授权：逻辑操作
									callPhone();
								} else {
									//没有被授权：申请授权
									//2.申请授权
									ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
								}
							}

							//3.回调，处理申请后的结果
							@Override
							public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
																   @NonNull int[] grantResults) {
								super.onRequestPermissionsResult(requestCode, permissions, grantResults);

								switch (requestCode) {//requestCode请求码判断
									case 1:
										//判断权限是否被授权
										if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
											//授权成功
											callPhone();
										} else {
											//授权失败
											Toast.makeText(this, "授权失败，无法操作", Toast.LENGTH_SHORT).show();
										}

										break;
								}
							}

								//拨打电话
								@SuppressLint("MissingPermission")
								private void callPhone() {
									Intent intent = new Intent(Intent.ACTION_CALL);
									intent.setData(Uri.parse("tel:10086"));
									MainActivity.this.startActivity(intent);
								}
							}
											
				
				
				
						public class MainActivity extends AppCompatActivity implements View.OnClickListener {

							private Button read_contacts;
							private Button read_sms;
							private Button read_picture;
							private Button read_audio;
							private Button read_medio;
							private TextView txt;

							@Override
							protected void onCreate(Bundle savedInstanceState) {
								super.onCreate(savedInstanceState);
								setContentView(R.layout.activity_main);
								initView();
							}

							private void initView() {
								read_contacts = (Button) findViewById(R.id.read_contacts);
								read_sms = (Button) findViewById(R.id.read_sms);
								read_picture = (Button) findViewById(R.id.read_picture);
								read_audio = (Button) findViewById(R.id.read_audio);
								read_medio = (Button) findViewById(R.id.read_medio);
								txt = (TextView) findViewById(R.id.txt);

								read_contacts.setOnClickListener(this);
								read_sms.setOnClickListener(this);
								read_picture.setOnClickListener(this);
								read_audio.setOnClickListener(this);
								read_medio.setOnClickListener(this);
							}

							@Override
							public void onClick(View v) {
								switch (v.getId()) {
									case R.id.read_contacts:
										checkContacts();
										break;
									case R.id.read_sms:
										checkSms();
										break;
									case R.id.read_picture:
										checkPicture();
										break;
									case R.id.read_audio:
										checkAudio();
										break;
									case R.id.read_medio:
										checkMedio();
										break;
								}
							}

							//------------------------------------------------
							//检测读取视频权限
							private void checkMedio() {
								if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
									readMedio();
								}else{ 
									ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},5);
								}
							}

							//检测读取音频权限
							private void checkAudio() {
								if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
									readAudio();
								}else{
									ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
								}
							}

							//检测读取图片权限
							private void checkPicture() {
								if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
									readPicture();
								}else{
									ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3);
								}
							}

							//检测读取短信权限
							private void checkSms() {
								if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)==PackageManager.PERMISSION_GRANTED){
									readSms();
								}else{
									ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},2);
								}
							}

							//检测读取通讯录权限
							private void checkContacts() {
								if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
									readContact();
								}else{
									ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
								}
							}

							//--------------------------------------------------------------
							private void readMedio() {//读取视频
								Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;//视频URI路径
								String audioSize = MediaStore.Video.Media.SIZE;//视频长度
								String audioData = MediaStore.Video.Media.DATA;//视频路径
								String audioDuration = MediaStore.Video.Media.DURATION;//视频播放时间

								Cursor query = getContentResolver().query(uri, null, null, null, null);
								if (query!=null){
									txt.setText("");
									StringBuffer sb = new StringBuffer();
									while (query.moveToNext()){
										String size = query.getString(query.getColumnIndex(audioSize));
										String data = query.getString(query.getColumnIndex(audioData));
										String duration = query.getString(query.getColumnIndex(audioDuration));
										sb.append(data +":"+size+":"+duration+"\n");
									}
									query.close();
									txt.setText(sb.toString());
								}
							}


							private void readAudio() {//读取音频
								Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;//音频URI路径
								String audioSize = MediaStore.Audio.Media.SIZE;//音频大小
								String audioData = MediaStore.Audio.Media.DATA;//音频路径
								String audioDuration = MediaStore.Audio.Media.DURATION;//音频播放长度

								Cursor query = getContentResolver().query(uri, null, null, null, null);
								if (query!=null){
									txt.setText("");
									StringBuffer sb = new StringBuffer();
									while (query.moveToNext()){
										String size = query.getString(query.getColumnIndex(audioSize));
										String data = query.getString(query.getColumnIndex(audioData));
										String duration = query.getString(query.getColumnIndex(audioDuration));
										sb.append(data +":"+size+":"+duration+"\n");
									}
									query.close();
									txt.setText(sb.toString());
								}
							}

							private void readPicture() {//读取图片
								Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//图片URI路径
								String imgSize = MediaStore.Images.Media.SIZE;//图片长度
								String imgData = MediaStore.Images.Media.DATA;//图片路径

								Cursor query = getContentResolver().query(uri, null, null, null, null);
								if (query!=null){
									txt.setText("");
									StringBuffer sb = new StringBuffer();
									while (query.moveToNext()){
										String size = query.getString(query.getColumnIndex(imgSize));
										String data = query.getString(query.getColumnIndex(imgData));
										sb.append(data +":"+size+"\n");
									}
									query.close();
									txt.setText(sb.toString());
								}
							}

							private void readSms() {//读取短信
								Uri uri = Uri.parse("content://sms");//短信路径
								Cursor query = getContentResolver().query(uri, null, null, null, null);
								if (query!=null){
									txt.setText("");
									StringBuffer sb = new StringBuffer();
									while (query.moveToNext()){
										String address = query.getString(query.getColumnIndex("address"));
										String type = query.getString(query.getColumnIndex("type"));
										String body = query.getString(query.getColumnIndex("body"));

										sb.append("号码："+address+",类型："+type+",正文："+body +"\n");
									}
									query.close();
									txt.setText(sb.toString());
								}
							}

							private void readContact() {   //读取通讯录
								Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;//通讯录Uri路径
								String phoneName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME; //联系人的姓名
								String phoneNumber = ContactsContract.CommonDataKinds.Phone.NUMBER; //联系人的手机号

								Cursor query = getContentResolver().query(uri, null, null, null, null);
								if (query!=null){
									txt.setText("");
									StringBuffer sb = new StringBuffer();
									while (query.moveToNext()){
										String name = query.getString(query.getColumnIndex(phoneName));
										String number = query.getString(query.getColumnIndex(phoneNumber));

										sb.append("姓名:"+name +",手机号："+number+"\n");
									}
									query.close();
									txt.setText(sb.toString());
								}
							}

							//---------------------------------------------------------------
							//权限申请回调处理
							@Override
							public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
																   @NonNull int[] grantResults) {
								super.onRequestPermissionsResult(requestCode, permissions, grantResults);
								switch (requestCode){
									case 1://通讯录授权
										if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
											readContact();
										}else{
											Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
										}
										break;
									case 2://短信授权
										if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
											readSms();
										}else{
											Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
										}
										break;
									case 3://图片授权
										if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
											readPicture();
										}else{
											Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
										}
										break;
									case 4://音频授权
										if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
											readAudio();
										}else{
											Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
										}
										break;
									case 5://视频授权
										if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
											readMedio();
										}else{
											Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
										}
										break;
								}
							}
						}									
										
		2.自定义url  由A软件  操作B软件

                           A：
						      
							  1.MySq
							      public class MyDb extends SQLiteOpenHelper {

										public MyDb(Context context){
											this(context,"h1807b.db",null,1);
										}
										public MyDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
											super(context, name, factory, version);
										}

										@Override
										public void onCreate(SQLiteDatabase db) {
											String sql = "create table book(id integer primary key autoincrement,name text,author text,pages int(50),price double(50))";
											db.execSQL(sql);
										}

										@Override
										public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

										}
									}
							  
							   2.DatabaseProvider文件extends ContentProvider 实现里面的方法
							   
										public class DatabaseProvider extends ContentProvider {

											private MyDb myDb;
											private static UriMatcher uriMatcher;

											//生成对应的Uri路径   com.jiyun.h1807b改认真路径必须与清单文件中配置路径保持一致
											static {
												uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
												uriMatcher.addURI("com.jiyun.h1807b","book",0);//整张表
												uriMatcher.addURI("com.jiyun.h1807b","book/#",1);//单条id表记录
											}

											//创建数据库
											@Override
											public boolean onCreate() {
												myDb = new MyDb(getContext());//生成数据库
												return true;
											}

											public DatabaseProvider() {
											}

											//删除
											@Override
											public int delete(Uri uri, String selection, String[] selectionArgs) {
												SQLiteDatabase db = myDb.getWritableDatabase();//获取数据库操作类
												int ret = 0;
												switch (uriMatcher.match(uri)){//路径匹配
													case 0://整张表
														ret = db.delete("book", selection, selectionArgs);
														break;
													case 1://单条记录
														String bookId = uri.getPathSegments().get(1);//获取Uri路径中对应的id值

														ret = db.delete("book","id = ?",new String[]{bookId});
														break;
												}
												return  ret;
											}

											//插入
											@Override
											public Uri insert(Uri uri, ContentValues values) {
												SQLiteDatabase db = myDb.getWritableDatabase();
												Uri newUri = null;
												switch (uriMatcher.match(uri)){//匹配
													case 0:
													case 1:
														long bookId = db.insert("book", null, values);

														//根据插入后返回的id值  生成最新的Uri路径
														newUri = Uri.parse("content://com.jiyun.h1807b/book/" + bookId);
														break;
												}
												return  newUri;
											}

											//查询
											@Override
											public Cursor query(Uri uri, String[] projection, String selection,
																String[] selectionArgs, String sortOrder) {
												SQLiteDatabase db = myDb.getReadableDatabase();
												Cursor cursor = null;
												switch (uriMatcher.match(uri)){
													case 0://查询整张book表数据
														cursor = db.query("book",projection,selection,selectionArgs,null,null,sortOrder);
														break;
													case 1://查询对应ID的表数据
														String bookId = uri.getPathSegments().get(1);//获取对应的id值
														cursor = db.query("book",projection,"id = ?",new String[]{bookId},null,null,sortOrder);
														break;
												}
												return cursor;
											}

											//更改
											@Override
											public int update(Uri uri, ContentValues values, String selection,
															  String[] selectionArgs) {
												SQLiteDatabase db = myDb.getWritableDatabase();
												int ret = 0;
												switch (uriMatcher.match(uri)){
													case 0:
														ret = db.update("book", values, selection, selectionArgs);
														break;
													case 1:
														String bookId = uri.getPathSegments().get(1);//获取对应的id值
														ret = db.update("book",values,"id = ?",new String[]{bookId});
														break;
												}
												return ret;
											}

											//返回对应的MIME类型： 三部分组成
											@Override
											public String getType(Uri uri) {
												String str = null;
												switch (uriMatcher.match(uri)){
													case 0:
														str = "vnd.android.cursor.dir/vnd.com.jiyun.h1807b.book";
														break;
													case 1:
														str = "vnd.android.cursor.item/vnd.com.jiyun.h1807b.book";
														break;
												}
												return str;
											}

										}
                     
					   
					     B:通过uri去修改A软件中的数据  
						 
                                   xml是五个按钮    	
											public class MainActivity extends AppCompatActivity implements View.OnClickListener {

												private Button query;
												private Button insert;
												private Button update;
												private Button delete;
												private Uri insertNewUri;

												@Override
												protected void onCreate(Bundle savedInstanceState) {
													super.onCreate(savedInstanceState);
													setContentView(R.layout.activity_main);
													initView();
												}

												private void initView() {
													query = (Button) findViewById(R.id.query);
													insert = (Button) findViewById(R.id.insert);
													update = (Button) findViewById(R.id.update);
													delete = (Button) findViewById(R.id.delete);

													query.setOnClickListener(this);
													insert.setOnClickListener(this);
													update.setOnClickListener(this);
													delete.setOnClickListener(this);
												}

												@Override
												public void onClick(View v) {
													switch (v.getId()) {
														case R.id.query:
															query();
															break;
														case R.id.insert:
															insert();
															break;
														case R.id.update:
															update();
															break;
														case R.id.delete:
															delete();
															break;
													}
												}

												private void delete() {
													int delete = getContentResolver().delete(insertNewUri, null, null);
													if (delete>0){
														Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
													}else{
														Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
													}
												}

												private void update() {

													ContentValues values = new ContentValues();
													values.put("name","李四134494");
													values.put("author","李四");
													values.put("pages",1545);
													values.put("price",99.99);

													int update = getContentResolver().update(insertNewUri, values, null, null);
													if (update>0){
														Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show();
													}else{
   											    	}
												}

												private void insert() {
													Uri uri = Uri.parse("content://com.jiyun.h1807b/book");
													ContentValues values = new ContentValues();
													values.put("name","张三134494");
													values.put("author","张三");
													values.put("pages",1545);
													values.put("price",99.99);

													//插入新数据后，返回最新的包含ID值的Uri路径
													insertNewUri = getContentResolver().insert(uri, values);
												}

												private void query() {
													Uri uri = Uri.parse("content://com.jiyun.h1807b/book");
													Cursor cursor = getContentResolver().query(uri,null,null,null,null);
													if (cursor!=null){
														while (cursor.moveToNext()){
															String name = cursor.getString(cursor.getColumnIndex("name"));
															String author = cursor.getString(cursor.getColumnIndex("author"));
															int pages = cursor.getInt(cursor.getColumnIndex("pages"));
															double price = cursor.getDouble(cursor.getColumnIndex("price"));

															Log.e("lzj", "query: "+ name +":"+author+":"+pages+":"+price );
														}
														cursor.close();
													}
												}

												private void queryID() {
													Cursor cursor = getContentResolver().query(insertNewUri,null,null,null,null);
													if (cursor!=null){
														while (cursor.moveToNext()){
															String name = cursor.getString(cursor.getColumnIndex("name"));
															String author = cursor.getString(cursor.getColumnIndex("author"));
															int pages = cursor.getInt(cursor.getColumnIndex("pages"));
															double price = cursor.getDouble(cursor.getColumnIndex("price"));

															Log.e("lzj", "query: "+ name +":"+author+":"+pages+":"+price );
														}
														cursor.close();
													}
												}
											}					   
										