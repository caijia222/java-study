package json;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonParse {

	/**
	 * JSON(JavaScript Object Notation) 去除了所有JavaScript执行代码，只保留JavaScript的对象格式
	 * 	支持数据类型>>> 键值对：{"key": value};数组：[1, 2, 3];字符串："abc";数值（整数和浮点数）：12.34;布尔值：true或false;空值：null
		转换JavaBean需引入：com.fasterxml.jackson.core:jackson-databind:2.10.0
		转换特定Java类型如JavaTime需引入：com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0
	 	
	 */
	public static void main(String[] args) throws Exception {
		InputStream is = JacksonParse.class.getResourceAsStream("book.json");
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		// 反序列化时忽略不存在的JavaBean属性:
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Book book = mapper.readValue(is, Book.class); // 反序列化
		System.out.println(book);
		
		String str = mapper.writeValueAsString(book); // 序列化
		System.out.println(str);
		String str2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(book); // 格式化序列化
		System.out.println(str2);
	}
}

/**
 * 自定义解析实现类
 */
class IsbnDeserializer extends JsonDeserializer<BigInteger>{
	@Override
	public BigInteger deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String s = p.getValueAsString(); // 读取原始的JSON字符串内容:
		if(s != null) {
			return new BigInteger(s.replace("-", ""));
		}
		return null;
	}
	
} 

