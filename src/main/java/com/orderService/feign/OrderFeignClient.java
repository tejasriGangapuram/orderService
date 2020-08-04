package com.orderService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderService.pojo.OrderItem;

@FeignClient(name = "order-item-service")
public interface OrderFeignClient {

	 @GetMapping("/orderItem/fetchByOrderId/{orderId}")
	 public List<OrderItem> fetchByOrderId(@PathVariable("orderId") String orderId);
	 @PostMapping("/orderItem/addList")
	 public List<OrderItem> addOrderItems(@RequestBody List<OrderItem> orderItem);
}
