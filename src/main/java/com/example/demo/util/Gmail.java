//package com.example.demo.util;
//
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class Gmail {
//
//	// Google應用程式密碼
//	String googleAppPassword = "emqb npgc nbgt znt";
//
//	// 寄件者的電子郵件地址
//	String from = "yun96417@gmail.com";
//
//	// to: // 收件者的電子郵件地址
//	public void sendEmail(String to, String subject, String contentUrl) {
//
//		// 使用 Gmail SMTP 伺服器
//		String host = "smtp.gmail.com";
//
//		// 設定屬性
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.connectiontimeout", "10000"); // 連線超時設定
//		properties.put("mail.smtp.timeout", "10000"); // 整體超時設定
//		properties.put("mail.smtp.writetimeout", "10000"); // 寫入超時設定
//
//		// 建立會話物件，並提供驗證資訊
//		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(from, googleAppPassword);
//			}
//		});
//
//		try {
//			// 建立一個預設的 MimeMessage 物件
//			Message message = new MimeMessage(session);
//			// 設定寄件者
//			message.setFrom(new InternetAddress(from));
//			// 設定收件者
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//			// 設定郵件標題
//			message.setSubject(subject);
//			// 設定內容為 HTML 格式
//			message.setContent(contentUrl, "text/html; charset=utf-8");
//			// 傳送郵件
//			Transport.send(message);
//			// 發送成功
//			System.out.println("郵件發送成功: " + to);
//		} catch (MessagingException e) {
//			// 發送失敗
//			System.out.println("發送失敗: " + e.getMessage());
//		}
//	}
//
//}
