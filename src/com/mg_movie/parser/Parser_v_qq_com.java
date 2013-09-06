package com.mg_movie.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.type.Type_v_qq_com;

public class Parser_v_qq_com {
	
	public ArrayList<String> ParsePage(String url){
		ArrayList<String> listPage = new ArrayList<String>();
		try {
			Document document = Jsoup.connect(url).get();
			Element mod_pagenav = document.getElementById("pager");
			Elements channels = mod_pagenav.select("a.c_txt6");
			if (channels != null && channels.size() != 0) {
				int count = channels.size();
				int endPageNum = Integer.parseInt(channels.get(count-1).attr("title"));
				for (int i = 0; i < endPageNum; i++) {
					String urlString = KSetting.v_q_com_page_head+i+KSetting.v_q_com_page_end;
					AppLog.e(urlString);
					listPage.add(urlString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPage;
	}
	
	public List<Type_v_qq_com> ParseVideo(String url_stite){
		List<Type_v_qq_com> lists = new ArrayList<Type_v_qq_com>();
		Type_v_qq_com qq_video = null;
		try {
			Document document = Jsoup.connect(url_stite).get();
			Element mod_cont = document.getElementById("content");
			Elements mod_list_pic_130_lis = mod_cont.select("li");
			for (Element element : mod_list_pic_130_lis) {
				Elements video = element.select("a");
				if (video != null && video.size() != 0) {
					Element vElement = video.get(0);
					String video_title = vElement.attr("title");
					String video_url = vElement.attr("href");
					String video_img = vElement.select("img").get(0).attr("src");
					String video_mark = vElement.select("sup").get(0).text().trim();
					qq_video = new Type_v_qq_com();
					qq_video.setVideo_name(video_title);
					AppLog.e(video_title);
					qq_video.setVideo_urlstite(url_stite);
					qq_video.setVideo_source(checkSource(url_stite));
					qq_video.setVideo_url(video_url);
					qq_video.setVideo_img(video_img);
					qq_video.setVideo_mark(video_mark);
				}
				lists.add(qq_video);
				qq_video = null;
			}
		} catch (Exception e) {
		}
		return lists;
	}
	
	public String checkSource(String url){
		String source = "qita";
		if (url.indexOf("http://v.qq.com") != -1) {
			source = "tencent";
		}
		return source;
	}
}
