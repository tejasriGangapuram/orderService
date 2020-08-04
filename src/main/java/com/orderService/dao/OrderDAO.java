package com.orderService.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orderService.entity.Order;
import com.orderService.repository.OrderRepository;

@Component
public class OrderDAO {
	@Autowired
	OrderRepository repo;
	
	public List<Order> fetchAll(){
		return repo.findAll();
	}
	public Optional<Order> fetchById(String orderId){
		return repo.findById(orderId);
	}
	public void deleteById(String orderId){
		 repo.deleteById(orderId);
		 repo.flush();
	}
	public void add(Order order){
		 repo.save(order);
		 repo.flush();
	}

}
