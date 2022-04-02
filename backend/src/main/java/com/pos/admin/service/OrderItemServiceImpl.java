package com.pos.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pos.admin.dao.OrderDao;
import com.pos.admin.dao.OrderItemDao;
import com.pos.admin.dao.ProductDao;
import com.pos.admin.entity.Order;
import com.pos.admin.entity.OrderItem;
import com.pos.admin.entity.Product;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	@Autowired 
	private OrderServiceImpl orderServiceImpl;
	@Override
	public ResponseEntity<String> addItems(Long orderId, Long productId, OrderItem orderItem) {
		// TODO Auto-generated method stub
		ResponseEntity<String> response=null;
		if(!orderDao.existsById(orderId))
		{
			response=new ResponseEntity<String>("Order Id Not Found!",new HttpHeaders(),HttpStatus.OK);
		}
		if(!productDao.existsById(productId))
		{
			response=new ResponseEntity<String>("Product Id Not Found!",new HttpHeaders(),HttpStatus.OK);
		}
		Order order=orderDao.findById(orderId).get();
		orderItem.setOrder(order);
		Product product=productDao.findById(productId).get();
		
		orderItem.setProduct(product);
		if(order.getDiscount()==5.5)
		{
			orderItem.setPrice((((product.getMrp()*orderItem.getQuantity())-order.getDiscount())+product.getTax()));
		}
		else {
		orderItem.setPrice((product.getMrp()*orderItem.getQuantity())+product.getTax());
		}
		orderItemDao.save(orderItem);
	
		productDao.updateQuantity(product.getStock() - orderItem.getQuantity(), productId);
		Double sum=orderItemDao.getSumByOrderId(orderId);
		System.out.println(sum);
		String res=orderServiceImpl.updateTotalPrice(sum, orderId);
		System.out.println(res);
		response=new ResponseEntity<String>("Items added successfully!",new HttpHeaders(),HttpStatus.OK);
		return response;
	}
	@Override
	public ResponseEntity<List<OrderItem>> getOrderedItems(Long orderId) {
		// TODO Auto-generated method stub
		List<OrderItem> orderedItemsList=orderItemDao.getOrderedItems(orderId);
		return new ResponseEntity<>(orderedItemsList,new HttpHeaders(),HttpStatus.OK);
	}
	
	

}
