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