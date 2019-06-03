        private void initView() {
												web = (WebView) findViewById(R.id.web);

												web.getSettings().setJavaScriptEnabled(true);
												web.setWebViewClient(new WebViewClient());
												web.loadUrl("网址就OK");