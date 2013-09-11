package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_CutvLive implements Parcelable {

	private int tv_id;
	private String tv_name;
	private String tv_thumb_img;
	private String tv_logo;

	public Type_CutvLive() {

	}

	private Type_CutvLive(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		tv_id = in.readInt();
		tv_name = in.readString();
		tv_thumb_img = in.readString();
		tv_logo = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(tv_id);
		dest.writeString(tv_name);
		dest.writeString(tv_thumb_img);
		dest.writeString(tv_logo);
	}

	public static final Creator<Type_CutvLive> CREATOR = new Creator<Type_CutvLive>() {

		@Override
		public Type_CutvLive createFromParcel(Parcel source) {
			return new Type_CutvLive(source);
		}

		@Override
		public Type_CutvLive[] newArray(int size) {
			return new Type_CutvLive[size];
		}
	};

	public int getTv_id() {
		return tv_id;
	}

	public void setTv_id(int tv_id) {
		this.tv_id = tv_id;
	}

	public String getTv_name() {
		return tv_name;
	}

	public void setTv_name(String tv_name) {
		this.tv_name = tv_name;
	}

	public String getTv_thumb_img() {
		return tv_thumb_img;
	}

	public void setTv_thumb_img(String tv_thumb_img) {
		this.tv_thumb_img = tv_thumb_img;
	}

	public String getTv_logo() {
		return tv_logo;
	}

	public void setTv_logo(String tv_logo) {
		this.tv_logo = tv_logo;
	}
}
