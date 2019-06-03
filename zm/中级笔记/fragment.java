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