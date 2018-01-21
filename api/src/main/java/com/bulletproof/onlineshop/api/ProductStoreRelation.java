package com.bulletproof.onlineshop.api;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Relationship between product and store is modelled as 1..* instead of *..* to
 * reduce the number of records.
 */
@EqualsAndHashCode(of= {"sku"})
public class ProductStoreRelation {
	private String sku;
	private List<StoreQuantity> storeQuantity = new ArrayList<>();

	@Getter
	@Setter
	@EqualsAndHashCode
	public static class StoreQuantity {
		private String storeId;
		private String quantity;
	}

}
