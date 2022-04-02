package com.pos.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pos.admin.entity.OrderItem;

public interface OrderItemService {
	public ResponseEntity<String> addItems(Long orderId,Long productId,OrderItem orderItem);
	public ResponseEntity<List<OrderItem>> getOrderedItems(Long orderId);
}
