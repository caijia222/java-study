package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class ZipFileTest {
	
	@Test
	public void test1() throws IOException {
		try(ZipInputStream zip = new ZipInputStream(new FileInputStream("out/zipfile.zip"))){
			ZipEntry entry = null;
			while((entry = zip.getNextEntry()) != null) {
				System.out.println(entry.getName());
				if(!entry.isDirectory()) {
					int n;
					StringBuilder sb = new StringBuilder();
					while((n = zip.read()) != -1) {
						sb.append((char)n);
					}
					System.out.println(sb.toString());
				}
			}
		}
	}
	
	@Test
	public void test2() throws IOException {
		try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("out/zipfile.zip"))){
			Path rootPath = Paths.get("out/zipfile");
			Path path1 = Paths.get(rootPath.toString(), "file1.txt");
			Path path2 = Paths.get(rootPath.toString(), "file2.txt");
			Path path3 = Paths.get(rootPath.toString(),"dir", "file3.txt");
			Path[] paths = {path1,path2,path3};
			for (Path path : paths) {
				zip.putNextEntry(new ZipEntry(path.toString()));
				zip.write(Files.readAllBytes(path));
				zip.closeEntry();
			}
		}
	}

}
