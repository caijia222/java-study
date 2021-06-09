package designpattern.behavior;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class VisitorPattern {
	
	public static void main(String[] args) throws IOException {
		Files.walkFileTree(Paths.get("."), new MyFileVisitor());
	}
}

class MyFileVisitor extends SimpleFileVisitor<Path>{
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		System.out.println("pre visit dir:" + dir);
		return FileVisitResult.CONTINUE;
	}
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println("visit file:" + file);
		return FileVisitResult.CONTINUE;
	}
}
