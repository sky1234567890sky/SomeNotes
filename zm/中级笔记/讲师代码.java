
1.ListView
 
		/**
		 * 手动加载
		 */
				View inflate = LayoutInflater.from(this).inflate(R.layout.layout_bottom, null);
				lv.addFooterView(inflate);

				Button btn = inflate.findViewById(R.id.btn);
				btn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						++pno;//原来访问的页数上加1
						initData();
					}
				});

		 /**
		 * 自动加载：
		 *      ListView设置滑动监听
		 */
					lv.setOnScrollListener(this);
				}

				private boolean flag = false;//是否为最后一条数据，false  不是最后一条   true  是最后一条

				/**
				 * 滑动状态改变
				 *      scrollState：当前的状态
				 *      AbsListView.OnScrollListener.SCROLL_STATE_IDLE  静止状态
				 *
				 */
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {

					//flag为最后一条数据   &&    静止状态
					if (flag && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
						++pno;  //分页加载  pno加1
						initData();   //加载更多
					}
				}

				/**
				 * 滑动方法
				 * @param view
				 * @param firstVisibleItem  当前显示的第一个条目位置
				 * @param visibleItemCount  当前页面可显示的条目个数
				 * @param totalItemCount  总共条目数
				 */
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
						totalItemCount) {

					//判断是否为最后一条数据：第一个条目 +  可显示条目数  =  总共条目数   &&   总共条目数  > 0
					if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount >0){
						flag = true;   //符合最后一条数据
					}
				}
			  }
			  
			  
	  //创建选项菜单
				/**
				 *  分组：
				 *  <group android:id="@+id/group1">
				 *
				 *  Item条目：
				 *     android:id="@+id/item1"     Id
				 *     android:title="增加"        标题
				 *     android:icon="@android:drawable/ic_delete"    图标
				 *     app:showAsAction="always"    显示方式   always外部  never内部  ifRoom空间
				 *     android:orderInCategory="200"   排列方式   值越小越靠前显示
				 */
				@Override
				public boolean onCreateOptionsMenu(Menu menu) {
					//方式一：通过xml文件创建菜单
					getMenuInflater().inflate(R.menu.layout_menu,menu);
					//方式二：通过Java代码添加
					MenuItem item1 = menu.add(1, 100, 100, "全选");
					item1.setIcon(android.R.drawable.ic_dialog_map);
					item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
					menu.add(1,200,200,"反选");
					return super.onCreateOptionsMenu(menu);
				}

				//选项菜单的选中监听处理
				@Override
				public boolean onOptionsItemSelected(MenuItem item) {
					switch (item.getItemId()){
						case R.id.item1:
							Toast.makeText(MainActivity.this,"item:"+item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
						case R.id.item2:
							Toast.makeText(MainActivity.this,"item:"+item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
						case R.id.item3:
							Toast.makeText(MainActivity.this,"item:"+item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
						case R.id.item4:
							Toast.makeText(MainActivity.this,"item:"+item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
						case 100:
							Toast.makeText(MainActivity.this,"item:"+item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
						case 200:
							Toast.makeText(MainActivity.this,"item:"+item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
					}
					return super.onOptionsItemSelected(item);
				}
			}
			
2.侧滑餐单		
		
				//1.设置图标不显示处理
				nv.setItemIconTintList(null);

				//2.设置菜单点击处理
				nv.setNavigationItemSelectedListener(this);

				//3.设置侧滑菜单图片更改
				View headerView = nv.getHeaderView(0);
				ImageView img = headerView.findViewById(R.id.img);
				img.setImageResource(android.R.drawable.ic_delete);
				img.setOnClickListener(new View.OnClickListener() {
					@Override 
					public void onClick(View v) {
						Toast.makeText(MainActivity.this, "更换头像", Toast.LENGTH_SHORT).show();
					}
				});

				//对侧滑菜单的监听处理（开、关监听）
				dl.addDrawerListener(new DrawerLayout.DrawerListener() {
					@Override
					public void onDrawerSlide(@NonNull View view, float v) {
					}

					@Override
					public void onDrawerOpened(@NonNull View view) {
						Toast.makeText(MainActivity.this, "开启", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onDrawerClosed(@NonNull View view) {
						Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onDrawerStateChanged(int i) {
					}
				});

		//设置侧滑菜单的开关处理
				ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,dl,toolBar,R.string.app_name,R.string.app_name);
				dl.addDrawerListener(toggle);                              
				toggle.syncState();                                                  
				
			}
               //侧滑菜单的点击事件   需要手动实验接口 NavigationView.OnNavigationItemSelectedListener  生成如下方法
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				//menuItem.setChecked(true);
				switch (menuItem.getItemId()) {
					case R.id.item1:
						Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
						break;
					case R.id.item2:
						Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
						break;
					case R.id.item3:
						Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
						break;
					case R.id.item4:
						Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
						break;
				}
				return true;
			}

			//通过代码控制侧滑菜单的开启和关闭
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.btn1:
						dl.openDrawer(Gravity.LEFT);
						break;
					case R.id.btn2:
						dl.closeDrawer(Gravity.LEFT);
						break;
				}	

				
				

3.上下文
				
			//3.给对应的View控件设置上下文菜单
						registerForContextMenu(btn3);
						registerForContextMenu(btn1);
		  //1.创建上下文菜单
					@Override
					public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
						super.onCreateContextMenu(menu, v, menuInfo);

						//根据不同View设置不同上下文菜单
						if (v.getId() == R.id.btn1){
							//方式一：通过xml文件创建菜单
							getMenuInflater().inflate(R.menu.context_menu1, menu);
						}else{
							//方式二：通过Java代码创建菜单
							menu.add(1, 100, 100, "全选");
							menu.add(1, 200, 200, "反选");
						}
					}

					//2.设置上下文菜单的监听事件
					@Override
					public boolean onContextItemSelected(MenuItem item) {
						//获取ItemId进行判断
						switch (item.getItemId()) {
							case R.id.item1:
								Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
								break;
							case R.id.item2:
								Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
								break;
							case R.id.item3:
								Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
								break;
							case R.id.item4:
								Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
								break;
						}
						rdeturn super.onContextItemSelected(item);

3.Toolbar

		 private void initView() {
					//获取控件
					toolbar = (Toolbar) findViewById(R.id.toolbar);

					//设置Logo
					toolbar.setLogo(android.R.drawable.ic_dialog_email);

					//设置主标题
					toolbar.setTitle("玩Android");

					//设置子标题
					toolbar.setSubtitle("首页");

					//设置返回按钮
					toolbar.setNavigationIcon(android.R.drawable.ic_delete);
         1.千万不要忘啦大哥
		//设置标题栏显示
		setSupportActionBar(toolbar);

					//返回按钮的监听方式必须放置到setSupportActionBar之后才会有效果
					toolbar.setNavigationOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Toast.makeText(MainActivity.this,"关闭",Toast.LENGTH_SHORT).show();
						}
					});

					/**
					 * （了解即可）菜单监听处理的方式二
					 */
					//toolbar.setOnMenuItemClickListener(this);
				}

				/**
				 * 菜单的创建
				 */
				@Override
				public boolean onCreateOptionsMenu(Menu menu) {
					MenuItem item = menu.add(1, 100, 100, "增加");
					item.setIcon(R.mipmap.ic_launcher);//添加图标
					menu.add(1,200,200,"删除");
					return super.onCreateOptionsMenu(menu);
				}

				/**
				 * 选择菜单监听处理的方式一
				 */
				@Override
				public boolean onOptionsItemSelected(MenuItem item) {
					switch (item.getItemId()){
						case 100:
							Intent intent = new Intent(MainActivity.this,Main2Activity.class);
							startActivity(intent);
							Toast.makeText(MainActivity.this,"dadasdasda",Toast.LENGTH_SHORT).show();
							break;
						case 200:
							Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
							break;
					}
					return super.onOptionsItemSelected(item);
				}

				/**
				 * 菜单监听处理的方式二（了解即可）
				 */
				@Override
				public boolean onMenuItemClick(MenuItem menuItem) {
					switch (menuItem.getItemId()){
						case 100:
							Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
							break;
						case 200:
							Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
							break;
					}
					return false;
				}


				/**
				 * 解决菜单图标不显示问题
				 */
				@SuppressLint("RestrictedApi")
				@Override
				protected boolean onPrepareOptionsPanel(View view, Menu menu) {
					if (menu != null) {
						if (menu.getClass() == MenuBuilder.class) {
							//通过反射调用setOptionalIconsVisible（true）
							try {
								Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
								m.setAccessible(true);
								m.invoke(menu, true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					return super.onPrepareOptionsPanel(view, menu);

				
				
				
4.RecyclerView


			//1.获取控件
			lv = (RecyclerView) findViewById(R.id.lv);

			//2.数据源
			list = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				list.add(getE(i));
			}

			//3.设置适配器
			adapter = new MyAdapter(this,list);
			lv.setAdapter(adapter);

			//4.设置显示方式
			//线性方式
			/*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			lv.setLayoutManager(layoutManager);*/

			//网格方式
			GridLayoutManager layoutManager = new GridLayoutManager(this,2);
			lv.setLayoutManager(layoutManager);

			//瀑布流方式
			/*StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
			lv.setLayoutManager(layoutManager);*/
                                   
			//设置分隔线
			/*lv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
			lv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));*/

			//设置item条目的动画效果
			lv.setItemAnimator( new DefaultItemAnimator());



     2.适配器
			public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

				private Context context;
				private ArrayList<ResultDemo.DataBean.DatasBean> list;

				//构造方法传递
				public MyAdapter(Context context, ArrayList<ResultDemo.DataBean.DatasBean> list) {
					this.context = context;
					this.list = list;
				}

				//设置数据源
				public void setList(ArrayList<ResultDemo.DataBean.DatasBean> list) {
					this.list = list;
				}

				//创建显示视图
				@NonNull
				@Override
				public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

					/**
					 * 根据不同type类型、创建不同布局ViewHolder
					 */
					RecyclerView.ViewHolder viewHolder = null;//抽出父类统一返回
					if (type == 100){//显示第一种布局
						View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item1, null);
						viewHolder = new ViewHolderA(inflate);
					}else{//显示第二种布局
						View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item2, null);
						viewHolder = new ViewHolderB(inflate);
					}
					return viewHolder;//返回统一
				}
				//绑定数据
				@Override
				public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
					//获取数据源
					ResultDemo.DataBean.DatasBean datasBean = list.get(position);
					/**
					 * 根据不同的viewHolder显示不同的数据操作
					 */
					if (viewHolder instanceof  ViewHolderA){//判断viewHolder是否为ViewHolderA类型
						ViewHolderA viewHolderA = (ViewHolderA) viewHolder;//将父类viewHolder强转为子类ViewHolderA

						//数据绑定显示
						viewHolderA.titleA.setText(datasBean.getTitle());
						viewHolderA.sourceA.setText(datasBean.getChapterName());
						Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolderA.imgA);
					}else if (viewHolder instanceof  ViewHolderB){//判断viewHolder是否为ViewHolderB类型
						ViewHolderB viewHolderB = (ViewHolderB) viewHolder;//将父类viewHolder强转为子类ViewHolderB

						//数据绑定显示
						viewHolderB.titleB.setText(datasBean.getTitle());
						viewHolderB.sourceB.setText(datasBean.getChapterName());
						Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolderB.imgB);
					}

					/**
					 * 3.实现点击事件的回调处理
					 */
					viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (onItemClickListener !=null){
								onItemClickListener.onItemClick(position);
							}
						}
					});

					//长按监听、返回对应的position位置
					viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
						@Override
						public boolean onLongClick(View v) {
							//1.通过接口回调，把position传给页面使用
							if (onItemClickListener !=null){
								onItemClickListener.onLongItemClick(position);
							}
							//2.默认设置不响应
							return false;
						}
					});
				}

				//返回集合长度
				@Override
				public int getItemCount() {
					return list.size();
				}

				/**
				 *  根据position等不同规则，返回不同的类型值
				 *
				 *      本案例使用奇偶数划分
				 */
				@Override
				public int getItemViewType(int position) {
					if (position % 2 == 0){
						return 100;
					}else{
						return 200;
					}
				}

				//布局辅助类ViewHolderA
				class ViewHolderA extends RecyclerView.ViewHolder{

					private ImageView imgA;
					private TextView titleA;
					private TextView sourceA;
					public ViewHolderA(@NonNull View itemView) {
						super(itemView);
						imgA = itemView.findViewById(R.id.img);
						titleA = itemView.findViewById(R.id.title);
						sourceA = itemView.findViewById(R.id.source);
					}
				
				//布局辅助类ViewHolderB
				class ViewHolderB extends RecyclerView.ViewHolder{
					private ImageView imgB;
					private TextView titleB;
					private TextView sourceB;
					public ViewHolderB(@NonNull View itemView) {
						super(itemView);
						imgB = itemView.findViewById(R.id.img);
						titleB = itemView.findViewById(R.id.title);
						sourceB = itemView.findViewById(R.id.source);
					}
				}

				/**
				 * 1.定义接口，提供成员变量、实现公开的set方法
				 */
				private OnItemClickListener onItemClickListener;

				public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
					this.onItemClickListener = onItemClickListener;
				}

				public interface OnItemClickListener{
					void onItemClick(int position);
					void onLongItemClick(int position);
				}
			}


5.NotificationManager<通知>


					//1.获取通知管理器类
					NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                                                                                                                                                   
					//延时意图
					Intent intent = new Intent(this,Main2Activity.class);
					PendingIntent pendingIntent = PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);

					//2.构建通知类
					Notification.Builder builder = new Notification.Builder(this);
					builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
					builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));//设置大图标
					builder.setContentTitle("通知");//标题
					builder.setContentText("dadsadasdsadsa");//内容
					builder.setContentIntent(pendingIntent);//延时意图  ：  点击跳转页面
					builder.setAutoCancel(false);//自动消失
					builder.setDefaults(Notification.DEFAULT_ALL);//提示效果（震动、呼吸灯、声音提示）

					//3.获取通知
					Notification notification = builder.build();

					//4.发送通知
					notificationManager.notify(100,notification);
				}
				..........................................................................................................................

				private void send2() {
					//1.获取通知管理器类
					NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                                                           
					//2.构建通知类
					String channelId = "1";
					String channelName = "default";
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
						NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
						channel.enableLights(true);//开启指示灯,如果设备有的话。
						channel.setLightColor(Color.RED);//设置指示灯颜色
						channel.setShowBadge(true);//检测是否显示角标
						notificationManager.createNotificationChannel(channel);
					}

					//延时意图
					Intent intent = new Intent(this,Main2Activity.class);
					PendingIntent pendingIntent = PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                	//2.构建通知类
					NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
					builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
					builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));//设置大图标
					builder.setContentTitle("通知");//标题
					builder.setContentText("dadsadasdsadsa");//内容
					builder.setContentIntent(pendingIntent);//延时意图  ：  点击跳转页面
					builder.setAutoCancel(false);//自动消失
					builder.setDefaults(Notification.DEFAULT_ALL);//提示效果（震动、呼吸灯、声音提示）
					//3.获取通知
					Notification notification = builder.build();
					//4.发送通知
					notificationManager.notify(100,notification);



6.popupWindow<会动的弹窗>

				//1.创建PoP（必须包含：显示View、宽、高）
				final PopupWindow popupWindow = new PopupWindow(this);
				View inflate = LayoutInflater.from(this).inflate(R.layout.layout_item, null);
				popupWindow.setContentView(inflate);//显示的View控件
				popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);//显示宽度
				popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//显示高度
				popupWindow.setFocusable(true);//是否可以获取焦点

				PopupWindow popupWindow2 = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT, true); 

				//5.点击外部可以消失
				popupWindow.setBackgroundDrawable(new ColorDrawable());
				popupWindow.setOutsideTouchable(true);

				//6.设置进出动画
				popupWindow.setAnimationStyle(R.style.popWindow);

				//2.显示
				//popupWindow.showAsDropDown(bB);//显示到相对某个控件的正下方
				//popupWindow.showAsDropDown(bB,-100,-200);//显示到某个控件正下方，同时与X/Y的偏移值
				  popupWindow.showAtLocation(btn,Gravity.RIGHT|Gravity.BOTTOM,0,0);//相对父窗体的对齐方式

				//3.pop事件处理
				Button btn1 = inflate.findViewById(R.id.btn1);
				btn1.setOnClickListener(new View.OnClickListener() {//按钮事件监听处理
					@Override
					public void onClick(View v) {
						Toast.makeText(MainActivity.this,"弹出",Toast.LENGTH_SHORT).show();
					}
				});
				inflate.findViewById(R.id.ll).setOnClickListener(new View.OnClickListener() {//点击阴影关闭pop
					@Override
					public void onClick(View v) {
						//4.关闭Pop
						popupWindow.dismiss();
					}
				});
7.Fragment
        1.在activity添加动态的fragment
				private void initView() {
				A = (Button) findViewById(R.id.A);
				B = (Button) findViewById(R.id.B);
				C = (Button) findViewById(R.id.C);

				A.setOnClickListener(this);
				B.setOnClickListener(this);
				C.setOnClickListener(this);
			}

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.A:
						//1.创建待添加的fragment页面
						LeftFragment leftFragment = new LeftFragment();
						replaceFragment(leftFragment);

						break;
					case R.id.B:
						//1.创建待添加的fragment页面
						RightFragment rightFragment = new RightFragment();
						replaceFragment(rightFragment);

						break;
					case R.id.C:
						//1.创建待添加的fragment页面
						AFragment aFragment = new AFragment();
						replaceFragment(aFragment);

						break;
				}
			}

			private void replaceFragment(Fragment fragment) {
				//2.获取管理器类
				FragmentManager manager = getSupportFragmentManager();
				//3.开启事物
				FragmentTransaction transaction = manager.beginTransaction();
				//4.添加或替换
				transaction.replace(R.id.content,fragment);
				//5.提交事物
				transaction.commit();		
			}
			
			
    2.可是一层一层倒退的(有个的add增加,hide隐藏,show实现的结合)
			
			        FragmentManager manager = getSupportFragmentManager();
					FragmentTransaction transaction = manager.beginTransaction();
					//隐藏所有页面
					if (aFragment!=null){
						transaction.hide(aFragment);
					}
					if (bFragment!=null){
						transaction.hide(bFragment);
					}
					switch (v.getId()) {
						case R.id.btn1:
							if (aFragment == null){//判断为空，创建页面，添加页面
								aFragment = new AFragment();
								transaction.add(R.id.fl,aFragment);
							}else{//判断不为空、显示页面
								transaction.show(aFragment);
							}
							break;
						case R.id.btn2:
							if (bFragment== null){//判断为空，创建页面，添加页面
								bFragment = new BFragment();
								transaction.add(R.id.fl,bFragment);
							}else{//判断不为空、显示页面
								transaction.show(bFragment);
							}
							break;
					}
					transaction.addToBackStack(null);//回退栈
					transaction.commit();
					
	3.在activity中直接获取到fragment中控件(静,动态)

					   private void initView() {
							mBta = (Button) findViewById(R.id.bta);
							mBta.setOnClickListener(this);

							//寻找动态的fragment并赋予帧布局中
							FragmentManager manager = getSupportFragmentManager();
							FragmentTransaction transaction = manager.beginTransaction();
							AFragment aFragment = new AFragment();
							transaction.replace(R.id.content,aFragment,"aa");
							transaction.commit();
						}

						@Override
						public void onClick(View v) {
							switch (v.getId()) {
								default:
									break;
								case R.id.bta:
					//                Activity获取Fragment对象(静态方式)
					//                Fragment fragmentById =getSupportFragmentManager().findFragmentById(R.id.fm);  //在xml中的fragment的id           
					//                View view = fragmentById.getView();///获取Fragment中的根View控件
					//                Button viewById = view.findViewById(R.id.f);//通过View获取按钮控件
					//                viewById.setText("Activity获取Fragment对象(静态方式)");



									Fragment aa = getSupportFragmentManager().findFragmentByTag("aa");
									View view = aa.getView();
									Button button = view.findViewById(R.id.f);
									button.setText("Activity获取Fragment对象(动态方式)");
									
									break;
							}
						}	
		4.在fragment中直接更改activity中的控件

                FragmentActivity activity = getActivity(); //Fragment获取Activirty对象
                Button button = activity.findViewById(R.id.btnA);//通过activity获取控件
                button.setText("Fragment获取Activirty对象");//设置控件内容		
			
			
		5.activity-fragment的穿值方法
                
                 1.在创建之前传
				       //第一种:用过有参构造
                        	fragment:
                                    
										private String name;
										private int age;

										@SuppressLint("ValidFragment")
										public AFragment(String name, int age) {
											this.name = name;
											this.age = age;
										}						
				            activity:
										FragmentManager manager = getSupportFragmentManager();
										FragmentTransaction transaction = manager.beginTransaction();
										//第一种:用过有参构造
								        AFragment  fragment = new AFragment("赵龙", 19);
				                        transaction.replace(R.id.content, aFragment);
										transaction.commit();
			            //第二种:用bundle
						    activity:
							
							            FragmentManager manager = getSupportFragmentManager();
										FragmentTransaction transaction = manager.beginTransaction();
										//第二种:用bundle
						                Bundle bundle = new Bundle();
										bundle.putString("name","小明");
										bundle.putString("age","90");
										AFragment aFragment = new AFragment();
										aFragment.setArguments(bundle);
										transaction.replace(R.id.content, aFragment);
										transaction.commit();
							fragment:
							
                                        Bundle arguments = getArguments();
										String name = arguments.getString("name");
										String age = arguments.getString("age");
										mAbt.setText(name+":"+age);							
										
										
				
				 2.在创建之后传
				            activity:
							          aFragment.setName("熊大",12);
				        
				            fragment:
										创建set方法						     
										//给已有的改变
										public void setName(String name,int age) {
											mAbt.setText(name+":"+age);
										}
				                       
				
		6.fragment-activity的穿值方法	
				            fragment:
                                        //1.定义接口
										private AA aa;

										public void setAa(AA aa) {
											this.aa = aa;
										}

										public interface AA{
											void send(String name,String age);
										}
							
				
				
				                        //3.接口回调
									       if(aa!=null){
												 aa.send("dsa","dasdas");
											 }
				             activity:
							         
									//2.传递对象实现接口重写方法
									blankFragment.setSendDataToActivity(this);
				         
						           //接口回调数据刷新
									@Override
									public void send(String str1, String str2) {
										btnA.setText(str1+":"+str2);
									}
8.ViewPager的使用			
				
				      1.使用ViewPager静态绑定xml
										//1.获取控件
										vp = (ViewPager) findViewById(R.id.vp);

										//2.创建数据源（静态布局：模拟开机引导页面）
										list = new ArrayList<View>();
										View view1 = LayoutInflater.from(this).inflate(R.layout.layout_item1, null);
										View view2 = LayoutInflater.from(this).inflate(R.layout.layout_item2, null);
										View view3 = LayoutInflater.from(this).inflate(R.layout.layout_item3, null);
										list.add(view1);
 										list.add(view2);
										list.add(view3);

										//3.设置适配器
										adapter = new MyAdapter(this,list);
										vp.setAdapter(adapter);

									
										//设置默认选中位置
										vp.setCurrentItem(list.size()-1);
										
										//设置滑动监听事件
										vp.addOnPageChangeListener(this);
									}

									@Override
									public void onPageScrolled(int i, float v, int i1) {
										//滑动位置
									}

									@Override
									public void onPageSelected(int i) {
										//当前显示位置
										Toast.makeText(this,"显示"+i+"页面",Toast.LENGTH_SHORT).show();
									}

									@Override
									public void onPageScrollStateChanged(int i) {
										//滑动状态
										
										
		                        适配器
                                    public class MyAdapter extends PagerAdapter {
									private Context context;
									private ArrayList<View> list;

									public MyAdapter(Context context, ArrayList<View> list) {
										this.context = context;
										this.list = list;
									}

									//返回长度
									@Override
									public int getCount() {
										return list.size();
									}

									//判断视图
									@Override
									public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
										return view == o;
									}

									//初始化视图
									@NonNull
									@Override
									public Object instantiateItem(@NonNull ViewGroup container, int position) {
										//将对应view添加到容器中
										container.addView(list.get(position));
										//返回集合对应的View对象
										return list.get(position);
									}

									//销毁视图
									@Override
									public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
										//容器中移除视图View
										container.removeView(list.get(position));
									}
									//绑定tablayout
									    @Nullable
									@Override
									public CharSequence getPageTitle(int position) {
										return title.get(position);
									}
									
									
								}		
                         2.使用ViewPager绑定动态xml
                                   private int[] imgs = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

						           private void initView() {
									vp1 = (ViewPager) findViewById(R.id.vp1);

									list = new ArrayList<>();
									for (int i = 0; i < imgs.length; i++) {
										ImageView imageView = new ImageView(this);
										imageView.setImageResource(imgs[i]);
										list.add(imageView);
									 }
									adapter = new MyAdapter(this,list);
									vp1.setAdapter(adapter);
								    }
	
								   同一个适配器
								   
8.ViewPager.Fragment.TabLayout
								   
						3.使用ViewPager绑定静态fragment
									private TabLayout tab;
									private ViewPager vp;
									private ArrayList<Fragment> list;
									private ArrayList<String> title;
									private MyFragmentAdapter adapter;

									@Override
									protected void onCreate(Bundle savedInstanceState) {
										super.onCreate(savedInstanceState);
										setContentView(R.layout.activity_main5);
										initView();
									}

									private void initView() {
										tab = (TabLayout) findViewById(R.id.tab);
										vp = (ViewPager) findViewById(R.id.vp);

										list = new ArrayList<>();
										list.add(new AFragment());
										list.add(new BFragment());
										list.add(new CFragment());

										title = new ArrayList<>();
										title.add("新闻");
										title.add("娱乐");
										title.add("汽车");

										adapter = new MyFragmentAdapter(getSupportFragmentManager(),list,title);
										vp.setAdapter(adapter);

						//(静态绑定)tab与vp设置页面绑定
								tab.setupWithViewPager(vp);
						     
									
									    <android.support.design.widget.TabLayout
											android:layout_width="match_parent"
											android:layout_height="30dp"
											app:tabMode="scrollable"
											android:id="@+id/tab"
											app:tabMaxWidth="0dp"
											>
										</android.support.design.widget.TabLayout>
									
									
									
									配置器 继承 extends FragmentPagerAdapter
						4.使用ViewPager绑定动态fragment		
						                public class MainActivity extends AppCompatActivity {
										private TabLayout tab;
										private ViewPager vp;
										private ArrayList<Fragment> list;
										private MyFragmentAdapter adapter;

										@Override
										protected void onCreate(Bundle savedInstanceState) {
											super.onCreate(savedInstanceState);
											setContentView(R.layout.activity_main);
											initView();
										}

										private void initView() {
											tab = (TabLayout) findViewById(R.id.tab);
											vp = (ViewPager) findViewById(R.id.vp);

											list = new ArrayList<>();
											for (int i = 0; i < 20; i++) {
												//创建ViewPager中Fragment
												list.add(new BlankFragment("内容"+i));
												//创建TabLayout中的显示标题
												tab.addTab(tab.newTab().setText("标题"+i));
											}

											//适配器
											adapter = new MyFragmentAdapter(getSupportFragmentManager(),list);
											vp.setAdapter(adapter);

											//相互绑定
											tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {//通过选择对应的tab，切换vp的位置
												@Override
												public void onTabSelected(TabLayout.Tab tab) {
													vp.setCurrentItem(tab.getPosition());
												}
												@Override
												public void onTabUnselected(TabLayout.Tab tab) {
												}
												@Override
												public void onTabReselected(TabLayout.Tab tab) {
												}
											});
											vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));//通过vp的滑动，选择tab的位置
										}
									}
						
						   
9.banner的使用(轮播图)

                              1.在自定义数据库是
                                                    banner = (Banner) findViewById(R.id.banner);
													list = new ArrayList<>();
													list.add(R.mipmap.icon_home_pager_selected);
													list.add(R.mipmap.icon_knowledge_hierarchy_not_selected);
													list.add(R.mipmap.icon_me_not_selected);
													list.add(R.mipmap.icon_navigation_selected);

													//1.设置图片数据
													banner.setImages(list);

													//2.图片加载器
													banner.setImageLoader(new ImageLoader() {
														/**
														 *
														 * @param context  上下文对象
														 * @param path   图片路径类型  Object
														 * @param imageView  显示图片
														 */
														@Override
														public void displayImage(Context context, Object path, ImageView imageView) {
															Integer p = (Integer) path;
															imageView.setImageResource(p);

														   // Glide.with(context).load(path).into(imageView);
														}
													});

													banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
													//3.开启使用
													banner.start();
						     2.多布局加载(网络解析轮播图)
							 
													public class MyHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

													private Context context;
													private ArrayList<HomeBannerBean.DataBean> bannerList;
													private ArrayList<HomeArticleBean.DataBean.DatasBean> articleList;

													public MyHomeAdapter(Context context, ArrayList<HomeBannerBean.DataBean> bannerList,
																		 ArrayList<HomeArticleBean.DataBean.DatasBean> articleList) {
														this.context = context;
														this.bannerList = bannerList;
														this.articleList = articleList;
													}

													public void setBannerList(ArrayList<HomeBannerBean.DataBean> bannerList) {
														this.bannerList = bannerList;
													}

													public void setArticleList(ArrayList<HomeArticleBean.DataBean.DatasBean> articleList) {
														this.articleList = articleList;
													}

													@NonNull
													@Override
													public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

														RecyclerView.ViewHolder viewHolder = null;

														if (viewType == 0) {
															View inflate = LayoutInflater.from(context).inflate(R.layout.home_item_banner, null);
															viewHolder = new BannerViewHolder(inflate);
														} else {
															View view = LayoutInflater.from(context).inflate(R.layout.home_item_article, null);
															viewHolder = new ArticleViewHolder(view);
														}
														return viewHolder;
													}

													@Override
													public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
														if (holder instanceof BannerViewHolder) {
															BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

															bannerViewHolder.banner.setImages(bannerList);
															bannerViewHolder.banner.setImageLoader(new ImageLoader() {
																@Override
																public void displayImage(Context context, Object path, ImageView imageView) {
																	HomeBannerBean.DataBean dataBean = (HomeBannerBean.DataBean) path;
																	Glide.with(context).load(dataBean.getImagePath()).into(imageView);
																}
															});
															bannerViewHolder.banner.start();
														} else if (holder instanceof ArticleViewHolder) {
															ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;

															int newPosition = position;
															if (bannerList.size() > 0) {
																newPosition = position - 1;
															}
															final HomeArticleBean.DataBean.DatasBean datasBean = articleList.get(newPosition);
															articleViewHolder.tv_name.setText(datasBean.getAuthor());
															articleViewHolder.tv_info.setText(datasBean.getSuperChapterName());
															articleViewHolder.tv_content.setText(datasBean.getTitle());
															articleViewHolder.tv_time.setText(datasBean.getNiceDate());

															articleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
																@Override
																public void onClick(View v) {
																	if (onItemClickListener!=null){
																		onItemClickListener.onItemClick(datasBean);
																	}
																}
															});
														}
													}


													@Override
													public int getItemViewType(int position) {
														if (position == 0) {
															return 0;
														} else {
															return 1;
														}
													}

													@Override
													public int getItemCount() {
														if (bannerList.size() > 0) {
															return articleList.size() + 1;
														} else {
															return articleList.size();
														}
													}

													class BannerViewHolder extends RecyclerView.ViewHolder {

														private Banner banner;

														public BannerViewHolder(@NonNull View itemView) {
															super(itemView);
															banner = itemView.findViewById(R.id.banner);
														}
													}

													class ArticleViewHolder extends RecyclerView.ViewHolder {

														private TextView tv_name;
														private TextView tv_info;
														private TextView tv_content;
														private TextView tv_time;

														public ArticleViewHolder(@NonNull View itemView) {
															super(itemView);

															tv_name = itemView.findViewById(R.id.tv_name);
															tv_info = itemView.findViewById(R.id.tv_info);
															tv_content = itemView.findViewById(R.id.tv_content);
															tv_time = itemView.findViewById(R.id.tv_time);
														}
													}


													//
													private OnItemClickListener onItemClickListener;

													public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
														this.onItemClickListener = onItemClickListener;
													}

													public interface OnItemClickListener{
														void onItemClick(HomeArticleBean.DataBean.DatasBean datasBean);
													}
												}

10.WebView(给个网址直接打开)

					 
						                        private void initView() {
												web = (WebView) findViewById(R.id.web);

												web.getSettings().setJavaScriptEnabled(true);
												web.setWebViewClient(new WebViewClient());
												web.loadUrl("网址就OK");
						
									
									
									
11.XRecyclerView(可以向上刷新,向下自动加载啊)								

                               
									
									        mLv.setLoadingListener(new XRecyclerView.LoadingListener() {
											@Override
											public void onRefresh() {
											   a=0;
											   list.clear();
											   initDada();
											   mLv.refreshComplete();
											}

											@Override
											public void onLoadMore() {
												++a;
												initDada();
												mLv.loadMoreComplete();
												}
											});

											
12.ViewPager+Fragment+RadioGroup   单选按钮与fragment的绑定
											/**
											 * 初始化主页面ViewPager+Fragment+RadioGroup
											 */
											private void initViewPager() {

												//数据源
												list = new ArrayList<>();
												list.add(new HomeFragment());
												list.add(new HomeFragment());
												list.add(new HomeFragment());
												list.add(new HomeFragment());
												list.add(new HomeFragment());

												//适配器
												adapter = new MyMainPagerAdapter(getSupportFragmentManager(),list);
												vp.setAdapter(adapter);

												//设置默认选中页面
												rg.check(R.id.home);
												vp.setCurrentItem(0);

												//相互关联
												rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
													@Override
													public void onCheckedChanged(RadioGroup group, int checkedId) {
														switch (checkedId){
															case R.id.home:
																vp.setCurrentItem(0);
																break;
															case R.id.knowledge:
																vp.setCurrentItem(1);
																break;
															case R.id.navigation:
																vp.setCurrentItem(2);
																break;
															case R.id.project:
																vp.setCurrentItem(3);
																break;
															case R.id.me:
																vp.setCurrentItem(4);
																break;
														}
													}
												});

												vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
													@Override
													public void onPageScrolled(int i, float v, int i1) {

													}

													@Override
													public void onPageSelected(int position) {
														switch (position){
															case 0:
																rg.check(R.id.home);
																break;
															case 1:
																rg.check(R.id.knowledge);
																break;
															case 2:
																rg.check(R.id.navigation);
																break;
															case 3:
																rg.check(R.id.project);
																break;
															case 4:
																rg.check(R.id.me);
																break;

														}
													}

													@Override
													public void onPageScrollStateChanged(int i) {

													}
												});
											}



12.fragment的嵌套
									   if (msg.what == 0x001){
											String str = (String) msg.obj;

											//数据解析
											Gson gson = new Gson();
											ProjectTabBean projectTabBean = gson.fromJson(str, ProjectTabBean.class);

											//数据判断
											if (projectTabBean!=null&&projectTabBean.getData()!=null){

												list = new ArrayList<>();//创建数据源

												List<ProjectTabBean.DataBean> data = projectTabBean.getData();

												//循环添加页面、tab栏
												for (int i = 0; i < data.size(); i++) {
													list.add(new ProjectListFragment(data.get(i).getId()+""));//创建Fragment

													tab.addTab(tab.newTab().setText(data.get(i).getName()));//创建Tab
												}

												//设置适配器
							1主要是这个	//Fragment嵌套使用getChildFragmentManager()/进行管理切换
												adapter = new MyProjectPageAdapter(getChildFragmentManager(),list);
												vp.setAdapter(adapter);

												//相互关联
												tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
													@Override
													public void onTabSelected(TabLayout.Tab tab) {
														vp.setCurrentItem(tab.getPosition());
													}

													@Override
													public void onTabUnselected(TabLayout.Tab tab) {

													}

													@Override
													public void onTabReselected(TabLayout.Tab tab) {

													}
												});

												vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
											}

										}
									}	
13.懒加载哦

											/**
											 * 懒加载
											 */
											@Override
											public void setUserVisibleHint(boolean isVisibleToUser) {
												super.setUserVisibleHint(isVisibleToUser);

												if (getUserVisibleHint()){//判断是否需要加载数据
													initData();//开始加载网络数据
												}
											}
									
									
							   
14.广播


                         1.动态接收(新建的一个类继承 extends BroadcastReceiver)
						 
								/**
								 * 广播接收器：模拟动态监听 ：网络变化（系统广播）
								 */
								public class NetWorkChangeReceiver extends BroadcastReceiver {
									@Override 
									public void onReceive(Context context, Intent intent) {
										//获取网络管理器类
										ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);//网络
                                                                          
										//网络信息
										NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

										if (activeNetworkInfo!=null && activeNetworkInfo.isAvailable()){
											Toast.makeText(context,"有网",Toast.LENGTH_SHORT).show();
										}else{
											Toast.makeText(context,"没网",Toast.LENGTH_SHORT).show();
										}
									}
								}
								
								
							在接受的activity中注册		
									//注册广播
									netWorkChangeReceiver = new NetWorkChangeReceiver();//广播接收器
									IntentFilter intentFilter = new IntentFilter();
									intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//接收网络变化

									registerReceiver(netWorkChangeReceiver,intentFilter);//谁接、接谁
									
									//注销广播							
									@Override
									protected void onDestroy() {
										super.onDestroy();
									}

  				       2.静态接收,清单文件中
					                       
					                  <rece=,iver 
											android:name=".receiver2.MyReceiver1"
											android:enabled="true"
											android:exported="true">
											
											<intent-filter android:priority="80"> 接受级别
												<action android:name="com.jiyun.action.ABC" />
											</intent-filter>
											
										</receiver>
										
						3.无序发送(标准模式)
						
									Intent intent = new Intent("com.jiyun.action.ABC");
									intent.putExtra("name","zhansg");
									sendBroadcast(intent);          
                                 			
						4.有序发送
								
												Intent intent1 = new Intent("com.jiyun.action.ABC");
												sendOrderedBroadcast(intent1,null);
									 1.设置优先级是在清单文件中:priority="100" 
									 2.拦截是在接受的代码中写abortBroadcast拦截广播 
								    
										
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



















	
				