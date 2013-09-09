package com.mg_movie.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mg_movie.R;
import com.mg_movie.imageloader.AbsListViewBaseActivity;

public class MG_LIVE extends AbsListViewBaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_live);
		listView = (ListView) findViewById(R.id.listview);
		findViewById(R.id.home_top_menudraw).setOnClickListener(this);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("Live");
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
