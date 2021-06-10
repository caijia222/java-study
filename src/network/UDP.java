package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Test;

/**
 * UDP没有创建连接，数据包也是一次收发一个，所以没有流的概念
 * 在Java中使用UDP编程，仍然需要使用Socket，UDP端口和TCP端口虽然都使用0~65535，但他们是两套独立的端口
 */
public class UDP {
	
	@Test
	public void udpServer() throws IOException {
		@SuppressWarnings("resource")
		DatagramSocket ds = new DatagramSocket(6666);
		System.out.println("udp server in running");
		for(;;) {
			byte[] reqData = new byte[1024];
			DatagramPacket packet = new DatagramPacket(reqData, reqData.length);
			ds.receive(packet);
			
			String reqStr = new String(packet.getData(),packet.getOffset(),packet.getLength(),StandardCharsets.UTF_8);
			byte[] rspData = ("ok " + reqStr).getBytes(StandardCharsets.UTF_8);
			packet.setData(rspData);
			ds.send(packet);;
		}
	}
	
	@Test
	public void udpClient() throws IOException {
		DatagramSocket ds = new DatagramSocket();
		ds.setSoTimeout(1000);
		ds.connect(InetAddress.getByName("localhost"), 6666);
		Scanner scanner = new Scanner(System.in);
		for(;;) {
			System.out.println(">>> ");
			String inputStr = scanner.next();
			
			byte[] reqData = inputStr.getBytes(StandardCharsets.UTF_8);
			DatagramPacket packet = new DatagramPacket(reqData, reqData.length);
			ds.send(packet);
			
			byte[] rspData = new byte[1024];
			packet.setData(rspData);
			ds.receive(packet);
			String rspStr = new String(packet.getData(),packet.getOffset(),packet.getLength(),StandardCharsets.UTF_8);
			System.out.println("<<< " + rspStr);
			
			if(rspStr.equals("ok bye")) {
				scanner.close();
				ds.disconnect();
				ds.close();
				System.out.println("udp client disconnected.");
				break;
			}
		}
	}
}
