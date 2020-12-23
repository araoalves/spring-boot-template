package com.template.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.template.model.Cliente;

@Service
@Profile("kafka")
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "Kafka_Example_02", groupId = "group_id", containerFactory = "clienteKafkaListenerFactory")
	public void consumerCliente(Cliente cliente) {
		logger.info("Consumindo Cliente: "+cliente);
	}

}
