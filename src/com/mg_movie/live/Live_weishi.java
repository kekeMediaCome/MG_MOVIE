package com.mg_movie.live;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.mg_movie.R;
import com.mg_movie.adapter.MG_Live_Adapter;
import com.mg_movie.player.JieLiveVideoPlayer;
import com.mg_movie.type.Type_live_togic_1;

public class Live_weishi extends GlobalFragment implements OnClickListener,
		OnItemClickListener {
	private static Live_weishi instance = null;
	private View live_View;
	public ListView listView;
	public MG_Live_Adapter adapter;
	public ArrayList<Type_live_togic_1> list_weishi;

	public static Live_weishi getFragment() {
		if (instance == null) {
			instance = new Live_weishi();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		list_weishi = (ArrayList<Type_live_togic_1>) bundle
				.getSerializable("weishi");
		adapter = new MG_Live_Adapter(getActivity());
		adapter.setListItems(list_weishi);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		live_View = inflater
				.inflate(R.layout.mg_live_togic_1, container, false);
		return live_View;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		live_View.findViewById(R.id.home_top_menudraw).setOnClickListener(this);
		TextView home_top_name = (TextView) live_View
				.findViewById(R.id.home_top_name);
		home_top_name.setText("分类直播");
		listView = (ListView) live_View.findViewById(R.id.listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_top_menudraw:
			getActivity().finish();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), JieLiveVideoPlayer.class);
		Type_live_togic_1 live = list_weishi.get(position);
		intent.putExtra("path", live.getUrls().split(",")[0]);
		intent.putExtra("title", live.getTitle());
		startActivity(intent);
	}
}
