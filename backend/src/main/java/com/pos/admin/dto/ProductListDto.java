package com.pos.admin.dto;

import java.util.List;

import com.pos.admin.entity.Product;

public class ProductListDto {
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
