package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class ReflectionTest {
	
	@Test
	public void test1() {
		printClassInfo("".getClass());
		printClassInfo(Runnable.class);
		printClassInfo(java.time.Month.class);
		printClassInfo(String[].class);
		printClassInfo(int.class);
	}
	
    static void printClassInfo(Class<?> cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        System.out.println("Package name: " + cls.getPackage().getName());
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
        System.out.println("===========================");
    }
    
    @Test
    public void test2() throws Exception {
    	Class<Student> clz = Student.class;
    	System.out.println(clz.getField("score"));
    	System.out.println(clz.getDeclaredField("grade"));
    	Field[] fields = clz.getFields();
    	for (Field field : fields) System.out.println(field);
    	fields = clz.getDeclaredFields();
    	for (Field field : fields) System.out.println(field);
    }
    
    @Test
    public void test3() throws Exception {
    	Field f = String.class.getDeclaredField("value");
    	System.out.println(f.getName());
    	System.out.println(f.getType());
    	int modifiers = f.getModifiers();
    	System.out.println(Modifier.isPublic(modifiers));
    	System.out.println(Modifier.isProtected(modifiers));
    	System.out.println(Modifier.isPrivate(modifiers));
    	System.out.println(Modifier.isStatic(modifiers));
    	System.out.println(Modifier.isFinal(modifiers));
    }
    
    @Test
    public void test4() throws Exception {
    	Object p = new Person("xiaoming","male");
    	Class<?> clz = p.getClass();
    	Field field = clz.getDeclaredField("name");
    	field.setAccessible(true);
    	Object object = field.get(p);
    	System.out.println(object);
    	field.set(p, "xiaoqiang");
    	System.out.println(((Person)p).getName());
    }
    
    @Test
    public void test5() throws Exception {
    	Class<Student> clz = Student.class;
    	System.out.println(clz.getMethod("getGrade",int.class));
    	System.out.println(clz.getMethod("getName"));
    	Method method = clz.getDeclaredMethod("getGrade",String.class);
    	System.out.println(method);
    	System.out.println(method.getName());
    	System.out.println(method.getReturnType());
    	System.out.println(Arrays.toString(method.getParameters()));
    	System.out.println(method.getModifiers());
    }
    
    @Test
    public void test6() throws Exception {
    	String s = "Hello World";
    	Class<? extends String> class1 = s.getClass();
    	Method method = class1.getMethod("substring", int.class);
    	String ret = (String) method.invoke(s, 3);
    	System.out.println(ret);
    }
    
    /**
     * 反射调用静态方法
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {
    	Method method = Integer.class.getMethod("parseInt", String.class);
    	int ret = (int) method.invoke(null, "3");
    	System.out.println(ret);
    }

    @Test
    public void test8() throws Exception {
    	Method method = Student.class.getDeclaredMethod("setGrade", int.class);
    	method.setAccessible(true);
    	Student t = new Student();
    	method.invoke(t, 2);
    	System.out.println(t.getGrade());
    }
    
    @Test
    public void test9() throws Exception {
    	Method method = Person.class.getMethod("hello");
    	method.invoke(new Student());
    }
    
    @Test
    public void test10() throws Exception {
    	Constructor<Integer> constructor = Integer.class.getConstructor(int.class);
    	Integer newInstance = constructor.newInstance(22);
    	System.out.println(newInstance);
    }
    
    @Test
    public void test11() {
    	Class<Integer> clz = Integer.class;
    	Constructor<?>[] constructors = clz.getConstructors();
    	for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
    	constructors = clz.getDeclaredConstructors();
    	for (Constructor<?> constructor : constructors) {
    		System.out.println(constructor);
			
		}
    }
    
    @Test
    public void test12() {
    	Class<Integer> clz = Integer.class;
    	Class<? super Integer> superclass = clz.getSuperclass();
    	System.out.println(superclass);
    	Class<?> superclass2 = superclass.getSuperclass();
    	System.out.println(superclass2);
    	System.out.println(superclass2.getSuperclass());
    }
    
    @Test
    public void test13() {
    	Class<Integer> clz = Integer.class;
    	Class<?>[] interfaces = clz.getInterfaces();
    	for (Class<?> class1 : interfaces) {
			System.out.println(class1);
		}
    	System.out.println("==============");
    	interfaces = clz.getSuperclass().getInterfaces();
    	for (Class<?> class1 : interfaces) {
			System.out.println(class1);
		}
    }
    
    @Test
    public void test14() {
    	System.out.println(Integer.class.isAssignableFrom(Integer.class));
    	System.out.println(Number.class.isAssignableFrom(Integer.class));
    	System.out.println(Object.class.isAssignableFrom(Integer.class));
    	System.out.println(Integer.class.isAssignableFrom(Number.class));
    }
    
    @Test
    public void test15() {
    	Hello hello = 
    			(Hello)Proxy.newProxyInstance(
	    			ReflectionTest.class.getClassLoader(), 
	    			new Class[] {Hello.class}, 
	    			(proxy,method,args)->{
	    				System.out.println(method);
	    				System.out.println(Arrays.toString(args));
	    				return null;
	    			}
    			);
    	hello.morning("good morning");
    }
    
    @Test
    public void test16() {
    	Hello hello = new HelloDynamicProxy((proxy,method,args)->{
    		System.out.println(proxy);
			System.out.println(method);
			System.out.println(Arrays.toString(args));
			return null;
    	});
    	hello.morning("good morning");
    }
    
}

class Student extends Person {
	public int score;
	private int grade;
	public Student() {}
    public Student(String name,String sex) {super(name,sex);}
    public Student(String name,String sex,int score,int grade) {super(name,sex);this.score = score;this.grade = grade;}
    public int getGrade() {return grade;}
    public int getGrade(int test) {return 2;}
    @SuppressWarnings("unused")
	private int getGrade(String test) {return 2;}
    @SuppressWarnings("unused")
	private void setGrade(int test) {this.grade = test;}
    @Override
    public void hello() {System.out.println("Student.hello");}
}

class Person {
	private String name;
    private String sex;
    public Person() {}
    public Person(String name,String sex) {this.name = name;this.sex = sex;}
    public String getSex() {return sex;}
    public String getName() {return name;}
    public void hello() {System.out.println("Person.hello");}
}

interface Hello{
	void morning(String name);
}

class HelloDynamicProxy implements Hello{
	InvocationHandler handler;
	public HelloDynamicProxy(InvocationHandler handler) {
		this.handler = handler;
	}
	@Override
	public void morning(String name) {
		try {
			handler.invoke(this, Hello.class.getMethod("morning", String.class), new String[] {name});
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
