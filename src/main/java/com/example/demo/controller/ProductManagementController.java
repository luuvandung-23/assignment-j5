package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.interfaceDAO.CategoryDAO;
import com.example.demo.interfaceDAO.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Category;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.service.UploadController;

@Controller
@RequestMapping("/managerproduct")
public class ProductManagementController {
	@Autowired
	HttpSession session;
	@Autowired
	UploadController upload;
	@Autowired
	HttpServletRequest rq;
	@Autowired
	CategoryDAO categorydao;
	@Autowired
	ProductDAO productdao;
	@RequestMapping
	public String get(Model model,@ModelAttribute("product") Product product, @RequestParam("page") Optional<Integer> trang,
			
			@RequestParam(name = "typesort", defaultValue = "ASC") String kieu,
			@RequestParam(name = "id", defaultValue = "-1") Integer masp,

			@RequestParam(name = "typesort_page", defaultValue = "ASC") String kieu_trang,
			
			@RequestParam(name = "properties", defaultValue = "id") String tencot,
			@RequestParam("available")  Optional<Boolean>  status
			) {
		if(masp>-1) {
			model.addAttribute("product", productdao.findById(masp).orElse(null));
			model.addAttribute("categoryID",productdao.getOne(masp).getCategory().getId());
			if(status.orElse(null)!=null) {
				Product pd = productdao.getOne(masp);
				pd.setAvailable(status.orElse(true));
				productdao.save(pd);
			}
		}
		List<Category> dm = categorydao.findAll();
		Pageable pageable = PageRequest.of(trang.orElse(0), 8, Sort.by(Sort.Direction.valueOf(kieu),tencot));
		Page<Product> listproduct = productdao.findAll( pageable);
		rq.setAttribute("categorys", dm);
		rq.setAttribute("listproduct", listproduct);
		List<Integer> listtrang = new ArrayList<>();
		for (int i = 1; i <= listproduct.getTotalPages(); i++) {
			listtrang.add(i);
		}
		rq.setAttribute("listtrang", listtrang);
		rq.setAttribute("typesort", kieu.equals("DESC") ? "ASC" : "DESC");
		rq.setAttribute("page", trang.orElse(0));
		rq.setAttribute("typesort_page", kieu);
		rq.setAttribute("properties", tencot);
		
		return "qlsanpham";
	}
	@PostMapping("clear")
	public String reset() {
		return "redirect:/managerproduct";
	}
	@PostMapping("insert")
	public String insert(@RequestParam("page") Optional<Integer> trang,
			@RequestParam("image") MultipartFile image,
			@RequestParam(name = "typesort_page", defaultValue = "ASC") String kieu_trang,
			@RequestParam(name = "properties", defaultValue = "id") String tencot,@RequestParam(name = "idDmSelect", defaultValue = "sp01") String dm,
			@Valid @ModelAttribute("product") Product product,BindingResult result
			) throws IllegalStateException, IOException {

		System.out.println(2);
		product.setCategory(categorydao.getOne(dm));
		product.setImage(image.getOriginalFilename());
		productdao.save(product);
		upload.save(image);
		return "redirect:/managerproduct?page="+trang.orElse(0)+"&typesort="+kieu_trang+"&properties="+tencot;
	}
	
}
