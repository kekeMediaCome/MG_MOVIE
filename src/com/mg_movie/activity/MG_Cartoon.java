package com.mg_movie.activity;

import java.util.ArrayList;
import java.util.List;

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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_CT_Adapter;
import com.mg_movie.imageloader.AbsListViewBaseActivity;
import com.mg_movie.player.JieLiveVideoPlayer;
import com.mg_movie.type.Type_cartoon;
import com.mg_movie.utils.DBUtils;
import com.yixia.vparser.VParser;
import com.yixia.vparser.model.Video;

public class MG_Cartoon extends AbsListViewBaseActivity implements
		OnClickListener {

	public MG_Cartoon instance;
	public MG_CT_Adapter adapter;
	private PullToRefreshListView ctRefresh;
	DBUtils dbUtils;
	public List<Type_cartoon> list_main = new ArrayList<Type_cartoon>();
	public int index = 0;
	public int count = 0;
	VParser vParser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_tv);
		instance = this;
		MG_Exit.getInstance().addActivity(this);
		dbUtils = new DBUtils(this);
		ImageView home_top_img = (ImageView)findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("Cartoon");
		ctRefresh = (PullToRefreshListView) findViewById(R.id.listview);
		ctRefresh.setMode(Mode.PULL_FROM_END);
		ctRefresh.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				index++;
				new InitData().execute();
			}
		});
		listView = ctRefresh.getRefreshableView();
		adapter = new MG_CT_Adapter(this, imageLoader);
		((ListView) listView).setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Type_cartoon ct = list_main.get(position - 1);
				Video video = vParser.parse(ct.getCt_url());
				if (video != null) {
					Intent intent = new Intent();
					intent.putExtra("path", video.videoUri);
					intent.putExtra("title", video.title);
					intent.setClass(MG_Cartoon.this, JieLiveVideoPlayer.class);
					startActivity(intent);
				}
			}
		});
		new InitData().execute();
		vParser = new VParser(this);
	}

	public void StartMedia(String url, String title){
		
	}
	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
			
		}
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				if (index == 0) {
					list_main = dbUtils.getAllCTs();
				}
				int temp_pre = index * 100;
				int temp_end = (index + 1) * 100;
				for (int i = temp_pre; i < temp_end; i++) {
					adapter.addItemCartoon(list_main.get(i));
				}
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
			adapter.notifyDataSetChanged();
			ctRefresh.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

	@Override
	protected void onDestroy() {
		dbUtils.close();
		super.onDestroy();
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
