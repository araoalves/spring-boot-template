package com.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.template.model.Cliente;

@Service
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, Cliente> kafkaTemplate;
	
	private static final String TOPIC = "Kafka_Example_02";

	public void sendCliente(Cliente cliente) {
		kafkaTemplate.send(TOPIC,cliente);
		System.out.println("Enviando Cliente = " + cliente);	  
	}

}
