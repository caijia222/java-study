package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 用log4j2自己的接口类和实现类，log4j-api和log4j-core
 * @author 93652
 *
 */
public class Log4jTest {
	private static final Logger logger = LogManager.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		System.out.println(logger.getClass());
//		logger.info("hello world!");
//		logger.info("Some long-running operation returned {}",foo());
//		logger.info("Some long-running operation returned {}",()->foo());
		
		logger.trace("Entering application.");
		Bar bar = new Bar();
		if(!bar.doIt()) {
			logger.error("Didn't do it.");
		}
		logger.trace("Exiting application.");
	}
	
	public static String foo() {
		logger.traceEntry();
		logger.traceExit();
		return "foo ret";
	}

}
class Bar{
	private static final Logger logger = LogManager.getLogger();
	public boolean doIt() {
		logger.traceEntry();
		logger.error("Did it again!");
		return logger.traceExit(false);
	}
}
