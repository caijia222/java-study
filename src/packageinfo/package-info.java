/** 
 * <b>package-info不是平常类，其作用有三个:</b><br> 
 * 1、为标注在包上Annotation提供便利；<br> 
 * 2、声明包的私有类和常量；<br> 
 * 3、提供包的整体注释说明。<br>  
*/  
@PkgAnnotation
package packageinfo;

class PkgClass{
	
	public void fun() {
		System.out.println("fun()");
	}
}

class PkgConst{
	public static final String PACKAGE_CONST = "ABC";
}