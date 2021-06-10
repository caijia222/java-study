package network.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

/**
 * RMI是Remote Method Invocation的缩写。 RMI远程调用是指，一个JVM中的代码可以通过网络实现远程调用另一个JVM的某个方法
 *
 */
public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		// 连接到服务器localhost，端口1099:
		System.out.println("连接服务器>>>");
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		// 查找名称为"WorldClock"的服务并强制转型为WorldClock接口:
		WorldClock worldClock = (network.rmi.WorldClock) registry.lookup("WorldClock");
		// 正常调用接口方法:
		LocalDateTime localDateTime = worldClock.getLocalDateTime("Asia/Shanghai");
		System.out.println(localDateTime);
	}

}
