package com.template.business.rabbitmq;

import com.template.model.Cliente;
import com.template.service.rabbitmq.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class RabbitmqBO implements IRabbitmqBO{

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Override
    public Cliente enviarClienteRebbitMq(Cliente cliente) {
        rabbitMQProducer.sendAluno(cliente);
        return cliente;
    }
}
