package core;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.junit.Test;

public class JavaBeanTest {
	
	@Test
	public void test1() throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : propertyDescriptors) {
			System.out.println(pd.getName());
			System.out.println(pd.getReadMethod());
			System.out.println(pd.getWriteMethod());
		}
	}

}
