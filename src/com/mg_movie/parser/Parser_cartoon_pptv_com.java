package com.mg_movie.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.type.Type_cartoon;

public class Parser_cartoon_pptv_com {

	public ArrayList<String> ParsePage(String url) {
		ArrayList<String> listPage = new ArrayList<String>();
		try {
			Document document = Jsoup.connect(url).get();
			Elements pages = document.select("div.pages");
			if (pages != null) {
				Elements a = pages.get(0).select("a");
				int count = a.size();
				int pageEnd = Integer.parseInt(a.get(count - 2).text().trim());
				for (int i = 1; i <= pageEnd; i++) {
					String urlString = KSetting.cartoon_pptv_com_url_head + i
							+ KSetting.cartoon_pptv_com_url_end;
					AppLog.e(urlString);
					listPage.add(urlString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPage;
	}

	public List<Type_cartoon> ParseCartoon(String url_stite) {
		List<Type_cartoon> lists = new ArrayList<Type_cartoon>();
		Type_cartoon type_cartoon = null;
		try {
			Document document = Jsoup.connect(url_stite).get();
			Elements content = document.select("div.content");
			if (content != null) {
				Elements cts = content.get(0).select("div.bd").get(0).select("li");
				for (Element ct : cts) {
					Element a = ct.select("p.pic").get(0).select("a").get(0);
					String title = a.attr("title");
					String url = a.attr("href");
					String img = a.select("img").get(0).attr("src");
					String mark_txt = a.select("span.time").text().trim();
					String mark_sd = a.select("span.ico").text();
					if (mark_sd == null || mark_sd.equals("")) {
						mark_sd = "标清";
					}
					type_cartoon = new Type_cartoon();
					type_cartoon.setCt_name(title);
					type_cartoon.setCt_img(img);
					type_cartoon.setCt_url(url);
					type_cartoon.setCt_mark_sd(mark_sd);
					type_cartoon.setCt_mark_txt(mark_txt);
					type_cartoon.setCt_urlstite(url_stite);
					type_cartoon.setCt_source(checkSource(url_stite));
					lists.add(type_cartoon);
					type_cartoon = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	public String checkSource(String url) {
		String source = "qita";
		if (url.indexOf("http://v.qq.com") != -1) {
			source = "tencent";
		} else if (url.indexOf("http://video.56.com") != -1) {
			source = "56";
		} else if (url.indexOf("pptv.com") != -1) {
			source = "pptv";
		}
		return source;
	}
}
