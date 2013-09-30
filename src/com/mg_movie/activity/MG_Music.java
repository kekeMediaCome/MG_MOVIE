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
import com.mg_movie.adapter.MG_MusicAdapter;
import com.mg_movie.imageloader.AbsListViewBaseActivity;
import com.mg_movie.player.JieLiveVideoPlayer;
import com.mg_movie.type.Type_YinYueTai;
import com.mg_movie.utils.DBUtils;
import com.yixia.vparser.VParser;
import com.yixia.vparser.model.Video;

public class MG_Music extends AbsListViewBaseActivity implements OnClickListener{
	private MG_MusicAdapter adapter;
	private MG_Music instance;
	private PullToRefreshListView MusicRefresh;
	private DBUtils dbUtils;
	public List<Type_YinYueTai> list_main = new ArrayList<Type_YinYueTai>();
	private int index = 0;
	private int count = 0;
	private int end_music = 0;
	private VParser vParser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_music);
		instance = this;
		MG_Exit.getInstance().addActivity(instance);
		dbUtils = new DBUtils(instance);
		count = dbUtils.getMusicCount();
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(instance);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("Music");
		MusicRefresh = (PullToRefreshListView) findViewById(R.id.music_listview);
		MusicRefresh.setMode(Mode.PULL_FROM_END);
		MusicRefresh.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				if (end_music < count) {
					new InitData().execute();
				}
			}
		});
		adapter = new MG_MusicAdapter(instance, imageLoader);
		listView = MusicRefresh.getRefreshableView();
		((ListView)listView).setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Type_YinYueTai yinYueTai = list_main.get(position - 1);
				Video video = vParser.parse(yinYueTai.getMusic_url());
				if (video != null) {
					StartMedia(video.videoUri, yinYueTai.getMusic_title());
				}
			}
		});
		new InitData().execute();
		vParser = new VParser(this);
	}

	public void StartMedia(String url, String title) {
		Intent intent = new Intent();
		intent.putExtra("path", url);
		intent.putExtra("title", title);
		intent.setClass(MG_Music.this, JieLiveVideoPlayer.class);
		startActivity(intent);
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				if (end_music == 0) {
					list_main = dbUtils.getAllMusics();
				}
				index = end_music;
				end_music += 100;
				if (end_music > count) {
					end_music = count;
				}
				for (int i = index; i < end_music; i++) {
					adapter.addItemMusic(list_main.get(i));
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
			super.onPostExecute(result);
			MusicRefresh.onRefreshComplete();
			adapter.notifyDataSetChanged();
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
