package com.mg_movie.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Type_home_content implements Parcelable {

	private int img_id;
	private int title_id;
	private String cls_packet;

	public Type_home_content(){
		
	}
	private Type_home_content(Parcel in){
		readFromParcel(in);
	}
	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in){
		img_id = in.readInt();
		title_id = in.readInt();
		cls_packet = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(img_id);
		dest.writeInt(title_id);
		dest.writeString(cls_packet);
	}

	public final Creator<Type_home_content> CREATOR = new Creator<Type_home_content>() {
		@Override
		public Type_home_content createFromParcel(Parcel source) {
			return new Type_home_content(source);
		}

		@Override
		public Type_home_content[] newArray(int size) {
			return new Type_home_content[size];
		}
	};

	public int getImg_id() {
		return img_id;
	}

	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}

	public int getTitle_id() {
		return title_id;
	}

	public void setTitle_id(int title_id) {
		this.title_id = title_id;
	}

	public String getCls_packet() {
		return cls_packet;
	}

	public void setCls_packet(String cls_packet) {
		this.cls_packet = cls_packet;
	}

}
