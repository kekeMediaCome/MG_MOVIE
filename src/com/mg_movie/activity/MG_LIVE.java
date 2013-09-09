package com.mg_movie.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.mg_movie.R;
import com.mg_movie.imageloader.AbsListViewBaseActivity;

public class MG_LIVE extends AbsListViewBaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_live);
		listView = (ListView) findViewById(R.id.listview);
	}
	
}
