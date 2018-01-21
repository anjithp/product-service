package com.bulletproof.onlineshop.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@JsonInclude(Include.NON_NULL)
public class Store extends BaseResource {
	
	private double longitude;
	private double latitude;
	private Address address;
	
	public Store() {
		
	}

}
