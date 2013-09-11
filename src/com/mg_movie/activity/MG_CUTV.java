package com.mg_movie.activity;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mg_movie.KSetting;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_Live_CutvAdapter;
import com.mg_movie.dom.Parser_Live_CutvDom;
import com.mg_movie.imageloader.AbsListViewBaseActivity;
import com.mg_movie.type.Type_CutvLive;

public class MG_CUTV extends AbsListViewBaseActivity implements
		OnItemClickListener, OnClickListener {

	public MG_Live_CutvAdapter adapter;
	public ProgressDialog progressDialog;
	public List<Type_CutvLive> lists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_live_togic_1);
		MG_Exit.getInstance().addActivity(this);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new MG_Live_CutvAdapter(this);
		((ListView) listView).setAdapter(adapter);
		listView.setOnItemClickListener(this);
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("CUTV");
		progressDialog = new ProgressDialog(MG_CUTV.this);
		progressDialog.setMessage("加载中...");
		new InitData().execute();
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				lists = Parser_Live_CutvDom.parseXml(KSetting.cutvurl);
				adapter.setListItems(lists);
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
			adapter.notifyDataSetChanged();
			progressDialog.cancel();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Type_CutvLive cutvLive = lists.get(position);
		Bundle bundle = new Bundle();
		bundle.putParcelable("cutvlive", cutvLive);
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(MG_CUTV.this, MG_CUTV_Stub.class);
		startActivity(intent);
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
