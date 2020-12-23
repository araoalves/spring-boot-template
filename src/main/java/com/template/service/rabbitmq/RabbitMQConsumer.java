package com.template.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.template.model.Cliente;

@Service
@Profile("rabbitmq")
public class RabbitMQConsumer {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = "app1-queue")
	public void recievedMessageQueue1(Cliente cliente) {
		logger.info("Recebendo clinte da queue 1: " + cliente);
	}
	
	@RabbitListener(queues = "app2-queue")
	public void recievedMessageQueue2(Cliente cliente) {
		logger.info("Recebendo clinte da queue 2: " + cliente);
	}
}