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

											