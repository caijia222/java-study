package encryption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import org.junit.Test;

//import ch.qos.logback.core.encoder.ByteArrayUtil;

/**
 * @author 93652
 * 哈希(Hash)算法，又称摘要(Digest)算法
 */
public class HashAlgorithm {

	/**
	 * 报文签名密钥5bb7d8e1996d4de3854d8495a95e2922
	 * 报文加密秘钥bc324d7bd7424be0ab8d7023
	 */
	@Test
	public void test1() {
		System.out.println(Integer.toHexString("hello".hashCode()));
		System.out.println(Integer.toHexString("AaAaAa".hashCode()));
		System.out.println(Integer.toHexString("BBAaBB".hashCode()));
	}
	
	@Test
	public void test2() throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update("Hello".getBytes(StandardCharsets.UTF_8));
		md.update("World".getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
//		System.out.println(ByteArrayUtil.toHexString(digest));
		System.out.println(Arrays.toString(digest));
		System.out.println(new BigInteger(1, digest).toString());
	}
}
