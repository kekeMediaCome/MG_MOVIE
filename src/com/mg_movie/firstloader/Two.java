package com.mg_movie.firstloader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.mg_movie.R;

public class Two extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		return super.onCreateView(inflater, container, savedInstanceState);
		ImageView imageView = new ImageView(getActivity());
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setBackgroundResource(R.drawable.player_user_manual02);
		return imageView;
	}
}
