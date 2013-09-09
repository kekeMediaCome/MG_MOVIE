package com.mg_movie.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mg_movie.R;
import com.mg_movie.type.Type_tv;
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

public class MG_TV_Adapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	DisplayImageOptions options;
	ImageLoader imageLoader;
	public Context context;
	public List<Type_tv> listItems;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public MG_TV_Adapter(Context context, ImageLoader imageLoader) {
		mLayoutInflater = (LayoutInflater) context
				.getSystemService("layout_inflater");
		this.imageLoader = imageLoader;
		this.context = context;
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

	public void addItemTv(Type_tv tv) {
		if (listItems == null) {
			listItems = new ArrayList<Type_tv>();
		}
		listItems.add(tv);
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
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder viewHolder;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = mLayoutInflater.inflate(R.layout.mg_tv_item, null);
			viewHolder.logo = (ImageView) view.findViewById(R.id.tv_item_logo);
			viewHolder.mark_txt = (TextView) view
					.findViewById(R.id.tv_item_mark_txt);
			viewHolder.title = (TextView) view.findViewById(R.id.tv_item_title);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		Type_tv tv = listItems.get(position);
		viewHolder.title.setText(tv.getTv_name());
		viewHolder.mark_txt.setText(tv.getTv_mark_txt());
		imageLoader.displayImage(tv.getTv_img(), viewHolder.logo, options,
				animateFirstListener);
		return view;
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
		public TextView title;
		public TextView mark_txt;
	}

}
