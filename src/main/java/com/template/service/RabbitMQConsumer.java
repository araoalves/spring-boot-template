package com.template.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.template.model.Cliente;

@Component
public class RabbitMQConsumer {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = "javainuse.queue")
	public void recievedMessage(Cliente cliente) {
		logger.info("Recebendo clinte do RabbitMQ: " + cliente);
	}
}