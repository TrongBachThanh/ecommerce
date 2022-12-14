package com.ecommerce.demo.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ecommerce.demo.constants.EOrderStatus;
import com.ecommerce.demo.constants.EPaymentType;

@Entity
@Table(name="orders")
public class OrderEntity extends BaseEntity{
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="delivery_date")
	private Date deliveryDate;
	
	@Column(name="total_price")
	private Double totalPrice;
	
	@Column(name="note")
	private String note;
	
	@Column(name="ship")
	private Double ship;
	
	@Enumerated(EnumType.STRING)
	EPaymentType paymentType;
	
	@Enumerated(EnumType.STRING)
	EOrderStatus orderStatus;
	
	@Column(name="cancel_reson")
	private String cancelReson;


	
}
