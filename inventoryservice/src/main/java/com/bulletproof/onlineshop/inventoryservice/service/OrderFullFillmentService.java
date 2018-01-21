package com.bulletproof.onlineshop.inventoryservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

//This service can listen for created orders over a message queue
// and fullfill the orders by communicating with other services like shipping service.
public class OrderFullFillmentService {

	@RabbitListener(queues = "OrderQueue")
	//need to model the order object with relevant attributes
	public void processMessage(Object object) {
		//logic
	}
	
}
