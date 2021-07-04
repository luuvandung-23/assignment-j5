package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaceDAO.CategoryDAO;
import com.example.demo.interfaceDAO.ProductDAO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	
	@GetMapping
	public String index(@RequestParam("idDm") Optional<String> madm, @RequestParam("trang") Optional<Integer> trang) {
		List<Integer> listtrang = new ArrayList<>();
		Page<Product> listsp = null;
		String iddm = madm.orElse("");
		List<Category> listdm = categoryDAO.findAll();
		Pageable pageable = PageRequest.of(trang.orElse(0), 8);
		if(session.getAttribute("tim")==null) {
		if (iddm == "" && session.getAttribute("idDm")==null) {
			listsp = productDAO.findByKeywords("%", pageable);
		}else if(iddm == "" && session.getAttribute("idDm")!=null ){
			listsp = productDAO.findByCategory(session.getAttribute("idDm").toString(), pageable);
		}else if(iddm!="" && iddm!="all") {
			session.setAttribute("idDm",iddm);
			listsp = productDAO.findByCategory(session.getAttribute("idDm").toString(), pageable);
		}
		else {
			 listsp = productDAO.findByKeywords("%" +session.getAttribute("tim")+ "%", pageable);
		}}else {
			listsp = productDAO.findByKeywords("%" +session.getAttribute("tim")+ "%", pageable);
		}
		request.setAttribute("listsp", listsp);
		for (int i = 1; i <= listsp.getTotalPages(); i++) {
			listtrang.add(i);
		}
		request.setAttribute("page", trang.orElse(0));
		request.setAttribute("listtrang", listtrang);
		session.setAttribute("listdm", listdm);
		return "dssanpham";
	}
	@GetMapping("all")
	public String index2() {
		session.setAttribute("tim",null);
		session.setAttribute("idDm",null);
		return "redirect:/home";
	}
	@PostMapping
	public String Tim(@RequestParam("tim") Optional<String> tim, @RequestParam("trang") Optional<Integer> trang) {
		List<Integer> listtrang = new ArrayList<>();
		Pageable pageable = PageRequest.of(trang.orElse(0), 8);
		String timkiem = tim.orElse("");
		session.setAttribute("tim", timkiem);
		Page<Product> listsp = productDAO.findByKeywords("%" +tim.orElse("")+ "%", pageable);
		for (int i = 1; i <= listsp.getTotalPages(); i++) {
			listtrang.add(i);
		}
		request.setAttribute("page", trang.orElse(0));
		request.setAttribute("listtrang", listtrang);
		request.setAttribute("listsp", listsp);
		return "dssanpham";
	}

}
