package com.yummynoodlebar.core.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.BeanUtils;

import com.yummynoodlebar.events.orders.OrderDetails;

public class Order {
	@Getter
	private final Date dateTimeOfSubmission;
	@Getter
	@Setter
	private List<MenuItem> menuItems;
	@Getter
	private final UUID key;
	@Getter
	@Setter
	private Customer customer;
	@Getter
	private OrderStatus status;
	private List<OrderStatus> statusHistory;
	@Getter
	@Setter
	private Date expectedCompletionTime;
	@Getter
	@Setter
	private BigDecimal totalCost;

	// currently 5 minutes
	private final static long ACCEPT_CANCEL_TIME = 1000 * 60 * 5;

	public Order(final Date dateTimeOfSubmission) {
		this.key = UUID.randomUUID();
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<OrderStatus>();
	}

	public Order(final UUID key, final Date dateTimeOfSubmission) {
		this.key = key;
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<OrderStatus>();
	}

	public void addStatus(OrderStatus newStatus) {
		statusHistory.add(newStatus);
		status = newStatus;
	}

	public boolean canBeDeleted() {
		// accept cancellation if within 5 minutes of placing.
		return System.currentTimeMillis() - dateTimeOfSubmission.getTime() < ACCEPT_CANCEL_TIME;
	}

	public Order withMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
		return this;
	}

	public OrderDetails toOrderDetails() {
		OrderDetails details = new OrderDetails();

		details.setDateTimeOfSubmission(getDateTimeOfSubmission());
		details.setKey(getKey());

		return details;
	}

	public static Order fromOrderDetails(OrderDetails orderDetails) {
		Order order = new Order(orderDetails.getKey(), orderDetails.getDateTimeOfSubmission());

		BeanUtils.copyProperties(orderDetails, order);

		return order;
	}
}
