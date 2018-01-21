package com.bulletproof.onlineshop.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class Address {

	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	
	public Address() {
		
	}
	
}
