package com.bulletproof.onlineshop.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a product.
 * 
 * @author Anjithkumar Paila
 * @since 1.0.0
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@JsonInclude(Include.NON_NULL)
public class Product extends BaseResource{

	
	private String sku;
	private ProductType type;
	private double price;
	private String imagePath;
	
	
	//product attributes
	private Map<String,String> attributes = new HashMap<>();
	
	private List<String> storeIds = new ArrayList<>();
	
	
	public Product() {
		
	}
	
}