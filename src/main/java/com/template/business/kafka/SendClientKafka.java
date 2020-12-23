package com.template.business.kafka;

import com.template.model.Cliente;
import com.template.service.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class SendClientKafka implements ISendClientKafka {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public Cliente sendClientKafka(Cliente cliente) throws Exception {
        kafkaProducer.sendCliente(cliente);
        return cliente;
    }
}
