package com.example.Redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class RedisMsgPublisher implements RedisPublisherInterface {

	private Gson gson = new Gson();

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private ChannelTopic topic;

	public RedisMsgPublisher(RedisTemplate<String, String> redisTemplate, ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	public void publish(RedisMsg<?> message) {
		redisTemplate.convertAndSend(topic.getTopic(), gson.toJson(message));
	}

}
