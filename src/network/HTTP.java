package network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.util.IOUtils;
import org.junit.Test;


/**
 * HTTP就是目前使用最广泛的Web应用程序使用的基础协议，例如，浏览器访问网站，手机App访问后台服务器，都是通过HTTP协议实现的。
 * HTTP是HyperText Transfer Protocol的缩写，翻译为超文本传输协议，它是基于TCP协议之上的一种请求-响应协议。
 * HTTP/1.0协议，每次发送一个HTTP请求，客户端都需要先创建一个新的TCP连接，然后，收到服务器响应后，关闭这个TCP连接。
 * HTTP/1.1协议允许在一个TCP连接中反复发送-响应，但等待服务器响应后，才能发送下一个请求。
 * HTTP/2.0允许客户端在没有收到响应的时候，发送多个HTTP请求。
 *
 */
public class HTTP {
	
	/**
	 * 早期的JDK版本是通过HttpURLConnection访问HTTP，典型代码如下
	 */
	@Test
	public void oldGetRequest() throws Exception {
		URL url = new URL("http://www.youdao.com/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.setConnectTimeout(5000);
		// 设置HTTP头
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 11; Windows NT 5.1)");
		// 连接并发送请求
		System.out.println("发送请求");
		conn.connect();
		// 判断HTTP响应是否200
		if(conn.getResponseCode() != 200) {
			throw new RuntimeException("bad response");
		}
		System.out.println("成功响应");
		// 获取所有响应头
		Map<String, List<String>> map = conn.getHeaderFields();
		System.out.println("开始打印响应头信息");
		map.forEach((key,value) -> System.out.println(key + ": " + value));
		System.out.println("结束打印响应头信息");
		// 获取响应内容
		InputStream is = conn.getInputStream();
		StringWriter sw = new StringWriter();
		IOUtils.copy(new InputStreamReader(is), sw);
		System.out.println("开始打印响应体信息");
		System.out.println(sw.toString());
		System.out.println("结束打印响应体信息");
	}
	
	/**
	 * Java 11开始，引入了新的HttpClient，它使用链式调用的API，能大大简化HTTP的处理
	 * @throws Exception 
	 */
	@Test
	public void getRequest() throws Exception {
		HttpClient httpClient = HttpClient.newHttpClient();
		String url = "http://www.youdao.com/";
		HttpRequest request = HttpRequest.newBuilder(new URI(url))
			// 设置Header
			.header("User-Agent", "Java HttpClient").header("Accept", "*/*")
			// 设置超时
			.timeout(Duration.ofSeconds(5))
			// 设置版本
			.version(Version.HTTP_2).build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		// HTTP允许重复的Header，因此一个Header可以对应多个Value
		System.out.println("开始打印响应头");
		response.headers().map().forEach((key,value)->System.out.println(key + ": " + value));
		System.out.println("结束打印响应头");
		System.out.println("开始打印响应体");
		System.out.println(response.body());
		System.out.println("结束打印响应体");
	}

	@Test
	public void postRequest() throws Exception {
		HttpClient httpClient = HttpClient.newHttpClient();
		String url = "http://www.example.com/login";
		String body = "username=bob&password=123456";
		HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
			.header("Accept", "*/*").header("Content-Type", "application/x-www-form-urlencoded")
			.timeout(Duration.ofSeconds(5))
			.version(Version.HTTP_2)
			.POST(BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
		HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());
		System.out.println(response.body());
	}
}
