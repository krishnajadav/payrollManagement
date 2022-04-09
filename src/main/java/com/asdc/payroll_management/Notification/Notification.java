package com.asdc.payroll_management.Notification;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Notification {

	private String userEmail;
	private String message;
	private String subject;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Notification(String userEmail, String message, String subject) {
		super();
		this.userEmail = userEmail;
		this.message = message;
		this.subject = subject;
	}

	public Notification() {
		super();
	}

	public boolean sendEmail() {
		try {
			JavaMailSender emailObj = getJavaMailSender();
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("youremail");
			msg.setTo(userEmail);
			msg.setSubject(subject);
			msg.setText(message);
			emailObj.send(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("youremail");
		mailSender.setPassword("yourpassword");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}
