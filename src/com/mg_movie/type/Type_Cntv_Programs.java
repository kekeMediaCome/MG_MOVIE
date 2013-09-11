package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_Cntv_Programs implements Parcelable {

	private String playback;
	private String realTime;
	private String showtime;
	private String title;

	public Type_Cntv_Programs() {

	}

	private Type_Cntv_Programs(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		playback = in.readString();
		realTime = in.readString();
		showtime = in.readString();
		title = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(playback);
		dest.writeString(realTime);
		dest.writeString(showtime);
		dest.writeString(title);
	}

	public static final Creator<Type_Cntv_Programs> CREATOR = new Creator<Type_Cntv_Programs>() {

		@Override
		public Type_Cntv_Programs createFromParcel(Parcel source) {
			return new Type_Cntv_Programs(source);
		}

		@Override
		public Type_Cntv_Programs[] newArray(int size) {
			return new Type_Cntv_Programs[size];
		}
	};

	public String getPlayback() {
		return playback;
	}

	public void setPlayback(String playback) {
		this.playback = playback;
	}

	public String getRealTime() {
		return realTime;
	}

	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
