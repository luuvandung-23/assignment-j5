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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaceDAO.OrderDAO;
import com.example.demo.interfaceDAO.OrderDetailDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

@Controller
@RequestMapping("/billManagement")
public class BillManagementController {
	@Autowired
	OrderDetailDAO orderdetaildao;
	@Autowired
	OrderDAO orderdao;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;

	@GetMapping
	public String form(@RequestParam(name = "id", defaultValue = "-1") Long idorder,
			@RequestParam("trang") Optional<Integer> page,
			@RequestParam("status")  Optional<Integer> status ,
			@RequestParam(name = "typesort", defaultValue = "DESC") String type,
			
			@RequestParam(name = "typesort_page", defaultValue = "DESC") String typesort_page) {
		
		List<Integer> listtrang = new ArrayList<>();
		Account account = (Account) session.getAttribute("account");
		Pageable pageable = PageRequest.of(page.orElse(0), 8, Sort.by(Sort.Direction.valueOf(type), "id"));
		Page<Order> listorder = orderdao.findAll(pageable);
		if (idorder > -1) {
			List<OrderDetail> dshdct = orderdetaildao.findByOrder(idorder);
			request.setAttribute("cart", dshdct);
			int tong = 0;
			for (OrderDetail x : dshdct) {
				tong = (int) (tong + x.getQuantity() * x.getPrice());
			}
			request.setAttribute("thanhtien", tong);
			if(status.orElse(null)!=null) {
				Order order= orderdao.getOne(idorder);
				order.setStatus(status.orElse(0));
				orderdao.save(order);}
		}
		
		request.setAttribute("page", page.orElse(0));
		request.setAttribute("dshd", listorder);
		request.setAttribute("typeSort", type.equals("DESC") ? "ASC" : "DESC");
		request.setAttribute("typesort_page", type);
		for (int i = 1; i <= listorder.getTotalPages(); i++) {
			listtrang.add(i);
		}
		request.setAttribute("listtrang", listtrang);
		return "qlhoadon";
	}

}
