package com.mg_movie.activity;

import java.util.ArrayList;

import com.mg_movie.R;
import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_v_qq_com;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MG_Splash extends Activity {

	 private Animation alphaAnimation = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_splash);
		AppLog.enableLogging(true);
		alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_in);
		alphaAnimation.start();
		new InitData().execute();
	}
	
	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}
		ArrayList<String> listPages = null;
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			Parser_v_qq_com parse = new Parser_v_qq_com();
			listPages = parse.ParsePage(KSetting.v_q_com_url);
			try {
			} catch (Exception e) {
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
			Intent intent = new Intent();
			intent.setClass(MG_Splash.this, KK_MOVIE.class);
			intent.putExtra("pages", listPages);
			startActivity(intent);
			finish();
		}
	}

}
