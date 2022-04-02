package com.pos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.admin.entity.OrderItem;
import com.pos.admin.service.OrderItemService;

@RestController
@RequestMapping("/api/order-item")
@CrossOrigin("http://localhost:4200")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping("/order/{orderId}/product/{productId}/add-item")
	public ResponseEntity<String> addItems(@PathVariable("orderId") Long orderId,@PathVariable("productId") Long productId,@RequestBody OrderItem orderItem)
	{
		return orderItemService.addItems(orderId,productId,orderItem);
	}
	
	@GetMapping("/get-items/{orderId}")
	public ResponseEntity<List<OrderItem>> getOrderedItems(@PathVariable("orderId") Long orderId)
	{
		return orderItemService.getOrderedItems(orderId);
	}

}
