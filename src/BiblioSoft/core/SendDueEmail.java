package BiblioSoft.core;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * Send one Email to reader
 * @author Hu Yuxi
 *
 */
public class SendDueEmail {

	public static void SendDueEmail(String mail, String book_name, String reader_name, String date) throws AddressException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");// 连接协议
		properties.put("mail.smtp.host", "smtp.126.com");// 主机名
		properties.put("mail.smtp.port", 25);// 端口号
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
		properties.put("username","bibliosoftlib@126.com");

		// 得到回话对象
		Session session = Session.getInstance(properties);
		// 获取邮件对象
		Message message = new MimeMessage(session);
		// 设置发件人邮箱地址
		message.setFrom(new InternetAddress("bibliosoftlib@126.com"));
		// 设置收件人地址 
		message.setRecipients( RecipientType.TO, new InternetAddress[] { new InternetAddress(mail) });
		//防止脚本检测
		message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(properties.getProperty("username")));
		// 设置邮件标题
		message.setSubject("Bibliosoft:Return due reminder");
		// 设置邮件内容
		message.setText("Dear "+reader_name+":\r\n" + 
				"The book you borrowed "+ book_name +" should be returned by "+date+", please pay attention to returning it in time, thank you!");
		// 得到邮差对象
		Transport transport = session.getTransport();
		// 连接自己的邮箱账户
		transport.connect("bibliosoftlib@126.com", "bibliosofta6");// 密码为刚才得到的授权码
		// 发送邮件 
		transport.sendMessage(message, message.getAllRecipients());	
		transport.close();
	}

}
