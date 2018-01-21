package com.bulletproof.onlineshop.core.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bulletproof.onlineshop.api.Store;
import com.bulletproof.onlineshop.core.dao.StoreDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 *Defines endpoints for store management.
 * 
 * @author Anjith
 *@since 1.0.0
 */
@RestController
@RequestMapping("/stores")
@Api(value="Store Management")
public class StoreService {

	
	private StoreDao sdao;
	


	@Autowired 
	public StoreService(StoreDao pdao) {
		this.sdao = pdao;
	}
	
	/**
	 * Creates a store.
	 *
	 * @param store
	 */
	@RequestMapping( method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a store",response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a store",
            		responseHeaders= {@ResponseHeader(name="Location")}),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
	public ResponseEntity<Store> create(@RequestBody Store store) {
		// we can do validations before persitence
				//set an id
				store.setId(UUID.randomUUID().toString());
	
				sdao.create(store);
				
				HttpHeaders headers = new HttpHeaders();
			    headers.add("Content-Type", "application/json");
			    
			    //can be improved to construct URI  using uribuilding scheme	    
			    headers.add("Location", "http://onlineshop.com/core/stores/"  + store.getId());
			    return new ResponseEntity<Store>(store, headers, HttpStatus.CREATED);
	}

	/**
	 * Get a store based on ID.
	 * 
	 * @param id
	 * @return store
	 */
	@ApiOperation(value = "Get a store",response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a store"),
            @ApiResponse(code = 400, message = "Invalid store ID")
    })
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Store> get(@PathVariable("id") String id) {
	return null;	
	}

	/**
	 * Update a given store completely.
	 * 
	 * @param store
	 */
	@ApiOperation(value = "Update a store",response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated a store"),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Store store) {
		return null;
	}

	/**
	 * Patch a given store.
	 * 
	 * 
	 * @param store
	 * @return store
	 */
	@ApiOperation(value = "Patch a store",response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully patched a store"),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
	@RequestMapping(value="/{id}", method = RequestMethod.PATCH, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> patch(@PathVariable("id") String id, Store store) {
		return null;
	}

	/**
	 * Deletes a given store based on ID.
	 * 
	 * 
	 * @param id
	 */
	@ApiOperation(value = "Delete a store",response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a store"),
            @ApiResponse(code = 400, message = "Invalid store ID")
    })
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return null;
	}
}
