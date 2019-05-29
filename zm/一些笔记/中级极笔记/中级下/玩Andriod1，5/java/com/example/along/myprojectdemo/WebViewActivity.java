package com.example.along.myprojectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
public class WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        WebView webView = findViewById(R.id.wv);
        //获取webview的设置对象
        WebSettings settings = webView.getSettings();
        //设置webview支持js代码
        settings.setJavaScriptEnabled(true);

        //设配手机屏幕
        //支持viewport标签适配手机屏幕
        settings.setUseWideViewPort(true);
        //将h5页面的内容缩放到屏幕宽度以内
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl(url);

        //获取网页的title
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //title 网页的标题
                //mToolBar.setTitle(title);
            }
        });

    }
}
