package annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

@Report
public class AnnotationTest {

	@Report(value="r_value",level="r_level",type=3)
	@Report(value="r2_value",level="r2_level",type=4)
	public void test1(@Report String a) {}
	
	@Test
	public void test2() {
		if(AnnotationTest.class.isAnnotationPresent(Report.class)) {
			Report annotation = AnnotationTest.class.getAnnotation(Report.class);
			System.out.println(annotation.value());
			System.out.println(annotation.type());
			System.out.println(annotation.level());
		}
	}
	
	@Test
	public void test3() throws Exception {
		Method method = AnnotationTest.class.getMethod("test1", String.class);
		Annotation[][] ats = method.getParameterAnnotations();
		Report reprot = (Report) ats[0][0];
		System.out.println(reprot.level());
	}
	
	@Test
	public void test4() throws Exception {
		Person p = new Person();
		p.name = "";
		check(p);
	}
	
	public static void check(Object obj) throws Exception {
		Class<? extends Object> clz = obj.getClass();
		Field field = clz.getField("name");
		Object object = field.get(obj);
		if(object instanceof String) {
			String name = (String) object;
			Range range = field.getAnnotation(Range.class);
			if(name.length()<range.min() || name.length()>range.max()) 
				throw new IllegalArgumentException(String.format("name的长度为%s到%s之间", range.min(),range.max()));
		}
		
	}
	
	
}

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Repeatable(Reports.class)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Report{
	int type() default 0;
	String level() default "info";
	String value() default "";
}
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Reports{
	Report[] value();
}

@Report(type=1)
class Person{
	@Range(min=1,max=20)
	public String name;
}
class Student extends Person{}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Range{
	int min() default 0;
	int max() default 255;
}
