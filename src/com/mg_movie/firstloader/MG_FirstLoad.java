package com.mg_movie.firstloader;

import java.util.ArrayList;

import com.mg_movie.KSetting;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.activity.MG_HOME;
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
		MG_Exit.getInstance().addActivity(this);
		progressDialog = new ProgressDialog(MG_FirstLoad.this);
		progressDialog.setMessage(getResources().getString(
				R.string.video_parse_loading));
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
								Intent intent = new Intent(MG_FirstLoad.this,
										MG_HOME.class);
								startActivity(intent);
								finish();
							}
						}
					}

					@Override
					public void onPageScrollStateChanged(int state) {
					}
				});
	}
}
