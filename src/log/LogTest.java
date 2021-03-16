package log;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogTest {

	@Test
	public void test() {
		Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
        logger = Logger.getLogger(LogTest.class.getName());
        logger.severe("有一个严重问题");
        
	}
	
	@Test
	public void test2() {
		Log log = LogFactory.getLog(LogTest.class);
		log.info("start...");
		log.warn("end.");
		log.error("错误");
	}
}
