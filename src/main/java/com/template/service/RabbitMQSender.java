package com.template.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.config.rebbitmq.RabbitMQConfig;
import com.template.model.Cliente;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private RabbitMQConfig rabbitMQConfig;
	
	public void sendAluno(Cliente cliente) {
		amqpTemplate.convertAndSend(rabbitMQConfig.getApp1Exchange(), rabbitMQConfig.getApp1RoutingKey(), cliente);
		System.out.println("Enviando Cliente = " + cliente);	    
	}
}