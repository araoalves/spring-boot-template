package com.template.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.template.model.Cliente;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RebbitMqTest {
	
	@Autowired
	RabbitMQProducer rabbitMQSender;
	
	@Test
	public void enviarCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Arão Alves de Farias");
		cliente.setTelefone("989565465465");
		
		rabbitMQSender.sendAluno(cliente);
	}

}
