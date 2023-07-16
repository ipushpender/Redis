package com.example.Redis.service;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class RedisUpdateEvent extends ApplicationEvent{

	private RedisMsg<?> message;
	
	public RedisMsg<?> getMessage() {
		return message;
	}

	public RedisUpdateEvent(RedisMsg<?> source) {
		super("");
		this.message = source;
	}
	
}
