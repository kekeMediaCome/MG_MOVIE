package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_v_qq_com implements Parcelable {

	private String video_title;
	private String video_url;
	private String video_img;
	private String video_mark;

	@Override
	public int describeContents() {
		return 0;
	}

	public Type_v_qq_com(){
	}
	
	public Type_v_qq_com(Parcel in){
		video_title = in.readString();
		video_url = in.readString();
		video_img = in.readString();
		video_mark = in.readString();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(video_title);
		dest.writeString(video_url);
		dest.writeString(video_img);
		dest.writeString(video_mark);
	}

	public static final Creator<Type_v_qq_com> CREATOR = new Creator<Type_v_qq_com>() {

		@Override
		public Type_v_qq_com createFromParcel(Parcel source) {
			return new Type_v_qq_com(source);
		}

		@Override
		public Type_v_qq_com[] newArray(int size) {
			return new Type_v_qq_com[size];
		}
	};
	public String getVideo_title() {
		return video_title;
	}

	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getVideo_img() {
		return video_img;
	}

	public void setVideo_img(String video_img) {
		this.video_img = video_img;
	}

	public String getVideo_mark() {
		return video_mark;
	}

	public void setVideo_mark(String video_mark) {
		this.video_mark = video_mark;
	}

}
