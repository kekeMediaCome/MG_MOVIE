package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_cartoon implements Parcelable {

	private int ct_id;
	private String ct_name;
	private String ct_urlstite;
	private String ct_url;
	private String ct_img;
	private String ct_source;
	private String ct_mark_sd;
	private String ct_mark_txt;

	public Type_cartoon() {

	}

	private Type_cartoon(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		ct_id = in.readInt();
		ct_name = in.readString();
		ct_urlstite = in.readString();
		ct_url = in.readString();
		ct_img = in.readString();
		ct_source = in.readString();
		ct_mark_sd = in.readString();
		ct_mark_txt = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(ct_id);
		dest.writeString(ct_name);
		dest.writeString(ct_urlstite);
		dest.writeString(ct_url);
		dest.writeString(ct_img);
		dest.writeString(ct_source);
		dest.writeString(ct_mark_sd);
		dest.writeString(ct_mark_txt);
	}

	public static final Creator<Type_cartoon> CREATOR = new Creator<Type_cartoon>() {

		@Override
		public Type_cartoon createFromParcel(Parcel source) {
			return new Type_cartoon(source);
		}

		@Override
		public Type_cartoon[] newArray(int size) {
			return new Type_cartoon[size];
		}
	};

	public int getCt_id() {
		return ct_id;
	}

	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}

	public String getCt_name() {
		return ct_name;
	}

	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}

	public String getCt_urlstite() {
		return ct_urlstite;
	}

	public void setCt_urlstite(String ct_urlstite) {
		this.ct_urlstite = ct_urlstite;
	}

	public String getCt_url() {
		return ct_url;
	}

	public void setCt_url(String ct_url) {
		this.ct_url = ct_url;
	}

	public String getCt_img() {
		return ct_img;
	}

	public void setCt_img(String ct_img) {
		this.ct_img = ct_img;
	}

	public String getCt_source() {
		return ct_source;
	}

	public void setCt_source(String ct_source) {
		this.ct_source = ct_source;
	}

	public String getCt_mark_sd() {
		return ct_mark_sd;
	}

	public void setCt_mark_sd(String ct_mark_sd) {
		this.ct_mark_sd = ct_mark_sd;
	}

	public String getCt_mark_txt() {
		return ct_mark_txt;
	}

	public void setCt_mark_txt(String ct_mark_txt) {
		this.ct_mark_txt = ct_mark_txt;
	}

}
