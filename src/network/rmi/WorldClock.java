package network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

/**
 * RMI是Remote Method Invocation的缩写。
 * RMI远程调用是指，一个JVM中的代码可以通过网络实现远程调用另一个JVM的某个方法
 *
 */
interface WorldClock extends Remote{
	LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;
}