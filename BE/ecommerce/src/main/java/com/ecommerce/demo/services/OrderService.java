package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.OrderUpdateDto;
import com.ecommerce.demo.dto.response.OrderResponseDto;

public interface OrderService {
	public OrderResponseDto getOrderById(Long id);

	public OrderResponseDto createOrder(OrderUpdateDto dto);

	public OrderResponseDto updateOrder(OrderUpdateDto dto);

	public void deleteOrder(Long id);
}
