package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_YinYueTai implements Parcelable {

	private int music_id;
	private String music_title;
	private String music_url;
	private String music_urlstite;
	private String music_img;
	private String music_shdIco;
	private String music_time;
	private String music_player;

	public Type_YinYueTai() {

	}

	public Type_YinYueTai(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		music_id = in.readInt();
		music_time = in.readString();
		music_url = in.readString();
		music_urlstite = in.readString();
		music_img = in.readString();
		music_shdIco = in.readString();
		music_time = in.readString();
		music_player = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(music_id);
		dest.writeString(music_time);
		dest.writeString(music_url);
		dest.writeString(music_urlstite);
		dest.writeString(music_img);
		dest.writeString(music_shdIco);
		dest.writeString(music_time);
		dest.writeString(music_player);
	}

	public static final Creator<Type_YinYueTai> CREATOR = new Creator<Type_YinYueTai>() {

		@Override
		public Type_YinYueTai createFromParcel(Parcel source) {
			return new Type_YinYueTai(source);
		}

		@Override
		public Type_YinYueTai[] newArray(int size) {
			return new Type_YinYueTai[size];
		}
	};

	
	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public String getMusic_title() {
		return music_title;
	}

	public void setMusic_title(String music_title) {
		this.music_title = music_title;
	}

	public String getMusic_url() {
		return music_url;
	}

	public void setMusic_url(String music_url) {
		this.music_url = music_url;
	}

	public String getMusic_urlstite() {
		return music_urlstite;
	}

	public void setMusic_urlstite(String music_urlstite) {
		this.music_urlstite = music_urlstite;
	}

	
	public String getMusic_img() {
		return music_img;
	}

	public void setMusic_img(String music_img) {
		this.music_img = music_img;
	}

	public String getMusic_shdIco() {
		return music_shdIco;
	}

	public void setMusic_shdIco(String music_shdIco) {
		this.music_shdIco = music_shdIco;
	}

	public String getMusic_time() {
		return music_time;
	}

	public void setMusic_time(String music_time) {
		this.music_time = music_time;
	}

	public String getMusic_player() {
		return music_player;
	}

	public void setMusic_player(String music_player) {
		this.music_player = music_player;
	}

}
