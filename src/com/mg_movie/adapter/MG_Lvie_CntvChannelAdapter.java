package com.mg_movie.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mg_movie.R;
import com.mg_movie.type.Type_Cntv_Channel;
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

public class MG_Lvie_CntvChannelAdapter extends BaseAdapter {
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private LayoutInflater inflater;
	private List<Type_Cntv_Channel> mTvChannelList;
	DisplayImageOptions options;
	ImageLoader imageLoader;

	public MG_Lvie_CntvChannelAdapter(Context mContext, ImageLoader imageLoader) {
		super();
		inflater = LayoutInflater.from(mContext);
		this.imageLoader = imageLoader;
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
	}

	@Override
	public int getCount() {
		if (mTvChannelList != null) {
			return mTvChannelList.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return mTvChannelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView;
		if (convertView == null) {
			holderView = new HolderView();
			convertView = inflater.inflate(R.layout.tv_list_item, null);
			holderView.logo = (ImageView) convertView
					.findViewById(R.id.tv_item_logo);
			holderView.tv_title = (TextView) convertView
					.findViewById(R.id.tv_item_title);
			holderView.content = (TextView) convertView
					.findViewById(R.id.tv_item_mark_txt);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}
		final Type_Cntv_Channel localTvChannel = (Type_Cntv_Channel) mTvChannelList
				.get(position);
		holderView.tv_title.setText(localTvChannel.getChannelname());
		holderView.content.setText(localTvChannel.getProgram_name());
		imageLoader.displayImage(localTvChannel.getChannellogo(),
				holderView.logo, options, animateFirstListener);
		return convertView;
	}

	class HolderView {
		ImageView logo;
		TextView tv_title;
		TextView content;
	}

	public List<Type_Cntv_Channel> getmTvChanenList() {
		return mTvChannelList;
	}

	public void notifyDataSetChanged(List<Type_Cntv_Channel> paramList) {
		if (paramList == null)
			return;
		this.mTvChannelList = paramList;
		notifyDataSetChanged();
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
}
