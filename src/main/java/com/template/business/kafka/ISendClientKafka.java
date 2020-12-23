package com.template.business.kafka;

import com.template.model.Cliente;

public interface ISendClientKafka {
    Cliente sendClientKafka(Cliente cliente) throws Exception;
}
