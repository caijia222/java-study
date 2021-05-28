package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SLF4J是日志接口，Logback是日志实现类
 * @author 93652
 *
 */
public class SLF4jTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SLF4jTest.class);
	
	public static void main(String[] args) {
		logger.info("hello world");
	}

}
