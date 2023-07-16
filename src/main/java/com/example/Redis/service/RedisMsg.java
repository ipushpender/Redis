package com.example.Redis.service;


import lombok.Data;

@Data
public class RedisMsg<T> {
	RedisMessageType type;
	String username;
	String age;
	long eventTime = System.currentTimeMillis();
	long userId;
	T message;
}
