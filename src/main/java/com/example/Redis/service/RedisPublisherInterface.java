package com.example.Redis.service;

public interface RedisPublisherInterface {

	void publish(RedisMsg<?> msg);
}
