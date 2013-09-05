package com.mg_movie.adapter;

import java.util.List;

import com.mg_movie.R;
import com.mg_movie.type.Type_home_content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_HomeAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Type_home_content> listItems;

	public MG_HomeAdapter(LayoutInflater inflater,
			List<Type_home_content> listItems) {
		this.inflater = inflater;
		this.listItems = listItems;
	}

	@Override
	public int getCount() {
		if (listItems != null && listItems.size() != 0) {
			return listItems.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.mg_home_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView) convertView
					.findViewById(R.id.item_img_icon);
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.item_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Type_home_content type = listItems.get(position);
		viewHolder.img.setBackgroundResource(type.getImg_id());
		viewHolder.title.setText(type.getTitle_id());
		return convertView;
	}

	class ViewHolder {
		ImageView img;
		TextView title;
	}
}
