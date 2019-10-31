package com.example.recyclerview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyWebActivity extends AppCompatActivity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_web);

		webView = findViewById(R.id.web_view);
		webView.setWebViewClient(new WebViewClient());//点击超链接不会在系统浏览器打开

		webView = new WebView(getApplicationContext());

		setContentView(webView);
		/**
		 * url最后写!!!!!!!!
		 */
		webView.loadUrl("http://www.ihchina.cn");
		//webView.loadUrl("http://www.baidu.com");









		//webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
		webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
		webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
		webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
		webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
		webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
		webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
		//webView.getSettings().setDomStorageEnabled(true);//DOM Storage
// displayWebview.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用


		Toolbar toolbar = findViewById(R.id.web_view_toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}


//


	}

	//设置回退
	//覆盖Activity的onKeyDown(int keyCode,KeyEvent event)方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (webView.canGoBack()){
				webView.goBack();//goBack()表示返回WebView的上一个界面
				return true;
			}else
				finish();
		}
		return false;
	}

}
