package com.mg_movie.firstloader;

import java.util.ArrayList;

import com.mg_movie.KSetting;
import com.mg_movie.R;
import com.mg_movie.activity.MG_MOVIE;
import com.mg_movie.parser.Parser_v_qq_com;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MG_FirstLoad extends FragmentActivity {
	private ViewPager mPager;
	private PageIndicator mIndicator;
	private MG_FirstLoadAdapter mAdapter;
	private int endPager = 3;
	private boolean isFinish = false;
	private ArrayList<String> listPages = null;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle buildBundle) {
		super.onCreate(buildBundle);
		setContentView(R.layout.mg_firstload);
		progressDialog = new ProgressDialog(MG_FirstLoad.this);
		progressDialog.setMessage(getResources().getString(R.string.video_parse_loading));
		progressDialog.setCancelable(false);
		mAdapter = new MG_FirstLoadAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.firstload_pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (CirclePageIndicator) findViewById(R.id.firstlaod_indicator);
		mIndicator.setViewPager(mPager);
		// We set this on the indicator, NOT the pager
		mIndicator
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						
					}

					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
						if (position == endPager && positionOffset == 0) {
							if (!isFinish) {
								isFinish = true;
								new InitData().execute();
							}
						}
					}
					@Override
					public void onPageScrollStateChanged(int state) {
					}
				});
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
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Intent intent = new Intent(MG_FirstLoad.this, MG_MOVIE.class);
			intent.putExtra("pages", listPages);
			startActivity(intent);
			progressDialog.cancel();
			finish();
		}
	}
}
