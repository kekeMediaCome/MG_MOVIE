package com.mg_movie.activity;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.AppLog;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_v_qq_com;
import com.mg_movie.type.Type_v_qq_com;
import com.mg_movie.utils.DBUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MG_Splash extends MG_BaseActivity implements AnimationListener{

	 private Animation alphaAnimation = null;
	 private boolean isFirst = true;
	 private ImageView SplashLogo = null;
	 public static MG_Splash instance;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_splash);
		instance = this;
		AppLog.enableLogging(true);
		MG_Exit.getInstance().addActivity(this);
		loadAnimation();	
		SplashLogo = (ImageView)findViewById(R.id.splash_img);
		SplashLogo.startAnimation(alphaAnimation);
	}
	/**
	 * 加载动画特效
	 */
	private void loadAnimation(){
		alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_in);
		alphaAnimation.setAnimationListener(this);
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
//		if (!isFirst) {
//			Intent intent = new Intent();
//			intent.setClass(MG_Splash.this, MG_MOVIE.class);
//			intent.putExtra("pages", listPages);
//			startActivity(intent);
//			finish();
//		}else {
//			SplashLogo.startAnimation(alphaAnimation);
//		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
	}

}
