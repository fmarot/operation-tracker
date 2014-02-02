package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.RequestReadEvent;

import java.util.UUID;

public class RequestOrderStatusEvent extends RequestReadEvent {
	private UUID key;

	public RequestOrderStatusEvent(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
