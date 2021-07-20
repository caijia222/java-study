package xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DomParse {

	/**
	 * DOM解析 Document Object Model 一次性读取XML，并在内存中表示为树形结构
	 */
	public static void main(String[] args) throws Exception{
		InputStream is = DomParse.class.getResourceAsStream("book.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		printNode(doc, 0);

	}

	public static void printNode(Node n, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print(' ');
		}
		switch (n.getNodeType()) {
		case Node.DOCUMENT_NODE: // Document节点
			System.out.println("Document: " + n.getNodeName());
			break;
		case Node.ELEMENT_NODE: // 元素节点
			System.out.println("Element: " + n.getNodeName());
			break;
		case Node.TEXT_NODE: // 文本
			System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
			break;
		case Node.ATTRIBUTE_NODE: // 属性
			System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
			break;
		default: // 其他
			System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
		}
		for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
			printNode(child, indent + 1);
		}
	}
}
