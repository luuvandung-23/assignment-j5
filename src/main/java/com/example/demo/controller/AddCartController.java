package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaceDAO.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Product;
import com.example.demo.service.MailerServiceImpl;
import com.example.demo.interfaceDAO.*;

@Controller
public class AddCartController {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	OrderDAO orderdao;
	@Autowired
	OrderDetailDAO orderdetaildao;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	@Autowired
	MailerServiceImpl mailer;
	@Autowired
	ShoppingCartService cart; 
	@GetMapping("inforPro")
	public String index() {
		Product product = productDAO.getOne(Integer.valueOf(request.getParameter("msp")));
		request.setAttribute("sp", product);
		return "thongtinsp";
	}
	@GetMapping("cart/view")
	public String index1(Model model) {
		model.addAttribute("cart", cart.getItems());
		model.addAttribute("thanhtien",cart.getAmount());
		return "giohang";
	}
	
	@PostMapping("/cart/add")
	public String add(@RequestParam("id") Integer id, @RequestParam("amount") String soluong) {
		if(soluong!="") {
			cart.add(id, Integer.valueOf(soluong));
			return "redirect:/cart/view";
		}
		Product product = productDAO.getOne(id);
		request.setAttribute("sp", product);
		return "thongtinsp";
	}
	
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
	cart.remove(id);
	return "redirect:/cart/view";
	}
	@RequestMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id,
	@RequestParam("amount") String qty) {
	String mau = "[1-9][0-9]*";
	 if (qty.matches(mau)) {
		 cart.update(id, Integer.valueOf(qty));
	 }
	
	return "redirect:/cart/view";
	}
	
	@PostMapping("cart/order")
	public String order(@RequestParam("address") String diachi) throws MessagingException {
		//l???y t??i kho???n
		if(diachi=="" || cart.getItems().isEmpty()==true) {
			return "redirect:/cart/view";
		}
		Account account = (Account) session.getAttribute("account");
		//l???y m?? h??a ????n
		Order hd = new Order();
		hd.setAccount(account);
		hd.setAddress(diachi);
		hd.setStatus(0);
		//th??m v??o hoa ????n
		Order order = orderdao.save(hd);
		//l???y list product
		//them hoa ????n ct
		//String body = "Kh??ch h??ng :"+account.getFullname()+"\n?????a ch??? :"+ diachi +"\n" +"----id s???n ph???m-----t??n s???n ph???m-----gi??-----s??? l?????ng------T???ng ti???n---- \n";
		for(Product x: cart.getItems()) {
			//body = body + " ----"+x.getId()+"-----"+x.getName()+"-----"+x.getPrice()+"-----"+x.getAmount()+"------"+(x.getPrice()*x.getAmount())+"---- \n";
			OrderDetail orderdetail = new OrderDetail();
			 orderdetail.setPrice(x.getPrice());
			 orderdetail.setQuantity(x.getAmount());
			 orderdetail.setProduct(productDAO.getOne(x.getId()));
			 orderdetail.setOrder(order);
			 orderdetaildao.save(orderdetail);
		}
		//body =body + "Th??nh ti???n : "+cart.getAmount();
		// mailer.send(account.getEmail(), "????n h??ng ",body);
		//x??a s???ch gi??? h??ng
		cart.clear();
		//chuy???n ?????n qu???n l?? h??a ????n
		return "redirect:/hoadon?trang=0&typesort=DESC&id="+order.getId();
	}
	@PostMapping("cart/clear")
	public String clear() {
		cart.clear();
		return "redirect:/cart/view";
	}
}
