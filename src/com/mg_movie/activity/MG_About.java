package com.mg_movie.activity;

import com.mg_movie.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_About extends Activity implements OnClickListener {

	/* 需要显示的文本信息 */
	private WebView mWebView;
	private String mMsgPath;
	private String mMsgName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_about);

		mWebView = (WebView) findViewById(R.id.webview);
		/* 设置监听 */

		Intent intent = getIntent();
		mMsgPath = intent.getStringExtra("msgPath");
		mMsgName = intent.getStringExtra("msgName");
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText(mMsgName);
		readHtmlFormAssets();
	}

	// 利用webview来显示帮助的文本信息
	private void readHtmlFormAssets() {
		WebSettings webSettings = mWebView.getSettings();

		webSettings.setLoadWithOverviewMode(true);
		// WebView双击变大，再双击后变小，当手动放大后，双击可以恢复到原始大小
		// webSettings.setUseWideViewPort(true);
		// 设置WebView可触摸放大缩小：
		// webSettings.setBuiltInZoomControls(true);
		// WebView 背景透明效果
		mWebView.setBackgroundColor(Color.TRANSPARENT);
		mWebView.loadUrl("file:///android_asset/html/" + mMsgPath);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_top_menudraw:
			finish();
			break;
		}
	}
}
