package com.template.config.rebbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import lombok.Data;

@Data
@Configuration
public class RabbitMQConfig {

	@Value("${app1.exchange.name}")
	private String app1Exchange;

	@Value("${app1.queue.name}")
	private String app1Queue;

	@Value("${app1.routing.key}")
	private String app1RoutingKey;

	@Value("${app2.exchange.name}")
	private String app2Exchange;

	@Value("${app2.queue.name}")
	private String app2Queue;

	@Value("${app2.routing.key}")
	private String app2RoutingKey;
	
	
	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getApp1Exchangee() {
		return new TopicExchange(this.app1Exchange);
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getApp1Queuee() {
		return new Queue(this.app1Queue);
	}
	
	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingApp1() {
		return BindingBuilder.bind(getApp1Queuee()).to(getApp1Exchangee()).with(this.app1RoutingKey);
	}
	
	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getApp2Exchangee() {
		return new TopicExchange(this.app2Exchange);
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getApp2Queuee() {
		return new Queue(this.app2Queue);
	}
	
	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingApp2() {
		return BindingBuilder.bind(getApp2Queuee()).to(getApp2Exchangee()).with(this.app2RoutingKey);
	}

	/* Bean for rabbitTemplate */
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}
	
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
	
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}

}
