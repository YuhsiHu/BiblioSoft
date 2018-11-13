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
		properties.put("mail.transport.protocol", "smtp");// ����Э��
		properties.put("mail.smtp.host", "smtp.126.com");// ������
		properties.put("mail.smtp.port", 25);// �˿ں�
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");// �����Ƿ���ʾdebug��Ϣ true ���ڿ���̨��ʾ�����Ϣ
		properties.put("username","bibliosoftlib@126.com");

		// �õ��ػ�����
		Session session = Session.getInstance(properties);
		// ��ȡ�ʼ�����
		Message message = new MimeMessage(session);
		// ���÷����������ַ
		message.setFrom(new InternetAddress("bibliosoftlib@126.com"));
		// �����ռ��˵�ַ 
		message.setRecipients( RecipientType.TO, new InternetAddress[] { new InternetAddress(mail) });
		//��ֹ�ű����
		message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(properties.getProperty("username")));
		// �����ʼ�����
		message.setSubject("Bibliosoft:Return due reminder");
		// �����ʼ�����
		message.setText("Dear "+reader_name+":\r\n" + 
				"The book you borrowed "+ book_name +" should be returned by "+date+", please pay attention to returning it in time, thank you!");
		// �õ��ʲ����
		Transport transport = session.getTransport();
		// �����Լ��������˻�
		transport.connect("bibliosoftlib@126.com", "bibliosofta6");// ����Ϊ�ղŵõ�����Ȩ��
		// �����ʼ� 
		transport.sendMessage(message, message.getAllRecipients());	
		transport.close();
	}

}
