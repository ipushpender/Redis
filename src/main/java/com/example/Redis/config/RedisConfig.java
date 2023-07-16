package com.example.Redis.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.Redis.service.RedisMsgPublisher;
import com.example.Redis.service.RedisPublisherInterface;
import com.example.Redis.service.RedisSubscriber;

@Configuration
@EnableCaching
public class RedisConfig {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Value("${spring.redis.cluster.nodes}")
	private List<String> nodes;

	@Value("${application.redis.topicName}")
	private String topicName;

	@Value("${spring.redis.cluster.mode}")
	private boolean isCluster;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("127.0.0.1");
		redisStandaloneConfiguration.setPort(6379);// default port
//		redisStandaloneConfiguration.setPassword(null);//if user/password
//		redisStandaloneConfiguration.setUsername(null);

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
		return jedisConnectionFactory;

	}

	public RedisTemplate<String, String> redisTemplate() {

		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;

	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic(topicName);
	}

	@Bean
	RedisPublisherInterface redisPublisher() {
		return new RedisMsgPublisher(redisTemplate(), topic());
	}

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new RedisSubscriber(eventPublisher));
	}
}
