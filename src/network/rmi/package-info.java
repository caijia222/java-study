/**
 * RMI是Remote Method Invocation的缩写。 RMI远程调用是指，一个JVM中的代码可以通过网络实现远程调用另一个JVM的某个方法。
 * RMI通过自动生成stub和skeleton实现网络调用，客户端只需要查找服务并获得接口实例，服务器端只需要编写实现类并注册为服务；
 * RMI的序列化和反序列化可能会造成安全漏洞，因此调用双方必须是内网互相信任的机器，不要把1099端口暴露在公网上作为对外服务。
 */
package network.rmi;