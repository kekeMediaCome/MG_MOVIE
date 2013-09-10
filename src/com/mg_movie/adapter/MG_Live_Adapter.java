package com.mg_movie.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mg_movie.R;
import com.mg_movie.type.Type_live_togic_1;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_Live_Adapter extends BaseAdapter {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	public List<Type_live_togic_1> lists;
	private LayoutInflater inflater;
	DisplayImageOptions options;
	public Context context;

	public MG_Live_Adapter(Context context) {
		inflater = (LayoutInflater) context.getSystemService("layout_inflater");
		this.context = context;
	}

	public void setListItems(List<Type_live_togic_1> lists) {
		this.lists = lists;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (lists != null) {
			return lists.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
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
		Type_live_togic_1 type = lists.get(position);
		viewHolder.title.setText(type.getTitle());
		imageLoader.displayImage(type.getIcon(), viewHolder.img, options,
				animateFirstListener);
		return convertView;
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {
		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	class ViewHolder {
		ImageView img;
		TextView title;
	}

}
