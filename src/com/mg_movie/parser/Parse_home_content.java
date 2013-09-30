package com.mg_movie.parser;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.R;
import com.mg_movie.type.Type_home_content;

public class Parse_home_content {

	private String[] cls_packets = { "com.mg_movie.activity.MG_NetRadio",
			"com.mg_movie.activity.MG_NetRadio",
			"com.mg_movie.activity.MG_NetRadio",
			"com.mg_movie.activity.MG_NetRadio" };
	public int count = 8;

	public List<Type_home_content> getHomeContent() {
		List<Type_home_content> lists = new ArrayList<Type_home_content>();
		try {
			Type_home_content type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_1);
			type.setTitle_id(R.string.mjicon_1_title);
//			type.setCls_packet(cls_packets[0]);
			lists.add(type);
			type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_2);
			type.setTitle_id(R.string.mjicon_2_title);
			type.setCls_packet("com.mg_movie.activity.MG_Live");
			lists.add(type);
			type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_3);
			type.setTitle_id(R.string.mjicon_3_title);
			type.setCls_packet("com.mg_movie.activity.MG_NetRadio");
			lists.add(type);
			type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_4);
			type.setTitle_id(R.string.mjicon_4_title);
			type.setCls_packet("com.mg_movie.activity.MG_MOVIE");
			lists.add(type);
			type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_5);
			type.setTitle_id(R.string.mjicon_5_title);
			type.setCls_packet("com.mg_movie.activity.MG_TV");
			lists.add(type);
			type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_6);
			type.setTitle_id(R.string.mjicon_6_title);
			type.setCls_packet("com.mg_movie.activity.MG_Music");
			lists.add(type);
			type = new Type_home_content();
			type.setImg_id(R.drawable.mjicon_7);
			type.setTitle_id(R.string.mjicon_7_title);
			type.setCls_packet("com.mg_movie.activity.MG_Cartoon");
			lists.add(type);
//			type = new Type_home_content();
//			type.setImg_id(R.drawable.mjicon_8);
//			type.setTitle_id(R.string.mjicon_8_title);
//			type.setCls_packet("com.mg_movie.activity.MG_CUTV");
//			lists.add(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
}
