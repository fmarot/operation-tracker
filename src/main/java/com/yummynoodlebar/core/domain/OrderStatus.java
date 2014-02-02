package com.yummynoodlebar.core.domain;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;

import com.yummynoodlebar.events.orders.OrderStatusDetails;

@Getter
public class OrderStatus {

	private UUID orderId;
	private UUID id;
	private Date statusDate;
	private String status;

	public OrderStatus(UUID orderId, UUID id, final Date date, final String status) {
		this.orderId = orderId;
		this.id = id;
		this.status = status;
		this.statusDate = date;
	}

	public OrderStatusDetails toStatusDetails() {
		return new OrderStatusDetails(orderId, id, statusDate, status);
	}

	public static OrderStatus fromStatusDetails(OrderStatusDetails orderStatusDetails) {
		return new OrderStatus(orderStatusDetails.getOrderId(), orderStatusDetails.getId(), orderStatusDetails.getStatusDate(),
				orderStatusDetails.getStatus());
	}
}
