package com.orderService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderService.entity.Order;
import com.orderService.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService service;
	
	//FETCH ALL ORDERS	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<Order>>  fetchAll()
	{
	return new  ResponseEntity<List<Order>>(service.findAll(),HttpStatus.OK);
	}
	
	//FETCH ORDER BY ID 
	@GetMapping("/fetchByOrderId/{orderId}")
	public ResponseEntity<Order> fetchByOrderId(@PathVariable("orderId")String orderId)
	{
		return new  ResponseEntity<Order>(service.findById(orderId),HttpStatus.OK);
	}
	//ADD ORDER
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Order order){
		service.addOrder(order);
		return new ResponseEntity<String>("SUCCESSFULLY ADDED",HttpStatus.OK);
	}
	// DELETE ORDER By Id
	@PostMapping("/deleteByOrderId/{orderId}")
	public ResponseEntity<String> deleteByOrderId(@PathVariable("orderId")String orderId){
		service.deleteById(orderId);
		return new ResponseEntity<String>("SUCCESSFULLY DELETED",HttpStatus.OK);
	}
	
}
