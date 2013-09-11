package com.mg_movie.adapter;

import com.mg_movie.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MG__Home_MenuDraw_Adapter extends BaseAdapter {

	private int[] img_id = {R.drawable.video_cover,R.drawable.ic_menu_preferences, R.drawable.e_icon_info};
	private String[] title = {"拍摄视频", "设置", "关于"};
	private LayoutInflater inflater;
	
	public MG__Home_MenuDraw_Adapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return title.length;
	}

	@Override
	public Object getItem(int position) {
		return title[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater
					.inflate(R.layout.mg_home_menudraw_item, null);
			viewHolder.logo = (ImageView) convertView
					.findViewById(R.id.item_img_icon);
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.item_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.logo.setBackgroundResource(img_id[position]);
		viewHolder.title.setText(title[position]);
		return convertView;
	}

	class ViewHolder {
		ImageView logo;
		TextView title;
	}
}
