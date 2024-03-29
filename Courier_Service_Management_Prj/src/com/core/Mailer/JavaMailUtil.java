package com.core.Mailer;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.core.common.ApplicationConstants;

public class JavaMailUtil {
	public static void sendMail(String recepient,String otp) throws Exception {
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		properties.put(ApplicationConstants.SMTPAuthentication,"true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String myAccountEmail="naffy13325@gmail.com";
		String password="SHADOW13325!";
		
		Session session=Session.getInstance(properties,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail,password);
			}
		});
		
		Message message = prepareMessage(session,myAccountEmail,recepient,otp);
		Transport.send(message);
		System.out.println("Message sent successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String otp) {
		
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Courier Email");
			message.setText("Your One Time Password is "+otp);
			System.out.println(otp);
			return message;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
