package com.example.demo.interfaceDAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1 and o.available=1")
	Page<Product> findByKeywords(String keywords, Pageable pageable);
	
	@Query("SELECT o FROM Product o WHERE o.category.id = ?1 and o.available=1 ")
	Page<Product> findByCategory(String idCategory, Pageable pageable);
	
	
	
}
