package com.bulletproof.onlineshop.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bulletproof.onlineshop.api.Product;
import com.bulletproof.onlineshop.core.dao.ProductDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import io.swagger.annotations.ApiResponse;

/**
 * Defines endpoints for product catalog management and also can communicate with stores if there 
 * are any price changes of the products.
 * 
 * @author Anjith
 * @since 1.0.0
 */
@RestController
@RequestMapping("/products")
@Api(value = "Product Catalog Management")
@CrossOrigin(maxAge = 3600)
public class ProductService {

	private ProductDao pdao;

	private RabbitMessagingTemplate template;

	@Autowired 
	public ProductService(ProductDao pdao, RabbitMessagingTemplate template) {
		this.pdao = pdao;
		this.template = template;
	}

	/**
	 * Creates a product.
	 *
	 * @param product
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a product", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created a product", responseHeaders = {
			@ResponseHeader(name = "Location") }), @ApiResponse(code = 400, message = "Invalid input data") })
	public ResponseEntity<Product> create(@RequestBody Product product) {
		// we can do validations before persitence
		//set an id
		product.setId(UUID.randomUUID().toString());
		// generate and set sku or we can accept from user as well but we need to do unique sku validations
		product.setSku(UUID.randomUUID().toString());
		
		pdao.create(product);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
	    

	    //can be improved to construct URI  using uribuilding scheme	    
	    headers.add("Location", "http://onlineshop.com/core/products/"  + product.getId());
	    
	    return new ResponseEntity<Product>(product, headers, HttpStatus.CREATED);
	    
	}
	

	/**
	 * Patch a given product.
	 * 
	 * 
	 * @param product
	 * @return product
	 */
	@ApiOperation(value = "Patch a product", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully patched a product"),
			@ApiResponse(code = 400, message = "Invalid input data") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> patch(@PathVariable("id") String id, Product product) {
		
		// whenever the price changes we can inform all the stores. Inorder to achieve this, 
		// we need to use the pub-sub model of RabbitMQ(fan-out exchange model) so that price changes 
		//can be broadcasted to all the stores.
					
		//price change can be modelled as a separate class.
		template.convertAndSend("exchange-name", "routing-key", new Object());
				
		return null;
	}


	/**
	 * Update a given product completely.
	 * 
	 * @param product
	 */
	@ApiOperation(value = "Update a product", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated a product"),
			@ApiResponse(code = 400, message = "Invalid input data") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Product product) {
		
		// whenever the price changes we can inform all the stores. Inorder to achieve this, 
		// we need to use the pub-sub model of RabbitMQ(fan-out exchange model) so that price changes can be
		//broadcasted to all the stores.
		
		
		//price change can be modelled as a separate class.
		template.convertAndSend("exchange-name", "routing-key", new Object());
				
		return null;
	}


	
	/**
	 * Get a product based on ID.
	 * 
	 * @param id
	 * @return product
	 */
	@ApiOperation(value = "Get a product", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved a product"),
			@ApiResponse(code = 400, message = "Invalid product ID") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> get(@PathVariable("id") String id) {
		
		Product p = pdao.get(id);
		if( p!= null) {
		    return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/**
	 * Deletes a given product based on ID.
	 * 
	 * 
	 * @param id
	 */
	@ApiOperation(value = "Delete a product", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully deleted a product"),
			@ApiResponse(code = 400, message = "Invalid product ID") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return null;
	}

	/**
	 * Searches products by store location.
	 * 
	 * @return list of Products
	 */
	@ApiOperation(value = "Search products by store location. ", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved products") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> search(@RequestParam(value = "storeId", required = false) String storeId,
			@RequestParam(value = "longitude", required = false) String longitude,
			@RequestParam(value = "latitude", required = false) String latitude,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize) {
		List<Product> pl = new ArrayList<>();
		if(storeId == null) {
			//get store id from database using longitude and latitude and save it in a local cache.
			// for demo purpose I am expecting that user passes storeid as the input
		}else {
			 pl = pdao.findByStore(storeId, Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
		}
		
		return ResponseEntity.ok(pl);
	}
	


}
