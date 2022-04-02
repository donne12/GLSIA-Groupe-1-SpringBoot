package com.pos.admin.dto;

import java.util.List;

import com.pos.admin.entity.Order;

public class OrderListDto {
	private List<Order> orderList;

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> list) {
		this.orderList = list;
	}
	
}
