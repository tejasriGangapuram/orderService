package com.orderService.pojo;

import java.io.Serializable;

public class OrderItem  implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


private String orderId;
 
private String productCode;
 
private String productName;

private int quantity;



public OrderItem() {
	super();
}

public OrderItem(String orderId, String productCode, String productName, int quantity) {
	super();
	this.orderId = orderId;
	this.productCode = productCode;
	this.productName = productName;
	this.quantity = quantity;
}

public String getOrderId() {
	return orderId;
}

public void setOrderId(String orderId) {
	this.orderId = orderId;
}

public String getProductCode() {
	return productCode;
}

public void setProductCode(String productCode) {
	this.productCode = productCode;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}
