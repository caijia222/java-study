package log;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	@Test
	public void test() {
		java.util.logging.Logger logger = java.util.logging.Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
        logger = java.util.logging.Logger.getLogger(LogTest.class.getName());
        logger.severe("有一个严重问题");
        
	}
	
	@Test
	public void test2() {
		Log log = LogFactory.getLog(LogTest.class);
		log.info("start...");
		log.warn("end.");
		log.error("错误");
	}
	
	@Test
	public void test3() {
		Logger logger = LoggerFactory.getLogger(LogTest.class);
		logger.info("start...{}",LogTest.class.getName());
		logger.warn("end.");
		logger.error("错误");
	}
}
