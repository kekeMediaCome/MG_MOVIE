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
			Element pageNav = document.getElementById("pageNav");
			Elements pages = pageNav.select("span.separator");
			if (pages != null && pages.size() != 0) {
				int count = pages.size();
				String page = pages.get(count - 1).text().trim();
				count = Integer.parseInt(page.substring(1, page.length()-1));
				StringBuffer buffer = new StringBuffer();
				for (int i = 1; i <= count; i++) {
					buffer.append(KSetting.yinyuetai_url_head).append(i)
							.append(KSetting.yinyuetai_url_end);
					listPage.add(buffer.toString());
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
			int index = 0;
			for (Element music : musics) {
				index++;
				yinyue = new Type_YinYueTai();
				Element thumb_mv = music.select("div.thumb_mv").get(0);
				Element a = thumb_mv.select("a").get(0);
				yinyue.setMusic_url(a.attr("href"));
				Elements img = a.select("img");
				if (img != null && img.size() != 0) {
					yinyue.setMusic_img(img.get(0).attr("src"));
				}else {
					yinyue.setMusic_img("http://imgstatic.baidu.com/img/image/shouye/1f178a82b9014a900a9e7492a8773912b31bee79.jpg");
				}
				Elements shdIcoElement = thumb_mv.select("div.shdIco");
				if (shdIcoElement != null && shdIcoElement.size() != 0) {
					yinyue.setMusic_shdIco(shdIcoElement.get(0).text().trim());
				}else {
					yinyue.setMusic_shdIco("");
				}
				Elements v_timeElements = thumb_mv.select("div.v_time_num");
				if (v_timeElements != null && v_timeElements.size() != 0) {
					yinyue.setMusic_time(v_timeElements.get(0).text().trim());
				}else {
					yinyue.setMusic_time("");
				}
				Element info = music.select("div.info").get(0);
				String href = "";
				String title = "";
				String player = "";
				if (info != null) {
					Element a_title = info.select("a.special").get(0);
						if (a_title != null) {
							href = a_title.attr("href");
							title = a_title.attr("title");
							player = info.select("a.c3").get(0).text().trim();
						}
				}
				yinyue.setMusic_url(href);
				yinyue.setMusic_title(title);
				yinyue.setMusic_player(player);
				yinyue.setMusic_urlstite(url);
				lists.add(yinyue);
				AppLog.e("第 "+index+"  标题：  "+title);
				yinyue = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;
	}
}
