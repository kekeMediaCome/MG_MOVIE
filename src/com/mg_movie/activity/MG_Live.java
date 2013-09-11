package com.mg_movie.activity;

import com.mg_movie.MG_Exit;
import com.mg_movie.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_Live extends MG_BaseActivity implements OnClickListener{

	public TextView[] live_TV;
	public int[] live_TV_id = {R.id.live_1, R.id.live_2, R.id.live_3, R.id.live_4, R.id.live_5, R.id.live_6};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_live);
		MG_Exit.getInstance().addActivity(this);
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("Live");
		live_TV = new TextView[6];
		for (int i = 0; i < 6; i++) {
			live_TV[i] = (TextView)findViewById(live_TV_id[i]);
			live_TV[i].setOnClickListener(this);
		}
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.live_1:
			intent.setClass(MG_Live.this, MG_Live_Togic.class);
			startActivity(intent);
			break;
		case R.id.live_2:
			intent.setClass(MG_Live.this, MG_CUTV.class);
			startActivity(intent);
			break;
		case R.id.live_3:
			intent.setClass(MG_Live.this, MG_CNTV.class);
			startActivity(intent);
			break;
		case R.id.live_4:
			intent.setClass(MG_Live.this, MG_Custom.class);
			startActivity(intent);
			break;
		case R.id.home_top_menudraw:
			finish();
			break;
		}
	}
}
