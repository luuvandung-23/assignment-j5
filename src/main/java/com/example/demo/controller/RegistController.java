package com.example.demo.controller;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.interfaceDAO.AccountDAO;
import com.example.demo.model.Account;
import com.example.demo.service.MailerServiceImpl;
import com.example.demo.service.UploadController;

@Controller
@RequestMapping("register")
public class RegistController {
	@Autowired
	HttpServletRequest rq;
	@Autowired
	MailerServiceImpl mailer;
	@Autowired
	UploadController upload;
	@Autowired
	AccountDAO accountdao;
	@Autowired
	HttpSession session;
	@GetMapping
	public String dangki(@ModelAttribute("item") Account account) {
		return "registration";
	}
	
	
	@PostMapping
	public String create( @RequestParam("image") MultipartFile image, @Valid @ModelAttribute("item") Account account,BindingResult result) throws MessagingException, IllegalStateException, IOException {
		if (result.hasErrors()) {
			return "registration" ;
		} else {
			Optional<Account> account2 = accountdao.findById(account.getUsername());
			if(account2.orElse(null)!=null) {
				rq.setAttribute("error", "Tài khoản đã tồn tại !");
				return "registration" ;
				}
				account.setPhoto(image.getOriginalFilename());
				account.setActivated(true);
				accountdao.save(account);
				upload.save(image);
				rq.setAttribute("message", "Đăng kí thành công ! Mời bạn đăng nhập");
				mailer.send(account.getEmail(),"Đăng kí tài khoản", "Bạn vừa đăng kí thành công tài khoản tại Web Shop với tài khoản là : "+account.getUsername());
			}
		
			return "forward:/login/false" ; 
		}
		
		
	
}
