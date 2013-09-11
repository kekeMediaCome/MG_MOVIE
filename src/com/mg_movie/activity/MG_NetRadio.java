package com.mg_movie.activity;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.R;
import com.mg_movie.adapter.RadioListType;
import com.mg_movie.adapter.RadioMenuAdapter;
import com.mg_movie.adapter.RadioType;
import com.mg_movie.parser.RadioChannelParse;
import com.mg_movie.player.JieLiveVideoPlayer;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MG_NetRadio extends MG_BaseActivity implements
		RadioMenuAdapter.MenuListener, OnClickListener {

	private static final String radioURL = "http://rushplayer.com/wapstream.aspx?v=1.54&t=1&g=8&app=1000";
	private static final String STATE_ACTIVE_POSITION = "org.stagex.danmaku.activity.RadioActivity.activePosition";
	private static final String STATE_CONTENT_TEXT = "org.stagex.danmaku.activity.RadioActivity.contentText";

	public List<RadioType> ParentChannel;
	public List<RadioListType> channlesList;
	public ListView parentRadioListView;
	public ListView childRadioListView;
	private MenuDrawer mMenuDrawer;
	private RadioMenuAdapter mAdapter, mChildAdapter;
	private int mActivePosition = -1;
	private String mContentText;
	public ProgressDialog progressDialog;
	public ConnectivityManager con;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);
		if (inState != null) {
			mActivePosition = inState.getInt(STATE_ACTIVE_POSITION);
			mContentText = inState.getString(STATE_CONTENT_TEXT);
		}
		con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_CONTENT);
		mMenuDrawer.setContentView(R.layout.radio_channel_content);
		mMenuDrawer.setAllowIndicatorAnimation(true);

		ImageView home_top_img = (ImageView)findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("Radio");

		parentRadioListView = new ListView(this);
		parentRadioListView.setCacheColorHint(00000000);
		mAdapter = new RadioMenuAdapter(this);
		mAdapter.setListener(this);
		mAdapter.setActivePosition(mActivePosition);
		parentRadioListView.setAdapter(mAdapter);
		parentRadioListView.setOnItemClickListener(mItemClickListener);

		// mMenuDrawer.setMenuSize(BaseActivity.dip2px(150));
		mMenuDrawer.setMenuView(parentRadioListView);

		childRadioListView = (ListView) findViewById(R.id.radio_channel_listview);
		childRadioListView.setOnItemClickListener(childClickListener);
		mChildAdapter = new RadioMenuAdapter(this);
		childRadioListView.setAdapter(mChildAdapter);
		childRadioListView.setCacheColorHint(00000000);
		channlesList = new ArrayList<RadioListType>();
		progressDialog = new ProgressDialog(MG_NetRadio.this);
		progressDialog.setMessage("解析中...");
		// progressDialog.setCancelable(false);

	}

	@Override
	protected void onResume() {
		checkNetwork();
		if (ParentChannel == null || ParentChannel.size() == 0) {
			new InitParentChannelData(radioURL).execute();
		}
		super.onResume();
	}

	public AdapterView.OnItemClickListener childClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			RadioType type = ChildCurrentChannel.get(position);
			StartMedia(type.getUrl(), type.getName());
		}
	};

	public void StartMedia(String url, String title) {
		Intent intent = new Intent();
		intent.putExtra("path", url);
		intent.putExtra("title", title);
		intent.setClass(MG_NetRadio.this, JieLiveVideoPlayer.class);
		startActivity(intent);
	}

	public AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			RadioType type = ParentChannel.get(position);
			String name = type.getName();
			boolean isHas = false;
			int size = channlesList.size();
			for (int i = 0; i < size; i++) {
				RadioListType listType = channlesList.get(i);
				if (name.equals(listType.getName())) {
					isHas = true;
					mChildAdapter.setListItems(listType.getList());
					break;
				}
			}
			if (!isHas) {
				checkNetwork();
				new InitChildChannelData(type).execute();
			}
			mActivePosition = position;
			mMenuDrawer.setActiveView(view, position);
		}
	};

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_ACTIVE_POSITION, mActivePosition);
		outState.putString(STATE_CONTENT_TEXT, mContentText);
	}

	@Override
	public void onActiveViewChanged(View v) {
		mMenuDrawer.setActiveView(v, mActivePosition);
	}

	public List<RadioType> ChildCurrentChannel;

	class InitChildChannelData extends AsyncTask<Void, Void, Void> {
		RadioType type;

		InitChildChannelData(RadioType type) {
			this.type = type;
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				ChildCurrentChannel = RadioChannelParse.parseRadioChild(type
						.getUrl());
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
			RadioListType listType = new RadioListType();
			listType.setName(type.getName());
			listType.setList(ChildCurrentChannel);
			channlesList.add(listType);
			mChildAdapter.setListItems(ChildCurrentChannel);
			if (!mMenuDrawer.isMenuVisible()) {
				mMenuDrawer.openMenu();
			}
			progressDialog.cancel();
		}
	}

	class InitParentChannelData extends AsyncTask<Void, Void, Void> {
		String url;

		InitParentChannelData(String url) {
			this.url = url;
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				ParentChannel = RadioChannelParse.parseRadioChannel(url);
				if (ParentChannel == null || ParentChannel.size() == 0) {
					return null;
				}
				ChildCurrentChannel = RadioChannelParse
						.parseRadioChild(ParentChannel.get(0).getUrl());
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
			if (ParentChannel != null && ParentChannel.size() > 0) {
				RadioListType listType = new RadioListType();
				listType.setName(ParentChannel.get(0).getName());
				listType.setList(ChildCurrentChannel);
				channlesList.add(listType);
				mAdapter.setListItems(ParentChannel);
				mChildAdapter.setListItems(ChildCurrentChannel);
				mMenuDrawer.openMenu();
				progressDialog.cancel();
			}
		}
	}

	public void showInfo(String info) {
		Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
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
