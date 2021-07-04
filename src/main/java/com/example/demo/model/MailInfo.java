package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MailInfo {
	String from="dunglvph11016@fpt.edu.vn";
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;
	public MailInfo(String to, String subject, String body) {
		
		this.to = to;
		this.subject = subject;
		this.body = body;
		}

}
