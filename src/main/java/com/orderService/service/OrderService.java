package com.orderService.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderService.dao.OrderDAO;
import com.orderService.entity.Order;
import com.orderService.exception.InvalidOrderException;
import com.orderService.exception.OrderIDNotNullException;
import com.orderService.exception.OrderNotFoundException;
import com.orderService.feign.OrderFeignClient;
import com.orderService.pojo.OrderItem;

@Service
public class OrderService {
	Logger LOGGER=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderFeignClient feignClient;
	
	//ADD ORDER
	public void addOrder(Order order){
		if(null!=order&&null!=order.getOrderId()&&!order.getOrderId().isEmpty())
			{
			dao.add(order);
			
			//adding OrderItems using Feign Client
			
			feignClient.addOrderItems(order.getOrderItems());
			
			LOGGER.info("ORDER with Order ID "+order.getOrderId()+" added SUCCESFULLY.");
			}
		else{
			LOGGER.info("ENTER VALID ORDER DETAILS.");
			throw new InvalidOrderException();
		}
		
	}
	//FIND ALL ORDERS
	public List<Order> findAll(){
		List<Order> orderList= dao.fetchAll();
		for(Order order:orderList)
		{ String orderId=order.getOrderId();
			//fetching OrderItems Records using FeignClient Concept
			LOGGER.info("ORDER ITEMS FETCHING USING FEIGN CLIENT ");
			
			List<OrderItem> orderItem=feignClient.fetchByOrderId(orderId);
			
			
			if(!orderItem.isEmpty())
			{	order.setOrderItems(orderItem);
				orderItem.stream().forEach(p->{
				LOGGER.info("ORDER ID: "+orderId+";Product Code: "+p.getProductCode()+";ProductName: "+p.getProductName()+";Quantity: "+p.getQuantity());  
			});
			}
			else{
				LOGGER.info("No Order Items found in DB for the ORDER ID: "+orderId);
			}
		}
		orderList.stream().forEach(s->{
			LOGGER.info("ORDER ID "+s.getOrderId()+";CustomerName: "+s.getCustomerName()+";ShippingAddress: "+s.getShippingAddress()
			+";OrderDate: "+s.getOrderDate()+";TotalPrice: "+s.getTotalPrice()
			
			);
		});
		return orderList;
		
	}
	//FIND ORDER BY ID
	public Order findById(String orderId){
		Order order=null;
		if(null!=orderId&&!orderId.isEmpty())
		{	
			Optional<Order> opt=dao.fetchById(orderId);
		if(opt.isPresent())
		{
			order=opt.get();
			//fetching OrderIems Records using FeignClient Concept
			LOGGER.info("ORDER ITEMS FETCHING USING FEIGN CLIENT ");
			
			List<OrderItem> orderItem=feignClient.fetchByOrderId(orderId);
			
			
			if(!orderItem.isEmpty())
			{
				order.setOrderItems(orderItem);
				orderItem.stream().forEach(p->{
				LOGGER.info("Product Code: "+p.getProductCode()+";ProductName: "+p.getProductName()+";Quantity: "+p.getQuantity());  
			});
			}
			else{
				LOGGER.info("No Order Items found in DB for the ORDER ID: "+orderId);
			}
		}
		else{
			LOGGER.info("ORDER ID is not found in DB."+orderId);
			throw new OrderNotFoundException(orderId);
		}
		}
		else{
			LOGGER.info("ORDER ID cannot be NULL/EMPTY");
			throw new OrderIDNotNullException();
		}
		return order;
	}
	//DELETE ORDER BY ID	
			public void deleteById(String orderId){
		if(null!=orderId&&!orderId.isEmpty())
		{
			dao.deleteById(orderId);
			LOGGER.info("DELETED OrderID "+orderId+" SUCCESSFULLY");
		}else{
			LOGGER.info("ORDER ID cannot be NULL/EMPTY");
			throw new OrderIDNotNullException();
		}
		
	}
}
