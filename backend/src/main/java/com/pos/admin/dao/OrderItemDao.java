package com.pos.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pos.admin.entity.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem,Long>{
	
	@Query("SELECT sum(o.price) FROM OrderItem o WHERE o.order.orderId=:orderId")
	Double getSumByOrderId(@Param("orderId") Long orderId);
	
	@Query("FROM OrderItem o WHERE o.order.orderId=:orderId")
	List<OrderItem> getOrderedItems(@Param("orderId") Long orderId);
	
}
