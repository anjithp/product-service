package com.bulletproof.onlineshop.core.dao;

import java.util.List;

import com.bulletproof.onlineshop.api.Product;


public interface ProductDao  extends AbstractDao<Product>{
	List<Product> findByStore(String storeId, int pageNo, int pageSize);
}
