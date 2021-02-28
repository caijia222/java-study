package packageinfo;

import java.lang.annotation.Annotation;

public class Client {

	public static void main(String[] args) {
		Package[] packages = Package.getPackages();
		for (Package package1 : packages) {
			if (package1.getName().equals("packageinfo")) {
				Annotation[] annotations = package1.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof PkgAnnotation)
						System.out.println("I am the PkgAnnotation");
				}
			}
		}
	}

}
