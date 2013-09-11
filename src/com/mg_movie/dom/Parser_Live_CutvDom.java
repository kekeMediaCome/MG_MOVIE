package com.mg_movie.dom;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mg_movie.type.Type_CutvLive;
import com.mg_movie.utils.HttpUtil;

public class Parser_Live_CutvDom {

	public static List<Type_CutvLive> parseXml(String url) {
		List<Type_CutvLive> cutvList = new ArrayList<Type_CutvLive>();
		InputStream stream = HttpUtil.GetInputStreamFromURL(url);
		try {
			// 得到Dom解析对象工厂
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// 通过工厂创建Dom解析对象实例
			DocumentBuilder db = factory.newDocumentBuilder();
			// 将xml文件的输入流交给Dom解析对象进行解析，并将Dom树返回
			Document document = db.parse(stream);
			// 通过Dom树接收到根元素
			Element rootElement = document.getDocumentElement();
			// 通过根元素获得下属的所有名字为tv节点
			NodeList nodeList = rootElement.getElementsByTagName("tv");
			int nodeListcount = nodeList.getLength();
			// 遍历取出来的tv节点集合
			for (int i = 0; i < nodeListcount; i++) {
				// 得到一个tv节点
				Element videoElement = (Element) nodeList.item(i);
				// 新建一个tv对象
				Type_CutvLive cutvLive = new Type_CutvLive();
				// 取得tv标签的下属所有节点
				NodeList tvChildList = videoElement.getChildNodes();
				int countChild = tvChildList.getLength();
				for (int j = 0; j < countChild; j++) {
					// 创建一个引用，指向循环的标签
					Node node = tvChildList.item(j);
					// 如果此循环出来的元素是Element对象，即标签元素，那么执行以下代码
					if (Node.ELEMENT_NODE == node.getNodeType()) {
						// 如果这个标签的名字是tv_id,那么得到它的值，赋值给Person对象
						if ("tv_id".equals(node.getNodeName())) {
							String idValue = node.getFirstChild()
									.getNodeValue().trim();
							cutvLive.setTv_id(Integer.valueOf(idValue));
						} else if ("tv_name".equals(node.getNodeName())) {
							String tv_name = node.getFirstChild()
									.getNodeValue().trim();
							cutvLive.setTv_name(tv_name);
						} else if ("tv_thumb_img".equals(node.getNodeName())) {
							String tv_thumb_img = node.getFirstChild()
									.getNodeValue().trim();
							cutvLive.setTv_thumb_img(tv_thumb_img);
						} else if ("tv_logo".equals(node.getNodeName())) {
							String tv_logo = node.getFirstChild()
									.getNodeValue().trim();
							cutvLive.setTv_logo(tv_logo);
						}
					}
				}
				cutvList.add(cutvLive);
			}
		} catch (Exception e) {
		}

		return cutvList;
	}
}
