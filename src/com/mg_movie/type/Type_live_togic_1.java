package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_live_togic_1 implements Parcelable {

	private String _id;
	private String category;
	private String icon;
	private String province;
	private String resolution;
	private String title;
	private String urls;
	private int num;

	public Type_live_togic_1() {

	}

	private Type_live_togic_1(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		_id = in.readString();
		category = in.readString();
		icon = in.readString();
		province = in.readString();
		resolution = in.readString();
		title = in.readString();
		urls = in.readString();
		num = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(_id);
		dest.writeString(category);
		dest.writeString(icon);
		dest.writeString(province);
		dest.writeString(resolution);
		dest.writeString(title);
		dest.writeString(urls);
		dest.writeInt(num);
	}

	public static final Creator<Type_live_togic_1> CREATOR = new Creator<Type_live_togic_1>() {

		@Override
		public Type_live_togic_1 createFromParcel(Parcel source) {
			return new Type_live_togic_1(source);
		}

		@Override
		public Type_live_togic_1[] newArray(int size) {
			return new Type_live_togic_1[size];
		}
	};

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
