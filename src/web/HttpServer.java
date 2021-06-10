package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8080);
		System.out.println("server is running...");
		for (;;) {
			Socket socket = ss.accept();
			System.out.println("connected from " + socket.getRemoteSocketAddress());
			new Handler(socket).start();
		}
	}

}

class Handler extends Thread {
	Socket socket;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (InputStream is = socket.getInputStream(); 
				OutputStream os = socket.getOutputStream()) {
			handle(is,os);
		} catch (Exception e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("client disconnected.");
		}
	}

	private void handle(InputStream is, OutputStream os) throws IOException {
		var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		var writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
		boolean requestOk = false;
		String first = reader.readLine();
		if(first.startsWith("GET / HTTP/1.")) {
			requestOk = true;
		}
		for(;;) {
			String header = reader.readLine();
			if(header.isEmpty()) {
				break;
			}
			System.out.println(header);
		}
		System.out.println(requestOk ? "Response OK" : "Response Error");
		if(!requestOk) {
			writer.write("HTTP/1.0 404 Not Found\r\n");
			writer.write("Content-Lenght: 0\r\n");
			writer.write("\r\n");
			writer.flush();
		}else {
		    // 发送成功响应:
	        String data = "<html><body><h1>Hello, world!</h1></body></html>";
	        int length = data.getBytes(StandardCharsets.UTF_8).length;
	        writer.write("HTTP/1.0 200 OK\r\n");
	        writer.write("Connection: close\r\n");
	        writer.write("Content-Type: text/html\r\n");
	        writer.write("Content-Length: " + length + "\r\n");
	        writer.write("\r\n"); // 空行标识Header和Body的分隔
	        writer.write(data);
	        writer.flush();
		}
		
	}
}
