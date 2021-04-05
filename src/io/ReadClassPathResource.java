package io;

import java.io.FileReader;
import java.io.Reader;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class ReadClassPathResource {
	
	public static void main(String[] args) throws Exception {
//		InputStream is = ReadClassPathResource.class.getResourceAsStream("/setting.properties");
//		InputStream is = new FileInputStream("conf/setting.properties");
		Reader is = new FileReader("conf/setting.properties");
		if(is != null) {
			Properties pro = new Properties();
			pro.load(is);
			Set<Entry<Object, Object>> entrySet = pro.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				System.out.println(entry.getKey() + "=" + entry.getValue());
			}
		}
	}
}
