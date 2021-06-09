package network;

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
import java.util.Scanner;

import org.junit.Test;

/**
 * 应用程序通过一个Socket来建立一个远程连接，而Socket内部通过TCP/IP协议把数据传输到网络
 * 一个Socket就是由IP地址和端口号（范围是0～65535）组成，可以把Socket简单理解为IP地址加端口号，小于1024的端口属于特权端口，需要管理员权限
 * 对服务器端来说，它的Socket是指定的IP地址和指定的端口号；
 * 对客户端来说，它的Socket是它所在计算机的IP地址和一个由操作系统分配的随机端口号。
 */
public class TCPProggram {
	
	@Test
	public void tcpServer() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(6666);
		System.out.println("tcp server is running");
		while(true) {
			Socket socket = ss.accept();
			System.out.println("connected form " + socket.getRemoteSocketAddress());
			new ServerHandler(socket).start();
		}
	}
	
	@Test
	public void tcpClient() throws IOException {
		Socket socket = new Socket("localhost", 6666);
		try (InputStream is = socket.getInputStream()){
			try (OutputStream os = socket.getOutputStream()){
				var writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
				var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				System.out.println("[server] " + reader.readLine());
				Scanner scanner = new Scanner(System.in);
				for(;;) {
					System.out.println(">>> ");
					String req = scanner.next();
					writer.write(req);
					writer.newLine();
					writer.flush();
					String rsp = reader.readLine();
					System.out.println("<<< " + rsp);
					if(rsp.equals("bye")) {
						scanner.close();
						break;
					}
				}
			}
		}
		socket.close();
		System.out.println("disconnected.");
	}
}

class ServerHandler extends Thread{
	Socket socket;
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try (InputStream is = socket.getInputStream()){
			try (OutputStream os = socket.getOutputStream()){
				handle(is, os);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("client disconnected");
		}
	}

	private void handle(InputStream is, OutputStream os) throws IOException {
		var reader = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
		var writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
		writer.write("hello\n");
		writer.flush();
		for(;;) {
			String s = reader.readLine();
			if(s.equals("bye")) {
				writer.write("bye\n");
				writer.flush();
				break;
			}
			writer.write("ok " + s + "\n");
			writer.flush();
		}
	}
}
