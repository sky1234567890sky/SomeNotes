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