package com.mg_movie.activity;

import java.util.ArrayList;

import com.mg_movie.AppLog;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_v_qq_com;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MG_Splash extends MG_BaseActivity implements AnimationListener{

	 private Animation alphaAnimation = null;
	 private boolean isFirst = true;
	 private ArrayList<String> listPages = null;
	 private ImageView SplashLogo = null;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_splash);
		AppLog.enableLogging(true);
		MG_Exit.getInstance().addActivity(this);
		new InitData().execute();
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
	
	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
			
		}
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				Parser_v_qq_com parse = new Parser_v_qq_com();
				listPages = parse.ParsePage(KSetting.v_q_com_url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (isFirst) {
				isFirst = false;
			}
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (!isFirst) {
			Intent intent = new Intent();
			intent.setClass(MG_Splash.this, MG_MOVIE.class);
			intent.putExtra("pages", listPages);
			startActivity(intent);
			finish();
		}else {
			SplashLogo.startAnimation(alphaAnimation);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
	}

}
