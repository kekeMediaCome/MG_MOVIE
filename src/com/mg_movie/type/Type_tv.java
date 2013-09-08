package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_tv implements Parcelable {

	private int tv_id;
	private String tv_name;
	private String tv_urlstite;
	private String tv_url;
	private String tv_img;
	private String tv_source;
	private String tv_mark_sd;
	private String tv_mark_txt;

	public Type_tv() {

	}

	private Type_tv(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		tv_id = in.readInt();
		tv_name = in.readString();
		tv_urlstite = in.readString();
		tv_url = in.readString();
		tv_img = in.readString();
		tv_source = in.readString();
		tv_mark_sd = in.readString();
		tv_mark_txt = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(tv_id);
		dest.writeString(tv_name);
		dest.writeString(tv_urlstite);
		dest.writeString(tv_url);
		dest.writeString(tv_img);
		dest.writeString(tv_source);
		dest.writeString(tv_mark_sd);
		dest.writeString(tv_mark_txt);
	}

	public static final Creator<Type_tv> CREATOR = new Creator<Type_tv>() {

		@Override
		public Type_tv createFromParcel(Parcel source) {
			return new Type_tv(source);
		}

		@Override
		public Type_tv[] newArray(int size) {
			return new Type_tv[size];
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

	public String getTv_urlstite() {
		return tv_urlstite;
	}

	public void setTv_urlstite(String tv_urlstite) {
		this.tv_urlstite = tv_urlstite;
	}

	public String getTv_url() {
		return tv_url;
	}

	public void setTv_url(String tv_url) {
		this.tv_url = tv_url;
	}

	public String getTv_img() {
		return tv_img;
	}

	public void setTv_img(String tv_img) {
		this.tv_img = tv_img;
	}

	public String getTv_source() {
		return tv_source;
	}

	public void setTv_source(String tv_source) {
		this.tv_source = tv_source;
	}

	public String getTv_mark_sd() {
		return tv_mark_sd;
	}

	public void setTv_mark_sd(String tv_mark_sd) {
		this.tv_mark_sd = tv_mark_sd;
	}

	public String getTv_mark_txt() {
		return tv_mark_txt;
	}

	public void setTv_mark_txt(String tv_mark_txt) {
		this.tv_mark_txt = tv_mark_txt;
	}

}
