package com.mg_movie.adapter;

import java.util.List;

import com.mg_movie.R;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_CustomAdapter extends BaseExpandableListAdapter {

	private List<String> groupArray;
	private List<List<String[]>> childArray;
	private LayoutInflater mLayoutInflater;
	Activity activity;

	public MG_CustomAdapter(Activity activity, List<String> groupArray,
			List<List<String[]>> childArray) {
		this.activity = activity;
		this.groupArray = groupArray;
		this.childArray = childArray;
		mLayoutInflater = LayoutInflater.from(activity);
	}

	@Override
	public int getGroupCount() {
		if (groupArray != null) {
			return groupArray.size();
		} else {
			return 0;
		}
	}

	public void setLists(List<String> groupArray,
			List<List<String[]>> childArray) {
		this.groupArray = groupArray;
		this.childArray = childArray;
		notifyDataSetChanged();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childArray.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupArray.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childArray.get(groupPosition).get(childPosition)[0];
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(
					R.layout.mg_home_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.logo = (ImageView)convertView.findViewById(R.id.item_img_run);
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.item_title);
			viewHolder.name.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			viewHolder.name.setPadding(60, 0, 0, 0);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.logo.setVisibility(View.INVISIBLE);
		viewHolder.name.setText(groupArray.get(groupPosition));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(
					R.layout.mg_home_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.item_title);
			viewHolder.name.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			viewHolder.name.setPadding(60, 0, 0, 0);
			viewHolder.logo = (ImageView)convertView.findViewById(R.id.item_img_icon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(childArray.get(groupPosition)
				.get(childPosition)[0]);
		viewHolder.logo.setBackgroundResource(R.drawable.ic_userdef);
		return convertView;
	}

	class ViewHolder {
		TextView name;
		ImageView logo;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
