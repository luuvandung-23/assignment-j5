package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.interfaceDAO.ProductDAO;
import com.example.demo.interfaceDAO.ShoppingCartService;
import com.example.demo.model.Product;

@SessionScope
@Service
public class CartService  implements ShoppingCartService {
	Map<Integer, Product> map = new HashMap<>();
	@Autowired
	ProductDAO productdao;
	@Override
	public void add(Integer id, Integer soluong) {
		Product item = new Product();
		if(map.get(id)!=null) {
		item = map.get(id);
		item.setAmount(map.get(id).getAmount()+soluong);
		map.put(id, item);
		}
		else {
			item = productdao.getOne(id);
			item.setAmount(soluong);
			map.put(id, item);
		}
	}
	@Override
	public void remove(Integer id) {
	map.remove(id);
	}
	@Override
	public Product update(Integer id, int qty) {
		Product item = new Product();
		item = map.get(id);
		item.setAmount(qty);
		map.put(id, item);
	return item;
	}
	@Override
	public void clear() {
	map.clear();
	}
	@Override
	public Collection<Product> getItems() {
	return map.values();
	}
	@Override
	public int getCount() {
	return 0;
	}
	@Override
	public double getAmount() {
	double tongtien = 0;
	for(Product x : map.values()) {
		tongtien = tongtien +(x.getPrice()*x.getAmount());
	}
	return tongtien;
	}
}
