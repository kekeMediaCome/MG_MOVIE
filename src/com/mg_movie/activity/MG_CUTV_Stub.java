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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.mg_movie.KSetting;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_Live_Cutv_StubAdapter;
import com.mg_movie.dom.Parser_Live_CutvSubDom;
import com.mg_movie.imageloader.AbsListViewBaseActivity;
import com.mg_movie.player.JieLiveVideoPlayer;
import com.mg_movie.type.Type_CutvLive;
import com.mg_movie.type.Type_CutvLiveSub;

public class MG_CUTV_Stub extends AbsListViewBaseActivity implements
		OnItemClickListener, OnClickListener {

	public MG_Live_Cutv_StubAdapter adapter;
	public ProgressDialog progressDialog;
	public List<Type_CutvLiveSub> lists;
	public Type_CutvLive cutvLive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_live_togic_1);
		MG_Exit.getInstance().addActivity(this);
		Bundle bundle = getIntent().getExtras();
		cutvLive = (Type_CutvLive) bundle.getParcelable("cutvlive");
		listView = (ListView) findViewById(R.id.listview);
		adapter = new MG_Live_Cutv_StubAdapter(this);
		((ListView) listView).setAdapter(adapter);
		listView.setOnItemClickListener(this);
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText(cutvLive.getTv_name());
		progressDialog = new ProgressDialog(MG_CUTV_Stub.this);
		progressDialog.setMessage("加载中...");
		new InitData().execute();
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				String urlString  = KSetting.cutv_sub_url + cutvLive.getTv_id();
				lists = Parser_Live_CutvSubDom.parseXml(urlString);
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
		Type_CutvLiveSub cutvLiveSub = lists.get(position);
		StartMedia(cutvLiveSub.getMobile_url(), cutvLiveSub.getChannel_name());
	}

	public void StartMedia(String url, String title) {
		Intent intent = new Intent();
		intent.putExtra("path", url);
		intent.putExtra("title", title);
		intent.setClass(MG_CUTV_Stub.this, JieLiveVideoPlayer.class);
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
