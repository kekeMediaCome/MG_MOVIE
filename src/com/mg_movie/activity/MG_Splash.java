package com.mg_movie.activity;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.smart.SmartBannerManager;

import com.mg_movie.AppLog;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.firstloader.MG_FirstLoad;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MG_Splash extends MG_BaseActivity implements AnimationListener {

	private Animation alphaAnimation = null;
	private ImageView SplashLogo = null;
	public static MG_Splash instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MG_Exit.getInstance().addActivity(this);
		instance = this;
		if (!isFirstIn()) {
			setContentView(R.layout.mg_splash);
			AppLog.enableLogging(true);
			loadAnimation();
			SplashLogo = (ImageView) findViewById(R.id.splash_img);
			SplashLogo.startAnimation(alphaAnimation);
		} else {
			Intent intent = new Intent();
			intent.setClass(MG_Splash.this, MG_FirstLoad.class);
			startActivity(intent);
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 初始化接口，应用启动的时候调用
				// 参数：appId, appSecret, 调试模式
				AdManager.getInstance(instance).init("5e3ae60c9a9aa945",
						"3cbd8f0c54c2dc5b", false);
				// 如果使用积分广告，请务必调用积分广告的初始化接口:
				OffersManager.getInstance(instance).onAppLaunch();
				SmartBannerManager.init(instance);
				 // 插屏广告预加载
				// SpotManager.getInstance(instance).loadSpotAds();
			}
		}).start();
	}

	/**
	 * 加载动画特效
	 */
	private void loadAnimation() {
		alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_in);
		alphaAnimation.setAnimationListener(this);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Intent intent = new Intent();
		intent.setClass(MG_Splash.this, MG_HOME.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

}
