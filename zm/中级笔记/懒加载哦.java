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
									