package com.bulletproof.onlineshop.core.dao;

public interface AbstractDao<T> {

	void create(T t);	
	T get(String ID);
	void update(T t);
	void delete(String ID);
	
}
