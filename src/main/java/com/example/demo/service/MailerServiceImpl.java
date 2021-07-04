package com.example.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.model.MailInfo;
@Service
public class MailerServiceImpl {
	@Autowired
	JavaMailSender sender;
	List<MimeMessage> queue = new ArrayList<>();
	public void send(MailInfo mail) throws MessagingException {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
		helper.setReplyTo(mail.getFrom());
		String[] attachments = mail.getAttachments();
		if(attachments != null && attachments.length > 0) {
		for(String path: attachments) {
		File file = new File(path);
		helper.addAttachment(file.getName(), file);
		}
		}
		// Gửi message đến SMTP server
		queue.add(message);
		}
	public void send(String to, String subject, String body)
	throws MessagingException {
	this.send(new MailInfo(to, subject, body));
	}
	@Scheduled(fixedDelay = 1000)
	public void run() {
		while(!queue.isEmpty()) {
			MimeMessage message = queue.remove(0);
			sender.send(message);
		}
	}

}
