package com.example.Redis.service;

import java.lang.reflect.Type;

import org.json.JSONObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.example.Redis.model.SampleClass;
import com.example.Redis.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisSubscriber implements MessageListener{

	private Gson gson = new Gson();
	private ApplicationEventPublisher publisher;
	
	
	public RedisSubscriber(ApplicationEventPublisher publisher) {
		this.publisher =publisher;	
	}
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			RedisUpdateEvent event = new RedisUpdateEvent(getRedisMessage(message.toString()));
		    this.publisher.publishEvent(event);
		} catch (Exception e) {
			log.error("Error while subscribe redis message :-" + message.toString(), e);
		}
		
	}

	private RedisMsg<?> getRedisMessage(String msg) {

		Type type = new TypeToken<RedisMsg<String>>() {}.getType();// to fetch Type of RedisMessageType 
		
		switch (RedisMessageType.valueOf(new JSONObject(msg).get("type").toString())) {
		case TYPEA:
			//on basis of TYPEA we will identify Msg dataType
			type = new TypeToken<RedisMsg<SampleClass>>() {}.getType();
			break;
		case TYPEB:
			//on basis of TYPEB we will identify Msg dataType
			type = new TypeToken<RedisMsg<User>>() {}.getType();
			break;
		default:
			break;
		}

		return gson.fromJson(msg.toString(), type);
	}

}
