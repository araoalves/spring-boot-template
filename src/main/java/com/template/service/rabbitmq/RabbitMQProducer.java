package com.template.service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.template.config.rebbitmq.RabbitMQConfig;
import com.template.model.Cliente;

@Service
@Profile("rabbitmq")
public class RabbitMQProducer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private RabbitMQConfig rabbitMQConfig;
	
	public void sendAluno(Cliente cliente) {
		amqpTemplate.convertAndSend(rabbitMQConfig.getApp1Exchange(), rabbitMQConfig.getApp1RoutingKey(), cliente);
		System.out.println("Enviando Cliente = " + cliente);	    
	}
}