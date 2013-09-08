package com.mg_movie.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.type.Type_tv;
import com.mg_movie.type.Type_tv_list;

public class Parser_tv_qq_com {

	/**
	 * 获取电视台节目
	 * 
	 * @param url
	 * @return
	 */
	public ArrayList<String> ParsePage(String url) {
		ArrayList<String> listPage = new ArrayList<String>();
		try {
			Document document = Jsoup.connect(url).get();
			Element mod_pagenav = document.getElementById("pager");
			Elements channels = mod_pagenav.select("a.c_txt6");
			if (channels != null && channels.size() != 0) {
				int count = channels.size();
				int endPageNum = Integer.parseInt(channels.get(count - 1).attr(
						"title"));
				for (int i = 0; i < endPageNum; i++) {
					String urlString = KSetting.tv_q_com_url_head + i
							+ KSetting.tv_q_com_url_end;
					AppLog.e(urlString);
					listPage.add(urlString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPage;
	}

	public List<Type_tv> ParseTV(String url_stite) {
		List<Type_tv> lists = new ArrayList<Type_tv>();
		Type_tv type_tv = null;
		try {
			Document document = Jsoup.connect(url_stite).get();
			Element mod_cont = document.getElementById("content");
			Elements mod_list_pic_130 = mod_cont.select("li");
			for (Element element : mod_list_pic_130) {
				Elements tv = element.select("a");
				if (tv != null && tv.size() != 0) {
					Element vElement = tv.get(0);
					//过滤片花
					String tv_mark_txt = vElement.select("span.figure_mask")
							.get(0).select("em.mask_txt").text().trim();
					if (tv_mark_txt != null && !tv_mark_txt.trim().equals("")) {
						String tv_name = vElement.attr("title");
						String tv_url = vElement.attr("href");
						String tv_img = vElement.select("img").get(0).attr("src");
						Elements sups = vElement.select("sup");
						if (sups == null || sups.size() == 1) {
							String tv_mark_sd;
							if (sups == null) {
								tv_mark_sd = "标清";
							}else {
								tv_mark_sd = sups.text().trim();
								if (tv_mark_sd.indexOf("片花") != -1) {
									continue;
								}
							}
							type_tv = new Type_tv();
							type_tv.setTv_name(tv_name);
							type_tv.setTv_urlstite(url_stite);
							type_tv.setTv_url(tv_url);
							type_tv.setTv_source(checkSource(url_stite));
							type_tv.setTv_img(tv_img);
							type_tv.setTv_mark_sd(tv_mark_sd);
							type_tv.setTv_mark_txt(tv_mark_txt);
							lists.add(type_tv);
							type_tv = null;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;
	}

	/**
	 * 获取某个频道的所有节目
	 * 
	 * @param url
	 * @return
	 */
	public List<Type_tv_list> ParseTVAll(String url) {
		List<Type_tv_list> lists = new ArrayList<Type_tv_list>();
		Type_tv_list tv = null;
		try {
			Document document = Jsoup.connect(url).get();
			Element mod_videolist = document.getElementById("mod_videolist");
			Elements mod_album_notitlist_lists = mod_videolist
					.select("div.mod_album_notitlist_lists");
			for (Element element : mod_album_notitlist_lists) {
				Elements tv_lists = element.select("a");
				for (Element tv_channel : tv_lists) {
					String sub_title = tv_channel.attr("title");
					String sub_url = tv_channel.attr("href");
					if (!sub_url.startsWith("http://v.qq.com/")) {
						sub_url = "http://v.qq.com"+sub_url;
					}
					tv = new Type_tv_list();
					tv.setSub_title(sub_title);
					tv.setSub_url(sub_url);
					lists.add(tv);
					tv = null;
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
		}
		return source;
	}
}
