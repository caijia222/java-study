package log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Commons Logging是一个日志库，它是由Apache创建的日志模块,
 * 可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。
 * 默认情况下，Commons Logging自动搜索并使用Log4j，commons-logging要使用log4j2必须引用log4j-api，log4j-core，log4j-jcl三个包。
 * 如果没有找到Log4j，再使用其自带的Jdk14Logger，内部会调用JDK Logging
 * 
 * Commons Logging的六个日志级别：FATAL，ERROR，WARNING，INFO，DEBUG，TRACE
 * 
 * @author 93652
 *
 */
public class CommonsLoggingTest {
	
	private static final Log log = LogFactory.getLog(CommonsLoggingTest.class);
	
	public static void main(String[] args) {
		System.out.println(log.getClass());
		log.info("start...");
		log.warn("end.");
		Person p = new Person();
		p.foo();
		Student s = new Student();
		s.bar();
	}
}

class Person{
	//在实例方法中引用Log，通常定义一个实例变量，子类可以直接使用该log实例
	protected final Log log = LogFactory.getLog(getClass());
	void foo() {
		log.info("foo");
	}
}
class Student extends Person{
	void bar() {
		log.info("bar");
	}
}