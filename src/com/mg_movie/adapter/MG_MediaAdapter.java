package com.mg_movie.adapter;

import java.util.List;

import com.mg_movie.R;
import com.mg_movie.type.Type_v_qq_com;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_MediaAdapter extends BaseAdapter {

	private List<Type_v_qq_com> listItems;
	private LayoutInflater inflater;
	DisplayImageOptions options;
	ImageLoader imageLoader;

	public MG_MediaAdapter(Context context, ImageLoader imageLoader) {
		inflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
		this.imageLoader = imageLoader;
	}

	public void setListItem(List<Type_v_qq_com> list) {
		listItems = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (listItems != null) {
			return listItems.size();
		} else {
			return 0;
		}

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
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.kk_movie_item, null);
			viewHolder.video_img = (ImageView) convertView
					.findViewById(R.id.video_img);
			viewHolder.video_mark = (TextView) convertView
					.findViewById(R.id.video_mask);
			viewHolder.video_title = (TextView) convertView
					.findViewById(R.id.video_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Type_v_qq_com movieType = listItems.get(position);
		viewHolder.video_mark.setText(movieType.getVideo_mark());
		viewHolder.video_title.setText(movieType.getVideo_title());
		imageLoader.displayImage(movieType.getVideo_img(),
				viewHolder.video_img, options);
		return convertView;
	}

	class ViewHolder {
		private ImageView video_img;
		private TextView video_mark;
		private TextView video_title;
	}

}
