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
