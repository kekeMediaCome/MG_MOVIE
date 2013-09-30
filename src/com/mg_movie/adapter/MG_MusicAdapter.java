package com.mg_movie.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mg_movie.AppLog;
import com.mg_movie.R;
import com.mg_movie.type.Type_YinYueTai;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MG_MusicAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private List<Type_YinYueTai> listItems;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public MG_MusicAdapter(Context context, ImageLoader imageLoader) {
		mLayoutInflater = (LayoutInflater) context
				.getSystemService("layout_inflater");
		this.imageLoader = imageLoader;
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
	}

	@Override
	public int getCount() {
		if (listItems != null) {
			return listItems.size();
		} else
			return 0;
	}

	public void addItemMusic(Type_YinYueTai yinYueTai) {
		if (listItems == null) {
			listItems = new ArrayList<Type_YinYueTai>();
		}
		listItems.add(yinYueTai);
		AppLog.e(yinYueTai.getMusic_title());
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
			convertView = mLayoutInflater.inflate(R.layout.music_list_item, null);
			viewHolder.logo = (ImageView) convertView.findViewById(R.id.tv_item_logo);
			viewHolder.shdIco = (TextView) convertView.findViewById(R.id.tv_item_hd);
			viewHolder.time = (TextView) convertView.findViewById(R.id.tv_item_time);
			viewHolder.player = (TextView) convertView.findViewById(R.id.tv_item_mark_txt);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_item_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Type_YinYueTai yinYueTai = listItems.get(position);
		viewHolder.shdIco.setText(yinYueTai.getMusic_shdIco());
		viewHolder.time.setText(yinYueTai.getMusic_time());
		viewHolder.title.setText(yinYueTai.getMusic_title());
		viewHolder.player.setText(yinYueTai.getMusic_player());
		imageLoader.displayImage(yinYueTai.getMusic_img(), viewHolder.logo,
				options, animateFirstListener);
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
		public ImageView logo;
		public TextView shdIco;
		public TextView time;
		public TextView title;
		public TextView player;
	}
}
