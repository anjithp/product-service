package com.bulletproof.onlineshop.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@JsonInclude(Include.NON_NULL)
public abstract class BaseResource {
	
	protected String id;
	protected String name;
	protected String description;
	protected long lastUpdatedTime;
	

}
