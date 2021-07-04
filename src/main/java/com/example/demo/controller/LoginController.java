package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaceDAO.AccountDAO;
import com.example.demo.model.Account;

@Controller
public class LoginController {
	@Autowired
	AccountDAO accountdao;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	@GetMapping("/login")
	public String index(@RequestParam("error") Optional<String> error) {
		request.setAttribute("error", error.orElse(null));
		return "login";
	}
	@PostMapping("/login/false")
	public String index2() {
		return "login";
	}
	@PostMapping("login")
	public String index2(@RequestParam("username")String tk,@RequestParam("password")String mk) {
		
		try {
			Account account = accountdao.getOne(tk);
				if(!account.getPassword().equals(mk)) {
					request.setAttribute("error","Mật khẩu sai");
				}else {
					if(account.isActivated()==true) {
						session.setAttribute("account", account);
						return "redirect:/home";
					}
					else {
						request.setAttribute("error","Tài khoản đã bị tạm dừng ");
					}	
				}
				
		}catch (Exception e) {
			request.setAttribute("error","Tài khoản không tồn tại !");
			return "forward:/login/false";
		}
		return "forward:/login/false";
		
	}
	@GetMapping("/logout")
	public String index3() {
		session.setAttribute("account", null);
		return "redirect:/home";
	}
}