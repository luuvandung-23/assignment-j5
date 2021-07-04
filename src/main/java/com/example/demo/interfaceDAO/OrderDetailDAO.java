package com.example.demo.interfaceDAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.ThongKe;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	@Query("SELECT o FROM OrderDetail o WHERE o.order.id = ?1")
	List<OrderDetail> findByOrder(Long id);
	
	@Query("SELECT new ThongKe(o.product, sum(o.quantity), sum(o.quantity*o.price)) "
			+ " FROM OrderDetail o where year(o.order.createDate) = ?1 and month(o.order.createDate) = ?2 and o.order.status = 3 "
			+ " GROUP BY o.product")
			List<ThongKe> getThongkeTungThang(Integer nam , Integer thang);
	@Query("SELECT new ThongKe(o.product, sum(o.quantity), sum(o.quantity*o.price)) "
			+ " FROM OrderDetail o where year(o.order.createDate) = ?1   and o.order.status = 3"
			+ " GROUP BY o.product")
			List<ThongKe> getThongkeTungNam(Integer nam);
}
