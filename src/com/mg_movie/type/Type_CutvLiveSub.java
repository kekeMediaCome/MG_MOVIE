package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_CutvLiveSub implements Parcelable {

	private int channel_id;
	private String channel_name;
	private String thumb;
	private String mobile_url;

	public Type_CutvLiveSub() {

	}

	private Type_CutvLiveSub(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		channel_id = in.readInt();
		channel_name = in.readString();
		thumb = in.readString();
		mobile_url = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(channel_id);
		dest.writeString(channel_name);
		dest.writeString(thumb);
		dest.writeString(mobile_url);
	}

	public static final Creator<Type_CutvLiveSub> CREATOR = new Creator<Type_CutvLiveSub>() {

		@Override
		public Type_CutvLiveSub createFromParcel(Parcel source) {
			return new Type_CutvLiveSub(source);
		}

		@Override
		public Type_CutvLiveSub[] newArray(int size) {
			return new Type_CutvLiveSub[size];
		}
	};

	public int getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getMobile_url() {
		return mobile_url;
	}

	public void setMobile_url(String mobile_url) {
		this.mobile_url = mobile_url;
	}

}
