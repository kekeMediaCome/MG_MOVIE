package com.mg_movie.parser;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.type.Type_YinYueTai;

public class Parser_music_yinyuetai_com {

	public ArrayList<String> ParsePage(String url) {
		ArrayList<String> listPage = new ArrayList<String>();
		try {
			Document document = Jsoup.connect(url).get();
			Element pageNav = document.getElementById("pagetype").getElementById("pageNav");
			Elements pages = pageNav.select("span.separator");
			if (pages != null && pages.size() != 0) {
				int count = Integer.parseInt(pages.get(0).text().trim());
				StringBuffer buffer = new StringBuffer();
				for (int i = 1; i <= count; i++) {
					buffer.append(KSetting.yinyuetai_url_head).append(i)
							.append(KSetting.yinyuetai_url_end);
					listPage.add(buffer.toString());
					AppLog.e(buffer.toString());
					buffer.delete(0, buffer.length());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPage;
	}

	public ArrayList<Type_YinYueTai> ParseMusic(String url) {
		ArrayList<Type_YinYueTai> lists = new ArrayList<Type_YinYueTai>();
		try {
			Document document = Jsoup.connect(url).get();
			Element mv_list_vertical = document.getElementById("mvlist");
			Elements musics = mv_list_vertical.select("li");
			Type_YinYueTai yinyue = null;
			for (Element music : musics) {
				yinyue = new Type_YinYueTai();
				Element thumb_mv = music.select("div.thumb_mv").get(0);
				Element a = thumb_mv.select("a").get(0);
				yinyue.setMusic_url(a.attr("href"));
				Element img = a.select("img.src").get(0);
				yinyue.setMusic_img(img.attr("src"));
				yinyue.setMusic_shdIco(thumb_mv.select("div.shdIco").get(0).text().trim());
				yinyue.setMusic_time(thumb_mv.select("div.v_time_num").get(0).text().trim());
				Element info = music.select("div.info").get(0);
				Element a_title = info.select("a.special").get(0);
				yinyue.setMusic_url(a_title.attr("href"));
				yinyue.setMusic_title(a_title.attr("title"));
				yinyue.setMusic_player(info.select("a.c3").get(0).text().trim());
				yinyue.setMusic_urlstite(url);
				lists.add(yinyue);
				yinyue = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;
	}
}
