package com.mg_movie.type;

import java.io.Serializable;

public class Type_Cntv_Remind implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int STATUS_IS_NEW = 1;
	public static final int STATUS_IS_OLD = 0;
	private String channel_name = "";
	private int id;
	private int is_new = 1;
	private String pic = "";
	private String remind_time = "";
	private String title = "";

	public String getChannel_name() {
		return this.channel_name;
	}

	public int getId() {
		return this.id;
	}

	public int getIs_new() {
		return this.is_new;
	}

	public String getPic() {
		return this.pic;
	}

	public String getRemind_time() {
		return this.remind_time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setChannel_name(String paramString) {
		this.channel_name = paramString;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}

	public void setIs_new(int paramInt) {
		this.is_new = paramInt;
	}

	public void setPic(String paramString) {
		this.pic = paramString;
	}

	public void setRemind_time(String paramString) {
		this.remind_time = paramString;
	}

	public void setTitle(String paramString) {
		this.title = paramString;
	}
}