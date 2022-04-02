package com.pos.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pos.admin.dto.OrderListDto;
import com.pos.admin.entity.Order;

public interface OrderService {

	public String deleteOrder(Long orderId);

	public String updateOrder(Long orderId, Order orderUpdated);

	public ResponseEntity<String> addOrder(Long customerId, Long addressId, Order order);

	public Order getOrderById(Long id);

	public List<Order> getAllOrder();

	public Long getOrderId(Long userId);

	ResponseEntity<OrderListDto> getOrders(Long customerId);
}
