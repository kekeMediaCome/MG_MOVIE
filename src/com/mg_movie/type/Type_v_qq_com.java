package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_v_qq_com implements Parcelable {

	private int video_id;
	private String video_name;
	private String video_urlstite;
	private String video_url;
	private String video_img;
	private String video_source;
	private String video_mark;

	@Override
	public int describeContents() {
		return 0;
	}

	public Type_v_qq_com() {
	}

	public Type_v_qq_com(Parcel in) {
		video_id = in.readInt();
		video_name = in.readString();
		video_urlstite = in.readString();
		video_url = in.readString();
		video_img = in.readString();
		video_source = in.readString();
		video_mark = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(video_id);
		dest.writeString(video_name);
		dest.writeString(video_urlstite);
		dest.writeString(video_url);
		dest.writeString(video_img);
		dest.writeString(video_source);
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

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getVideo_name() {
		return video_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}

	public String getVideo_urlstite() {
		return video_urlstite;
	}

	public void setVideo_urlstite(String video_urlstite) {
		this.video_urlstite = video_urlstite;
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

	public String getVideo_source() {
		return video_source;
	}

	public void setVideo_source(String video_source) {
		this.video_source = video_source;
	}

	public String getVideo_mark() {
		return video_mark;
	}

	public void setVideo_mark(String video_mark) {
		this.video_mark = video_mark;
	}

}
