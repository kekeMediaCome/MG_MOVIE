package com.mg_movie.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mg_movie.KSetting;
import com.mg_movie.type.Type_v_qq_com;

public class Parser_movielist_56_com {

	public ArrayList<String> ParsePage(String url) {
		ArrayList<String> listPage = new ArrayList<String>();
		try {
			Document document = Jsoup.connect(url).get();
			Elements mod56_page_total_page = document.select(
					"div.mod56_page_pn").select("a");
			int count = mod56_page_total_page.size();
			int endPageNum = Integer.parseInt(mod56_page_total_page
					.get(count - 2).text().trim());
			for (int i = 0; i < endPageNum; i++) {
				String urlString = KSetting.movielist_56_com_head + i
						+ KSetting.movielist_56_com_end;
				listPage.add(urlString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPage;
	}

	public List<Type_v_qq_com> ParseVideo(String url_stite) {
		List<Type_v_qq_com> lists = new ArrayList<Type_v_qq_com>();
		Type_v_qq_com qq_video = null;
		try {
			Document document = Jsoup.connect(url_stite).get();
			Element tv_extend = document.getElementById("content");
			Elements videos = tv_extend.select("dl");
			for (Element element : videos) {
				Element a_img = element.select("a").first();
				String video_url = a_img.attr("href");
				String video_mark = a_img.text();
				Element img = a_img.select("img").first();
				String video_name = img.attr("alt");
				String video_img = img.attr("src");
				qq_video = new Type_v_qq_com();
				qq_video.setVideo_name(video_name);
				qq_video.setVideo_urlstite(url_stite);
				qq_video.setVideo_source(checkSource(url_stite));
				qq_video.setVideo_url(video_url);
				qq_video.setVideo_img(video_img);
				qq_video.setVideo_mark(video_mark);
				lists.add(qq_video);
				qq_video = null;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return lists;
	}

	public String checkSource(String url){
		String source = "qita";
		if (url.indexOf("http://v.qq.com") != -1) {
			source = "tencent";
		}else if (url.indexOf("http://video.56.com") != -1) {
			source = "56";
		}
		return source;
	}
}
