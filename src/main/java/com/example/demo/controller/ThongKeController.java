package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaceDAO.OrderDAO;
import com.example.demo.interfaceDAO.OrderDetailDAO;
import com.example.demo.interfaceDAO.ProductDAO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Product;
import com.example.demo.model.ThongKe;

@Controller
@RequestMapping("thongke")
public class ThongKeController {
	@Autowired 
	ProductDAO productdao;
	@Autowired 
	OrderDetailDAO orderdetail;
	@Autowired 
	OrderDAO orderdao;
	@Autowired 
	HttpServletRequest rq;
	@GetMapping
	public String index(@RequestParam("year") Optional<Integer> nam) {
		Pageable pageable = PageRequest.of(0, 1);
		List<Integer>  nammax = orderdao.nammax(pageable);
		List<Integer>  nammin = orderdao.nammin(pageable);
		rq.setAttribute("nammax", nammax);
		rq.setAttribute("nammin", nammin);
		List<Product>  sp= productdao.findAll();
		List<Integer> cacnam=orderdao.nam(); 
		rq.setAttribute("cacnam", cacnam);
		rq.setAttribute("sp", sp);
		Long sosp = productdao.count();
		rq.setAttribute("so", sosp);
		List<List<String>>  map = new ArrayList<>();
		Double tongtien = (double) 0;
		List<ThongKe> thongkenam = orderdetail.getThongkeTungNam(nam.orElse(nammax.get(0)));
		List<String> tongsl = new ArrayList<>();
		Integer b = 0;
		for(int j =0 ; j<sosp ; j++) {
			if(b<=thongkenam.size()-1 && thongkenam.get(b).getProduct().getId()==sp.get(j).getId()) {
				tongsl.add(thongkenam.get(b).getQuantity()+"");
				b++;
			}else {
				tongsl.add(0+"");
			}
		}
		rq.setAttribute("tongsl", tongsl);
		for(int i = 1; i<=12;i++) {
			List<String> listso= new ArrayList<>();
			List<ThongKe> thongkethang = orderdetail.getThongkeTungThang(nam.orElse(2021), i);
			Double tong = 0.0;
			Integer a = 0;
			listso.add(i+"");
				for(int j =0 ; j<sosp ; j++) {
					if(a<=thongkethang.size()-1 && thongkethang.get(a).getProduct().getId()==sp.get(j).getId()) {
						listso.add(thongkethang.get(a).getQuantity()+"");
						tong = tong +thongkethang.get(a).getAmount();
						a++;
					}else {
						listso.add(0+"");
					}
				}
				tongtien = tongtien+tong;
			listso.add(tong+"");
			map.add(listso);
		}
	rq.setAttribute("tongtien", tongtien);
		rq.setAttribute("cactruong", map);
		rq.setAttribute("year", nam.orElse(nammax.get(0)));
		return "thongke";
	}
	@GetMapping("detail")
	public String detail(@RequestParam("year") Optional<Integer> nam , @RequestParam("month") Optional<Integer> thang,@RequestParam("tong") Optional<String> tong) {
		Long sosp = productdao.count();
		rq.setAttribute("so", sosp);
		List<ThongKe> thongke = orderdetail.getThongkeTungThang(nam.orElse(2021), thang.orElse(1));
		rq.setAttribute("thongke", thongke);
		rq.setAttribute("year", nam.orElse(2021));
		rq.setAttribute("month", thang.orElse(1));
		rq.setAttribute("tong", tong.orElse("0"));
		return "thongkethang";
	}
}
