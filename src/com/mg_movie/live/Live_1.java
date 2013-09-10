package com.mg_movie.live;

import com.mg_movie.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Live_1 extends GlobalFragment implements OnClickListener{
	private static final String KEY_CONTENT = "Live_1";

	private static Live_1 instance = null;
	private View live_View;
	public static Live_1 getFragment(){
		if (instance == null) {
			instance = new Live_1();
		}
		return instance;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		live_View = inflater.inflate(R.layout.mg_live_togic_1, container, false);
		return live_View;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		live_View.findViewById(R.id.home_top_menudraw).setOnClickListener(this);
		TextView home_top_name = (TextView) live_View.findViewById(R.id.home_top_name);
		home_top_name.setText("分类直播");
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_top_menudraw:
			getActivity().finish();
			break;
		}
	}
}
