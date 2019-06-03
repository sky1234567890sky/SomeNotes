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