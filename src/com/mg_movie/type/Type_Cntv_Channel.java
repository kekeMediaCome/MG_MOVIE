package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_Cntv_Channel implements Parcelable {

	private String channelid;
	private String channellogo;
	private String channelname;
	private String channelurl;
	private String program_name;
	private int viewback;

	public Type_Cntv_Channel() {

	}

	private Type_Cntv_Channel(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		channelid = in.readString();
		channellogo = in.readString();
		channelname = in.readString();
		channelurl = in.readString();
		program_name = in.readString();
		viewback = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(channelid);
		dest.writeString(channellogo);
		dest.writeString(channelname);
		dest.writeString(channelurl);
		dest.writeString(program_name);
		dest.writeInt(viewback);
	}

	public static final Creator<Type_Cntv_Channel> CREATOR = new Creator<Type_Cntv_Channel>() {

		@Override
		public Type_Cntv_Channel createFromParcel(Parcel source) {
			return new Type_Cntv_Channel(source);
		}

		@Override
		public Type_Cntv_Channel[] newArray(int size) {
			return new Type_Cntv_Channel[size];
		}
	};

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getChannellogo() {
		return channellogo;
	}

	public void setChannellogo(String channellogo) {
		this.channellogo = channellogo;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public String getChannelurl() {
		return channelurl;
	}

	public void setChannelurl(String channelurl) {
		this.channelurl = channelurl;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	public int getViewback() {
		return viewback;
	}

	public void setViewback(int viewback) {
		this.viewback = viewback;
	}

}
