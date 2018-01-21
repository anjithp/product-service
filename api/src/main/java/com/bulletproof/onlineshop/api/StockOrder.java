package com.bulletproof.onlineshop.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@JsonInclude(Include.NON_NULL)
public class StockOrder extends BaseResource {
	String storeId;
	StockOrderStatus status;
	
	List<ProductQuantity> requiredProducts = new ArrayList<>();
	
	@Getter
	@Setter
	public static class ProductQuantity {
		private String productId;
		private String quntity;
	}
	
	public enum StockOrderStatus {
		CREATED,INPROCESS,COMPLETE;
	}
}
