package log;

import java.util.logging.Logger;

import org.junit.Test;

/**
 * JDK自带的java.util.logging.Logger定义了7个日志级别，从严重到普通：SEVERE,WARNING,INFO,CONFIG,FINE,FINER,FINEST<br>
 * 默认级别是INFO
 * <p>
 * Logging系统在JVM启动时读取配置文件并完成初始化，一旦开始运行main()方法，就无法修改配置,
 * 配置不太方便，需要在JVM启动时传递参数-Djava.util.logging.config.file=<config-file-name>,
 * 所以使用不广泛
 * @author 93652
 * 
 */
public class JDKLoggingTest {
	
	@Test
	public void test1() {
		Logger logger = Logger.getGlobal();
		System.out.println(logger.getClass());
		logger.info("start process...");
		logger.warning("memory is running out...");
		logger.fine("ignored.");
		logger.severe("process is terminated...");
	}
	
	@Test
	public void test2() {
		Logger logger = Logger.getLogger(JDKLoggingTest.class.getName());
		System.out.println(logger.getClass());
		logger.info("hello world");
	}

}
