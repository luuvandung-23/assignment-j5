package com.example.demo.interfaceDAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;
import com.example.demo.model.Product;

public interface OrderDAO extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o WHERE o.account.username = ?1")
	Page<Order> findByUser(String username,Pageable pageable);
	
	@Query("SELECT DISTINCT year(o.createDate) as nam FROM Order o  ORDER BY nam DESC ")
	List<Integer> nam();
	@Query("SELECT DISTINCT year(o.createDate) as nam FROM Order o  ORDER BY nam DESC")
	List<Integer>  nammax(Pageable pageable);
	@Query("SELECT DISTINCT year(o.createDate) as nam FROM Order o  ORDER BY nam ASC")
	List<Integer>  nammin(Pageable pageable);
}
