package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_CntvMediaType implements Parcelable {

	private String mediatype;
	private String name;

	public Type_CntvMediaType() {

	}

	private Type_CntvMediaType(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		mediatype = in.readString();
		name = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mediatype);
		dest.writeString(name);
	}

	public static final Creator<Type_CntvMediaType> CREATOR = new Creator<Type_CntvMediaType>() {

		@Override
		public Type_CntvMediaType createFromParcel(Parcel source) {
			return new Type_CntvMediaType(source);
		}

		@Override
		public Type_CntvMediaType[] newArray(int size) {
			return new Type_CntvMediaType[size];
		}
	};

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
