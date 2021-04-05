package io;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FileObjectTest {
	
	@Test
	public void test1() throws Exception {
		File file = new File("..");
		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(File.separator);
	}
	
	@Test
	public void test2() {
        File f1 = new File("C:\\Windows");
        File f2 = new File("C:\\Windows\\notepad.exe");
        File f3 = new File("C:\\Windows\\nothing");
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        System.out.println(f2.isFile());
        System.out.println(f2.isDirectory());
        System.out.println(f3.isFile());
        System.out.println(f3.isDirectory());
	}
	
	@Test
	public void test3() throws Exception {
		File dir = new File("test/test2");
		dir.mkdirs();
		File file = new File(dir,"file.txt");
		if (file.createNewFile()) {
			System.out.println(file.getAbsolutePath());
		    System.out.println(file.canRead());
		    System.out.println(file.canWrite());
		    System.out.println(file.canExecute());
		    if (file.delete()) {
		        System.out.println("删除成功");
		    }
		}
	}
	
	@Test
	public void test4() throws Exception {
		File f = File.createTempFile("tmp-", ".txt");
		f.deleteOnExit();
		System.out.println(f.isFile());
		System.out.println(f.getAbsolutePath());
	}
	
	@Test
	public void test5() {
		File f = new File("C:\\Windows");
		File[] listFiles = f.listFiles();
		for (File file : listFiles) {
			System.out.println(file);
		}
		System.out.println("===================");
		listFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".exe");
			}
		});
		for (File file : listFiles) {
			System.out.println(file);
		}
	}
	
	@Test
	public void test6() {
		Path path1 = Paths.get(".", "project","study");
		System.out.println("path1=" + path1);
		Path path2 = path1.toAbsolutePath();
		System.out.println("path2=" + path2);
		Path path3 = path2.normalize();
		System.out.println("path3=" + path3);
		File file = path3.toFile();
		System.out.println("file=" + file);
		Path parentPath = Paths.get("test").toAbsolutePath().normalize();
		System.out.println("parentPath =" + parentPath);
		for(Path p : parentPath) {
			System.out.println(p);
		}
	}
}
