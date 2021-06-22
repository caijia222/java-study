package xstream;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

/**
 * 需要xmlpull xstream xpp3
 */
public class XStreamTest {
	XStream xStream = new XStream(new Xpp3Driver());
	Person person = new Person();
	String xml = """
			<person personid="2">
			  <name>X stream</name>
			  <address>上海</address>
			  <tel>87654343</tel>
			  <email>54332@qq.com</email>
			</person>
			""";
	{
		person.setPersonid("2");
		person.setAddress("上海");
		person.setEmail("54332@qq.com");
		person.setTel("87654343");
		person.setName("X stream");
        xStream.alias("person", Person.class);
        xStream.useAttributeFor(Person.class, "personid");
	}
	
	@Test
	public void objectToXml() {
        System.out.println(xStream.toXML(person));
	}
	
	@Test
	public void XmlToObject() {
        System.out.println((Person) xStream.fromXML(xml));
	}
}
