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

import com.mg_movie.type.Type_CutvLiveSub;
import com.mg_movie.utils.HttpUtil;

public class Parser_Live_CutvSubDom {
	public static List<Type_CutvLiveSub> parseXml(String url) {
		List<Type_CutvLiveSub> cutvList = new ArrayList<Type_CutvLiveSub>();
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
			NodeList nodeList = rootElement.getElementsByTagName("channel");
			int nodeListcount = nodeList.getLength();
			// 遍历取出来的channel节点集合
			for (int i = 0; i < nodeListcount; i++) {
				// 得到一个channel节点
				Element videoElement = (Element) nodeList.item(i);
				// 新建一个channel对象
				Type_CutvLiveSub cutvLiveSub = new Type_CutvLiveSub();
				// 取得tv标签的下属所有节点
				NodeList tvChildList = videoElement.getChildNodes();
				int countChild = tvChildList.getLength();
				for (int j = 0; j < countChild; j++) {
					// 创建一个引用，指向循环的标签
					Node node = tvChildList.item(j);
					// 如果此循环出来的元素是Element对象，即标签元素，那么执行以下代码
					if (Node.ELEMENT_NODE == node.getNodeType()) {
						// 如果这个标签的名字是channel_id,那么得到它的值，赋值给channel对象
						if ("channel_id".equals(node.getNodeName())) {
							String idValue = node.getFirstChild()
									.getNodeValue().trim();
							cutvLiveSub.setChannel_id(Integer.valueOf(idValue));
						} else if ("channel_name".equals(node.getNodeName())) {
							String tv_name = node.getFirstChild()
									.getNodeValue().trim();
							cutvLiveSub.setChannel_name(tv_name);
						} else if ("thumb".equals(node.getNodeName())) {
							String tv_thumb_img = node.getFirstChild()
									.getNodeValue().trim();
							cutvLiveSub.setThumb(tv_thumb_img);
						} else if ("mobile_url".equals(node.getNodeName())) {
							String tv_logo = node.getFirstChild()
									.getNodeValue().trim();
							cutvLiveSub.setMobile_url(tv_logo);
						}
					}
				}
				cutvList.add(cutvLiveSub);
			}
		} catch (Exception e) {
		}

		return cutvList;
	}
}
