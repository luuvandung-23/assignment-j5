package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.service.AccountInterceptor;

@Configuration
public class ConfigInter implements WebMvcConfigurer{
	@Autowired
	AccountInterceptor khach;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(khach)
	.addPathPatterns("/thongke/**","/billManagement/**","/cart/order","/hoadon/**","/managerproduct/**");
	}
}
