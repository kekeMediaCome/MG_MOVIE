package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 存放电视台的所有节目
 * 
 * @author jie
 * 
 */
public class Type_tv_list implements Parcelable {

	private String title;
	private String sub_title;
	private String sub_url;

	public Type_tv_list() {

	}

	private Type_tv_list(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		title = in.readString();
		sub_title = in.readString();
		sub_url = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(sub_title);
		dest.writeString(sub_url);
	}

	public static final Creator<Type_tv_list> CREATOR = new Creator<Type_tv_list>() {

		@Override
		public Type_tv_list createFromParcel(Parcel source) {
			return new Type_tv_list(source);
		}

		@Override
		public Type_tv_list[] newArray(int size) {
			return new Type_tv_list[size];
		}
	};

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getSub_url() {
		return sub_url;
	}

	public void setSub_url(String sub_url) {
		this.sub_url = sub_url;
	}

}
