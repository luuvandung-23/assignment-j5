package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Account;

@Service
public class AccountInterceptor implements HandlerInterceptor {
	@Autowired
	HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		Account user = (Account) session.getAttribute("account");
		String error = "";
		if (user == null) {
			error = "Please login";
		} else if (!user.isRole() == true && uri.startsWith("/billManagement")
				|| !user.isRole() == true && uri.startsWith("/managerproduct")
				|| !user.isRole() == true && uri.startsWith("/thongke")) {
			error = "Access denied!";
		}
		if (error.length() > 0) {
			response.sendRedirect("/login?error=" + error);
			return false;
		}
		return true;
	}

}
