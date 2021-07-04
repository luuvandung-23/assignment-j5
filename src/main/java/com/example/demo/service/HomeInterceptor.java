package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HomeInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
	HttpServletResponse response, Object handler) throws Exception {
	
	return true;
	}
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
	Object handler, ModelAndView mv) throws Exception {
		
	}
}
