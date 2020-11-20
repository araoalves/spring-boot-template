package com.template.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.template.model.Cliente;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;	
	String kafkaTopic = "java_in_use_topic";		
	
	public void sendAluno(Cliente cliente) {
		amqpTemplate.convertAndSend(exchange, routingkey, cliente);
		System.out.println("Enviando Cliente = " + cliente);
	    
	}
}