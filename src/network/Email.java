package network;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;

import org.junit.Test;

import com.sun.mail.pop3.POP3SSLStore;

/**
 * 类似Outlook这样的邮件软件称为MUA：Mail User Agent
 * 邮件服务器则称为MTA：Mail Transfer Agent
 * 最终到达的邮件服务器称为MDA：Mail Delivery Agent
 */
public class Email {
	
	/**
	 * MUA到MTA发送邮件的协议就是SMTP协议，它是Simple Mail Transport Protocol的缩写，使用标准端口25，也可以使用加密端口465或587<br>
	 * SMTP协议是一个建立在TCP之上的协议，任何程序发送邮件都必须遵守SMTP协议<br>
	 * QQ邮箱：SMTP服务器是smtp.qq.com，端口是465/587<br>
	 * 163邮箱：SMTP服务器是smtp.163.com，端口是465<br>
	 * Gmail邮箱：SMTP服务器是smtp.gmail.com，端口是465/587<br>
	 */
	@Test
	public void sendEmail() throws Exception {
		
		// 设置SMTP服务器所需参数
		String smtpUrl = "smtp.qq.com"; // 服务器地址
		String username = "936522591@qq.com"; // QQ邮箱用户名
		String password = "qlrxanelegyrbdhb"; // QQ邮箱授权码
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", smtpUrl); // SMTP主机名
		properties.setProperty("mail.smtp.port", "587"); // 主机端口号
		properties.setProperty("mail.smtp.auth", "true"); // 是否需要用户认证
		properties.setProperty("mail.smtp.starttls.enable", "true"); // 启用TLS加密
		
		// 获取Session实例
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true); // 设置debug模式，便于调试
		
		// 构造Message对象
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("936522591@qq.com")); // 发件地址
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("caijia@agree.com.cn")); // 收件地址
		message.setSubject("hello", "UTF-8"); // 邮件主题
		message.setText("Hi this is the first email from javax-mail", "UTF-8"); // 邮件正文
		
		// 发送邮件
		Transport.send(message); 
	}

	/**
	 * 接收邮件是MUA到MDA服务器抓取邮件到本地的过程<br>
	 * 接收邮件使用最广泛的协议是POP3：Post Office Protocol version 3，它也是一个建立在TCP连接之上的协议。
	 * POP3服务器的标准端口是110，如果整个会话需要加密，那么使用加密端口995。<br>
	 * 另一种接收邮件的协议是IMAP：Internet Mail Access Protocol，它使用标准端口143和加密端口993。<br>
	 * IMAP和POP3的主要区别是，IMAP协议在本地的所有操作都会自动同步到服务器上，并且，IMAP可以允许用户在邮件服务器的收件箱中创建文件夹。<br>
	 * @throws Exception 
	 */
	@Test
	public void receiveEmail() throws Exception {
		String host = "pop.qq.com";
		int port = 995;
		String username = "936522591@qq.com";
		String password = "qlrxanelegyrbdhb";
		
		Properties pros = new Properties();
		pros.setProperty("mail.store.protocol", "pop3"); // 协议名称
		pros.setProperty("mail.pop3.host", host); // POP3主机名
		pros.setProperty("mail.pop3.port", String.valueOf(port)); // 端口号
		
		// 启动SSL
		pros.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		pros.put("mail.smtp.socketFactory.port", String.valueOf(port));
		
		// 连接到Store
		URLName url = new URLName("pop3", host, port, "", username, password);
		Session session = Session.getDefaultInstance(pros, null);
		session.setDebug(true);
		Store store = new POP3SSLStore(session, url);
		store.connect();
		
		// 获取收件箱
		Folder folder = store.getFolder("INBOX");
		// 以读写方式打开
		folder.open(Folder.READ_WRITE);
		
		System.out.println("Total Message: " + folder.getMessageCount()); // 邮件总数
		System.out.println("New Message: " + folder.getNewMessageCount()); // 新邮件数量
		System.out.println("Unread Message: " + folder.getUnreadMessageCount()); // 未读邮件数量
		System.out.println("Deleted Message: " + folder.getDeletedMessageCount()); // 已删除邮件数量
		
		// 获取未读邮件
		FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
		Message[] messages = folder.search(ft);
		for (Message message : messages) {
			MimeMessage msg = (MimeMessage) message;
			// 邮件主题
			System.out.println("Subject: " + MimeUtility.decodeText(msg.getSubject()));
			// 发件人
			Address[] froms = msg.getFrom();
			InternetAddress address = (InternetAddress) froms[0];
			String personal = address.getPersonal();
			String from = personal == null ? address.getAddress() : (MimeUtility.decodeText(personal) + "<" + address.getAddress() + ">");
			System.out.println("From: " + from);
			
			// 获取邮件正文
			System.out.println("content: " + msg.getContent());
		}
		folder.close();
		store.close();
		
	}
}
