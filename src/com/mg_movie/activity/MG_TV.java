package com.mg_movie.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_TV_Adapter;
import com.mg_movie.imageloader.AbsListViewBaseActivity;
import com.mg_movie.player.JieLiveVideoPlayer;
import com.mg_movie.type.Type_tv;
import com.mg_movie.utils.DBUtils;
import com.yixia.vparser.VParser;
import com.yixia.vparser.model.Video;

public class MG_TV extends AbsListViewBaseActivity implements OnClickListener {
	public MG_TV instance;
	public MG_TV_Adapter adapter;
	private PullToRefreshListView tvRefresh;
	DBUtils dbUtils;
	public List<Type_tv> list_main = new ArrayList<Type_tv>();
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
		findViewById(R.id.home_top_menudraw).setOnClickListener(this);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("TV");
		tvRefresh = (PullToRefreshListView) findViewById(R.id.listview);
		tvRefresh.setMode(Mode.PULL_FROM_END);
		tvRefresh.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				index++;
				new InitData().execute();
			}
		});
		listView = tvRefresh.getRefreshableView();
		adapter = new MG_TV_Adapter(this, imageLoader);
		((ListView) listView).setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Type_tv tv = list_main.get(position - 1);
				Video video  = vParser.parse(tv.getTv_url());
				if (video != null) {
					Intent intent = new Intent();
					intent.putExtra("path", video.videoUri);
					intent.putExtra("title", video.title);
					intent.setClass(MG_TV.this, JieLiveVideoPlayer.class);
					startActivity(intent);
				}
			}
		});
		new InitData().execute();
		vParser = new VParser(this);
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				if (index == 0) {
					list_main = dbUtils.getAllTVs();
				}
				int temp_pre = index * 100;
				int temp_end = (index + 1) * 100;
				for (int i = temp_pre; i < temp_end; i++) {
					adapter.addItemTv(list_main.get(i));
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
			tvRefresh.onRefreshComplete();
			super.onPostExecute(result);
		}
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
