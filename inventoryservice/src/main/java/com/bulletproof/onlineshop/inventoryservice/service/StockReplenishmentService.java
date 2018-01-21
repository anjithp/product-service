package com.bulletproof.onlineshop.inventoryservice.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bulletproof.onlineshop.api.StockOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 *Defines endpoints for stock management.
 * 
 *@author Anjith
 *@since 1.0.0
 */
@RestController
@RequestMapping("/services/rest/stockorders")
@Api(value="Inventory Management")
public class StockReplenishmentService {

	
	//We can set triggers for product quantity and whenever the conditions are reached we can automatically call
	//this endpoint
	@RequestMapping( method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a stock order",response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a stock order",
            		responseHeaders= {@ResponseHeader(name="Location")}),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
	public ResponseEntity<StockOrder> orderStocks(@RequestBody StockOrder po) {
		//logic
		return null;
	}

	
}
