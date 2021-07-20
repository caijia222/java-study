package xml;

import java.io.InputStream;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonParse {

	/**
	 * XML(eXtensible Markup Language)文档解析成一个JavaBean
	 * com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.10.1
	 * org.codehaus.woodstox:woodstox-core-asl:4.4.1
	 */
	public static void main(String[] args) throws Exception{
		InputStream is = JacksonParse.class.getResourceAsStream("book.xml");
		JacksonXmlModule module = new JacksonXmlModule();
		XmlMapper mapper = new XmlMapper(module);
		Book book = mapper.readValue(is, Book.class);
		System.out.println(book);
	}
}